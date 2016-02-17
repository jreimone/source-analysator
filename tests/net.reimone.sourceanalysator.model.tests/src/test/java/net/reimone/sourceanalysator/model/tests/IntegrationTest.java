package net.reimone.sourceanalysator.model.tests;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.hasItems;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.junit.Test;

import net.reimone.sourceanalysator.Article;
import net.reimone.sourceanalysator.GeneralSource;
import net.reimone.sourceanalysator.Source;
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
			String recommendGeneralSourceName = sourceAnalysator.recommendGeneralSourceName(url);
			System.out.println(recommendGeneralSourceName);
			
			// create general sources for recommendations
			GeneralSource generalSource = sourceAnalysator.createOrGetGeneralSource(recommendGeneralSourceName);
			
			// create sources
			Source source = sourceAnalysator.createOrGetSource(url);
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
			String url = source.getUrl();
			String recommendGeneralSourceName = sourceAnalysator.recommendGeneralSourceName(url);
			GeneralSource generalSourceOfSource = source.getGeneralSource();
			
			GeneralSource generalSource = entry.getKey();
			assertThat("returned and linked general source of source", generalSource, is(equalTo(generalSourceOfSource)));
			String generalSourceName = generalSource.getName();
			assertThat("name of general source", generalSourceName, is(equalTo(recommendGeneralSourceName)));
			List<String> aliases = generalSource.getAliases();
			System.out.println("aliases of '" + generalSourceName + "': " + aliases);
		}
	}
}
