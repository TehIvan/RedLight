package me.ivan.RedLight.arena;

import me.ivan.RedLight.RedLightPlugin;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

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
        this.arenas.put(name, arena);

    }
    public void deleteArena(String name) {}

    public void updateArena(String name, Arena arena) {
        this.arenas.put(name, arena);
    }

    public Arena getArena(String name) {
        return this.arenas.get(name);
    }

    public List<Arena> getArenas() {
        return this.arenas.values().stream().toList();
    }
}
