package com.devnics.RedLight.task;

import com.devnics.RedLight.Game;
import com.devnics.RedLight.RedLightPlugin;
import org.bukkit.scheduler.BukkitRunnable;

public class LightTimer extends BukkitRunnable {

    private RedLightPlugin plugin;
    private Game game;

    private int time;
    public LightTimer(RedLightPlugin plugin, Game game, int time) {
        this.plugin = plugin;
        this.game = game;
        this.time = time;
    }

    public void run() {
        this.game.setRedLight(!this.game.isRedLight);
    }

}
