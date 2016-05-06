package cock.topia.tvg.Utils;

import org.bukkit.event.Listener;

import cock.topia.tvg.Main;

public class RankScoreboard  implements Listener{
	
	Main plugin;
	
	public RankScoreboard(Main plugin) {
		this.plugin = plugin;
	}
	
	  /*@EventHandler
	  public void onNameTag(AsyncPlayerReceiveNameTagEvent e) {
		  Player p = e.getNamedPlayer();
	      if (plugin.playerInGroup(Main.lobbyWorld.getName(), p, "developer") || plugin.playerInGroup(Main.lobbyWorld.getName(), p, "tvgcrew")) {
		  e.setTag(ChatColor.DARK_RED + "" + ChatColor.BOLD +  e.getNamedPlayer().getName());
		  }else if (plugin.playerInGroup(Main.lobbyWorld.getName(), p, "admin") || plugin.playerInGroup(Main.lobbyWorld.getName(), p, "adminplus")
				  || plugin.playerInGroup(Main.lobbyWorld.getName(), p, "adminteamleider") || plugin.playerInGroup(Main.lobbyWorld.getName(), p, "kaashosting")) {
			  e.setTag(ChatColor.RED + "" + ChatColor.BOLD +  e.getNamedPlayer().getName());
		  }else if (plugin.playerInGroup(Main.lobbyWorld.getName(), p, "moderator") || plugin.playerInGroup(Main.lobbyWorld.getName(), p, "moderatorplus")
				  || plugin.playerInGroup(Main.lobbyWorld.getName(), p, "moderatorteamleider")) {
			  e.setTag(ChatColor.BLUE + "" + ChatColor.BOLD +  e.getNamedPlayer().getName());
		  }else if (plugin.playerInGroup(Main.lobbyWorld.getName(), p, "helper") || plugin.playerInGroup(Main.lobbyWorld.getName(), p, "helperplus")
				  || plugin.playerInGroup(Main.lobbyWorld.getName(), p, "helperteamleider")) {
			  e.setTag(ChatColor.YELLOW + "" + ChatColor.BOLD +  e.getNamedPlayer().getName());
			  
		  }else if (plugin.playerInGroup(Main.lobbyWorld.getName(), p, "bouwteam")) {
			  e.setTag(ChatColor.GREEN + "" + ChatColor.BOLD +  e.getNamedPlayer().getName());
		  }else if (plugin.playerInGroup(Main.lobbyWorld.getName(), p, "youtube") || plugin.playerInGroup(Main.lobbyWorld.getName(), p, "teamleider")) {
			  e.setTag(ChatColor.GOLD + "" + ChatColor.BOLD +  e.getNamedPlayer().getName());
		  }else if (plugin.playerInGroup(Main.lobbyWorld.getName(), p, "cock")) {
			  e.setTag(ChatColor.LIGHT_PURPLE + "" + e.getNamedPlayer().getName());
		  }else if (plugin.playerInGroup(Main.lobbyWorld.getName(), p, "emerald")) {
			  e.setTag(ChatColor.GREEN + "" + e.getNamedPlayer().getName());
		  }else if (plugin.playerInGroup(Main.lobbyWorld.getName(), p, "diamond")) {
			  e.setTag(ChatColor.AQUA + "" + e.getNamedPlayer().getName());
		  }else if (plugin.playerInGroup(Main.lobbyWorld.getName(), p, "gold")) {
			  e.setTag(ChatColor.YELLOW + "" + e.getNamedPlayer().getName());
		  }else if (plugin.playerInGroup(Main.lobbyWorld.getName(), p, "redstone")) {
			  e.setTag(ChatColor.RED + "" + e.getNamedPlayer().getName());
		  }else if (plugin.playerInGroup(Main.lobbyWorld.getName(), p, "iron")) {
			  e.setTag(ChatColor.WHITE + "" + e.getNamedPlayer().getName());
		  }else if (plugin.playerInGroup(Main.lobbyWorld.getName(), p, "lapis")) {
			  e.setTag(ChatColor.DARK_BLUE + "" + e.getNamedPlayer().getName());
		  }else if (plugin.playerInGroup(Main.lobbyWorld.getName(), p, "coal")) {
			  e.setTag(ChatColor.DARK_GRAY + "" + e.getNamedPlayer().getName());
	      }else if (plugin.playerInGroup(Main.lobbyWorld.getName(), p, "wood")) {
			  e.setTag(ChatColor.GOLD + "" + e.getNamedPlayer().getName());
		  }else if (plugin.playerInGroup(Main.lobbyWorld.getName(), p, "speler")) {
			  e.setTag(ChatColor.GRAY + "" + e.getNamedPlayer().getName());
		  }
	}
	*/
	  
}