package server.Inventory;

import java.util.ArrayList;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import server.Main;

public class PlayerVisibility implements Listener {
	
    public ArrayList<UUID> clickdelay = new ArrayList<UUID>();
    
	private Main plugin;
	 
	public PlayerVisibility(Main instance) {
	    this.plugin = instance;
	}
	
    private int id;
    
    public void setId(int id) {
        this.id = id;
    }
	
	@SuppressWarnings("deprecation")
	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent e) {
		Player player = e.getPlayer();
		Inventory inv = player.getInventory();
		
		ItemStack chest2 = new ItemStack(Material.getMaterial(351), 1, (byte) 10);
		ItemMeta plate2 = chest2.getItemMeta();
		plate2.setDisplayName(ChatColor.GREEN + "" + ChatColor.BOLD + "Player Visibility -> " + ChatColor.GOLD + "" + ChatColor.BOLD + "ON");
		ArrayList<String> iron21 = new ArrayList<String>();
		iron21.add(ChatColor.GRAY + "Right click to toggle players on/" + ChatColor.RED + "" + ChatColor.BOLD + "off!");
		plate2.setLore(iron21);
		chest2.setItemMeta(plate2);
		
		inv.contains(chest2);
		inv.setItem(1, chest2);
		
		for (Player p : Bukkit.getServer().getOnlinePlayers()) {
            if (p != e.getPlayer()) {
                    if (plugin.usingClock.contains(p.getName())) {
                            p.hidePlayer(e.getPlayer());
                    }
                    else {
                            p.showPlayer(e.getPlayer()); 
                    }
            }
    }
	}
	
	@EventHandler
	public void onPlayerQuit(PlayerQuitEvent e) {
		Bukkit.getServer().getScheduler().cancelTask(id);
		for (Player all : Bukkit.getServer().getOnlinePlayers()) {
		e.getPlayer().showPlayer(all);
		}
	}
	
	@SuppressWarnings("deprecation")
	@EventHandler
	public void onWatchClick(PlayerInteractEvent e) {	
		ItemStack off = new ItemStack(Material.getMaterial(351), 1, (byte) 8);
		ItemMeta plate2 = off.getItemMeta();
		plate2.setDisplayName(ChatColor.GREEN + "" + ChatColor.BOLD + "Player Visibility -> " + ChatColor.RED + "" + ChatColor.BOLD + "OFF");
		ArrayList<String> iron21 = new ArrayList<String>();
		iron21.add(ChatColor.GRAY + "Right click to toggle players " + ChatColor.RED + ChatColor.BOLD + "on" + ChatColor.GRAY + "/off!");
		plate2.setLore(iron21);
		off.setItemMeta(plate2);
		
		ItemStack on = new ItemStack(Material.getMaterial(351), 1, (byte) 10);
		ItemMeta plate21 = on.getItemMeta();
		plate21.setDisplayName(ChatColor.GREEN + "" + ChatColor.BOLD + "Player Visibility -> " + ChatColor.GOLD + "" + ChatColor.BOLD + "ON");
		ArrayList<String> iron211 = new ArrayList<String>();
		iron211.add(ChatColor.GRAY + "Right click to toggle players on/" + ChatColor.RED + "" + ChatColor.BOLD + "off!");
		plate21.setLore(iron211);
		on.setItemMeta(plate21);
		
		final Player player = (Player) e.getPlayer();
		ItemStack cl = e.getItem();
		if (player.getItemInHand() != null) {
			if (e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK) {
				if (e.getItem().hasItemMeta() && e.getItem().getItemMeta().getDisplayName().contains((ChatColor.GREEN + "" + ChatColor.BOLD + "Player Visibility -> " + ChatColor.GOLD + "" + ChatColor.BOLD + "ON"))) {
			    	if (cl.getType() == Material.getMaterial(351)) {
			    		if (!plugin.usingClock.contains(e.getPlayer().getName())) {//
					e.setCancelled(true);
					player.getInventory().setItem(1, null);
					player.getInventory().setItem(1, off);
					plugin.usingClock.add(player.getName());
					for (Player p : Bukkit.getServer().getOnlinePlayers()) {
                        if (p != e.getPlayer()) {
                                e.getPlayer().hidePlayer(p);   
                        }
					}
	    			return;
			    	}//
			    	}
				}else if (e.getItem().hasItemMeta() && e.getItem().getItemMeta().getDisplayName().contains((ChatColor.GREEN + "" + ChatColor.BOLD + "Player Visibility -> " + ChatColor.RED + "" + ChatColor.BOLD + "OFF"))) {
					if (cl.getType() == Material.getMaterial(351)) {
						if (plugin.usingClock.contains(e.getPlayer().getName())) {
						player.getInventory().setItem(1, null);
						player.getInventory().setItem(1, on);
						plugin.usingClock.remove(player.getName());
						for (Player p : Bukkit.getServer().getOnlinePlayers()) {
	                        if (p != e.getPlayer()) {
	                            e.getPlayer().showPlayer(p);    
	                        }
	                    }
		    			return;
						}
					}
				}
			}
    	}
	}

}
