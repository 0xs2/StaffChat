package dev.oxs.staffchat.commands;

import dev.oxs.staffchat.StaffChat;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;


import static org.bukkit.Bukkit.getPlayer;

public class StaffChatCheck implements CommandExecutor {

    private final StaffChat plugin;

    public StaffChatCheck(Plugin plugin) {
        this.plugin = (StaffChat) plugin;
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (!(commandSender.hasPermission("staffchat.check") || commandSender.isOp())) {
            String message = plugin.getLanguage().getMessage("no_permission");
            commandSender.sendMessage(message);
            return true;
        }

        if (strings.length != 0) {
            Player p = getPlayer(strings[0]);

            if (p == null) {
                String message = plugin.getLanguage().getMessage("staffchat_check_online");
                message = message.replace("%player%", strings[0]);
                commandSender.sendMessage(message);
                return true;
            }

            if (!p.hasPermission("staffchat.toggle") && !p.hasPermission("staffchat.use") || !p.isOp()) {
                String message = plugin.getLanguage().getMessage("no_permission_other");
                message = message.replace("%player%", p.getName());
                commandSender.sendMessage(message);
                return true;
            }

            Boolean i = plugin.getToggleStatus(p.getUniqueId());
            String message = plugin.getLanguage().getMessage("staffchat_check_other");
            message = message.replace("%player%", p.getName());
            message = message.replace("%status%", (i ? ChatColor.GREEN + "on" : ChatColor.RED +"off"));
            commandSender.sendMessage(message);
            return true;

        }
        else {
            Player p = getPlayer(commandSender.getName());
            Boolean i = plugin.getToggleStatus(p.getUniqueId());
            String message = plugin.getLanguage().getMessage("staffchat_check");
            message = message.replace("%status%", (i ? ChatColor.GREEN + "on" : ChatColor.RED +"off"));
            commandSender.sendMessage(message);
            return true;
        }
    }
}
