package Recreatie;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.Event.Result;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;

public class Restaurant implements Listener {	
	
	@SuppressWarnings("deprecation")
	@EventHandler
	public void onPlayerStairClick(PlayerInteractEvent e) {
		Block block = e.getClickedBlock();
		Player player = e.getPlayer();
		if (e.hasBlock() && e.getAction() == Action.RIGHT_CLICK_BLOCK) {
			if (e.getClickedBlock().getType() == Material.WOOD_STAIRS) {
				if (e.getPlayer().isOnGround()) {
				//Vector vec = new Vector(e.getClickedBlock().getX(), e.getClickedBlock().getY(), e.getClickedBlock().getZ());
				/*Arrow a = e.getClickedBlock().getLocation().getWorld().spawnArrow(
						e.getClickedBlock().getLocation(),
						vec,
						e.getClickedBlock().getLocation().getPitch(),
						e.getClickedBlock().getLocation().getYaw());
				*/
					if(player.getVehicle() != null)
						player.getVehicle().remove();
					
				Item drop = dropSeat(block);
				List<Item> drops = checkChair(drop);

				if (drops != null) {
					drop.remove();
					return;
				}
				
				drop.setItemStack(new ItemStack(Material.PUMPKIN_STEM));
				drop.setPassenger(e.getPlayer());
				
				e.setUseInteractedBlock(Result.DENY);
				}
				
			}
		}
	}
	
	private Item dropSeat(Block chair) {
		Location location = chair.getLocation().add(0.5, (0.7 - 0.5), 0.5);
		Item drop = location.getWorld().dropItemNaturally(location, new ItemStack(Material.WOOD_STAIRS));
		drop.setPickupDelay(Integer.MAX_VALUE);
		drop.teleport(location);
		drop.setVelocity(new Vector(0, 0, 0));
		return drop;
	}
	
	private List<Item> checkChair(Item drop) {
		List<Item> drops = new ArrayList<Item>();

		// Check for already existing chair items.
		for(Entity e : drop.getNearbyEntities(0.2, 0.2, 0.2)) {
			if(e != null && e instanceof Item && e.getPassenger() != null)
				drops.add(drop);
		}

		if(drops.isEmpty() == false)
			return drops;

		return null;
	}
}