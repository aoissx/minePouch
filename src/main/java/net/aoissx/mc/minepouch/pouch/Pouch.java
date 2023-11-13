package net.aoissx.mc.minepouch.pouch;

import net.aoissx.mc.minepouch.database.PouchDao;
import net.aoissx.mc.minepouch.database.UserDao;
import net.aoissx.mc.minepouch.model.Item;
import net.aoissx.mc.minepouch.model.User;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import java.util.List;

public class Pouch {
    public Pouch(){}

    public Inventory getPouch(Player p){
        UserDao dao = new UserDao(p);

        User user = dao.getUser();
        int size = user.getSize();

        // インベントリを作成
        Inventory pouch = createPouch(size);

        // 中身を取得
        PouchDao pdao = new PouchDao(p);
        List<Item> items = pdao.getItems();

        // itemをinvに配置
        for(Item item: items){
            pouch.setItem(item.getItemIndex(),item.getItem());
        }
        return pouch;
    }

    private Inventory createPouch(int size){
        Inventory pouch = Bukkit.createInventory(null,size,"Pouch");
        return pouch;
    }
}
