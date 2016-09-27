package net.reimone.sourceanalysator.rcp;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;

import org.eclipse.core.runtime.ILog;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.osgi.framework.Bundle;

import com.google.common.collect.Maps;

import net.reimone.sourceanalysator.Library;
import net.reimone.sourceanalysator.SourceanalysatorFactory;
import net.reimone.sourceanalysator.core.ILibraryFactory;

public class EclipseLibraryFactory implements ILibraryFactory {

	private static final Map<Object, Object> PROPERTIES = Maps.newHashMap();
	
	static {
		PROPERTIES.put(XMLResource.OPTION_ENCODING, StandardCharsets.UTF_8.name());
	}
	
	private IPath libraryPath;
	private ILog log;
	private Bundle bundle;
	
	public EclipseLibraryFactory(IPath libraryPath, ILog log, Bundle bundle) {
		this.libraryPath = libraryPath;
		this.log = log;
		this.bundle = bundle;
	}

	@Override
	public Library createLibrary() {
		ResourceSet rs = new ResourceSetImpl();
		String libraryPathString = libraryPath.toString();
		URI uri = URI.createFileURI(libraryPathString);
		Library library = null;
		try {
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
					resource.save(getProperties());
				} else if (!(model instanceof Library)) {
					library = SourceanalysatorFactory.eINSTANCE.createLibrary();
					contents.set(0, library);
					resource.save(getProperties());
				} else {
					library = (Library) model;
				}
			}
		} catch (IOException e) {
			log.log(new Status(IStatus.ERROR, bundle.getSymbolicName(), "Couldn't load library file", e));
		}
		return library;
	}
	
	protected static Map<Object, Object> getProperties() {
		return PROPERTIES;
	}

}
