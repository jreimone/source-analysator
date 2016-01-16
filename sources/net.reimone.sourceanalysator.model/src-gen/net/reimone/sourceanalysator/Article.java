/**
 */
package net.reimone.sourceanalysator;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Article</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link net.reimone.sourceanalysator.Article#getName <em>Name</em>}</li>
 *   <li>{@link net.reimone.sourceanalysator.Article#getSources <em>Sources</em>}</li>
 * </ul>
 *
 * @see net.reimone.sourceanalysator.SourceanalysatorPackage#getArticle()
 * @model annotation="org.emftext.language.mecore.resource.mecore.mopp.MecoreWrapper WARNING='This element was generated from an .mecore file. Removing this annotation will signal the MinimalEcore builder to keep this element.'"
 * @generated
 */
public interface Article extends EObject {
	/**
	 * Returns the value of the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Name</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Name</em>' attribute.
	 * @see #setName(String)
	 * @see net.reimone.sourceanalysator.SourceanalysatorPackage#getArticle_Name()
	 * @model required="true"
	 *        annotation="org.emftext.language.mecore.resource.mecore.mopp.MecoreWrapper WARNING='This element was generated from an .mecore file. Removing this annotation will signal the MinimalEcore builder to keep this element.'"
	 * @generated
	 */
	String getName();

	/**
	 * Sets the value of the '{@link net.reimone.sourceanalysator.Article#getName <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Name</em>' attribute.
	 * @see #getName()
	 * @generated
	 */
	void setName(String value);

	/**
	 * Returns the value of the '<em><b>Sources</b></em>' reference list.
	 * The list contents are of type {@link net.reimone.sourceanalysator.Source}.
	 * It is bidirectional and its opposite is '{@link net.reimone.sourceanalysator.Source#getArticles <em>Articles</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Sources</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Sources</em>' reference list.
	 * @see net.reimone.sourceanalysator.SourceanalysatorPackage#getArticle_Sources()
	 * @see net.reimone.sourceanalysator.Source#getArticles
	 * @model opposite="articles"
	 *        annotation="org.emftext.language.mecore.resource.mecore.mopp.MecoreWrapper WARNING='This element was generated from an .mecore file. Removing this annotation will signal the MinimalEcore builder to keep this element.'"
	 * @generated
	 */
	EList<Source> getSources();

} // Article
