package net.reimone.sourceanalysator.rcp.handler;

import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Shell;

import net.reimone.sourceanalysator.core.ISourceAnalysator;
import net.reimone.sourceanalysator.rcp.Util;
import net.reimone.sourceanalysator.rcp.dialog.ArticleNameDialog;

public class NewArticleHandler {

	@Execute
	public void handle(ISourceAnalysator sourceAnalysator, Shell currentShell) {
		// ask for article title
		ArticleNameDialog articleNameDialog = new ArticleNameDialog(currentShell);
		articleNameDialog.open();
		String articleTitle = articleNameDialog.getArticleTitle();
		// get local word file
		FileDialog fileDialog = Util.INSTANCE.createFileDialog(currentShell);
		String selectedLocalFile = fileDialog.open();
		// and finally create the new article
		sourceAnalysator.createOrGetArticle(articleTitle, selectedLocalFile);
	}

}
