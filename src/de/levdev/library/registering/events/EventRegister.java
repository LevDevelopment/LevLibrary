package de.levdev.library.registering.events;

import com.google.common.reflect.ClassPath;
import de.levdev.library.Library;
import de.levdev.library.exceptions.LibraryAlreadyInitializedException;
import de.levdev.library.registering.Register;
import de.levdev.library.registering.commands.CommandRegister;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.event.Listener;

import java.io.IOException;
import java.lang.reflect.Constructor;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by Levin on 16.09.2016.
 */
public abstract class EventRegister {

    private static Library library = Library.getLibrary();

    public static Library getLibrary() {
        return library;
    }

    public static void setLibrary(Library library) throws LibraryAlreadyInitializedException{
        if(getLibrary() != null) {
            EventRegister.library = library;
        } else {
            throw new LibraryAlreadyInitializedException();
        }
    }

    public void registerEvents(Class<?>... listeners) {
        for (Class<?> clazz : listeners) {
            boolean isConstructor = false;
            try {
                clazz.getConstructor(new Class[] { library.getClass() });
                isConstructor = true;
            }catch(NoSuchMethodException ex1) {
                isConstructor = false;
            }

            try {
                Listener listener = null;
                if(isConstructor) {
                    Constructor<?> cww = clazz.getConstructor(new Class[] { library.getClass() });
                    listener = (Listener) cww.newInstance(new Object[] { library });
                }else{
                    listener = (Listener) clazz.newInstance();
                }
                Bukkit.getPluginManager().registerEvents(listener, library);
            }catch(Exception ex) {
                Logger.getLogger(Register.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void registerEventsPackage(String packageFile){
        try {
            for(ClassPath.ClassInfo classInfo : ClassPath.from(getLibrary().getClass().getClassLoader()).getTopLevelClasses(packageFile)) {
                Class<?> clazz = Class.forName(classInfo.getName());

                if(Listener.class.isAssignableFrom(clazz)) {
                    registerEvents(clazz);
                }
            }
        } catch (IOException | ClassNotFoundException e) {
            Bukkit.getConsoleSender().sendMessage(getLibrary().getPrefix() + ChatColor.DARK_RED +
                    "Die Listener konnten nicht geladen werden!");
            e.printStackTrace();
        }
    }

}
