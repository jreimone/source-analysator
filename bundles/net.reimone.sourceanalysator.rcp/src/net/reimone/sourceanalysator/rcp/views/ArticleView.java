package net.reimone.sourceanalysator.rcp.views;

import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.inject.Inject;

import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.observable.Realm;
import org.eclipse.core.databinding.observable.list.IObservableList;
import org.eclipse.core.databinding.observable.map.IObservableMap;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.e4.core.di.annotations.Optional;
import org.eclipse.e4.ui.di.Focus;
import org.eclipse.e4.ui.di.UIEventTopic;
import org.eclipse.emf.databinding.EMFObservables;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.jface.databinding.swt.WidgetProperties;
import org.eclipse.jface.databinding.viewers.ObservableListContentProvider;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.layout.TableColumnLayout;
import org.eclipse.jface.viewers.ColumnPixelData;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.Text;

import net.reimone.sourceanalysator.Article;
import net.reimone.sourceanalysator.Source;
import net.reimone.sourceanalysator.SourceanalysatorPackage.Literals;
import net.reimone.sourceanalysator.core.ISourceAnalysator;
import net.reimone.sourceanalysator.rcp.Events;

public class ArticleView {

	private DataBindingContext m_bindingContext;

	@Inject
	private ISourceAnalysator sourceAnalysator;
	
	private Text textArticleTitle;

	private Article selectedArticle;
	private Text txtWordfile;
	private Table table;
	private TableViewer tableViewer;

	private TableColumn tableColumnURL;
	private TableColumn tableColumnGeneralSource;

	public ArticleView() {
	}

	/**
	 * Create contents of the view part.
	 */
	@PostConstruct
	public void createControls(Composite parent) {
		GridLayout gl_parent = new GridLayout(1, false);
		parent.setLayout(gl_parent);
		Composite compositeMetaInformation = new Composite(parent, SWT.NONE);
		compositeMetaInformation.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		GridLayout gl_compositeMetaInformation = new GridLayout(2, false);
		compositeMetaInformation.setLayout(gl_compositeMetaInformation);
		Label lblArtikelTitel = new Label(compositeMetaInformation, SWT.LEFT);
		lblArtikelTitel.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblArtikelTitel.setText("Titel:");

		textArticleTitle = new Text(compositeMetaInformation, SWT.BORDER);
		textArticleTitle.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));

		Label lblWorddatei = new Label(compositeMetaInformation, SWT.NONE);
		lblWorddatei.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblWorddatei.setText("Word-Datei:");

		txtWordfile = new Text(compositeMetaInformation, SWT.BORDER);
		txtWordfile.setEditable(false);
		txtWordfile.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));

		Composite compositeButtons = new Composite(parent, SWT.NONE);
		compositeButtons.setLayout(new GridLayout(1, false));
		compositeButtons.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));

		Button btnAnalyze = new Button(compositeButtons, SWT.NONE);
		btnAnalyze.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				handleAnalyzeButton(e.display);
			}
		});
		btnAnalyze.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, true, false, 1, 1));
		btnAnalyze.setText("Analysieren");
		
		Composite compositeSourcesTable = new Composite(parent, SWT.NONE);
		compositeSourcesTable.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		TableColumnLayout tcl_compositeSourcesTable = new TableColumnLayout();
		compositeSourcesTable.setLayout(tcl_compositeSourcesTable);
		
		tableViewer = new TableViewer(compositeSourcesTable, SWT.BORDER | SWT.FULL_SELECTION);
		table = tableViewer.getTable();
		table.setHeaderVisible(true);
		table.setLinesVisible(true);
		
		TableViewerColumn tableViewerColumnURL = new TableViewerColumn(tableViewer, SWT.NONE);
		tableColumnURL = tableViewerColumnURL.getColumn();
		tcl_compositeSourcesTable.setColumnData(tableColumnURL, new ColumnPixelData(500, true, true));
		tableColumnURL.setText("URL");
		
		TableViewerColumn tableViewerColumnGeneralSource = new TableViewerColumn(tableViewer, SWT.NONE);
		tableColumnGeneralSource = tableViewerColumnGeneralSource.getColumn();
		tcl_compositeSourcesTable.setColumnData(tableColumnGeneralSource, new ColumnPixelData(150, true, true));
		tableColumnGeneralSource.setText("Kategorie");
		tableViewerColumnGeneralSource.setEditingSupport(new GeneralSourceEditingSupport(tableViewer, sourceAnalysator));
//		m_bindingContext = initDataBindings();
	}

	protected void handleAnalyzeButton(Display display) {
		List<Source> sources = selectedArticle.getSources();
		boolean generateSources = true;
		if (!sources.isEmpty()) {
			String title = "Quellen überschreiben?";
			String message = "Diese Aktion überschreibt die existierenden Quellen und analysiert das Word-Dokument erneut.\n"
					+ "Möchtest du wirklich fortfahren?";
			generateSources = MessageDialog.openQuestion(display.getActiveShell(), title, message);
		}
		
		if (generateSources) {
			sourceAnalysator.generateSourcesForArticle(selectedArticle);
		}
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
		ObservableListContentProvider listContentProvider = new ObservableListContentProvider();
		IObservableMap[] observeMaps = EMFObservables.observeMaps(listContentProvider.getKnownElements(), new EStructuralFeature[]{Literals.SOURCE__URL, Literals.SOURCE__GENERAL_SOURCE});
		tableViewer.setLabelProvider(new ArticleSourcesLabelProvider(observeMaps, tableColumnGeneralSource));
		tableViewer.setContentProvider(listContentProvider);
		//
		IObservableList selectedArticleSourcesObserveList = EMFObservables.observeList(Realm.getDefault(), selectedArticle, Literals.ARTICLE__SOURCES);
		tableViewer.setInput(selectedArticleSourcesObserveList);
		//
		return bindingContext;
	}
}
