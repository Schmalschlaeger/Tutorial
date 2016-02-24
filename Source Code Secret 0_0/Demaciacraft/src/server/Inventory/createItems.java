package server.Inventory;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Skull;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import server.Main;

public class createItems {
	
	public static void createSlot9Item(Player player) {
		Inventory inv = player.getInventory();
		
		ItemStack chest2 = new ItemStack(Material.SIGN);
		ItemMeta plate2 = chest2.getItemMeta();
		plate2.setDisplayName(ChatColor.DARK_AQUA +  "InfoBot " + ChatColor.GOLD + "Help Item");
		ArrayList<String> iron21 = new ArrayList<String>();
		iron21.add(ChatColor.GRAY + "Don't know what this is?");
		iron21.add(ChatColor.GRAY + "Use " + ChatColor.RED + "/infobot");
		plate2.setLore(iron21);
		chest2.setItemMeta(plate2);
		
		inv.contains(chest2);
		inv.setItem(7, chest2);
	}
	
	public static void createCustomItemLores(Player p, Inventory inv, Material material, byte byt, String displayName, int slot, String lore, String lore2) {		
		ItemStack chest2 = new ItemStack(material, 1, (byte) byt);
		ItemMeta plate2 = chest2.getItemMeta();
		plate2.setDisplayName(displayName);
		ArrayList<String> iron21 = new ArrayList<String>();
		iron21.add(lore);
		iron21.add(lore2);
		plate2.setLore(iron21);
		chest2.setItemMeta(plate2);
		
		inv.contains(chest2);
		inv.setItem(slot, chest2);
	}
	
	public static void createCustomItemLores3(Player p, Inventory inv, Material material, byte byt, String displayName, int slot, String lore, String lore2, String lore3) {		
		ItemStack chest2 = new ItemStack(material, 1, (byte) byt);
		ItemMeta plate2 = chest2.getItemMeta();
		plate2.setDisplayName(displayName);
		ArrayList<String> iron21 = new ArrayList<String>();
		iron21.add(lore);
		iron21.add(lore2);
		iron21.add(lore3);
		plate2.setLore(iron21);
		chest2.setItemMeta(plate2);
		
		inv.contains(chest2);
		inv.setItem(slot, chest2);
	}
	
	public static void createCustomItem(Player p, Inventory inv, Material material, byte byt, String displayName, int slot) {		
		ItemStack chest2 = new ItemStack(material, 1, (byte) byt);
		ItemMeta plate2 = chest2.getItemMeta();
		plate2.setDisplayName(displayName);
		chest2.setItemMeta(plate2);
		
		inv.contains(chest2);
		inv.setItem(slot, chest2);
	}
	
	public static void createCustomHeadFromPlayer(Player p, Inventory inv, int slot, String displayName) {
		ItemStack skull = getPlayerHead(p.getName());
        
        SkullMeta meta = (SkullMeta) skull.getItemMeta();
        meta.setDisplayName(displayName);
        skull.setItemMeta(meta);
        
        inv.setItem(slot, skull);
	}
	
	public static ItemStack getPlayerHead(String name) {
        ItemStack stack = new ItemStack(Material.AIR);
        //stack is what we will return
        boolean done = false;
        //done is weather we have a free space or not to place or head
        Location loc = null;
        // the location which will be our head blocks location
        int i = 0;
        //an Integer which we will use in the while up ahead -.-
        while (!done) {
            //while we have not found a place for our player head
            if (!Bukkit.getWorld(Main.MainWorld).getBlockAt(-29999970,64,-29999970 + i).getType().equals(Material.AIR)) {
                //check if block isn't air and if it isn't we add to our Integer "i". The need for it is hard to explain.
                i++;
                //adding :D
            }else{
                //YES we found a clear space!
                done = Boolean.valueOf(done);
                //I dont want to suppress warnings for unused even though we use it.
                done = true;
                // yes we're almost done
                loc = new Location(Bukkit.getWorld(Main.MainWorld),-29999970,64,29999970 + i);
                //pretty self explanatory
                break;
            }
        }
        //lets first make our loc a skull
        loc.getBlock().setType(Material.SKULL);
        //get the skull state
        Skull s = (Skull) loc.getBlock().getState();
        //set the owner
        s.setOwner(name);
        //update the block
        s.update();
        //even though it has only one drop Block#getDrops() returns an ItemStack[]
        for (ItemStack s1 : s.getBlock().getDrops()) {
            //we have our head
            stack = s1;
        }
        //return the block back to air
        s.getBlock().setType(Material.AIR);
        //and voila We have a perfect skull!!!!! :D
        return stack;
    }

}
