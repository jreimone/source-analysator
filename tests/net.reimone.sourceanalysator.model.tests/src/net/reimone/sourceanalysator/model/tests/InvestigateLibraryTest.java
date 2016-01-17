package net.reimone.sourceanalysator.model.tests;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;

import java.util.List;
import java.util.Map;

import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;
import org.junit.BeforeClass;
import org.junit.Test;

import net.reimone.sourceanalysator.Article;
import net.reimone.sourceanalysator.GeneralSource;
import net.reimone.sourceanalysator.Library;
import net.reimone.sourceanalysator.Source;
import net.reimone.sourceanalysator.SourceanalysatorFactory;
import net.reimone.sourceanalysator.SourceanalysatorPackage;
import net.reimone.sourceanalysator.core.ISourceAnalysator;
import net.reimone.sourceanalysator.core.impl.SourceAnalysator;

public class InvestigateLibraryTest {

	private static SourceanalysatorFactory factory = SourceanalysatorFactory.eINSTANCE;
	
	@BeforeClass
	public static void setUp() {
		Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap()
				.put(Resource.Factory.Registry.DEFAULT_EXTENSION, new XMIResourceFactoryImpl());
		EPackage.Registry.INSTANCE.put(SourceanalysatorPackage.eNS_URI, SourceanalysatorPackage.eINSTANCE);
	}
	
	@Test
	public void analyseSingleArticleSourcesTest() {
		Library library = createSimpleLibrary();
		Article article = library.getArticles().get(0);
		assertThat(article.getTitle(), is(equalTo("Fuck the system")));
		
		ISourceAnalysator analysator = new SourceAnalysator(library);
		Map<GeneralSource, List<Source>> generalSources = analysator.getGeneralSourcesOfArticle(article);
		
		assertThat(generalSources.size(), is(equalTo(2)));
		
	}

	private Library createSimpleLibrary() {
		Library library = factory.createLibrary();
		
		// create sources
		GeneralSource spiegel = factory.createGeneralSource();
		spiegel.setName("Spiegel");
		library.getGeneralSources().add(spiegel);
		
		GeneralSource guardian = factory.createGeneralSource();
		guardian.setName("Guardian");
		library.getGeneralSources().add(guardian);
		
		Source spiegel1 = factory.createSource();
		spiegel1.setUrl("http://www.spon.de/crap");
		spiegel1.setGeneralSource(spiegel);
		library.getSources().add(spiegel1);
		
		Source spiegel2 = factory.createSource();
		spiegel2.setUrl("http://www.spiegel.de/trash");
		spiegel2.setGeneralSource(spiegel);
		library.getSources().add(spiegel2);
		
		Source guardian1 = factory.createSource();
		guardian1.setUrl("http://www.guardian.com/article1");
		guardian1.setGeneralSource(guardian);
		library.getSources().add(guardian1);
		
		// create articles
		Article article1 = factory.createArticle();
		article1.setTitle("Fuck the system");
		article1.getSources().add(spiegel1);
		article1.getSources().add(spiegel2);
		article1.getSources().add(guardian1);
		library.getArticles().add(article1);
		
		Article article2 = factory.createArticle();
		article2.setTitle("Refugees welcome");
		article2.getSources().add(spiegel2);
		article2.getSources().add(guardian1);
		library.getArticles().add(article2);
		
		return library;
	}
}
