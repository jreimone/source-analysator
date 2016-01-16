/**
 */
package net.reimone.sourceanalysator.impl;

import net.reimone.sourceanalysator.Article;
import net.reimone.sourceanalysator.GeneralSource;
import net.reimone.sourceanalysator.Library;
import net.reimone.sourceanalysator.Source;
import net.reimone.sourceanalysator.SourceanalysatorFactory;
import net.reimone.sourceanalysator.SourceanalysatorPackage;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

import org.eclipse.emf.ecore.impl.EPackageImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Package</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class SourceanalysatorPackageImpl extends EPackageImpl implements SourceanalysatorPackage {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass generalSourceEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass sourceEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass libraryEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass articleEClass = null;

	/**
	 * Creates an instance of the model <b>Package</b>, registered with
	 * {@link org.eclipse.emf.ecore.EPackage.Registry EPackage.Registry} by the package
	 * package URI value.
	 * <p>Note: the correct way to create the package is via the static
	 * factory method {@link #init init()}, which also performs
	 * initialization of the package, or returns the registered package,
	 * if one already exists.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.emf.ecore.EPackage.Registry
	 * @see net.reimone.sourceanalysator.SourceanalysatorPackage#eNS_URI
	 * @see #init()
	 * @generated
	 */
	private SourceanalysatorPackageImpl() {
		super(eNS_URI, SourceanalysatorFactory.eINSTANCE);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static boolean isInited = false;

	/**
	 * Creates, registers, and initializes the <b>Package</b> for this model, and for any others upon which it depends.
	 * 
	 * <p>This method is used to initialize {@link SourceanalysatorPackage#eINSTANCE} when that field is accessed.
	 * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #eNS_URI
	 * @see #createPackageContents()
	 * @see #initializePackageContents()
	 * @generated
	 */
	public static SourceanalysatorPackage init() {
		if (isInited) return (SourceanalysatorPackage)EPackage.Registry.INSTANCE.getEPackage(SourceanalysatorPackage.eNS_URI);

		// Obtain or create and register package
		SourceanalysatorPackageImpl theSourceanalysatorPackage = (SourceanalysatorPackageImpl)(EPackage.Registry.INSTANCE.get(eNS_URI) instanceof SourceanalysatorPackageImpl ? EPackage.Registry.INSTANCE.get(eNS_URI) : new SourceanalysatorPackageImpl());

		isInited = true;

		// Create package meta-data objects
		theSourceanalysatorPackage.createPackageContents();

		// Initialize created meta-data
		theSourceanalysatorPackage.initializePackageContents();

		// Mark meta-data to indicate it can't be changed
		theSourceanalysatorPackage.freeze();

  
		// Update the registry and return the package
		EPackage.Registry.INSTANCE.put(SourceanalysatorPackage.eNS_URI, theSourceanalysatorPackage);
		return theSourceanalysatorPackage;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getGeneralSource() {
		return generalSourceEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getGeneralSource_Name() {
		return (EAttribute)generalSourceEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getGeneralSource_Sources() {
		return (EReference)generalSourceEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getSource() {
		return sourceEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getSource_Url() {
		return (EAttribute)sourceEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getSource_GeneralSource() {
		return (EReference)sourceEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getSource_Articles() {
		return (EReference)sourceEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getLibrary() {
		return libraryEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getLibrary_GeneralSources() {
		return (EReference)libraryEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getLibrary_Sources() {
		return (EReference)libraryEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getLibrary_Articles() {
		return (EReference)libraryEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getArticle() {
		return articleEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getArticle_Name() {
		return (EAttribute)articleEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getArticle_Sources() {
		return (EReference)articleEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SourceanalysatorFactory getSourceanalysatorFactory() {
		return (SourceanalysatorFactory)getEFactoryInstance();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private boolean isCreated = false;

	/**
	 * Creates the meta-model objects for the package.  This method is
	 * guarded to have no affect on any invocation but its first.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void createPackageContents() {
		if (isCreated) return;
		isCreated = true;

		// Create classes and their features
		generalSourceEClass = createEClass(GENERAL_SOURCE);
		createEAttribute(generalSourceEClass, GENERAL_SOURCE__NAME);
		createEReference(generalSourceEClass, GENERAL_SOURCE__SOURCES);

		sourceEClass = createEClass(SOURCE);
		createEAttribute(sourceEClass, SOURCE__URL);
		createEReference(sourceEClass, SOURCE__GENERAL_SOURCE);
		createEReference(sourceEClass, SOURCE__ARTICLES);

		libraryEClass = createEClass(LIBRARY);
		createEReference(libraryEClass, LIBRARY__GENERAL_SOURCES);
		createEReference(libraryEClass, LIBRARY__SOURCES);
		createEReference(libraryEClass, LIBRARY__ARTICLES);

		articleEClass = createEClass(ARTICLE);
		createEAttribute(articleEClass, ARTICLE__NAME);
		createEReference(articleEClass, ARTICLE__SOURCES);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private boolean isInitialized = false;

	/**
	 * Complete the initialization of the package and its meta-model.  This
	 * method is guarded to have no affect on any invocation but its first.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void initializePackageContents() {
		if (isInitialized) return;
		isInitialized = true;

		// Initialize package
		setName(eNAME);
		setNsPrefix(eNS_PREFIX);
		setNsURI(eNS_URI);

		// Create type parameters

		// Set bounds for type parameters

		// Add supertypes to classes

		// Initialize classes, features, and operations; add parameters
		initEClass(generalSourceEClass, GeneralSource.class, "GeneralSource", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getGeneralSource_Name(), ecorePackage.getEString(), "name", null, 1, 1, GeneralSource.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getGeneralSource_Sources(), this.getSource(), this.getSource_GeneralSource(), "sources", null, 0, -1, GeneralSource.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(sourceEClass, Source.class, "Source", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getSource_Url(), ecorePackage.getEString(), "url", null, 1, 1, Source.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getSource_GeneralSource(), this.getGeneralSource(), this.getGeneralSource_Sources(), "generalSource", null, 1, 1, Source.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getSource_Articles(), this.getArticle(), this.getArticle_Sources(), "articles", null, 0, -1, Source.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(libraryEClass, Library.class, "Library", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getLibrary_GeneralSources(), this.getGeneralSource(), null, "generalSources", null, 0, -1, Library.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getLibrary_Sources(), this.getSource(), null, "sources", null, 0, -1, Library.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getLibrary_Articles(), this.getArticle(), null, "articles", null, 0, -1, Library.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(articleEClass, Article.class, "Article", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getArticle_Name(), ecorePackage.getEString(), "name", null, 1, 1, Article.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getArticle_Sources(), this.getSource(), this.getSource_Articles(), "sources", null, 0, -1, Article.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		// Create resource
		createResource(eNS_URI);

		// Create annotations
		// org.emftext.language.mecore.resource.mecore.mopp.MecoreWrapper
		createOrgAnnotations();
	}

	/**
	 * Initializes the annotations for <b>org.emftext.language.mecore.resource.mecore.mopp.MecoreWrapper</b>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void createOrgAnnotations() {
		String source = "org.emftext.language.mecore.resource.mecore.mopp.MecoreWrapper";	
		addAnnotation
		  (this, 
		   source, 
		   new String[] {
			 "WARNING", "This element was generated from an .mecore file. Removing this annotation will signal the MinimalEcore builder to keep this element."
		   });	
		addAnnotation
		  (generalSourceEClass, 
		   source, 
		   new String[] {
			 "WARNING", "This element was generated from an .mecore file. Removing this annotation will signal the MinimalEcore builder to keep this element."
		   });	
		addAnnotation
		  (getGeneralSource_Name(), 
		   source, 
		   new String[] {
			 "WARNING", "This element was generated from an .mecore file. Removing this annotation will signal the MinimalEcore builder to keep this element."
		   });	
		addAnnotation
		  (getGeneralSource_Sources(), 
		   source, 
		   new String[] {
			 "WARNING", "This element was generated from an .mecore file. Removing this annotation will signal the MinimalEcore builder to keep this element."
		   });	
		addAnnotation
		  (sourceEClass, 
		   source, 
		   new String[] {
			 "WARNING", "This element was generated from an .mecore file. Removing this annotation will signal the MinimalEcore builder to keep this element."
		   });	
		addAnnotation
		  (getSource_Url(), 
		   source, 
		   new String[] {
			 "WARNING", "This element was generated from an .mecore file. Removing this annotation will signal the MinimalEcore builder to keep this element."
		   });	
		addAnnotation
		  (getSource_GeneralSource(), 
		   source, 
		   new String[] {
			 "WARNING", "This element was generated from an .mecore file. Removing this annotation will signal the MinimalEcore builder to keep this element."
		   });	
		addAnnotation
		  (getSource_Articles(), 
		   source, 
		   new String[] {
			 "WARNING", "This element was generated from an .mecore file. Removing this annotation will signal the MinimalEcore builder to keep this element."
		   });	
		addAnnotation
		  (libraryEClass, 
		   source, 
		   new String[] {
			 "WARNING", "This element was generated from an .mecore file. Removing this annotation will signal the MinimalEcore builder to keep this element."
		   });	
		addAnnotation
		  (getLibrary_GeneralSources(), 
		   source, 
		   new String[] {
			 "WARNING", "This element was generated from an .mecore file. Removing this annotation will signal the MinimalEcore builder to keep this element."
		   });	
		addAnnotation
		  (getLibrary_Sources(), 
		   source, 
		   new String[] {
			 "WARNING", "This element was generated from an .mecore file. Removing this annotation will signal the MinimalEcore builder to keep this element."
		   });	
		addAnnotation
		  (getLibrary_Articles(), 
		   source, 
		   new String[] {
			 "WARNING", "This element was generated from an .mecore file. Removing this annotation will signal the MinimalEcore builder to keep this element."
		   });	
		addAnnotation
		  (articleEClass, 
		   source, 
		   new String[] {
			 "WARNING", "This element was generated from an .mecore file. Removing this annotation will signal the MinimalEcore builder to keep this element."
		   });	
		addAnnotation
		  (getArticle_Name(), 
		   source, 
		   new String[] {
			 "WARNING", "This element was generated from an .mecore file. Removing this annotation will signal the MinimalEcore builder to keep this element."
		   });	
		addAnnotation
		  (getArticle_Sources(), 
		   source, 
		   new String[] {
			 "WARNING", "This element was generated from an .mecore file. Removing this annotation will signal the MinimalEcore builder to keep this element."
		   });
	}

} //SourceanalysatorPackageImpl
