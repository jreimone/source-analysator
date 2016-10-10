package net.reimone.sourceanalysator.rcp.views;

import java.util.Map;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.inject.Inject;

import org.eclipse.e4.core.di.annotations.Optional;
import org.eclipse.e4.ui.di.Focus;
import org.eclipse.e4.ui.di.UIEventTopic;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import net.reimone.sourceanalysator.Article;
import net.reimone.sourceanalysator.rcp.Events;
import net.reimone.sourceanalysator.rcp.Util;

import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.observable.Realm;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.jface.databinding.swt.WidgetProperties;
import org.eclipse.emf.databinding.EMFObservables;
import net.reimone.sourceanalysator.SourceanalysatorPackage.Literals;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

public class ArticleView {

	private DataBindingContext m_bindingContext;

	private Text textArticleTitle;

	private Article selectedArticle;
	private Text txtWordfile;

	public ArticleView() {
	}

	/**
	 * Create contents of the view part.
	 */
	@PostConstruct
	public void createControls(Composite parent) {
		Composite composite = new Composite(parent, SWT.NONE);
		GridLayout gl_composite = new GridLayout(2, false);
		gl_composite.marginWidth = 10;
		gl_composite.marginHeight = 10;
		composite.setLayout(gl_composite);
		Label lblArtikelTitel = new Label(composite, SWT.LEFT);
		lblArtikelTitel.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblArtikelTitel.setText("Titel:");

		textArticleTitle = new Text(composite, SWT.BORDER);
		textArticleTitle.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Label lblWorddatei = new Label(composite, SWT.NONE);
		lblWorddatei.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblWorddatei.setText("Word-Datei:");
		
		txtWordfile = new Text(composite, SWT.BORDER);
		txtWordfile.setEditable(false);
		txtWordfile.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
	}

	@Optional
	@Inject
	private void subscribeArticleSelectionChanged(
			@UIEventTopic(Events.ARTICLE_SELECTION_CHANGED) Map<String, Object> data) {
		Object selectedObject = data.get(Events.ARTICLE_SELECTION_CHANGED_ARTICLE);
		if (selectedObject != null && selectedObject instanceof Article) {
			selectedArticle = (Article) selectedObject;
			if (m_bindingContext != null) {
				Realm realm = m_bindingContext.getValidationRealm();
				realm.exec(new Runnable() {
					
					@Override
					public void run() {
						m_bindingContext.dispose();
					}
				});
			}
			m_bindingContext = initDataBindings();
			m_bindingContext.updateTargets();
		}
	}

	@PreDestroy
	public void dispose() {
	}

	@Focus
	public void setFocus() {
		// TODO Set the focus to control
	}
	protected DataBindingContext initDataBindings() {
		DataBindingContext bindingContext = new DataBindingContext();
		//
		IObservableValue observeTextTextArticleTitleObserveWidget = WidgetProperties.text(SWT.Modify).observe(textArticleTitle);
		IObservableValue selectedArticleTitleObserveValue = EMFObservables.observeValue(selectedArticle, Literals.ARTICLE__TITLE);
		bindingContext.bindValue(observeTextTextArticleTitleObserveWidget, selectedArticleTitleObserveValue, null, null);
		//
		IObservableValue observeTextTxtWordfileObserveWidget = WidgetProperties.text(SWT.Modify).observe(txtWordfile);
		IObservableValue selectedArticleLocalFileObserveValue = EMFObservables.observeValue(selectedArticle, Literals.ARTICLE__LOCAL_FILE);
		bindingContext.bindValue(observeTextTxtWordfileObserveWidget, selectedArticleLocalFileObserveValue, null, null);
		//
		return bindingContext;
	}
}
