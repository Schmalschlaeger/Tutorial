package mazehunter.utils;

import org.bukkit.block.Sign;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.SignChangeEvent;
import org.bukkit.event.player.PlayerInteractEvent;

public class SignListener implements Listener{
	
//	private startCountdown gameManager = new startCountdown(null);
	
	@EventHandler
	public void onSignClick(PlayerInteractEvent e) {	
		if (!(e.getAction() == Action.RIGHT_CLICK_BLOCK)) return;
        if (e.getClickedBlock().getState() instanceof Sign) {
                Sign s = (Sign) e.getClickedBlock().getState();
                if (s.getLine(0).equalsIgnoreCase("§3[MHunter]")) {
                	if (s.getLine(2).equalsIgnoreCase(">-Join-<")) {
  //              		gameManager.players.add(e.getPlayer());
 //               		gameManager.addPlayer(e.getPlayer());
//                		gameManager.gameManagerTask();
                	}
                 }
             }
        }
	
	@EventHandler
    public void onSignChange(SignChangeEvent e){
    if (e.getLine(0).equalsIgnoreCase("[MHunter]")){
    	if (e.getLine(1).equalsIgnoreCase("join")) {
        e.setLine(0, "§3[MHunter]");
        e.setLine(1, "");
        e.setLine(2, ">-Join-<");
    	}
    }
	}

}
