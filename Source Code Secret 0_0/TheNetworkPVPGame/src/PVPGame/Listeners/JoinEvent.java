package PVPGame.Listeners;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import PVPGame.Main;
import PVPGame.Utils.GameManager;
import PVPGame.Utils.MessageManager;
import PVPGame.Utils.MessageManager.MsgType;

public class JoinEvent implements Listener {

	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent e) {
		Player p = e.getPlayer();
		
		Main.setScoreBoard(p);
		Main.updateScoreboard(p);
		MessageManager.broadcastAEmptyLineOfText();
		GameManager.getManager().addPlayer(p);
		KitSelector.kitDefault.add(p.getName());
				
		Bukkit.broadcastMessage(ChatColor.GREEN + "+ " + MsgType.JOIN + p.getName() + " has joined the game! "
		+ "Players online now: " + ChatColor.AQUA + Bukkit.getServer().getOnlinePlayers().size());
		
		GameManager.getManager().setItems(p);
		
		if (Main.isIngame() == true) {
			p.sendMessage(MsgType.SENDMESSAGE + "" + ChatColor.AQUA + "Welcome to our PVP game, please wait before the game is finished! Or spectate this running game");
		}else {
			p.sendMessage(MsgType.SENDMESSAGE + "Welcome to this game, choose your kit and prepare for the battle!");
		}
	}
}
