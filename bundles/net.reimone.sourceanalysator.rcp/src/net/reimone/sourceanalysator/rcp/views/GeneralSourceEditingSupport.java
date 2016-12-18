package net.reimone.sourceanalysator.rcp.views;

import java.util.List;
import java.util.Objects;

import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.ComboBoxCellEditor;
import org.eclipse.jface.viewers.EditingSupport;
import org.eclipse.jface.viewers.TableViewer;

import com.google.common.collect.Lists;

import net.reimone.sourceanalysator.GeneralSource;
import net.reimone.sourceanalysator.Source;
import net.reimone.sourceanalysator.core.ISourceAnalysator;

public class GeneralSourceEditingSupport extends EditingSupport {

	private TableViewer viewer;
	private ISourceAnalysator sourceAnalysator;
	private ComboBoxCellEditor comboBoxCellEditor;

	public GeneralSourceEditingSupport(TableViewer viewer, ISourceAnalysator sourceAnalysator) {
		super(viewer);
		this.viewer = viewer;
		this.sourceAnalysator = sourceAnalysator;
	}

	@Override
	protected CellEditor getCellEditor(Object element) {
		GeneralSource generalSource = getGeneralSourceFromSource(element);
		String[] aliases = getAliases(generalSource);
		comboBoxCellEditor = new ComboBoxCellEditor(viewer.getTable(), aliases);
		return comboBoxCellEditor;
	}

	private String[] getAliases(GeneralSource generalSource) {
		List<String> aliases = getAliasesList(generalSource);
		return aliases.toArray(new String[0]);
	}

	private List<String> getAliasesList(GeneralSource generalSource) {
		List<String> aliases = Lists.newArrayList();
		if (generalSource != null) {
			aliases.add(generalSource.getName());
			aliases.addAll(generalSource.getAliases());
		}
		return aliases;
	}

	private Source getSource(Object element) {
		Source source = null;
		if (element instanceof Source) {
			source = (Source) element;
		}
		
		return Objects.requireNonNull(source);
	}
	
	private GeneralSource getGeneralSourceFromSource(Object element) {
		Source source = getSource(element);
		GeneralSource generalSource = source.getGeneralSource();
		return Objects.requireNonNull(generalSource);
	}

	@Override
	protected boolean canEdit(Object element) {
		return true;
	}

	@Override
	protected Object getValue(Object element) {
		GeneralSource generalSource = getGeneralSourceFromSource(element);
		List<String> aliases = getAliasesList(generalSource);
		return aliases.indexOf(generalSource.getName());
	}

	@Override
	protected void setValue(Object element, Object value) {
		Source source = getSource(element);
		GeneralSource generalSource = getGeneralSourceFromSource(element);
		List<String> aliasesList = getAliasesList(generalSource);
		int index = (Integer) value;
		String generalSourceName = aliasesList.get(index);
		sourceAnalysator.setGeneralSourceOfSource(source, generalSourceName);
		String[] aliases = getAliases(generalSource);
		comboBoxCellEditor.setItems(aliases);
	}

}
