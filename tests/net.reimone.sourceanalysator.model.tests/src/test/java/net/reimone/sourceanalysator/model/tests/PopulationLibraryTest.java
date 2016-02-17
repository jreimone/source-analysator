package net.reimone.sourceanalysator.model.tests;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;

import java.util.List;

import org.junit.Test;

import net.reimone.sourceanalysator.Article;
import net.reimone.sourceanalysator.GeneralSource;
import net.reimone.sourceanalysator.Library;
import net.reimone.sourceanalysator.Source;
import net.reimone.sourceanalysator.core.ILibraryFactory;
import net.reimone.sourceanalysator.core.ISourceAnalysator;
import net.reimone.sourceanalysator.core.impl.SourceAnalysator;
import net.reimone.sourceanalysator.model.tests.util.AbstractSourceAnalysatorTest;
import net.reimone.sourceanalysator.model.tests.util.TestLibraryFactory;

public class PopulationLibraryTest extends AbstractSourceAnalysatorTest {

	private static final String ARTICLE_REFUGEES = "refugees.docx";
	
	@Test
	public void recommendGeneralSourceForSourceWithExistingGeneralSourceTest() {
		ISourceAnalysator sourceAnalysator = createSourceAnalysator();

		String generalSourceName = "WTF";
		GeneralSource generalSource = sourceAnalysator.createOrGetGeneralSource(generalSourceName);
		String urlReference = "http://www.abc.co.uk/123";
		Source source = sourceAnalysator.createOrGetSource(urlReference);
		sourceAnalysator.linkSourceWithGeneralSource(source, generalSource);
		
		String url = "http://www.abc.co.uk/5678";
		String recommendedGeneralSourceName = sourceAnalysator.recommendGeneralSourceName(url);
		assertThat(recommendedGeneralSourceName, is(equalTo(generalSourceName)));
		
		url = "http://www.foo.abc.co.uk/5678";
		recommendedGeneralSourceName = sourceAnalysator.recommendGeneralSourceName(url);
		assertThat(recommendedGeneralSourceName, is(equalTo(generalSourceName)));
		
		url = "http://foo.abc.co.uk/5678";
		recommendedGeneralSourceName = sourceAnalysator.recommendGeneralSourceName(url);
		assertThat(recommendedGeneralSourceName, is(equalTo(generalSourceName)));
		
		url = "foo.abc.co.uk/5678/34/97/?arr";
		recommendedGeneralSourceName = sourceAnalysator.recommendGeneralSourceName(url);
		assertThat(recommendedGeneralSourceName, is(equalTo(generalSourceName)));
	}
	
	@Test
	public void linkSourceAndGeneralSourceTest() {
		ISourceAnalysator sourceAnalysator = createSourceAnalysator();

		String generalSourceName = "WTF";
		GeneralSource generalSource = sourceAnalysator.createOrGetGeneralSource(generalSourceName);
		List<Source> sources = generalSource.getSources();
		assertThat("count of linked sources", sources.size(), is(equalTo(0)));

		String url = "http://www.abc.co.uk/123";
		Source source = sourceAnalysator.createOrGetSource(url);
		GeneralSource generalSourceOfArticle = source.getGeneralSource();
		assertThat("general Source Of Article", generalSourceOfArticle, is(nullValue()));
		
		sourceAnalysator.linkSourceWithGeneralSource(source, generalSource);
		generalSourceOfArticle = source.getGeneralSource();
		assertThat("general Source Of Article", generalSourceOfArticle, is(notNullValue()));
		assertThat("count of linked sources", sources.size(), is(equalTo(1)));
		assertThat("general source of article", generalSourceOfArticle, is(equalTo(generalSource)));
		assertThat("source of general source", sources.get(0), is(equalTo(source)));
	}
	
	@Test
	public void recommendGeneralSourceForSourceTest() {
		ISourceAnalysator sourceAnalysator = createSourceAnalysator();

		Library library = sourceAnalysator.getSingleLibrary();
		List<Source> sources = library.getSources();
		assertThat("sources counts in library", sources.size(), is(equalTo(0)));
		
		String url = "http://www.abc.co.uk/123";
		String recommendedGeneralSourceName = sourceAnalysator.recommendGeneralSourceName(url);
		String expectedGeneralSourceName = "abc";
		assertThat("recommended general source name", recommendedGeneralSourceName, is(equalTo(expectedGeneralSourceName)));
	}
	
	@Test
	public void createOrGetSourceSourceTest() {
		ISourceAnalysator sourceAnalysator = createSourceAnalysator();

		Library library = sourceAnalysator.getSingleLibrary();
		List<Source> sources = library.getSources();
		assertThat("sources counts in library", sources.size(), is(equalTo(0)));
		
		// create new source
		String url = "http://www.abc.co.uk/123";
		Source source = sourceAnalysator.createOrGetSource(url);
		assertThat("source", source, is(notNullValue()));
		assertThat("sources counts in library", sources.size(), is(equalTo(1)));
		assertThat(source, is(equalTo(sources.get(0))));
		
		// retrieve existing source
		Source existingSource = sourceAnalysator.createOrGetSource(url);
		assertThat("source", source, is(notNullValue()));
		assertThat("sources counts in library", sources.size(), is(equalTo(1)));
		assertThat(existingSource, is(equalTo(source)));
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
	public void createOrGetArticleTest() {
		ISourceAnalysator sourceAnalysator = createSourceAnalysator();

		Library library = sourceAnalysator.getSingleLibrary();
		List<Article> articles = library.getArticles();
		assertThat("article counts in library", articles.size(), is(equalTo(0)));

		// create new article
		String articleTitle = "Refugees welcome";
		Article newArticle = sourceAnalysator.createOrGetArticle(articleTitle, ARTICLE_REFUGEES);
		assertThat("article", newArticle, is(notNullValue()));
		assertThat("article counts in library", articles.size(), is(equalTo(1)));
		assertThat("title of the new article", newArticle.getTitle(), is(equalTo(articles.get(0).getTitle())));
		assertThat("localFile of the new article", newArticle.getLocalFile(), is(equalTo(articles.get(0).getLocalFile())));
		
		// retrieve existing article
		Article existingArticle = sourceAnalysator.createOrGetArticle(articleTitle, ARTICLE_REFUGEES);
		assertThat("article counts in library", articles.size(), is(equalTo(1)));
		assertThat("articles are equal", existingArticle, is(equalTo(newArticle)));
		
		// create article with different localFile property
		newArticle = sourceAnalysator.createOrGetArticle(articleTitle, ARTICLE_REFUGEES + ".different");
		assertThat("article", newArticle, is(notNullValue()));
		assertThat("article counts in library", articles.size(), is(equalTo(2)));
		assertThat("title of the new article", newArticle.getTitle(), is(equalTo(articles.get(0).getTitle())));
		assertThat("localFile of the new article", newArticle.getLocalFile(), is(equalTo(ARTICLE_REFUGEES + ".different")));
	}

	@Test
	public void getSingletonLibraryTest() {
		ISourceAnalysator analysator = createSourceAnalysator();

		Library library1 = analysator.getSingleLibrary();
		Library library2 = analysator.getSingleLibrary();

		assertThat(library1, is(equalTo(library2)));
	}

	private ISourceAnalysator createSourceAnalysator() {
		ILibraryFactory libraryFactory = new TestLibraryFactory();
		ISourceAnalysator analysator = new SourceAnalysator();
		analysator.initialize(libraryFactory);
		return analysator;
	}
}
