package net.elytrapvp.elytrastaff;

import net.elytrapvp.elytrastaff.commands.AbstractCommand;
import org.bukkit.plugin.java.JavaPlugin;

public final class ElytraStaff extends JavaPlugin {
    private MySQL mySQL;
    private SettingsManager settingsManager;

    @Override
    public void onEnable() {
        // Plugin startup logic

        // Load plugin settings.
        settingsManager = new SettingsManager(this);

        // Connect to MySQL.
        mySQL = new MySQL(this);

        // Register our commands with Spigot.
        AbstractCommand.registerCommands(this);
    }

    /**
     * Be able to connect to MySQL.
     * @return MySQL.
     */
    public MySQL getMySQL() {
        return mySQL;
    }

    /**
     * Get the Settings Manager, which gives us access to the plugin Configuration.
     * @return Settings Manager.
     */
    public SettingsManager getSettingsManager() {
        return settingsManager;
    }
}
