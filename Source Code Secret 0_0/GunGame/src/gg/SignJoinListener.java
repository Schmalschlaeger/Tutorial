package gg;

import org.bukkit.ChatColor;
import org.bukkit.block.Sign;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.SignChangeEvent;
import org.bukkit.event.player.PlayerInteractEvent;

public class SignJoinListener implements Listener {
	
	static {//Updating Signs
		//Location yourLocation  = new Location(world,x,y,z);
		//Block block = yourLocation.getBlock();
		//if(block.getState() instanceof Sign){
		  //Sign sign = (Sign)block.getState();
		  ////sign.setLine(0, "Line 1");
		  //sign.update();
		//}
	}
	
	@EventHandler
	public void onSignClick(PlayerInteractEvent e) {		
		if (!(e.getAction() == Action.RIGHT_CLICK_BLOCK)) return;
        if (e.getClickedBlock().getState() instanceof Sign) {
                Sign s = (Sign) e.getClickedBlock().getState();
                if (s.getLine(0).equalsIgnoreCase("§b[GunGame]")) {
                	if (s.getLine(3).equalsIgnoreCase("1")) {
                    		GunMinigameListener.getManager().addPlayer(e.getPlayer(), 1);
                	}else if (s.getLine(3).equalsIgnoreCase("2")) {
                		GunMinigameListener.getManager().addPlayer(e.getPlayer(), 2);
                	}else if (s.getLine(3).equalsIgnoreCase("3")) {
                		GunMinigameListener.getManager().addPlayer(e.getPlayer(), 3);
                    	}else if (s.getLine(2).equalsIgnoreCase("§cLeave Arena")) {
                    		GunMinigameListener.getManager().removePlayer(e.getPlayer());                    		
                    	}
					}
				}
			}
	
	@EventHandler
    public void onSignChange(SignChangeEvent e){
        if (e.getLine(0).equalsIgnoreCase("[GunGame]")){
       	 if (e.getLine(1).equalsIgnoreCase("1")){
             e.setLine(0, "§b[GunGame]");
             e.setLine(1, "Click to join");
             e.setLine(2, "Arena");
             e.setLine(3, "1");
         	}else if (e.getLine(1).equalsIgnoreCase("2")){
                e.setLine(0, "§b[GunGame]");
                e.setLine(1, "Click to join");
                e.setLine(2, "Arena");
                e.setLine(3, "2");
         	}else if (e.getLine(1).equalsIgnoreCase("3")){
                e.setLine(0, "[GunGame]");
                e.setLine(1, "Click to join");
                e.setLine(2, "Arena");
                e.setLine(3, "3");
         	}else if (e.getLine(1).equalsIgnoreCase("leave")){
         		e.setLine(0, "§b[GunGame]");
         		e.setLine(1, "");
                e.setLine(2, "§cLeave Arena");
         	}else {
         		e.getPlayer().sendMessage(ChatColor.RED + "Error, during checking the lines!");
         		e.getBlock().breakNaturally();
         	}
        }
	}

}
