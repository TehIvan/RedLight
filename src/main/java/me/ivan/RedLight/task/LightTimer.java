package me.ivan.RedLight.task;

import me.ivan.RedLight.Game;
import me.ivan.RedLight.RedLightPlugin;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
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

            if (!this.game.isRedLight) {
                player.playSound(
                        player.getLocation(),
                        Sound.BLOCK_STONE_BUTTON_CLICK_OFF,
                        5,
                        5
                );
            }

            player.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(ChatColor.translateAlternateColorCodes('&', this.game.isRedLight ? "&cRed Light" : "&aGreen Light")));
        }
    }
}
