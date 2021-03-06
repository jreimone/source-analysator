/**
 */
package net.reimone.sourceanalysator.impl;

import java.util.Collection;

import net.reimone.sourceanalysator.GeneralSource;
import net.reimone.sourceanalysator.Source;
import net.reimone.sourceanalysator.SourceanalysatorPackage;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

import org.eclipse.emf.ecore.util.EDataTypeUniqueEList;
import org.eclipse.emf.ecore.util.EObjectWithInverseResolvingEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>General Source</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link net.reimone.sourceanalysator.impl.GeneralSourceImpl#getName <em>Name</em>}</li>
 *   <li>{@link net.reimone.sourceanalysator.impl.GeneralSourceImpl#getSources <em>Sources</em>}</li>
 *   <li>{@link net.reimone.sourceanalysator.impl.GeneralSourceImpl#getAliases <em>Aliases</em>}</li>
 *   <li>{@link net.reimone.sourceanalysator.impl.GeneralSourceImpl#isDontCount <em>Dont Count</em>}</li>
 * </ul>
 *
 * @generated
 */
public class GeneralSourceImpl extends MinimalEObjectImpl.Container implements GeneralSource {
	/**
	 * The default value of the '{@link #getName() <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getName()
	 * @generated
	 * @ordered
	 */
	protected static final String NAME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getName() <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getName()
	 * @generated
	 * @ordered
	 */
	protected String name = NAME_EDEFAULT;

	/**
	 * The cached value of the '{@link #getSources() <em>Sources</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSources()
	 * @generated
	 * @ordered
	 */
	protected EList<Source> sources;

	/**
	 * The cached value of the '{@link #getAliases() <em>Aliases</em>}' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAliases()
	 * @generated
	 * @ordered
	 */
	protected EList<String> aliases;

	/**
	 * The default value of the '{@link #isDontCount() <em>Dont Count</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isDontCount()
	 * @generated
	 * @ordered
	 */
	protected static final boolean DONT_COUNT_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isDontCount() <em>Dont Count</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isDontCount()
	 * @generated
	 * @ordered
	 */
	protected boolean dontCount = DONT_COUNT_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected GeneralSourceImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return SourceanalysatorPackage.Literals.GENERAL_SOURCE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getName() {
		return name;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setName(String newName) {
		String oldName = name;
		name = newName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SourceanalysatorPackage.GENERAL_SOURCE__NAME, oldName, name));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Source> getSources() {
		if (sources == null) {
			sources = new EObjectWithInverseResolvingEList<Source>(Source.class, this, SourceanalysatorPackage.GENERAL_SOURCE__SOURCES, SourceanalysatorPackage.SOURCE__GENERAL_SOURCE);
		}
		return sources;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<String> getAliases() {
		if (aliases == null) {
			aliases = new EDataTypeUniqueEList<String>(String.class, this, SourceanalysatorPackage.GENERAL_SOURCE__ALIASES);
		}
		return aliases;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isDontCount() {
		return dontCount;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setDontCount(boolean newDontCount) {
		boolean oldDontCount = dontCount;
		dontCount = newDontCount;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SourceanalysatorPackage.GENERAL_SOURCE__DONT_COUNT, oldDontCount, dontCount));
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
			case SourceanalysatorPackage.GENERAL_SOURCE__SOURCES:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getSources()).basicAdd(otherEnd, msgs);
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
			case SourceanalysatorPackage.GENERAL_SOURCE__SOURCES:
				return ((InternalEList<?>)getSources()).basicRemove(otherEnd, msgs);
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
			case SourceanalysatorPackage.GENERAL_SOURCE__NAME:
				return getName();
			case SourceanalysatorPackage.GENERAL_SOURCE__SOURCES:
				return getSources();
			case SourceanalysatorPackage.GENERAL_SOURCE__ALIASES:
				return getAliases();
			case SourceanalysatorPackage.GENERAL_SOURCE__DONT_COUNT:
				return isDontCount();
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
			case SourceanalysatorPackage.GENERAL_SOURCE__NAME:
				setName((String)newValue);
				return;
			case SourceanalysatorPackage.GENERAL_SOURCE__SOURCES:
				getSources().clear();
				getSources().addAll((Collection<? extends Source>)newValue);
				return;
			case SourceanalysatorPackage.GENERAL_SOURCE__ALIASES:
				getAliases().clear();
				getAliases().addAll((Collection<? extends String>)newValue);
				return;
			case SourceanalysatorPackage.GENERAL_SOURCE__DONT_COUNT:
				setDontCount((Boolean)newValue);
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
			case SourceanalysatorPackage.GENERAL_SOURCE__NAME:
				setName(NAME_EDEFAULT);
				return;
			case SourceanalysatorPackage.GENERAL_SOURCE__SOURCES:
				getSources().clear();
				return;
			case SourceanalysatorPackage.GENERAL_SOURCE__ALIASES:
				getAliases().clear();
				return;
			case SourceanalysatorPackage.GENERAL_SOURCE__DONT_COUNT:
				setDontCount(DONT_COUNT_EDEFAULT);
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
			case SourceanalysatorPackage.GENERAL_SOURCE__NAME:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
			case SourceanalysatorPackage.GENERAL_SOURCE__SOURCES:
				return sources != null && !sources.isEmpty();
			case SourceanalysatorPackage.GENERAL_SOURCE__ALIASES:
				return aliases != null && !aliases.isEmpty();
			case SourceanalysatorPackage.GENERAL_SOURCE__DONT_COUNT:
				return dontCount != DONT_COUNT_EDEFAULT;
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
		result.append(" (name: ");
		result.append(name);
		result.append(", aliases: ");
		result.append(aliases);
		result.append(", dontCount: ");
		result.append(dontCount);
		result.append(')');
		return result.toString();
	}

} //GeneralSourceImpl
