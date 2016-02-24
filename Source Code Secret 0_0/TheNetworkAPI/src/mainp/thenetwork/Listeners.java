package mainp.thenetwork;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

public class Listeners implements Listener{
	
	PermissionsManager perms = new PermissionsManager();
	
	private boolean disableBlockBreakEvent = false;
	
	@EventHandler
	public void onPlayerBlockBreak(BlockBreakEvent e) {
		if (disableBlockBreakEvent == true && e.getPlayer().hasPermission(perms.getStaffPermissions())) {
			e.setCancelled(true);
		}
	}

}
