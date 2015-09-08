package tuts;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
 
/**
  *
  * You are permitted to share this code and distribute it as you wish
  * DONT FORGET TO EDIT THE CODE TO YOUR LIKE!!!
  * All I ask is that you give me credit somewhere wherever you decide to post
  * your plugin and link them to my youtube account:
  * https://www.youtube.com/user/JusJusCrafti
  */
 
public class API {
	
	Tutorial p;
 
    public API(Tutorial i){
        p = i;
    }
    
    /**
    *
    * Close a ride with the given ride name!
    *
    * @param name : The name of the ride you created!
    * @param player : The player who initiated the command!
    */
	public void setRideClosed(String name, CommandSender player) {
        FileConfiguration c = this.p.getConfig();
        
        if (c.contains("Rides." + name)) {
        	if (c.getBoolean("Rides." + name + ".isClosed") == true) {
        		c.set("Rides." + name + ".isClosed", false);
        		p.saveConfig();
        		Bukkit.getServer().broadcastMessage(ChatColor.DARK_PURPLE + "The ride " + name + " has been closed!");
        	}else {
        		player.sendMessage(ChatColor.RED + "This ride is already closed!");
        	}
        }else {
        	player.sendMessage(ChatColor.RED + "No ride found by the name: " + ChatColor.YELLOW + name);
        }
	}
	
    /**
    *
    * Open a ride with the given ride name!
    *
    * @param name : The name of the ride you created!
    * @param player : The player who initiated the command!
    */
	public void setRideOpen(String name, CommandSender player) {
        FileConfiguration c = this.p.getConfig();
        
        if (c.contains("Rides." + name)) {
        	if (c.getBoolean("Rides." + name + ".isClosed") == false) {
        		c.set("Rides." + name + ".isClosed", true);
        		p.saveConfig();
        		Bukkit.getServer().broadcastMessage(ChatColor.GOLD + "The ride " + name + " has been Opened!");
        		for (final Player all : Bukkit.getServer().getOnlinePlayers()) {
        			all.getWorld().playSound(all.getLocation(), Sound.CHICKEN_EGG_POP, 5, 5);
        		}
        	}else {
        		player.sendMessage(ChatColor.RED + "This ride is already open!");
        	}
        }else {
        	player.sendMessage(ChatColor.RED + "No ride found by the name: " + ChatColor.YELLOW + name);
        	player.sendMessage(ChatColor.RED + "Note the uppercase!");
        }
	}
    
  /**
    *
    * Creates a named ride at the command sender's current location
    *
    * @param sender : The player who initiated the command
    * @param rideName : The name of the ride to be created. Must be one word!
    */
    public void createRide(Player sender, String rideName){
        FileConfiguration c = p.getConfig();
        Location loc = sender.getLocation();
        String world = sender.getWorld().getName();
        int x, y, z;
        float yaw, pitch;
 
        x = loc.getBlockX();
        y = loc.getBlockY();
        z = loc.getBlockZ();
        yaw = loc.getYaw();
        pitch = loc.getPitch();

        c.set("Rides." + rideName + ".x", x);
        c.set("Rides." + rideName + ".y", y);
        c.set("Rides." + rideName + ".z", z);
        c.set("Rides." + rideName + ".yaw", yaw);
        c.set("Rides." + rideName + ".pitch", pitch);
        c.set("Rides." + rideName + ".world", world);
        c.set("Rides." + rideName + ".isClosed", false);
        p.saveConfig();
 
        sender.sendMessage(ChatColor.AQUA+ "Created Ride " +
                ChatColor.RED + rideName + ChatColor.AQUA + " at " + ChatColor.RED + x + ", " + y + ", " + z + ChatColor.AQUA + " in world " + ChatColor.GREEN + 
                "'" + ChatColor.RED + sender.getWorld().getName() + ChatColor.GREEN + "'");
    }
 
  /**
    *
    * Teleport a player to a specific ride.
    *
    * @param sender : The player who initiated the command
    * @param rideName : The lobby to teleport the sender to
    */
    public void teleportToRide(Player sender, String rideName){
        FileConfiguration c = p.getConfig();
        int x, y, z, yaw, pitch;
 
        if (c.contains("Rides." + rideName)){
            World world = Bukkit.getServer().getWorld(c.getString("Rides." + rideName + ".world"));
            x = c.getInt("Rides." + rideName + ".x");
            y = c.getInt("Rides." + rideName + ".y");
            z = c.getInt("Rides." + rideName + ".z");
            yaw = c.getInt("Rides." + rideName + ".yaw");
            pitch = c.getInt("Rides." + rideName + ".pitch");
 
            sender.teleport(new Location(world, x, y, z, yaw, pitch));
            sender.sendMessage(ChatColor.GOLD + "Teleported you to the Ride: " +
                    ChatColor.RED + rideName);
        }else{
            sender.sendMessage(ChatColor.RED + "Error. Invalid Ride name!");
        }       
    }
 
  /**
    *
    * Used to delete a specific ride.
    *
    * @param sender : The player who initiated the command
    * @param rideName : The name of the lobby to be removed
    */
    public void removeRide(Player sender, String rideName){
        FileConfiguration c = p.getConfig();
 
        if (c.contains("Rides." + rideName)){
            c.set("Rides." + rideName, null);
            p.saveConfig();
            sender.sendMessage(ChatColor.GOLD + "Successfully removed the Ride: " + ChatColor.RED + rideName);
        }else{
            sender.sendMessage(ChatColor.RED + "No Ride found by the name: " +
                    ChatColor.YELLOW + rideName);
        }
    }
    
    
   /**
     *
     * Used to send a list of all rides to the command sender
     *
     * @param sender : The person who initiated the command and will be sent the msg
     */    
    public void listRides(Player sender){
    	FileConfiguration c = p.getConfig();
    	
    	if (c.contains("Rides.")) {
    		sender.sendMessage(ChatColor.GOLD + "Your Rides are: " + ChatColor.DARK_PURPLE + c.getConfigurationSection("Rides").getKeys(false));
    	}else {
    		sender.sendMessage(ChatColor.RED + "You didnt have set any Rides!");
    		}
    	}
    }