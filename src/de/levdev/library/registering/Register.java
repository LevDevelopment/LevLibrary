package de.levdev.library.registering;

import de.levdev.library.Library;
import de.levdev.library.exceptions.LibraryAlreadyInitializedException;
import de.levdev.library.registering.commands.CommandRegister;
import de.levdev.library.registering.events.EventRegister;
import org.bukkit.command.CommandExecutor;
import org.bukkit.plugin.Plugin;

/**
 * Created by Levin on 16.09.2016.
 */
public class Register<L extends Plugin>{

    private L library;

    public Register(L library) {
        this.library = library;
    }

    public CommandRegister getCommandRegister(){
        if(CommandRegister.getLibrary() == null){
            try {
                CommandRegister.setLibrary(getLibrary());
            } catch (LibraryAlreadyInitializedException e){}
        }

        return new CommandRegister() {
            @Override
            public void registerCommand(String name, String description, CommandExecutor commandExecutor, String... aliases) {
                super.registerCommand(name, description, commandExecutor, aliases);
            }
        };
    }

    public EventRegister getEventRegister(){
        if(EventRegister.getLibrary() == null){
            try {
                EventRegister.setLibrary(getLibrary());
            } catch (LibraryAlreadyInitializedException e){}
        }

        return new EventRegister() {
            @Override
            public void registerEvents(Class<?>... listeners) {
                super.registerEvents(listeners);
            }
        };
    }

    private Library getLibrary(){
        return (Library) library;
    }

}
