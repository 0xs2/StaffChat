package dev.oxs.staffchat.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import static org.bukkit.Bukkit.getServer;

public class StaffChatUsage implements CommandExecutor {

    private final Plugin plugin;

    public StaffChatUsage(Plugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (!(commandSender.hasPermission("staffchat.use") || commandSender.isOp())) {
            commandSender.sendMessage(ChatColor.RED + "No permission.");
            return true;
        }
        if (strings.length > 0) {
            String message = ChatColor.stripColor(String.join(" ", strings));
            StaffChatMessage(commandSender.getName(), message);
        } else {
            commandSender.sendMessage(ChatColor.RED + "Usage: /sc <message>");
            return true;
        }
        return true;
    }

    private void StaffChatMessage(String sender, String message) {
        for (Player onlinePlayer : getServer().getOnlinePlayers()) {
            if (onlinePlayer.hasPermission("staffchat.see") || onlinePlayer.isOp()) {
                onlinePlayer.sendMessage(ChatColor.RED + "[StaffChat] " + ChatColor.WHITE + sender + ": " + ChatColor.WHITE + message);
            }
        }
    }
}
