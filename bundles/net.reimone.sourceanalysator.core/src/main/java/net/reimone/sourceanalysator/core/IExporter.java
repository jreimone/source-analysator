package net.reimone.sourceanalysator.core;

import java.io.File;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import net.reimone.sourceanalysator.Article;
import net.reimone.sourceanalysator.GeneralSource;
import net.reimone.sourceanalysator.Source;

/**
 * Use this interface to provide different export mechanisms.
 */
public interface IExporter {

	/**
	 * Export statistics of given <code>articles</code> and <code>generalSourcesOfArticles</code> to a file.
	 */
	public File export(List<Article> articles,  List<Entry<String,Integer>> statistics);
	
	/**
	 * Generates the final statistics by adding all counts of general sources with same name together and
	 */
	public List<Entry<String, Integer>> generateStatisticsForGeneralSourcesOfArticles(Map<GeneralSource, List<Source>> generalSourcesOfArticles);
}
