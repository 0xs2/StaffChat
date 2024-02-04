package dev.oxs.staffchat.commands;

import dev.oxs.staffchat.StaffChat;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import java.util.HashMap;
import java.util.UUID;

import static org.bukkit.Bukkit.getPlayer;

public class StaffChatToggle implements CommandExecutor {
    private final HashMap<UUID, Boolean> staffChatToggled;

    private final StaffChat plugin;

    public StaffChatToggle(Plugin plugin, HashMap<UUID, Boolean> staffChatToggled) {

        this.plugin = (StaffChat) plugin;
        this.staffChatToggled = staffChatToggled;

    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        Player p = getPlayer(commandSender.getName());

        if (!(commandSender.hasPermission("staffchat.toggle") || commandSender.isOp())) {

            String message = plugin.getLanguage().getMessage("no_permission");
            commandSender.sendMessage(message);
            return true;
        }

        if (!staffChatToggled.containsKey(p.getUniqueId())) {
            staffChatToggled.put(p.getUniqueId(), false);
        }

        boolean toggleStatus = !staffChatToggled.get(p.getUniqueId());
        staffChatToggled.put(p.getUniqueId(), toggleStatus);

        String message = plugin.getLanguage().getMessage("staffchat_toggle");
        message = message.replace("%status%", (toggleStatus ? ChatColor.GREEN + "on" : ChatColor.RED +"off"));
        commandSender.sendMessage(message);
        return true;
    }

}
