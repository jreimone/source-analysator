package net.reimone.sourceanalysator.model.tests.util;

import static org.junit.Assert.fail;

import java.io.File;

import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;
import org.junit.BeforeClass;

import net.reimone.sourceanalysator.Library;
import net.reimone.sourceanalysator.SourceanalysatorPackage;
import net.reimone.sourceanalysator.core.ILibraryFactory;
import net.reimone.sourceanalysator.core.ISourceAnalysator;
import net.reimone.sourceanalysator.core.impl.SourceAnalysator;

public class AbstractSourceAnalysatorTest {

	public static final String ARTICLE_FILE_REFUGEES = "src/test/resources/refugees.docx";
	public static final String ARTICLE_TITLE = "Refugees welcome";
	public static final String ARTICLE_FILE_REFUGEES_SMALL = "src/test/resources/refugees_small.docx";
	public static final String[] ARTICLE_HYPERLINKS = new String[]{
			"http://www.morgenweb.de/nachrichten/politik/sie-konnen-es-nicht-lassen-1.2620328",
			"https://yougov.de/news/2016/02/09/schiessbefehl-und-verfassungstreue-der-afd-informa/",
			"http://www.wahlrecht.de/umfragen/insa.htm"
	};
	
	@BeforeClass
	public static void setUp() {
		Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap()
				.put(Resource.Factory.Registry.DEFAULT_EXTENSION, new XMIResourceFactoryImpl());
		EPackage.Registry.INSTANCE.put(SourceanalysatorPackage.eNS_URI, SourceanalysatorPackage.eINSTANCE);
	}

	public AbstractSourceAnalysatorTest() {
		super();
	}

	protected ISourceAnalysator createSourceAnalysator() {
		ILibraryFactory libraryFactory = new TestLibraryFactory();
		ISourceAnalysator analysator = new SourceAnalysator();
		analysator.initialize(libraryFactory);
		return analysator;
	}
	
	protected ISourceAnalysator createSourceAnalysator(Library library) {
		TestLibraryFactory libraryFactory = new TestLibraryFactory();
		libraryFactory.setLibrary(library);
		ISourceAnalysator analysator = new SourceAnalysator();
		analysator.initialize(libraryFactory);
		return analysator;
	}
	
	protected static String getAbsolutePathOfFile(String localFile) {
		File file = new File(localFile);
		if (!file.exists()) {
			fail("file " + localFile + " must exist");
		}
		
		return file.getAbsolutePath();
	}
}