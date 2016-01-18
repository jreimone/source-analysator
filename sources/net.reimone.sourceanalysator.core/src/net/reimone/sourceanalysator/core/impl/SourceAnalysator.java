package net.reimone.sourceanalysator.core.impl;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.google.common.collect.Lists;

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
		return getGeneralSourcesOfArticles(Lists.newArrayList(article));
	}

	@Override
	public Map<GeneralSource, List<Source>> getGeneralSourcesOfArticles(List<Article> articles) {
		if (articles == null) {
			return null;
		}
		
		if (library == null) {
			return null;
		}

		Map<GeneralSource, List<Source>> result = new LinkedHashMap<>();
		// must be a list instead of a set since sources can be referenced more than once from different articles
		List<Source> sources = new LinkedList<>();
		for (Article article : articles) {
			List<Source> articleSources = article.getSources();
			sources.addAll(articleSources);
		}
		for (Source source : sources) {
			GeneralSource generalSource = source.getGeneralSource();
			if (generalSource == null) {
				continue;
			}
			
			List<Source> generalSourceSources = result.getOrDefault(generalSource, new LinkedList<>());
			result.putIfAbsent(generalSource, generalSourceSources);
			// no containment check because same source can be referenced several times from different articles
			generalSourceSources.add(source);
		}
		return result;
	}

}
