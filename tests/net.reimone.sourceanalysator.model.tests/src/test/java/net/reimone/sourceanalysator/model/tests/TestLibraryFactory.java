package net.reimone.sourceanalysator.model.tests;

import net.reimone.sourceanalysator.Library;
import net.reimone.sourceanalysator.SourceanalysatorFactory;
import net.reimone.sourceanalysator.core.ILibraryFactory;

public class TestLibraryFactory implements ILibraryFactory {

	private Library library;

	public void setLibrary(Library library) {
		this.library = library;
	}
	
	@Override
	public Library createLibrary() {
		if (library == null) {
			library = SourceanalysatorFactory.eINSTANCE.createLibrary();
		}
		return library;
	}

}
