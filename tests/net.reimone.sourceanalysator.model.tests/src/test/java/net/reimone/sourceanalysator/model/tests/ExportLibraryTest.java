package net.reimone.sourceanalysator.model.tests;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.junit.Before;
import org.junit.Test;

import net.reimone.sourceanalysator.Article;
import net.reimone.sourceanalysator.GeneralSource;
import net.reimone.sourceanalysator.Library;
import net.reimone.sourceanalysator.Source;
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
