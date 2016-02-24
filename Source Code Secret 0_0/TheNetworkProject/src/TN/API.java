package TN;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
 
/**
  *
  * You are permitted to share this code and distribute it as you wish
  * All I ask is that you give me credit somewhere wherever you decide to post
  * your plugin and link them to my BukkitForums account and/or my
  * BukkitDev account (Can be found on my BukkitForums page).
  */
 
public class API implements Listener {
	
	public ArrayList<String> walk = new ArrayList<String>();
	public static Set<String> hub = new HashSet<String>();
    
    private Main plugin;
	 
	public API(Main instance) {
	    this.plugin = instance;
	}
  
    public void createHub(Player sender){
        final String prefix = ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("prefix") + " ");
        FileConfiguration c = plugin.lobbys;
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
        plugin.saveLobbyConfig();
 
        sender.sendMessage(prefix + ChatColor.GREEN + "Successfully set hub location at: " +
                ChatColor.GOLD + x + ", " + y + ", " + z + ChatColor.GREEN + " in world " + "'" + ChatColor.RED + sender.getWorld().getName() + ChatColor.GREEN + "'");
    }
 
  /**
    *
    * Used to teleport the player
    *
    * @param player : The player to be teleported
    */
    public void teleportToHub(final Player player){
        final String prefix = ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("prefix") + " ");
        FileConfiguration c = plugin.lobbys;
        FileConfiguration conf = plugin.getConfig();
        final int x;
		final int y;
		final int z;
		final int yaw;
		final int pitch;
		final int delay = conf.getInt("Hubdelay");
 
        if (c.getKeys(false).contains("Hub")){
            final World world = Bukkit.getServer().getWorld(c.getString("Hub.world"));
            x = c.getInt("Hub.x");
            y = c.getInt("Hub.y");
            z = c.getInt("Hub.z");
            yaw = c.getInt("Hub.yaw");
            pitch = c.getInt("Hub.pitch");
            
            if (!hub.contains(player.getName())) {
            	hub.add(player.getName());
            	if (delay != 0) {
                    player.sendMessage(prefix + ChatColor.LIGHT_PURPLE + "Wait " + delay + " seconds to teleport! Dont move!");
            		Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
                    	@Override
                    	public void run() {
                    		if (hub.contains(player.getName())) {
                            player.sendMessage(prefix + ChatColor.LIGHT_PURPLE + "Teleporting you to the hub.......");
                    		player.sendMessage(prefix + ChatColor.LIGHT_PURPLE + "Teleported!");
                    		player.teleport(new Location(world, x, y, z, yaw, pitch));
                    		hub.remove(player.getName());
                    		}
                    	}
                    }, delay * 20);
            	}else {
            		
            Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
            	@Override
            	public void run() {
                    player.sendMessage(prefix + ChatColor.LIGHT_PURPLE + "Teleporting you to the hub.......");
            		player.sendMessage(prefix + ChatColor.LIGHT_PURPLE + "Teleported!");
            		player.teleport(new Location(world, x, y, z, yaw, pitch));
            		hub.remove(player.getName());
            	}
            }, delay * 20);
            	}

            }
            
 
        }else{
            player.sendMessage(prefix + "An error has been detected to perfrom this command! We cant find an hub, we are nice enough to teleport you to the server spawn!");
            player.teleport(player.getWorld().getSpawnLocation());
        }
    }


    
 //   @EventHandler
 //   public void onPlayerMove(PlayerMoveEvent e) {
  //      String prefix = ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("prefix") + " ");
  //  	Player player = e.getPlayer();
  //  	if (hub.contains(e.getPlayer().getName())) {
  //  		hub.remove(player.getName());
  //  		player.sendMessage(prefix + "You have walked! Dont move if you want to teleport!");
  //  	}
  //  }
 
  /**
    *
    * Creates a named lobby at the command sender's current location
    *
    * @param sender : The player who initiated the command
    * @param lobbyName : The name of the lobby to be created. Must be one word!
    */
    public void createLobby(Player sender, String lobbyName){
        final String prefix = ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("prefix") + " ");
        FileConfiguration c = plugin.lobbys;
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
        plugin.saveLobbyConfig();
 
        sender.sendMessage(prefix + ChatColor.GREEN + "Created lobby " +
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
        final String prefix = ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("prefix") + " ");
        FileConfiguration c = plugin.lobbys;
        int x, y, z, yaw, pitch;
 
        if (c.contains("Lobbies." + lobbyName)){
            World world = Bukkit.getServer().getWorld(c.getString("Lobbies." + lobbyName + ".world"));
            x = c.getInt("Lobbies." + lobbyName + ".x");
            y = c.getInt("Lobbies." + lobbyName + ".y");
            z = c.getInt("Lobbies." + lobbyName + ".z");
            yaw = c.getInt("Lobbies." + lobbyName + ".yaw");
            pitch = c.getInt("Lobbies." + lobbyName + ".pitch");
 
            sender.teleport(new Location(world, x, y, z, yaw, pitch));
            sender.sendMessage(prefix + ChatColor.LIGHT_PURPLE + "Teleported you to the lobby: " +
                    ChatColor.RED + lobbyName);
        }else{
            sender.sendMessage(prefix + ChatColor.RED + "Error. Invalid lobby name!");
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
        FileConfiguration c = plugin.lobbys;
        final String prefix = ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("prefix") + " ");
 
        if (c.contains("Lobbies." + lobbyName)){
            c.set("Lobbies." + lobbyName, null);
            plugin.saveLobbyConfig();
            sender.sendMessage(prefix + ChatColor.LIGHT_PURPLE + "Successfully removed the lobby: " + ChatColor.RED + lobbyName);
        }else{
            sender.sendMessage(prefix + ChatColor.RED + "No lobby found by the name: " +
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
        final String prefix = ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("prefix") + " ");
        FileConfiguration c = plugin.lobbys;
    	
    	if (c.contains("hub")) {
    		sender.sendMessage(prefix + ChatColor.LIGHT_PURPLE + "Your hub x: " + ChatColor.DARK_PURPLE + c.getConfigurationSection("hub.x").getKeys(false));
    	}else {
    		sender.sendMessage(prefix + ChatColor.RED + "You didnt have set an hub!");
    	}
    }
    
    public void listLobbies(Player sender){
        final String prefix = ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("prefix") + " ");
    	FileConfiguration c = plugin.lobbys;
    	
    	if (c.contains("Lobbies.")) {
    		sender.sendMessage(prefix + ChatColor.LIGHT_PURPLE +  "Your lobbies are: " + ChatColor.DARK_PURPLE + c.getConfigurationSection("Lobbies").getKeys(false));
    	}else {
    		sender.sendMessage(prefix + ChatColor.RED + "You didnt have set any lobbies!");
    		}
    	}
    }