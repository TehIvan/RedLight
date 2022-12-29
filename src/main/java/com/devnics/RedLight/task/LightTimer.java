package com.devnics.RedLight.task;

import com.devnics.RedLight.Game;
import com.devnics.RedLight.RedLightPlugin;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.UUID;

public class LightTimer extends BukkitRunnable {

    private RedLightPlugin plugin;
    private Game game;
    public LightTimer(RedLightPlugin plugin, Game game) {
        this.plugin = plugin;
        this.game = game;
    }

    public void run() {
        for (UUID uuid: this.game.getPlayers()) {
            Player player = Bukkit.getPlayer(uuid);

            if (player == null) continue;

            player.
                    spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(ChatColor.translateAlternateColorCodes('&', this.game.isRedLight ? "&cRED" : "&aGREEN")));
        }
    }
}
