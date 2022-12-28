package com.devnics.RedLight;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

public class Util {

    public static void send(CommandSender sender, String msg) {
        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', msg));
    }

}
