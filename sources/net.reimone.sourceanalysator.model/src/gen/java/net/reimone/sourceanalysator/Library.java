/**
 */
package net.reimone.sourceanalysator;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Library</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link net.reimone.sourceanalysator.Library#getGeneralSources <em>General Sources</em>}</li>
 *   <li>{@link net.reimone.sourceanalysator.Library#getSources <em>Sources</em>}</li>
 *   <li>{@link net.reimone.sourceanalysator.Library#getArticles <em>Articles</em>}</li>
 * </ul>
 *
 * @see net.reimone.sourceanalysator.SourceanalysatorPackage#getLibrary()
 * @model annotation="org.emftext.language.mecore.resource.mecore.mopp.MecoreWrapper WARNING='This element was generated from an .mecore file. Removing this annotation will signal the MinimalEcore builder to keep this element.'"
 * @generated
 */
public interface Library extends EObject {
	/**
	 * Returns the value of the '<em><b>General Sources</b></em>' containment reference list.
	 * The list contents are of type {@link net.reimone.sourceanalysator.GeneralSource}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>General Sources</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>General Sources</em>' containment reference list.
	 * @see net.reimone.sourceanalysator.SourceanalysatorPackage#getLibrary_GeneralSources()
	 * @model containment="true"
	 *        annotation="org.emftext.language.mecore.resource.mecore.mopp.MecoreWrapper WARNING='This element was generated from an .mecore file. Removing this annotation will signal the MinimalEcore builder to keep this element.'"
	 * @generated
	 */
	EList<GeneralSource> getGeneralSources();

	/**
	 * Returns the value of the '<em><b>Sources</b></em>' containment reference list.
	 * The list contents are of type {@link net.reimone.sourceanalysator.Source}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Sources</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Sources</em>' containment reference list.
	 * @see net.reimone.sourceanalysator.SourceanalysatorPackage#getLibrary_Sources()
	 * @model containment="true"
	 *        annotation="org.emftext.language.mecore.resource.mecore.mopp.MecoreWrapper WARNING='This element was generated from an .mecore file. Removing this annotation will signal the MinimalEcore builder to keep this element.'"
	 * @generated
	 */
	EList<Source> getSources();

	/**
	 * Returns the value of the '<em><b>Articles</b></em>' containment reference list.
	 * The list contents are of type {@link net.reimone.sourceanalysator.Article}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Articles</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Articles</em>' containment reference list.
	 * @see net.reimone.sourceanalysator.SourceanalysatorPackage#getLibrary_Articles()
	 * @model containment="true"
	 *        annotation="org.emftext.language.mecore.resource.mecore.mopp.MecoreWrapper WARNING='This element was generated from an .mecore file. Removing this annotation will signal the MinimalEcore builder to keep this element.'"
	 * @generated
	 */
	EList<Article> getArticles();

} // Library
