package me.ivan.RedLight;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

public class Util {

    public static Component normal(String message) {
        return MiniMessage.miniMessage().deserialize(
                "<green>" + message
        );
    }

    public static Component error(String message) {
        return MiniMessage.miniMessage().deserialize(
                "<red>" + message
        );
    }
}
