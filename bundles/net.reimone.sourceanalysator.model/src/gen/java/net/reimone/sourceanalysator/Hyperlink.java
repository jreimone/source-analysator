/**
 */
package net.reimone.sourceanalysator;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Hyperlink</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link net.reimone.sourceanalysator.Hyperlink#getUrl <em>Url</em>}</li>
 *   <li>{@link net.reimone.sourceanalysator.Hyperlink#getSources <em>Sources</em>}</li>
 * </ul>
 *
 * @see net.reimone.sourceanalysator.SourceanalysatorPackage#getHyperlink()
 * @model
 * @generated
 */
public interface Hyperlink extends EObject {
	/**
	 * Returns the value of the '<em><b>Url</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Url</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Url</em>' attribute.
	 * @see #setUrl(String)
	 * @see net.reimone.sourceanalysator.SourceanalysatorPackage#getHyperlink_Url()
	 * @model required="true"
	 * @generated
	 */
	String getUrl();

	/**
	 * Sets the value of the '{@link net.reimone.sourceanalysator.Hyperlink#getUrl <em>Url</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Url</em>' attribute.
	 * @see #getUrl()
	 * @generated
	 */
	void setUrl(String value);

	/**
	 * Returns the value of the '<em><b>Sources</b></em>' reference list.
	 * The list contents are of type {@link net.reimone.sourceanalysator.Source}.
	 * It is bidirectional and its opposite is '{@link net.reimone.sourceanalysator.Source#getHyperlink <em>Hyperlink</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Sources</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Sources</em>' reference list.
	 * @see net.reimone.sourceanalysator.SourceanalysatorPackage#getHyperlink_Sources()
	 * @see net.reimone.sourceanalysator.Source#getHyperlink
	 * @model opposite="hyperlink"
	 * @generated
	 */
	EList<Source> getSources();

} // Hyperlink
