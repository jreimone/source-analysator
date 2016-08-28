package net.reimone.sourceanalysator.product.views;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.eclipse.e4.ui.di.Focus;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormAttachment;

public class ArticleView {

	public ArticleView() {
	}

	/**
	 * Create contents of the view part.
	 */
	@PostConstruct
	public void createControls(Composite parent) {
		FormLayout fl_parent = new FormLayout();
		fl_parent.marginRight = 10;
		fl_parent.marginBottom = 10;
		fl_parent.marginLeft = 10;
		fl_parent.marginTop = 10;
		parent.setLayout(fl_parent);
		Label label = new Label(parent, SWT.LEFT);
		FormData fd_label = new FormData();
		fd_label.bottom = new FormAttachment(0, 298);
		fd_label.right = new FormAttachment(0, 448);
		fd_label.top = new FormAttachment(0);
		fd_label.left = new FormAttachment(0);
		label.setLayoutData(fd_label);
		label.setText("Article name:");
	}

	@PreDestroy
	public void dispose() {
	}

	@Focus
	public void setFocus() {
		// TODO	Set the focus to control
	}

}
