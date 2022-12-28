package com.devnics.RedLight;

import co.aikar.commands.PaperCommandManager;
import com.devnics.RedLight.command.RedLightCommand;
import com.devnics.RedLight.listener.PlayerMovement;
import org.bukkit.plugin.java.JavaPlugin;

public final class RedLightPlugin extends JavaPlugin {

    public Game game;

    @Override
    public void onEnable() {
        // Plugin startup logic

        this.saveDefaultConfig();

        this.game = new Game(this);

        PaperCommandManager manager = new PaperCommandManager(this);

        manager.registerCommand(
                new RedLightCommand(this)
        );

        this.getServer().getPluginManager().registerEvents(new PlayerMovement(this), this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
