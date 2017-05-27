package net.reimone.sourceanalysator.rcp;

public interface Events {

	public static final String ARTICLE_SELECTION_CHANGED				= "article/selectionChanged";
	public static final String ARTICLE_SELECTION_CHANGED_ARTICLE		= ARTICLE_SELECTION_CHANGED + "/selectedArticle";
	
	public static final String ARTICLE_CHECKED_STATE_CHANGED			= "article/checkedStateChanged";
	public static final String ARTICLE_CHECKED_STATE_CHANGED_ARTICLES	= ARTICLE_CHECKED_STATE_CHANGED + "/checkedArticles";
	
	public static final String GENERAL_SOURCE_DONT_COUNT_CHANGED		= "generalSource/dontCountChanged";
	public static final String GENERAL_SOURCE_DONT_COUNT_CHANGED_GENERAL_SOURCE	= GENERAL_SOURCE_DONT_COUNT_CHANGED + "/changedSource";
}
