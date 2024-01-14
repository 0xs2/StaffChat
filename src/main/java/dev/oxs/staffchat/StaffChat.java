package dev.oxs.staffchat;

import dev.oxs.staffchat.commands.*;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;
import java.util.HashMap;
import java.util.UUID;
import java.util.logging.Logger;

public class StaffChat extends JavaPlugin implements Listener {

    private Logger log;
    private String pluginName;
    //Basic Plugin Info
    private static StaffChat plugin;
    private PluginDescriptionFile pdf;

    private HashMap<UUID, Boolean> staffChatToggled = new HashMap<>();

    public void StaffChatMessage(String sender, String message) {
        for (Player onlinePlayer : getServer().getOnlinePlayers()) {
            if (onlinePlayer.hasPermission("staffchat.see") || onlinePlayer.isOp()) {
                onlinePlayer.sendMessage(ChatColor.RED + "[StaffChat] " + ChatColor.WHITE + sender + ": " + ChatColor.WHITE + message);
            }
        }
    }
    @Override
    public void onEnable() {
        plugin = this;
        log = this.getServer().getLogger();
        pdf = this.getDescription();
        pluginName = pdf.getName();

        final StaffChatListener sc = new StaffChatListener(plugin, staffChatToggled);
        Bukkit.getPluginManager().registerEvents(sc, plugin);

        // commmands
        Bukkit.getPluginCommand("StaffChatCommand").setExecutor(new StaffChatCommand(plugin));
        Bukkit.getPluginCommand("StaffChatUsage").setExecutor(new StaffChatUsage(plugin));
        Bukkit.getPluginCommand("StaffChatToggle").setExecutor(new StaffChatToggle(plugin, staffChatToggled));

        pluginName = pdf.getName();

        log.info("[" + pluginName + "] Is Loading, Version: " + pdf.getVersion());
    }

    @Override
    public void onDisable() {
        log.info(pluginName + " has been disabled.");
    }

}
