package com.frozen.celeste;

import org.bukkit.plugin.java.JavaPlugin;

public final class Celeste extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        getServer().getPluginManager().registerEvents(new DashListener(), this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
