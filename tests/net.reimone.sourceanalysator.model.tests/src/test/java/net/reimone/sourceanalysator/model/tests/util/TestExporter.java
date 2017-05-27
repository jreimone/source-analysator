package net.reimone.sourceanalysator.model.tests.util;

import java.io.File;
import java.util.List;
import java.util.Map.Entry;

import net.reimone.sourceanalysator.Article;
import net.reimone.sourceanalysator.core.AbstractExporter;
import net.reimone.sourceanalysator.core.IExporter;

public class TestExporter extends AbstractExporter implements IExporter {

	@Override
	public File export(List<Article> articles, List<Entry<String, Integer>> statistics) {
		return null;
	}

}
