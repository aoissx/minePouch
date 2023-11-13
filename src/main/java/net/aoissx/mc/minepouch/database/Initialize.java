package net.aoissx.mc.minepouch.database;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class Initialize extends DatabaseManager {

    public Initialize() {
        super();
    }

    public boolean Init() {
        boolean ans = true;
        String user = getPrefix() + "users";
        String pouch = getPrefix() + "pouch";
        String user_table = "CREATE TABLE IF NOT EXISTS " + user + " ( " +
                "id VARCHAR(255) PRIMARY KEY, " +
                "size INT " +
                ");";
        String pouch_table = "CREATE TABLE IF NOT EXISTS " + pouch + " ( " +
                "    id SERIAL PRIMARY KEY, " +
                "    uuid VARCHAR(255), "+
                "    item_index INT, " +
                "    item BYTEA " +
                ");";

        try (Connection conn = getConnection();
             Statement st = conn.createStatement()) {

            st.execute(user_table);
            st.execute(pouch_table);

        } catch (SQLException e) {
            ans = false;
        }

        return ans;
    }

}
