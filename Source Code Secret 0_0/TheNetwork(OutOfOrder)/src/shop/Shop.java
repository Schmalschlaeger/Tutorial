package shop;

import java.util.ArrayList;

import net.milkbowl.vault.economy.Economy;
import net.minecraft.util.com.google.common.io.ByteArrayDataOutput;
import net.minecraft.util.com.google.common.io.ByteStreams;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

import shop.MessageManager.MsgType;

import com.bergerkiller.bukkit.common.scoreboards.CommonObjective;
import com.bergerkiller.bukkit.common.scoreboards.CommonScore;
import com.bergerkiller.bukkit.common.scoreboards.CommonScoreboard;

public class Shop extends JavaPlugin implements Listener {
	
	public static Inventory menu = Bukkit.createInventory(null, 9, ChatColor.AQUA + "" + ChatColor.BOLD + "Shop");
	public static Inventory buy = Bukkit.createInventory(null, 36, ChatColor.AQUA + "" + ChatColor.BOLD + "Buy menu");
	public static Inventory sell = Bukkit.createInventory(null, 27, ChatColor.AQUA + "" + ChatColor.BOLD + "Sell menu");
	
    public ArrayList<Player> trail = new ArrayList<Player>();
    
    public static Economy econ = null;
    
    public static int eventTimer = 0;
    
    static int diamondPrice = 250;
    static int coalPrice = 20;
    static int ironPrice = 50;
    static int goldPrice = 100;
    static int carrotOnAnStickPrice = 50;
    static int goldNuggetPrice = 100;
    static int blazeRodePrice = 100;
    static int quartzPrice = 45;
    static int quartzBlockPrice = 100;
    static int chestPrice = 10;
    static int enchantmentTablePrice = 500;
    static int sunFlowerPrice = 25;
    static int redstoneBlockPrice = 125;
    static int bookPrice = 35;
    static int paintingPrice = 55;
    static int coblleWallPrice = 20;
    static int flintAndSteelPrice = 30;
    static int eggPrice = 50;
    static int enderPearlPrice = 150;
    static int enchantmentBottlePrice = 400;
    static int cakePrice = 120;
    static int boatPrice = 10;
    static int carrotPrice = 50;
    static int patatoPrice = 500;
    static int pumpkinPiePrice = 50;
    static int fishingRodPrice = 20;
    static int rawBeefPrice = 20;
    
    private int id;
    
    public void setId(int id) {
        this.id = id;
    }
	
	public void onEnable() {
		if (!setupEconomy() ) {
            getLogger().severe(String.format("[%s] - Disabled due to no Vault dependency found!", getDescription().getName()));
            getServer().getPluginManager().disablePlugin(this);
            return;
        }
		Bukkit.getServer().getPluginManager().registerEvents(this, this);
		Bukkit.getServer().getPluginManager().registerEvents(new ClickEventBuy(), this);
		getServer().getMessenger().registerOutgoingPluginChannel(this, "BungeeCord");
		
		getCommand("event").setExecutor(new Events(this)); 
		
		 StartTimer();
		
        getServer().getScheduler().scheduleSyncRepeatingTask(this, new Runnable() {
            public void run() {
              int onlinePlayers = Bukkit.getOnlinePlayers().length;
              int totalPlayers = Bukkit.getOfflinePlayers().length;

              for (Player player : Bukkit.getOnlinePlayers()) {
                String pname = player.getName().toString();
  		        final int balance1 = (int) econ.getBalance(pname);
  		        
                CommonScoreboard board = CommonScoreboard.get(player);
                CommonObjective sidebar = board.getObjective(CommonScoreboard.Display.SIDEBAR);

                CommonScore totonline = sidebar.getScore("totonline");
                CommonScore totplayers = sidebar.getScore("totplayers");
                CommonScore balance = sidebar.getScore("balance");
                
                CommonScore empty1 = sidebar.getScore("empty1");
                CommonScore empkil = sidebar.getScore("empkil");
                CommonScore amp = sidebar.getScore("amp");
                
                CommonScore timer = sidebar.getScore("timer");
                CommonScore timerInt = sidebar.getScore("timerInt");
                CommonScore timerNoEvent = sidebar.getScore("timerNoEvent");
                
                CommonScore totonlineInt = sidebar.getScore("totonlineInt");
                CommonScore totplayersInt = sidebar.getScore("totplayersInt");
                CommonScore balanceInt = sidebar.getScore("balanceInt");

                if (totonline == null) {
                  totonline = sidebar.createScore("totonline", ChatColor.GREEN + "" + ChatColor.BOLD + "Now Online", 5);
                  totplayers = sidebar.createScore("totplayers", ChatColor.GREEN + "" + ChatColor.BOLD + "TotalPlayers", 2);
                  balance = sidebar.createScore("balance", ChatColor.GREEN + "" + ChatColor.BOLD + "Your Money", 8);
                	timer = sidebar.createScore("timer", ChatColor.AQUA + "" + ChatColor.BOLD + "Event in", -1);
                 }
                else {
                	/*
                	 * money
                	 * moneyint
                	 * 
                	 * online
                	 * onlineint
                	 * 
                	 * totplayers
                	 * totplayersint                	  
                	 */
                    totonlineInt = sidebar.createScore("totonlineInt", Integer.toString(onlinePlayers), 4);
                    totplayersInt = sidebar.createScore("totplayersInt", Integer.toString(totalPlayers), 1);
                    balanceInt = sidebar.createScore("balanceInt", Integer.toString(balance1), 7);       
                    empty1 = sidebar.createScore("empty1", "      ", 3);      
                    empkil = sidebar.createScore("empkil", "      ", 6); 
                    amp = sidebar.createScore("amp", "       ", 0); 
                    
                    if (eventTimer == 0) {
                    	sidebar.removeScore("timerInt");
                    	timerNoEvent = sidebar.createScore("timerNoEvent", "No event!", -3);
                    	
                    	timerNoEvent.setValue(-3);
                    	timerNoEvent.update();
                    }else {
                    	sidebar.removeScore("timerNoEvent");
                    	timerInt = sidebar.createScore("timerInt", Integer.toString(eventTimer), -3);
                    	
                    	timerInt.setValue(-3);
                    	timerInt.update();
                    }
                	
                	balance.setValue(8);
                    balance.update();
                    balanceInt.setValue(7);
                    balanceInt.update();
                    
                    empkil.setValue(6);
                    empkil.update();
                    
                    totonline.setValue(5);
                    totonline.update();
                    totonlineInt.setValue(4);
                    totonlineInt.update();
                    
                    empty1.setValue(3);
                    empty1.update();
                    
                    totplayers.setValue(2);
                    totplayers.update();
                    totplayersInt.setValue(1);
                    totplayersInt.update();
                    
                    amp.setValue(0);
                    amp.update();
                    
                	timer.setValue(-2);
                	timer.update();
                    
                    for (Player all : Bukkit.getServer().getOnlinePlayers()) {
                 	    CommonScoreboard board2 = CommonScoreboard.get(all);
                	    CommonObjective sidebar2 = board2.getObjective(CommonScoreboard.Display.SIDEBAR);
                	    sidebar2.show();
                	    sidebar2.setDisplayName(ChatColor.GOLD + "" + ChatColor.BOLD + "The-Network");
                    }
                }
              }
            }
          }
          , 20L, 20L);
	}
	
	public void StartTimer() {
	    Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(this, new Runnable() {
	        @Override
	        public void run() {
	            Bukkit.getServer().broadcastMessage(MsgType.GRAY 
	            		+ " Server is in BETA! Please report bugs and glitches!");
	            Timer(); // Call the other timer
	        }
	    }, 1000); // After 50 seconds
	}
	
	public void Timer() {
	    Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(this, new Runnable() {
	        @Override
	        public void run() {
	            Bukkit.getServer().broadcastMessage(MsgType.GRAY 
	            		+ " We are working on a network server, this is part of our plan!");
	            AnotherTimer();
	        }
	    }, 1000);
	}
	
	public void AnotherTimer() {
	    Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(this, new Runnable() {
	        @Override
	        public void run() {
	            Bukkit.getServer().broadcastMessage(MsgType.GRAY  
	            		+ " type /shop anywhere to open the shop! Or use the signs at the spawn!");
	            StartTimer();
	        }
	    }, 1000);
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
	
	static { //Normal menu
		
		ItemStack menu1 = new ItemStack(Material.IRON_SPADE);
		ItemMeta shop1 = menu1.getItemMeta();
		shop1.setDisplayName(ChatColor.AQUA + "Sell Menu!");
		menu1.setItemMeta(shop1);
		
		menu.contains(menu1);
		menu.setItem(3, menu1);
		
		ItemStack menu2 = new ItemStack(Material.DIAMOND_SPADE);
		ItemMeta shop2 = menu2.getItemMeta();
		shop2.setDisplayName(ChatColor.GOLD + "Buy Menu!");
		menu2.setItemMeta(shop2);
		
		menu.contains(menu2);
		menu.setItem(5, menu2);
	}
	
	static { //Buy menu
		
		ItemStack menu1 = new ItemStack(Material.ARROW);
		ItemMeta shop1 = menu1.getItemMeta();
			shop1.setDisplayName(ChatColor.AQUA + "Go Back!");
			menu1.setItemMeta(shop1);
				
			buy.contains(menu1);
			buy.setItem(35, menu1);
		
		ItemStack menu2 = new ItemStack(Material.DIAMOND);
		ItemMeta shop2 = menu2.getItemMeta();
			shop2.setDisplayName(ChatColor.GOLD + "Diamond");
			ArrayList<String> iron2 = new ArrayList<String>();
			iron2.add(ChatColor.GRAY + "");
			iron2.add(ChatColor.GOLD + "Cost: " + ChatColor.RED + diamondPrice);
			iron2.add(ChatColor.GRAY + "Right click to buy this item!");
			shop2.setLore(iron2);
			menu2.setItemMeta(shop2);
				
			buy.contains(menu2);
			buy.setItem(0, menu2);
		
		ItemStack menu3 = new ItemStack(Material.COAL);
		ItemMeta shop3 = menu3.getItemMeta();
			shop3.setDisplayName(ChatColor.GOLD + "Coal");
			ArrayList<String> iron3 = new ArrayList<String>();
			iron3.add(ChatColor.GRAY + "");
			iron3.add(ChatColor.GOLD + "Cost: " + ChatColor.RED + coalPrice);
			iron3.add(ChatColor.GRAY + "Right click to buy this item!");
			shop3.setLore(iron3);
			menu3.setItemMeta(shop3);
				
			buy.contains(menu3);
			buy.setItem(1, menu3);

		ItemStack menu4 = new ItemStack(Material.IRON_INGOT);
		ItemMeta shop4 = menu4.getItemMeta();
			shop4.setDisplayName(ChatColor.GOLD + "Iron");
			ArrayList<String> iron4 = new ArrayList<String>();
			iron4.add(ChatColor.GRAY + "");
			iron4.add(ChatColor.GOLD + "Cost: " + ChatColor.RED + ironPrice);
			iron4.add(ChatColor.GRAY + "Right click to buy this item!");
			shop4.setLore(iron4);
			menu4.setItemMeta(shop4);
				
			buy.contains(menu4);
			buy.setItem(2, menu4);

		ItemStack menu5 = new ItemStack(Material.GOLD_INGOT);
		ItemMeta shop5 = menu5.getItemMeta();
			shop5.setDisplayName(ChatColor.GOLD + "Gold");
			ArrayList<String> iron5 = new ArrayList<String>();
			iron5.add(ChatColor.GRAY + "");
			iron5.add(ChatColor.GOLD + "Cost: " + ChatColor.RED + goldPrice);
			iron5.add(ChatColor.GRAY + "Right click to buy this item!");
			shop5.setLore(iron5);
			menu5.setItemMeta(shop5);
				
			buy.contains(menu5);
			buy.setItem(3, menu5);

		ItemStack menu6 = new ItemStack(Material.CARROT_STICK);
		ItemMeta shop6 = menu6.getItemMeta();
			shop6.setDisplayName(ChatColor.GOLD + "Carrot on an stick!");
			ArrayList<String> iron6 = new ArrayList<String>();
			iron6.add(ChatColor.GRAY + "");
			iron6.add(ChatColor.GOLD + "Cost: " + ChatColor.RED + carrotOnAnStickPrice);
			iron6.add(ChatColor.GRAY + "Right click to buy this item!");
			shop6.setLore(iron6);
			menu6.setItemMeta(shop6);
			
			buy.contains(menu6);
			buy.setItem(4, menu6);

		ItemStack menu7 = new ItemStack(Material.GOLD_NUGGET);
		ItemMeta shop7 = menu7.getItemMeta();
			shop7.setDisplayName(ChatColor.GOLD + "Gold Nugget");
			ArrayList<String> iron7 = new ArrayList<String>();
			iron7.add(ChatColor.GRAY + "");
			iron7.add(ChatColor.GOLD + "Cost: " + ChatColor.RED + goldNuggetPrice);
			iron7.add(ChatColor.GRAY + "Right click to buy this item!");
			shop7.setLore(iron7);
			menu7.setItemMeta(shop7);
				
			buy.contains(menu7);
			buy.setItem(5, menu7);

		ItemStack menu8 = new ItemStack(Material.BLAZE_ROD);
		ItemMeta shop8 = menu8.getItemMeta();
			shop8.setDisplayName(ChatColor.GOLD + "Blaze rod");
			ArrayList<String> iron8 = new ArrayList<String>();
			iron8.add(ChatColor.GRAY + "");
			iron8.add(ChatColor.GOLD + "Cost: " + ChatColor.RED + blazeRodePrice);
			iron8.add(ChatColor.GRAY + "Right click to buy this item!");
			shop8.setLore(iron8);
			menu8.setItemMeta(shop8);
				
			buy.contains(menu8);
			buy.setItem(6, menu8);

		ItemStack menu9 = new ItemStack(Material.QUARTZ);
		ItemMeta shop9 = menu9.getItemMeta();
			shop9.setDisplayName(ChatColor.GOLD + "Quartz");
			ArrayList<String> iron9 = new ArrayList<String>();
			iron9.add(ChatColor.GRAY + "");
			iron9.add(ChatColor.GOLD + "Cost: " + ChatColor.RED + quartzPrice);
			iron9.add(ChatColor.GRAY + "Right click to buy this item!");
			shop9.setLore(iron9);
			menu9.setItemMeta(shop9);
			
			buy.contains(menu9);
			buy.setItem(7, menu9);

		ItemStack menu10 = new ItemStack(Material.QUARTZ_ORE);
		ItemMeta shop10 = menu10.getItemMeta();
		shop10.setDisplayName(ChatColor.GOLD + "Quartz Ore");
		ArrayList<String> iron10 = new ArrayList<String>();
		iron10.add(ChatColor.GRAY + "");
		iron10.add(ChatColor.GOLD + "Cost: " + ChatColor.RED + quartzBlockPrice);
		iron10.add(ChatColor.GRAY + "Right click to buy this item!");
		shop10.setLore(iron10);
		menu10.setItemMeta(shop10);
				
		buy.contains(menu10);
		buy.setItem(8, menu10);
		
		ItemStack menu11 = new ItemStack(Material.CHEST);
		ItemMeta shop11 = menu11.getItemMeta();
		shop11.setDisplayName(ChatColor.GOLD + "Chest");
		ArrayList<String> iron11 = new ArrayList<String>();
		iron11.add(ChatColor.GRAY + "");
		iron11.add(ChatColor.GOLD + "Cost: " + ChatColor.RED + chestPrice);
		iron11.add(ChatColor.GRAY + "Right click to buy this item!");
		shop11.setLore(iron11);
		menu11.setItemMeta(shop11);
				
		buy.contains(menu11);
		buy.setItem(9, menu11);
		
		ItemStack menu12 = new ItemStack(Material.ENCHANTMENT_TABLE);
		ItemMeta shop12 = menu12.getItemMeta();
		shop12.setDisplayName(ChatColor.GOLD + "Enchantment Table");
		ArrayList<String> iron12 = new ArrayList<String>();
		iron12.add(ChatColor.GRAY + "");
		iron12.add(ChatColor.GOLD + "Cost: " + ChatColor.RED + enchantmentTablePrice);
		iron12.add(ChatColor.GRAY + "Right click to buy this item!");
		shop12.setLore(iron12);
		menu12.setItemMeta(shop12);
				
		buy.contains(menu12);
		buy.setItem(10, menu12);
		
		@SuppressWarnings("deprecation")
		ItemStack menu13 = new ItemStack(Material.getMaterial(175));
		ItemMeta shop13 = menu13.getItemMeta();
		shop13.setDisplayName(ChatColor.GOLD + "Sun Flower");
		ArrayList<String> iron13 = new ArrayList<String>();
		iron13.add(ChatColor.GRAY + "");
		iron13.add(ChatColor.GOLD + "Cost: " + ChatColor.RED + sunFlowerPrice);
		iron13.add(ChatColor.GRAY + "Right click to buy this item!");
		shop13.setLore(iron13);
		menu13.setItemMeta(shop13);
				
		buy.contains(menu13);
		buy.setItem(11, menu13);
		
		ItemStack menu14 = new ItemStack(Material.REDSTONE_BLOCK);
		ItemMeta shop14 = menu14.getItemMeta();
		shop14.setDisplayName(ChatColor.GOLD + "Redstone Block");
		ArrayList<String> iron14 = new ArrayList<String>();
		iron14.add(ChatColor.GRAY + "");
		iron14.add(ChatColor.GOLD + "Cost: " + ChatColor.RED + redstoneBlockPrice);
		iron14.add(ChatColor.GRAY + "Right click to buy this item!");
		shop14.setLore(iron14);
		menu14.setItemMeta(shop14);
				
		buy.contains(menu14);
		buy.setItem(12, menu14);
		
		ItemStack menu15 = new ItemStack(Material.BOOK);
		ItemMeta shop15 = menu15.getItemMeta();
		shop15.setDisplayName(ChatColor.GOLD + "Book");
		ArrayList<String> iron15 = new ArrayList<String>();
		iron15.add(ChatColor.GRAY + "");
		iron15.add(ChatColor.GOLD + "Cost: " + ChatColor.RED + bookPrice);
		iron15.add(ChatColor.GRAY + "Right click to buy this item!");
		shop15.setLore(iron15);
		menu15.setItemMeta(shop15);
				
		buy.contains(menu15);
		buy.setItem(13, menu15);
		
		ItemStack menu16 = new ItemStack(Material.REDSTONE_LAMP_ON);
		ItemMeta shop16 = menu16.getItemMeta();
		shop16.setDisplayName(ChatColor.GOLD + "RedstoneLamp");
		ArrayList<String> iron16 = new ArrayList<String>();
		iron16.add(ChatColor.GRAY + "");
		iron16.add(ChatColor.GOLD + "Cost: " + ChatColor.RED + "85");
		iron16.add(ChatColor.GRAY + "Right click to buy this item!");
		shop16.setLore(iron16);
		menu16.setItemMeta(shop16);
				
		buy.contains(menu16);
		buy.setItem(14, menu16);
		
		ItemStack menu17 = new ItemStack(Material.PAINTING);
		ItemMeta shop17 = menu17.getItemMeta();
		shop17.setDisplayName(ChatColor.GOLD + "Painting");
		ArrayList<String> iron17 = new ArrayList<String>();
		iron17.add(ChatColor.GRAY + "");
		iron17.add(ChatColor.GOLD + "Cost: " + ChatColor.RED + paintingPrice);
		iron17.add(ChatColor.GRAY + "Right click to buy this item!");
		shop17.setLore(iron17);
		menu17.setItemMeta(shop17);
				
		buy.contains(menu17);
		buy.setItem(18, menu17);
		
		ItemStack menu18 = new ItemStack(Material.COBBLE_WALL);
		ItemMeta shop18 = menu18.getItemMeta();
		shop18.setDisplayName(ChatColor.GOLD + "CobbleStone Wall");
		ArrayList<String> iron18 = new ArrayList<String>();
		iron18.add(ChatColor.GRAY + "");
		iron18.add(ChatColor.GOLD + "Cost: " + ChatColor.RED + coblleWallPrice);
		iron18.add(ChatColor.GRAY + "Right click to buy this item!");
		shop18.setLore(iron18);
		menu18.setItemMeta(shop18);
				
		buy.contains(menu18);
		buy.setItem(19, menu18);
		
		ItemStack menu19 = new ItemStack(Material.FLINT_AND_STEEL);
		ItemMeta shop19 = menu19.getItemMeta();
		shop19.setDisplayName(ChatColor.GOLD + "Flint and Steel");
		ArrayList<String> iron19 = new ArrayList<String>();
		iron19.add(ChatColor.GRAY + "");
		iron19.add(ChatColor.GOLD + "Cost: " + ChatColor.RED + flintAndSteelPrice);
		iron19.add(ChatColor.GRAY + "Right click to buy this item!");
		shop19.setLore(iron19);
		menu19.setItemMeta(shop19);
				
		buy.contains(menu19);
		buy.setItem(23, menu19);
		
		ItemStack menu20 = new ItemStack(Material.EGG);
		ItemMeta sop20 = menu20.getItemMeta();
		sop20.setDisplayName(ChatColor.GOLD + "Egg");
		ArrayList<String> iron20 = new ArrayList<String>();
		iron20.add(ChatColor.GRAY + "");
		iron20.add(ChatColor.GOLD + "Cost: " + ChatColor.RED + eggPrice);
		iron20.add(ChatColor.GRAY + "Right click to buy this item!");
		sop20.setLore(iron20);
		menu20.setItemMeta(sop20);
				
		buy.contains(menu20);
		buy.setItem(24, menu20);
		
		ItemStack menu21 = new ItemStack(Material.ENDER_PEARL);
		ItemMeta shop21 = menu21.getItemMeta();
		shop21.setDisplayName(ChatColor.GOLD + "EnderPearl");
		ArrayList<String> iron21 = new ArrayList<String>();
		iron21.add(ChatColor.GRAY + "");
		iron21.add(ChatColor.GOLD + "Cost: " + ChatColor.RED + enderPearlPrice);
		iron21.add(ChatColor.GRAY + "Right click to buy this item!");
		shop21.setLore(iron21);
		menu21.setItemMeta(shop21);
				
		buy.contains(menu21);
		buy.setItem(25, menu21);
		
		@SuppressWarnings("deprecation")
		ItemStack menu22 = new ItemStack(Material.getMaterial(384));
		ItemMeta shop22 = menu22.getItemMeta();
		shop22.setDisplayName(ChatColor.GOLD + "Enchantment Bottle");
		ArrayList<String> iron22 = new ArrayList<String>();
		iron22.add(ChatColor.GRAY + "");
		iron22.add(ChatColor.GOLD + "Cost: " + ChatColor.RED + enchantmentBottlePrice);
		iron22.add(ChatColor.GRAY + "Right click to buy this item!");
		shop22.setLore(iron22);
		menu22.setItemMeta(shop22);
				
		buy.contains(menu22);
		buy.setItem(26, menu22);
		
		ItemStack menu23 = new ItemStack(Material.CAKE);
		ItemMeta shop23 = menu23.getItemMeta();
		shop23.setDisplayName(ChatColor.GOLD + "Cake");
		ArrayList<String> iron23 = new ArrayList<String>();
		iron23.add(ChatColor.GRAY + "");
		iron23.add(ChatColor.GOLD + "Cost: " + ChatColor.RED + cakePrice);
		iron23.add(ChatColor.GRAY + "Right click to buy this item!");
		shop23.setLore(iron23);
		menu23.setItemMeta(shop23);
				
		buy.contains(menu23);
		buy.setItem(27, menu23);
		
		ItemStack menu24 = new ItemStack(Material.BOAT);
		ItemMeta shop24 = menu24.getItemMeta();
		shop24.setDisplayName(ChatColor.GOLD + "Boat");
		ArrayList<String> iron24 = new ArrayList<String>();
		iron24.add(ChatColor.GRAY + "");
		iron24.add(ChatColor.GOLD + "Cost: " + ChatColor.RED + boatPrice);
		iron24.add(ChatColor.GRAY + "Right click to buy this item!");
		shop24.setLore(iron24);
		menu24.setItemMeta(shop24);
				
		buy.contains(menu24);
		buy.setItem(28, menu24);
		
		ItemStack menu25 = new ItemStack(Material.CARROT_ITEM);
		ItemMeta shop25 = menu25.getItemMeta();
		shop25.setDisplayName(ChatColor.GOLD + "Carrot");
		ArrayList<String> iron25 = new ArrayList<String>();
		iron25.add(ChatColor.GRAY + "");
		iron25.add(ChatColor.GOLD + "Cost: " + ChatColor.RED + carrotPrice);
		iron25.add(ChatColor.GRAY + "Right click to buy this item!");
		shop25.setLore(iron25);
		menu25.setItemMeta(shop25);
				
		buy.contains(menu25);
		buy.setItem(29, menu25);
		
		ItemStack menu26 = new ItemStack(Material.POTATO_ITEM);
		ItemMeta shop26 = menu26.getItemMeta();
		shop26.setDisplayName(ChatColor.GOLD + "Potato");
		ArrayList<String> iron26 = new ArrayList<String>();
		iron26.add(ChatColor.GRAY + "");
		iron26.add(ChatColor.GOLD + "Cost: " + ChatColor.RED + patatoPrice);
		iron26.add(ChatColor.GRAY + "Right click to buy this item!");
		shop26.setLore(iron26);
		menu26.setItemMeta(shop26);
				
		buy.contains(menu26);
		buy.setItem(30, menu26);
		
		ItemStack menu27 = new ItemStack(Material.PUMPKIN_PIE);
		ItemMeta shop27 = menu27.getItemMeta();
		shop27.setDisplayName(ChatColor.GOLD + "Pumpkin Pie");
		ArrayList<String> iron27 = new ArrayList<String>();
		iron27.add(ChatColor.GRAY + "");
		iron27.add(ChatColor.GOLD + "Cost: " + ChatColor.RED + pumpkinPiePrice);
		iron27.add(ChatColor.GRAY + "Right click to buy this item!");
		shop27.setLore(iron27);
		menu27.setItemMeta(shop27);
				
		buy.contains(menu27);
		buy.setItem(31, menu27);
		
		ItemStack menu28 = new ItemStack(Material.FISHING_ROD);
		ItemMeta shop28 = menu28.getItemMeta();
		shop28.setDisplayName(ChatColor.GOLD + "Fishing Rod");
		ArrayList<String> iron28 = new ArrayList<String>();
		iron28.add(ChatColor.GRAY + "");
		iron28.add(ChatColor.GOLD + "Cost: " + ChatColor.RED + fishingRodPrice);
		iron28.add(ChatColor.GRAY + "Right click to buy this item!");
		shop28.setLore(iron28);
		menu28.setItemMeta(shop28);
				
		buy.contains(menu28);
		buy.setItem(32, menu28);
		
		ItemStack menu29 = new ItemStack(Material.RAW_BEEF);
		ItemMeta shop29 = menu29.getItemMeta();
		shop29.setDisplayName(ChatColor.GOLD + "Raw Beef");
		ArrayList<String> iron29 = new ArrayList<String>();
		iron29.add(ChatColor.GRAY + "");
		iron29.add(ChatColor.GOLD + "Cost: " + ChatColor.RED + rawBeefPrice);
		iron29.add(ChatColor.GRAY + "Right click to buy this item!");
		shop29.setLore(iron29);
		menu29.setItemMeta(shop29);
				
		buy.contains(menu29);
		buy.setItem(33, menu29);
		
		
	}
	
	static { //Sell menu
		
		ItemStack menu1 = new ItemStack(Material.ARROW);
		ItemMeta shop1 = menu1.getItemMeta();
			shop1.setDisplayName(ChatColor.GOLD + "Go Back!");
			menu1.setItemMeta(shop1);
				
			sell.contains(menu1);
			sell.setItem(26, menu1);
		
		ItemStack menu2 = new ItemStack(Material.LOG);
		ItemMeta shop2 = menu2.getItemMeta();
		shop2.setDisplayName(ChatColor.AQUA + "LOG");
		ArrayList<String> iron2 = new ArrayList<String>();
		iron2.add(ChatColor.GRAY + "");
		iron2.add(ChatColor.AQUA + "You get: " + ChatColor.RED + "10");
		iron2.add(ChatColor.GRAY + "Right click to Sell this item!");
		shop2.setLore(iron2);
		menu2.setItemMeta(shop2);
		
		sell.contains(menu2);
		sell.setItem(0, menu2);
		
		ItemStack menu3 = new ItemStack(Material.COBBLESTONE);
		ItemMeta shop3 = menu3.getItemMeta();
		shop3.setDisplayName(ChatColor.AQUA + "CobbleStone");
		ArrayList<String> iron3 = new ArrayList<String>();
		iron3.add(ChatColor.GRAY + "");
		iron3.add(ChatColor.AQUA + "You get: " + ChatColor.RED + "1");
		iron3.add(ChatColor.GRAY + "Right click to Sell this item!");
		shop3.setLore(iron3);
		menu3.setItemMeta(shop3);
		
		sell.contains(menu3);
		sell.setItem(1, menu3);
		
		ItemStack menu4 = new ItemStack(Material.WOOD);
		ItemMeta shop4 = menu4.getItemMeta();
		shop4.setDisplayName(ChatColor.AQUA + "Wood");
		ArrayList<String> iron4 = new ArrayList<String>();
		iron4.add(ChatColor.GRAY + "");
		iron4.add(ChatColor.AQUA + "You get: " + ChatColor.RED + "2");
		iron4.add(ChatColor.GRAY + "Right click to Sell this item!");
		shop4.setLore(iron4);
		menu4.setItemMeta(shop4);
		
		sell.contains(menu4);
		sell.setItem(2, menu4);
		
		ItemStack menu5 = new ItemStack(Material.WOOL);
		ItemMeta shop5 = menu5.getItemMeta();
		shop5.setDisplayName(ChatColor.AQUA + "Wool");
		ArrayList<String> iron5 = new ArrayList<String>();
		iron5.add(ChatColor.GRAY + "");
		iron5.add(ChatColor.AQUA + "You get: " + ChatColor.RED + "10");
		iron5.add(ChatColor.GRAY + "Right click to Sell this item!");
		shop5.setLore(iron5);
		menu5.setItemMeta(shop5);
		
		sell.contains(menu5);
		sell.setItem(3, menu5);
		
		ItemStack menu6 = new ItemStack(Material.BED);
		ItemMeta shop6 = menu6.getItemMeta();
		shop6.setDisplayName(ChatColor.AQUA + "Bed");
		ArrayList<String> iron6 = new ArrayList<String>();
		iron6.add(ChatColor.GRAY + "");
		iron6.add(ChatColor.AQUA + "You get: " + ChatColor.RED + "20");
		iron6.add(ChatColor.GRAY + "Right click to Sell this item!");
		shop6.setLore(iron6);
		menu6.setItemMeta(shop6);
		
		sell.contains(menu6);
		sell.setItem(4, menu6);
		
		ItemStack menu7 = new ItemStack(Material.GOLD_BLOCK);
		ItemMeta shop7 = menu7.getItemMeta();
		shop7.setDisplayName(ChatColor.AQUA + "Gold Block");
		ArrayList<String> iron7 = new ArrayList<String>();
		iron7.add(ChatColor.GRAY + "");
		iron7.add(ChatColor.AQUA + "You get: " + ChatColor.RED + "50");
		iron7.add(ChatColor.GRAY + "Right click to Sell this item!");
		shop7.setLore(iron7);
		menu7.setItemMeta(shop7);
		
		sell.contains(menu7);
		sell.setItem(5, menu7);
		
		ItemStack menu8 = new ItemStack(Material.IRON_BLOCK);
		ItemMeta shop8 = menu8.getItemMeta();
		shop8.setDisplayName(ChatColor.AQUA + "Iron Block");
		ArrayList<String> iron8 = new ArrayList<String>();
		iron8.add(ChatColor.GRAY + "");
		iron8.add(ChatColor.AQUA + "You get: " + ChatColor.RED + "80");
		iron8.add(ChatColor.GRAY + "Right click to Sell this item!");
		shop8.setLore(iron8);
		menu8.setItemMeta(shop8);
		
		sell.contains(menu8);
		sell.setItem(6, menu8);
		
		ItemStack menu9 = new ItemStack(Material.DIAMOND_BLOCK);
		ItemMeta shop9 = menu9.getItemMeta();
		shop9.setDisplayName(ChatColor.AQUA + "Diamond Block");
		ArrayList<String> iron9 = new ArrayList<String>();
		iron9.add(ChatColor.GRAY + "");
		iron9.add(ChatColor.AQUA + "You get: " + ChatColor.RED + "200");
		iron9.add(ChatColor.GRAY + "Right click to Sell this item!");
		shop9.setLore(iron9);
		menu9.setItemMeta(shop9);
		
		sell.contains(menu9);
		sell.setItem(7, menu9);
		
		ItemStack menu10 = new ItemStack(Material.EMERALD_BLOCK);
		ItemMeta shop10 = menu10.getItemMeta();
		shop10.setDisplayName(ChatColor.AQUA + "Emerald Block");
		ArrayList<String> iron10 = new ArrayList<String>();
		iron10.add(ChatColor.GRAY + "");
		iron10.add(ChatColor.AQUA + "You get: " + ChatColor.RED + "300");
		iron10.add(ChatColor.GRAY + "Right click to Sell this item!");
		shop10.setLore(iron10);
		menu10.setItemMeta(shop10);
		
		sell.contains(menu10);
		sell.setItem(8, menu10);
		
		ItemStack menu11 = new ItemStack(Material.NETHER_BRICK_ITEM);
		ItemMeta shop11 = menu11.getItemMeta();
		shop11.setDisplayName(ChatColor.AQUA + "Nether Brick");
		ArrayList<String> iron11 = new ArrayList<String>();
		iron11.add(ChatColor.GRAY + "");
		iron11.add(ChatColor.AQUA + "You get: " + ChatColor.RED + "50");
		iron11.add(ChatColor.GRAY + "Right click to Sell this item!");
		shop11.setLore(iron11);
		menu11.setItemMeta(shop11);
		
		sell.contains(menu11);
		sell.setItem(9, menu11);
		
		ItemStack menu12 = new ItemStack(Material.MELON_SEEDS);
		ItemMeta shop12 = menu12.getItemMeta();
		shop12.setDisplayName(ChatColor.AQUA + "Melon Seeds");
		ArrayList<String> iron12 = new ArrayList<String>();
		iron12.add(ChatColor.GRAY + "");
		iron12.add(ChatColor.AQUA + "You get: " + ChatColor.RED + "20");
		iron12.add(ChatColor.GRAY + "Right click to Sell this item!");
		shop12.setLore(iron12);
		menu12.setItemMeta(shop12);
		
		sell.contains(menu12);
		sell.setItem(10, menu12);
		
		@SuppressWarnings("deprecation")
		ItemStack menu13 = new ItemStack(Material.getMaterial(45));
		ItemMeta sop13 = menu13.getItemMeta();
		sop13.setDisplayName(ChatColor.AQUA + "Bricks");
		ArrayList<String> iron13 = new ArrayList<String>();
		iron13.add(ChatColor.GRAY + "");
		iron13.add(ChatColor.AQUA + "You get: " + ChatColor.RED + "30");
		iron13.add(ChatColor.GRAY + "Right click to Sell this item!");
		sop13.setLore(iron13);
		menu13.setItemMeta(sop13);
		
		sell.contains(menu13);
		sell.setItem(11, menu13);
		
		ItemStack menu14 = new ItemStack(Material.BOOKSHELF);
		ItemMeta shop14 = menu14.getItemMeta();
		shop14.setDisplayName(ChatColor.AQUA + "BookShelf");
		ArrayList<String> iron14 = new ArrayList<String>();
		iron14.add(ChatColor.GRAY + "");
		iron14.add(ChatColor.AQUA + "You get: " + ChatColor.RED + "50");
		iron14.add(ChatColor.GRAY + "Right click to Sell this item!");
		shop14.setLore(iron14);
		menu14.setItemMeta(shop14);
		
		sell.contains(menu14);
		sell.setItem(12, menu14);
		
		ItemStack menu15 = new ItemStack(Material.OBSIDIAN);
		ItemMeta shop15 = menu15.getItemMeta();
		shop15.setDisplayName(ChatColor.AQUA + "Obsidian");
		ArrayList<String> iron15 = new ArrayList<String>();
		iron15.add(ChatColor.GRAY + "");
		iron15.add(ChatColor.AQUA + "You get: " + ChatColor.RED + "40");
		iron15.add(ChatColor.GRAY + "Right click to Sell this item!");
		shop15.setLore(iron15);
		menu15.setItemMeta(shop15);
		
		sell.contains(menu15);
		sell.setItem(13, menu15);
		
		ItemStack menu16 = new ItemStack(Material.QUARTZ_BLOCK);
		ItemMeta shop16 = menu16.getItemMeta();
		shop16.setDisplayName(ChatColor.AQUA + "Block of quartz");
		ArrayList<String> iron16 = new ArrayList<String>();
		iron16.add(ChatColor.GRAY + "");
		iron16.add(ChatColor.AQUA + "You get: " + ChatColor.RED + "40");
		iron16.add(ChatColor.GRAY + "Right click to Sell this item!");
		shop16.setLore(iron16);
		menu16.setItemMeta(shop16);
		
		sell.contains(menu16);
		sell.setItem(14, menu16);
		
		ItemStack menu17 = new ItemStack(Material.SOUL_SAND);
		ItemMeta shop17 = menu17.getItemMeta();
		shop17.setDisplayName(ChatColor.AQUA + "Soul Sand");
		ArrayList<String> iron17 = new ArrayList<String>();
		iron17.add(ChatColor.GRAY + "");
		iron17.add(ChatColor.AQUA + "You get: " + ChatColor.RED + "60");
		iron17.add(ChatColor.GRAY + "Right click to Sell this item!");
		shop17.setLore(iron17);
		menu17.setItemMeta(shop17);
		
		sell.contains(menu17);
		sell.setItem(15, menu17);
		
		ItemStack menu18 = new ItemStack(Material.STAINED_GLASS);
		ItemMeta shop18 = menu18.getItemMeta();
		shop18.setDisplayName(ChatColor.AQUA + "Stained Class");
		ArrayList<String> iron18 = new ArrayList<String>();
		iron18.add(ChatColor.GRAY + "");
		iron18.add(ChatColor.AQUA + "You get: " + ChatColor.RED + "10");
		iron18.add(ChatColor.GRAY + "Right click to Sell this item!");
		shop18.setLore(iron18);
		menu18.setItemMeta(shop18);
		
		sell.contains(menu18);
		sell.setItem(16, menu18);
		
		ItemStack menu19 = new ItemStack(Material.APPLE);
		ItemMeta shop19 = menu19.getItemMeta();
		shop19.setDisplayName(ChatColor.AQUA + "Bed");
		ArrayList<String> iron19 = new ArrayList<String>();
		iron19.add(ChatColor.GRAY + "");
		iron19.add(ChatColor.AQUA + "You get: " + ChatColor.RED + "10");
		iron19.add(ChatColor.GRAY + "Right click to Sell this item!");
		shop19.setLore(iron19);
		menu19.setItemMeta(shop19);
		
		sell.contains(menu19);
		sell.setItem(17, menu19);
		
		ItemStack menu20 = new ItemStack(Material.HAY_BLOCK);
		ItemMeta shop20 = menu20.getItemMeta();
		shop20.setDisplayName(ChatColor.AQUA + "Hay Bale");
		ArrayList<String> iron20 = new ArrayList<String>();
		iron20.add(ChatColor.GRAY + "");
		iron20.add(ChatColor.AQUA + "You get: " + ChatColor.RED + "50");
		iron20.add(ChatColor.GRAY + "Right click to Sell this item!");
		shop20.setLore(iron20);
		menu20.setItemMeta(shop20);
		
		sell.contains(menu20);
		sell.setItem(18, menu20);
		
		ItemStack menu21 = new ItemStack(Material.COAL_BLOCK);
		ItemMeta shop21 = menu21.getItemMeta();
		shop21.setDisplayName(ChatColor.AQUA + "Coal Block");
		ArrayList<String> iron21 = new ArrayList<String>();
		iron21.add(ChatColor.GRAY + "");
		iron21.add(ChatColor.AQUA + "You get: " + ChatColor.RED + "40");
		iron21.add(ChatColor.GRAY + "Right click to Sell this item!");
		shop21.setLore(iron21);
		menu21.setItemMeta(shop21);
		
		sell.contains(menu21);
		sell.setItem(19, menu21);
		
		ItemStack menu22 = new ItemStack(Material.LAPIS_BLOCK);
		ItemMeta shop22 = menu22.getItemMeta();
		shop22.setDisplayName(ChatColor.AQUA + "Lapis Lazuli Block");
		ArrayList<String> iron22 = new ArrayList<String>();
		iron22.add(ChatColor.GRAY + "");
		iron22.add(ChatColor.AQUA + "You get: " + ChatColor.RED + "60");
		iron22.add(ChatColor.GRAY + "Right click to Sell this item!");
		shop22.setLore(iron22);
		menu22.setItemMeta(shop22);
		
		sell.contains(menu22);
		sell.setItem(20, menu22);
		
		ItemStack menu23 = new ItemStack(Material.BREWING_STAND);
		ItemMeta shop23 = menu23.getItemMeta();
		shop23.setDisplayName(ChatColor.AQUA + "Brewing Stand");
		ArrayList<String> iron23 = new ArrayList<String>();
		iron23.add(ChatColor.GRAY + "");
		iron23.add(ChatColor.AQUA + "You get: " + ChatColor.RED + "80");
		iron23.add(ChatColor.GRAY + "Right click to Sell this item!");
		shop23.setLore(iron23);
		menu23.setItemMeta(shop23);
		
		sell.contains(menu23);
		sell.setItem(21, menu23);
		
		ItemStack menu24 = new ItemStack(Material.GOLDEN_APPLE);
		ItemMeta shop24 = menu24.getItemMeta();
		shop24.setDisplayName(ChatColor.AQUA + "Golden Apple");
		ArrayList<String> iron24 = new ArrayList<String>();
		iron24.add(ChatColor.GRAY + "");
		iron24.add(ChatColor.AQUA + "You get: " + ChatColor.RED + "100");
		iron24.add(ChatColor.GRAY + "Right click to Sell this item!");
		shop24.setLore(iron24);
		menu24.setItemMeta(shop24);
		
		sell.contains(menu24);
		sell.setItem(22, menu24);
		
		ItemStack menu25 = new ItemStack(Material.JACK_O_LANTERN);
		ItemMeta shop25 = menu25.getItemMeta();
		shop25.setDisplayName(ChatColor.AQUA + "Jack o Lantern");
		ArrayList<String> iron25 = new ArrayList<String>();
		iron25.add(ChatColor.GRAY + "");
		iron25.add(ChatColor.AQUA + "You get: " + ChatColor.RED + "20");
		iron25.add(ChatColor.GRAY + "Right click to Sell this item!");
		shop25.setLore(iron25);
		menu25.setItemMeta(shop25);
		
		sell.contains(menu25);
		sell.setItem(23, menu25);
		
		ItemStack menu26 = new ItemStack(Material.MAGMA_CREAM);
		ItemMeta shop26 = menu26.getItemMeta();
		shop26.setDisplayName(ChatColor.AQUA + "Magma Cream");
		ArrayList<String> iron26 = new ArrayList<String>();
		iron26.add(ChatColor.GRAY + "");
		iron26.add(ChatColor.AQUA + "You get: " + ChatColor.RED + "40");
		iron26.add(ChatColor.GRAY + "Right click to Sell this item!");
		shop26.setLore(iron26);
		menu26.setItemMeta(shop26);
		
		sell.contains(menu26);
		sell.setItem(24, menu26);
		
		
		
	}
	
	@EventHandler
	public void onPlayerShowScoreBoard(PlayerJoinEvent e) {
	    Player player = e.getPlayer();
	    CommonScoreboard board = CommonScoreboard.get(player);
	    CommonObjective sidebar = board.getObjective(CommonScoreboard.Display.SIDEBAR);
	    sidebar.show();
	    sidebar.setDisplayName(ChatColor.GOLD + "" + ChatColor.BOLD + "The-Network");
	}
	
	@EventHandler
	public void onSpadeClick(InventoryClickEvent e) {
		Player player = (Player) e.getWhoClicked();
		Inventory inv = e.getInventory();
		ItemStack cl = e.getCurrentItem();
			if (inv.getName().equals(menu.getName())) {
			 	if (e.getCurrentItem().hasItemMeta() && e.getCurrentItem().getItemMeta().getDisplayName().contains(ChatColor.AQUA + "Sell Menu!")) {
			    	if (cl.getType() == Material.IRON_SPADE) {
					e.setCancelled(true);
					player.closeInventory();
					player.openInventory(sell);
			    	}
			    }else if (e.getCurrentItem().hasItemMeta() && e.getCurrentItem().getItemMeta().getDisplayName().contains(ChatColor.GOLD + "Buy Menu!")) {
				    	if (cl.getType() == Material.DIAMOND_SPADE) {
							e.setCancelled(true);
							player.closeInventory();
							player.openInventory(buy);
				    	}
			    	}
			 	}
			}
	
	public void serverConnect(String server, Player p) {
    	ByteArrayDataOutput out = ByteStreams.newDataOutput();
        out.writeUTF("Connect");
        out.writeUTF(server);
		p.sendPluginMessage(this, "BungeeCord", out.toByteArray());
    }
	
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args){
		final Player p = (Player) sender;
		if (cmd.getName().equalsIgnoreCase("shop")) {
			p.openInventory(menu);
			return true;
		}
		if (cmd.getName().equalsIgnoreCase("hub")) {
			serverConnect("lobby", p);
			return true;
		}
		if (cmd.getName().equalsIgnoreCase("vip")) {
			if (p.hasPermission("network.trail")) {
				if (!trail.contains(p)) {
					id = Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(this, new Runnable() {
						@Override
						public void run() {
							ParticleEffect.FLAME.display(p.getLocation(), 0.2f, 0.5f, 0.2f, 0.1F, 70);
						}
					}, 10, 10);
					
		            p.sendMessage(ChatColor.GOLD + "Your trail has been Enabled");
		            trail.add(p);
				}else {
					Bukkit.getServer().getScheduler().cancelTask(id);
					p.sendMessage(ChatColor.GOLD + "Your trail has been disabled");
					trail.remove(p);
					return true;
				}
			}else {
				p.sendMessage(ChatColor.RED + "No permissions for you! Buy VIP to use the trail!");
				return true;
			}
		}
		return false;
	}
	@EventHandler
	public void onPlayerJoinMessage(PlayerJoinEvent e) {
		e.setJoinMessage(ChatColor.RED + "+ " + ChatColor.GOLD + e.getPlayer().getName() + " has joined" + ChatColor.AQUA + " " 
	+ ChatColor.BOLD + "The Network Survival");
	}
	
	@EventHandler
	public void onPlayerLeave(PlayerQuitEvent e) {
		Bukkit.getServer().getScheduler().cancelTask(id);
		
		e.setQuitMessage(ChatColor.RED + "- " + ChatColor.GOLD + e.getPlayer().getName() + " has leaved" + ChatColor.AQUA + " " 
	+ ChatColor.BOLD + "The Network Survival");
	}

}
