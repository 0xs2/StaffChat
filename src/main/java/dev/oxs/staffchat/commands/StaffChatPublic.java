package dev.oxs.staffchat.commands;

import dev.oxs.staffchat.StaffChat;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

public class StaffChatPublic implements CommandExecutor {

    private final StaffChat plugin;

    public StaffChatPublic(Plugin plugin) {
        this.plugin = (StaffChat) plugin;
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (!(commandSender.hasPermission("staffchat.public") || commandSender.isOp())) {
            String message = plugin.getLanguage().getMessage("no_permission");
            commandSender.sendMessage(message);
            return true;
        } else {

            if(strings.length > 0) {
                Player player = (Player) commandSender;
                String message = String.join(" ", strings);

                plugin.PublicChatMessage(player,message);
                return true;
            }
            else {
                String message = plugin.getLanguage().getMessage("publicchat_usage");
                commandSender.sendMessage(message);
                return true;
            }
        }
    }
}
