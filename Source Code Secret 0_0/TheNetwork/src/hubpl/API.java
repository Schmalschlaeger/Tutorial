package hubpl;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import hubpl.Hub;
import hubpl.MessageManager.MsgType;
 
/**
  *
  * You are permitted to share this code and distribute it as you wish
  * All I ask is that you give me credit somewhere wherever you decide to post
  * your plugin and link them to my BukkitForums account and/or my
  * BukkitDev account (Can be found on my BukkitForums page).
  */
 
public class API {
    Hub p;
 
    public API(Hub i){
        p = i;
    }
    
    
 
  
    public void createHub(Player sender){
        FileConfiguration c = p.newConfigz;
        Location loc = sender.getLocation();
        String world = sender.getWorld().getName();
        int x, y, z;
        float yaw, pitch;
 
        x = loc.getBlockX();
        y = loc.getBlockY();
        z = loc.getBlockZ();
        yaw = loc.getYaw();
        pitch = loc.getPitch();
 
        c.set("Hub.x", x);
        c.set("Hub.y", y);
        c.set("Hub.z", z);
        c.set("Hub.yaw", yaw);
        c.set("Hub.pitch", pitch);
        c.set("Hub.world", world);
        p.saveNewConfig();
 
        sender.sendMessage(MsgType.CREATE + "Successfully set hub location at: " +
                ChatColor.GOLD + x + ", " + y + ", " + z + ChatColor.GREEN + " in world " + "'" + ChatColor.RED + sender.getWorld().getName() + ChatColor.GREEN + "'");
    }
 
  /**
    *
    * Used to teleport the player
    *
    * @param player : The player to be teleported
    */
    public void teleportToHub(Player player){
        FileConfiguration c = p.newConfigz;
        int x, y, z, yaw, pitch;
 
        if (c.getKeys(false).contains("Hub")){
            World world = Bukkit.getServer().getWorld(c.getString("Hub.world"));
            x = c.getInt("Hub.x");
            y = c.getInt("Hub.y");
            z = c.getInt("Hub.z");
            yaw = c.getInt("Hub.yaw");
            pitch = c.getInt("Hub.pitch");
 
            player.teleport(new Location(world, x, y, z, yaw, pitch));
 
        }else{
            player.sendMessage(MsgType.ERROR + "Error. No hub has been set! Ask the server admin!");
        }
    }
 
  /**
    *
    * Creates a named lobby at the command sender's current location
    *
    * @param sender : The player who initiated the command
    * @param lobbyName : The name of the lobby to be created. Must be one word!
    */
    public void createLobby(Player sender, String lobbyName){
        FileConfiguration c = p.newConfigz;
        Location loc = sender.getLocation();
        String world = sender.getWorld().getName();
        int x, y, z;
        float yaw, pitch;
 
        x = loc.getBlockX();
        y = loc.getBlockY();
        z = loc.getBlockZ();
        yaw = loc.getYaw();
        pitch = loc.getPitch();

        c.set("Lobbies." + lobbyName + ".x", x);
        c.set("Lobbies." + lobbyName + ".y", y);
        c.set("Lobbies." + lobbyName + ".z", z);
        c.set("Lobbies." + lobbyName + ".yaw", yaw);
        c.set("Lobbies." + lobbyName + ".pitch", pitch);
        c.set("Lobbies." + lobbyName + ".world", world);
        p.saveNewConfig();
 
        sender.sendMessage(MsgType.CREATE+ "Created lobby " +
                ChatColor.RED + lobbyName + ChatColor.AQUA + " at " + ChatColor.RED + x + ", " + y + ", " + z + ChatColor.GREEN + " in world " + "'" + ChatColor.RED + sender.getWorld().getName() + ChatColor.GREEN + "'");
    }
 
  /**
    *
    * Teleport a player to a specific lobby
    *
    * @param sender : The player who initiated the command
    * @param lobbyName : The lobby to teleport the sender to
    */
    public void teleportToLobby(Player sender, String lobbyName){
        FileConfiguration c = p.newConfigz;
        int x, y, z, yaw, pitch;
 
        if (c.contains("Lobbies." + lobbyName)){
            World world = Bukkit.getServer().getWorld(c.getString("Lobbies." + lobbyName + ".world"));
            x = c.getInt("Lobbies." + lobbyName + ".x");
            y = c.getInt("Lobbies." + lobbyName + ".y");
            z = c.getInt("Lobbies." + lobbyName + ".z");
            yaw = c.getInt("Lobbies." + lobbyName + ".yaw");
            pitch = c.getInt("Lobbies." + lobbyName + ".pitch");
 
            sender.teleport(new Location(world, x, y, z, yaw, pitch));
            sender.sendMessage(MsgType.NORMAL + "Teleported you to the lobby: " +
                    ChatColor.RED + lobbyName);
        }else{
            sender.sendMessage(MsgType.ERROR + "Error. Invalid lobby name!");
        }       
    }
 
  /**
    *
    * Used to delete a specific lobby
    *
    * @param sender : The player who initiated the command
    * @param lobbyName : The name of the lobby to be removed
    */
    public void removeLobby(Player sender, String lobbyName){
        FileConfiguration c = p.newConfigz;
 
        if (c.contains("Lobbies." + lobbyName)){
            c.set("Lobbies." + lobbyName, null);
            p.saveNewConfig();
            sender.sendMessage(MsgType.NORMAL + "Successfully removed the lobby: " + ChatColor.RED + lobbyName);
        }else{
            sender.sendMessage(MsgType.ERROR + "No lobby found by the name: " +
                    ChatColor.YELLOW + lobbyName);
        }
    }
    
   /**
     *
     * Used to send a list of all lobbies to the command sender
     *
     * @param sender : The person who initiated the command and will be sent the msg
     */
    public void listHub(Player sender) {
        FileConfiguration c = p.newConfigz;
    	
    	if (c.contains("hub")) {
    		sender.sendMessage(MsgType.NORMAL + "Your hub x: " + ChatColor.DARK_PURPLE + c.getConfigurationSection("hub.x").getKeys(false));
    	}else {
    		sender.sendMessage(MsgType.ERROR + "You didnt have set an hub!");
    	}
    }
    
    public void listLobbies(Player sender){
    	FileConfiguration c = p.newConfigz;
    	
    	if (c.contains("Lobbies.")) {
    		sender.sendMessage(MsgType.NORMAL + "Your lobbies are: " + ChatColor.DARK_PURPLE + c.getConfigurationSection("Lobbies").getKeys(false));
    	}else {
    		sender.sendMessage(MsgType.ERROR + "You didnt have set any lobbies!");
    		}
    	}
    }