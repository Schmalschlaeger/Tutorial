package DoubleJump;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Effect;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.event.player.PlayerChangedWorldEvent;
import org.bukkit.event.player.PlayerGameModeChangeEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerLevelChangeEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.util.Vector;

import DoubleJump.MessageManager.MsgType;

public class doublejump extends JavaPlugin implements Listener {
	
    public void onEnable() {
    	getConfig().options().copyDefaults(true);
        saveDefaultConfig();
       
        Bukkit.getServer().getPluginManager().registerEvents(this, this);       
    }
    
    public void onDisable() {
    	reloadConfig();
    }
 
    private ArrayList<Player> jumpcooldown = new ArrayList<Player>();
    private ArrayList<String> toggle = new ArrayList<String>();

    private int id;
    
    public void setId(int id) {
        this.id = id;
    }
    
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e) {
    	//if (!e.getPlayer().getWorld().equals(getConfig().getConfigurationSection("disabledWorlds"))) {
    	Player p = e.getPlayer();
    	if (p.hasPermission("dj.jumper")) { 
        toggle.add(p.getName());
        e.getPlayer().setAllowFlight(true);
    	}
		if (getConfig().getBoolean("jumpwithxp", true)) {
    	if (p.getExp() == 0) {
    		p.setExp(1);    		
    	}        
		}
    }
    
    @EventHandler
    public void onWorldSwitch(PlayerChangedWorldEvent e) {
    	Player p = e.getPlayer();
    	//if (!e.getPlayer().getWorld().equals(getConfig().getConfigurationSection("disabledWorlds"))) {    		
    		if (!toggle.contains(p.getName())) {
    			toggle.add(p.getName());
    			e.getPlayer().setAllowFlight(true);
    			if (getConfig().getBoolean("jumpwithxp", true)) {
    		    	if (p.getExp() == 0) {
    		    		p.setExp(1);    		
    		    	}        
    				}
    		}
    	}
    
    @EventHandler
    public void onLevelChange(PlayerLevelChangeEvent e) {
    	Player p = e.getPlayer();
		if (getConfig().getBoolean("jumpwithxp", true)) {
    	if (p.getExp() == 1) {
    		p.setAllowFlight(true);
    	}
    	}
    }
    
    @EventHandler
    public void onPlayerSwitchFly(PlayerGameModeChangeEvent e) {
    	if (toggle.contains(e.getPlayer().getName())) {
    		e.getPlayer().setAllowFlight(true);
    	}
    }

    @EventHandler
    public void onPlayerLeave(PlayerQuitEvent e) {
    	Player p = e.getPlayer();
    	if (toggle.contains(p.getName())) {
    	toggle.remove(p.getName());
    	}
    }


	@EventHandler
    public void onPlayerMove(final PlayerMoveEvent e) {
    	//if (!e.getPlayer().getWorld().equals(getConfig().getConfigurationSection("disabledWorlds"))) {
		FileConfiguration conf = this.getConfig();
		final int delay = conf.getInt("jumpdelay");
    	final Player p = e.getPlayer();
    	Location loc = e.getPlayer().getLocation();
    	if(p.getGameMode() != GameMode.CREATIVE && p.isFlying()) {
    		if (!p.hasPermission("dj.jumper")) { 
    			return;
    		}    
    		if (getConfig().getBoolean("jumpwithxp", true)) {
    		if (p.getExp() == 0){
    			return;
    		}else {
    		
    		if (e.getFrom().getY() < e.getTo().getY()) {
            if (jumpcooldown.contains(e.getPlayer())) return;
            if (!getConfig().getString("message").isEmpty()) {
            e.getPlayer().sendMessage(ChatColor.GOLD + ChatColor.translateAlternateColorCodes('&', getConfig().getString("message")));
            }
            p.setFlying(false);
            p.setAllowFlight(false);
            p.getWorld().playEffect(p.getLocation(), Effect.MOBSPAWNER_FLAMES, 9);
            p.getWorld().playSound(p.getLocation(), Sound.IRONGOLEM_HIT, 1, 1);
            ParticleEffect.CLOUD.display(loc, 0.0f, 0.5f, 0.6f, 0.2F, 35);
            p.setVelocity(p.getLocation().getDirection().multiply(getConfig().getInt("jumplenght")));
            p.setVelocity(new Vector(p.getVelocity().getX(), getConfig().getDouble("jumpheight"), e.getPlayer().getVelocity().getZ()));
            p.setExp(0);
            jumpcooldown.add(e.getPlayer());
            id = Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(this, new Runnable() {
				@SuppressWarnings("deprecation")
				public void run() {
					if (p.isOnGround()) {
                    jumpcooldown.remove(e.getPlayer());
                    e.getPlayer().setAllowFlight(true);
                    Bukkit.getServer().getScheduler().cancelTask(id);
            			p.setExp(1);            		
					}
                	
                }
            }, delay * 20L, 0L);
    		}
            
        }
    }else {
    	
		if (e.getFrom().getY() < e.getTo().getY()) {
            if (jumpcooldown.contains(e.getPlayer())) return;
            if (!getConfig().getString("message").isEmpty()) {
            e.getPlayer().sendMessage(ChatColor.GOLD + ChatColor.translateAlternateColorCodes('&', getConfig().getString("message")));
            }
            p.setFlying(false);
            p.setAllowFlight(false);
            p.getWorld().playEffect(p.getLocation(), Effect.MOBSPAWNER_FLAMES, 9);
            p.getWorld().playSound(p.getLocation(), Sound.IRONGOLEM_HIT, 1, 1);
            ParticleEffect.CLOUD.display(loc, 0.0f, 0.5f, 0.6f, 0.2F, 35);
            p.setVelocity(p.getLocation().getDirection().multiply(getConfig().getInt("jumplenght")));
            p.setVelocity(new Vector(p.getVelocity().getX(), getConfig().getDouble("jumpheight"), e.getPlayer().getVelocity().getZ()));
            jumpcooldown.add(e.getPlayer());
            id = Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(this, new Runnable() {
				@SuppressWarnings("deprecation")
				public void run() {
					if (p.isOnGround()) {
                    jumpcooldown.remove(e.getPlayer());
                    e.getPlayer().setAllowFlight(true);
                    Bukkit.getServer().getScheduler().cancelTask(id);           		
					}
                	
                }
            }, delay * 20L, 0L);
		}
		}
    	}
}
 
    @EventHandler
    public void onPlayerDamage(final EntityDamageEvent e) { 
		FileConfiguration conf = this.getConfig();
		final int delay = conf.getInt("jumpdelay");
        if (e.getEntity() instanceof Player) {
        	final Player p = (Player) e.getEntity();
        	if (toggle.contains(p.getName())) {	
                if (e.getCause() == DamageCause.FALL) {
                    e.setDamage(0.0);        
                    Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(this, new Runnable() {
                        public void run() {
                    		if (getConfig().getBoolean("jumpwithxp", false)) {
                    			p.setAllowFlight(true);
                    		}
                            }
                    }, delay * 20L);
                }
            }
        }
    }
    
    
    	@SuppressWarnings("deprecation")
		public boolean onCommand(final CommandSender s, Command cmd, String CommandLabel, String[] args) {
    		final Player p = (Player) s;
    		if (!(s instanceof Player)) {
				s.sendMessage(ChatColor.RED + "You arent an player! Only players ingame can toggle the doublejump");  
				return true;
			}
    		
    		if(cmd.getName().equalsIgnoreCase("doublejump")) {
    			if (args.length == 0) {
    			p.sendMessage(ChatColor.GOLD + "------------- " + MsgType.NORMAL + "---------------------");
            	p.sendMessage(ChatColor.GOLD + "Developed by: " + ChatColor.GRAY + getDescription().getAuthors());
            	p.sendMessage(ChatColor.GOLD + "Version: " + ChatColor.GRAY + getDescription().getVersion());
            	p.sendMessage(ChatColor.GOLD + "Minecraft version: " + ChatColor.GRAY + "1.8+");
            	p.sendMessage(ChatColor.YELLOW  + "Typ /dj help for an list of commands.");
    			return true;
    		}

    		if (args.length == 1) {
        		if (args[0].equalsIgnoreCase("help")) {
        			if (!p.hasPermission("dj.help")) {
        				s.sendMessage(MsgType.NOPERMS + "");
        			}
        			p.sendMessage(ChatColor.GOLD + "----------- " + MsgType.NORMAL + ChatColor.RED +  "[Commands]" + ChatColor.GOLD + " --------------");
        			p.sendMessage(ChatColor.GOLD + "/doublejump|dj: " + ChatColor.GRAY + "Info about the plugin");
        			p.sendMessage(ChatColor.GOLD + "/dj|doublejump help: " + ChatColor.GRAY + "Shows this help page!");
        			p.sendMessage(ChatColor.GOLD + "/dj|doublejump reload: " + ChatColor.GRAY + "Reload the config!");
        			p.sendMessage(ChatColor.GOLD + "/dj|doublejump enable <player>: " + ChatColor.GRAY + "Enable the DoubleJump for an player!");
        			p.sendMessage(ChatColor.GOLD + "/dj|doublejump disable <player>: " + ChatColor.GRAY + "Disable the DoubleJump for an player!");
        			return true;
        		}
        	}
    		if (args.length == 1) {
        		if (args[0].equalsIgnoreCase("reload")) {
        			if(!p.hasPermission("dj.reload")) {
            			s.sendMessage(MsgType.NOPERMS + "");
        			}
        			else {
        			   	 s.sendMessage(MsgType.NORMAL + "Reload plugin........");
        			   	 reloadConfig();
        			     Bukkit.getScheduler().scheduleSyncDelayedTask(this, new Runnable() {
        			    	@Override
        			    	 public void run() {
        			    		 s.sendMessage(MsgType.NORMAL + "Succesfull reloaded!");
        			    		 for (Player all : Bukkit.getServer().getOnlinePlayers()) {
        			     		if (getConfig().getBoolean("jumpwithxp", true)) {
        			     			all.setExp(1);        			     			
        			     		}else {        			     			
        			     			all.setExp(0);        			     			
        			     		}
        			     		}
        			    	 }
        			     }
        			     , 20L);
        			     
        			}
        		}
        	}
    		if (args.length == 2) {
        		if (args[0].equalsIgnoreCase("enable")) {
        	    	/*if (p.getWorld().equals(getConfig().getConfigurationSection("disabledWorlds"))) {
        	    		s.sendMessage(MsgType.ERROR + "This player is in a disabled world!");
        	    		return true;
        	    	*/
        			if(!p.hasPermission("dj.toggle")) {
            			p.sendMessage(MsgType.NOPERMS + "");
        			}
        			if (args.length == 1) {
        				s.sendMessage(MsgType.ERROR + "Please specify a Player");
        				return true;
        			}
        			
        			if(args.length < 2) {
                        s.sendMessage(MsgType.ERROR + "Not enough arguments! Try /djenable <player>");
                        return false;
                    }
        			if(args.length > 2) {
        				s.sendMessage(MsgType.ERROR + "Too many arguments! Try /djenable <player>");
        				return true;
        			}

        			Player target = Bukkit.getServer().getPlayer(args[1]);
        			if (target == null) {
        				s.sendMessage(MsgType.ERROR + "Player " + args[1] + " is not online!");
        				return true;
        			}
        			if (toggle.contains(target.getName())) {
        				s.sendMessage(MsgType.ERROR + "Player " + target.getName() + " has already the doublejump! If this is an error! Make sure the player is not switches from gamemode!");
        				return true;
        			}
        			if (target.getGameMode() == GameMode.CREATIVE) {
        				s.sendMessage(MsgType.ERROR + "Player " + target.getName() + " has gamemode and can't use the doublejump!");
        				return true;
        			} 
        			if (target.getGameMode() == GameMode.SURVIVAL || target.getGameMode() == GameMode.ADVENTURE) {
        			target.setAllowFlight(true);
        			toggle.add(target.getName());
        			s.sendMessage(MsgType.NORMAL + "DoubleJump enabled for " + ChatColor.DARK_PURPLE + target.getName());
        			target.sendMessage(MsgType.NORMAL + "Your doublejump is enabled by " + s.getName());
        			if (getConfig().getBoolean("jumpwithxp", true)) {
        			target.setExp(1);
        			}
        			return true;
        			
        			}
        		}
        	}
    		if (args.length == 2) {
        		if (args[0].equalsIgnoreCase("disable")) {
        			
        	    	/*if (p.getWorld().equals(getConfig().getConfigurationSection("disabledWorlds").getKeys(false))) {
        	    		s.sendMessage(MsgType.ERROR + "This player is in a disabled world!");
        	    		return true;
        	    	}*/
        			if(!p.hasPermission("dj.toggle")) {
            			p.sendMessage(MsgType.NOPERMS + "");
        			}
        			if (args.length == 1) {
        				s.sendMessage(MsgType.ERROR + "Please specify a Player");
        				return true;
        			}
        			
        			if(args.length < 2) {
                        s.sendMessage(MsgType.ERROR + "Not enough arguments! Try /djdisable <player>");
                        return false;
                    }
        			if(args.length > 2) {
        				s.sendMessage(MsgType.ERROR + "Too many arguments! Try /djdisable <player>");
        				return true;
        			}

        			Player target = Bukkit.getServer().getPlayer(args[1]);
        			if (target == null) {
        				s.sendMessage(MsgType.ERROR + "Player " + args[1] + " is not online!");
        				return true;
        			}        			
        			if (!toggle.contains(target.getName())) {
        				s.sendMessage(MsgType.ERROR + "Player " + target.getName() + " din't have any doublejump! If this is an error! Make sure the player is not switches from gamemode!");
        				return true;
        			}
        			target.setFlying(false);
        			target.setAllowFlight(false);
        			toggle.remove(target.getName());
        			s.sendMessage(MsgType.NORMAL + "" + ChatColor.YELLOW + "DoubleJump disabled for " + ChatColor.DARK_PURPLE + args[1]);
        			target.sendMessage(MsgType.NORMAL + "" + ChatColor.YELLOW + "Your doublejump is disabled by " + s.getName());
        			if (getConfig().getBoolean("jumpwithxp", true)) {
        				p.setExp(0);
        			}
        			return true;        		
        	}
    	}
    }
			return false;
    }
}



