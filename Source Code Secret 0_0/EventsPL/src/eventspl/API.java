package eventspl;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import eventspl.MessageManager.MsgType;
 
public class API {
    events p;
 
    public API(events i){
        p = i;
    }
      
    public void createLeavePoint(Player sender){
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
 
        c.set("Events.x", x);
        c.set("Hub.y", y);
        c.set("Hub.z", z);
        c.set("Hub.yaw", yaw);
        c.set("Hub.pitch", pitch);
        c.set("Hub.world", world);
        p.saveNewConfig();
 
        sender.sendMessage(MsgType.CREATE + "Successfully set hub location at: " +
                ChatColor.GOLD + x + ", " + y + ", " + z + ChatColor.GREEN + " in world " + "'" + ChatColor.RED + sender.getWorld().getName() + ChatColor.GREEN + "'");
    }
 
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
 
    public void createEvent(Player sender, String eventName){
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

        c.set("Events." + eventName + ".x", x);
        c.set("Events." + eventName + ".y", y);
        c.set("Events." + eventName + ".z", z);
        c.set("Events." + eventName + ".yaw", yaw);
        c.set("Events." + eventName + ".pitch", pitch);
        c.set("Events." + eventName + ".world", world);
        p.saveNewConfig();
 
        sender.sendMessage(MsgType.CREATE+ "Created event " +
                ChatColor.RED + eventName + ChatColor.AQUA + " at " + ChatColor.RED + x + ", " + y + ", " + z + ChatColor.GREEN + " in world " + "'" + ChatColor.RED + sender.getWorld().getName() + ChatColor.GREEN + "'");
    }
 
    public void teleportToEvent(Player sender, String lobbyName){
        FileConfiguration c = p.newConfigz;
        int x, y, z, yaw, pitch;
 
        if (c.contains("Events." + lobbyName)){
            World world = Bukkit.getServer().getWorld(c.getString("Events." + lobbyName + ".world"));
            x = c.getInt("Events." + lobbyName + ".x");
            y = c.getInt("Events." + lobbyName + ".y");
            z = c.getInt("Events." + lobbyName + ".z");
            yaw = c.getInt("Events." + lobbyName + ".yaw");
            pitch = c.getInt("Events." + lobbyName + ".pitch");
 
            sender.teleport(new Location(world, x, y, z, yaw, pitch));
            sender.sendMessage(MsgType.NORMAL + "Teleported you to the Event: " +
                    ChatColor.RED + lobbyName);
        }else{
            sender.sendMessage(MsgType.ERROR + "Error. Invalid Event name!");
        }       
    }

    public void removeEvent(Player sender, String Eventname){
        FileConfiguration c = p.newConfigz;
 
        if (c.contains("Events." + Eventname)){
            c.set("Events." + Eventname, null);
            p.saveNewConfig();
            sender.sendMessage(MsgType.NORMAL + "Successfully removed the Event: " + ChatColor.RED + Eventname);
        }else{
            sender.sendMessage(MsgType.ERROR + "No Event found by the name: " +
                    ChatColor.YELLOW + Eventname);
        }
    }
    
    
    public void listEvents(Player sender){
    	FileConfiguration c = p.newConfigz;
    	
    	if (c.contains("Events.")) {
    		sender.sendMessage(MsgType.NORMAL + "Your Events are: " + ChatColor.DARK_PURPLE + c.getConfigurationSection("Events").getKeys(false));
    	}else {
    		sender.sendMessage(MsgType.ERROR + "You didnt have set any Events!");
    		}
    	}
    }