package de.levdev.library;

import de.levdev.library.commands.Command_Lib;
import de.levdev.library.exceptions.LibraryAlreadyInitializedException;
import de.levdev.library.registering.Register;
import de.levdev.library.registering.commands.CommandRegister;
import de.levdev.library.registering.events.EventRegister;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * Created by Levin on 16.09.2016.
 */
public class Library extends JavaPlugin{

    private static Library library;

    private Register<Library> register;

    /* ===[ Strings ]=== */
    private final String PREFIX = ChatColor.DARK_RED + "LIBRARY " + ChatColor.GRAY + "| " + ChatColor.RESET;


    @Override
    public void onEnable() {
        new Thread(() -> {
            //try {
                //CommandRegister.setLibrary(library);
                System.out.println(getPrefix() + ChatColor.GREEN + "Der CommandRegister wurde aktiviert!");
                //EventRegister.setLibrary(library);
                System.out.println(getPrefix() + ChatColor.GREEN + "Der EventRegister wurde aktiviert!");
            //} catch (LibraryAlreadyInitializedException e) {
                //e.printStackTrace();
            //}

            register.getCommandRegister().registerCommand("lib", "", new Command_Lib());
            //System.out.println("REGISTERED");
        }).start();
    }

    @Override
    public void onLoad() {
        library = this;

        register = new Register<>(this);
    }

    public static Library getLibrary() {
        return library;
    }

    public String getPrefix() {
        return PREFIX;
    }

    public String getVersion(){
        return getLibrary().getDescription().getVersion();
    }
}
