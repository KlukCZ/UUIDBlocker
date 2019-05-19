package me.KlukCZ.UUIDBlocker;

import me.KlukCZ.UUIDBlocker.Events.EventClass;
import org.bukkit.ChatColor;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin implements Listener {

    private Commands commands = new Commands(this);

    public void onEnable(){
        getCommand(commands.uuidblocker).setExecutor(commands);
        getCommand(commands.uuid).setExecutor(commands);
        getServer().getConsoleSender().sendMessage(ChatColor.GOLD + "UUIDBlocker> " + ChatColor.DARK_GREEN + "UUID Blocker successfully Enabled!");
        getServer().getPluginManager().registerEvents(new EventClass(this), this);
        loadConfig();
    }

    public void onDisable(){
        getServer().getConsoleSender().sendMessage(ChatColor.GOLD + "UUIDBlocker> " + ChatColor.DARK_GREEN + "UUID Blocker successfully Disabled!");
    }

    public void loadConfig(){
        getConfig().options().copyDefaults(true);
        saveConfig();
    }
}
