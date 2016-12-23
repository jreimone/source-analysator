package net.reimone.sourceanalysator.model.tests;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.hasItems;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.junit.Test;

import net.reimone.sourceanalysator.Article;
import net.reimone.sourceanalysator.GeneralSource;
import net.reimone.sourceanalysator.Hyperlink;
import net.reimone.sourceanalysator.Library;
import net.reimone.sourceanalysator.Source;
import net.reimone.sourceanalysator.SourceanalysatorFactory;
import net.reimone.sourceanalysator.core.ISourceAnalysator;
import net.reimone.sourceanalysator.model.tests.util.AbstractSourceAnalysatorTest;

public class PopulationLibraryTest extends AbstractSourceAnalysatorTest {

	@Test
	public void retrieveHyperlinksFromLocalFileTest() {
		ISourceAnalysator sourceAnalysator = createSourceAnalysator();
		Article article = sourceAnalysator.createOrGetArticle(ARTICLE_TITLE,
				getAbsolutePathOfFile(ARTICLE_FILE_REFUGEES_SMALL));
		Set<String> hyperlinks = sourceAnalysator.retrieveHyperlinksFromLocalFile(article);
		System.out.println(hyperlinks);
		assertThat("hyperlinks", hyperlinks, is(notNullValue()));
		assertThat("hyperlinks in localFile", new ArrayList<>(hyperlinks), hasItems(ARTICLE_HYPERLINKS));
		assertThat("hyperlink count in localFile", hyperlinks.size(), is(equalTo(ARTICLE_HYPERLINKS.length)));
	}

	@Test
	public void generateSourcesForArticleTest() {
		ISourceAnalysator sourceAnalysator = createSourceAnalysator();
		Article article = sourceAnalysator.createOrGetArticle(ARTICLE_TITLE,
				getAbsolutePathOfFile(ARTICLE_FILE_REFUGEES_SMALL));
		assertThat("article", article, is(notNullValue()));

		Map<GeneralSource, List<Source>> generalSourcesOfArticle = sourceAnalysator.getGeneralSourcesOfArticle(article);
		assertThat(generalSourcesOfArticle.isEmpty(), is(true));

		sourceAnalysator.generateSourcesForArticle(article);
		// assertions
		generalSourcesOfArticle = sourceAnalysator.getGeneralSourcesOfArticle(article);
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
			assertThat("returned and linked general source of source", generalSource,
					is(equalTo(generalSourceOfSource)));
			String generalSourceName = generalSource.getName();
			assertThat("name of general source", generalSourceName, is(equalTo(recommendGeneralSourceName)));
			List<String> aliases = generalSource.getAliases();
			System.out.println("aliases of '" + generalSourceName + "': " + aliases);
		}
	}

	@Test
	public void modifyAliasTest() {
		ISourceAnalysator sourceAnalysator = createSourceAnalysator();

		// use default general source name
		String urlReference = "http://www.spiegel.de/123";
		Hyperlink hyperlink = sourceAnalysator.createOrGetHyperlink(urlReference);
		String recommendGeneralSourceName = sourceAnalysator.recommendGeneralSourceName(hyperlink);
		GeneralSource generalSource = sourceAnalysator.createOrGetGeneralSource(recommendGeneralSourceName);
		Source source = SourceanalysatorFactory.eINSTANCE.createSource();
		source.setHyperlink(hyperlink);
		sourceAnalysator.linkSourceWithGeneralSource(source, generalSource);
		assertThat(generalSource.getName(), is(equalTo("spiegel")));
		List<String> aliases = generalSource.getAliases();
		assertThat(aliases.size(), is(equalTo(1)));
		assertThat(aliases.contains("spiegel.de"), is(true));

		// check if recommendation for different URL results in same general source
		String newURL = "spiegel.de/nö";
		hyperlink = sourceAnalysator.createOrGetHyperlink(newURL);
		recommendGeneralSourceName = sourceAnalysator.recommendGeneralSourceName(hyperlink);
		GeneralSource newGeneralSource = sourceAnalysator.createOrGetGeneralSource(recommendGeneralSourceName);
		source = SourceanalysatorFactory.eINSTANCE.createSource();
		source.setHyperlink(hyperlink);
		sourceAnalysator.linkSourceWithGeneralSource(source, generalSource);
		assertThat(newGeneralSource.getName(), is(equalTo("spiegel")));
		assertThat(newGeneralSource, is(equalTo(generalSource)));
		assertThat(aliases.size(), is(equalTo(1)));
		assertThat(aliases.contains("spiegel.de"), is(true));

		// set general source name to another new alias
		String newGeneralSourceName = "WTF";
		newGeneralSource = sourceAnalysator.setGeneralSourceOfSource(source, newGeneralSourceName);
		assertThat(newGeneralSource.getName(), is(equalTo("WTF")));
		assertThat(newGeneralSource, is(equalTo(generalSource)));
		assertThat(aliases.size(), is(equalTo(2)));
		assertThat(aliases.contains("spiegel"), is(true));
		assertThat(aliases.contains("spiegel.de"), is(true));
	}

	@Test
	public void recommendGeneralSourceForSourceWithExistingGeneralSourceTest() {
		ISourceAnalysator sourceAnalysator = createSourceAnalysator();

		String generalSourceName = "WTF";
		GeneralSource generalSource = sourceAnalysator.createOrGetGeneralSource(generalSourceName);
		String urlReference = "http://www.abc.co.uk/123";
		Hyperlink hyperlink = sourceAnalysator.createOrGetHyperlink(urlReference);
		Source source = SourceanalysatorFactory.eINSTANCE.createSource();
		source.setHyperlink(hyperlink);
		sourceAnalysator.linkSourceWithGeneralSource(source, generalSource);

		String url = "http://www.abc.co.uk/5678";
		hyperlink = sourceAnalysator.createOrGetHyperlink(url);
		String recommendedGeneralSourceName = sourceAnalysator.recommendGeneralSourceName(hyperlink);
		assertThat(recommendedGeneralSourceName, is(equalTo(generalSourceName)));

		url = "http://www.foo.abc.co.uk/5678";
		hyperlink = sourceAnalysator.createOrGetHyperlink(url);
		recommendedGeneralSourceName = sourceAnalysator.recommendGeneralSourceName(hyperlink);
		assertThat(recommendedGeneralSourceName, is(equalTo(generalSourceName)));

		url = "http://foo.abc.co.uk/5678";
		hyperlink = sourceAnalysator.createOrGetHyperlink(url);
		recommendedGeneralSourceName = sourceAnalysator.recommendGeneralSourceName(hyperlink);
		assertThat(recommendedGeneralSourceName, is(equalTo(generalSourceName)));

		url = "foo.abc.co.uk/5678/34/97/?arr";
		hyperlink = sourceAnalysator.createOrGetHyperlink(url);
		recommendedGeneralSourceName = sourceAnalysator.recommendGeneralSourceName(hyperlink);
		assertThat(recommendedGeneralSourceName, is(equalTo(generalSourceName)));
	}

	@Test
	public void linkSourceAndGeneralSourceTest() {
		ISourceAnalysator sourceAnalysator = createSourceAnalysator();

		String url = "http://www.abc.co.uk/123";
		Hyperlink hyperlink = sourceAnalysator.createOrGetHyperlink(url);
		Source source = SourceanalysatorFactory.eINSTANCE.createSource();
		source.setHyperlink(hyperlink);
		GeneralSource generalSourceOfArticle = source.getGeneralSource();
		assertThat("general Source Of Article", generalSourceOfArticle, is(nullValue()));

		String recommendedGeneralSourceName = sourceAnalysator.recommendGeneralSourceName(hyperlink);
		GeneralSource generalSource = sourceAnalysator.createOrGetGeneralSource(recommendedGeneralSourceName);
		sourceAnalysator.linkSourceWithGeneralSource(source, generalSource);
		generalSourceOfArticle = source.getGeneralSource();
		List<Source> sources = generalSource.getSources();
		assertThat("general Source Of Article", generalSourceOfArticle, is(notNullValue()));
		assertThat("count of linked sources", sources.size(), is(equalTo(1)));
		assertThat("general source of article", generalSourceOfArticle, is(equalTo(generalSource)));
		assertThat("source of general source", sources.get(0), is(equalTo(source)));
	}

	@Test
	public void recommendGeneralSourceForSourceTest() {
		ISourceAnalysator sourceAnalysator = createSourceAnalysator();

		Library library = sourceAnalysator.getSingleLibrary();
		List<Hyperlink> hyperlinks = library.getHyperlinks();
		assertThat("sources counts in library", hyperlinks.size(), is(equalTo(0)));

		String url = "http://www.abc.co.uk/123";
		Hyperlink hyperlink = sourceAnalysator.createOrGetHyperlink(url);
		String recommendedGeneralSourceName = sourceAnalysator.recommendGeneralSourceName(hyperlink);
		String expectedGeneralSourceName = "abc";
		assertThat("recommended general source name", recommendedGeneralSourceName,
				is(equalTo(expectedGeneralSourceName)));
	}

	@Test
	public void createOrGetHyperlinkTest() {
		ISourceAnalysator sourceAnalysator = createSourceAnalysator();

		Library library = sourceAnalysator.getSingleLibrary();
		List<Hyperlink> hyperlinks = library.getHyperlinks();
		assertThat("sources counts in library", hyperlinks.size(), is(equalTo(0)));

		// create new source
		String url = "http://www.abc.co.uk/123";
		Hyperlink hyperlink = sourceAnalysator.createOrGetHyperlink(url);
		assertThat("hyperlink", hyperlink, is(notNullValue()));
		assertThat("hyperlinks counts in library", hyperlinks.size(), is(equalTo(1)));
		assertThat(hyperlink, is(equalTo(hyperlinks.get(0))));

		// retrieve existing source
		Hyperlink existingHyperlink = sourceAnalysator.createOrGetHyperlink(url);
		assertThat("existingHyperlink", existingHyperlink, is(notNullValue()));
		assertThat("hyperlinks counts in library", hyperlinks.size(), is(equalTo(1)));
		assertThat(existingHyperlink, is(equalTo(hyperlink)));
	}

	@Test
	public void createOrGetGeneralSourceTest() {
		ISourceAnalysator sourceAnalysator = createSourceAnalysator();

		Library library = sourceAnalysator.getSingleLibrary();
		List<GeneralSource> generalSources = library.getGeneralSources();
		assertThat("general sources counts in library", generalSources.size(), is(equalTo(0)));

		// create new general source
		String generalSourceName = "muRRR";
		GeneralSource generalSource = sourceAnalysator.createOrGetGeneralSource(generalSourceName);
		assertThat("general source", generalSource, is(notNullValue()));
		assertThat("general sources counts in library", generalSources.size(), is(equalTo(1)));
		assertThat(generalSource, is(equalTo(generalSources.get(0))));

		// retrieve existing general source
		GeneralSource existingGeneralSource = sourceAnalysator.createOrGetGeneralSource(generalSourceName);
		assertThat("general source", generalSource, is(notNullValue()));
		assertThat("general sources counts in library", generalSources.size(), is(equalTo(1)));
		assertThat(existingGeneralSource, is(equalTo(generalSource)));
	}

	@Test
	public void createOrGetArticleNullTest() {
		ISourceAnalysator sourceAnalysator = createSourceAnalysator();
		Library library = sourceAnalysator.getSingleLibrary();
		List<Article> articles = library.getArticles();
		assertThat("article counts in library", articles.size(), is(equalTo(0)));

		// both are null
		String articleTitle = null;
		String articleFileString = null;
		Article newArticle = sourceAnalysator.createOrGetArticle(articleTitle, articleFileString);
		assertThat("article", newArticle, is(nullValue()));

		// article name null
		articleTitle = null;
		articleFileString = ARTICLE_FILE_REFUGEES;
		newArticle = sourceAnalysator.createOrGetArticle(articleTitle, articleFileString);
		assertThat("article", newArticle, is(nullValue()));

		// file name is null
		articleTitle = "Refugees welcome";
		articleFileString = null;
		newArticle = sourceAnalysator.createOrGetArticle(articleTitle, articleFileString);
		assertThat("article", newArticle, is(nullValue()));

	}

	@Test
	public void createOrGetArticleTest() {
		ISourceAnalysator sourceAnalysator = createSourceAnalysator();

		Library library = sourceAnalysator.getSingleLibrary();
		List<Article> articles = library.getArticles();
		assertThat("article counts in library", articles.size(), is(equalTo(0)));

		// create new article
		String articleTitle = "Refugees welcome";
		Article newArticle = sourceAnalysator.createOrGetArticle(articleTitle, ARTICLE_FILE_REFUGEES);
		assertThat("article", newArticle, is(notNullValue()));
		assertThat("article counts in library", articles.size(), is(equalTo(1)));
		assertThat("title of the new article", newArticle.getTitle(), is(equalTo(articles.get(0).getTitle())));
		assertThat("localFile of the new article", newArticle.getLocalFile(),
				is(equalTo(articles.get(0).getLocalFile())));

		// retrieve existing article
		Article existingArticle = sourceAnalysator.createOrGetArticle(articleTitle, ARTICLE_FILE_REFUGEES);
		assertThat("article counts in library", articles.size(), is(equalTo(1)));
		assertThat("articles are equal", existingArticle, is(equalTo(newArticle)));

		// create article with different localFile property
		newArticle = sourceAnalysator.createOrGetArticle(articleTitle, ARTICLE_FILE_REFUGEES + ".different");
		assertThat("article", newArticle, is(notNullValue()));
		assertThat("article counts in library", articles.size(), is(equalTo(2)));
		assertThat("title of the new article", newArticle.getTitle(), is(equalTo(articles.get(0).getTitle())));
		assertThat("localFile of the new article", newArticle.getLocalFile(),
				is(equalTo(ARTICLE_FILE_REFUGEES + ".different")));
	}

	@Test
	public void getSingletonLibraryTest() {
		ISourceAnalysator analysator = createSourceAnalysator();

		Library library1 = analysator.getSingleLibrary();
		Library library2 = analysator.getSingleLibrary();

		assertThat(library1, is(equalTo(library2)));
	}

}
