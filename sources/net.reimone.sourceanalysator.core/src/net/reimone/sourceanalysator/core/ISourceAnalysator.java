package net.reimone.sourceanalysator.core;

import java.util.List;
import java.util.Map;

import net.reimone.sourceanalysator.Article;
import net.reimone.sourceanalysator.GeneralSource;
import net.reimone.sourceanalysator.Source;

public interface ISourceAnalysator {

	/**
	 * Returns a map which maps a general source to the sources referenced by the given article
	 */
	Map<GeneralSource, List<Source>> getGeneralSourcesOfArticle(Article article);

}
