package de.levdev.library.commands;

import de.levdev.library.Library;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

/**
 * Created by Levin on 18.09.2016.
 */
public class Command_Lib implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String label, String[] args) {
        if(command.getName().equalsIgnoreCase("lib")){
            if(args.length == 1){
                if(args[0].equalsIgnoreCase("info")){
                    commandSender.sendMessage(Library.getLibrary().getPrefix() + ChatColor.GREEN + "Aktuelle Version: " + ChatColor.RED + Library.getLibrary().getVersion());
                }
            }
        }
        return true;
    }
}
