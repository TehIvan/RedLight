package com.devnics.RedLight.listener;

import com.devnics.RedLight.RedLightPlugin;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

public class PlayerMovement implements Listener {

    private RedLightPlugin plugin;

    public PlayerMovement(RedLightPlugin plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerMovement(PlayerMoveEvent event) {
        if (this.plugin.game.getWinners().contains(event.getPlayer().getUniqueId())) return;
        if (this.plugin.game.getLosers().contains(event.getPlayer().getUniqueId())) return;
        if (!this.plugin.game.getPlayers().contains(event.getPlayer().getUniqueId())) return;

        if (event.getFrom().getBlockX() == event.getTo().getBlockX() && event.getFrom().getBlockY() == event.getTo().getBlockY() && event.getFrom().getBlockZ() == event.getTo().getBlockZ()) return;

        if (this.plugin.game.isRedLight && this.plugin.game.death && this.plugin.getConfig().getInt("arena.safezoneX") < event.getPlayer().getLocation().getBlockX() && this.plugin.getConfig().getInt("arena.spawnX") > event.getPlayer().getLocation().getBlockX()) {
            this.plugin.game.removePlayer(event.getPlayer().getUniqueId());
            return;
        }

        if (this.plugin.getConfig().getInt("arena.safezoneX") > event.getPlayer().getLocation().getBlockX()) {
            this.plugin.game.setWinner(event.getPlayer());
        }
    }
}
