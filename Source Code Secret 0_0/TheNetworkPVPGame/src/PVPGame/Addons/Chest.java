package PVPGame.Addons;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
 
public class Chest {
 
	private Inventory inventory;
	private Block block;
	
	//location
	private String world;
	private int x, y, z;
	
	public Chest(Location location, Material material, String title, int size) {
		this.x = location.getBlockX();
		this.y = location.getBlockY();
		this.z = location.getBlockZ();
		this.world = location.getWorld().getName();
		
		location.getBlock().setType(material);
		this.block = location.getBlock();	
		this.inventory = Bukkit.getServer().createInventory(null, size, title);
	}
	
	public Block getBlock() {
		return block;
	}
	
	public Location getLocation() {
		return new Location(Bukkit.getWorld(world), x, y, z);
	}
	
	public Inventory getInventory() {
		return inventory;
	}
	
	public String getTitle() {
		return inventory.getTitle();
	}
	
	public int getSize() {
		return inventory.getSize();
	}
	
	public void setInventory(Inventory inventory) {
		this.inventory = inventory;
	}
	
	public void openInventory(Player player) {
		player.openInventory(this.inventory);
	}
	
	public void addItem(ItemStack itemstack) {
		this.inventory.addItem(itemstack);
	}
	
}