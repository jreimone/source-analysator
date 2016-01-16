/**
 */
package net.reimone.sourceanalysator;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Source</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link net.reimone.sourceanalysator.Source#getUrl <em>Url</em>}</li>
 *   <li>{@link net.reimone.sourceanalysator.Source#getGeneralSource <em>General Source</em>}</li>
 *   <li>{@link net.reimone.sourceanalysator.Source#getArticles <em>Articles</em>}</li>
 * </ul>
 *
 * @see net.reimone.sourceanalysator.SourceanalysatorPackage#getSource()
 * @model annotation="org.emftext.language.mecore.resource.mecore.mopp.MecoreWrapper WARNING='This element was generated from an .mecore file. Removing this annotation will signal the MinimalEcore builder to keep this element.'"
 * @generated
 */
public interface Source extends EObject {
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
	 * @see net.reimone.sourceanalysator.SourceanalysatorPackage#getSource_Url()
	 * @model required="true"
	 *        annotation="org.emftext.language.mecore.resource.mecore.mopp.MecoreWrapper WARNING='This element was generated from an .mecore file. Removing this annotation will signal the MinimalEcore builder to keep this element.'"
	 * @generated
	 */
	String getUrl();

	/**
	 * Sets the value of the '{@link net.reimone.sourceanalysator.Source#getUrl <em>Url</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Url</em>' attribute.
	 * @see #getUrl()
	 * @generated
	 */
	void setUrl(String value);

	/**
	 * Returns the value of the '<em><b>General Source</b></em>' reference.
	 * It is bidirectional and its opposite is '{@link net.reimone.sourceanalysator.GeneralSource#getSources <em>Sources</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>General Source</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>General Source</em>' reference.
	 * @see #setGeneralSource(GeneralSource)
	 * @see net.reimone.sourceanalysator.SourceanalysatorPackage#getSource_GeneralSource()
	 * @see net.reimone.sourceanalysator.GeneralSource#getSources
	 * @model opposite="sources" required="true"
	 *        annotation="org.emftext.language.mecore.resource.mecore.mopp.MecoreWrapper WARNING='This element was generated from an .mecore file. Removing this annotation will signal the MinimalEcore builder to keep this element.'"
	 * @generated
	 */
	GeneralSource getGeneralSource();

	/**
	 * Sets the value of the '{@link net.reimone.sourceanalysator.Source#getGeneralSource <em>General Source</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>General Source</em>' reference.
	 * @see #getGeneralSource()
	 * @generated
	 */
	void setGeneralSource(GeneralSource value);

	/**
	 * Returns the value of the '<em><b>Articles</b></em>' reference list.
	 * The list contents are of type {@link net.reimone.sourceanalysator.Article}.
	 * It is bidirectional and its opposite is '{@link net.reimone.sourceanalysator.Article#getSources <em>Sources</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Articles</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Articles</em>' reference list.
	 * @see net.reimone.sourceanalysator.SourceanalysatorPackage#getSource_Articles()
	 * @see net.reimone.sourceanalysator.Article#getSources
	 * @model opposite="sources"
	 *        annotation="org.emftext.language.mecore.resource.mecore.mopp.MecoreWrapper WARNING='This element was generated from an .mecore file. Removing this annotation will signal the MinimalEcore builder to keep this element.'"
	 * @generated
	 */
	EList<Article> getArticles();

} // Source
