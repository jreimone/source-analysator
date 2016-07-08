package net.reimone.sourceanalysator.product;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.core.runtime.ILog;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.Status;
import org.eclipse.e4.core.contexts.IEclipseContext;
import org.eclipse.e4.ui.workbench.lifecycle.PostContextCreate;
import org.eclipse.e4.ui.workbench.lifecycle.PreSave;
import org.eclipse.e4.ui.workbench.lifecycle.ProcessAdditions;
import org.eclipse.e4.ui.workbench.lifecycle.ProcessRemovals;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;

import net.reimone.sourceanalysator.Library;
import net.reimone.sourceanalysator.SourceanalysatorFactory;

/**
 * This is a stub implementation containing e4 LifeCycle annotated methods.<br />
 * There is a corresponding entry in <em>plugin.xml</em> (under the
 * <em>org.eclipse.core.runtime.products' extension point</em>) that references this class.
 **/
@SuppressWarnings("restriction")
public class E4LifeCycle {

	private static final String LIBRARY_FILE = "library.sourceanalysator";
	private ILog log;
	private Bundle thisBundle;

	@PostContextCreate
	void postContextCreate(IEclipseContext workbenchContext) {
	}

	@PreSave
	void preSave(IEclipseContext workbenchContext) {
		Library library = workbenchContext.getActive(Library.class);
		Resource resource = library.eResource();
		try {
			resource.save(getProperties());
		} catch (IOException e) {
			log.log(new Status(IStatus.ERROR, thisBundle.getSymbolicName(), "Couldn't create library file", e));
		}
	}

	@ProcessAdditions
	void processAdditions(IEclipseContext workbenchContext) {
		thisBundle = FrameworkUtil.getBundle(getClass());
		log = Platform.getLog(thisBundle);
		IPath bundleDataArea = Platform.getStateLocation(thisBundle);
		IPath libraryPath = bundleDataArea.append(LIBRARY_FILE);
		ResourceSet rs = new ResourceSetImpl();
		String libraryPathString = libraryPath.toString();
		URI uri = URI.createFileURI(libraryPathString);
		Library library = null;
		try {
			// TODO use ILibraryFactory
			File libraryFile = new File(libraryPathString);
			if (!libraryFile.exists()) {
				Resource resource = rs.createResource(uri);
				library = SourceanalysatorFactory.eINSTANCE.createLibrary();
				resource.getContents().add(library);
				resource.save(getProperties());
			} else {
				Resource resource = rs.getResource(uri, true);
				List<EObject> contents = resource.getContents();
				EObject model = contents.get(0);
				if (model == null) {
					library = SourceanalysatorFactory.eINSTANCE.createLibrary();
					contents.add(library);
					resource.save(Collections.emptyMap());
				} else if (!(model instanceof Library)) {
					library = SourceanalysatorFactory.eINSTANCE.createLibrary();
					contents.set(0, library);
					resource.save(Collections.emptyMap());
				} else {
					library = (Library) model;
				}
			}
			workbenchContext.set(Library.class, library);
		} catch (IOException e) {
			log.log(new Status(IStatus.ERROR, thisBundle.getSymbolicName(), "Couldn't create library file", e));
		}
	}

	private Map<Object, Object> getProperties() {
		Map<Object, Object> properties = new HashMap<>();
		properties.put(XMLResource.OPTION_ENCODING, StandardCharsets.UTF_8.name());
		return properties;
	}

	@ProcessRemovals
	void processRemovals(IEclipseContext workbenchContext) {
	}
}
