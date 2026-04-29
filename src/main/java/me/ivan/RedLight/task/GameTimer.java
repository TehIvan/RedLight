package me.ivan.RedLight.task;

import me.ivan.RedLight.Game;
import me.ivan.RedLight.RedLightPlugin;
import org.bukkit.ChatColor;
import org.bukkit.scheduler.BukkitRunnable;

public class GameTimer extends BukkitRunnable {

    private RedLightPlugin plugin;
    private Game game;
    private int time;

    public GameTimer(RedLightPlugin plugin, Game game, int time) {
        this.plugin = plugin;
        this.game = game;
        this.time = time;
    }

    public void run() {
        if (this.time <= 0) {
            this.cancel();
            this.game.light.cancel();
            this.game.proceed();
            return;
        }

        this.game.bossbar.setTitle(ChatColor.YELLOW + Integer.toString(this.time) + "m remaining!");
        this.time = this.time - 1;
    }
}
