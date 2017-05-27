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
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;

import net.reimone.sourceanalysator.Library;
import net.reimone.sourceanalysator.SourceanalysatorPackage.Literals;
import net.reimone.sourceanalysator.core.ISourceAnalysator;

public class GeneralSourcesView {
	
	private DataBindingContext m_bindingContext;

	private Library library;

	private IEventBroker eventBroker;
	
	private Table table;
	private TableViewer tableViewer;

	private TableColumn tableColumnCount;
	
	@Inject
	public GeneralSourcesView(ISourceAnalysator sourceAnalysator, IEventBroker eventBroker) {
		this.eventBroker = eventBroker;
		library = sourceAnalysator.getSingleLibrary();
	}

	/**
	 * Create contents of the view part.
	 */
	@PostConstruct
	public void createControls(Composite parent) {
		parent.setLayout(new GridLayout(1, false));
		
		tableViewer = new TableViewer(parent, SWT.BORDER | SWT.FULL_SELECTION);
		table = tableViewer.getTable();
		table.setLinesVisible(true);
		table.setHeaderVisible(true);
		table.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		
		TableViewerColumn tableViewerColumn = new TableViewerColumn(tableViewer, SWT.NONE);
		TableColumn tblclmnKategorie = tableViewerColumn.getColumn();
		tblclmnKategorie.setWidth(300);
		tblclmnKategorie.setText("Kategorie");
		
		TableViewerColumn tableViewerColumn_1 = new TableViewerColumn(tableViewer, SWT.NONE);
		tableViewerColumn_1.setEditingSupport(new DontCountEditingSupport(tableViewer, eventBroker));
		tableColumnCount = tableViewerColumn_1.getColumn();
		tableColumnCount.setWidth(100);
		tableColumnCount.setText("Zählen?");
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
		IObservableMap[] observeMaps = EMFObservables.observeMaps(listContentProvider.getKnownElements(), new EStructuralFeature[]{Literals.GENERAL_SOURCE__NAME, Literals.GENERAL_SOURCE__DONT_COUNT});
		tableViewer.setLabelProvider(new DontCountLabelProvider(observeMaps, tableColumnCount));
		tableViewer.setContentProvider(listContentProvider);
		//
		IObservableList libraryGeneralSourcesObserveList = EMFObservables.observeList(Realm.getDefault(), library, Literals.LIBRARY__GENERAL_SOURCES);
		tableViewer.setInput(libraryGeneralSourcesObserveList);
		//
		return bindingContext;
	}
}
