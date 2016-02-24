package PVPGame.Listeners;

import org.bukkit.ChatColor;
import org.bukkit.block.Sign;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.SignChangeEvent;
import org.bukkit.event.player.PlayerInteractEvent;

import PVPGame.Utils.TeamManager;

public class SignListener implements Listener{
	
	@EventHandler
	public void onSignClick(PlayerInteractEvent e) {	
		if (!(e.getAction() == Action.RIGHT_CLICK_BLOCK)) return;
        if (e.getClickedBlock().getState() instanceof Sign) {
                Sign s = (Sign) e.getClickedBlock().getState();
                if (s.getLine(0).equalsIgnoreCase("§3[PVPZ]")) {
                	if (s.getLine(2).equalsIgnoreCase("§4§lRed")) {
                		TeamManager.addToTeam(e.getPlayer(), "red");
                	}else if (s.getLine(2).equalsIgnoreCase("§1§lBlue")) {
                		TeamManager.addToTeam(e.getPlayer(), "blue");
                	}else if (s.getLine(2).equalsIgnoreCase("§lRandom")) {
                		TeamManager.addToTeam(e.getPlayer(), "random");
                	}
                	else if (s.getLine(1).equalsIgnoreCase("§c§lTeams")) {
                		//e.getPlayer().sendMessage("SETTING TEAMS!");
                		//GameManager.getManager().setTeams();
                	}
                }
        	}
        }
	
	@EventHandler
    public void onSignChange(SignChangeEvent e){
    if (e.getLine(0).equalsIgnoreCase("[lobby]")){
        e.setLine(0, "§3[lobby]");
        e.setLine(1, "Back to");
        e.setLine(2, "the hub");
   	}else if (e.getLine(0).equalsIgnoreCase("[PVPGame]")){
       	 if (e.getLine(1).equalsIgnoreCase("red")){
           e.setLine(0, "§3[PVPZ]");
           e.setLine(1, "§0Join Team");
           e.setLine(2, "§4§lRed");
       	}else if (e.getLine(1).equalsIgnoreCase("blue")){
            e.setLine(0, "§3[PVPZ]");
            e.setLine(1, "§0Join Team");
            e.setLine(2, "§1§lBlue");
       	}else if (e.getLine(1).equalsIgnoreCase("random")){
            e.setLine(0, "§3[PVPZ]");
            e.setLine(1, "§0Join Team");
            e.setLine(2, "§lRandom");
       	}else if (e.getLine(1).equalsIgnoreCase("teams")){
            e.setLine(0, "§3[PVPZ]");
            e.setLine(1, "§c§lTeams");
       	}else {
       		e.getPlayer().sendMessage(ChatColor.RED + "Sign name not found! Try another!");
      		 e.getBlock().breakNaturally(); 		 
       	}
        }
	}

}
