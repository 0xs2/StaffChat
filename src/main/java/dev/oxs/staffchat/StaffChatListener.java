package dev.oxs.staffchat;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChatEvent;
import org.bukkit.event.player.PlayerJoinEvent;

import java.util.HashMap;
import java.util.UUID;

public class StaffChatListener implements Listener {

    private final HashMap<UUID, Boolean> staffChatToggled;

    private StaffChat plugin;

    public StaffChatListener(StaffChat plugin, HashMap<UUID, Boolean> staffChatToggled) {
        this.plugin = plugin;
        this.staffChatToggled = staffChatToggled;

    }

    @EventHandler
    public void onPlayerChat(PlayerChatEvent event) {
        Player player = event.getPlayer();
        String uuid = String.valueOf(player.getUniqueId());
        String message = event.getMessage();

        if(player.hasPermission("staffchat.toggle") && player.hasPermission("staffchat.see") || player.isOp()) {
            if (plugin.getToggleStatus(UUID.fromString(uuid))) {
                event.setCancelled(true);
                plugin.StaffChatMessage(player, message);
            }
        }
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Boolean toggleJoinMsg = StaffChatSettings.getInstance(plugin).getConfigBoolean("settings.staffchat-toggle-alert");

        if(toggleJoinMsg) {
            Player player = event.getPlayer();
            String uuid = String.valueOf(player.getUniqueId());

            if (player.hasPermission("staffchat.toggle") && player.hasPermission("staffchat.see") || player.isOp()) {

                Boolean s = plugin.getToggleStatus(UUID.fromString(uuid));

                if (s) {
                    String message = plugin.getLanguage().getMessage("staffchat_togglejoin");
                    message = message.replace("%status%", (s ? ChatColor.GREEN + "on" : ChatColor.RED +"off"));
                    message = message.replace("%prefix%", plugin.getPluginPrefix());
                    message = message.replace("%player%", player.getName());
                    player.sendMessage(message);
                }
            }
        }
    }
}
