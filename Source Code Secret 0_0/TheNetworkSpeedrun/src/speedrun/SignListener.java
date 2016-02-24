package speedrun;

import java.util.ArrayList;

import net.minecraft.util.com.google.common.io.ByteArrayDataOutput;
import net.minecraft.util.com.google.common.io.ByteStreams;

import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.Sign;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.SignChangeEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import speedrun.Tasks.Countdown;

public class SignListener implements Listener {
	
    static Main plugin;
    
    public SignListener(Main m) {
        plugin = m;
    }
    
	public void serverConnect(String server, Player p) {
    	ByteArrayDataOutput out = ByteStreams.newDataOutput();
        out.writeUTF("Connect");
        out.writeUTF(server);
		p.sendPluginMessage(plugin, "BungeeCord", out.toByteArray());
    }
	
	public static ArrayList<String> isWalkedOnPlate = new ArrayList<String>();
	public static ArrayList<String> isWalkedOnPlate2 = new ArrayList<String>();
    
	@SuppressWarnings("deprecation")
	@EventHandler
    public void onPlayerMove(PlayerMoveEvent event) {
    	Player p = event.getPlayer();
    	if (SpeedRunManager.getManager().players.contains(event.getPlayer())) {
        Block maybeALavaBlock = event.getPlayer().getWorld().getBlockAt(event.getTo());
        	if (!(event.getPlayer().getGameMode() == GameMode.CREATIVE)) {
        		 if(maybeALavaBlock != null && maybeALavaBlock.getType().equals(Material.STONE_PLATE) && !event.getPlayer().isDead()) {
        			 if (!isWalkedOnPlate.contains(event.getPlayer().getName())) {
     				event.getPlayer().sendMessage(ChatColor.GREEN + "Timer Started!");
     				isWalkedOnPlate.add(event.getPlayer().getName());
     				isWalkedOnPlate2.add(p.getName());
     				Countdown.timer.put(event.getPlayer().getName(), 1);
        			 }else {
        				 if (!isWalkedOnPlate2.contains(event.getPlayer().getName())) {
        				 event.getPlayer().sendMessage(ChatColor.GREEN + "Timer Restarted!");
          				isWalkedOnPlate2.add(p.getName());
          				Countdown.timer.put(event.getPlayer().getName(), 1);
        				 }
        			 }
        		 }else if (maybeALavaBlock != null && maybeALavaBlock.getType().equals(Material.WOOD_PLATE) && !event.getPlayer().isDead()) {
 	                p.sendMessage(ChatColor.GREEN + "" + ChatColor.BOLD + 
	                		"CONGRATULATIONS! You have done this in: " + Countdown.timer.get(event.getPlayer().getName()) + " seconds!");
     				SpeedRunManager.getManager().removePlayerFromAllMaps(p);
            		isWalkedOnPlate.remove(event.getPlayer().getName());
            		isWalkedOnPlate2.remove(p.getName());
            		Countdown.timer.remove(event.getPlayer().getName());
            		SpeedRunManager.getManager().removePlayer(p);
        		 }
            if (SpeedRunManager.getManager().normal.contains(event.getPlayer())) {
                if(maybeALavaBlock != null && maybeALavaBlock.getType().equals(Material.STATIONARY_WATER) && !event.getPlayer().isDead()) {
            Location loc = new Location(event.getPlayer().getWorld(), 294, 126, 362);
            event.getPlayer().teleport(loc);
            isWalkedOnPlate2.remove(p.getName());
            Countdown.timer.remove(event.getPlayer().getName());
            //Bukkit.getServer().getScheduler().cancelTask(id);
                }
            }else if (SpeedRunManager.getManager().nether.contains(event.getPlayer())) {
            	 if(maybeALavaBlock != null && maybeALavaBlock.getType().equals(Material.STATIONARY_LAVA) 
            			 || event.getPlayer().getLocation().getBlock().getRelative(BlockFace.DOWN).getType() == Material.NETHERRACK 
            			 && !event.getPlayer().isDead()) {
               Location loc = new Location(event.getPlayer().getWorld(), -294, 123, -317, -90, 3);
               event.getPlayer().teleport(loc);
               event.getPlayer().setFireTicks(0);
               event.getPlayer().getLocation().setPitch(90);
               event.getPlayer().getLocation().setY(4);
               isWalkedOnPlate2.remove(p.getName());
               Countdown.timer.remove(event.getPlayer().getName());
            	 }
            }else if (SpeedRunManager.getManager().speedy.contains(event.getPlayer())) {
            	Block block = p.getLocation().getBlock().getRelative(BlockFace.DOWN);
           	 if(block != null && block.getType().equals(Material.WOOL) && !event.getPlayer().isDead()) {
           		 if (block.getData() == 15) {
           		Location loc = new Location(event.getPlayer().getWorld(), 596.5, 126, 600.5);
              event.getPlayer().teleport(loc);
              event.getPlayer().getLocation().setPitch(90);
              event.getPlayer().getLocation().setY(4);
              isWalkedOnPlate2.remove(p.getName());
              Countdown.timer.remove(event.getPlayer().getName());
           		 }
           	 }
           }else if (SpeedRunManager.getManager().jungle.contains(event.getPlayer())) {
            	if(maybeALavaBlock != null && maybeALavaBlock.getType().equals(Material.STATIONARY_WATER) && !event.getPlayer().isDead()) {
            		Location loc = new Location(event.getPlayer().getWorld(), 321, 137, -400, -90, 3);
            		event.getPlayer().teleport(loc);
            		//plugin.cooldown1.remove(event.getPlayer().getName());
            		isWalkedOnPlate2.remove(p.getName());
            		Countdown.timer.remove(event.getPlayer().getName());
            	}
            }//else {
            //event.getPlayer().sendMessage("You are not in any game? Teleport you to speedrun lobby!");
            //event.getPlayer().teleport(event.getPlayer().getWorld().getSpawnLocation());
            //isWalkedOnPlate.remove(event.getPlayer().getName());
            //isWalkedOnPlate2.remove(p.getName());
            //Countdown.timer.remove(event.getPlayer().getName());
            //}
            }   
    	}
	}
	
	@EventHandler
	public void onPlayerQuit(PlayerQuitEvent e) {
		isWalkedOnPlate.remove(e.getPlayer().getName());
		isWalkedOnPlate2.remove(e.getPlayer().getName());
		SpeedRunManager.getManager().removePlayerFromAllMaps(e.getPlayer());
		Countdown.timer.remove(e.getPlayer().getName());
		SpeedRunManager.getManager().removePlayer(e.getPlayer());
		e.getPlayer().getInventory().clear();
	}
    
	@EventHandler
	public void onSlimeClick5(PlayerInteractEvent e) {
		final Player player = (Player) e.getPlayer();
		Inventory inv = player.getInventory();
		ItemStack cl = player.getItemInHand();
		if (e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK) {
			if (inv.getName().equals(inv.getName())) {
				if (cl.getType() == Material.FEATHER) {
					if (SpeedRunManager.getManager().players.contains(player)) {
						if (SpeedRunManager.getManager().nether.contains(player)) {
						Location loc = new Location(e.getPlayer().getWorld(), -294, 123, -317, -90, 3);
			            e.getPlayer().teleport(loc);
			            e.getPlayer().getLocation().setPitch(-90);
			            e.getPlayer().getLocation().setY(3);
			            e.getPlayer().setFireTicks(0);
						}else if (SpeedRunManager.getManager().normal.contains(player)) {
							Location loc = new Location(e.getPlayer().getWorld(), 294, 126, 362);
				            e.getPlayer().teleport(loc);
				            e.getPlayer().setFireTicks(0);
						}else if (SpeedRunManager.getManager().jungle.contains(player)) {
							Location loc = new Location(e.getPlayer().getWorld(), 321, 137, -400, -90, 3);
				            e.getPlayer().teleport(loc);
				            e.getPlayer().setFireTicks(0);
						}else if (SpeedRunManager.getManager().speedy.contains(player)) {
							Location loc = new Location(e.getPlayer().getWorld(), 596.5, 126, 600.5);
				            e.getPlayer().teleport(loc);
				            e.getPlayer().setFireTicks(0);
						}
			            isWalkedOnPlate2.remove(player.getName());
			            Countdown.timer.remove(e.getPlayer().getName());
					}
				}
			}
		}
	}
	
	@EventHandler
	public void onSignClick(PlayerInteractEvent e) {	
		if (!(e.getAction() == Action.RIGHT_CLICK_BLOCK)) return;
        if (e.getClickedBlock().getState() instanceof Sign) {
                Sign s = (Sign) e.getClickedBlock().getState();
                if (s.getLine(0).equalsIgnoreCase("§3[SpeedRun]")) {
                	if (s.getLine(2).equalsIgnoreCase("§c§lPlains")) {
                		Location loc = new Location(e.getPlayer().getWorld(), 294, 126, 362);
                		SpeedRunManager.getManager().addPlayer(e.getPlayer(), loc);
                		SpeedRunManager.getManager().normal.add(e.getPlayer());
                	}else if (s.getLine(2).equalsIgnoreCase("§2§lNether")) {
                		Location loc = new Location(e.getPlayer().getWorld(), -294, 123, -317, -90, 3);
                		SpeedRunManager.getManager().nether.add(e.getPlayer());
                		SpeedRunManager.getManager().addPlayer(e.getPlayer(), loc);
                	}else if (s.getLine(2).equalsIgnoreCase("§5§lSpeedy")) {
                		Location loc = new Location(e.getPlayer().getWorld(), 596.5, 126, 600.5);
                		SpeedRunManager.getManager().speedy.add(e.getPlayer());
                		SpeedRunManager.getManager().addPlayer(e.getPlayer(), loc);
                	}else if (s.getLine(2).equalsIgnoreCase("§6Leave")) {
                		e.getPlayer().sendMessage(ChatColor.GOLD + "You have left speedrun! Thanks for playing :D");
                		SpeedRunManager.getManager().removePlayer(e.getPlayer());
                		isWalkedOnPlate.remove(e.getPlayer().getName());
                		isWalkedOnPlate2.remove(e.getPlayer().getName());
                		Countdown.timer.remove(e.getPlayer().getName());
                	}else if (s.getLine(2).equalsIgnoreCase("§5§lJungle")) {
                		Location loc = new Location(e.getPlayer().getWorld(), 321, 137, -400, -90, 3);
                		SpeedRunManager.getManager().jungle.add(e.getPlayer());
                		SpeedRunManager.getManager().addPlayer(e.getPlayer(), loc);
                	}
                }else if (s.getLine(0).equalsIgnoreCase("§3[Lobby]")) {
                	if (s.getLine(2).equalsIgnoreCase("the hub")) {
                		serverConnect("lobby", e.getPlayer());
                	}
                }else if (s.getLine(0).equalsIgnoreCase("§c[SpeedRun]")) {
                	if (s.getLine(1).equalsIgnoreCase("§4§lMaintenance")) {
                		if (s.getLine(2).equalsIgnoreCase("§c§lPlains")) {
                			if (e.getPlayer().hasPermission("network.rank.owner") || e.getPlayer().hasPermission("network.rank.builder")) {
                			Location loc = new Location(e.getPlayer().getWorld(), 294, 126, 362);
                    		SpeedRunManager.getManager().addPlayer(e.getPlayer(), loc);
                    		SpeedRunManager.getManager().normal.add(e.getPlayer());
                    		e.getPlayer().sendMessage(ChatColor.RED + "This map is currently on maintenance, you are one of the staff! Just try this map and figure out whats wrong with this map!");
                			}else {
                				e.getPlayer().sendMessage(ChatColor.RED + "Sorry, but only staff can join it right now!");
                				return;
                			}
                		}else if (s.getLine(2).equalsIgnoreCase("§2§lNether")) {
                			if (e.getPlayer().hasPermission("network.rank.owner") || e.getPlayer().hasPermission("network.rank.builder")) {
                				Location loc = new Location(e.getPlayer().getWorld(), -294, 123, -317, -90, 3);
                        		SpeedRunManager.getManager().nether.add(e.getPlayer());
                        		SpeedRunManager.getManager().addPlayer(e.getPlayer(), loc);
                        		e.getPlayer().sendMessage(ChatColor.RED + "This map is currently on maintenance, you are one of the staff! Just try this map and figure out whats wrong with this map!");
                    			}else {
                    				e.getPlayer().sendMessage(ChatColor.RED + "Sorry, but only staff can join it right now!");
                    				return;
                    			}
                		}else if (s.getLine(2).equalsIgnoreCase("§5§lJungle")) {
                			if (e.getPlayer().hasPermission("network.rank.owner") || e.getPlayer().hasPermission("network.rank.builder")) {
                				Location loc = new Location(e.getPlayer().getWorld(), 321, 137, -400, -90, 3);
                        		SpeedRunManager.getManager().jungle.add(e.getPlayer());
                        		SpeedRunManager.getManager().addPlayer(e.getPlayer(), loc);
                        		e.getPlayer().sendMessage(ChatColor.RED + "This map is currently on maintenance, you are one of the staff! Just try this map and figure out whats wrong with this map!");
                    			}else {
                    				e.getPlayer().sendMessage(ChatColor.RED + "Sorry, but only staff can join it right now!");
                    				return;
                    			}
                		}
                	}else if (s.getLine(1).equalsIgnoreCase("§c§lJoin")) {
                		if (s.getLine(2).equalsIgnoreCase("§5§lPlains")) {
                			if (e.getPlayer().hasPermission("network.rank.vip") || e.getPlayer().hasPermission("network.rank.vipPlus") || e.getPlayer().hasPermission("network.rank.legend") || e.getPlayer().hasPermission("network.rank.builder")) {
                			Location loc = new Location(e.getPlayer().getWorld(), 294, 126, 362);
                    		SpeedRunManager.getManager().addPlayer(e.getPlayer(), loc);
                    		SpeedRunManager.getManager().normal.add(e.getPlayer());
                    		e.getPlayer().sendMessage(ChatColor.GOLD + "You are a special person, try this map out! Give a feedback about this map :D");
                			}else {
                				e.getPlayer().sendMessage(ChatColor.RED + "Sorry, but only Vip's can join it right now! Buy vip at the store!");
                				return;
                			}
                		}else if (s.getLine(2).equalsIgnoreCase("§2§lNether")) {
                			if (e.getPlayer().hasPermission("network.rank.vip") || e.getPlayer().hasPermission("network.rank.vipPlus") || e.getPlayer().hasPermission("network.rank.legend") || e.getPlayer().hasPermission("network.rank.builder")) {
                				Location loc = new Location(e.getPlayer().getWorld(), -294, 123, -317, -90, 3);
                        		SpeedRunManager.getManager().nether.add(e.getPlayer());
                        		SpeedRunManager.getManager().addPlayer(e.getPlayer(), loc);
                        		e.getPlayer().sendMessage(ChatColor.GOLD + "You are a special person, try this map out! Give a feedback about this map :D");
                    			}else {
                    				e.getPlayer().sendMessage(ChatColor.RED + "Sorry, but only Vip's can join it right now! Buy vip at the store!");
                    				return;
                    			}
                		}else if (s.getLine(2).equalsIgnoreCase("§2§lJungle")) {
                			if (e.getPlayer().hasPermission("network.rank.vip") || e.getPlayer().hasPermission("network.rank.vipPlus") || e.getPlayer().hasPermission("network.rank.legend") || e.getPlayer().hasPermission("network.rank.builder")) {
                				Location loc = new Location(e.getPlayer().getWorld(), 321, 137, -400, -90, 3);
                        		SpeedRunManager.getManager().jungle.add(e.getPlayer());
                        		SpeedRunManager.getManager().addPlayer(e.getPlayer(), loc);
                        		e.getPlayer().sendMessage(ChatColor.GOLD + "You are a special person, try this map out! Give a feedback about this map :D");
                    			}else {
                    				e.getPlayer().sendMessage(ChatColor.RED + "Sorry, but only Vip's can join it right now! Buy vip at the store!");
                    				return;
                    			}
                		}
                	}
                }
            }
        }
	
	@EventHandler
    public void onSignChange(SignChangeEvent e){
    if (e.getLine(0).equalsIgnoreCase("[lobby]")){
        e.setLine(0, "§3[lobby]");
        e.setLine(1, "Back to");
        e.setLine(2, "the hub");
   	}else if (e.getLine(0).equalsIgnoreCase("[SpeedRun]")){
       	 if (e.getLine(1).equalsIgnoreCase("plains")){
           e.setLine(0, "§3[SpeedRun]");
           e.setLine(1, "§b§lJoin");
           e.setLine(2, "§c§lPlains");
           e.setLine(3, "§oNo rank");
       	 }else if (e.getLine(1).equalsIgnoreCase("nether")){
             e.setLine(0, "§3[SpeedRun]");
             e.setLine(1, "§b§lJoin");
             e.setLine(2, "§2§lNether");
             e.setLine(3, "§oNo rank");
       	}else if (e.getLine(1).equalsIgnoreCase("jungle")){
            e.setLine(0, "§3[SpeedRun]");
            e.setLine(1, "§b§lJoin");
            e.setLine(2, "§5§lJungle");
            e.setLine(3, "§oNo rank");
       	}else if (e.getLine(1).equalsIgnoreCase("end")){
            e.setLine(0, "§3[SpeedRun]");
            e.setLine(1, "§b§lJoin");
            e.setLine(2, "§4§lEnd");
            e.setLine(3, "§d§oVIP only");
       	}else if (e.getLine(1).equalsIgnoreCase("speedy")){
            e.setLine(0, "§3[SpeedRun]");
            e.setLine(1, "§b§lJoin");
            e.setLine(2, "§5§lSpeedy");
            e.setLine(3, "§oNo rank");
       	}else if (e.getLine(1).equalsIgnoreCase("leave")){
            e.setLine(0, "§3[SpeedRun]");
            e.setLine(1, "§6Click to");
            e.setLine(2, "§6Leave");
       	}else {
       		e.getPlayer().sendMessage(ChatColor.RED + "Map name not found! Try another!");
      		 e.getBlock().breakNaturally(); 		 
       	}
        }
	}

}
