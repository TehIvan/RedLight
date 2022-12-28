package com.devnics.RedLight.listener;

import com.devnics.RedLight.RedLightPlugin;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

public class PlayerMovement implements Listener {

    private RedLightPlugin plugin;

    public PlayerMovement(RedLightPlugin plugin) {
        this.plugin = plugin;
    }

    public void onPlayerMovement(PlayerMoveEvent event) {
        if (this.plugin.game.getWinners().contains(event.getPlayer().getUniqueId())) return;
        if (!this.plugin.game.getPlayers().contains(event.getPlayer().getUniqueId())) return;

        if (event.getPlayer().getLocation().getBlockX() <= this.plugin.getConfig().getInt("arena.safezoneX")) {
            this.plugin.game.setWinner(event.getPlayer());
        } else if (this.plugin.game.isRedLight) {

        }
    }
}
