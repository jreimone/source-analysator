package net.reimone.sourceanalysator.product.views;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.inject.Inject;

import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.observable.Realm;
import org.eclipse.core.databinding.observable.list.IObservableList;
import org.eclipse.e4.ui.di.Focus;
import org.eclipse.emf.databinding.EMFObservables;
import org.eclipse.jface.databinding.viewers.ObservableListTreeContentProvider;
import org.eclipse.jface.viewers.CheckboxTreeViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Tree;

import net.reimone.sourceanalysator.Article;
import net.reimone.sourceanalysator.Library;
import net.reimone.sourceanalysator.SourceanalysatorPackage.Literals;
import net.reimone.sourceanalysator.core.ISourceAnalysator;
import net.reimone.sourceanalysator.product.databinding.EMFBeansListObservableFactory;
import net.reimone.sourceanalysator.product.databinding.EMFTreeBeanAdvisor;
import net.reimone.sourceanalysator.product.databinding.EMFTreeObservableLabelProvider;

public class ArticlesTreeView {
	
	private DataBindingContext m_bindingContext;

	@Inject
	private ISourceAnalysator sourceAnalysator;
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
}
