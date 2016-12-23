/**
 */
package net.reimone.sourceanalysator.impl;

import net.reimone.sourceanalysator.Article;
import net.reimone.sourceanalysator.GeneralSource;
import net.reimone.sourceanalysator.Hyperlink;
import net.reimone.sourceanalysator.Source;
import net.reimone.sourceanalysator.SourceanalysatorPackage;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

import org.eclipse.emf.ecore.util.EcoreUtil;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Source</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link net.reimone.sourceanalysator.impl.SourceImpl#getGeneralSource <em>General Source</em>}</li>
 *   <li>{@link net.reimone.sourceanalysator.impl.SourceImpl#getArticle <em>Article</em>}</li>
 *   <li>{@link net.reimone.sourceanalysator.impl.SourceImpl#getHyperlink <em>Hyperlink</em>}</li>
 * </ul>
 *
 * @generated
 */
public class SourceImpl extends MinimalEObjectImpl.Container implements Source {
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
	 * The cached value of the '{@link #getHyperlink() <em>Hyperlink</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getHyperlink()
	 * @generated
	 * @ordered
	 */
	protected Hyperlink hyperlink;

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
	public Article getArticle() {
		if (eContainerFeatureID() != SourceanalysatorPackage.SOURCE__ARTICLE) return null;
		return (Article)eInternalContainer();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetArticle(Article newArticle, NotificationChain msgs) {
		msgs = eBasicSetContainer((InternalEObject)newArticle, SourceanalysatorPackage.SOURCE__ARTICLE, msgs);
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setArticle(Article newArticle) {
		if (newArticle != eInternalContainer() || (eContainerFeatureID() != SourceanalysatorPackage.SOURCE__ARTICLE && newArticle != null)) {
			if (EcoreUtil.isAncestor(this, newArticle))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			NotificationChain msgs = null;
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newArticle != null)
				msgs = ((InternalEObject)newArticle).eInverseAdd(this, SourceanalysatorPackage.ARTICLE__SOURCES, Article.class, msgs);
			msgs = basicSetArticle(newArticle, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SourceanalysatorPackage.SOURCE__ARTICLE, newArticle, newArticle));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Hyperlink getHyperlink() {
		if (hyperlink != null && hyperlink.eIsProxy()) {
			InternalEObject oldHyperlink = (InternalEObject)hyperlink;
			hyperlink = (Hyperlink)eResolveProxy(oldHyperlink);
			if (hyperlink != oldHyperlink) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, SourceanalysatorPackage.SOURCE__HYPERLINK, oldHyperlink, hyperlink));
			}
		}
		return hyperlink;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Hyperlink basicGetHyperlink() {
		return hyperlink;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetHyperlink(Hyperlink newHyperlink, NotificationChain msgs) {
		Hyperlink oldHyperlink = hyperlink;
		hyperlink = newHyperlink;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, SourceanalysatorPackage.SOURCE__HYPERLINK, oldHyperlink, newHyperlink);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setHyperlink(Hyperlink newHyperlink) {
		if (newHyperlink != hyperlink) {
			NotificationChain msgs = null;
			if (hyperlink != null)
				msgs = ((InternalEObject)hyperlink).eInverseRemove(this, SourceanalysatorPackage.HYPERLINK__SOURCES, Hyperlink.class, msgs);
			if (newHyperlink != null)
				msgs = ((InternalEObject)newHyperlink).eInverseAdd(this, SourceanalysatorPackage.HYPERLINK__SOURCES, Hyperlink.class, msgs);
			msgs = basicSetHyperlink(newHyperlink, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SourceanalysatorPackage.SOURCE__HYPERLINK, newHyperlink, newHyperlink));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case SourceanalysatorPackage.SOURCE__GENERAL_SOURCE:
				if (generalSource != null)
					msgs = ((InternalEObject)generalSource).eInverseRemove(this, SourceanalysatorPackage.GENERAL_SOURCE__SOURCES, GeneralSource.class, msgs);
				return basicSetGeneralSource((GeneralSource)otherEnd, msgs);
			case SourceanalysatorPackage.SOURCE__ARTICLE:
				if (eInternalContainer() != null)
					msgs = eBasicRemoveFromContainer(msgs);
				return basicSetArticle((Article)otherEnd, msgs);
			case SourceanalysatorPackage.SOURCE__HYPERLINK:
				if (hyperlink != null)
					msgs = ((InternalEObject)hyperlink).eInverseRemove(this, SourceanalysatorPackage.HYPERLINK__SOURCES, Hyperlink.class, msgs);
				return basicSetHyperlink((Hyperlink)otherEnd, msgs);
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
			case SourceanalysatorPackage.SOURCE__ARTICLE:
				return basicSetArticle(null, msgs);
			case SourceanalysatorPackage.SOURCE__HYPERLINK:
				return basicSetHyperlink(null, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eBasicRemoveFromContainerFeature(NotificationChain msgs) {
		switch (eContainerFeatureID()) {
			case SourceanalysatorPackage.SOURCE__ARTICLE:
				return eInternalContainer().eInverseRemove(this, SourceanalysatorPackage.ARTICLE__SOURCES, Article.class, msgs);
		}
		return super.eBasicRemoveFromContainerFeature(msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case SourceanalysatorPackage.SOURCE__GENERAL_SOURCE:
				if (resolve) return getGeneralSource();
				return basicGetGeneralSource();
			case SourceanalysatorPackage.SOURCE__ARTICLE:
				return getArticle();
			case SourceanalysatorPackage.SOURCE__HYPERLINK:
				if (resolve) return getHyperlink();
				return basicGetHyperlink();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case SourceanalysatorPackage.SOURCE__GENERAL_SOURCE:
				setGeneralSource((GeneralSource)newValue);
				return;
			case SourceanalysatorPackage.SOURCE__ARTICLE:
				setArticle((Article)newValue);
				return;
			case SourceanalysatorPackage.SOURCE__HYPERLINK:
				setHyperlink((Hyperlink)newValue);
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
			case SourceanalysatorPackage.SOURCE__GENERAL_SOURCE:
				setGeneralSource((GeneralSource)null);
				return;
			case SourceanalysatorPackage.SOURCE__ARTICLE:
				setArticle((Article)null);
				return;
			case SourceanalysatorPackage.SOURCE__HYPERLINK:
				setHyperlink((Hyperlink)null);
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
			case SourceanalysatorPackage.SOURCE__GENERAL_SOURCE:
				return generalSource != null;
			case SourceanalysatorPackage.SOURCE__ARTICLE:
				return getArticle() != null;
			case SourceanalysatorPackage.SOURCE__HYPERLINK:
				return hyperlink != null;
		}
		return super.eIsSet(featureID);
	}

} //SourceImpl
