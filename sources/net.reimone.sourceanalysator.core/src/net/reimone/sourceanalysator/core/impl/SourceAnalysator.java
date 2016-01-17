package net.reimone.sourceanalysator.core.impl;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import net.reimone.sourceanalysator.Article;
import net.reimone.sourceanalysator.GeneralSource;
import net.reimone.sourceanalysator.Library;
import net.reimone.sourceanalysator.Source;
import net.reimone.sourceanalysator.core.ISourceAnalysator;

public class SourceAnalysator implements ISourceAnalysator {

	private Library library;

	public SourceAnalysator(Library library) {
		this.library = library;
	}

	@Override
	public Map<GeneralSource, List<Source>> getGeneralSourcesOfArticle(Article article) {
		if (library == null) {
			return null;
		}
		
		if (article == null) {
			return null;
		}
		
		Map<GeneralSource, List<Source>> result = new LinkedHashMap<>();
		return result;
	}

}
