package net.reimone.sourceanalysator.model.tests;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.junit.Test;

import com.google.common.base.Function;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;

import net.reimone.sourceanalysator.Article;
import net.reimone.sourceanalysator.GeneralSource;
import net.reimone.sourceanalysator.Library;
import net.reimone.sourceanalysator.Source;
import net.reimone.sourceanalysator.SourceanalysatorFactory;
import net.reimone.sourceanalysator.core.ISourceAnalysator;
import net.reimone.sourceanalysator.core.impl.SourceAnalysator;

public class InvestigateLibraryTest extends AbstractSourceAnalysatorTest {

	private GeneralSource spiegel;
	private GeneralSource guardian;
	
	@Test
	public void analyseNullArticleTest() {
		Article article = null;
		Library library = createSimpleLibrary();
		ISourceAnalysator analysator = getSourceAnalysator(library);
		Map<GeneralSource, List<Source>> generalSources = analysator.getGeneralSourcesOfArticle(article);
		
		assertThat("general sources of null article", generalSources, is(notNullValue()));
		assertThat("count of general sources of null article", generalSources.size(), is(equalTo(0)));
	}
	
	@Test
	public void analyseSingleArticleSourcesTest() {
		Library library = createSimpleLibrary();
		Article article = library.getArticles().get(0);
		assertThat("name of article", article.getTitle(), is(equalTo("Fuck the system")));
		
		ISourceAnalysator analysator = getSourceAnalysator(library);
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
	
	@Test
	public void analyseMultipleArticleSourcesTest() {
		Library library = createSimpleLibrary();
		
		Article article1 = library.getArticles().get(0);
		assertThat("name of article", article1.getTitle(), is(equalTo("Fuck the system")));
		
		Article article2 = library.getArticles().get(1);
		assertThat("name of article", article2.getTitle(), is(equalTo("Refugees welcome")));
		
		ISourceAnalysator analysator = getSourceAnalysator(library);
		List<Article> articles = Lists.newArrayList(article1, article2);
		Map<GeneralSource, List<Source>> generalSources = analysator.getGeneralSourcesOfArticles(articles);
		assertThat("general sopurces count", generalSources.size(), is(equalTo(2)));
		
		List<GeneralSource> requiredSources = Lists.newArrayList(spiegel, guardian);
		for (Entry<GeneralSource, List<Source>> entry : generalSources.entrySet()) {
			GeneralSource generalSource = entry.getKey();
			boolean removedGeneralSource = requiredSources.remove(generalSource);
			assertThat("expected general source was found", removedGeneralSource, is(true));
			List<Source> sources = entry.getValue();
			if (generalSource.equals(spiegel)) {
				assertThat("count of referenced articles of " + generalSource.getName(), sources.size(), is(equalTo(3)));
			} else if (generalSource.equals(guardian)) {
				assertThat("count of referenced articles of " + generalSource.getName(), sources.size(), is(equalTo(2)));
			} else {
				fail("wrong general source");
			}
		}
		printSourcesOfArticles(Lists.newArrayList(article1, article2), generalSources);
	}

	private ISourceAnalysator getSourceAnalysator(Library library) {
		TestLibraryFactory libraryFactory = new TestLibraryFactory();
		libraryFactory.setLibrary(library);
		ISourceAnalysator analysator = new SourceAnalysator();
		analysator.initialize(libraryFactory);
		return analysator;
	}

	private void printSourcesOfArticles(List<Article> articles, Map<GeneralSource, List<Source>> generalSources) {
		System.out.println("Selected articles: " + Iterables.toString(Lists.transform(articles, new Function<Article, String>() {

			@Override
			public String apply(Article input) {
				return input.getTitle();
			}
		})));
		for (GeneralSource generalSource : generalSources.keySet()) {
			List<Source> sources = generalSources.get(generalSource);
			System.out.println(generalSource.getName() + " " + sources.size() + "x");
		}
	}
	
	private Library createSimpleLibrary() {
		SourceanalysatorFactory factory = SourceanalysatorFactory.eINSTANCE;
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
