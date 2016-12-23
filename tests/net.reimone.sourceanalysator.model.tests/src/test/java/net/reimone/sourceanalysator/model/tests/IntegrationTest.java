package net.reimone.sourceanalysator.model.tests;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertThat;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.stream.Collectors;

import org.junit.Test;

import net.reimone.sourceanalysator.Article;
import net.reimone.sourceanalysator.GeneralSource;
import net.reimone.sourceanalysator.Hyperlink;
import net.reimone.sourceanalysator.Source;
import net.reimone.sourceanalysator.SourceanalysatorFactory;
import net.reimone.sourceanalysator.core.ISourceAnalysator;
import net.reimone.sourceanalysator.model.tests.util.AbstractSourceAnalysatorTest;

public class IntegrationTest extends AbstractSourceAnalysatorTest {

	/**
	 * Checks if the library is populated correctly by executing the following:
	 * - create article from title and file
	 * - retrieve hyperlinks
	 * - recommend categories for hyperlinks
	 * - create general sources for recommendations
	 * - create sources
	 * - add source to article
	 */
	@Test
	public void retrieveHyperlinksFromLocalFileTest() {
		ISourceAnalysator sourceAnalysator = createSourceAnalysator();
		
		// create article from title and file
		Article article = sourceAnalysator.createOrGetArticle(ARTICLE_TITLE, getAbsolutePathOfFile(ARTICLE_FILE_REFUGEES_SMALL));
		
		// retrieve hyperlinks
		Set<String> hyperlinks = sourceAnalysator.retrieveHyperlinksFromLocalFile(article);
		System.out.println(hyperlinks);
		assertThat("hyperlinks", hyperlinks, is(notNullValue()));
		assertThat("hyperlinks in localFile", new ArrayList<>(hyperlinks), hasItems(ARTICLE_HYPERLINKS));
		assertThat("hyperlink count in localFile", hyperlinks.size(), is(equalTo(ARTICLE_HYPERLINKS.length)));
		
		for (String url : hyperlinks) {
			// recommend categories for hyperlinks
			Hyperlink hyperlink = sourceAnalysator.createOrGetHyperlink(url);
			String recommendGeneralSourceName = sourceAnalysator.recommendGeneralSourceName(hyperlink);
			System.out.println(recommendGeneralSourceName);
			
			// create general sources for recommendations
			GeneralSource generalSource = sourceAnalysator.createOrGetGeneralSource(recommendGeneralSourceName);
			
			// create sources
			Source source = SourceanalysatorFactory.eINSTANCE.createSource();
			source.setHyperlink(hyperlink);
			sourceAnalysator.linkSourceWithGeneralSource(source, generalSource);
			
			// add source to article
			article.getSources().add(source);
		}
		
		// assertions
		Map<GeneralSource, List<Source>> generalSourcesOfArticle = sourceAnalysator.getGeneralSourcesOfArticle(article);
		assertThat("generalSourcesOfArticle", generalSourcesOfArticle, is(notNullValue()));
		assertThat("generalSourcesOfArticle count", generalSourcesOfArticle.size(), is(equalTo(3)));
		for (Entry<GeneralSource, List<Source>> entry : generalSourcesOfArticle.entrySet()) {
			List<Source> sources = entry.getValue();
			assertThat("sourcesOfArticle count", sources.size(), is(equalTo(1)));
			Source source = sources.get(0);
			Hyperlink hyperlink = source.getHyperlink();
			String recommendGeneralSourceName = sourceAnalysator.recommendGeneralSourceName(hyperlink);
			GeneralSource generalSourceOfSource = source.getGeneralSource();
			
			GeneralSource generalSource = entry.getKey();
			assertThat("returned and linked general source of source", generalSource, is(equalTo(generalSourceOfSource)));
			String generalSourceName = generalSource.getName();
			assertThat("name of general source", generalSourceName, is(equalTo(recommendGeneralSourceName)));
			List<String> aliases = generalSource.getAliases();
			System.out.println("aliases of '" + generalSourceName + "': " + aliases);
		}
	}
	
	/**
	 * Checks if the following scenario works:
	 * - load article 1 which contains 2 sources of same category
	 * - check if only one general source (category) exists which references both sources
	 * - load article 2 which contains 1 source which is also contained in the article 1
	 * - check if the only general source references 3 sources
	 * - set general source name for the source which is contained in both articles
	 * - check if still one general source exists and that it only references the 3 sources
	 */
	@Test
	public void modifyAliasAndReanalyzeTest() {
		ISourceAnalysator sourceAnalysator = createSourceAnalysator();

		// load the first article
		Article article1 = sourceAnalysator.createOrGetArticle(ARTICLE_TITLE,
				getAbsolutePathOfFile("src/test/resources/refugees_sameCategories.docx"));
		sourceAnalysator.generateSourcesForArticle(article1);
		List<Source> sources = article1.getSources();
		assertThat(sources, is(notNullValue()));
		Hyperlink yougov1 = sourceAnalysator.createOrGetHyperlink(
				"https://yougov.de/news/2016/02/09/schiessbefehl-und-verfassungstreue-der-afd-informa/");
		Hyperlink yougov2 = sourceAnalysator.createOrGetHyperlink("https://www.yougov.de/neuneuneu");
		List<Hyperlink> hyperlinks = sources.stream().map(source -> source.getHyperlink()).collect(Collectors.toList());
		assertThat(hyperlinks, hasItems(yougov1));
		assertThat(hyperlinks, hasItems(yougov2));
		assertThat(hyperlinks.size(), is(equalTo(2)));
		assertThat(sources.size(), is(equalTo(2)));
		// check default general source name
		Source source1 = sources.get(0);
		GeneralSource generalSource1 = source1.getGeneralSource();
		Source source2 = sources.get(1);
		GeneralSource generalSource2 = source2.getGeneralSource();
		assertThat(generalSource2, is(equalTo(generalSource1)));
		String name = generalSource1.getName();
		assertThat(name, is(equalTo("yougov")));
		List<String> aliases = generalSource1.getAliases();
		assertThat(aliases, is(notNullValue()));
		assertThat(aliases, hasItems("yougov.de"));
		assertThat(aliases.size(), is(equalTo(1)));
		// check sources of general source
		List<Source> sourcesOfGeneralSource = generalSource1.getSources();
		assertThat(sourcesOfGeneralSource, hasItems(sources.toArray(new Source[0])));
		assertThat(sourcesOfGeneralSource.size(), is(equalTo(2)));

		// load the second article
		Article article2 = sourceAnalysator.createOrGetArticle(ARTICLE_TITLE + "2",
				getAbsolutePathOfFile("src/test/resources/refugees_sameCategories2.docx"));
		sourceAnalysator.generateSourcesForArticle(article2);
		List<Source> sources2 = article2.getSources();
		assertThat(sources2, is(notNullValue()));
		Hyperlink yougov3 = sourceAnalysator.createOrGetHyperlink("https://www.yougov.de/neuneuneu");
		List<Hyperlink> hyperlinks2 = sources2.stream().map(source -> source.getHyperlink()).collect(Collectors.toList());
		assertThat(hyperlinks2, hasItems(yougov3));
		assertThat(hyperlinks2.size(), is(equalTo(1)));
		assertThat(sources2.size(), is(equalTo(1)));
		// check that source 2 is not equal to source 3
		Source source3 = sources2.get(0);
		assertThat(source3, is(not(equalTo(source2))));
		List<Source> yougov3Sources = yougov3.getSources();
		List<Article> articles = yougov3Sources.stream().map(source -> source.getArticle()).collect(Collectors.toList());
		assertThat(articles, hasItems(article1, article2));
		assertThat(articles.size(), is(equalTo(2)));
		// check default general source 
		GeneralSource generalSource3 = source3.getGeneralSource();
		assertThat(generalSource3, is(equalTo(generalSource1)));
		name = generalSource3.getName();
		assertThat(name, is(equalTo("yougov")));
		aliases = generalSource3.getAliases();
		assertThat(aliases, is(notNullValue()));
		assertThat(aliases, hasItems("yougov.de"));
		assertThat(aliases.size(), is(equalTo(1)));
		// check sources of general source
		sourcesOfGeneralSource = generalSource3.getSources();
		assertThat(sourcesOfGeneralSource, hasItems(sources.toArray(new Source[0])));
		assertThat(sourcesOfGeneralSource.size(), is(equalTo(3)));
	}
}
