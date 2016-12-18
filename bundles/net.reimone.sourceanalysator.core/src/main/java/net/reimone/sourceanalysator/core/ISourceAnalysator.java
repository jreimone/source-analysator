package net.reimone.sourceanalysator.core;

import java.util.List;
import java.util.Map;
import java.util.Set;

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
	 * Creates a new article with the given name or retrieves the existing article with that name. 
	 * @return 
	 */
	public Article createOrGetArticle(String articleTitle, String localFile);

//	/**
//	 * Retrieves the {@link GeneralSource} with the given name or <code>null</code>. 
//	 */
//	public GeneralSource getGeneralSourceByName(String generalSourceName);
	
	/**
	 * Creates a new {@link GeneralSource} or retrieves the existing one with the given name. 
	 */
	public GeneralSource createOrGetGeneralSource(String generalSourceName);

	/**
	 * First, extracts the domain (without contry code) from the given domain.
	 * If articles from that domain already exist and are linked to another general source,
	 * the name of the linked general source is returned. Otherwise, the extracted domain.
	 */
	public String recommendGeneralSourceName(String url);

	/**
	 * Creates a new {@link Source} or retrieves the existing one with the given url. 
	 */
	public Source createOrGetSource(String url);

	public GeneralSource setGeneralSourceOfSource(Source source, String newName);
	
	/**
	 * Links the given source with the given general source.
	 */
	public void linkSourceWithGeneralSource(Source source, GeneralSource generalSource);

	/**
	 * Extracts all hyperlinks contained in the file being resolved from {@link Article#getLocalFile()}.
	 * @param article
	 * @return
	 */
	public Set<String> retrieveHyperlinksFromLocalFile(Article article);

	/**
	 * Generates the {@link Source}s for the given {@link Article} and adds them to the {@link Library}.
	 */
	public void generateSourcesForArticle(Article article);

}
