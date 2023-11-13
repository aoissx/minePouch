package net.aoissx.mc.minepouch;

import net.aoissx.mc.minepouch.commands.CommandsManager;
import net.aoissx.mc.minepouch.database.Initialize;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public final class MinePouch extends JavaPlugin {

    public static Plugin plg;
    private static FileConfiguration config;

    private String cmd = "pouch";

    /**
     * コンストラクタ
     */
    public MinePouch() {
        super();
        plg = this;
    }

    /**
     * インスタンス取得
     */

    public static Plugin getInstance() {
        return plg;
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    @Override
    public void onEnable() {
        // Plugin startup logic

        // コンフィグ
        saveDefaultConfig();
        config = getConfig();

        // データベース
        Initialize initialize = new Initialize();
        if (!initialize.Init()) {
            getServer().getPluginManager().disablePlugin(this);
            return;
        }

        // cmds
        getCommand(cmd).setExecutor(new CommandsManager());

        // events

    }

    public FileConfiguration getConfig() {
        return config;
    }

    public void reloadConfig() {
        config = super.getConfig();
    }
}
