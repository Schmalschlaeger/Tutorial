package WH;

import java.util.ArrayList;

import net.minecraft.util.com.google.common.io.ByteArrayDataOutput;
import net.minecraft.util.com.google.common.io.ByteStreams;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin implements Listener{
	
	public static Inventory hub = Bukkit.createInventory(null, 9, ChatColor.AQUA + "" + ChatColor.BOLD + "Server Selector");
	public static String serverName;
	
	static {
		ItemStack chest2 = new ItemStack(Material.DIAMOND_SWORD);
		ItemMeta plate2 = chest2.getItemMeta();
		plate2.setDisplayName(ChatColor.GOLD + "" + ChatColor.BOLD + "Factions Server");
		ArrayList<String> iron2 = new ArrayList<String>();
		iron2.add(ChatColor.GRAY + "A powerfull factions server!");
		plate2.setLore(iron2);
		chest2.setItemMeta(plate2);
		
		hub.contains(chest2);
		hub.setItem(1, chest2);
		
		ItemStack chest21 = new ItemStack(Material.IRON_BARDING);
		ItemMeta plate21 = chest21.getItemMeta();
		plate21.setDisplayName(ChatColor.GOLD + "" + ChatColor.BOLD + "Prison Server");
		ArrayList<String> iron21 = new ArrayList<String>();
		iron21.add(ChatColor.GRAY + "A wonderfull prison server!");
		plate21.setLore(iron21);
		chest21.setItemMeta(plate21);
		
		hub.contains(chest21);
		hub.setItem(2, chest21);
	}
	
	public void onEnable() {
		Bukkit.getServer().getPluginManager().registerEvents(this, this);
		getServer().getMessenger().registerOutgoingPluginChannel(this, "BungeeCord");
	}
	
	public void serverConnect(String server, Player p) {
    	ByteArrayDataOutput out = ByteStreams.newDataOutput();
        out.writeUTF("Connect");
        out.writeUTF(server);
		p.sendPluginMessage(this, "BungeeCord", out.toByteArray());
	}
	
	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent e) {
		Player p = e.getPlayer();
		
		ItemStack chest2 = new ItemStack(Material.DIAMOND);
		ItemMeta plate2 = chest2.getItemMeta();
		plate2.setDisplayName(ChatColor.GOLD + "" + ChatColor.BOLD + "Server Selector");
		ArrayList<String> iron2 = new ArrayList<String>();
		iron2.add(ChatColor.GRAY + "Select an server by clicking on it!");
		plate2.setLore(iron2);
		chest2.setItemMeta(plate2);
		
		p.getInventory().contains(chest2);
		p.getInventory().addItem(chest2);
	}
	
	@EventHandler
	public void onPlayerInterAct(PlayerInteractEvent e) {
		Player player = (Player) e.getPlayer();
		ItemStack cl = e.getItem();
		if (player.getItemInHand() != null) {
			if (e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK) {
				if (e.getItem().hasItemMeta() && e.getItem().getItemMeta().getDisplayName().contains((ChatColor.GOLD + "" + ChatColor.BOLD + "Server Selector"))) {
			    	if (cl.getType() == Material.DIAMOND) {
					e.setCancelled(true);
					player.openInventory(hub);
			    	}
				}	
			}
		}
	}
	
	@EventHandler
	public void onChestPlateClick(InventoryClickEvent e) {
		Player player = (Player) e.getWhoClicked();
		Inventory inv = e.getInventory();
		ItemStack cl = e.getCurrentItem();
			if (inv.getName().equals(hub.getName())) {
			 	if (e.getCurrentItem().hasItemMeta() && e.getCurrentItem().getItemMeta().getDisplayName().contains(ChatColor.GOLD + "" + ChatColor.BOLD + "Factions Server")) {
			    	if (cl.getType() == Material.DIAMOND_SWORD) {
					e.setCancelled(true);
					player.closeInventory();
					serverConnect("Factions", player);
			    	}
			 	}else if (e.getCurrentItem().hasItemMeta() && e.getCurrentItem().getItemMeta().getDisplayName().contains(ChatColor.GOLD + "" + ChatColor.BOLD + "Prison Server")) {
			 		if (cl.getType() == Material.IRON_BARDING) {
						e.setCancelled(true);
						player.closeInventory();
						serverConnect("Jail", player);
			 		}
			 	}else if (e.getCurrentItem().hasItemMeta() && e.getCurrentItem().getItemMeta().getDisplayName().contains(ChatColor.DARK_AQUA + "The amazing ThemePark's")) {
			 		if (cl.getType() == Material.DIAMOND) {
						e.setCancelled(true);
						player.closeInventory();
			 		}
			 	}
			}
	   }

}
