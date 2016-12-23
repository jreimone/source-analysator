/**
 */
package net.reimone.sourceanalysator.impl;

import net.reimone.sourceanalysator.*;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.impl.EFactoryImpl;

import org.eclipse.emf.ecore.plugin.EcorePlugin;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class SourceanalysatorFactoryImpl extends EFactoryImpl implements SourceanalysatorFactory {
	/**
	 * Creates the default factory implementation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static SourceanalysatorFactory init() {
		try {
			SourceanalysatorFactory theSourceanalysatorFactory = (SourceanalysatorFactory)EPackage.Registry.INSTANCE.getEFactory(SourceanalysatorPackage.eNS_URI);
			if (theSourceanalysatorFactory != null) {
				return theSourceanalysatorFactory;
			}
		}
		catch (Exception exception) {
			EcorePlugin.INSTANCE.log(exception);
		}
		return new SourceanalysatorFactoryImpl();
	}

	/**
	 * Creates an instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SourceanalysatorFactoryImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EObject create(EClass eClass) {
		switch (eClass.getClassifierID()) {
			case SourceanalysatorPackage.GENERAL_SOURCE: return createGeneralSource();
			case SourceanalysatorPackage.SOURCE: return createSource();
			case SourceanalysatorPackage.LIBRARY: return createLibrary();
			case SourceanalysatorPackage.ARTICLE: return createArticle();
			case SourceanalysatorPackage.HYPERLINK: return createHyperlink();
			default:
				throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public GeneralSource createGeneralSource() {
		GeneralSourceImpl generalSource = new GeneralSourceImpl();
		return generalSource;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Source createSource() {
		SourceImpl source = new SourceImpl();
		return source;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Library createLibrary() {
		LibraryImpl library = new LibraryImpl();
		return library;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Article createArticle() {
		ArticleImpl article = new ArticleImpl();
		return article;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Hyperlink createHyperlink() {
		HyperlinkImpl hyperlink = new HyperlinkImpl();
		return hyperlink;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SourceanalysatorPackage getSourceanalysatorPackage() {
		return (SourceanalysatorPackage)getEPackage();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @deprecated
	 * @generated
	 */
	@Deprecated
	public static SourceanalysatorPackage getPackage() {
		return SourceanalysatorPackage.eINSTANCE;
	}

} //SourceanalysatorFactoryImpl
