package net.reimone.sourceanalysator.rcp.views;

import java.util.HashMap;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.inject.Inject;

import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.observable.Realm;
import org.eclipse.core.databinding.observable.list.IObservableList;
import org.eclipse.e4.core.services.events.IEventBroker;
import org.eclipse.e4.ui.di.Focus;
import org.eclipse.emf.databinding.EMFObservables;
import org.eclipse.jface.databinding.viewers.ObservableListTreeContentProvider;
import org.eclipse.jface.viewers.CheckboxTreeViewer;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.ITreeSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Tree;

import com.google.common.collect.Maps;

import net.reimone.sourceanalysator.Article;
import net.reimone.sourceanalysator.Library;
import net.reimone.sourceanalysator.SourceanalysatorPackage.Literals;
import net.reimone.sourceanalysator.core.ISourceAnalysator;
import net.reimone.sourceanalysator.rcp.Events;
import net.reimone.sourceanalysator.rcp.databinding.EMFBeansListObservableFactory;
import net.reimone.sourceanalysator.rcp.databinding.EMFTreeBeanAdvisor;
import net.reimone.sourceanalysator.rcp.databinding.EMFTreeObservableLabelProvider;

public class ArticlesTreeView {
	
	private DataBindingContext m_bindingContext;

	@Inject
	private ISourceAnalysator sourceAnalysator;
	@Inject
	private IEventBroker eventBroker;
	private Library library;
	
	private CheckboxTreeViewer checkboxTreeViewer;
	
	public ArticlesTreeView() {
	}

	/**
	 * Create contents of the view part.
	 */
	@PostConstruct
	public void createControls(Composite parent) {
		library = sourceAnalysator.getSingleLibrary();
		checkboxTreeViewer = new CheckboxTreeViewer(parent, SWT.BORDER | SWT.CHECK);
		Tree tree = checkboxTreeViewer.getTree();
		checkboxTreeViewer.addSelectionChangedListener(new ISelectionChangedListener() {
			
			@Override
			public void selectionChanged(SelectionChangedEvent event) {
				handleSelectionChanged(event);
			}
		});
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
		EMFBeansListObservableFactory treeObservableFactory = new EMFBeansListObservableFactory(Article.class, Literals.ARTICLE__SOURCES);
		EMFTreeBeanAdvisor treeAdvisor = new EMFTreeBeanAdvisor(null, Literals.ARTICLE__SOURCES, null);
		ObservableListTreeContentProvider treeContentProvider = new ObservableListTreeContentProvider(treeObservableFactory, treeAdvisor);
		checkboxTreeViewer.setLabelProvider(new EMFTreeObservableLabelProvider(treeContentProvider.getKnownElements(), Literals.ARTICLE__TITLE, null));
		checkboxTreeViewer.setContentProvider(treeContentProvider);
		//
		IObservableList libraryArticlesObserveList = EMFObservables.observeList(Realm.getDefault(), library, Literals.LIBRARY__ARTICLES);
		checkboxTreeViewer.setInput(libraryArticlesObserveList);
		//
		return bindingContext;
	}

	private void handleSelectionChanged(SelectionChangedEvent event) {
		ITreeSelection selection = (ITreeSelection) event.getSelection();
		Object firstElement = selection.getFirstElement();
		if (firstElement instanceof Article) {
			Article selectedArticle = (Article) firstElement;
			HashMap<String, Object> data = Maps.newHashMap();
			data.put(Events.ARTICLE_SELECTION_CHANGED_ARTICLE, selectedArticle);
			eventBroker.post(Events.ARTICLE_SELECTION_CHANGED, data);
		}
	}
}
