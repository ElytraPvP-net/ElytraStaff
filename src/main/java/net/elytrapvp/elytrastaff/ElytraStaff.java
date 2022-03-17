package net.elytrapvp.elytrastaff;

import net.elytrapvp.elytrastaff.commands.AbstractCommand;
import org.bukkit.plugin.java.JavaPlugin;

public final class ElytraStaff extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic

        // Register our commands with Spigot.
        AbstractCommand.registerCommands(this);
    }
}
