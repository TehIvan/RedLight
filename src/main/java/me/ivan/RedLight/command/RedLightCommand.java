package me.ivan.RedLight.command;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.*;
import me.ivan.RedLight.RedLightPlugin;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.stream.Collectors;

@CommandAlias("redlight|red")
public class RedLightCommand extends BaseCommand {

    private RedLightPlugin plugin;

    public RedLightCommand(RedLightPlugin plugin) {
        this.plugin = plugin;
    }

    @Default
    public void generalInfo(CommandSender sender) {
        sender.sendMessage(
                this.plugin.normal(
                        "You are running RedLight V" + this.plugin.getPluginMeta().getVersion()
                                + " by " +
                                String.join(", ", this.plugin.getPluginMeta().getAuthors())
                )
        );
    }

    @Subcommand("arena create")
    @CommandPermission("redlight.arena.create")
    public void createArena(Player player, @Single String name) {
        // insert validation

        this.plugin.getArenaManager().createArena(name);
        player.sendMessage(this.plugin.normal("Created arena known as: " + name);
    }

    @Subcommand("arena delete")
    @CommandPermission("redlight.arena.delete")
    public void deleteArena(Player player, @Single String name) {
        // insert validation

        this.plugin.getArenaManager().deleteArena(name);
        player.sendMessage(this.plugin.normal("Deleted arena known as: " + name);
    }

    @Subcommand("arena set deathzone")
    @CommandPermission("redlight.arena.deathzone.set")
    public void setDeathzone(Player player, @Single String name, @Single String region) {

    }

    @Subcommand("arena set safezone")
    @CommandPermission("redlight.arena.safezone.set")
    public void setSafezone(Player player, @Single String name, @Single String region) {

    }

}
