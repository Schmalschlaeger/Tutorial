package Recreatie;

import java.util.ArrayList;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Sign;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.SignChangeEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import scoreboard.Main;
import scoreboard.MessageManager.MsgType;

public class GunShop implements Listener {
	
    static Main plugin;
    
    public GunShop(Main m) {
        plugin = m;
    }
    
    private String line1 = "§8[§bGunShop§8]";
    
    private int uzi = 1500;
    private int ultraPistol = 550;
    private int shotgun = 1150;
    private int sniper = 2000;
    private int rocketLauncher = 2500;
	
	/*
	 * User's
	 * 1. Pistol (Basic)
	 * 2. Ultra Pistol
	 * 3. Shotgun 
	 * 4. Uzi
	 * 
	 * Vip's
	 * 5. Sniper
	 * 6. Rocket Launcher
	 */
	
	static {//Gun's
		ItemStack chest2 = new ItemStack(Material.GOLD_HOE);
		ItemMeta plate2 = chest2.getItemMeta();
		plate2.setDisplayName(ChatColor.GOLD + "" + ChatColor.BOLD + "Uzi");
		ArrayList<String> iron2 = new ArrayList<String>();
		iron2.add(ChatColor.GRAY + "");
		iron2.add(ChatColor.GRAY + "Right Click to shoot on the blocks!");
		iron2.add(ChatColor.GRAY + "You can only use this item in minigames!");
		plate2.setLore(iron2);
		chest2.setItemMeta(plate2);
		
		ItemStack chest21 = new ItemStack(Material.WOOD_HOE);
		ItemMeta plate21 = chest21.getItemMeta();
		plate21.setDisplayName(ChatColor.GOLD + "" + ChatColor.BOLD + "Ultra Pistol");
		chest21.addEnchantment(Enchantment.DURABILITY, 1);
		ArrayList<String> iron21 = new ArrayList<String>();
		iron21.add(ChatColor.GRAY + "");
		iron21.add(ChatColor.GRAY + "Right Click to shoot on the blocks!");
		iron21.add(ChatColor.GRAY + "You can only use this item in minigames!");
		plate21.setLore(iron21);
		chest21.setItemMeta(plate21);
		
		ItemStack chest211 = new ItemStack(Material.IRON_HOE);
		ItemMeta plate211 = chest211.getItemMeta();
		plate211.setDisplayName(ChatColor.GOLD + "" + ChatColor.BOLD + "ShotGun");
		ArrayList<String> iron211 = new ArrayList<String>();
		iron211.add(ChatColor.GRAY + "");
		iron211.add(ChatColor.GRAY + "Right Click to shoot on the blocks!");
		iron211.add(ChatColor.GRAY + "You can only use this item in minigames!");
		plate211.setLore(iron211);
		chest211.setItemMeta(plate211);
		
		//VIP SHOP
		
		ItemStack chest2111 = new ItemStack(Material.GOLD_HOE);
		ItemMeta plate2111 = chest2111.getItemMeta();
		plate2111.setDisplayName(ChatColor.GOLD + "" + ChatColor.BOLD + "Sniper");
		chest211.addEnchantment(Enchantment.DURABILITY, 1);
		ArrayList<String> iron2111 = new ArrayList<String>();
		iron2111.add(ChatColor.GRAY + "");
		iron2111.add(ChatColor.GRAY + "Right Click to shoot on the blocks!");
		iron2111.add(ChatColor.GRAY + "You can only use this item as a " + ChatColor.DARK_PURPLE + "VIP");
		plate2111.setLore(iron2111);
		chest2111.setItemMeta(plate2111);
		
		ItemStack chest21111 = new ItemStack(Material.DIAMOND_HOE);
		ItemMeta plate21111 = chest21111.getItemMeta();
		plate21111.setDisplayName(ChatColor.GOLD + "" + ChatColor.BOLD + "Rocket Launcher");
		chest211.addEnchantment(Enchantment.DURABILITY, 1);
		ArrayList<String> iron21111 = new ArrayList<String>();
		iron21111.add(ChatColor.GRAY + "");
		iron21111.add(ChatColor.GRAY + "Right Click to shoot on the blocks!");
		iron21111.add(ChatColor.GRAY + "You can only use this item as a " + ChatColor.DARK_PURPLE + "VIP");
		plate21111.setLore(iron21111);
		chest21111.setItemMeta(plate21111);
	}	
	
	@EventHandler
	public void onSignClick(PlayerInteractEvent e) {	
		if (!(e.getAction() == Action.RIGHT_CLICK_BLOCK)) return;
        if (e.getClickedBlock().getState() instanceof Sign) {
                Sign s = (Sign) e.getClickedBlock().getState();
                if (s.getLine(0).equalsIgnoreCase(line1)) {
                	if (s.getLine(2).equalsIgnoreCase("§oUzi")) {
						if (plugin.getBoolean("Guns.uzi") == false) {
							if(Main.playerPoints.getAPI().take(e.getPlayer().getName(), uzi)) {
							e.getPlayer().sendMessage(ChatColor.GOLD + "Uzi bought! Coins costed: " + s.getLine(3));
							e.getPlayer().sendMessage(ChatColor.GOLD + "Added to your minigame list!");
							plugin.updateConfigPerPlayer("Guns.uzi", true);
							}else {
								e.getPlayer().sendMessage(MsgType.ACCESOIRIES + "Hmm... I think you must check your points before buying this gun!");
								return;
							}
						}else {
							e.getPlayer().sendMessage(ChatColor.RED + "Already an uzi in you minigame list!");
						}
                    	}else if (s.getLine(2).equalsIgnoreCase("§oUltra Pistol")) {
                    		if (plugin.getBoolean("Guns.ultrapistol") == false) {
                    		if(Main.playerPoints.getAPI().take(e.getPlayer().getName(), ultraPistol)) {
                    			e.getPlayer().sendMessage(ChatColor.GOLD + "Ultra Pistol bought! Coins costed: " + s.getLine(3));
    							plugin.updateConfigPerPlayer("Guns.ultrapistol", true);
                    		}else {
								e.getPlayer().sendMessage(MsgType.ACCESOIRIES + "Hmm... I think you must check your points before buying this gun!");
								return;
                    		}
							}else {
								e.getPlayer().sendMessage(ChatColor.RED + "Already an Ultra Pistol in you minigame list!");
							}
                    	}else if (s.getLine(2).equalsIgnoreCase("§oShotGun")) {
                    		if (plugin.getBoolean("Guns.shotgun") == false) {
                    			if(Main.playerPoints.getAPI().take(e.getPlayer().getName(), shotgun)) {
                    				e.getPlayer().sendMessage(ChatColor.GOLD + "Shotgun bought! Coins costed: " + s.getLine(3));
        							plugin.updateConfigPerPlayer("Guns.shotgun", true);
                    			}else {
    								e.getPlayer().sendMessage(MsgType.ACCESOIRIES + "Hmm... I think you must check your points before buying this gun!");
    								return;
                    			}                    				
                    		}else {
								e.getPlayer().sendMessage(ChatColor.RED + "Already an Shotgun in you minigame list!");
                    		}
                    	}else if (s.getLine(2).equalsIgnoreCase("§oSniper")) {
                    		if (e.getPlayer().hasPermission("magic.vip")) {
                    		if (plugin.getBoolean("Guns.vip.sniper") == false) {//
                    		if(Main.playerPoints.getAPI().take(e.getPlayer().getName(), sniper)) {
                    			e.getPlayer().sendMessage(ChatColor.GOLD + "Sniper bought! Coins costed: " + s.getLine(3));
    							plugin.updateConfigPerPlayer("Guns.vip.sniper", true);
                    		}else {
								e.getPlayer().sendMessage(MsgType.ACCESOIRIES + "Hmm... I think you must check your points before buying this gun!");
								return;
                    		}
							}else {
								e.getPlayer().sendMessage(ChatColor.RED + "Already an sniper in you minigame list!");
							}
                    	}else {
                    		e.getPlayer().sendMessage(ChatColor.RED + "Your are not VIP! Buy vip on the site!");
                    	}
                    	}else if (s.getLine(2).equalsIgnoreCase("§oR. Launcher")) {
                    		if (e.getPlayer().hasPermission("magic.vip")) {
                    		if (plugin.getBoolean("Guns.vip.rocketLauncher") == false) {//
                    		if(Main.playerPoints.getAPI().take(e.getPlayer().getName(), rocketLauncher)) {
                    			e.getPlayer().sendMessage(ChatColor.GOLD + "Rocket Launcher bought! Coins costed: " + s.getLine(3));
    							plugin.updateConfigPerPlayer("Guns.vip.rocketLauncher", true);
                    		}else {
								e.getPlayer().sendMessage(MsgType.ACCESOIRIES + "Hmm... I think you must check your points before buying this gun!");
								return;
                    		}
							}else {
								e.getPlayer().sendMessage(ChatColor.RED + "Already an rocket launcher in you minigame list!");
							}
                    	}else {
                    		e.getPlayer().sendMessage(ChatColor.RED + "Your are not VIP! Buy vip on the site!");
                    	}
                    	}
					}
				}
			}
	
	@EventHandler
    public void onSignChange(SignChangeEvent e){
        if (e.getLine(0).equalsIgnoreCase("[GunShop]")){
       	 if (e.getLine(1).equalsIgnoreCase("uzi")){
           e.setLine(0, line1);
           e.setLine(1, "§oClick To Buy");
           e.setLine(2, "§oUzi");
           e.setLine(3, uzi + " §oPoints");
       	 }else if (e.getLine(1).equalsIgnoreCase("ultra pistol")){
       		e.setLine(0, line1);
            e.setLine(1, "§oClick To Buy");
            e.setLine(2, "§oUltra Pistol");
            e.setLine(3, ultraPistol + " §oPoints");
       	}else if (e.getLine(1).equalsIgnoreCase("shotgun")){
       		e.setLine(0, line1);
            e.setLine(1, "§oClick To Buy");
            e.setLine(2, "§oShotGun");
            e.setLine(3, shotgun + " §oPoints");
       	}else if (e.getLine(1).equalsIgnoreCase("sniper")){
       		e.setLine(0, line1);
            e.setLine(1, "§5[VIP]");
            e.setLine(2, "§oSniper");
            e.setLine(3, sniper + " §oPoints");
       	}else if (e.getLine(1).equalsIgnoreCase("rocket launcher")){
       		e.setLine(0, line1);
            e.setLine(1, "§5[VIP]");
            e.setLine(2, "§oR. Launcher");
            e.setLine(3, rocketLauncher + " §oPoints");
       	}else {
       		e.getPlayer().sendMessage(ChatColor.RED + "Gun name not found! Try another!");
      		 e.getBlock().breakNaturally(); 		 
       	}
       	 }
	}

}
