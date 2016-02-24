package main;

import java.util.HashMap;
import java.util.Random;
import java.util.UUID;

import net.milkbowl.vault.economy.EconomyResponse;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.block.Sign;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.SignChangeEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;

import com.cnaude.chairs.api.ChairsAPI;

public class Randomizer implements Listener{
	
	Main plugin;
	
	public Randomizer(Main plugin) {
		this.plugin = plugin;
	}
	
	//Araylist TODO: Money inezetten/storen
	//Randomizer TODO: Random getal
	//Einde/PickAWinner TODO: Checken of gewonnen
	
	public String noCoinsInHand = ChatColor.RED + "Sorry, you must right click with an coin!";
	public String NoMoney = ChatColor.RED + "Sorry, you dont have any coins left! Buy coins @ the shop!";
	public Inventory menu = null;
	
	
	public static HashMap<UUID, Integer> totalMoneyLager = new HashMap<UUID, Integer>();
	public static HashMap<UUID, Integer> totalMoneyHoger = new HashMap<UUID, Integer>();	
	
	public void sayRandomGetal(final Player player, final Block block, final int coins) {
		final Sign sign = (Sign) block.getLocation().getBlock().getState();
		
		final Random random = new Random();
		final int getal = random.nextInt(100);
		
		player.sendMessage(ChatColor.GOLD + "" + ChatColor.BOLD + "You have bet on the randomizer.");
		
		Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
	        @Override
	        public void run() {
	        	player.sendMessage(ChatColor.GOLD + "" + ChatColor.BOLD + "So, now we are picking a random number for you!");
	        }
	    }, 20 * 2);
		
		Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
	        @Override
	        public void run() {
	        	player.sendMessage(ChatColor.GOLD + "" + ChatColor.BOLD + "The previous number was: " + ChatColor.RED + Integer.parseInt(sign.getLine(2)));
	        }
	    }, 20 * 5);
		
		Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
	        @Override
	        public void run() {
	        	player.sendMessage(ChatColor.GOLD + "" + ChatColor.BOLD + "So, you must be higher or lower then this number!");
	        }
	    }, 20 * 7);
		
		Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
	        @Override
	        public void run() {
	        	player.sendMessage(ChatColor.GOLD + "" + ChatColor.BOLD + "We are making a random number for you! A.T.M.");
	        }
	    }, 20 * 8);
		
		Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
	        @Override
	        public void run() {
	        	player.sendMessage(ChatColor.GOLD + "" + ChatColor.BOLD + "Your total random number is: ");
	        }
	    }, 20 * 12);
		
		Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
	        @Override
	        public void run() {
	        	player.sendMessage(ChatColor.RED + "" + ChatColor.BOLD + "     ");
	        	player.sendMessage(ChatColor.RED + "                  " + ChatColor.BOLD + getal);
	        	player.sendMessage(ChatColor.RED + "" + ChatColor.BOLD + "     ");
	        	player.playSound(player.getLocation(), Sound.LEVEL_UP, 1, 1);
	        	
	        	
	        }
	    }, 20 * 13);
		
		Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
	        @Override
	        public void run() {
	        	if (Integer.parseInt(sign.getLine(2)) < getal) {
	        		if (totalMoneyHoger.containsKey(player.getUniqueId())) {
	        			player.sendMessage(ChatColor.RED + "" + ChatColor.BOLD + "CORRECT! You have bet higher, so you have won x 1.5 money reward! Money reward: " + 
	        		    totalMoneyHoger.get(player.getUniqueId()) * 1.5 + " coins!");
	        			gavePlayerMoney(player, totalMoneyHoger.get(player.getUniqueId()) * 1.5, "Randomizer");
		      			totalMoneyHoger.remove(player.getUniqueId());
	        		}else if(totalMoneyLager.containsKey(player.getUniqueId())) {
	        			player.sendMessage(ChatColor.RED + "" + ChatColor.BOLD + "You have bet lower, but the number " + ChatColor.AQUA + getal + 
	        					ChatColor.RED + "" + ChatColor.BOLD + " was higher then " + ChatColor.AQUA + Integer.parseInt(sign.getLine(2))
	        					+ ChatColor.RED + "" + ChatColor.BOLD + " Better luck next time!");
	        			totalMoneyLager.remove(player.getUniqueId());
	        		}
	        	}else if (Integer.parseInt(sign.getLine(2)) > getal) {
	        		if (totalMoneyHoger.containsKey(player.getUniqueId())) {
	        			player.sendMessage(ChatColor.RED + "" + ChatColor.BOLD + "You have bet higher, but the number " + ChatColor.AQUA + getal + 
	        					ChatColor.RED + "" + ChatColor.BOLD + " was lower then " + ChatColor.AQUA + Integer.parseInt(sign.getLine(2))
	        					+ ChatColor.RED + "" + ChatColor.BOLD + " Better luck next time!");
	        			totalMoneyHoger.remove(player.getUniqueId());
	        		}else if(totalMoneyLager.containsKey(player.getUniqueId())) {
	        			if (!sign.getLine(3).equals("§c§ox2.0 Reward")) {
	        			player.sendMessage(ChatColor.RED + "" + ChatColor.BOLD + "CORRECT! You have bet lower, so you have won x 1.5 money reward! Money reward: " 
	        		    + totalMoneyLager.get(player.getUniqueId()) * 1.5 + " coins!");
	        			gavePlayerMoney(player, totalMoneyLager.get(player.getUniqueId()) * 1.5, "Randomizer");
		      			totalMoneyLager.remove(player.getUniqueId());
	        			}else {
	        				player.sendMessage(ChatColor.RED + "" + ChatColor.BOLD + "JACKPOT! You have bet lower, so you have won x 2.0 money reward! Money reward: " 
	        	        		    + totalMoneyLager.get(player.getUniqueId()) * 2.0 + " coins!");
	        	        			gavePlayerMoney(player, totalMoneyLager.get(player.getUniqueId()) * 2.0, "Randomizer");
	        		      			totalMoneyLager.remove(player.getUniqueId());
	        			}
	        		}
	        	}
	      		sign.setLine(2, Integer.toString(getal));
      			sign.update();
      			
      			sign.setLine(3, "§8§ox1.5 Reward");
      			sign.update();
      			
      			int chance = 5; //50% chance
      			float diceroll = random.nextInt(100);
      			if (chance > diceroll) {
      				sign.setLine(3, "§c§ox2.0 Reward");
      	  			sign.update();
      	  			Bukkit.broadcastMessage(ChatColor.GOLD + "[RANDOMIZER] " + ChatColor.AQUA + "A randomzier has x2.0 payout. Go and claim it!");
      			}
	        }
	    }, 20 * 15);
	}
	
	public void gavePlayerMoney(Player p, double coins, String nameAttractie) {
		String playerName = p.getName();
		Main.econ.depositPlayer(playerName, coins);
		p.sendMessage(ChatColor.AQUA + "You just earned " + ChatColor.RED + coins + ChatColor.AQUA + " from " + nameAttractie + " and now have " 
		+ ChatColor.RED + Main.econ.getBalance(playerName) + ChatColor.AQUA + " total money!");
	}
	
	@EventHandler
	public void onPlayerInteractEvent(PlayerInteractEvent e) {
		Player player = e.getPlayer();
		String playerName = player.getName();
		if (!(e.getAction() == Action.RIGHT_CLICK_BLOCK)) return;
		 if (!(e.getClickedBlock().getState() instanceof Sign)) return;
	 		if ((e.getPlayer().getItemInHand().getType() == Material.RAW_CHICKEN)) {
			 	if (ChairsAPI.isSitting(player)) {
		 		Sign s = (Sign) e.getClickedBlock().getState();
		 			if (s.getLine(0).equalsIgnoreCase("randomizer")) {
		 				if (s.getLine(2).equalsIgnoreCase("▲§4§lHIGHER§0▲")) {
		 				if (s.getLine(3).equalsIgnoreCase("§8$500")) {
		 					if (totalMoneyHoger.containsKey(player.getUniqueId()) || totalMoneyLager.containsKey(player.getUniqueId())) {
		 						player.sendMessage(ChatColor.RED + "You have already bet!");
		 					}else {
		 						if (ChairsAPI.isSitting(player)) {
		 							EconomyResponse r = Main.econ.withdrawPlayer(playerName, 500);
		 					         if(r.transactionSuccess()) {
		 					        	 totalMoneyHoger.put(player.getUniqueId(), 500);
		 					        	 sayRandomGetal(player, e.getClickedBlock().getLocation().add(0,1,1).getBlock(), 500);
		 					         } else {
		 					        	 player.sendMessage(NoMoney);
		 					         }
		 						}
		 					}
		 				}
		 			}
		 				else if (s.getLine(2).equalsIgnoreCase("▼§4§lLOWER§0▼")) {
		 				if (s.getLine(3).equalsIgnoreCase("§8$500")) {
		 					if (totalMoneyLager.containsKey(player.getUniqueId()) || totalMoneyHoger.containsKey(player.getUniqueId())) {
		 						player.sendMessage(ChatColor.RED + "You have already bet!");
		 					}else {
		 						if (ChairsAPI.isSitting(player)) {
		 							EconomyResponse r = Main.econ.withdrawPlayer(playerName, 500);
		 					         if(r.transactionSuccess()) {
		 					        	 totalMoneyLager.put(player.getUniqueId(), 500);
		 					        	 sayRandomGetal(player, e.getClickedBlock().getLocation().add(0,1,-1).getBlock(), 500);
		 					         } else {
		 					        	 player.sendMessage(NoMoney);
		 					         }
		 						}
		 					}
		 				}
		 			}
		 		}
	 		}else {
	 			player.sendMessage(ChatColor.RED + "You must sit before you can bet!");
	 		}
	 	}else {
 			e.getPlayer().sendMessage(noCoinsInHand);
	 	}
	}
	
	public void startSignEffect(final SignChangeEvent e) {
        Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
	        @Override
	        public void run() {
	        	 e.setLine(0, "§0RANDOMIZER");
	        }
	    }, 20);
        
        Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
	        @Override
	        public void run() {
	        	 e.setLine(0, "§5RANDOMIZER");
	        }
	    }, 40);
        
        Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
	        @Override
	        public void run() {
	        	 e.setLine(0, "§6RANDOMIZER");
	        }
	    }, 60);
        
        Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
	        @Override
	        public void run() {
	        	 e.setLine(0, "§9RANDOMIZER");
	        }
	    }, 80);
        
        Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
	        @Override
	        public void run() {
	        	 e.setLine(0, "§4RANDOMIZER");
	        }
	    }, 100);
        
        Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
	        @Override
	        public void run() {
	        	 e.setLine(0, "§5RANDOMIZER");
	        	 resetSignEffect(e);
	        }
	    }, 120);
	}
	
	public void resetSignEffect(final SignChangeEvent e) {
		startSignEffect(e);
	}
	
	@EventHandler
    public void onSignChange(final SignChangeEvent e){
    if (e.getLine(0).equalsIgnoreCase("randomizer")){
    	if (e.getLine(1).equalsIgnoreCase("500")) {
    		if (e.getLine(2).equalsIgnoreCase("hoger")) {
        e.setLine(0, "§0RANDOMIZER");
        e.setLine(1, "");
        e.setLine(2, "▲§4§lHIGHER§0▲");
        e.setLine(3, "§8$500");
        
        startSignEffect(e);
    		}else if (e.getLine(2).equalsIgnoreCase("lager")) {
    	        e.setLine(0, "§0RANDOMIZER");
    	        e.setLine(1, "");
    	        e.setLine(2, "▼§4§lLOWER§0▼");
    	        e.setLine(3, "§8$500");
    	        
    	        startSignEffect(e);
    	    		}
    	}else if (e.getLine(1).equalsIgnoreCase("[getal]")){
    		e.setLine(0, "");
    		e.setLine(1, "§9NUMBER TO BET");
    		e.setLine(2, "50");
    		e.setLine(3, "§8§ox1.5 Reward");
    	}
    }
	}
}
