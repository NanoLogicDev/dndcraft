package dev.nanologic.dndcraft;

import dev.nanologic.dndcraft.commands.CommandRoll;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class Dndcraft extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        Bukkit.getLogger().info("onEnable");

        // Commands
        this.getCommand("roll").setExecutor(new CommandRoll());
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        Bukkit.getLogger().info("onDisable");
    }
}
