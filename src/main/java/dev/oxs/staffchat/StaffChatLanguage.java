package dev.oxs.staffchat;

// thank you johnymuffin

import org.bukkit.util.config.Configuration;

import java.io.File;
import java.util.HashMap;

public class StaffChatLanguage extends Configuration {
    private HashMap<String, String> map;

    public StaffChatLanguage(File file, boolean dev) {
        super(file);
        map = new HashMap<String, String>();
        loadDefaults();
        if(!dev) {
            loadFile();
        }
    }

    private void loadDefaults() {
        map.put("no_permission", "&4Sorry, you don't have permission for this command.");
        map.put("no_permission_other", "&4%player% &cdoes not have permission for this command.");
        map.put("staffchat_usage", "&cUsage: /sc <message>");
        map.put("publicchat_usage", "&cUsage: /pc <message>");
        map.put("staffchat_toggle", "&7You have turned staff chat %status%&7.");
        map.put("staffchat_togglejoin", "%prefix% &7Hello &f%player%&7, currently have staff chat %status%&7.");
        map.put("staffchat_check", "&7You currently have staff chat %status%&7.");
        map.put("staffchat_check_other", "&f%player% &7currently has staff chat %status%&7.");
        map.put("staffchat_check_online", "&4%player% &cis not online.");
        map.put("staffchat_menu", "&7--------- %prefix% &7---------" +
                "\n&7Version: &f%version%" +
                "\n&7Description: &f%description%" +
                "\n&7Author(s): &f%author%");
    }

    private void loadFile() {
        this.load();
        for (String key : map.keySet()) {
            if (this.getString(key) == null) {
                this.setProperty(key, map.get(key));
            } else {
                map.put(key, this.getString(key));
            }
        }
        this.save();
    }

    public String getMessage(String msg) {
        String loc = map.get(msg);
        if (loc != null) {
            return loc.replace("&", "\u00a7");
        }
        return msg;
    }


}
