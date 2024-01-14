package dev.oxs.staffchat;

import dev.oxs.staffchat.commands.*;
import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Logger;

public class StaffChat extends JavaPlugin implements Listener {

    private Logger log;
    private String pluginName;
    //Basic Plugin Info
    private static StaffChat plugin;
    private PluginDescriptionFile pdf;

    @Override
    public void onEnable() {


        plugin = this;
        log = this.getServer().getLogger();
        pdf = this.getDescription();
        pluginName = pdf.getName();

        // commmands
        Bukkit.getPluginCommand("StaffChatCommand").setExecutor(new StaffChatCommand(plugin));
        Bukkit.getPluginCommand("StaffChatUsage").setExecutor(new StaffChatUsage(plugin));

        pluginName = pdf.getName();

        log.info("[" + pluginName + "] Is Loading, Version: " + pdf.getVersion());
    }

    @Override
    public void onDisable() {
        log.info(pluginName + " has been disabled.");
    }
    
}
