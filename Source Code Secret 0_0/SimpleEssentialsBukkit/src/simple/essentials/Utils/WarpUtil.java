package simple.essentials.Utils;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

import simple.essentials.Main;
import simple.essentials.Utils.MessageManager.MsgType;
 
public class WarpUtil implements Listener {

    public void createWarpy(Player sender, String wname){
        FileConfiguration c = Main.newConfigz;
    	String warpName = ("" + wname.charAt(0)).toUpperCase() + wname.substring(1);
        Location loc = sender.getLocation();
        String world = sender.getWorld().getName();
        int x, y, z;
        float yaw, pitch;
 
        x = loc.getBlockX();
        y = loc.getBlockY();
        z = loc.getBlockZ();
        yaw = loc.getYaw();
        pitch = loc.getPitch();

        c.set("warps." + warpName + ".x", x);
        c.set("warps." + warpName + ".y", y);
        c.set("warps." + warpName + ".z", z);
        c.set("warps." + warpName + ".yaw", yaw);
        c.set("warps." + warpName + ".pitch", pitch);
        c.set("warps." + warpName + ".world", world);
        Main.saveCustomConfig();
 
        sender.sendMessage(MsgType.SENDMESSAGE + "" + ChatColor.GREEN + "Created warp " +
                ChatColor.RED + warpName + ChatColor.AQUA + " at " + ChatColor.RED + x + ", " + y + ", " + z + ChatColor.GREEN + " in world " + "'" + ChatColor.RED + sender.getWorld().getName() + ChatColor.GREEN + "'");
    }

    public void teleportToWarp(Player sender, String wname){
    	String warpName = ("" + wname.charAt(0)).toUpperCase() + wname.substring(1);
        FileConfiguration c = Main.newConfigz;
        int x, y, z, yaw, pitch;
 
        if (c.contains("warps." + warpName)){
            World world = Bukkit.getServer().getWorld(c.getString("warps." + warpName + ".world"));
            x = c.getInt("warps." + warpName + ".x");
            y = c.getInt("warps." + warpName + ".y");
            z = c.getInt("warps." + warpName + ".z");
            yaw = c.getInt("warps." + warpName + ".yaw");
            pitch = c.getInt("warps" + warpName + ".pitch");
 
            sender.teleport(new Location(world, x, y, z, yaw, pitch));
            sender.sendMessage(MsgType.SENDMESSAGE + "" + ChatColor.LIGHT_PURPLE + "Teleported you to the warp: " +
                    ChatColor.RED + warpName);
        }else{
            sender.sendMessage(MsgType.SENDMESSAGE + "" + ChatColor.RED + "Error. Invalid warp name!");
        }       
    }

    public void removeWarp(Player sender, String wname){
        FileConfiguration c = Main.newConfigz;
    	String warpName = ("" + wname.charAt(0)).toUpperCase() + wname.substring(1);
 
        if (c.contains("warps." + warpName)){
            c.set("warps." + warpName, null);
            Main.saveCustomConfig();
            sender.sendMessage(MsgType.SENDMESSAGE + "" + ChatColor.LIGHT_PURPLE + "Successfully removed the warp: " + ChatColor.RED + warpName);
        }else{
            sender.sendMessage(MsgType.SENDMESSAGE + "" + ChatColor.RED + "No warp found by the name: " +
                    ChatColor.YELLOW + warpName);
        }
    }
    
    public void listWarps(Player sender){
    	FileConfiguration c = Main.newConfigz;
    	
    	if (c.contains("warps.")) {
    		sender.sendMessage(MsgType.SENDMESSAGE + "" + ChatColor.LIGHT_PURPLE +  "Your warps are: " + ChatColor.DARK_PURPLE + c.getConfigurationSection("warps.").getKeys(false));
    	}else {
    		sender.sendMessage(MsgType.SENDMESSAGE + "" + ChatColor.RED + "You didnt have set any warps! Use /createwarp to get started!");
    		}
    	}
    }