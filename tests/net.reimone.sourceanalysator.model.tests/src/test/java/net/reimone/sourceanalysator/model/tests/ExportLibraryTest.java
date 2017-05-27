package net.reimone.sourceanalysator.model.tests;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.junit.Before;
import org.junit.Test;

import com.google.common.collect.Lists;

import net.reimone.sourceanalysator.Article;
import net.reimone.sourceanalysator.GeneralSource;
import net.reimone.sourceanalysator.Hyperlink;
import net.reimone.sourceanalysator.Library;
import net.reimone.sourceanalysator.Source;
import net.reimone.sourceanalysator.SourceanalysatorFactory;
import net.reimone.sourceanalysator.core.IExporter;
import net.reimone.sourceanalysator.core.ISourceAnalysator;
import net.reimone.sourceanalysator.model.tests.util.AbstractSourceAnalysatorTest;
import net.reimone.sourceanalysator.model.tests.util.TestExporter;

public class ExportLibraryTest extends AbstractSourceAnalysatorTest {

	private Library library;
	private ISourceAnalysator sourceAnalysator;

	@Before
	public void setUp() {
		library = createSimpleLibrary();
		sourceAnalysator = createSourceAnalysator(library);
	}
	
	@Test
	public void testExportSimpleLibrary() {
		List<Article> articles = library.getArticles();
		Map<GeneralSource, List<Source>> generalSourcesOfArticles = sourceAnalysator.getGeneralSourcesOfArticles(articles);
		IExporter exporter = new TestExporter();
		List<Entry<String,Integer>> statistics = exporter.generateStatisticsForGeneralSourcesOfArticles(generalSourcesOfArticles);
		
		// assertions
		assertThat(statistics, is(notNullValue()));
		// 2 general sources
		assertThat(statistics.size(), is(equalTo(2)));
		testSpiegelAndGuardian(statistics);
	}
	
	@Test
	public void testExportLibraryWithoutDontCounts() {
		// first we add a general source which mustn't be counted
		SourceanalysatorFactory factory = SourceanalysatorFactory.eINSTANCE;
		GeneralSource flickr = factory.createGeneralSource();
		flickr.setDontCount(true);
		flickr.setName("Flickr");
		library.getGeneralSources().add(flickr);
		// we need some hyperlinks for this new dont-count category
		Hyperlink flickr1 = factory.createHyperlink();
		flickr1.setUrl("https://www.flickr.com/photos/use-less-useless-words/");
		library.getHyperlinks().add(flickr1);
		Hyperlink flickr2 = factory.createHyperlink();
		flickr2.setUrl("https://www.flickr.com/photos/use-less-useless-words2/");
		library.getHyperlinks().add(flickr2);
		// now we need to create new sources for the hyperlinks
		Source source1 = factory.createSource();
		source1.setHyperlink(flickr1);
		source1.setGeneralSource(flickr);
		Source source2 = factory.createSource();
		source2.setHyperlink(flickr2);
		source2.setGeneralSource(flickr);
		// these source now have to be added to the existing articles
		List<Article> articles = library.getArticles();
		assertThat(articles.size(), is(equalTo(2)));
		articles.get(0).getSources().add(source1);
		articles.get(1).getSources().add(source2);
		
		// first, check the general sources of the articles
		Map<GeneralSource, List<Source>> generalSourcesOfArticles = sourceAnalysator.getGeneralSourcesOfArticles(articles);
		assertThat(generalSourcesOfArticles.size(), is(equalTo(3)));
		ArrayList<String> categories = Lists.newArrayList("Spiegel", "Guardian", "Flickr");
		for (Entry<GeneralSource, List<Source>> entry : generalSourcesOfArticles.entrySet()) {
			assertThat(categories.remove(entry.getKey().getName()), is(true));
		}
		assertThat(categories.isEmpty(), is(true));
		
		// second, in contrast to the 3 categories there must only 2 included in the exported statistics
		IExporter exporter = new TestExporter();
		List<Entry<String,Integer>> statistics = exporter.generateStatisticsForGeneralSourcesOfArticles(generalSourcesOfArticles);
		
		// assertions
		assertThat(statistics, is(notNullValue()));
		// 2 general sources
		assertThat(statistics.size(), is(equalTo(2)));
		testSpiegelAndGuardian(statistics);
	}

	private void testSpiegelAndGuardian(List<Entry<String, Integer>> statistics) {
		for (Entry<String, Integer> entry : statistics) {
			String category = entry.getKey();
			Integer categoryCount = entry.getValue();
			if ("Spiegel".equals(category)) {
				assertThat(categoryCount, is(equalTo(3)));
			} else if("Guardian".equals(category)) {
				assertThat(categoryCount, is(equalTo(2)));
			} else {
				fail("unrecognized category: " + category);
			}
		}
	}
	
	
}
