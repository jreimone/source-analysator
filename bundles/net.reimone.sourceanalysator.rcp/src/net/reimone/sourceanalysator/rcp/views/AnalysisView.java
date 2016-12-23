package net.reimone.sourceanalysator.rcp.views;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.inject.Inject;

import org.eclipse.e4.core.di.annotations.Optional;
import org.eclipse.e4.ui.di.Focus;
import org.eclipse.e4.ui.di.UIEventTopic;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

import com.google.common.collect.Lists;

import net.reimone.sourceanalysator.Article;
import net.reimone.sourceanalysator.GeneralSource;
import net.reimone.sourceanalysator.Source;
import net.reimone.sourceanalysator.core.ISourceAnalysator;
import net.reimone.sourceanalysator.rcp.Events;
import net.reimone.sourceanalysator.rcp.Util;

import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

public class AnalysisView {

	@Inject
	private ISourceAnalysator sourceAnalysator;
	
	private Label lblCheckedArticles;
	private Composite statisticsComposite;
	private List<Label> currentStatisticsLabels = Lists.newArrayList();

	private List<Article> checkedArticles;
	
	public AnalysisView() {
	}

	/**
	 * Create contents of the view part.
	 */
	@PostConstruct
	public void createControls(Composite parent) {
		parent.setLayout(new GridLayout(1, false));
		
		statisticsComposite = new Composite(parent, SWT.NONE);
		statisticsComposite.setLayout(new GridLayout(2, false));
		statisticsComposite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		
		Label lblStatic = new Label(statisticsComposite, SWT.NONE);
		lblStatic.setLayoutData(new GridData(SWT.LEFT, SWT.TOP, false, false, 1, 1));
		lblStatic.setText("Folgende Artikel sind ausgewählt:");
		
		lblCheckedArticles = new Label(statisticsComposite, SWT.NONE);
		lblCheckedArticles.setLayoutData(new GridData(SWT.LEFT, SWT.TOP, false, false, 1, 1));
		lblCheckedArticles.setBounds(0, 0, 55, 15);
		new Label(statisticsComposite, SWT.NONE);
		new Label(statisticsComposite, SWT.NONE);
		
		Label lblAnzahlDerQuellen = new Label(statisticsComposite, SWT.NONE);
		lblAnzahlDerQuellen.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false, false, 2, 1));
		lblAnzahlDerQuellen.setText("Anzahl der Quellen in den ausgewählten Artikeln:");
		
		Composite buttonComposite = new Composite(parent, SWT.NONE);
		buttonComposite.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, true, false, 1, 1));
		
		Button btnWordExport = new Button(buttonComposite, SWT.NONE);
		btnWordExport.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				Util.INSTANCE.writeAndOpenStatistics(sourceAnalysator, checkedArticles);
			}
		});
		btnWordExport.setBounds(0, 0, 75, 25);
		btnWordExport.setText("Word-Export");
	}

	@Optional
	@Inject
	private void subscribeArticleCheckedStateChanged(
			@UIEventTopic(Events.ARTICLE_CHECKED_STATE_CHANGED) Map<String, List<Article>> data) {
		checkedArticles = data.get(Events.ARTICLE_CHECKED_STATE_CHANGED_ARTICLES);
		String checkedArticlesString = checkedArticles.stream().map(article -> article.getTitle()).collect(Collectors.joining(", "));
		lblCheckedArticles.setText(checkedArticlesString);
		
		Map<GeneralSource, List<Source>> generalSourcesOfArticles = sourceAnalysator.getGeneralSourcesOfArticles(checkedArticles);
		generateStatistics(statisticsComposite, generalSourcesOfArticles);
		
		statisticsComposite.layout();
	}
	
	private void generateStatistics(Composite statisticsComposite, Map<GeneralSource, List<Source>> generalSourcesOfArticles) {
		clearCurrentStatistics();
		
		for (Entry<GeneralSource, List<Source>> entry : generalSourcesOfArticles.entrySet()) {
			GeneralSource generalSource = entry.getKey();
			String name = generalSource.getName();
			List<Source> sources = entry.getValue();
			int sourceCount = sources.size();
			
			Label lblGeneralSource = new Label(statisticsComposite, SWT.NONE);
			lblGeneralSource.setText(name);
			
			Label lblCount = new Label(statisticsComposite, SWT.NONE);
			lblCount.setText(Integer.toString(sourceCount) + "x");
			
			currentStatisticsLabels.add(lblGeneralSource);
			currentStatisticsLabels.add(lblCount);
		}
	}

	private void clearCurrentStatistics() {
		for (Label label : currentStatisticsLabels) {
			label.dispose();
		}
		currentStatisticsLabels.clear();
	}

	@PreDestroy
	public void dispose() {
	}

	@Focus
	public void setFocus() {
		// TODO	Set the focus to control
	}
}
