package me.ivan.RedLight;

import co.aikar.commands.PaperCommandManager;
import lombok.Getter;
import me.ivan.RedLight.arena.ArenaManager;
import me.ivan.RedLight.command.RedLightCommand;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;


@Getter
public final class RedLightPlugin extends JavaPlugin {

    private Economy economy;

    private ArenaManager arenaManager;

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
        setupEconomy();

        this.arenaManager = new ArenaManager(this);

        this.saveDefaultConfig();

        PaperCommandManager manager = new PaperCommandManager(this);

        manager.registerCommand(
                new RedLightCommand(this)
        );

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    // Chat Utilities (Maybe move to extra util class one day..)
    public Component normal(String message) {
        return MiniMessage.miniMessage().deserialize(
                this.getConfig().getString("lang.prefix") + this.getConfig().getString("lang.color-normal") + message
        );
    }

    public Component error(String message) {
        return MiniMessage.miniMessage().deserialize(
                this.getConfig().getString("lang.prefix") + this.getConfig().getString("lang.color-error") + message
        );
    }
}
