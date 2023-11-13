package net.aoissx.mc.minepouch.database;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import net.aoissx.mc.minepouch.MinePouch;
import org.bukkit.configuration.file.FileConfiguration;

import java.sql.Connection;
import java.sql.SQLException;

public class DatabaseManager {

    private static HikariConfig h_config;
    private static String prefix;
    private HikariDataSource dataSource;

    public DatabaseManager() {
        if (h_config == null) {
            FileConfiguration config = MinePouch.getInstance().getConfig();
            String host = config.getString("db.host", "example.com");
            String port = config.getString("db.port", "5432");
            String name = config.getString("db.name", "minecraft");
            String db_url = "jdbc:postgresql://" + host + ":" + port + "/" + name;
            String db_user = config.getString("db.user", "postgresql");
            String db_passwd = config.getString("db.passwd", "postgresql");
            prefix = config.getString("db.prefix", "mp-");

            h_config.setJdbcUrl(db_url);
            h_config.setUsername(db_user);
            h_config.setPassword(db_passwd);

            h_config.setMaximumPoolSize(10);
            h_config.setMinimumIdle(5);
            h_config.setIdleTimeout(30000);
        }

        this.dataSource = new HikariDataSource(h_config);
    }

    public String getPrefix() {
        return prefix;
    }

    synchronized Connection getConnection() throws SQLException {
        Connection con = null;
        try {
            con = this.dataSource.getConnection();
        } catch (SQLException e) {
            throw e;
        }

        return con;
    }

}
