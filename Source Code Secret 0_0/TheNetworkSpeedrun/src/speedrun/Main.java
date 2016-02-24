package speedrun;

import java.util.ArrayList;
import java.util.HashMap;

import net.minecraft.util.com.google.common.io.ByteArrayDataOutput;
import net.minecraft.util.com.google.common.io.ByteStreams;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.FireworkEffect;
import org.bukkit.FireworkEffect.Type;
import org.bukkit.Location;
import org.bukkit.block.Sign;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Firework;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.meta.FireworkMeta;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitTask;
import org.bukkit.util.Vector;

import speedrun.Tasks.Countdown;
import speedrun.Tasks.Ranks;

public class Main extends JavaPlugin implements Listener {

	public ArrayList<String> hidingPlayers = new ArrayList<String>();
	public ArrayList<Player> delay = new ArrayList<Player>();
	public static HashMap<Player, Integer> timerhash = new HashMap<Player, Integer>();
	public static ArrayList<String> timer = new ArrayList<String>();
	public ArrayList<String> usingClock;
	
	public static boolean cd = false;
	public boolean yt = false;
	public int clicked = 0;
	
	@SuppressWarnings("unused")
	public void onEnable() {
		BukkitTask ranks = new Ranks(this).runTaskTimer(this, 20, 100);
		BukkitTask timer = new Countdown(this).runTaskTimer(this, 0, 20);
		
		Bukkit.getServer().getPluginManager().registerEvents(new Ranks(this), this);
		Bukkit.getServer().getPluginManager().registerEvents(this, this);
		Bukkit.getServer().getPluginManager().registerEvents(new SignListener(this), this);
		Bukkit.getServer().getPluginManager().registerEvents(new LaunchPads(), this);
		Bukkit.getServer().getPluginManager().registerEvents(new invisClock(this), this);
		Bukkit.getServer().getPluginManager().registerEvents(new Maintenance(this), this);
		
		getServer().getMessenger().registerOutgoingPluginChannel(this, "BungeeCord");
		this.usingClock = new ArrayList<String>();
		
    	getCommand("mainon").setExecutor(new Maintenance(this));
    	getCommand("mainoff").setExecutor(new Maintenance(this));
	}
	
	@EventHandler
	public void onPlayerClick(final PlayerInteractEvent e) {
		Location l4 = new Location(e.getPlayer().getWorld(), -389, 119, 891);
		Location l2 = new Location(e.getPlayer().getWorld(), -389, 119, 891);
		Location l1 = new Location(e.getPlayer().getWorld(), -390, 119, 892);
		if (e.getAction() == Action.LEFT_CLICK_AIR || e.getAction() == Action.LEFT_CLICK_BLOCK) {
				if (yt == true) {
			Firework fw132 = (Firework) l4.getWorld().spawnEntity(l4, EntityType.FIREWORK);
	        FireworkMeta fwm132 = fw132.getFireworkMeta();
	        FireworkEffect effect132 = FireworkEffect.builder().flicker(true).withColor(Color.ORANGE, Color.RED).withFade().with(Type.BURST).trail(true).build();
	        fwm132.addEffect(effect132);
	        fw132.setVelocity(new Vector(0, 0, 0));
	        fw132.setFireworkMeta(fwm132);
	        
			Firework fw1321 = (Firework) l4.getWorld().spawnEntity(l4, EntityType.FIREWORK);
	        FireworkMeta fwm1321 = fw1321.getFireworkMeta();
	        FireworkEffect effect1321 = FireworkEffect.builder().flicker(true).withColor(Color.ORANGE, Color.RED).withFade().with(Type.BURST).trail(true).build();
	        fwm1321.addEffect(effect1321);
	        fw1321.setVelocity(new Vector(0, 0, 0));
	        fw1321.setFireworkMeta(fwm1321);
	        
	        clicked = clicked+1;
			e.getPlayer().getWorld().strikeLightning(l4);
			e.getPlayer().getWorld().strikeLightning(l2);
			e.getPlayer().getWorld().strikeLightning(l1);
			
			if (clicked == 2) {
			Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(this, new Runnable() {
				@Override
				public void run() {
					e.getPlayer().performCommand("kill robstef");
					clicked = 0;
				}
			}, 15L);
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
	
	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent e) {
		Player p = e.getPlayer();
		e.setJoinMessage(null);
		p.teleport(p.getWorld().getSpawnLocation());
		
		
	}
	
	
	@EventHandler
	public void onPlayerQuit(PlayerQuitEvent e) {
		Player p = e.getPlayer();
		e.setQuitMessage(null);
		p.teleport(p.getWorld().getSpawnLocation());
		SpeedRunManager.getManager().removePlayer(p);
	}
	
	   public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args){
	        final Player p = (Player) sender;
	        if (cmd.getName().equalsIgnoreCase("youtube")) {
	        	if (!p.hasPermission("network.rank.owner")) {
	        		p.sendMessage(ChatColor.RED + "You dont have permissions!");
	        		return true;
	        	}
	        	if (args.length == 0) {
	        		p.sendMessage(ChatColor.BLUE + "Sorry, but you must use /youtube <on/off>!");
	        		return true;
	        	}
	        	if (args.length == 1) {
	        		if (args[0].equalsIgnoreCase("on")) {
	        			if (yt == true) {
	        				p.sendMessage(ChatColor.RED + "Sorry, youtube mode is already enabled!");
	        				return true;
	        			}else {
	        			yt = true;
	        			p.sendMessage(ChatColor.GOLD + "Enabled youtube mode!");
	        			return true;
	        			}
	        		}
	        		if (args[0].equalsIgnoreCase("off")) {
	        			if (yt == false) {
	        				p.sendMessage(ChatColor.RED + "Sorry, youtube mode is already disabled!");
	        				return true;
	        			}else {
	        			yt = false;
	        			p.sendMessage(ChatColor.GOLD + "Disabled youtube mode!");
	        			return true;
	        			}
	        		}
	        	}
	        }
	        if (cmd.getName().equalsIgnoreCase("settings")) {
	        	if (!p.hasPermission("network.rank.owner")) {
	        		p.sendMessage(ChatColor.RED + "You dont have permissions!");
	        		return true;
	        	}
	        	if (args.length < 3) {
	        		p.sendMessage(ChatColor.RED + "Use /settings <maintenance/defect/beta> <plains/nether/jungle> <on/off>");
	        		return true;
	        	}
	        	if (args.length == 3) {
	        		Location plains = new Location(p.getWorld(), -59, 128, 72);
	        		Location nether = new Location(p.getWorld(), -59, 128, 73);
	        		Location jungle = new Location(p.getWorld(), -59, 128, 71);
	        		Location jungle2 = new Location(p.getWorld(), -51, 128, 71);
	        		Location plains2 = new Location(p.getWorld(), -51, 128, 72);
	        		Location nether2 = new Location(p.getWorld(), -51, 128, 73);
	        		if (args[0].equalsIgnoreCase("beta")) {
	        			if (args[1].equalsIgnoreCase("plains")) {
	        				if (args[2].equalsIgnoreCase("on")) {
		        				Sign sign = (Sign) plains.getBlock().getState();
		        				Sign sign2 = (Sign) plains.getBlock().getState();
		        				sign.setLine(0, "§c[SpeedRun]");
		        				sign.setLine(1, "§c§lJoin");
		        				sign.setLine(3, "§4§lVIP-BETA");
		        				sign.update();
		        				sign2.setLine(0, "§c[SpeedRun]");
		        				sign2.setLine(1, "§c§lJoin");
		        				sign2.setLine(3, "§4§lVIP-BETA");
		        				sign2.update();
		        				p.sendMessage(ChatColor.GOLD + "Sign updated to beta!"); 
		        				Bukkit.getServer().broadcastMessage(ChatColor.AQUA + "[Speedrun] " + ChatColor.GOLD + "The map " + ChatColor.RED + "plains " +  ChatColor.GOLD + "is now on beta, only vips can test and join it!");
		        				for (Player pl : SpeedRunManager.getManager().normal) {
		        					SpeedRunManager.getManager().removePlayer(pl);
		        					SpeedRunManager.getManager().normal.remove(pl);
		        			        pl.sendMessage(ChatColor.RED + "" + ChatColor.BOLD + "Sorry, but this map is on BETA! We have send you to the lobby! Sorry for the inconvenience");
		        					return true;
		        				}
	        					return true;
	        				}else if (args[2].equalsIgnoreCase("off")) {
		        				Sign sign = (Sign) plains.getBlock().getState();
		        				Sign sign2 = (Sign) plains2.getBlock().getState();
		        				sign.setLine(0, "§3[SpeedRun]");
		        				sign.setLine(1, "§b§lJoin");
		        				sign.setLine(3, "§oNo rank");
		        				sign.update();
		        				sign2.setLine(0, "§3[SpeedRun]");
		        				sign2.setLine(1, "§b§lJoin");
		        				sign2.setLine(3, "§oNo rank");
		        				sign2.update();
		        				p.sendMessage(ChatColor.GOLD + "Sign updated to online!");
		        				Bukkit.getServer().broadcastMessage(ChatColor.AQUA + "[Speedrun] " + ChatColor.GOLD + "The map " + ChatColor.RED + "plains " +  ChatColor.GOLD + "is back online!");
		        			    return true;
	        				}
	        			}else if (args[1].equalsIgnoreCase("jungle")) {
	        				if (args[2].equalsIgnoreCase("on")) {
		        				Sign sign = (Sign) jungle.getBlock().getState();
		        				Sign sign2 = (Sign) jungle2.getBlock().getState();
		        				sign.setLine(0, "§c[SpeedRun]");
		        				sign.setLine(1, "§c§lJoin");
		        				sign.setLine(3, "§4§lVIP-BETA");
		        				sign.update();
		        				sign2.setLine(0, "§c[SpeedRun]");
		        				sign2.setLine(1, "§c§lJoin");
		        				sign2.setLine(3, "§4§lVIP-BETA");
		        				sign2.update();
		        				p.sendMessage(ChatColor.GOLD + "Sign updated to beta!"); 
		        				Bukkit.getServer().broadcastMessage(ChatColor.AQUA + "[Speedrun] " + ChatColor.GOLD + "The map " + ChatColor.RED + "jungle " +  ChatColor.GOLD + "is now on beta, only vips can test and join it!");
		        				for (Player pl : SpeedRunManager.getManager().jungle) {
		        					SpeedRunManager.getManager().removePlayer(pl);
		        					SpeedRunManager.getManager().jungle.remove(pl);
		        			        pl.sendMessage(ChatColor.RED + "" + ChatColor.BOLD + "Sorry, but this map is on BETA! We have send you to the lobby! Sorry for the inconvenience");
		        					return true;
		        				}
	        					return true;
	        				}else if (args[2].equalsIgnoreCase("off")) {
		        				Sign sign = (Sign) jungle.getBlock().getState();
		        				Sign sign2 = (Sign) jungle2.getBlock().getState();
		        				sign.setLine(0, "§3[SpeedRun]");
		        				sign.setLine(1, "§b§lJoin");
		        				sign.setLine(3, "§oNo rank");
		        				sign.update();
		        				sign2.setLine(0, "§3[SpeedRun]");
		        				sign2.setLine(1, "§b§lJoin");
		        				sign2.setLine(3, "§oNo rank");
		        				sign2.update();
		        				p.sendMessage(ChatColor.GOLD + "Sign updated to online!");
		        				Bukkit.getServer().broadcastMessage(ChatColor.AQUA + "[Speedrun] " + ChatColor.GOLD + "The map " + ChatColor.RED + "jungle " +  ChatColor.GOLD + "is back online!");
		        			    return true;
	        				}
	        			}else if (args[1].equalsIgnoreCase("nether")) {
	        				if (args[2].equalsIgnoreCase("on")) {
		        				Sign sign = (Sign) nether.getBlock().getState();
		        				Sign sign2 = (Sign) nether2.getBlock().getState();
		        				sign.setLine(0, "§c[SpeedRun]");
		        				sign.setLine(1, "§c§lJoin");
		        				sign.setLine(3, "§4§lVIP-BETA");
		        				sign.update();
		        				sign2.setLine(0, "§c[SpeedRun]");
		        				sign2.setLine(1, "§c§lJoin");
		        				sign2.setLine(3, "§4§lVIP-BETA");
		        				sign2.update();
		        				p.sendMessage(ChatColor.GOLD + "Sign updated to beta!"); 
		        				Bukkit.getServer().broadcastMessage(ChatColor.AQUA + "[Speedrun] " + ChatColor.GOLD + "The map " + ChatColor.RED + "nether " +  ChatColor.GOLD + "is now on beta, only vips can test and join it!");
		        				for (Player pl : SpeedRunManager.getManager().normal) {
		        					SpeedRunManager.getManager().removePlayer(pl);
		        					SpeedRunManager.getManager().normal.remove(pl);
		        			        pl.sendMessage(ChatColor.RED + "" + ChatColor.BOLD + "Sorry, but this map is on BETA! We have send you to the lobby! Sorry for the inconvenience");
		        					return true;
		        				}
	        					return true;
	        				}else if (args[2].equalsIgnoreCase("off")) {
		        				Sign sign = (Sign) nether.getBlock().getState();
		        				Sign sign2 = (Sign) nether2.getBlock().getState();
		        				sign.setLine(0, "§3[SpeedRun]");
		        				sign.setLine(1, "§b§lJoin");
		        				sign.setLine(3, "§oNo rank");
		        				sign.update();
		        				sign2.setLine(0, "§3[SpeedRun]");
		        				sign2.setLine(1, "§b§lJoin");
		        				sign2.setLine(3, "§oNo rank");
		        				sign2.update();
		        				p.sendMessage(ChatColor.GOLD + "Sign updated to online!");
		        				Bukkit.getServer().broadcastMessage(ChatColor.AQUA + "[Speedrun] " + ChatColor.GOLD + "The map " + ChatColor.RED + "nether " +  ChatColor.GOLD + "is back online!");
		        			    return true;
	        				}
	        			}
	        		}
	        		if (args[0].equalsIgnoreCase("maintenance")) {
        				if (args[1].equalsIgnoreCase("jungle")) {
	        				if (args[2].equalsIgnoreCase("on")) {
		        				Sign sign = (Sign) jungle.getBlock().getState();
		        				Sign sign2 = (Sign) jungle2.getBlock().getState();
		        				sign.setLine(0, "§c[SpeedRun]");
		        				sign.setLine(1, "§4§lMaintenance");
		        				sign.update();
		        				sign2.setLine(0, "§c[SpeedRun]");
		        				sign2.setLine(1, "§4§lMaintenance");
		        				sign2.update();
		        				p.sendMessage(ChatColor.GOLD + "Sign updated to maintenance!"); 
		        				Bukkit.getServer().broadcastMessage(ChatColor.AQUA + "[Speedrun] " + ChatColor.GOLD + "The map " + ChatColor.RED + "jungle " +  ChatColor.GOLD + "is on maintenance!");
		        				for (Player pl : SpeedRunManager.getManager().jungle) {
		        					SpeedRunManager.getManager().removePlayer(pl);
		        					SpeedRunManager.getManager().jungle.remove(pl);
		        			        pl.sendMessage(ChatColor.RED + "" + ChatColor.BOLD + "Sorry, but this map is on maintenance! We have send you to the lobby! Sorry for the inconvenience");
		        					return true;
		        				}
	        					return true;
	        				}else if (args[2].equalsIgnoreCase("off")) {
		        				Sign sign = (Sign) jungle.getBlock().getState();
		        				Sign sign2 = (Sign) jungle2.getBlock().getState();
		        				sign.setLine(0, "§3[SpeedRun]");
		        				sign.setLine(1, "§b§lJoin");
		        				sign.setLine(3, "§oNo rank");
		        				sign.update();
		        				sign2.setLine(0, "§3[SpeedRun]");
		        				sign2.setLine(1, "§b§lJoin");
		        				sign2.setLine(3, "§oNo rank");
		        				sign2.update();
		        				p.sendMessage(ChatColor.GOLD + "Sign updated to online!");
		        				Bukkit.getServer().broadcastMessage(ChatColor.AQUA + "[Speedrun] " + ChatColor.GOLD + "The map " + ChatColor.RED + "jungle " +  ChatColor.GOLD + "is back online!");
		        			    return true;
	        				}
        				}
        				else if (args[1].equalsIgnoreCase("plains")) {
	        				if (args[2].equalsIgnoreCase("on")) {
		        				Sign sign = (Sign) plains.getBlock().getState();
		        				Sign sign2 = (Sign) plains2.getBlock().getState();
		        				sign.setLine(0, "§c[SpeedRun]");
		        				sign.setLine(1, "§4§lMaintenance");
		        				sign.update();
		        				sign2.setLine(0, "§c[SpeedRun]");
		        				sign2.setLine(1, "§4§lMaintenance");
		        				sign2.update();
		        				p.sendMessage(ChatColor.GOLD + "Sign updated to maintenance!"); 
		        				Bukkit.getServer().broadcastMessage(ChatColor.AQUA + "[Speedrun] " + ChatColor.GOLD + "The map " + ChatColor.RED + "plains " +  ChatColor.GOLD + "is on maintenance!");
		        				for (Player pl : SpeedRunManager.getManager().normal) {
		        					SpeedRunManager.getManager().removePlayer(pl); //TODO
		        					SpeedRunManager.getManager().normal.remove(pl);
		        			        pl.sendMessage(ChatColor.RED + "" + ChatColor.BOLD + "Sorry, but this map is on maintenance! We have send you to the lobby! Sorry for the inconvenience");
		        					return true;
		        				}
	        					return true;
	        				}else if (args[2].equalsIgnoreCase("off")) {
		        				Sign sign = (Sign) plains.getBlock().getState();
		        				Sign sign2 = (Sign) plains2.getBlock().getState();
		        				sign.setLine(0, "§3[SpeedRun]");
		        				sign.setLine(1, "§b§lJoin");
		        				sign.setLine(3, "§oNo rank");
		        				sign.update();
		        				sign2.setLine(0, "§3[SpeedRun]");
		        				sign2.setLine(1, "§b§lJoin");
		        				sign2.setLine(3, "§oNo rank");
		        				sign2.update();
		        				p.sendMessage(ChatColor.GOLD + "Sign updated to online!");
		        				Bukkit.getServer().broadcastMessage(ChatColor.AQUA + "[Speedrun] " + ChatColor.GOLD + "The map " + ChatColor.RED + "plains " +  ChatColor.GOLD + "is back online!");
		        			    return true;
	        				}
	        			}else if (args[1].equalsIgnoreCase("nether")) {
	        				if (args[2].equalsIgnoreCase("on")) {
		        				Sign sign = (Sign) nether.getBlock().getState();
		        				Sign sign2 = (Sign) nether2.getBlock().getState();
		        				sign.setLine(0, "§c[SpeedRun]");
		        				sign.setLine(1, "§4§lMaintenance");
		        				sign.update();
		        				sign2.setLine(0, "§c[SpeedRun]");
		        				sign2.setLine(1, "§4§lMaintenance");
		        				sign2.update();
		        				p.sendMessage(ChatColor.GOLD + "Sign updated to maintenance!"); 
		        				Bukkit.getServer().broadcastMessage(ChatColor.AQUA + "[Speedrun] " + ChatColor.GOLD + "The map " + ChatColor.RED + "nether " +  ChatColor.GOLD + "is on maintenance!");
		        				for (Player pl : SpeedRunManager.getManager().nether) {
		        					SpeedRunManager.getManager().removePlayer(pl);
		        					SpeedRunManager.getManager().nether.remove(pl);
		        			        pl.sendMessage(ChatColor.RED + "" + ChatColor.BOLD + "Sorry, but this map is on maintenance! We have send you to the lobby! Sorry for the inconvenience");
		        					return true;
		        				}
	        					return true;
	        				}else if (args[2].equalsIgnoreCase("off")) {
		        				Sign sign = (Sign) nether.getBlock().getState();
		        				Sign sign2 = (Sign) nether2.getBlock().getState();
		        				sign.setLine(0, "§3[SpeedRun]");
		        				sign.setLine(1, "§b§lJoin");
		        				sign.setLine(3, "§oNo rank");
		        				sign.update();
		        				sign2.setLine(0, "§3[SpeedRun]");
		        				sign2.setLine(1, "§b§lJoin");
		        				sign2.setLine(3, "§oNo rank");
		        				sign2.update();
		        				p.sendMessage(ChatColor.GOLD + "Sign updated to online!");
		        				Bukkit.getServer().broadcastMessage(ChatColor.AQUA + "[Speedrun] " + ChatColor.GOLD + "The map " + ChatColor.RED + "nether " +  ChatColor.GOLD + "is back online!");
	        					return true;
	        				}
	        			}
	            	}
	        		if (args[0].equalsIgnoreCase("defect")) {
	        			if (args[1].equalsIgnoreCase("plains")) {
	        				if (args[2].equalsIgnoreCase("on")) {
		        				Sign sign = (Sign) plains.getBlock().getState();
		        				Sign sign2 = (Sign) plains2.getBlock().getState();
		        				sign.setLine(0, "§c[SpeedRun]");
		        				sign.setLine(1, "§4§lDefect");
		        				sign.update();
		        				sign2.setLine(0, "§c[SpeedRun]");
		        				sign2.setLine(1, "§4§lDefect");
		        				sign2.update();
		        				p.sendMessage(ChatColor.GOLD + "Sign updated to defect!"); 
		        				Bukkit.getServer().broadcastMessage(ChatColor.AQUA + "[Speedrun] " + ChatColor.GOLD + "The map " + ChatColor.RED + "plains " +  ChatColor.GOLD + "is defect!");
		        				for (Player pl : SpeedRunManager.getManager().normal) {
		        					SpeedRunManager.getManager().removePlayerWhenDefect(pl);
		        					SpeedRunManager.getManager().normal.remove(pl);
		        					return true;
		        				}
	        					return true;
	        				}else if (args[2].equalsIgnoreCase("off")) {
		        				Sign sign = (Sign) plains.getBlock().getState();
		        				Sign sign2 = (Sign) plains2.getBlock().getState();
		        				sign.setLine(0, "§3[SpeedRun]");
		        				sign.setLine(1, "§b§lJoin");
		        				sign.setLine(3, "§oNo rank");
		        				sign.update();
		        				sign2.setLine(0, "§3[SpeedRun]");
		        				sign2.setLine(1, "§b§lJoin");
		        				sign2.setLine(3, "§oNo rank");
		        				sign2.update();
		        				p.sendMessage(ChatColor.GOLD + "Sign updated to online!");
		        				Bukkit.getServer().broadcastMessage(ChatColor.AQUA + "[Speedrun] " + ChatColor.GOLD + "The map " + ChatColor.RED + "plains " +  ChatColor.GOLD + "is back online!");
		        			    return true;
	        				}
	        			}else if (args[1].equalsIgnoreCase("nether")) {
	        				if (args[2].equalsIgnoreCase("on")) {
		        				Sign sign = (Sign) nether.getBlock().getState();
		        				Sign sign2 = (Sign) nether2.getBlock().getState();
		        				sign.setLine(0, "§c[SpeedRun]");
		        				sign.setLine(1, "§4§lDefect");
		        				sign.update();
		        				sign2.setLine(0, "§c[SpeedRun]");
		        				sign2.setLine(1, "§4§lDefect");
		        				sign2.update();
		        				p.sendMessage(ChatColor.GOLD + "Sign updated to defect!"); 
		        				Bukkit.getServer().broadcastMessage(ChatColor.AQUA + "[Speedrun] " + ChatColor.GOLD + "The map " + ChatColor.RED + "nether " +  ChatColor.GOLD + "is defect!");
		        				for (Player pl : SpeedRunManager.getManager().nether) {
		        					SpeedRunManager.getManager().removePlayerWhenDefect(pl);
		        					SpeedRunManager.getManager().nether.remove(pl);
		        					return true;
		        				}
	        					return true;
	        				}else if (args[2].equalsIgnoreCase("off")) {
		        				Sign sign = (Sign) nether.getBlock().getState();
		        				Sign sign2 = (Sign) nether2.getBlock().getState();
		        				sign.setLine(0, "§3[SpeedRun]");
		        				sign.setLine(1, "§b§lJoin");
		        				sign.setLine(3, "§oNo rank");
		        				sign.update();
		        				sign2.setLine(0, "§3[SpeedRun]");
		        				sign2.setLine(1, "§b§lJoin");
		        				sign2.setLine(3, "§oNo rank");
		        				sign2.update();
		        				p.sendMessage(ChatColor.GOLD + "Sign updated to online!");
		        				Bukkit.getServer().broadcastMessage(ChatColor.AQUA + "[Speedrun] " + ChatColor.GOLD + "The map " + ChatColor.RED + "nether " +  ChatColor.GOLD + "is back online!");
	        					return true;
	        				}
	        			}else if (args[1].equalsIgnoreCase("jungle")) {
	        				if (args[2].equalsIgnoreCase("on")) {
		        				Sign sign = (Sign) jungle.getBlock().getState();
		        				Sign sign2 = (Sign) jungle2.getBlock().getState();
		        				sign.setLine(0, "§c[SpeedRun]");
		        				sign.setLine(1, "§4§lDefect");
		        				sign.update();
		        				sign2.setLine(0, "§c[SpeedRun]");
		        				sign2.setLine(1, "§4§lDefect");
		        				sign2.update();
		        				p.sendMessage(ChatColor.GOLD + "Sign updated to defect!"); 
		        				Bukkit.getServer().broadcastMessage(ChatColor.AQUA + "[Speedrun] " + ChatColor.GOLD + "The map " + ChatColor.RED + "jungle " +  ChatColor.GOLD + "is defect!");
		        				for (Player pl : SpeedRunManager.getManager().jungle) {
		        					SpeedRunManager.getManager().removePlayerWhenDefect(pl);
		        					SpeedRunManager.getManager().jungle.remove(pl);
		        					return true;
		        				}
	        					return true;
	        				}else if (args[2].equalsIgnoreCase("off")) {
		        				Sign sign = (Sign) jungle.getBlock().getState();
		        				Sign sign2 = (Sign) jungle2.getBlock().getState();
		        				sign.setLine(0, "§3[SpeedRun]");
		        				sign.setLine(1, "§b§lJoin");
		        				sign.setLine(3, "§oNo rank");
		        				sign.update();
		        				sign2.setLine(0, "§3[SpeedRun]");
		        				sign2.setLine(1, "§b§lJoin");
		        				sign2.setLine(3, "§oNo rank");
		        				sign2.update();
		        				p.sendMessage(ChatColor.GOLD + "Sign updated to online!");
		        				Bukkit.getServer().broadcastMessage(ChatColor.AQUA + "[Speedrun] " + ChatColor.GOLD + "The map " + ChatColor.RED + "jungle " +  ChatColor.GOLD + "is back online!");
	        					return true;
	        				}
	        				}
	        		}
	        	}
	        }
			return false;
	   }
}
