package tuts;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;



public class API {
	
	private Tutorial plugin;
	
	public API(Tutorial instance) {
		this.plugin = instance;
	}

	public void createWarp(Player sender, String warpname) {
		FileConfiguration c = plugin.newCustomConfig;
		Location loc = sender.getLocation();
		String world = sender.getWorld().getName();
		int x, y, z;
		float yaw, pitch;
		
		x = loc.getBlockX();
		y = loc.getBlockY();
		z = loc.getBlockZ();
		yaw = loc.getYaw();
		pitch = loc.getPitch();
		
		c.set("warps." + warpname + ".x", x);
		c.set("warps." + warpname + ".y", y);
		c.set("warps." + warpname + ".z", z);
		c.set("warps." + warpname + ".yaw", yaw);
		c.set("warps." + warpname + ".pitch", pitch);
		c.set("warps." + warpname + ".world", world);
		plugin.saveCustomConfig();
		
		sender.sendMessage(ChatColor.GOLD + "Created warp " + ChatColor.RED + warpname + ChatColor.GOLD + " in world " + ChatColor.RED + world);
	}
	
	public void teleportToWarp(Player sender, String warpname) {
		FileConfiguration c = plugin.newCustomConfig;
		int x, y, z, yaw, pitch;
		
		if (c.contains("warps." + warpname)) {
			World world = Bukkit.getServer().getWorld(c.getString("warps." + warpname + ".world"));
			x = c.getInt("warps." + warpname + ".x");
			y = c.getInt("warps." + warpname + ".y");
			z = c.getInt("warps." + warpname + ".z");
			yaw = c.getInt("warps." + warpname + ".yaw");
			pitch = c.getInt("warps." + warpname + ".pitch");
			
			sender.teleport(new Location(world, x, y ,z ,yaw ,pitch));
			sender.sendMessage(ChatColor.GOLD + "Teleported you to the warp " + ChatColor.RED + warpname);
			
		}else {
			sender.sendMessage(ChatColor.DARK_RED + "No warp name found with the warp " + warpname);
		}
	}
	
	public void listWarps(Player sender) {
		FileConfiguration c = plugin.newCustomConfig;
		
		if (c.contains("warps.")) {
			sender.sendMessage(ChatColor.GOLD + "Your warps: " + ChatColor.RED + c.getConfigurationSection("warps").getKeys(false));
		}else {
			sender.sendMessage(ChatColor.RED + "You dont have any warps!");
		}
	}
	
}
