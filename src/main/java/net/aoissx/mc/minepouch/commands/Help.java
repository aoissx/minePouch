package net.aoissx.mc.minepouch.commands;

import org.bukkit.entity.Player;

public class Help {
    public void Help(Player player) {
        String msg = "=======================\n" +
                "  Pouch Plugin - Usage\n" +
                "=======================\n" +
                "\n" +
                "The Pouch plugin adds a secondary inventory that players can access at any time. Players can open their pouch and retrieve pouch-specific items.\n" +
                "\n" +
                "1. /pouch open\n" +
                "   - Description: Opens your own pouch.\n" +
                "   - Usage Example: /pouch open\n" +
                "   - Permission: Players only\n" +
                "\n" +
                "2. /pouch get\n" +
                "   - Description: Retrieves the pouch item.\n" +
                "   - Usage Example: /pouch get\n" +
                "   - Permission: Players only\n" +
                "\n" +
                "3. /pouch size <user_name> <size>\n" +
                "   - Description: Changes the size of the pouch. The size can be specified from 1 to 6.\n" +
                "   - Usage Example: /pouch size PlayerName 3\n" +
                "   - Permission: Only OP (Operator)\n" +
                "\n" +
                "The Pouch plugin allows players to manage their pouch and pouch-specific items efficiently.\n" +
                "\n";

        player.sendMessage(msg);
    }

}
