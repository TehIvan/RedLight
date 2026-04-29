package me.ivan.RedLight.arena;

import me.ivan.RedLight.RedLightPlugin;

import java.util.HashMap;

public class ArenaManager {

    private RedLightPlugin plugin;

    private HashMap<String, Arena> arenas = new HashMap<>();

    public ArenaManager(RedLightPlugin plugin) {
        this.plugin = plugin;

        for (String key: this.plugin.getConfig().getConfigurationSection("arenas").getKeys(false)) {
            // load..
        }
    }

    public void createArena(String name) {
        Arena arena = new Arena();

        arena.setName(name);


    }
    public void deleteArena(String name) {}
}
