package net.reimone.sourceanalysator.core;

import java.util.List;
import java.util.Map;

import net.reimone.sourceanalysator.Article;
import net.reimone.sourceanalysator.GeneralSource;
import net.reimone.sourceanalysator.Library;
import net.reimone.sourceanalysator.Source;

public interface ISourceAnalysator {

	/**
	 * Returns a map which maps a general source to the sources referenced by the given article
	 */
	public Map<GeneralSource, List<Source>> getGeneralSourcesOfArticle(Article article);

	/**
	 * Returns a map which maps a general source to the sources referenced by the given articles 
	 */
	public Map<GeneralSource, List<Source>> getGeneralSourcesOfArticles(List<Article> articles);

	/**
	 * Initializes the source analysator with the given libraryFactory.
	 * 
	 * @param libraryFactory
	 */
	public void initialize(ILibraryFactory libraryFactory);

	/**
	 * Returns the singleton {@link Library} 
	 */
	public Library getSingleLibrary();

	/**
	 * Creates a new article with the given name without sources. 
	 */
	public void createArticle(String articleTitle);

}
