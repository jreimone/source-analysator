package net.reimone.sourceanalysator.core.impl;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.net.URI;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFHyperlink;
import org.apache.poi.xwpf.usermodel.XWPFHyperlinkRun;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.eclipse.emf.ecore.util.EcoreUtil;

import com.google.common.collect.Lists;
import com.google.common.net.InternetDomainName;

import net.reimone.sourceanalysator.Article;
import net.reimone.sourceanalysator.GeneralSource;
import net.reimone.sourceanalysator.Hyperlink;
import net.reimone.sourceanalysator.Library;
import net.reimone.sourceanalysator.Source;
import net.reimone.sourceanalysator.SourceanalysatorFactory;
import net.reimone.sourceanalysator.core.IExporter;
import net.reimone.sourceanalysator.core.ILibraryFactory;
import net.reimone.sourceanalysator.core.ISourceAnalysator;

public class SourceAnalysator implements ISourceAnalysator {

	private static Logger logger = Logger.getLogger(SourceAnalysator.class.getName());

	private Library library;
	private ILibraryFactory libraryFactory;

	@Override
	public Map<GeneralSource, List<Source>> getGeneralSourcesOfArticle(Article article) {
		if (article == null) {
			return Collections.emptyMap();
		}
		return getGeneralSourcesOfArticles(Lists.newArrayList(article));
	}

	@Override
	public Map<GeneralSource, List<Source>> getGeneralSourcesOfArticles(List<Article> articles) {
		if (articles == null) {
			return Collections.emptyMap();
		}

		if (library == null) {
			initLibrary();
		}

		Map<GeneralSource, List<Source>> result = new LinkedHashMap<>();
		// must be a list instead of a set since sources can be referenced more than once from different articles
		List<Source> sources = new LinkedList<>();
		for (Article article : articles) {
			List<Source> articleSources = article.getSources();
			sources.addAll(articleSources);
		}
		for (Source source : sources) {
			GeneralSource generalSource = source.getGeneralSource();
			if (generalSource == null) {
				continue;
			}

			List<Source> generalSourceSources = result.getOrDefault(generalSource, new LinkedList<>());
			result.putIfAbsent(generalSource, generalSourceSources);
			// no containment check because same source can be referenced several times from different articles
			generalSourceSources.add(source);
		}
		return result;
	}

	@Override
	public void initialize(ILibraryFactory libraryFactory) {
		this.libraryFactory = libraryFactory;
		initLibrary();
	}

	private void initLibrary() {
		if (libraryFactory == null) {
			throw new NullPointerException("The library factory hasn't been set. Invoke initialize(..) first");
		}

		if (library == null) {
			library = libraryFactory.createLibrary();
		}
	}

	@Override
	public Library getSingleLibrary() {
		initLibrary();
		return library;
	}

	@Override
	public Article createOrGetArticle(String articleTitle, String localFile) {
		if (articleTitle == null || articleTitle.isEmpty()) {
			return null;
		}

		if (localFile == null || localFile.isEmpty()) {
			return null;
		}

		Article article = getArticleByTitleAndLocalFile(articleTitle, localFile);
		if (article != null) {
			return article;
		}

		article = SourceanalysatorFactory.eINSTANCE.createArticle();
		article.setTitle(articleTitle);
		article.setLocalFile(localFile);
		getSingleLibrary().getArticles().add(article);
		return article;
	}

	@Override
	public GeneralSource createOrGetGeneralSource(String generalSourceName) {
		GeneralSource generalSource = getGeneralSourceByName(generalSourceName);
		if (generalSource != null) {
			return generalSource;
		}

		generalSource = SourceanalysatorFactory.eINSTANCE.createGeneralSource();
		generalSource.setName(generalSourceName);
		library.getGeneralSources().add(generalSource);
		return generalSource;
	}

	private Article getArticleByTitleAndLocalFile(String articleTitle, String localFile) {
		if (articleTitle == null || articleTitle.isEmpty()) {
			return null;
		}

		if (localFile == null || localFile.isEmpty()) {
			return null;
		}

		List<Article> articles = library.getArticles();
		for (Article article : articles) {
			if (article.getTitle().equals(articleTitle)) {
				if (article.getLocalFile().equals(localFile)) {
					return article;
				}
			}
		}

		return null;
	}

	private GeneralSource getGeneralSourceByName(String generalSourceName) {
		if (generalSourceName == null || generalSourceName.isEmpty()) {
			return null;
		}

		List<GeneralSource> generalSources = library.getGeneralSources();
		for (GeneralSource generalSource : generalSources) {
			if (generalSource.getName().equals(generalSourceName)) {
				return generalSource;
			}
		}

		return null;
	}

	@Override
	public String recommendGeneralSourceName(Hyperlink hyperlink) {
		if (hyperlink == null) {
			return null;
		}
		
		String url = hyperlink.getUrl();
		InternetDomainName topPrivateDomain = getPrivateDomainNameForURL(url);
		String privateDomainName = topPrivateDomain.toString();

		// first, search for existing aliases
		GeneralSource generalSourceByAlias = getGeneralSourceByAlias(privateDomainName);
		if (generalSourceByAlias != null) {
			return generalSourceByAlias.getName();
		}

		// second, return the domain
		String[] split = privateDomainName.split("\\.");
		String domain = split[0];
		return domain;
	}

	private InternetDomainName getPrivateDomainNameForURL(String url) {
		if (url == null || url.isEmpty()) {
			return null;
		}

		URI uri = URI.create(url);
		String host = uri.getHost();

		InternetDomainName domainName = null;
		if (host != null) {
			// when the url has no protocol like 'http://'
			domainName = InternetDomainName.from(host);
		} else {
			// get url without path or query
			url = url.split("/")[0];
			domainName = InternetDomainName.from(url);
		}

		InternetDomainName topPrivateDomain = domainName.topPrivateDomain();
		return topPrivateDomain;
	}

	private GeneralSource getGeneralSourceByAlias(String alias) {
		if (alias == null || alias.isEmpty()) {
			return null;
		}

		List<GeneralSource> generalSources = library.getGeneralSources();
		for (GeneralSource generalSource : generalSources) {
			List<String> aliases = generalSource.getAliases();
			for (String generalSourceAlias : aliases) {
				if (generalSourceAlias.equals(alias)) {
					return generalSource;
				}
			}
		}

		return null;
	}

	@Override
	public Hyperlink createOrGetHyperlink(String url) {
		if (url == null || url.isEmpty()) {
			return null;
		}

		Hyperlink hyperlink = getHyperlinkByURL(url);
		if (hyperlink != null) {
			return hyperlink;
		}

		hyperlink = SourceanalysatorFactory.eINSTANCE.createHyperlink();
		hyperlink.setUrl(url);
		library.getHyperlinks().add(hyperlink);
		return hyperlink;
	}

	private Hyperlink getHyperlinkByURL(String sourceURL) {
		if (sourceURL == null || sourceURL.isEmpty()) {
			return null;
		}

		List<Hyperlink> hyperlinks = library.getHyperlinks();
		for (Hyperlink hyperlink : hyperlinks) {
			if (hyperlink.getUrl().equals(sourceURL)) {
				return hyperlink;
			}
		}

		return null;
	}

	@Override
	public void linkSourceWithGeneralSource(Source source, GeneralSource generalSource) {
		if (source == null) {
			return;
		}

		if (generalSource == null) {
			return;
		}

		GeneralSource oldGeneralSource = source.getGeneralSource();
		if (generalSource.equals(oldGeneralSource)) {
			return;
		}
		
		source.setGeneralSource(generalSource);
		Hyperlink hyperlink = source.getHyperlink();
		String url = hyperlink.getUrl();
		InternetDomainName domainName = getPrivateDomainNameForURL(url);
		List<String> aliases = generalSource.getAliases();
		String alias = domainName.toString();
		if (!aliases.contains(alias)) {
			aliases.add(alias);
		}
	}

	@Override
	public Set<String> retrieveHyperlinksFromLocalFile(Article article) {
		String localFile = article.getLocalFile();
		File file = new File(localFile);
		if (!file.exists()) {
			logger.severe("File " + localFile + " doesn't exist");
			return null;
		}

		if (!file.canRead()) {
			logger.severe("File " + localFile + " is not readable");
			return null;
		}

		Set<String> hyperlinkStrings = new LinkedHashSet<>();
		try (BufferedInputStream is = new BufferedInputStream(new FileInputStream(file))) {
			if (!localFile.endsWith(".docx")) {
				logger.severe("File " + localFile + " is not supported");
				return null;
			}

			try (XWPFDocument document = new XWPFDocument(is)) {
				extractHyperlinks(hyperlinkStrings, document);
			}
		} catch (Exception e) {
			logger.log(Level.SEVERE, "Error while opening the file " + localFile, e);
			return null;
		}

		return hyperlinkStrings;
	}

	// private void extractHyperlinks(Set<String> hyperlinks, XWPFDocument document) {
	// XWPFHyperlink[] hyperlinksArray = document.getHyperlinks();
	// for (XWPFHyperlink xwpfHyperlink : hyperlinksArray) {
	// String url = xwpfHyperlink.getURL();
	// if (url != null && !url.isEmpty()) {
	// hyperlinks.add(url);
	// }
	// }
	// }

	private void extractHyperlinks(Set<String> hyperlinks, XWPFDocument document) {
		Iterator<XWPFParagraph> it = document.getParagraphsIterator();
		while (it.hasNext()) {
			XWPFParagraph paragraph = it.next();

			// Do the paragraph text
			for (XWPFRun run : paragraph.getRuns()) {
				if (run instanceof XWPFHyperlinkRun) {
					XWPFHyperlinkRun xwpfHyperlinkRun = (XWPFHyperlinkRun) run;
					XWPFHyperlink hyperlink = xwpfHyperlinkRun.getHyperlink(document);
					if (hyperlink != null) {
						hyperlinks.add(hyperlink.getURL());
					}
				}
			}
		}
	}

	@Override
	public void generateSourcesForArticle(Article article) {
		List<Source> sources = Lists.newArrayList(article.getSources());
		for (Source source : sources) {
			EcoreUtil.delete(source, true);
		}
		
		Set<String> urls = retrieveHyperlinksFromLocalFile(article);
		for (String url : urls) {
			Hyperlink hyperlink = createOrGetHyperlink(url);
			
			String recommendedGeneralSourceName = recommendGeneralSourceName(hyperlink);
			GeneralSource generalSource = createOrGetGeneralSource(recommendedGeneralSourceName);
			Source source = SourceanalysatorFactory.eINSTANCE.createSource();
			source.setHyperlink(hyperlink);
			linkSourceWithGeneralSource(source, generalSource);
			article.getSources().add(source);
		}
	}
	
	@Override
	public GeneralSource setGeneralSourceOfSource(Source source, String newName) {
		if (source == null) {
			return null;
		}
		
		if (newName == null) {
			return null;
		}
		
		GeneralSource generalSource = source.getGeneralSource();
		if (generalSource == null) {
			// if not exists then return null because it has to be linked first with general source
//			String recommendedGeneralSourceName = recommendGeneralSourceName(newName);
//			generalSource = createOrGetGeneralSource(recommendedGeneralSourceName);
//			linkSourceWithGeneralSource(source, generalSource);
			return null;
		}
		
		// if new name equals old name then do nothing
		String oldName = generalSource.getName();
		if (newName.equals(oldName)) {
			return generalSource;
		}
		
		// rename general source and rotate alias
		List<String> aliases = generalSource.getAliases();
		if (aliases.contains(newName)) {
			aliases.remove(newName);
		}
		if (!aliases.contains(oldName)) {
			aliases.add(oldName);
		}
		generalSource.setName(newName);
		return generalSource;
	}

	@Override
	public File exportStatisticsOfArticlesToFile(List<Article> articles, IExporter exporter) {
		if (exporter == null) {
			return null;
		}
		
		Map<GeneralSource, List<Source>> generalSourcesOfArticles = getGeneralSourcesOfArticles(articles);
		File file = exporter.export(articles, generalSourcesOfArticles);
		return file;
	}
}
