package net.reimone.sourceanalysator.rcp;

import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Shell;

public class Util {

	public static final Util INSTANCE = new Util();
	
	private Util () {
		// singleton
	}
	
	public FileDialog createFileDialog(Shell shell) {
		FileDialog fileDialog = new FileDialog(shell);
		fileDialog.setText("Wähle die Word-Datei aus Junge...");
		fileDialog.setFilterExtensions(new String[] { "*.docx" });
		fileDialog.setFilterNames(new String[] { "Word-Dateien(*.docx)" });
		return fileDialog;
	}
}
