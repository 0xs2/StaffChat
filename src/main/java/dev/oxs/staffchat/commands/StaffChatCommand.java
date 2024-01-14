package dev.oxs.staffchat.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.Plugin;

public class StaffChatCommand implements CommandExecutor {

    private final Plugin plugin;

    public StaffChatCommand(Plugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (!(commandSender.hasPermission("staffchat.menu") || commandSender.isOp())) {
            commandSender.sendMessage(ChatColor.RED + "No permission.");
            return true;
        } else {
            commandSender.sendMessage("Version : " + ChatColor.RED + "v" + plugin.getDescription().getVersion());
            commandSender.sendMessage("Description : " + ChatColor.RED + plugin.getDescription().getDescription());
            commandSender.sendMessage("Author(s) : " + ChatColor.RED + plugin.getDescription().getAuthors().toString());
            return true;
        }
    }
}
