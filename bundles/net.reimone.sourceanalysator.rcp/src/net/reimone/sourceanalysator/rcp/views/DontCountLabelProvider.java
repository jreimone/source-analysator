package net.reimone.sourceanalysator.rcp.views;

import org.eclipse.core.databinding.observable.map.IObservableMap;
import org.eclipse.jface.databinding.viewers.ObservableMapLabelProvider;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;

import net.reimone.sourceanalysator.GeneralSource;

public class DontCountLabelProvider extends ObservableMapLabelProvider {
	
	private TableColumn tableColumnCount;
	private Table table;

	public DontCountLabelProvider(@SuppressWarnings("rawtypes") IObservableMap[] observeMaps, TableColumn tableColumnCount) {
		super(observeMaps);
		this.tableColumnCount = tableColumnCount;
		table = tableColumnCount.getParent();
	}

	@Override
	public String getColumnText(Object element, int columnIndex) {
		if (!table.getColumn(columnIndex).equals(tableColumnCount)) {
			return super.getColumnText(element, columnIndex);
		}
		
		if (element instanceof GeneralSource) {
			GeneralSource generalSource = (GeneralSource) element;
			boolean dontCount = generalSource.isDontCount();
			if (dontCount) {
				// display semantics is vice versa since the column is labeled as "Count?" instead of "don't count?"
				return "nein";
			}
			return "ja";
		}
		return super.getColumnText(element, columnIndex);
	}
	
}