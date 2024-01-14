package dev.oxs.staffchat;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChatEvent;
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
            if (getToggleStatus(UUID.fromString(uuid))) {
                event.setCancelled(true);
                plugin.StaffChatMessage(player.getName(), message);
            }
        }
    }



    private boolean getToggleStatus(UUID player) {
        return staffChatToggled.getOrDefault(player, false);
    }

}
