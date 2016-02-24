package Pretpark;

import java.util.ArrayList;

import net.milkbowl.vault.economy.Economy;
import net.milkbowl.vault.economy.EconomyResponse;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import Pretpark.MessageManager.MsgType;

public class Kits extends JavaPlugin implements Listener{
	
	public static Inventory normal = Bukkit.createInventory(null, 9, ChatColor.AQUA + "" + ChatColor.BOLD + "Normal Kits");
	public static Inventory vip1 = Bukkit.createInventory(null, 9, ChatColor.AQUA + "" + ChatColor.BOLD + "VIP Kits");
	public static Inventory vip11 = Bukkit.createInventory(null, 9, ChatColor.AQUA + "" + ChatColor.BOLD + "VIP+ Kits");
	
	public static Economy econ = null;
	
	String piston = ChatColor.AQUA + "[ ]";
	static Player playerp;
	
	public void onEnable() {
		
		 if (!setupEconomy() ) {
	            getLogger().severe(String.format("[%s] - Disabled due to no Vault dependency found!", getDescription().getName()));
	            getServer().getPluginManager().disablePlugin(this);
	            return;
	        }
		
		Bukkit.getServer().getPluginManager().registerEvents(new NetherStar(this), this);
		Bukkit.getServer().getPluginManager().registerEvents(new Events(this), this);
		Bukkit.getServer().getPluginManager().registerEvents(new bossbar(), this);
		Bukkit.getServer().getPluginManager().registerEvents(new AutoRespawn(this), this);
		Bukkit.getServer().getPluginManager().registerEvents(this, this);
	}
	
	private boolean setupEconomy() {
        if (getServer().getPluginManager().getPlugin("Vault") == null) {
            return false;
        }
        RegisteredServiceProvider<Economy> rsp = getServer().getServicesManager().getRegistration(Economy.class);
        if (rsp == null) {
            return false;
        }
        econ = rsp.getProvider();
        return econ != null;
    }
	
	@EventHandler
	public void onPlayerGetMoney(final PlayerJoinEvent e) {
		Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(this, new Runnable() {
			@Override
			public void run() {
				econ.depositPlayer(e.getPlayer().getName(), 5);
				e.getPlayer().sendMessage(MsgType.NORMAL + "You got " + ChatColor.RED + "5" + ChatColor.YELLOW + " coins from us! for FREE");
			}
		}, 6000, 6000);
	}
	
    static { //Normal menu
    	
    	ItemStack chest2 = new ItemStack(Material.WEB);
		ItemMeta plate2 = chest2.getItemMeta();
		plate2.setDisplayName(ChatColor.AQUA + "[" + ChatColor.GOLD + " Remove your kit items! " + ChatColor.AQUA + "]");
		ArrayList<String> iron2 = new ArrayList<String>();
		iron2.add(ChatColor.GRAY + "");
		iron2.add(ChatColor.GRAY + "This item removes your armor and speed!");
		iron2.add(ChatColor.DARK_GRAY + "No kits will be removed, only your items.");
		iron2.add(ChatColor.DARK_GRAY + "You can use you kit when ever you want!");
		plate2.setLore(iron2);
		chest2.setItemMeta(plate2);
		
		normal.setItem(8, chest2);
		
		ItemStack chest = new ItemStack(Material.MINECART);
		ItemMeta plate = chest.getItemMeta();
		plate.setDisplayName(ChatColor.AQUA + "[" + ChatColor.GOLD + " MineCart Kit " + ChatColor.AQUA + "]");
		ArrayList<String> iron = new ArrayList<String>();
		iron.add(ChatColor.GRAY + "");
		iron.add(ChatColor.GRAY + "This kit gives you:");
		iron.add(ChatColor.DARK_GRAY + "Speed 1 + Lether Helmet");
		iron.add(ChatColor.GRAY + "");
		iron.add(ChatColor.GOLD + "Cost: 10");
		plate.setLore(iron);
		chest.setItemMeta(plate);
		
		normal.setItem(0, chest);
		
		ItemStack chest1 = new ItemStack(Material.BREAD);
		ItemMeta plate1 = chest1.getItemMeta();
		plate1.setDisplayName(ChatColor.AQUA + "[" + ChatColor.GOLD + " Bread Kit " + ChatColor.AQUA + "]");
		ArrayList<String> iron1 = new ArrayList<String>();
		iron1.add(ChatColor.GRAY + "");
		iron1.add(ChatColor.GRAY + "This kit gives you:");
		iron1.add(ChatColor.DARK_GRAY + "Speed 2 + Iron Helmet");
		iron1.add(ChatColor.GRAY + "");
		iron1.add(ChatColor.GOLD + "Cost: 50");
		plate1.setLore(iron1);
		chest1.setItemMeta(plate1);
		
		normal.setItem(1, chest1);
		
		ItemStack chest3 = new ItemStack(Material.RAW_FISH);
		ItemMeta plate3 = chest3.getItemMeta();
		plate3.setDisplayName(ChatColor.AQUA + "[" + ChatColor.GOLD + " Fish Kit " + ChatColor.AQUA + "]");
		ArrayList<String> iron3 = new ArrayList<String>();
		iron3.add(ChatColor.GRAY + "");
		iron3.add(ChatColor.GRAY + "This kit gives you:");
		iron3.add(ChatColor.DARK_GRAY + "Speed 2 + Gold Helmet + Gold Boots");
		iron3.add(ChatColor.GRAY + "");
		iron3.add(ChatColor.GOLD + "Cost: 100");
		plate3.setLore(iron3);
		chest3.setItemMeta(plate3);
		
		normal.setItem(2, chest3);
		
	}
	
	static { //Vip Menu
		
		ItemStack chest = new ItemStack(Material.IRON_CHESTPLATE);
		ItemMeta plate = chest.getItemMeta();
		plate.setDisplayName(ChatColor.AQUA + "[" + ChatColor.GOLD + " Vip IronChestPlate Kit " + ChatColor.AQUA + "]");
		ArrayList<String> iron = new ArrayList<String>();
		iron.add(ChatColor.GRAY + "");
		iron.add(ChatColor.GRAY + "This kit gives you:");
		iron.add(ChatColor.DARK_GRAY + "Speed 2 + Full Iron Armor");
		iron.add(ChatColor.GRAY + "");
		iron.add(ChatColor.GOLD + "Cost: " + ChatColor.RED + "FREE");
		plate.setLore(iron);
		chest.setItemMeta(plate);
		
		vip1.setItem(0, chest);
		
		ItemStack chest1 = new ItemStack(Material.GOLD_CHESTPLATE);
		ItemMeta plate1 = chest1.getItemMeta();
		plate1.setDisplayName(ChatColor.AQUA + "[" + ChatColor.GOLD + " Vip GoldChestPlate Kit " + ChatColor.AQUA + "]");
		ArrayList<String> iron1 = new ArrayList<String>();
		iron1.add(ChatColor.GRAY + "");
		iron1.add(ChatColor.GRAY + "This kit gives you:");
		iron1.add(ChatColor.DARK_GRAY + "Speed 2 + Full Gold Armor");
		iron1.add(ChatColor.GRAY + "");
		iron1.add(ChatColor.GOLD + "Cost: " + ChatColor.RED + "FREE");
		plate1.setLore(iron1);
		chest1.setItemMeta(plate1);
		
		vip1.setItem(1, chest1);
		
	}
	
	static { //VIP++ Menu
		
	}	
	
	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent e) {
		Player p = e.getPlayer();
		Inventory inv = p.getInventory();
		
		ItemStack pis0 = new ItemStack(Material.PISTON_BASE);
		ItemMeta im0 = pis0.getItemMeta();
		im0.setDisplayName(piston);
		pis0.setItemMeta(im0);
		
		ItemStack pis1 = new ItemStack(Material.PISTON_BASE);
		ItemMeta im1 = pis1.getItemMeta();
		im1.setDisplayName(piston);
		pis1.setItemMeta(im1);
		
		ItemStack pis2 = new ItemStack(Material.PISTON_BASE);
		ItemMeta im2 = pis2.getItemMeta();
		im2.setDisplayName(piston);
		pis2.setItemMeta(im2);
		
		ItemStack pis3 = new ItemStack(Material.PISTON_BASE);
		ItemMeta im3 = pis3.getItemMeta();
		im3.setDisplayName(piston);
		pis3.setItemMeta(im3);
		
		ItemStack pis4 = new ItemStack(Material.PISTON_BASE);
		ItemMeta im4 = pis4.getItemMeta();
		im4.setDisplayName(piston);
		pis4.setItemMeta(im4);
		
		ItemStack pis5 = new ItemStack(Material.PISTON_BASE);
		ItemMeta im5 = pis5.getItemMeta();
		im5.setDisplayName(piston);
		pis5.setItemMeta(im5);
		
		ItemStack pis6 = new ItemStack(Material.PISTON_BASE);
		ItemMeta im6 = pis6.getItemMeta();
		im6.setDisplayName(piston);
		pis6.setItemMeta(im6);
		
		ItemStack pis7 = new ItemStack(Material.PISTON_BASE);
		ItemMeta im7 = pis7.getItemMeta();
		im7.setDisplayName(piston);
		pis7.setItemMeta(im7);
		
		ItemStack pis8 = new ItemStack(Material.PISTON_BASE);
		ItemMeta im8 = pis8.getItemMeta();
		im8.setDisplayName(piston);
		pis8.setItemMeta(im8);
		
		ItemStack pis9 = new ItemStack(Material.PISTON_BASE);
		ItemMeta im9 = pis9.getItemMeta();
		im9.setDisplayName(piston);
		pis9.setItemMeta(im9);
		
		ItemStack pis10 = new ItemStack(Material.PISTON_BASE);
		ItemMeta im10 = pis10.getItemMeta();
		im10.setDisplayName(piston);
		pis10.setItemMeta(im10);
		
		ItemStack pis11 = new ItemStack(Material.PISTON_BASE);
		ItemMeta im11 = pis11.getItemMeta();
		im11.setDisplayName(piston);
		pis11.setItemMeta(im11);
		
		ItemStack pis12 = new ItemStack(Material.PISTON_BASE);
		ItemMeta im12 = pis12.getItemMeta();
		im12.setDisplayName(piston);
		pis12.setItemMeta(im12);
		
		ItemStack pis13 = new ItemStack(Material.PISTON_BASE);
		ItemMeta im13 = pis13.getItemMeta();
		im13.setDisplayName(piston);
		pis13.setItemMeta(im13);
		
		ItemStack pis14 = new ItemStack(Material.PISTON_BASE);
		ItemMeta im14 = pis14.getItemMeta();
		im14.setDisplayName(piston);
		pis14.setItemMeta(im14);
		
		ItemStack pis15 = new ItemStack(Material.PISTON_BASE);
		ItemMeta im15 = pis15.getItemMeta();
		im15.setDisplayName(piston);
		pis15.setItemMeta(im15);
		
		ItemStack pis16 = new ItemStack(Material.PISTON_BASE);
		ItemMeta im16 = pis16.getItemMeta();
		im16.setDisplayName(piston);
		pis16.setItemMeta(im16);
		
		ItemStack pis17 = new ItemStack(Material.PISTON_BASE);
		ItemMeta im17 = pis17.getItemMeta();
		im17.setDisplayName(piston);
		pis17.setItemMeta(im17);
		
		ItemStack pis18 = new ItemStack(Material.PISTON_BASE);
		ItemMeta im18 = pis18.getItemMeta();
		im18.setDisplayName(piston);
		pis18.setItemMeta(im18);
		
		ItemStack pis19 = new ItemStack(Material.PISTON_BASE);
		ItemMeta im19 = pis19.getItemMeta();
		im19.setDisplayName(piston);
		pis19.setItemMeta(im19);
		
		ItemStack pis20 = new ItemStack(Material.PISTON_BASE);
		ItemMeta im20 = pis20.getItemMeta();
		im20.setDisplayName(piston);
		pis20.setItemMeta(im20);
		
		ItemStack pis21 = new ItemStack(Material.PISTON_BASE);
		ItemMeta im21 = pis21.getItemMeta();
		im21.setDisplayName(piston);
		pis21.setItemMeta(im21);
		
		ItemStack pis22 = new ItemStack(Material.PISTON_BASE);
		ItemMeta im22 = pis22.getItemMeta();
		im22.setDisplayName(piston);
		pis22.setItemMeta(im22);
		
		ItemStack pis23 = new ItemStack(Material.PISTON_BASE);
		ItemMeta im23 = pis23.getItemMeta();
		im23.setDisplayName(piston);
		pis23.setItemMeta(im23);
		
		ItemStack flower = new ItemStack(Material.RAILS);
		ItemMeta flow = flower.getItemMeta();
		flow.setDisplayName(ChatColor.AQUA + "[" + " Normal Kits " + ChatColor.AQUA + "]");
		flower.setItemMeta(flow);
		
		ItemStack flower1 = new ItemStack(Material.ACTIVATOR_RAIL);
		ItemMeta flow1 = flower1.getItemMeta();
		flow1.setDisplayName(ChatColor.AQUA + "[" + " VIP" + ChatColor.GOLD + "" + ChatColor.AQUA + " Kits " + ChatColor.AQUA + "]");
		ArrayList<String> flowe1 = new ArrayList<String>();
		flowe1.add(ChatColor.GRAY + "");
		flowe1.add(ChatColor.GOLD + "Only for VIP");
		flow1.setLore(flowe1);
		flower1.setItemMeta(flow1);
				
		ItemStack flower2 = new ItemStack(Material.POWERED_RAIL);
		ItemMeta flow2 = flower2.getItemMeta();
		flow2.setDisplayName(ChatColor.AQUA + "[" + " VIP" + ChatColor.GOLD + "+" + ChatColor.AQUA + " Kits " + ChatColor.AQUA + "]");
		ArrayList<String> flowe2 = new ArrayList<String>();
		flowe2.add(ChatColor.GRAY + "");
		flowe2.add(ChatColor.GOLD + "Only for VIP+");
		flow2.setLore(flowe2);
		flower2.setItemMeta(flow2);
				
		inv.contains(pis0);
		inv.setItem(9, pis0);
		
		inv.contains(pis1);
		inv.setItem(10, pis1);
		
		inv.contains(pis2);
		inv.setItem(11, pis2);
		
		inv.contains(pis3);
		inv.setItem(12, pis3);
		
		inv.contains(pis4);
		inv.setItem(13, pis4);
		
		inv.contains(pis5);
		inv.setItem(14, pis5);
		
		inv.contains(pis6);
		inv.setItem(15, pis6);
		
		inv.contains(pis7);
		inv.setItem(16, pis7);
		
		inv.contains(pis8);
		inv.setItem(17, pis8);
		
		inv.contains(pis9);
		inv.setItem(18, pis9);
		
		inv.contains(pis10);
		inv.setItem(19, pis10);
		
		inv.contains(pis11);
		inv.setItem(20, pis11);
		
		inv.contains(flower);
		inv.setItem(21, flower);
		
		inv.contains(flower1);
		inv.setItem(22, flower1);
		
		inv.contains(flower2);
		inv.setItem(23, flower2);

		inv.contains(pis15);
		inv.setItem(24, pis15);

		inv.contains(pis16);
		inv.setItem(25, pis16);

		inv.contains(pis17);
		inv.setItem(26, pis17);

		inv.contains(pis18);
		inv.setItem(27, pis18);

		inv.contains(pis19);
		inv.setItem(28, pis19);

		inv.contains(pis20);
		inv.setItem(29, pis20);

		inv.contains(pis21);
		inv.setItem(30, pis21);

		inv.contains(pis12);
		inv.setItem(31, pis12);	

		inv.contains(pis13);
		inv.setItem(32, pis13);	

		inv.contains(pis14);
		inv.setItem(33, pis14);	

		inv.contains(pis22);
		inv.setItem(34, pis22);	
		
		inv.contains(pis23);
		inv.setItem(35, pis23);	
		
	}

	@EventHandler
	public void onIronChestPlateClick(InventoryClickEvent e) {
		
		ItemStack arrow3 = new ItemStack(Material.IRON_HELMET);
		ItemMeta arroww3 = arrow3.getItemMeta();
		arroww3.setDisplayName(ChatColor.AQUA + "Iron Helmet");
		arrow3.setItemMeta(arroww3);
		
		ItemStack arrow2 = new ItemStack(Material.IRON_CHESTPLATE);
		ItemMeta arroww2 = arrow2.getItemMeta();
		arroww2.setDisplayName(ChatColor.AQUA + "Iron ChestPlate");
		arrow2.setItemMeta(arroww2);
		
		ItemStack arrow = new ItemStack(Material.IRON_LEGGINGS);
		ItemMeta arroww = arrow.getItemMeta();
		arroww.setDisplayName(ChatColor.AQUA + "Iron Leggings");
		arrow.setItemMeta(arroww);
		
		ItemStack arrow4 = new ItemStack(Material.IRON_BOOTS);
		ItemMeta arroww4 = arrow4.getItemMeta();
		arroww4.setDisplayName(ChatColor.AQUA + "Iron Boots");
		arrow4.setItemMeta(arroww4);
		
		Player player = (Player) e.getWhoClicked();
		Inventory inv = e.getInventory();
		ItemStack cl = e.getCurrentItem();
			if (inv.getName().equals(vip1.getName())) {
				if (e.getCurrentItem().hasItemMeta() && e.getCurrentItem().getItemMeta().getDisplayName().contains(ChatColor.AQUA + "[" + ChatColor.GOLD + " Vip IronChestPlate Kit " + ChatColor.AQUA + "]")) {
			    	if (cl.getType() == Material.IRON_CHESTPLATE) {
					e.setCancelled(true);
					player.closeInventory();
					
					player.removePotionEffect(PotionEffectType.SPEED);
					player.getInventory().setArmorContents(null);
					
						player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 999999999, 1));
						player.getInventory().setHelmet(arrow3);
						player.getInventory().setChestplate(arrow2);
						player.getInventory().setLeggings(arrow);
						player.getInventory().setBoots(arrow4);
						player.sendMessage(MsgType.NORMAL + "You chosen the ChestPlate Kit!");
			    	}
					}
				}
			}
	
	@EventHandler
	public void onRawFishClick(InventoryClickEvent e) {
		
		ItemStack arrow3 = new ItemStack(Material.GOLD_HELMET);
		ItemMeta arroww3 = arrow3.getItemMeta();
		arroww3.setDisplayName(ChatColor.AQUA + "Gold Helmet");
		arrow3.setItemMeta(arroww3);
		
		ItemStack arrow4 = new ItemStack(Material.GOLD_BOOTS);
		ItemMeta arroww4 = arrow4.getItemMeta();
		arroww4.setDisplayName(ChatColor.AQUA + "Gold Boots");
		arrow4.setItemMeta(arroww4);
		
		Player player = (Player) e.getWhoClicked();
		Inventory inv = e.getInventory();
		ItemStack cl = e.getCurrentItem();
			if (inv.getName().equals(normal.getName())) {
			 	if (e.getCurrentItem().hasItemMeta() && e.getCurrentItem().getItemMeta().getDisplayName().contains(ChatColor.AQUA + "[" + ChatColor.GOLD + " Fish Kit " + ChatColor.AQUA + "]")) {
			    	if (cl.getType() == Material.RAW_FISH) {
					e.setCancelled(true);
					player.closeInventory();
					
					EconomyResponse r = econ.withdrawPlayer(player.getName(), 100);
					if (r.transactionSuccess()) {
					
					player.removePotionEffect(PotionEffectType.SPEED);
					player.getInventory().setArmorContents(null);
					
						player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 999999999, 1));
						player.getInventory().setHelmet(arrow3);
						player.getInventory().setBoots(arrow4);
						player.sendMessage(MsgType.NORMAL + "You chosen the Fish Kit!");
	     				} else {
	     					player.sendMessage(MsgType.ERROR + "You dont have enough money to use this kit!");
	     				}
					}
				}
			}
	    }
	
	@EventHandler
	public void onGoldChestPlateClick(InventoryClickEvent e) {
		
		ItemStack arrow3 = new ItemStack(Material.GOLD_HELMET);
		ItemMeta arroww3 = arrow3.getItemMeta();
		arroww3.setDisplayName(ChatColor.AQUA + "GOLD Helmet");
		arrow3.setItemMeta(arroww3);
		
		ItemStack arrow2 = new ItemStack(Material.GOLD_CHESTPLATE);
		ItemMeta arroww2 = arrow2.getItemMeta();
		arroww2.setDisplayName(ChatColor.AQUA + "Gold ChestPlate");
		arrow2.setItemMeta(arroww2);
		
		ItemStack arrow = new ItemStack(Material.GOLD_LEGGINGS);
		ItemMeta arroww = arrow.getItemMeta();
		arroww.setDisplayName(ChatColor.AQUA + "Gold Leggings");
		arrow.setItemMeta(arroww);
		
		ItemStack arrow4 = new ItemStack(Material.GOLD_BOOTS);
		ItemMeta arroww4 = arrow4.getItemMeta();
		arroww4.setDisplayName(ChatColor.AQUA + "Gold Boots");
		arrow4.setItemMeta(arroww4);
		
		Player player = (Player) e.getWhoClicked();
		Inventory inv = e.getInventory();
		ItemStack cl = e.getCurrentItem();
			if (inv.getName().equals(vip1.getName())) {
				if (e.getCurrentItem().hasItemMeta() && e.getCurrentItem().getItemMeta().getDisplayName().contains(ChatColor.AQUA + "[" + ChatColor.GOLD + " Vip GoldChestPlate Kit " + ChatColor.AQUA + "]")) {
		     		if (cl.getType() == Material.GOLD_CHESTPLATE) {
					e.setCancelled(true);
					player.closeInventory();
					
					player.removePotionEffect(PotionEffectType.SPEED);
					player.getInventory().setArmorContents(null);
					
						player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 999999999, 1));
						player.getInventory().setHelmet(arrow3);
						player.getInventory().setChestplate(arrow2);
						player.getInventory().setLeggings(arrow);
						player.getInventory().setBoots(arrow4);
						player.sendMessage(MsgType.NORMAL + "You chosen the ChestPlate Kit!");
					}
				}
			}
	    }
	
	@EventHandler
	public void onMineCartPlateClick(InventoryClickEvent e) {
		
		ItemStack arrow3 = new ItemStack(Material.LEATHER_HELMET);
		ItemMeta arroww3 = arrow3.getItemMeta();
		arroww3.setDisplayName(ChatColor.AQUA + "Leather Helmet");
		arrow3.setItemMeta(arroww3);
		
		Player player = (Player) e.getWhoClicked();
		Inventory inv = e.getInventory();
		ItemStack cl = e.getCurrentItem();
			if (inv.getName().equals(normal.getName())) {
				if (e.getCurrentItem().hasItemMeta() && e.getCurrentItem().getItemMeta().getDisplayName().contains(ChatColor.AQUA + "[" + ChatColor.GOLD + " MineCart Kit " + ChatColor.AQUA + "]")) {
			    	if (cl.getType() == Material.MINECART) {
					e.setCancelled(true);
					player.closeInventory();
					
					EconomyResponse r = econ.withdrawPlayer(player.getName(), 10);
					if (r.transactionSuccess()) {
					
					player.removePotionEffect(PotionEffectType.SPEED);
					player.getInventory().setArmorContents(null);
					
						player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 999999999, 0));
						player.getInventory().setHelmet(arrow3);
						player.sendMessage(MsgType.NORMAL + "You chosen the Minecart Kit!");
				    	} else {
				    		player.sendMessage(MsgType.ERROR + "You dont have enough money to use this kit!");
				    	}
					}
				}
			}
	    }
	
	@EventHandler
	public void onWebClick(InventoryClickEvent e) {
		Player player = (Player) e.getWhoClicked();
		Inventory inv = e.getInventory();
		ItemStack cl = e.getCurrentItem();
			if (inv.getName().equals(normal.getName()) || inv.getName().equals(vip1.getName()) || inv.getName().equals(vip11.getName())) {
				if (e.getCurrentItem().hasItemMeta() && e.getCurrentItem().getItemMeta().getDisplayName().contains(ChatColor.AQUA + "[" + ChatColor.GOLD + " Remove your kit items! " + ChatColor.AQUA + "]")) {
			    	if (cl.getType() == Material.WEB) {
					e.setCancelled(true);
					player.closeInventory();
					
					player.removePotionEffect(PotionEffectType.SPEED);
					player.getInventory().setArmorContents(null);
						player.sendMessage(MsgType.NORMAL + "Your kit has been cleared!");
				    	}
					}
				}
	       }

	@EventHandler
	public void onBreadClick(InventoryClickEvent e) {
		
		ItemStack arrow3 = new ItemStack(Material.IRON_HELMET);
		ItemMeta arroww3 = arrow3.getItemMeta();
		arroww3.setDisplayName(ChatColor.AQUA + "Iron Helmet");
		arrow3.setItemMeta(arroww3);
		
		Player player = (Player) e.getWhoClicked();
		Inventory inv = e.getInventory();
		ItemStack cl = e.getCurrentItem();
			if (inv.getName().equals(normal.getName())) {
				if (e.getCurrentItem().hasItemMeta() && e.getCurrentItem().getItemMeta().getDisplayName().contains(ChatColor.AQUA + "[" + ChatColor.GOLD + " Bread Kit " + ChatColor.AQUA + "]")) {
		    		if (cl.getType() == Material.BREAD) {
					e.setCancelled(true);
					player.closeInventory();
					
					EconomyResponse r = econ.withdrawPlayer(player.getName(), 50);
					if (r.transactionSuccess()) {
					
					player.removePotionEffect(PotionEffectType.SPEED);
					player.getInventory().setArmorContents(null);
					
						player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 999999999, 1));
						player.getInventory().setHelmet(arrow3);
						player.sendMessage(MsgType.NORMAL + "You chosen the Bread Kit!");
			    		} else {
			    			player.sendMessage(MsgType.ERROR + "You dont have enough money to use this kit!");
			    		}
					}
				}
			}
	}
	
	@EventHandler
	public void onPistonClick(InventoryClickEvent e) {
		Inventory inv = e.getInventory();
		ItemStack cl = e.getCurrentItem();
		if (inv.getName().equals(inv.getName())) {
	    	if (e.getCurrentItem().hasItemMeta() && e.getCurrentItem().getItemMeta().getDisplayName().contains(ChatColor.AQUA + "[ ]")) {
				if (cl.getType() == Material.PISTON_BASE) {
					e.setCancelled(true);
				}
			}
		}
	}
	
	@EventHandler
	public void onActivatorRialsClick(InventoryClickEvent e) {
		Player player = (Player) e.getWhoClicked();
		Inventory inv = e.getInventory();
		ItemStack cl = e.getCurrentItem();
		if (inv.getName().equals(inv.getName())) {
	    	if (e.getCurrentItem().hasItemMeta() && e.getCurrentItem().getItemMeta().getDisplayName().contains(ChatColor.AQUA + "[" + " VIP" + ChatColor.GOLD + "" + ChatColor.AQUA + " Kits " + ChatColor.AQUA + "]")) {
				if (cl.getType() == Material.ACTIVATOR_RAIL) {
					e.setCancelled(true);
					player.openInventory(vip1);
					}
				}
			}
		}
	
	
	@EventHandler
	public void onPoweredRaisl(InventoryClickEvent e) {
		Player player = (Player) e.getWhoClicked();
		Inventory inv = e.getInventory();
		ItemStack cl = e.getCurrentItem();
		if (e.getCurrentItem().hasItemMeta() && e.getCurrentItem().getItemMeta().getDisplayName().contains(ChatColor.AQUA + "[" + " VIP" + ChatColor.GOLD + "+" + ChatColor.AQUA + " Kits " + ChatColor.AQUA + "]")) {
			if (inv.getName().equals(inv.getName())) {
				if (cl.getType() == Material.POWERED_RAIL) {
					e.setCancelled(true);
					player.openInventory(vip11);
			     		}
					}
				}
			}
	

	@EventHandler
	public void onRailsClick(InventoryClickEvent e) {
		Player player = (Player) e.getWhoClicked();
		Inventory inv = e.getInventory();
		ItemStack cl = e.getCurrentItem();
		if (e.getCurrentItem().hasItemMeta() && e.getCurrentItem().getItemMeta().getDisplayName().contains(ChatColor.AQUA + "[" + " Normal Kits " + ChatColor.AQUA + "]")) {
			if (inv.getName().equals(inv.getName())) {
				if (cl.getType() == Material.RAILS) {
					e.setCancelled(true);
					player.openInventory(normal);
				}
			}
		}
	}

}
