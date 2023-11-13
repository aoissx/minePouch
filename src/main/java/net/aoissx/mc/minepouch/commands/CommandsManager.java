package net.aoissx.mc.minepouch.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class CommandsManager implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (!(sender instanceof Player p)) {
            sender.sendMessage("This command can only be executed from the player.");
            return false;
        }

        if (args.length == 0) {
            p.sendMessage("/pouch <open|get|size|help>");
            return true;
        }

        String subCmd = args[0];
        if (subCmd.equalsIgnoreCase("open")) {
            Open openCmd = new Open();
            openCmd.Open(p);
        } else if (subCmd.equalsIgnoreCase("get")) {
            Get getCmd = new Get();
            getCmd.Get(p);
        } else if (subCmd.equalsIgnoreCase("size")) {
            Size sizeCmd = new Size();
            sizeCmd.Size(p);
        } else {
            p.sendMessage("Error: " + subCmd + " is an invalid command.");
            Help helpCmd = new Help();
            helpCmd.Help(p);
            return false;
        }
        return true;
    }
}
