package tuts;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;

import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;

public class Main extends JavaPlugin implements Listener{
	
	/*
	 * Survvial
	 * Skyars
	 * Creative
	 */
	
	public void onEnable() {
		Bukkit.getServer().getPluginManager().registerEvents(this, this);
		
		getServer().getMessenger().registerOutgoingPluginChannel(this, "BungeeCord");
	}
	
	private static Inventory inv = Bukkit.createInventory(null, 36, ChatColor.AQUA + "Server Selector");
	
	static {
		createITem(inv, Material.STAINED_CLAY, 1, (byte) 3, "§6SURVIVAL", "§cClick to join", 4);
		createITem(inv, Material.STAINED_CLAY, 1, (byte) 4, "§3SkyWars", "§cClick to join", 5);
		createITem(inv, Material.STAINED_CLAY, 1, (byte) 5, "§2Creative", "§cClick to join", 6);
	}
	
	public void serverConnect(String server, Player p) {
    	ByteArrayDataOutput out = ByteStreams.newDataOutput();
        out.writeUTF("Connect");
        out.writeUTF(server);
		p.sendPluginMessage(this, "BungeeCord", out.toByteArray());
    }
	
	public static void createITem(Inventory inv, Material mat, int amount, byte b, String displayNAme, String lore1, int place) {
		ItemStack stack = new ItemStack(mat, amount, b);
		ItemMeta meta = stack.getItemMeta();
		
		meta.setDisplayName(displayNAme);
		List<String> lore = new ArrayList<String>();
		lore.add("");
		lore.add(lore1);
		meta.setLore(lore);
		stack.setItemMeta(meta);
		
		inv.setItem(place, stack);
	}
	
	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent e) {
		if (!e.getPlayer().getInventory().contains(new ItemStack(Material.COMPASS, 1))) {
			e.getPlayer().getInventory().addItem(new ItemStack(Material.COMPASS, 1));
		}
	}
	
	@EventHandler
	public void inItemClick(InventoryClickEvent e) {
		Player p = (Player) e.getWhoClicked();
		if (e.getInventory() != null || !e.getInventory().getName().equalsIgnoreCase(null) || e.getCurrentItem().getItemMeta().hasDisplayName()) {
			p.closeInventory();
			return;
		}
		if (e.getInventory().getName().equalsIgnoreCase(ChatColor.stripColor("Server Selector"))) {
			if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§6SURVIVAL")) {
				if (e.getCurrentItem().getType() == Material.STAINED_CLAY) {
					e.setCancelled(true);
					p.closeInventory();
					serverConnect("survival", p);
				}
			}
		}
	}
	
	@SuppressWarnings("deprecation")
	@EventHandler
	public void onPlayerClick(PlayerInteractEvent e) {
		Player p = e.getPlayer();
		
		if (e.getItem() != null) {
			if (p.getItemInHand() != null || p.getItemInHand().getItemMeta().hasDisplayName()) {
				if (p.getItemInHand().getType() == Material.COMPASS) {
					p.openInventory(inv);
					p.playSound(p.getLocation(), Sound.BLOCK_CHEST_OPEN, 1, 1);
				}
			}
		}
	}
	
}
