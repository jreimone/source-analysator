package net.reimone.sourceanalysator.rcp;

import java.io.File;
import java.util.HashMap;
import java.util.List;

import org.eclipse.e4.core.services.events.IEventBroker;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.swt.program.Program;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Shell;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import net.reimone.sourceanalysator.Article;
import net.reimone.sourceanalysator.core.IExporter;
import net.reimone.sourceanalysator.core.ISourceAnalysator;
import net.reimone.sourceanalysator.rcp.exporter.WordExporter;

public class Util {

	public static final Util INSTANCE = new Util();

	private Util() {
		// singleton
	}

	public FileDialog createFileDialog(Shell shell) {
		FileDialog fileDialog = new FileDialog(shell);
		fileDialog.setText("Wähle die Word-Datei aus Junge...");
		fileDialog.setFilterExtensions(new String[] { "*.docx" });
		fileDialog.setFilterNames(new String[] { "Word-Dateien(*.docx)" });
		return fileDialog;
	}

	public void handleArticleSelectionChanged(SelectionChangedEvent event, IEventBroker eventBroker) {
		IStructuredSelection selection = (IStructuredSelection) event.getSelection();
		Object firstElement = selection.getFirstElement();
		if (firstElement instanceof Article) {
			Article selectedArticle = (Article) firstElement;
			HashMap<String, Object> data = Maps.newHashMap();
			data.put(Events.ARTICLE_SELECTION_CHANGED_ARTICLE, selectedArticle);
			eventBroker.post(Events.ARTICLE_SELECTION_CHANGED, data);
		}
	}

	public void handleArticleCheckedStateChanged(Object[] checkedObjects, IEventBroker eventBroker) {
		List<Article> checkedArticles = Lists.newArrayList();
		if (checkedObjects != null) {
			for (Object object : checkedObjects) {
				if (object instanceof Article) {
					checkedArticles.add((Article) object);
				}
			}
		}

		HashMap<String, Object> data = Maps.newHashMap();
		data.put(Events.ARTICLE_CHECKED_STATE_CHANGED_ARTICLES, checkedArticles);
		eventBroker.post(Events.ARTICLE_CHECKED_STATE_CHANGED, data);
	}
	
	public void writeAndOpenStatistics(ISourceAnalysator sourceAnalysator, List<Article> checkedArticles) {
		IExporter exporter = new WordExporter();
		File file = sourceAnalysator.exportStatisticsOfArticlesToFile(checkedArticles, exporter);
		Program.launch(file.getAbsolutePath());
	}
}
