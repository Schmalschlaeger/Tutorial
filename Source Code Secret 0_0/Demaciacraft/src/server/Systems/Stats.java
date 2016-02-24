package server.Systems;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class Stats {
	
	public static String modPermissions = "demaciacraft.rank.mod";
	public static String admindPermissions = "demaciacraft.rank.admin";
	public static String miniAdminPermissions = "demaciacraft.rank.miniadmin";
	
	public static String getRank(Player p) {
		if (p.isOp()) {
			return ChatColor.RED + "Owner";
		}else if (p.hasPermission(modPermissions)){
			return ChatColor.GOLD + "Moderator";
		}else if (p.hasPermission(admindPermissions)){
			return ChatColor.RED + "Admin";
		}else if (p.hasPermission(miniAdminPermissions)){
			return ChatColor.YELLOW + "Mini Admin";
		}else {
			return ChatColor.GRAY + "Normal player";
		}
	}
	
}
