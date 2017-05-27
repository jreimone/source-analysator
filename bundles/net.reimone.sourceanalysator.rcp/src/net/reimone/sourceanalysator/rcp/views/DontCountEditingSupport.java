package net.reimone.sourceanalysator.rcp.views;

import java.util.HashMap;
import java.util.Objects;

import org.eclipse.e4.core.services.events.IEventBroker;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.CheckboxCellEditor;
import org.eclipse.jface.viewers.EditingSupport;
import org.eclipse.jface.viewers.TableViewer;

import com.google.common.collect.Maps;

import net.reimone.sourceanalysator.GeneralSource;
import net.reimone.sourceanalysator.rcp.Events;

public class DontCountEditingSupport extends EditingSupport {
	
	private IEventBroker eventBroker;
	
	private TableViewer viewer;

	public DontCountEditingSupport(TableViewer viewer, IEventBroker eventBroker) {
		super(viewer);
		this.viewer = viewer;
		this.eventBroker = eventBroker;
	}

	protected boolean canEdit(Object element) {
		return true;
	}

	protected CellEditor getCellEditor(Object element) {
		return new CheckboxCellEditor(viewer.getTable());
	}

	private GeneralSource getGeneralSource(Object element) {
		GeneralSource generalSource = null;
		if (element instanceof GeneralSource) {
			generalSource = (GeneralSource) element;
		}
		return Objects.requireNonNull(generalSource);
	}

	protected Object getValue(Object element) {
		GeneralSource generalSource = getGeneralSource(element);
		return generalSource.isDontCount();
	}

	protected void setValue(Object element, Object value) {
		GeneralSource generalSource = getGeneralSource(element);
		if (value instanceof Boolean) {
			Boolean dontCount = (Boolean) value;
			generalSource.setDontCount(dontCount);
			HashMap<String, Object> data = Maps.newHashMap();
			data.put(Events.GENERAL_SOURCE_DONT_COUNT_CHANGED_GENERAL_SOURCE, generalSource);
			eventBroker.post(Events.GENERAL_SOURCE_DONT_COUNT_CHANGED, data);
		}
		viewer.refresh();
	}
}