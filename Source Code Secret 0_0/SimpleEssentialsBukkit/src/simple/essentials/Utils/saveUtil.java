package simple.essentials.Utils;

import org.bukkit.Location;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import simple.essentials.Main;

public class saveUtil {
	
	public static void saveLocationToConfig(String path, Location loc, Player p) {
		String x = Integer.toString(p.getLocation().getBlockX());
		String y = Integer.toString(p.getLocation().getBlockY());
		String z = Integer.toString(p.getLocation().getBlockZ());
		String pitch = Float.toString(p.getLocation().getPitch());
		String yaw = Float.toString(p.getLocation().getYaw());
		String world = p.getWorld().getName();
		
		Main.playerConfig.set(path + ".lastLocation.x", x);
		Main.playerConfig.set(path + ".lastLocation.y", y);
		Main.playerConfig.set(path + ".lastLocation.z", z);
		Main.playerConfig.set(path + ".lastLocation.yaw", yaw);
		Main.playerConfig.set(path + ".lastLocation.pitch", pitch);
		Main.playerConfig.set(path + ".lastLocation.world", world);
		Main.saveCustomPlayerConfig();
	}
	
	public static void setBanReason(String path, String reason, OfflinePlayer p) {
		Main.playerConfig.set(path + ".banReason", reason);
		Main.saveCustomPlayerConfig();
	}

}
