package com.devnics.RedLight.task;

import com.devnics.RedLight.Game;
import com.devnics.RedLight.RedLightPlugin;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class GameTimer extends BukkitRunnable {

    private RedLightPlugin plugin;
    private Game game;
    private int time;
    private BossBar bossBar;

    public GameTimer(RedLightPlugin plugin, Game game, int time) {
        this.plugin = plugin;
        this.game = game;
        this.time = time;
        this.bossBar = Bukkit.createBossBar(
                ChatColor.YELLOW + "Please wait..",
                BarColor.GREEN,
                BarStyle.SOLID
        );
        game.getPlayers().forEach(uuid -> {
            Player player = Bukkit.getPlayer(uuid);

            if (player != null) {
                this.bossBar.addPlayer(player);
            }
        });
    }

    public void run() {
        if (this.time <= 0) {
            this.cancel();
            this.game.proceed();
            return;
        }

        this.bossBar.setTitle(ChatColor.YELLOW + Integer.toString(this.time) + "m remaining!");
        this.time = this.time - 1;
    }
}
