package tuts;


import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.plugin.java.JavaPlugin;
 
public class Tutorial extends JavaPlugin implements Listener {
	
	public void onEnable() {  
        Bukkit.getServer().getPluginManager().registerEvents(this, this);     
    }
	
	@EventHandler
	public void onPlayerWalk(PlayerMoveEvent e) {
		if (e.getFrom().getBlockX() == e.getTo().getBlockX() && e.getFrom().getBlockY() == e.getTo().getBlockY() && e.getFrom().getBlockZ() == e.getTo().getBlockZ()) return;
			
		StatsManager.addWalkedBlocks(e.getPlayer().getName(), 1);
	}
	
	@EventHandler
	public void onPlayerKill(PlayerDeathEvent e) {
		if (e.getEntity() instanceof Player) {
			if (e.getEntity().getKiller() instanceof Player) {
				Player killer = e.getEntity().getKiller();
				
				StatsManager.addPlayerKills(killer.getName(), 1);
			}
		}
	}
	
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args){		
		if (cmd.getName().equalsIgnoreCase("stats")) {
			sender.sendMessage(ChatColor.GOLD + "Your player kills: " + ChatColor.RED + StatsManager.getPlayerKills(sender.getName()) 
					+ ChatColor.GOLD + "Your walked blocks: " + ChatColor.RED + StatsManager.getBlocksWalked(sender.getName()));
			return true;
		}
		return false;
	}
	
}