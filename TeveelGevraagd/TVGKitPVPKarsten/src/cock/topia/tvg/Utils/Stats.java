package cock.topia.tvg.Utils;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import cock.topia.tvg.Main;

public class Stats {
	
	Main plugin;
	public Stats(Main plugin) {
		this.plugin = plugin;
	}
	
	String[] staffRanks = new String[]{
			"tvgcrew", "headdeveloper", "developer", "teamleider", "kaashosting", "adminplus", "admin", "moderatorteamleider", "moderatorplus", "moderator",
			 "helperteamleider", "helperplus", "helper", "testbuilder", "builder", "builderplus", "builderplus", "builderteamleider"
	};
	
	String[] playerRanks = new String[]{
			"wood", "coal", "lapis", "iron", "redstone", "gold", "diamond", "emerald", "cock"
	};
	
	public boolean isStaff(Player p) {
		for (String ranks : staffRanks) {
			if (plugin.playerInGroup(Main.lobbyWorld.getName(), p, ranks)) {
				return true;
			}
		}
		return false;
	}
	
	public boolean isInRank(Player p) {
		for (String ranks : playerRanks) {
			if (plugin.playerInGroup(Main.lobbyWorld.getName(), p, ranks)) {
				return true;
			}
		}
		return false;
	}
	
	public ChatColor getRankMessageColor(Player p) {
		if (isStaff(p)){
			return ChatColor.WHITE;
		}else {
			return ChatColor.GRAY;
		}
	}
	
	public ChatColor getRankColor(Player p) {
		  if (plugin.playerInGroup(Main.lobbyWorld.getName(), p, "tvgcrew")) {
			  return ChatColor.DARK_RED;
		  }else if (plugin.playerInGroup(Main.lobbyWorld.getName(), p, "headdeveloper")) {
			  return ChatColor.DARK_RED;
		  }else if (plugin.playerInGroup(Main.lobbyWorld.getName(), p, "developer")) {
			  return ChatColor.DARK_RED;
		  }else if (plugin.playerInGroup(Main.lobbyWorld.getName(), p, "kaashosting")) {
			  return ChatColor.YELLOW;
		  }else if (plugin.playerInGroup(Main.lobbyWorld.getName(), p, "teamleider")) {
			  return ChatColor.GOLD;
		  }else if (plugin.playerInGroup(Main.lobbyWorld.getName(), p, "adminteamleider")) {
			  return ChatColor.RED;
		  }else if (plugin.playerInGroup(Main.lobbyWorld.getName(), p, "adminplus")) {
			  return ChatColor.RED;
		  }else if (plugin.playerInGroup(Main.lobbyWorld.getName(), p, "admin")) {
			  return ChatColor.RED;
		  }else if (plugin.playerInGroup(Main.lobbyWorld.getName(), p, "moderatorteamleider")) {
			  return ChatColor.BLUE;
		  }else if (plugin.playerInGroup(Main.lobbyWorld.getName(), p, "moderatorplus")) {
			  return ChatColor.BLUE;
		  }else if (plugin.playerInGroup(Main.lobbyWorld.getName(), p, "moderator")) {
			  return ChatColor.BLUE;
		  }else if (plugin.playerInGroup(Main.lobbyWorld.getName(), p, "helperteamleider")) {
			  return ChatColor.YELLOW;
		  }else if (plugin.playerInGroup(Main.lobbyWorld.getName(), p, "helperplus")) {
			  return ChatColor.YELLOW;
		  }else if (plugin.playerInGroup(Main.lobbyWorld.getName(), p, "helper")) {
			  return ChatColor.YELLOW;
		  }else if (plugin.playerInGroup(Main.lobbyWorld.getName(), p, "testbuilder")) {
			  return ChatColor.GREEN;
		  }else if (plugin.playerInGroup(Main.lobbyWorld.getName(), p, "builder")) {
			  return ChatColor.GREEN;
		  }else if (plugin.playerInGroup(Main.lobbyWorld.getName(), p, "builderplus")) {
			  return ChatColor.GREEN;
		  }else if (plugin.playerInGroup(Main.lobbyWorld.getName(), p, "builderteamleider")) {
			  return ChatColor.GREEN;
			  
		  }else if (plugin.playerInGroup(Main.lobbyWorld.getName(), p, "youtube")) {
			  return ChatColor.WHITE;
		      }else if (plugin.playerInGroup(Main.lobbyWorld.getName(), p, "emerald")) {
				  return ChatColor.GREEN;
		      }else if (plugin.playerInGroup(Main.lobbyWorld.getName(), p, "cock")) {
				  return ChatColor.LIGHT_PURPLE;
		      }else if (plugin.playerInGroup(Main.lobbyWorld.getName(), p, "diamond")) {
				  return ChatColor.AQUA;
			  }else if (plugin.playerInGroup(Main.lobbyWorld.getName(), p, "gold")) {
				  return ChatColor.YELLOW;
			  }else if (plugin.playerInGroup(Main.lobbyWorld.getName(), p, "redstone")) {
				  return ChatColor.DARK_RED;
			  }else if (plugin.playerInGroup(Main.lobbyWorld.getName(), p, "iron")) {
				  return ChatColor.WHITE;
			  }else if (plugin.playerInGroup(Main.lobbyWorld.getName(), p, "lapis")) {
				  return ChatColor.DARK_BLUE;
			  }else if (plugin.playerInGroup(Main.lobbyWorld.getName(), p, "coal")) {
				  return ChatColor.DARK_GRAY;
			  }else if (plugin.playerInGroup(Main.lobbyWorld.getName(), p, "wood")) {
				  return ChatColor.GOLD;
			  }else {
				  return ChatColor.GRAY;
			  }
		}
	
	public String getRank(Player p) {
	  if (plugin.playerInGroup(Main.lobbyWorld.getName(), p, "tvgcrew")) {
		  return ChatColor.LIGHT_PURPLE + "" + ChatColor.BOLD + "TVG" + ChatColor.DARK_RED + "" + ChatColor.BOLD;
	  }else if (plugin.playerInGroup(Main.lobbyWorld.getName(), p, "headdeveloper")) {
		  return ChatColor.DARK_RED + "" + ChatColor.BOLD + "Developer" + ChatColor.DARK_AQUA + "" + ChatColor.BOLD + "TL" + ChatColor.DARK_PURPLE;
	  }else if (plugin.playerInGroup(Main.lobbyWorld.getName(), p, "developer")) {
		  return ChatColor.DARK_RED + "" + ChatColor.BOLD + "Developer" + ChatColor.DARK_PURPLE;
	  }else if (plugin.playerInGroup(Main.lobbyWorld.getName(), p, "teamleider")) {
		  return ChatColor.GOLD + "" + ChatColor.BOLD + "TeamLeider" + ChatColor.AQUA;
	  }else if (plugin.playerInGroup(Main.lobbyWorld.getName(), p, "kaashosting")) {
		  return ChatColor.YELLOW + "" + ChatColor.BOLD + "KaasHosting" + ChatColor.RED;
	  }else if (plugin.playerInGroup(Main.lobbyWorld.getName(), p, "adminteamleider")) {
		  return ChatColor.RED + "" + ChatColor.BOLD + "Admin" + ChatColor.DARK_AQUA + "" + ChatColor.BOLD + "TL" + ChatColor.GOLD;
	  }else if (plugin.playerInGroup(Main.lobbyWorld.getName(), p, "adminplus")) {
		  return ChatColor.RED + "" + ChatColor.BOLD + "Admin" + ChatColor.DARK_AQUA + "" + ChatColor.BOLD + "+" + ChatColor.GOLD;
	  }else if (plugin.playerInGroup(Main.lobbyWorld.getName(), p, "admin")) {
		  return ChatColor.RED + "" + ChatColor.BOLD + "Admin" + ChatColor.GOLD;
	  }else if (plugin.playerInGroup(Main.lobbyWorld.getName(), p, "moderatorteamleider")) {
		  return ChatColor.BLUE + "" + ChatColor.BOLD + "Mod" + ChatColor.DARK_AQUA + "" + ChatColor.BOLD + "TL" + ChatColor.RED;
	  }else if (plugin.playerInGroup(Main.lobbyWorld.getName(), p, "moderatorplus")) {
		  return ChatColor.BLUE + "" + ChatColor.BOLD + "Mod" + ChatColor.DARK_AQUA + "" + ChatColor.BOLD + "+" + ChatColor.RED;
	  }else if (plugin.playerInGroup(Main.lobbyWorld.getName(), p, "moderator")) {
		  return ChatColor.BLUE + "" + ChatColor.BOLD + "Mod" + ChatColor.RED;
	  }else if (plugin.playerInGroup(Main.lobbyWorld.getName(), p, "helperteamleider")) {
		  return ChatColor.YELLOW + "" + ChatColor.BOLD + "Helper"  + ChatColor.DARK_AQUA + "" + ChatColor.BOLD + "TL" + ChatColor.BLUE;
	  }else if (plugin.playerInGroup(Main.lobbyWorld.getName(), p, "helperplus")) {
		  return ChatColor.YELLOW + "" + ChatColor.BOLD + "Helper" + ChatColor.DARK_AQUA + "" + ChatColor.BOLD + "+" + ChatColor.BLUE;
	  }else if (plugin.playerInGroup(Main.lobbyWorld.getName(), p, "helper")) {
		  return ChatColor.YELLOW + "" + ChatColor.BOLD + "Helper" + ChatColor.BLUE;
	  }else if (plugin.playerInGroup(Main.lobbyWorld.getName(), p, "testbuilder")) {
		  return ChatColor.GREEN + "" + ChatColor.BOLD + "§3§lTest§f-§a§lBuilder" + ChatColor.LIGHT_PURPLE;
	  }else if (plugin.playerInGroup(Main.lobbyWorld.getName(), p, "builder")) {
		  return ChatColor.GREEN + "" + ChatColor.BOLD + "Builder" + ChatColor.LIGHT_PURPLE;
	  }else if (plugin.playerInGroup(Main.lobbyWorld.getName(), p, "builderplus")) {
		  return ChatColor.GREEN + "" + ChatColor.BOLD + "Builder"  + ChatColor.DARK_AQUA + "" + ChatColor.BOLD + "+" + ChatColor.LIGHT_PURPLE;
	  }else if (plugin.playerInGroup(Main.lobbyWorld.getName(), p, "builderteamleider")) {
		  return ChatColor.GREEN + "" + ChatColor.BOLD + "Builder"  + ChatColor.DARK_AQUA + "" + ChatColor.BOLD + "TL" + ChatColor.LIGHT_PURPLE;
		  
	  }else if (plugin.playerInGroup(Main.lobbyWorld.getName(), p, "youtube")) {
		  return ChatColor.WHITE + "" + ChatColor.BOLD + "You" + ChatColor.RED + "" + ChatColor.BOLD + "tube " + ChatColor.WHITE;
	  }else if (plugin.playerInGroup(Main.lobbyWorld.getName(), p, "cock")) {
		  return ChatColor.DARK_RED + "" + ChatColor.MAGIC + "#" + ChatColor.LIGHT_PURPLE + "" + ChatColor.BOLD + "Cock" + ChatColor.DARK_RED 
				  + ChatColor.MAGIC + "#" + ChatColor.WHITE;
	      }else if (plugin.playerInGroup(Main.lobbyWorld.getName(), p, "emerald")) {
			  return ChatColor.GREEN + "Emerald" + ChatColor.WHITE;
	      }else if (plugin.playerInGroup(Main.lobbyWorld.getName(), p, "diamond")) {
			  return ChatColor.AQUA + "Diamond" + ChatColor.WHITE;
		  }else if (plugin.playerInGroup(Main.lobbyWorld.getName(), p, "gold")) {
			  return ChatColor.YELLOW + "Gold" + ChatColor.WHITE;
		  }else if (plugin.playerInGroup(Main.lobbyWorld.getName(), p, "redstone")) {
			  return ChatColor.DARK_RED + "Redstone" + ChatColor.WHITE;
		  }else if (plugin.playerInGroup(Main.lobbyWorld.getName(), p, "iron")) {
			  return ChatColor.WHITE + "Iron" + ChatColor.WHITE;
		  }else if (plugin.playerInGroup(Main.lobbyWorld.getName(), p, "lapis")) {
			  return ChatColor.DARK_BLUE + "Lapis" + ChatColor.WHITE;
		  }else if (plugin.playerInGroup(Main.lobbyWorld.getName(), p, "coal")) {
			  return ChatColor.DARK_GRAY + "Coal" + ChatColor.WHITE;
		  }else if (plugin.playerInGroup(Main.lobbyWorld.getName(), p, "wood")) {
			  return ChatColor.GOLD + "Wood" + ChatColor.WHITE;
		  }else {
			  return ChatColor.GRAY + "Speler";
		  }
	}
	
	/*
	 * wood
	 * coal
	 * lapis
	 * iron
	 * redstone
	 * gold
	 * diamond
	 * emerald
	 */

}
