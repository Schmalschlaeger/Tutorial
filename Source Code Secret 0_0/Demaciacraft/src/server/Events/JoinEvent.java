package server.Events;

import java.util.UUID;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import server.Main;
import server.MessageManager;
import server.Achievements.Achievements;
import server.Inventory.createItems;
import server.Systems.InfoBot;

public class JoinEvent implements Listener{
	
	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent e) {
		Player p = e.getPlayer();
		MessageManager.broadCastAEmptyLineOfText(e.getPlayer());
		
		createItems.createSlot9Item(p);
		
		UUID pUUID = p.getUniqueId();
		Main.infoBotConfig.set("players." + pUUID + ".name", p.getName());
		Main.saveCustomPlayerConfig();
		
		Main.AchievementConfig.set("players." + pUUID + ".name", p.getName());
		Main.saveCustomAchievementConfig();
		
		Achievements.createAchievementConfig(p);
		
			 if (Main.infoBotConfig.getString("players." + p.getUniqueId() + ".disabled") == null) {
				 Main.infoBotConfig.set("players." + p.getUniqueId() + ".disabled", false);		
				 Main.saveCustomPlayerConfig();
			 }
		
			 if (InfoBot.isDisabled(pUUID) == true) {
				 p.sendMessage("    ");
				 p.sendMessage(InfoBot.infoBotMessage);
				 p.sendMessage(ChatColor.GOLD + "Wait, i can remember your name! " + ChatColor.RED + p.getName().toUpperCase() + ChatColor.GOLD + 
						 "! HAHAHAHA, are you not tired that im ruin your life?");
				 p.sendMessage(ChatColor.GOLD + "Earlier i was saving your ass, but now. I should watch out for me! MHUHAHAHA");
				 p.sendMessage("    ");
			 }else {
				 	p.sendMessage("    ");
					p.sendMessage(InfoBot.infoBotMessage);
					p.sendMessage(ChatColor.GOLD + "Welcome " + p.getName() + " to DemaciaCraft. Just remember if you need help use " + ChatColor.RED + "/infobot " + 
					ChatColor.GOLD + "or" + ChatColor.RED + " /help");
					p.sendMessage(ChatColor.GOLD + "I'm always with you to support you ;)");
					p.sendMessage("    ");
			 }
		
	}

}
