package net.reimone.sourceanalysator.rcp;

import java.io.IOException;

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
import org.eclipse.emf.ecore.resource.Resource;
import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;

import net.reimone.sourceanalysator.Library;
import net.reimone.sourceanalysator.core.ILibraryFactory;
import net.reimone.sourceanalysator.core.ISourceAnalysator;
import net.reimone.sourceanalysator.core.impl.SourceAnalysator;

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
	public void postContextCreate(IEclipseContext workbenchContext) {
	}

	@PreSave
	public void preSave(IEclipseContext workbenchContext) {
		ISourceAnalysator sourceAnalysator = workbenchContext.getActive(ISourceAnalysator.class);
		Library library = sourceAnalysator.getSingleLibrary();
		Resource resource = library.eResource();
		try {
			resource.save(EclipseLibraryFactory.getProperties());
		} catch (IOException e) {
			log.log(new Status(IStatus.ERROR, thisBundle.getSymbolicName(), "Couldn't save library file", e));
		}
	}

	@ProcessAdditions
	public void processAdditions(IEclipseContext workbenchContext) {
		// register source analysator
		thisBundle = FrameworkUtil.getBundle(getClass());
		log = Platform.getLog(thisBundle);
		IPath bundleDataArea = Platform.getStateLocation(thisBundle);
		IPath libraryPath = bundleDataArea.append(LIBRARY_FILE);
		ILibraryFactory libraryFactory = new EclipseLibraryFactory(libraryPath, log, thisBundle);
		ISourceAnalysator analysator = new SourceAnalysator();
		analysator.initialize(libraryFactory);
		workbenchContext.set(ISourceAnalysator.class, analysator);
	}

	@ProcessRemovals
	public void processRemovals(IEclipseContext workbenchContext) {
	}
}
