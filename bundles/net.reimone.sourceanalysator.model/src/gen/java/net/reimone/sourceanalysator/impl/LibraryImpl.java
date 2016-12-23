/**
 */
package net.reimone.sourceanalysator.impl;

import java.util.Collection;

import net.reimone.sourceanalysator.Article;
import net.reimone.sourceanalysator.GeneralSource;
import net.reimone.sourceanalysator.Hyperlink;
import net.reimone.sourceanalysator.Library;
import net.reimone.sourceanalysator.SourceanalysatorPackage;

import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Library</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link net.reimone.sourceanalysator.impl.LibraryImpl#getGeneralSources <em>General Sources</em>}</li>
 *   <li>{@link net.reimone.sourceanalysator.impl.LibraryImpl#getArticles <em>Articles</em>}</li>
 *   <li>{@link net.reimone.sourceanalysator.impl.LibraryImpl#getHyperlinks <em>Hyperlinks</em>}</li>
 * </ul>
 *
 * @generated
 */
public class LibraryImpl extends MinimalEObjectImpl.Container implements Library {
	/**
	 * The cached value of the '{@link #getGeneralSources() <em>General Sources</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getGeneralSources()
	 * @generated
	 * @ordered
	 */
	protected EList<GeneralSource> generalSources;

	/**
	 * The cached value of the '{@link #getArticles() <em>Articles</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getArticles()
	 * @generated
	 * @ordered
	 */
	protected EList<Article> articles;

	/**
	 * The cached value of the '{@link #getHyperlinks() <em>Hyperlinks</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getHyperlinks()
	 * @generated
	 * @ordered
	 */
	protected EList<Hyperlink> hyperlinks;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected LibraryImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return SourceanalysatorPackage.Literals.LIBRARY;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<GeneralSource> getGeneralSources() {
		if (generalSources == null) {
			generalSources = new EObjectContainmentEList<GeneralSource>(GeneralSource.class, this, SourceanalysatorPackage.LIBRARY__GENERAL_SOURCES);
		}
		return generalSources;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Article> getArticles() {
		if (articles == null) {
			articles = new EObjectContainmentEList<Article>(Article.class, this, SourceanalysatorPackage.LIBRARY__ARTICLES);
		}
		return articles;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Hyperlink> getHyperlinks() {
		if (hyperlinks == null) {
			hyperlinks = new EObjectContainmentEList<Hyperlink>(Hyperlink.class, this, SourceanalysatorPackage.LIBRARY__HYPERLINKS);
		}
		return hyperlinks;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case SourceanalysatorPackage.LIBRARY__GENERAL_SOURCES:
				return ((InternalEList<?>)getGeneralSources()).basicRemove(otherEnd, msgs);
			case SourceanalysatorPackage.LIBRARY__ARTICLES:
				return ((InternalEList<?>)getArticles()).basicRemove(otherEnd, msgs);
			case SourceanalysatorPackage.LIBRARY__HYPERLINKS:
				return ((InternalEList<?>)getHyperlinks()).basicRemove(otherEnd, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case SourceanalysatorPackage.LIBRARY__GENERAL_SOURCES:
				return getGeneralSources();
			case SourceanalysatorPackage.LIBRARY__ARTICLES:
				return getArticles();
			case SourceanalysatorPackage.LIBRARY__HYPERLINKS:
				return getHyperlinks();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case SourceanalysatorPackage.LIBRARY__GENERAL_SOURCES:
				getGeneralSources().clear();
				getGeneralSources().addAll((Collection<? extends GeneralSource>)newValue);
				return;
			case SourceanalysatorPackage.LIBRARY__ARTICLES:
				getArticles().clear();
				getArticles().addAll((Collection<? extends Article>)newValue);
				return;
			case SourceanalysatorPackage.LIBRARY__HYPERLINKS:
				getHyperlinks().clear();
				getHyperlinks().addAll((Collection<? extends Hyperlink>)newValue);
				return;
		}
		super.eSet(featureID, newValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eUnset(int featureID) {
		switch (featureID) {
			case SourceanalysatorPackage.LIBRARY__GENERAL_SOURCES:
				getGeneralSources().clear();
				return;
			case SourceanalysatorPackage.LIBRARY__ARTICLES:
				getArticles().clear();
				return;
			case SourceanalysatorPackage.LIBRARY__HYPERLINKS:
				getHyperlinks().clear();
				return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean eIsSet(int featureID) {
		switch (featureID) {
			case SourceanalysatorPackage.LIBRARY__GENERAL_SOURCES:
				return generalSources != null && !generalSources.isEmpty();
			case SourceanalysatorPackage.LIBRARY__ARTICLES:
				return articles != null && !articles.isEmpty();
			case SourceanalysatorPackage.LIBRARY__HYPERLINKS:
				return hyperlinks != null && !hyperlinks.isEmpty();
		}
		return super.eIsSet(featureID);
	}

} //LibraryImpl
