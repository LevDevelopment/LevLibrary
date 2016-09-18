package de.levdev.library.registering.handlers;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import java.util.Arrays;

/**
 * Created by Levin on 18.09.2016.
 */

public class DynCommand extends Command {

    private CommandExecutor exec;

    public DynCommand(String name, String description, CommandExecutor exec, String... aliases) {
        super(name);
        this.exec = exec;
        super.setDescription(description);
        super.setAliases(Arrays.asList(aliases));
    }

    @Override
    public boolean execute(CommandSender cs, String label, String[] args) {
        exec.onCommand(cs, this, label, args);
        return false;
    }
}

