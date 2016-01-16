package net.reimone.sourceanalysator.model.tests;

import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;
import org.junit.Before;

import net.reimone.sourceanalysator.Library;
import net.reimone.sourceanalysator.SourceanalysatorFactory;
import net.reimone.sourceanalysator.SourceanalysatorPackage;

public class InvestigateLibraryTest {

	@Before
	public void setUp() {
		Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap()
				.put(Resource.Factory.Registry.DEFAULT_EXTENSION, new XMIResourceFactoryImpl());
		EPackage.Registry.INSTANCE.put(SourceanalysatorPackage.eNS_URI, SourceanalysatorPackage.eINSTANCE);
	}

	private Library createSimpleLibrary() {
		Library library = SourceanalysatorFactory.eINSTANCE.createLibrary();
		return library;
	}
}
