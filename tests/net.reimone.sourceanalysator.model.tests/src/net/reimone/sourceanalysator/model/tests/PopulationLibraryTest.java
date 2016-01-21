package net.reimone.sourceanalysator.model.tests;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.List;

import org.junit.Test;

import net.reimone.sourceanalysator.Article;
import net.reimone.sourceanalysator.Library;
import net.reimone.sourceanalysator.core.ILibraryFactory;
import net.reimone.sourceanalysator.core.ISourceAnalysator;
import net.reimone.sourceanalysator.core.impl.SourceAnalysator;

public class PopulationLibraryTest extends AbstractSourceAnalysatorTest {

	@Test
	public void createArticleWithoutSourcesTest() {
		ISourceAnalysator sourceAnalysator = createSourceAnalysator();
		
		Library library = sourceAnalysator.getSingleLibrary();
		List<Article> articles = library.getArticles();
		assertThat("article counts in library", articles.size(), is(equalTo(0)));
		
		String articleTitle = "Refugees welcome";
		sourceAnalysator.createArticle(articleTitle);
		assertThat("article counts in library", articles.size(), is(equalTo(1)));

		Article newArticle = articles.get(0);
		assertThat("title of the new article", newArticle.getTitle(), is(equalTo(articleTitle)));
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
