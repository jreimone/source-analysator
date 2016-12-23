/**
 */
package net.reimone.sourceanalysator;

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
 *   <li>{@link net.reimone.sourceanalysator.Source#getGeneralSource <em>General Source</em>}</li>
 *   <li>{@link net.reimone.sourceanalysator.Source#getArticle <em>Article</em>}</li>
 *   <li>{@link net.reimone.sourceanalysator.Source#getHyperlink <em>Hyperlink</em>}</li>
 * </ul>
 *
 * @see net.reimone.sourceanalysator.SourceanalysatorPackage#getSource()
 * @model annotation="org.emftext.language.mecore.resource.mecore.mopp.MecoreWrapper WARNING='This element was generated from an .mecore file. Removing this annotation will signal the MinimalEcore builder to keep this element.'"
 * @generated
 */
public interface Source extends EObject {
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
	 * Returns the value of the '<em><b>Article</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link net.reimone.sourceanalysator.Article#getSources <em>Sources</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Article</em>' container reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Article</em>' container reference.
	 * @see #setArticle(Article)
	 * @see net.reimone.sourceanalysator.SourceanalysatorPackage#getSource_Article()
	 * @see net.reimone.sourceanalysator.Article#getSources
	 * @model opposite="sources" required="true" transient="false"
	 *        annotation="org.emftext.language.mecore.resource.mecore.mopp.MecoreWrapper WARNING='This element was generated from an .mecore file. Removing this annotation will signal the MinimalEcore builder to keep this element.'"
	 * @generated
	 */
	Article getArticle();

	/**
	 * Sets the value of the '{@link net.reimone.sourceanalysator.Source#getArticle <em>Article</em>}' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Article</em>' container reference.
	 * @see #getArticle()
	 * @generated
	 */
	void setArticle(Article value);

	/**
	 * Returns the value of the '<em><b>Hyperlink</b></em>' reference.
	 * It is bidirectional and its opposite is '{@link net.reimone.sourceanalysator.Hyperlink#getSources <em>Sources</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Hyperlink</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Hyperlink</em>' reference.
	 * @see #setHyperlink(Hyperlink)
	 * @see net.reimone.sourceanalysator.SourceanalysatorPackage#getSource_Hyperlink()
	 * @see net.reimone.sourceanalysator.Hyperlink#getSources
	 * @model opposite="sources" required="true"
	 * @generated
	 */
	Hyperlink getHyperlink();

	/**
	 * Sets the value of the '{@link net.reimone.sourceanalysator.Source#getHyperlink <em>Hyperlink</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Hyperlink</em>' reference.
	 * @see #getHyperlink()
	 * @generated
	 */
	void setHyperlink(Hyperlink value);

} // Source
