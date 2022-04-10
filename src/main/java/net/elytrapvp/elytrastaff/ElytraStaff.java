package net.elytrapvp.elytrastaff;

import net.elytrapvp.elytrastaff.commands.AbstractCommand;
import org.bukkit.plugin.java.JavaPlugin;

public final class ElytraStaff extends JavaPlugin {
    private SettingsManager settingsManager;

    @Override
    public void onEnable() {
        // Plugin startup logic

        // Load plugin settings.
        settingsManager = new SettingsManager(this);

        // Register our commands with Spigot.
        AbstractCommand.registerCommands(this);
    }

    /**
     * Get the settings manager.
     * @return Settings Manager.
     */
    public SettingsManager getSettingsManager() {
        return settingsManager;
    }
}
