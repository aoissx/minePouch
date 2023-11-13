package net.aoissx.mc.minepouch.database;

import net.aoissx.mc.minepouch.model.Item;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.io.BukkitObjectInputStream;
import org.bukkit.util.io.BukkitObjectOutputStream;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PouchDao extends DatabaseManager {
    private Player player;

    public PouchDao(Player player) {
        this.player = player;
    }

    public void saveItem(int index, ItemStack item) {
        String uuid = player.getUniqueId().toString();

        try (Connection con = getConnection()) {
            String sql = "INSERT INTO " + getPrefix() + "pouch (uuid, item_index, item) VALUES (?, ?, ?)";
            try (PreparedStatement ps = con.prepareStatement(sql)) {
                ps.setString(1, uuid);
                ps.setInt(2, index);
                byte[] itemData = serializeItemStack(item);
                ps.setBytes(3, itemData);

                ps.executeUpdate();
            }
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
    }

    public List<Item> getItems() {
        String uuid = player.getUniqueId().toString();
        List<Item> items = new ArrayList<>();

        try (Connection con = getConnection()) {
            String selectQuery = "SELECT * FROM " + getPrefix() + "pouch WHERE uuid = ?";
            try (PreparedStatement ps = con.prepareStatement(selectQuery)) {
                ps.setString(1, uuid);
                try (ResultSet rs = ps.executeQuery()) {
                    while (rs.next()) {
                        int id = rs.getInt("id");
                        int itemIndex = rs.getInt("item_index");
                        byte[] itemData = rs.getBytes("item");
                        ItemStack item = deserializeItemStack(itemData);

                        Item p = new Item();
                        p.setId(id);
                        p.setUuid(uuid);
                        p.setItemIndex(itemIndex);
                        p.setItem(item);

                        items.add(p);
                    }
                }
            } catch (SQLException | IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return items;
    }

    private byte[] serializeItemStack(ItemStack itemStack) throws IOException {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        BukkitObjectOutputStream dataOutput = new BukkitObjectOutputStream(outputStream);
        dataOutput.writeObject(itemStack);
        dataOutput.close();
        return outputStream.toByteArray();
    }

    private ItemStack deserializeItemStack(byte[] itemData) throws IOException, ClassNotFoundException {
        ByteArrayInputStream inputStream = new ByteArrayInputStream(itemData);
        BukkitObjectInputStream dataInput = new BukkitObjectInputStream(inputStream);
        ItemStack itemStack = (ItemStack) dataInput.readObject();
        dataInput.close();
        return itemStack;
    }
}
