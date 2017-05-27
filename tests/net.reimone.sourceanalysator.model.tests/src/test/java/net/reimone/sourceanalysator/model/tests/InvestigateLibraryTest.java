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

import com.google.common.collect.Iterators;
import com.google.common.collect.Lists;

import net.reimone.sourceanalysator.Article;
import net.reimone.sourceanalysator.GeneralSource;
import net.reimone.sourceanalysator.Library;
import net.reimone.sourceanalysator.Source;
import net.reimone.sourceanalysator.core.ISourceAnalysator;
import net.reimone.sourceanalysator.model.tests.util.AbstractSourceAnalysatorTest;

public class InvestigateLibraryTest extends AbstractSourceAnalysatorTest {

	@Test
	public void analyseNullArticleTest() {
		Article article = null;
		Library library = createSimpleLibrary();
		ISourceAnalysator analysator = createSourceAnalysator(library);
		Map<GeneralSource, List<Source>> generalSources = analysator.getGeneralSourcesOfArticle(article);
		
		assertThat("general sources of null article", generalSources, is(notNullValue()));
		assertThat("count of general sources of null article", generalSources.size(), is(equalTo(0)));
	}
	
	@Test
	public void analyseSingleArticleSourcesTest() {
		Library library = createSimpleLibrary();
		Article article = library.getArticles().get(0);
		assertThat("name of article", article.getTitle(), is(equalTo("Fuck the system")));
		
		ISourceAnalysator analysator = createSourceAnalysator(library);
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
		
		ISourceAnalysator analysator = createSourceAnalysator(library);
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
	
	private void printSourcesOfArticles(List<Article> articles, Map<GeneralSource, List<Source>> generalSources) {
		System.out.println("Selected articles: " + Iterators.toString(articles.stream().map(article -> article.getTitle()).iterator()));
		for (GeneralSource generalSource : generalSources.keySet()) {
			List<Source> sources = generalSources.get(generalSource);
			System.out.println(generalSource.getName() + " " + sources.size() + "x");
		}
	}
	
}
