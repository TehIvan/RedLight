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


}
