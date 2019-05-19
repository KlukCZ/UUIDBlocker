package me.KlukCZ.UUIDBlocker;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Commands implements CommandExecutor{

    private Main plugin;

    public Commands(Main main){
        plugin = main;
    }

    String uuidblocker = "uuidblocker";
    String uuid = "uuid";

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        try{
            if (cmd.getName().equalsIgnoreCase(uuidblocker)){
                if (args[0].equalsIgnoreCase("help")){
                    sender.sendMessage(" ");
                    sender.sendMessage(" ");
                    sender.sendMessage("§6§lUUIDBlocker §9§lHelp");
                    sender.sendMessage(" ");
                    sender.sendMessage("§7/uuidblocker help");
                    sender.sendMessage("§6§l> §7Shows all commands of the plugin");
                    sender.sendMessage(" ");
                    sender.sendMessage("§7/uuidblocker info");
                    sender.sendMessage("§6§l> §7Shows all the information of the plugin");
                    sender.sendMessage(" ");
                    sender.sendMessage("§7/uuidblocker add §9§l<Player>");
                    sender.sendMessage("§6§l> §7Adds a Player to the UUID BlockList");
                    sender.sendMessage(" ");
                    sender.sendMessage("§7/uuidblocker remove §9§l<Player>");
                    sender.sendMessage("§6§l> §7Removes a Player from the UUID BlockList");
                    sender.sendMessage(" ");
                    sender.sendMessage("§7/uuid");
                    sender.sendMessage("§6§l> §7Shows your unique UUID");
                    return true;
                }

                if (args[0].equalsIgnoreCase("info")){
                    sender.sendMessage(" ");
                    sender.sendMessage(" ");
                    sender.sendMessage("§6§lUUIDBlocker §9§lInfo");
                    sender.sendMessage(" ");
                    sender.sendMessage("§6§l Author> §9§l KlukCZ");
                    sender.sendMessage("§6§l Version> §9§l 1.0");
                    sender.sendMessage("§6§l Release> §a§lSTABLE");
                    return true;
                }

                if (args[0].equalsIgnoreCase("add")){
                    if (sender.hasPermission("uuidblocker.admin")){
                        if (args.length == 2){
                            for (Player selected : Bukkit.getServer().getOnlinePlayers()){
                                if (selected.getName().equalsIgnoreCase(args[1])){
                                    plugin.getConfig().set("banned-uuids." + selected.getUniqueId().toString(), true);
                                    plugin.saveConfig();
                                    selected.kickPlayer("§6§lUUIDBlocker> §7Your UUID has been added to the UUID BlockList of this server!");
                                    sender.sendMessage("§6§lUUIDBlocker> §9§l" + selected.getName() + " §7has been added from the UUID block list!");
                                    return true;
                                }
                            }
                            sender.sendMessage("§6§lUUIDBlocker> §7That player cannot be found!");
                            return true;
                        }
                        return true;
                    }else{
                        sender.sendMessage("§6§lUUIDBlocker> §cYOu do not have permission to do that!");
                    }
                }

                if (args[0].equalsIgnoreCase("remove")){
                    if (sender.hasPermission("uuidblocker.admin")){
                        if (args.length == 2){
                            for (OfflinePlayer player : Bukkit.getOfflinePlayers()){
                                if (player.getName().equalsIgnoreCase(args[1])){
                                    plugin.getConfig().set("banned-uuids." + player.getUniqueId().toString(), null);
                                    plugin.saveConfig();
                                    sender.sendMessage("§6§lUUIDBlocker> §9§l" + player.getName() + " §7has been removed from the UUID block list!");
                                    return true;
                                }
                            }
                            sender.sendMessage("§6§lUUIDBlocker> §7That player cannot be found!");
                            return true;
                        }
                        return true;
                    }else{
                        sender.sendMessage("§6§lUUIDBlocker> §cYOu do not have permission to do that!");
                    }
                }
                return true;
            }

            if (cmd.getName().equalsIgnoreCase(uuid)){
                Player player = (Player) sender;

                sender.sendMessage("§6§lUUID> §7Your UUID is: §9§l" + player.getUniqueId());
                return true;
            }
            return false;
        }catch (Exception e){
            sender.sendMessage("§6§lUUIDBlocker> §9§l/uuidblocker help §7for help");
            return true;
        }
    }
}
