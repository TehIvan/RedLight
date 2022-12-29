package com.devnics.RedLight;

import com.devnics.RedLight.task.GameTimer;
import com.devnics.RedLight.task.LightTimer;
import lombok.Setter;
import org.bukkit.*;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.Random;
import java.util.UUID;

public class Game {

    private RedLightPlugin plugin;

    private ArrayList<UUID> players = new ArrayList<>();
    private ArrayList<UUID> winners = new ArrayList<>();
    private ArrayList<UUID> losers = new ArrayList<>();

    @Setter
    public boolean death = false;
    public boolean isRedLight = false;
    public BossBar bossbar = Bukkit.createBossBar(
                    ChatColor.YELLOW + "Please wait..",
                    BarColor.GREEN,
                    BarStyle.SOLID);

    private BukkitRunnable timer = null;
    public BukkitRunnable light = null;

    private int round = 0;

    public Game(RedLightPlugin plugin) {
        this.plugin = plugin;
        this.round = this.plugin.getConfig().getInt("rounds");
    }

    public void startGame() {

        this.timer = new GameTimer(this.plugin, this, this.round * 2);
        this.timer.runTaskTimer(this.plugin, 0, 20 * 60);

        int a = this.round * 4;

        this.light = new LightTimer(this.plugin, this);
        this.light.runTaskTimer(this.plugin, 20, 20);

        this.winners = new ArrayList<>();
        this.losers = new ArrayList<>();

        boolean e = false;

        if (this.round <= 2) {
            e = true;
        }

        Location location = this.plugin.getConfig().getLocation("arena.spawn");

        for (UUID playerUUID: this.players) {
            Player player = Bukkit.getPlayer(playerUUID);

            if (player != null) {
                this.bossbar.addPlayer(player);

                player.setGameMode(GameMode.SURVIVAL);
                player.teleport(location);
                player.sendMessage(
                        "",
                        ChatColor.GREEN + "" + ChatColor.BOLD + "Round " + Integer.toString((this.plugin.getConfig().getInt("rounds") - this.round) + 1),
                        e ? ChatColor.RED + "" + ChatColor.BOLD + "ELIMINATION ROUND" : ""
                );
            }
        }
    }

    public void proceed() {
        if (this.round == 1 || (this.winners.size() == 0 && this.round <= 2)) {
            this.end();
            return;
        }

        this.round = this.round - 1;
        this.light.cancel();
        this.timer.cancel();
        Bukkit.getScheduler().scheduleSyncDelayedTask(this.plugin, new Runnable() {
            @Override
            public void run() {
                startGame();
            }
        }, 20 * 5);
    }

    public void end() {
        this.timer.cancel();
        this.light.cancel();
        this.bossbar.removeAll();
        this.losers = new ArrayList<>();

        for (Player player: Bukkit.getOnlinePlayers()) {
            if (this.winners.contains(player.getUniqueId()) || this.losers.contains(player.getUniqueId())) {
                Bukkit.dispatchCommand(player, "spawn");
                player.setGameMode(GameMode.SURVIVAL);
            }

            if (this.winners.size() == 0) {
                Util.send(player, "&cGame has ended! No winners were declared!");
            } else {
                String[] str = new String[2 + winners.size()];

                str[0] = "";
                str[1] = Util.translate("&e&lWINNERS");

                int index = 1;

                for (UUID winner: winners) {
                    str[index + 1] = Util.translate("&7{i}. {plr}").replace("{i}", Integer.toString(index)).replace("{plr}", Bukkit.getPlayer(winner).getName());
                    index++;
                };

                player.sendMessage(
                        str
                );
            }
        }

        this.players = new ArrayList<>();
        this.winners = new ArrayList<>();
    }

    // TP to winner's area, set to array list
    public void setWinner(Player player) {
        player.setGameMode(GameMode.SPECTATOR);

        int coins = 100 - (winners.size() * 10);

        if (coins != 0) {
            this.plugin.economy.depositPlayer(player, coins);
            Util.send(player, "You have received &e${c}!".replace("{c}", Integer.toString(coins)));
        } else {
            Util.send(player, "&cYou completed the round too slow! No coins for you.");
        }

        winners.add(player.getUniqueId());

        for (UUID pl: this.players) {
            Player plr = Bukkit.getPlayer(pl);

            if (plr != null)
                plr.sendMessage(Util.translate("&7{} completed round {r}".replace("{}", player.getName()).replace("{r}", Integer.toString((this.plugin.getConfig().getInt("rounds") - this.round) + 1))));
        }

        if (this.winners.size() + this.losers.size() == this.players.size()) {
            this.proceed();
            return;
        }
    }

    public void setRedLight(boolean isRedLight) {

        this.players.stream().filter(r -> !this.winners.contains(r)).forEach(uuid -> {
            Player player = Bukkit.getPlayer(uuid);
            if (player != null) {
                this.isRedLight = isRedLight;

                if (isRedLight) {
                    Bukkit.getScheduler().scheduleSyncDelayedTask(this.plugin, new Runnable() {
                        @Override
                        public void run() {
                            setDeath(true);
                        }
                    }, 2 * 20);
                } else {
                    setDeath(false);
                }
            }
        });

    }
    public void addPlayer(Player player) {
        this.players.add(player.getUniqueId());

        World world = Bukkit.getWorld(this.plugin.getConfig().getString("waitlobby"));

        player.teleport(
                world.getSpawnLocation()
        );
    }

    public void removePlayer(UUID uuid) {

        Player player = Bukkit.getPlayer(uuid);

        for (UUID pl: this.players) {
            Player plr = Bukkit.getPlayer(pl);

            if (plr != null)
                plr.sendMessage(Util.translate("&7{} failed round {r}".replace("{}", player.getName()).replace("{r}", Integer.toString((this.plugin.getConfig().getInt("rounds") - this.round) + 1))));
        }

        if (this.round <= 2) {
            this.players.remove(uuid);
        } else {
            this.losers.add(uuid);
        }

        if (player != null) {
            player.setGameMode(GameMode.SPECTATOR);
        }

        if (this.losers.size() + this.winners.size() == this.players.size()) {
            this.proceed();
        }
    }

    public ArrayList<UUID> getPlayers() {
        return players;
    }

    public ArrayList<UUID> getLosers() {
        return losers;
    }

    public ArrayList<UUID> getWinners() {
        return winners;
    }
}
