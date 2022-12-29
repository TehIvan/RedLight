package com.devnics.RedLight.command;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.CommandPermission;
import co.aikar.commands.annotation.Name;
import co.aikar.commands.annotation.Subcommand;
import com.devnics.RedLight.RedLightPlugin;
import com.devnics.RedLight.Util;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

@CommandAlias("redlight|red")
public class RedLightCommand extends BaseCommand {

    private RedLightPlugin plugin;

    public RedLightCommand(RedLightPlugin plugin) {
        this.plugin = plugin;
    }

    @Subcommand("join")
    public void joinGame(Player player) {
        this.plugin.game.addPlayer(player);
        Util.send(player, "&aYou are now in the waiting area. Please wait for the next game to start.");
    }

    @Subcommand("setrounds")
    @CommandPermission("red.use")
    public void setRounds(Player player, @Name("rounds") Integer num) {
        this.plugin.getConfig().set("rounds", num);
        this.plugin.saveConfig();

        Util.send(player, "&aSet number of rounds in one game to {}".replace("{}", "&e" + Integer.toString(num)));
    }

    @Subcommand("setspawn")
    @CommandPermission("red.use")
    public void setSpawn(Player player) {
        this.plugin.getConfig().set("arena.spawn", player.getLocation());
        this.plugin.saveConfig();

        Util.send(player, "&aSet arena spawn!");
    }

    @Subcommand("start")
    @CommandPermission("red.use")
    public void startGame(CommandSender sender) {
        this.plugin.game.startGame();
    }

    @Subcommand("light")
    @CommandPermission("red.use")
    public void ChangeLight(CommandSender sender) {
        this.plugin.game.setRedLight(!this.plugin.game.isRedLight);
        Util.send(sender, "Now: " + (this.plugin.game.isRedLight ? "&cRED" : "&aGREEN"));
    }

    @Subcommand("end")
    @CommandPermission("red.use")
    public void End(CommandSender sender) {
        this.plugin.game.end();
        Util.send(sender, "&cDone!");
    }
}
