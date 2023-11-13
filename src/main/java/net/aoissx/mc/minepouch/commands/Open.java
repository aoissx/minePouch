package net.aoissx.mc.minepouch.commands;

import net.aoissx.mc.minepouch.database.PouchDao;
import net.aoissx.mc.minepouch.pouch.Pouch;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public class Open {
    public void Open(Player p) {

        Pouch pouch = new Pouch();

        Inventory inv = pouch.getPouch(p);

        // Playerにinvを開かせる
        p.openInventory(inv);
    }
}
