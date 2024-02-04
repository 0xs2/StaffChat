package dev.oxs.staffchat.commands;

import dev.oxs.staffchat.StaffChat;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.Plugin;

public class StaffChatCommand implements CommandExecutor {

    private final StaffChat plugin;

    public StaffChatCommand(Plugin plugin) {
        this.plugin = (StaffChat) plugin;
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (!(commandSender.hasPermission("staffchat.menu") || commandSender.isOp())) {
            String message = plugin.getLanguage().getMessage("no_permission");
            commandSender.sendMessage(message);
            return true;
        } else {


            String message = plugin.getLanguage().getMessage("staffchat_menu");
            message = message.replace("%prefix%", plugin.getPluginPrefix());
            message = message.replace("%version%",plugin.getDescription().getVersion());
            message = message.replace("%description%", plugin.getDescription().getDescription());
            message = message.replace("%author%", plugin.getDescription().getAuthors().toString());
            sendWithNewline(commandSender, message);
            return true;
        }
    }

    protected void sendWithNewline(CommandSender commandSender, String message) {
        String[] lines = message.split("\\n");
        for (String line : lines) {
            commandSender.sendMessage(line);
        }
    }
}
