package dev.oxs.staffchat.commands;

import dev.oxs.staffchat.StaffChat;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.Plugin;

public class StaffChatUsage implements CommandExecutor {

    private StaffChat plugin;

    public StaffChatUsage(Plugin plugin) {
        this.plugin = (StaffChat) plugin;
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (!(commandSender.hasPermission("staffchat.use") || commandSender.isOp())) {
            commandSender.sendMessage(ChatColor.RED + "No permission.");
            return true;
        }
        if (strings.length > 0) {
            String message = String.join(" ", strings);
            plugin.StaffChatMessage(commandSender.getName(), message);
        } else {
            commandSender.sendMessage(ChatColor.RED + "Usage: /sc <message>");
            return true;
        }
        return true;
    }



}
