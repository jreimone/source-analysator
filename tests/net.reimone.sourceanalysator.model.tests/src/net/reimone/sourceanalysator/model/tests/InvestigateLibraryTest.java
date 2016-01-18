package net.reimone.sourceanalysator.model.tests;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;
import org.junit.BeforeClass;
import org.junit.Test;

import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;

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

	private GeneralSource spiegel;
	private GeneralSource guardian;
	
	@Test
	public void analyseSingleArticleSourcesTest() {
		Library library = createSimpleLibrary();
		Article article = library.getArticles().get(0);
		assertThat("name of article", article.getTitle(), is(equalTo("Fuck the system")));
		
		ISourceAnalysator analysator = new SourceAnalysator(library);
		Map<GeneralSource, List<Source>> generalSources = analysator.getGeneralSourcesOfArticle(article);
		assertThat("general sopurces count", generalSources.size(), is(equalTo(2)));
		
		List<GeneralSource> requiredSources = Lists.newArrayList(spiegel, guardian);
		for (Entry<GeneralSource, List<Source>> entry : generalSources.entrySet()) {
			GeneralSource generalSource = entry.getKey();
			boolean removedGeneralSource = requiredSources.remove(generalSource);
			assertThat("expected general source was found", removedGeneralSource, is(true));
			List<Source> sources = entry.getValue();
			if (generalSource.equals(spiegel)) {
				assertThat("count of referenced articles of " + generalSource.getName(), sources.size(), is(equalTo(2)));
			} else if (generalSource.equals(guardian)) {
				assertThat("count of referenced articles of " + generalSource.getName(), sources.size(), is(equalTo(1)));
			} else {
				fail("wrong general source");
			}
		}
		printSourcesOfArticles(Lists.newArrayList(article), generalSources);
	}

	private void printSourcesOfArticles(List<Article> articles, Map<GeneralSource, List<Source>> generalSources) {
		System.out.println("Selected articles: " + Iterables.toString(articles));
		for (GeneralSource generalSource : generalSources.keySet()) {
			List<Source> sources = generalSources.get(generalSource);
			System.out.println(generalSource.getName() + " " + sources.size() + "x");
		}
	}
	
	private Library createSimpleLibrary() {
		Library library = factory.createLibrary();
		
		// create sources
		spiegel = factory.createGeneralSource();
		spiegel.setName("Spiegel");
		library.getGeneralSources().add(spiegel);
		
		guardian = factory.createGeneralSource();
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
