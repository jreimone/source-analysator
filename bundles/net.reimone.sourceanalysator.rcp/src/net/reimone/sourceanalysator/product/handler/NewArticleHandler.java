package net.reimone.sourceanalysator.product.handler;

import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Shell;

import net.reimone.sourceanalysator.core.ISourceAnalysator;
import net.reimone.sourceanalysator.product.dialog.ArticleNameDialog;

public class NewArticleHandler {

	@Execute
	public void handle(ISourceAnalysator sourceAnalysator, Shell currentShell) {
		// ask for article title
		ArticleNameDialog articleNameDialog = new ArticleNameDialog(currentShell);
		articleNameDialog.open();
		String articleTitle = articleNameDialog.getArticleTitle();
		// get local word file
		FileDialog fileDialog = new FileDialog(currentShell);
		fileDialog.setText("Wähle die Word-Datei aus Junge...");
		fileDialog.setFilterExtensions(new String[] { "*.docx" });
		fileDialog.setFilterNames(new String[] { "Word-Dateien(*.docx)" });
		String selectedLocalFile = fileDialog.open();
		// and finally create the new article
		sourceAnalysator.createOrGetArticle(articleTitle, selectedLocalFile);
	}
}
