package plugin;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.event.PostLoginEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.api.plugin.Plugin;
import net.md_5.bungee.event.EventHandler;

public class Main extends Plugin implements Listener{
	
	public void onEnable() {
		ProxyServer.getInstance().getPluginManager().registerListener(this, this);
		ProxyServer.getInstance().getPluginManager().registerListener(this, new ServerListener());
		getProxy().getPluginManager().registerCommand(this, new HubCommand());
	}
	 
	@EventHandler
	public void KickServerEvent(final PostLoginEvent e) {
				for (int i = 0; i < 44; i++) {
			e.getPlayer().sendMessage(new TextComponent(" "));
			e.getPlayer().sendMessage(new TextComponent(" "));
			e.getPlayer().sendMessage(new TextComponent(" "));
		}
		e.getPlayer().sendMessage(new TextComponent(ChatColor.GOLD + "" + ChatColor.BOLD + "============================================"));
		e.getPlayer().sendMessage(new TextComponent(ChatColor.RED + "            Welcome to " + ChatColor.AQUA + "The" + ChatColor.DARK_AQUA + "Network " + ChatColor.RED + e.getPlayer().getName()));
		e.getPlayer().sendMessage(new TextComponent(ChatColor.BLUE + "     We have: " + ChatColor.RED + "Minigames, Extreme Survival, PVP"));
		e.getPlayer().sendMessage(new TextComponent(ChatColor.BLUE + "     Choose an server with your compass"));
		e.getPlayer().sendMessage(new TextComponent(ChatColor.BLUE + "     or use an portal to go to an server you like."));
		e.getPlayer().sendMessage(new TextComponent(ChatColor.BLUE + "     You can also subscribe to our youtube channel"));
		e.getPlayer().sendMessage(new TextComponent(ChatColor.RED + "      http://www.youtube.com/user/JusJusCrafti"));
		e.getPlayer().sendMessage(new TextComponent(ChatColor.BLUE + "     Or donate:" + ChatColor.RED + "" + ChatColor.ITALIC + " 'Coming Soon'"));
		e.getPlayer().sendMessage(new TextComponent(ChatColor.GOLD + "" + ChatColor.BOLD + "============================================"));
	}

	
	public static void send(ProxiedPlayer to, String server) {
	    to.connect(ProxyServer.getInstance().getServerInfo(server));
	}
	
}
