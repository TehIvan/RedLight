package com.devnics.RedLight;

import co.aikar.commands.PaperCommandManager;
import com.devnics.RedLight.command.RedLightCommand;
import com.devnics.RedLight.listener.PlayerMovement;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

public final class RedLightPlugin extends JavaPlugin {

    public Game game;
    public Economy economy;

    public void setupEconomy() {
        if (getServer().getPluginManager().getPlugin("Vault") == null) {
            return;
        }
        RegisteredServiceProvider<Economy> rsp = getServer().getServicesManager().getRegistration(Economy.class);
        if (rsp == null) {
            return;
        }
        economy = rsp.getProvider();
        return;
    }
    @Override
    public void onEnable() {
        // Plugin startup logic

        setupEconomy();
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
