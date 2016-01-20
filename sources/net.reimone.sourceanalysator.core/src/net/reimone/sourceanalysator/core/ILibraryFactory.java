package net.reimone.sourceanalysator.core;

import net.reimone.sourceanalysator.Library;

/**
 * A factory interface for creating a {@link Library}.
 * 
 */
public interface ILibraryFactory {

	/**
	 * Creates a library.
	 */
	public Library createLibrary();
}
