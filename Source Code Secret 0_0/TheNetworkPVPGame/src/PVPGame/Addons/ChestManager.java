package PVPGame.Addons;

import java.util.ArrayList;
import java.util.Iterator;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;
 
public class ChestManager implements Listener {
 
	private ArrayList<Chest> chests;
	private Plugin plugin;
	
	//default values
	private String title;
	private int size;
	private Material material;
 
	public ChestManager(Plugin plugin, Material material, String title, int size) {
		this.plugin = plugin;
		chests = new ArrayList<Chest>();
		
		this.material = material;
		this.title = title;
		this.size = size;
		
		this.plugin.getServer().getPluginManager().registerEvents(this, plugin);
	}
	
	public void addChest(Chest chest) {
		chests.add(chest);
	}
	
	public void addChest(Location location, final ItemStack... content) {
		addChest(new Chest(location, material, title, size) {{
			for(ItemStack itemstack : content) {
				addItem(itemstack);
			}
		}});
	}
	
	public void addChest(Location location, String title, final ItemStack... content) {
		addChest(new Chest(location, material, title, size) {{
			for(ItemStack itemstack : content) {
				addItem(itemstack);
			}
		}});
	}
	
	public void addChest(Location location, String title, int size, final ItemStack... content) {
		addChest(new Chest(location, material, title, size) {{
			for(ItemStack itemstack : content) {
				addItem(itemstack);
			}
		}});
	}
	
	public void addChest(Location location, String title, int size, Material material, final ItemStack... content) {
		addChest(new Chest(location, material, title, size) {{
			for(ItemStack itemstack : content) {
				addItem(itemstack);
			}
		}});
	}
	
	public boolean removeChest(Location location) {
		
		Iterator<Chest> iterator = chests.iterator();
		
		while(iterator.hasNext()) {
			Chest c = iterator.next();
			if(equals(c.getLocation(), location)) {
				chests.remove(c);
				return true;
			}
		}
		return false;
	}
	
	public void clear() {
		chests.clear();
	}
	
	public ArrayList<Chest> getChests() {
		return chests;
	}
	
	public Chest getChest(Location location) {
		for(Chest chest : chests) {
			if(equals(chest.getLocation(), location)) return chest;
		}
		return null;
	}
	
	public boolean isChest(Location location) {
		return getChest(location) != null;	
	}
	
	//event
	@EventHandler
	private void onClick(PlayerInteractEvent e) {
		if(e.getAction() != Action.RIGHT_CLICK_BLOCK) return;
		if(e.getClickedBlock() == null) return;
		Chest c = getChest(e.getClickedBlock().getLocation());
		if(c == null) return;
		c.openInventory(e.getPlayer());
	}
	
	private boolean equals(Location a, Location b) {
		return (a.getBlockX() == b.getBlockX() && a.getBlockY() == b.getBlockY() && a.getBlockZ() == b.getBlockZ());
	}
}