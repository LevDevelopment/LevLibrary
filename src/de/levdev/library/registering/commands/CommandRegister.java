package de.levdev.library.registering.commands;

import de.levdev.library.Library;
import de.levdev.library.exceptions.LibraryAlreadyInitializedException;
import de.levdev.library.registering.Register;
import de.levdev.library.registering.handlers.DynCommand;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandMap;

import java.lang.reflect.Field;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by Levin on 16.09.2016.
 */
public abstract class CommandRegister {

    private static Library library = Library.getLibrary();

    private static String VERSION;
    private static CommandMap commandMap = null;

    static {
        String path = Bukkit.getServer().getClass().getPackage().getName();
        VERSION = path.substring(path.lastIndexOf(".") + 1, path.length());
    }

    public static Library getLibrary() {
        return library;
    }

    public static void setLibrary(Library library) throws LibraryAlreadyInitializedException{
        if(getLibrary() != null) {
            CommandRegister.library = library;
        } else {
            throw new LibraryAlreadyInitializedException();
        }
    }

    public void registerCommand(String name, String description, CommandExecutor commandExecutor, String... aliases) {
        try {
            DynCommand dynCmd = new DynCommand(name, description, commandExecutor, aliases);
            if(commandMap == null){
                Class<?> craftServerClass = Class.forName("org.bukkit.craftbukkit." + VERSION + ".CraftServer");
                Field field = craftServerClass.getDeclaredField("commandMap");
                field.setAccessible(true);
                commandMap = (CommandMap) field.get(Bukkit.getServer());
            }
            commandMap.register(library.getName(), dynCmd);
        }catch(Exception ex) {
            Logger.getLogger(Register.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
