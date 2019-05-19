package me.KlukCZ.UUIDBlocker.Events;

import me.KlukCZ.UUIDBlocker.Main;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.util.List;


public class EventClass implements Listener {

    public Main plugin;

    public EventClass(Main main){
        plugin = main;
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event){
        Player player = event.getPlayer();
        
        if (player.hasPermission("uuidblocker.admin")){
            player.sendMessage("§6§lUUIDBlocker> §7Thanks for using §6§lUUIDBlocker §7made by §9§lKlukCZ! §c§l<3");
        }
        
        if(plugin.getConfig().getBoolean("banned-uuids." + player.getUniqueId().toString())){
            player.kickPlayer("§6§lUUIDBlocker> §7Your UUID is on the UUID BlockList of this server!");
        }

    }
}
