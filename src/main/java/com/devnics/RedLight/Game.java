package com.devnics.RedLight;

import com.devnics.RedLight.task.GameTimer;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.UUID;

public class Game {

    private RedLightPlugin plugin;

    private ArrayList<UUID> players = new ArrayList<>();
    private ArrayList<UUID> winners = new ArrayList<>();

    boolean isRedLight = false;

    private BukkitRunnable timer = null;

    private int round = 0;

    public Game(RedLightPlugin plugin) {
        this.plugin = plugin;
        this.round = this.plugin.getConfig().getInt("rounds");
    }

    public void startGame() {

        this.timer = new GameTimer(this.plugin, this, this.round * 2);
        this.timer.runTaskTimer(this.plugin, 0, 20 * 60);
        this.winners = new ArrayList<>();

        boolean e = false;

        if (this.round <= 2) {
            e = true;
        }

        Location location = this.plugin.getConfig().getLocation("arena.spawn");

        for (UUID playerUUID: this.players) {
            Player player = Bukkit.getPlayer(playerUUID);

            if (player != null) {
                player.teleport(location);
                player.sendTitle(
                        ChatColor.GREEN + "" + ChatColor.BOLD + "GO",
                        e ? ChatColor.RED + "" + ChatColor.BOLD + "ELIMINATION ROUND" : "",
                        10,
                        20 * 5,
                        10
                );
            }
        }
    }

    public void proceed() {
        if (this.round == 1) {
            // Game Over.
            return;
        }

        if (this.round <= 2) {
            this.players.forEach(playerUUID -> {
                if (this.winners.contains(playerUUID)) return;
                this.removePlayer(playerUUID);
            });
        }

        this.round = this.round - 1;
        this.startGame();
    }

    public void addPlayer(Player player) {
        this.players.add(player.getUniqueId());

        World world = Bukkit.getWorld(this.plugin.getConfig().getString("waitlobby"));

        player.teleport(
                world.getSpawnLocation()
        );
    }

    public void removePlayer(UUID uuid) {
        this.players.remove(uuid);

        Player player = Bukkit.getPlayer(uuid);

        if (this.round <= 2) {
            if (player != null) {
                player.setHealth(0);
            }
        }
    }

    public ArrayList<UUID> getPlayers() {
        return players;
    }

}
