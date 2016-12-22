package net.reimone.sourceanalysator.rcp.views;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.inject.Inject;

import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.observable.Realm;
import org.eclipse.core.databinding.observable.list.IObservableList;
import org.eclipse.core.databinding.observable.map.IObservableMap;
import org.eclipse.e4.core.services.events.IEventBroker;
import org.eclipse.e4.ui.di.Focus;
import org.eclipse.emf.databinding.EMFObservables;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.jface.databinding.viewers.ObservableListContentProvider;
import org.eclipse.jface.databinding.viewers.ObservableMapLabelProvider;
import org.eclipse.jface.layout.TableColumnLayout;
import org.eclipse.jface.viewers.CheckboxTableViewer;
import org.eclipse.jface.viewers.ColumnWeightData;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;

import net.reimone.sourceanalysator.Library;
import net.reimone.sourceanalysator.SourceanalysatorPackage.Literals;
import net.reimone.sourceanalysator.core.ISourceAnalysator;
import net.reimone.sourceanalysator.rcp.Util;

import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.ICheckStateListener;
import org.eclipse.jface.viewers.CheckStateChangedEvent;

public class ArticlesListView {
	
	private DataBindingContext m_bindingContext;

	@Inject
	private IEventBroker eventBroker;
	
	@Inject
	private ISourceAnalysator sourceAnalysator;
	private Library library;
	
	private Table table;
	private CheckboxTableViewer checkboxTableViewer;
	
	public ArticlesListView() {
	}

	/**
	 * Create contents of the view part.
	 */
	@PostConstruct
	public void createControls(Composite parent) {
		
		checkboxTableViewer = CheckboxTableViewer.newCheckList(parent, SWT.BORDER | SWT.FULL_SELECTION);
		checkboxTableViewer.addCheckStateListener(new ICheckStateListener() {
			public void checkStateChanged(CheckStateChangedEvent event) {
				Util.INSTANCE.handleArticleCheckedStateChanged(checkboxTableViewer.getCheckedElements(), eventBroker);
			}
		});
		checkboxTableViewer.addSelectionChangedListener(new ISelectionChangedListener() {
			
			public void selectionChanged(SelectionChangedEvent event) {
				Util.INSTANCE.handleArticleSelectionChanged(event, eventBroker);
			}
		});
		table = checkboxTableViewer.getTable();
		table.setLinesVisible(true);
		library = sourceAnalysator.getSingleLibrary();
		m_bindingContext = initDataBindings();
	}

	@PreDestroy
	public void dispose() {
	}

	@Focus
	public void setFocus() {
		// TODO	Set the focus to control
	}
	protected DataBindingContext initDataBindings() {
		DataBindingContext bindingContext = new DataBindingContext();
		//
		ObservableListContentProvider listContentProvider = new ObservableListContentProvider();
		IObservableMap[] observeMaps = EMFObservables.observeMaps(listContentProvider.getKnownElements(), new EStructuralFeature[]{Literals.ARTICLE__TITLE});
		checkboxTableViewer.setLabelProvider(new ObservableMapLabelProvider(observeMaps));
		checkboxTableViewer.setContentProvider(listContentProvider);
		//
		IObservableList libraryArticlesObserveList = EMFObservables.observeList(Realm.getDefault(), library, Literals.LIBRARY__ARTICLES);
		checkboxTableViewer.setInput(libraryArticlesObserveList);
		//
		return bindingContext;
	}
}
