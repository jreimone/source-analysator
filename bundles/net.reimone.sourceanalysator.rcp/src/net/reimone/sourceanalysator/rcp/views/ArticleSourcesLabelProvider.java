package net.reimone.sourceanalysator.rcp.views;

import java.util.function.Function;

import org.eclipse.core.databinding.observable.map.IObservableMap;
import org.eclipse.emf.ecore.provider.EcoreItemProviderAdapterFactory;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.eclipse.emf.edit.provider.ReflectiveItemProviderAdapterFactory;
import org.eclipse.emf.edit.provider.resource.ResourceItemProviderAdapterFactory;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider;
import org.eclipse.jface.databinding.viewers.ObservableMapLabelProvider;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;

import net.reimone.sourceanalysator.GeneralSource;
import net.reimone.sourceanalysator.Hyperlink;
import net.reimone.sourceanalysator.Source;

public class ArticleSourcesLabelProvider extends ObservableMapLabelProvider {

	private TableColumn tableColumnGeneralSource;
	private Table table;

	private AdapterFactoryLabelProvider labelProvider;

	@SuppressWarnings("rawtypes")
	public ArticleSourcesLabelProvider(IObservableMap[] attributeMaps, TableColumn tableColumnGeneralSource) {
		super(attributeMaps);
		this.tableColumnGeneralSource = tableColumnGeneralSource;
		table = tableColumnGeneralSource.getParent();

		ComposedAdapterFactory adapterFactory = new ComposedAdapterFactory(
				org.eclipse.emf.edit.provider.ComposedAdapterFactory.Descriptor.Registry.INSTANCE);
		adapterFactory.addAdapterFactory(new ResourceItemProviderAdapterFactory());
		adapterFactory.addAdapterFactory(new EcoreItemProviderAdapterFactory());
		adapterFactory.addAdapterFactory(new ReflectiveItemProviderAdapterFactory());
		labelProvider = new AdapterFactoryLabelProvider(adapterFactory);

	}

	@Override
	public Image getColumnImage(Object element, int columnIndex) {
		return getTextOrImage(element, columnIndex, (hyperlink) -> {
			return labelProvider.getImage(hyperlink);
		}, (generalSource) -> {
			return labelProvider.getImage(generalSource);
		});
	}

	@Override
	public String getColumnText(Object element, int columnIndex) {
		return getTextOrImage(element, columnIndex, (hyperlink) -> {
			String text = labelProvider.getText(hyperlink);
			return text;
//			return super.getColumnText(element, columnIndex);
		}, (generalSource) -> {
			String text = labelProvider.getText(generalSource);
			return text;
		});
	}

	private <R> R getTextOrImage(Object element, int columnIndex, Function<Hyperlink, R> hyperlinkFunction,
			Function<GeneralSource, R> function) {
		if (!(element instanceof Source)) {
			return null;
		}

		Source source = (Source) element;
		Hyperlink hyperlink = source.getHyperlink();
		GeneralSource generalSource = source.getGeneralSource();
		
		TableColumn currentColumn = table.getColumn(columnIndex);
		if (!tableColumnGeneralSource.equals(currentColumn)) {
			return hyperlinkFunction.apply(hyperlink);
		}
		return function.apply(generalSource);
	}
	
}
