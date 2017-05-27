package net.reimone.sourceanalysator.core;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import net.reimone.sourceanalysator.GeneralSource;
import net.reimone.sourceanalysator.Source;

/**
 * This class provides a default implementation for the generation of statistics. 
 * THis is independent from the actual export to a file. 
 *
 */
public abstract class AbstractExporter implements IExporter {

	public AbstractExporter() {
		super();
	}

	@Override
	public List<Entry<String, Integer>> generateStatisticsForGeneralSourcesOfArticles(Map<GeneralSource, List<Source>> generalSourcesOfArticles) {
		List<Entry<String, Integer>> result = Lists.newArrayList();
		if (generalSourcesOfArticles == null) {
			return result;
		}
	
		Map<String, Integer> categoryCounts = Maps.newHashMap();
		for (Entry<GeneralSource, List<Source>> entry : generalSourcesOfArticles.entrySet()) {
			GeneralSource generalSource = entry.getKey();
			List<Source> sources = entry.getValue();
			int count = sources.size();
			
			String categoryName = generalSource.getName();
			Integer finalCount = categoryCounts.get(categoryName);
			if (finalCount == null) {
				finalCount = 0;
			}
			finalCount += count;
			categoryCounts.put(categoryName, finalCount);
		}
		
		result = categoryCounts.entrySet().stream().sorted(Map.Entry.comparingByValue(Collections.reverseOrder())).collect(Collectors.toList());
		return result;
	}

}