package net.reimone.sourceanalysator.model.tests.util;

import static org.junit.Assert.fail;

import java.io.File;

import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;
import org.junit.BeforeClass;

import net.reimone.sourceanalysator.Article;
import net.reimone.sourceanalysator.GeneralSource;
import net.reimone.sourceanalysator.Hyperlink;
import net.reimone.sourceanalysator.Library;
import net.reimone.sourceanalysator.Source;
import net.reimone.sourceanalysator.SourceanalysatorFactory;
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
	
	protected GeneralSource spiegel;
	protected GeneralSource guardian;
	
	@BeforeClass
	public static void setUpStatic() {
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
	
	protected Library createSimpleLibrary() {
		SourceanalysatorFactory factory = SourceanalysatorFactory.eINSTANCE;
		Library library = factory.createLibrary();
		
		// create general sources
		spiegel = factory.createGeneralSource();
		spiegel.setName("Spiegel");
		library.getGeneralSources().add(spiegel);
		
		guardian = factory.createGeneralSource();
		guardian.setName("Guardian");
		library.getGeneralSources().add(guardian);
		
		Hyperlink spiegel1 = factory.createHyperlink();
		spiegel1.setUrl("http://www.spon.de/crap");
		library.getHyperlinks().add(spiegel1);
		
		Hyperlink spiegel2 = factory.createHyperlink();
		spiegel2.setUrl("http://www.spiegel.de/trash");
		library.getHyperlinks().add(spiegel2);
		
		Hyperlink guardian1 = factory.createHyperlink();
		guardian1.setUrl("http://www.guardian.com/article1");
		library.getHyperlinks().add(guardian1);
		
		// create articles
		Article article1 = factory.createArticle();
		article1.setTitle("Fuck the system");
		Source source = factory.createSource();
		source.setHyperlink(spiegel1);
		source.setGeneralSource(spiegel);
		article1.getSources().add(source);
		
		source = factory.createSource();
		source.setHyperlink(spiegel2);
		source.setGeneralSource(spiegel);
		article1.getSources().add(source);
		
		source = factory.createSource();
		source.setHyperlink(guardian1);
		source.setGeneralSource(guardian);
		article1.getSources().add(source);
		library.getArticles().add(article1);
		
		Article article2 = factory.createArticle();
		article2.setTitle(ARTICLE_TITLE);
		source = factory.createSource();
		source.setHyperlink(spiegel1);
		source.setGeneralSource(spiegel);
		article2.getSources().add(source);
		
		source = factory.createSource();
		source.setHyperlink(guardian1);
		source.setGeneralSource(guardian);
		article2.getSources().add(source);
		library.getArticles().add(article2);
		
		return library;
	}
	
	protected static String getAbsolutePathOfFile(String localFile) {
		File file = new File(localFile);
		if (!file.exists()) {
			fail("file " + localFile + " must exist");
		}
		
		return file.getAbsolutePath();
	}
}