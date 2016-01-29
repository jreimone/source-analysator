package net.reimone.sourceanalysator.model.tests.util;

import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;
import org.junit.BeforeClass;

import net.reimone.sourceanalysator.SourceanalysatorPackage;

public class AbstractSourceAnalysatorTest {

	@BeforeClass
	public static void setUp() {
		Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap()
				.put(Resource.Factory.Registry.DEFAULT_EXTENSION, new XMIResourceFactoryImpl());
		EPackage.Registry.INSTANCE.put(SourceanalysatorPackage.eNS_URI, SourceanalysatorPackage.eINSTANCE);
	}

	public AbstractSourceAnalysatorTest() {
		super();
	}

}