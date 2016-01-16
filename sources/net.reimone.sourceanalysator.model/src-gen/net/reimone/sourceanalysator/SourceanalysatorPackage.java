/**
 */
package net.reimone.sourceanalysator;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

/**
 * <!-- begin-user-doc -->
 * The <b>Package</b> for the model.
 * It contains accessors for the meta objects to represent
 * <ul>
 *   <li>each class,</li>
 *   <li>each feature of each class,</li>
 *   <li>each operation of each class,</li>
 *   <li>each enum,</li>
 *   <li>and each data type</li>
 * </ul>
 * <!-- end-user-doc -->
 * @see net.reimone.sourceanalysator.SourceanalysatorFactory
 * @model kind="package"
 *        annotation="org.emftext.language.mecore.resource.mecore.mopp.MecoreWrapper WARNING='This element was generated from an .mecore file. Removing this annotation will signal the MinimalEcore builder to keep this element.'"
 * @generated
 */
public interface SourceanalysatorPackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "sourceanalysator";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "http://www.reimone.net/sourceanalysator";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "sourceanalysator";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	SourceanalysatorPackage eINSTANCE = net.reimone.sourceanalysator.impl.SourceanalysatorPackageImpl.init();

	/**
	 * The meta object id for the '{@link net.reimone.sourceanalysator.impl.GeneralSourceImpl <em>General Source</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see net.reimone.sourceanalysator.impl.GeneralSourceImpl
	 * @see net.reimone.sourceanalysator.impl.SourceanalysatorPackageImpl#getGeneralSource()
	 * @generated
	 */
	int GENERAL_SOURCE = 0;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GENERAL_SOURCE__NAME = 0;

	/**
	 * The feature id for the '<em><b>Sources</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GENERAL_SOURCE__SOURCES = 1;

	/**
	 * The number of structural features of the '<em>General Source</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GENERAL_SOURCE_FEATURE_COUNT = 2;

	/**
	 * The number of operations of the '<em>General Source</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GENERAL_SOURCE_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link net.reimone.sourceanalysator.impl.SourceImpl <em>Source</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see net.reimone.sourceanalysator.impl.SourceImpl
	 * @see net.reimone.sourceanalysator.impl.SourceanalysatorPackageImpl#getSource()
	 * @generated
	 */
	int SOURCE = 1;

	/**
	 * The feature id for the '<em><b>Url</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SOURCE__URL = 0;

	/**
	 * The feature id for the '<em><b>General Source</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SOURCE__GENERAL_SOURCE = 1;

	/**
	 * The feature id for the '<em><b>Articles</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SOURCE__ARTICLES = 2;

	/**
	 * The number of structural features of the '<em>Source</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SOURCE_FEATURE_COUNT = 3;

	/**
	 * The number of operations of the '<em>Source</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SOURCE_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link net.reimone.sourceanalysator.impl.LibraryImpl <em>Library</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see net.reimone.sourceanalysator.impl.LibraryImpl
	 * @see net.reimone.sourceanalysator.impl.SourceanalysatorPackageImpl#getLibrary()
	 * @generated
	 */
	int LIBRARY = 2;

	/**
	 * The feature id for the '<em><b>General Sources</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LIBRARY__GENERAL_SOURCES = 0;

	/**
	 * The feature id for the '<em><b>Sources</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LIBRARY__SOURCES = 1;

	/**
	 * The feature id for the '<em><b>Articles</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LIBRARY__ARTICLES = 2;

	/**
	 * The number of structural features of the '<em>Library</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LIBRARY_FEATURE_COUNT = 3;

	/**
	 * The number of operations of the '<em>Library</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LIBRARY_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link net.reimone.sourceanalysator.impl.ArticleImpl <em>Article</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see net.reimone.sourceanalysator.impl.ArticleImpl
	 * @see net.reimone.sourceanalysator.impl.SourceanalysatorPackageImpl#getArticle()
	 * @generated
	 */
	int ARTICLE = 3;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARTICLE__NAME = 0;

	/**
	 * The feature id for the '<em><b>Sources</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARTICLE__SOURCES = 1;

	/**
	 * The number of structural features of the '<em>Article</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARTICLE_FEATURE_COUNT = 2;

	/**
	 * The number of operations of the '<em>Article</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARTICLE_OPERATION_COUNT = 0;


	/**
	 * Returns the meta object for class '{@link net.reimone.sourceanalysator.GeneralSource <em>General Source</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>General Source</em>'.
	 * @see net.reimone.sourceanalysator.GeneralSource
	 * @generated
	 */
	EClass getGeneralSource();

	/**
	 * Returns the meta object for the attribute '{@link net.reimone.sourceanalysator.GeneralSource#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see net.reimone.sourceanalysator.GeneralSource#getName()
	 * @see #getGeneralSource()
	 * @generated
	 */
	EAttribute getGeneralSource_Name();

	/**
	 * Returns the meta object for the reference list '{@link net.reimone.sourceanalysator.GeneralSource#getSources <em>Sources</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Sources</em>'.
	 * @see net.reimone.sourceanalysator.GeneralSource#getSources()
	 * @see #getGeneralSource()
	 * @generated
	 */
	EReference getGeneralSource_Sources();

	/**
	 * Returns the meta object for class '{@link net.reimone.sourceanalysator.Source <em>Source</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Source</em>'.
	 * @see net.reimone.sourceanalysator.Source
	 * @generated
	 */
	EClass getSource();

	/**
	 * Returns the meta object for the attribute '{@link net.reimone.sourceanalysator.Source#getUrl <em>Url</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Url</em>'.
	 * @see net.reimone.sourceanalysator.Source#getUrl()
	 * @see #getSource()
	 * @generated
	 */
	EAttribute getSource_Url();

	/**
	 * Returns the meta object for the reference '{@link net.reimone.sourceanalysator.Source#getGeneralSource <em>General Source</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>General Source</em>'.
	 * @see net.reimone.sourceanalysator.Source#getGeneralSource()
	 * @see #getSource()
	 * @generated
	 */
	EReference getSource_GeneralSource();

	/**
	 * Returns the meta object for the reference list '{@link net.reimone.sourceanalysator.Source#getArticles <em>Articles</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Articles</em>'.
	 * @see net.reimone.sourceanalysator.Source#getArticles()
	 * @see #getSource()
	 * @generated
	 */
	EReference getSource_Articles();

	/**
	 * Returns the meta object for class '{@link net.reimone.sourceanalysator.Library <em>Library</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Library</em>'.
	 * @see net.reimone.sourceanalysator.Library
	 * @generated
	 */
	EClass getLibrary();

	/**
	 * Returns the meta object for the containment reference list '{@link net.reimone.sourceanalysator.Library#getGeneralSources <em>General Sources</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>General Sources</em>'.
	 * @see net.reimone.sourceanalysator.Library#getGeneralSources()
	 * @see #getLibrary()
	 * @generated
	 */
	EReference getLibrary_GeneralSources();

	/**
	 * Returns the meta object for the containment reference list '{@link net.reimone.sourceanalysator.Library#getSources <em>Sources</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Sources</em>'.
	 * @see net.reimone.sourceanalysator.Library#getSources()
	 * @see #getLibrary()
	 * @generated
	 */
	EReference getLibrary_Sources();

	/**
	 * Returns the meta object for the containment reference list '{@link net.reimone.sourceanalysator.Library#getArticles <em>Articles</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Articles</em>'.
	 * @see net.reimone.sourceanalysator.Library#getArticles()
	 * @see #getLibrary()
	 * @generated
	 */
	EReference getLibrary_Articles();

	/**
	 * Returns the meta object for class '{@link net.reimone.sourceanalysator.Article <em>Article</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Article</em>'.
	 * @see net.reimone.sourceanalysator.Article
	 * @generated
	 */
	EClass getArticle();

	/**
	 * Returns the meta object for the attribute '{@link net.reimone.sourceanalysator.Article#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see net.reimone.sourceanalysator.Article#getName()
	 * @see #getArticle()
	 * @generated
	 */
	EAttribute getArticle_Name();

	/**
	 * Returns the meta object for the reference list '{@link net.reimone.sourceanalysator.Article#getSources <em>Sources</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Sources</em>'.
	 * @see net.reimone.sourceanalysator.Article#getSources()
	 * @see #getArticle()
	 * @generated
	 */
	EReference getArticle_Sources();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	SourceanalysatorFactory getSourceanalysatorFactory();

	/**
	 * <!-- begin-user-doc -->
	 * Defines literals for the meta objects that represent
	 * <ul>
	 *   <li>each class,</li>
	 *   <li>each feature of each class,</li>
	 *   <li>each operation of each class,</li>
	 *   <li>each enum,</li>
	 *   <li>and each data type</li>
	 * </ul>
	 * <!-- end-user-doc -->
	 * @generated
	 */
	interface Literals {
		/**
		 * The meta object literal for the '{@link net.reimone.sourceanalysator.impl.GeneralSourceImpl <em>General Source</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see net.reimone.sourceanalysator.impl.GeneralSourceImpl
		 * @see net.reimone.sourceanalysator.impl.SourceanalysatorPackageImpl#getGeneralSource()
		 * @generated
		 */
		EClass GENERAL_SOURCE = eINSTANCE.getGeneralSource();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute GENERAL_SOURCE__NAME = eINSTANCE.getGeneralSource_Name();

		/**
		 * The meta object literal for the '<em><b>Sources</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference GENERAL_SOURCE__SOURCES = eINSTANCE.getGeneralSource_Sources();

		/**
		 * The meta object literal for the '{@link net.reimone.sourceanalysator.impl.SourceImpl <em>Source</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see net.reimone.sourceanalysator.impl.SourceImpl
		 * @see net.reimone.sourceanalysator.impl.SourceanalysatorPackageImpl#getSource()
		 * @generated
		 */
		EClass SOURCE = eINSTANCE.getSource();

		/**
		 * The meta object literal for the '<em><b>Url</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SOURCE__URL = eINSTANCE.getSource_Url();

		/**
		 * The meta object literal for the '<em><b>General Source</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SOURCE__GENERAL_SOURCE = eINSTANCE.getSource_GeneralSource();

		/**
		 * The meta object literal for the '<em><b>Articles</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SOURCE__ARTICLES = eINSTANCE.getSource_Articles();

		/**
		 * The meta object literal for the '{@link net.reimone.sourceanalysator.impl.LibraryImpl <em>Library</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see net.reimone.sourceanalysator.impl.LibraryImpl
		 * @see net.reimone.sourceanalysator.impl.SourceanalysatorPackageImpl#getLibrary()
		 * @generated
		 */
		EClass LIBRARY = eINSTANCE.getLibrary();

		/**
		 * The meta object literal for the '<em><b>General Sources</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference LIBRARY__GENERAL_SOURCES = eINSTANCE.getLibrary_GeneralSources();

		/**
		 * The meta object literal for the '<em><b>Sources</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference LIBRARY__SOURCES = eINSTANCE.getLibrary_Sources();

		/**
		 * The meta object literal for the '<em><b>Articles</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference LIBRARY__ARTICLES = eINSTANCE.getLibrary_Articles();

		/**
		 * The meta object literal for the '{@link net.reimone.sourceanalysator.impl.ArticleImpl <em>Article</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see net.reimone.sourceanalysator.impl.ArticleImpl
		 * @see net.reimone.sourceanalysator.impl.SourceanalysatorPackageImpl#getArticle()
		 * @generated
		 */
		EClass ARTICLE = eINSTANCE.getArticle();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ARTICLE__NAME = eINSTANCE.getArticle_Name();

		/**
		 * The meta object literal for the '<em><b>Sources</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ARTICLE__SOURCES = eINSTANCE.getArticle_Sources();

	}

} //SourceanalysatorPackage
