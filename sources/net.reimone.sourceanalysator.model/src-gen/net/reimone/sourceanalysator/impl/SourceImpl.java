/**
 */
package net.reimone.sourceanalysator.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;
import org.eclipse.emf.ecore.util.EObjectWithInverseResolvingEList;
import org.eclipse.emf.ecore.util.InternalEList;

import net.reimone.sourceanalysator.Article;
import net.reimone.sourceanalysator.GeneralSource;
import net.reimone.sourceanalysator.Source;
import net.reimone.sourceanalysator.SourceanalysatorPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Source</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link net.reimone.sourceanalysator.impl.SourceImpl#getUrl <em>Url</em>}</li>
 *   <li>{@link net.reimone.sourceanalysator.impl.SourceImpl#getGeneralSource <em>General Source</em>}</li>
 *   <li>{@link net.reimone.sourceanalysator.impl.SourceImpl#getArticles <em>Articles</em>}</li>
 * </ul>
 *
 * @generated
 */
public class SourceImpl extends MinimalEObjectImpl.Container implements Source {
	/**
	 * The default value of the '{@link #getUrl() <em>Url</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getUrl()
	 * @generated
	 * @ordered
	 */
	protected static final String URL_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getUrl() <em>Url</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getUrl()
	 * @generated
	 * @ordered
	 */
	protected String url = URL_EDEFAULT;

	/**
	 * The cached value of the '{@link #getGeneralSource() <em>General Source</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getGeneralSource()
	 * @generated
	 * @ordered
	 */
	protected GeneralSource generalSource;

	/**
	 * The cached value of the '{@link #getArticles() <em>Articles</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getArticles()
	 * @generated
	 * @ordered
	 */
	protected EList<Article> articles;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected SourceImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return SourceanalysatorPackage.Literals.SOURCE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setUrl(String newUrl) {
		String oldUrl = url;
		url = newUrl;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SourceanalysatorPackage.SOURCE__URL, oldUrl, url));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public GeneralSource getGeneralSource() {
		if (generalSource != null && generalSource.eIsProxy()) {
			InternalEObject oldGeneralSource = (InternalEObject)generalSource;
			generalSource = (GeneralSource)eResolveProxy(oldGeneralSource);
			if (generalSource != oldGeneralSource) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, SourceanalysatorPackage.SOURCE__GENERAL_SOURCE, oldGeneralSource, generalSource));
			}
		}
		return generalSource;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public GeneralSource basicGetGeneralSource() {
		return generalSource;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetGeneralSource(GeneralSource newGeneralSource, NotificationChain msgs) {
		GeneralSource oldGeneralSource = generalSource;
		generalSource = newGeneralSource;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, SourceanalysatorPackage.SOURCE__GENERAL_SOURCE, oldGeneralSource, newGeneralSource);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setGeneralSource(GeneralSource newGeneralSource) {
		if (newGeneralSource != generalSource) {
			NotificationChain msgs = null;
			if (generalSource != null)
				msgs = ((InternalEObject)generalSource).eInverseRemove(this, SourceanalysatorPackage.GENERAL_SOURCE__SOURCES, GeneralSource.class, msgs);
			if (newGeneralSource != null)
				msgs = ((InternalEObject)newGeneralSource).eInverseAdd(this, SourceanalysatorPackage.GENERAL_SOURCE__SOURCES, GeneralSource.class, msgs);
			msgs = basicSetGeneralSource(newGeneralSource, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SourceanalysatorPackage.SOURCE__GENERAL_SOURCE, newGeneralSource, newGeneralSource));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Article> getArticles() {
		if (articles == null) {
			articles = new EObjectWithInverseResolvingEList.ManyInverse<Article>(Article.class, this, SourceanalysatorPackage.SOURCE__ARTICLES, SourceanalysatorPackage.ARTICLE__SOURCES);
		}
		return articles;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case SourceanalysatorPackage.SOURCE__GENERAL_SOURCE:
				if (generalSource != null)
					msgs = ((InternalEObject)generalSource).eInverseRemove(this, SourceanalysatorPackage.GENERAL_SOURCE__SOURCES, GeneralSource.class, msgs);
				return basicSetGeneralSource((GeneralSource)otherEnd, msgs);
			case SourceanalysatorPackage.SOURCE__ARTICLES:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getArticles()).basicAdd(otherEnd, msgs);
		}
		return super.eInverseAdd(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case SourceanalysatorPackage.SOURCE__GENERAL_SOURCE:
				return basicSetGeneralSource(null, msgs);
			case SourceanalysatorPackage.SOURCE__ARTICLES:
				return ((InternalEList<?>)getArticles()).basicRemove(otherEnd, msgs);
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
			case SourceanalysatorPackage.SOURCE__URL:
				return getUrl();
			case SourceanalysatorPackage.SOURCE__GENERAL_SOURCE:
				if (resolve) return getGeneralSource();
				return basicGetGeneralSource();
			case SourceanalysatorPackage.SOURCE__ARTICLES:
				return getArticles();
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
			case SourceanalysatorPackage.SOURCE__URL:
				setUrl((String)newValue);
				return;
			case SourceanalysatorPackage.SOURCE__GENERAL_SOURCE:
				setGeneralSource((GeneralSource)newValue);
				return;
			case SourceanalysatorPackage.SOURCE__ARTICLES:
				getArticles().clear();
				getArticles().addAll((Collection<? extends Article>)newValue);
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
			case SourceanalysatorPackage.SOURCE__URL:
				setUrl(URL_EDEFAULT);
				return;
			case SourceanalysatorPackage.SOURCE__GENERAL_SOURCE:
				setGeneralSource((GeneralSource)null);
				return;
			case SourceanalysatorPackage.SOURCE__ARTICLES:
				getArticles().clear();
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
			case SourceanalysatorPackage.SOURCE__URL:
				return URL_EDEFAULT == null ? url != null : !URL_EDEFAULT.equals(url);
			case SourceanalysatorPackage.SOURCE__GENERAL_SOURCE:
				return generalSource != null;
			case SourceanalysatorPackage.SOURCE__ARTICLES:
				return articles != null && !articles.isEmpty();
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String toString() {
		if (eIsProxy()) return super.toString();

		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (url: ");
		result.append(url);
		result.append(')');
		return result.toString();
	}

} //SourceImpl
