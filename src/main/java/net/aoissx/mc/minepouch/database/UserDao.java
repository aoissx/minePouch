package net.aoissx.mc.minepouch.database;

import net.aoissx.mc.minepouch.model.User;
import org.bukkit.entity.Player;

public class UserDao extends DatabaseManager{
    private User user;
    private Player p;

    public UserDao(Player player) {
        this.p = player;
        this.user = new User();
    }

    public User getUser() {

        return user;
    }

}
