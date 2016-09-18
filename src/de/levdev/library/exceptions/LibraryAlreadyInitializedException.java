package de.levdev.library.exceptions;

import de.levdev.library.Library;

/**
 * Created by Levin on 16.09.2016.
 */
public class LibraryAlreadyInitializedException extends Exception {

    public LibraryAlreadyInitializedException(){
        super(Library.getLibrary().getPrefix() + "Die Library wurde bereits initialisiert!");
    }

}
