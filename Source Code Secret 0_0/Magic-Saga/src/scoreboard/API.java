package scoreboard;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import scoreboard.MessageManager.MsgType;
 
/**
  *
  * You are permitted to share this code and distribute it as you wish
  * DONT FORGET TO EDIT THE CODE TO YOUR LIKE!!!
  * All I ask is that you give me credit somewhere wherever you decide to post
  * your plugin and link them to my youtube account:
  * https://www.youtube.com/user/JusJusCrafti
  */
 
public class API {
	
	Main p;
 
    public API(Main i){
        p = i;
    }
    
  /**
    *
    * Creates a named ride at the command sender's current location
    *
    * @param sender : The player who initiated the command
    * @param rideName : The name of the ride to be created. Must be one word!
    */
    public void createRide(Player sender, String rideName){
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

        c.set("Rides." + rideName + ".x", x);
        c.set("Rides." + rideName + ".y", y);
        c.set("Rides." + rideName + ".z", z);
        c.set("Rides." + rideName + ".yaw", yaw);
        c.set("Rides." + rideName + ".pitch", pitch);
        c.set("Rides." + rideName + ".world", world);
        p.saveNewConfig();
 
        sender.sendMessage(MsgType.SHOW+ "Created Ride " +
                ChatColor.RED + rideName + ChatColor.AQUA + " at " + ChatColor.RED + x + ", " + y + ", " + z + ChatColor.GREEN + " in world " + "'" + ChatColor.RED + sender.getWorld().getName() + ChatColor.GREEN + "'");
    }
 
  /**
    *
    * Teleport a player to a specific ride.
    *
    * @param sender : The player who initiated the command
    * @param rideName : The lobby to teleport the sender to
    */
    public void teleportToRide(Player sender, String rideName){
        FileConfiguration c = p.newConfigz;
        int x, y, z, yaw, pitch;
 
        if (c.contains("Rides." + rideName)){
            World world = Bukkit.getServer().getWorld(c.getString("Rides." + rideName + ".world"));
            x = c.getInt("Rides." + rideName + ".x");
            y = c.getInt("Rides." + rideName + ".y");
            z = c.getInt("Rides." + rideName + ".z");
            yaw = c.getInt("Rides." + rideName + ".yaw");
            pitch = c.getInt("Rides." + rideName + ".pitch");
 
            sender.teleport(new Location(world, x, y, z, yaw, pitch));
            sender.sendMessage(MsgType.NORMAL + "Teleported you to the Ride: " +
                    ChatColor.RED + rideName);
        }else{
            sender.sendMessage(MsgType.ERROR + "Error. Invalid Ride name!");
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
        FileConfiguration c = p.newConfigz;
 
        if (c.contains("Rides." + rideName)){
            c.set("Rides." + rideName, null);
            p.saveNewConfig();
            sender.sendMessage(MsgType.NORMAL + "Successfully removed the Ride: " + ChatColor.RED + rideName);
        }else{
            sender.sendMessage(MsgType.ERROR + "No Ride found by the name: " +
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
    	FileConfiguration c = p.newConfigz;
    	
    	if (c.contains("Rides.")) {
    		sender.sendMessage(MsgType.NORMAL + "Your Rides are: " + ChatColor.DARK_PURPLE + c.getConfigurationSection("Rides").getKeys(false));
    	}else {
    		sender.sendMessage(MsgType.ERROR + "You didnt have set any Rides!");
    		}
    	}
    }