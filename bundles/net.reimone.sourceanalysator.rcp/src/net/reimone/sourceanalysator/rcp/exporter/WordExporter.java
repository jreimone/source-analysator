package net.reimone.sourceanalysator.rcp.exporter;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Platform;
import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.io.Files;

import net.reimone.sourceanalysator.Article;
import net.reimone.sourceanalysator.GeneralSource;
import net.reimone.sourceanalysator.Source;
import net.reimone.sourceanalysator.core.IExporter;

public class WordExporter implements IExporter {

	private static final Logger LOGGER = Logger.getLogger(WordExporter.class.getName());

	private static final String TEMPLATE_NAME = "template.docx";
	private static final String PLACEHOLDER_ARTICLES = "<<SELECTED_ARTICLES>>";
	private static final String PLACEHOLDER_COUNTS = "<<COUNT_AND_SOURCE>>";

	private static final int DEFAULT_FONT_SIZE = 11;

	@Override
	public File export(List<Article> articles, List<Entry<String, Integer>> statistics) {
		XWPFDocument template = openTemplate();
		if (template == null) {
			return null;
		}

		instantiateTemplate(template, articles, statistics);
		File file = saveDocument(template);
		return file;
	}

	private void instantiateTemplate(XWPFDocument template, List<Article> articles,
			List<Entry<String, Integer>> statistics) {
		boolean placeholderAvailable = true;
		if (articles != null && !articles.isEmpty()) {
			for (Article article : articles) {
				if (!placeholderAvailable) {
					break;
				}
				String title = article.getTitle();
				int index = articles.indexOf(article);
				boolean lastElement = (index == articles.size() - 1) ? true : false;
				placeholderAvailable = replaceTextOfSequence(template, PLACEHOLDER_ARTICLES, title, lastElement);
			}
		}

		if (statistics == null || statistics.isEmpty()) {
			return;
		}

		int totalCount = statistics.size();
		int currentEntry = 0;
		placeholderAvailable = true;
		for (Entry<String, Integer> entry : statistics) {
			if (!placeholderAvailable) {
				break;
			}
			currentEntry++;
			String name = entry.getKey();
			int sourceCount = entry.getValue();
			String text = sourceCount + "x " + name;

			boolean lastElement = (currentEntry == totalCount) ? true : false;
			placeholderAvailable = replaceTextOfSequence(template, PLACEHOLDER_COUNTS, text, lastElement);
		}
	}

	private boolean replaceTextOfSequence(XWPFDocument template, String placeholder, String replacement,
			boolean lastElement) {
		XWPFRun detectedRun = null;
		XWPFParagraph detectedParagraph = null;
		int runPosition = -1;

		List<XWPFParagraph> paragraphs = template.getParagraphs();
		if (paragraphs == null) {
			return false;
		}

		Iterator<XWPFParagraph> iterator = paragraphs.iterator();
		do {
			XWPFParagraph paragraph = iterator.next();
			List<XWPFRun> runs = paragraph.getRuns();
			if (runs != null) {
				runPosition = 0;
				for (XWPFRun run : runs) {
					String text = run.getText(0);
					// only replace if the whole line is a placeholder
					if (text != null && text.equals(placeholder)) {
						detectedRun = run;
						detectedParagraph = paragraph;
						break;
					}
					runPosition++;
				}
			}
		} while (detectedRun == null && iterator.hasNext());

		if (detectedRun == null) {
			return false;
		}

		if (!lastElement) {
			// create new run in front of found placeholder run when not last element
			XWPFRun runCopy = detectedParagraph.insertNewRun(runPosition);
			runCopy.setText(replacement, 0);
			// and copy the styling
			int fontSize = detectedRun.getFontSize();
			runCopy.setFontSize((fontSize == -1) ? DEFAULT_FONT_SIZE : fontSize);
			runCopy.setFontFamily(detectedRun.getFontFamily());
			runCopy.setBold(detectedRun.isBold());
			runCopy.setItalic(detectedRun.isItalic());
			runCopy.setStrikeThrough(detectedRun.isStrikeThrough());
			runCopy.setColor(detectedRun.getColor());
			runCopy.addCarriageReturn();
		} else {
			// otherwise just set the text
			detectedRun.setText(replacement, 0);
		}
		return true;
	}

	private XWPFDocument openTemplate() {
		InputStream inputStream = null;
		// 1. try to get the template from workspace
		inputStream = getTemplateStream();

		// 2. if not available copy the internal template
		if (inputStream == null) {
			URL templateURL = getClass().getResource(TEMPLATE_NAME);
			if (templateURL != null) {
				File templateFile = getTemplateFile();
				try {
					Files.copy(() -> templateURL.openStream(), templateFile);
					inputStream = getTemplateStream();
				} catch (IOException e) {
					LOGGER.log(Level.SEVERE, "Couldn't load word default template", e);
				}
			}
		}

		if (inputStream == null) {
			return null;
		}

		XWPFDocument document = null;
		try {
			document = new XWPFDocument(inputStream);
		} catch (IOException e) {
			LOGGER.log(Level.SEVERE, "Couldn't load word default template", e);
		}
		return document;
	}

	private InputStream getTemplateStream() {
		IWorkspace workspace = ResourcesPlugin.getWorkspace();
		if (workspace == null) {
			return null;
		}
		
		IWorkspaceRoot root = workspace.getRoot();
		if (root == null) {
			return null;
		}

		File existingTemplateFile = getTemplateFile();
		if (existingTemplateFile.exists() && existingTemplateFile.canRead()) {
			try {
				InputStream inputStream = existingTemplateFile.toURI().toURL().openStream();
				return inputStream;
			} catch (MalformedURLException e) {
				LOGGER.log(Level.INFO, "Couldn't load template file from workspace, thus default template is taken", e);
			} catch (IOException e) {
				LOGGER.log(Level.INFO, "Couldn't load template file from workspace, thus default template is taken", e);
			}
		}

		return null;
	}

	private File getTemplateFile() {
		Bundle thisBundle = FrameworkUtil.getBundle(getClass());
		IPath bundleDataArea = Platform.getStateLocation(thisBundle);
		IPath path = bundleDataArea.append(TEMPLATE_NAME);
		File existingTemplateFile = new File(path.toString());
		return existingTemplateFile;
	}

	private File saveDocument(XWPFDocument document) {
		File tempFile = null;
		try {
			tempFile = File.createTempFile("export-", ".docx");
		} catch (IOException e) {
			LOGGER.log(Level.SEVERE, "Couldn't create temporary word file", e);
			return null;
		}

		try (FileOutputStream out = new FileOutputStream(tempFile)) {
			document.write(out);
		} catch (IOException e) {
			LOGGER.log(Level.SEVERE, "Couldn't write to word file", e);
		}

		return tempFile;
	}

	@Override
	public List<Entry<String, Integer>> generateStatisticsForGeneralSourcesOfArticles(
			Map<GeneralSource, List<Source>> generalSourcesOfArticles) {
		List<Entry<String, Integer>> result = Lists.newArrayList();
		if (generalSourcesOfArticles == null) {
			return result;
		}

		Map<String, Integer> categoryCounts = Maps.newHashMap();
		for (Entry<GeneralSource, List<Source>> entry : generalSourcesOfArticles.entrySet()) {
			GeneralSource generalSource = entry.getKey();
			List<Source> sources = entry.getValue();
			int count = sources.size();
			
			String categoryName = generalSource.getName();
			Integer finalCount = categoryCounts.get(categoryName);
			if (finalCount == null) {
				finalCount = 0;
			}
			finalCount += count;
			categoryCounts.put(categoryName, finalCount);
		}
		
		result = categoryCounts.entrySet().stream().sorted(Map.Entry.comparingByValue(Collections.reverseOrder())).collect(Collectors.toList());
		return result;
	}
}
