package tuts;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChatEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

@SuppressWarnings("deprecation")
public class Tutorial extends JavaPlugin implements Listener{
	
	public ArrayList<String> antwoorden = new ArrayList<String>();
	
	public boolean answered = false;

	public void onEnable() { 	
		Bukkit.getServer().getPluginManager().registerEvents(this, this);
		
		antwoorden.add("jusjusgames");
		antwoorden.add("JusJusGames");
		antwoorden.add("jusjus games");
		
		getServer().getScheduler().scheduleSyncRepeatingTask(this, new Runnable() {
			@Override
			public void run() {
				Bukkit.broadcastMessage(ChatColor.GOLD + "[Quizz] " + ChatColor.AQUA + "Hoe heet het youtube kanaal van deze plugin?");
				answered = false;
			}
		}, 20 * 10, 20 * 10);
    }
	
	@EventHandler
	public void onPlayerChat(PlayerChatEvent e) {
		String message = e.getMessage();
		Player p = e.getPlayer();
		
		if (antwoorden.contains(message) && (!answered))  {
			answered = true;
			
			p.sendMessage(ChatColor.GOLD + "CORRECT: Enjoy your reward!");
			p.getInventory().addItem(new ItemStack(Material.GOLDEN_APPLE));
		}
	}
	
}