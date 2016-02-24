package Mob;

import Mob.MobBarAPI;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class bossbar extends JavaPlugin implements Listener {
	
	@Override
	public void onEnable() {
		Bukkit.getServer().getPluginManager().registerEvents(this, this);
		
		FileConfiguration config = this.getConfig();
    	config.options().copyDefaults(true);
    	config.addDefault("bossbar.text", "§6Welcome to this server {player}");
    	saveConfig();
	}
	
	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent e) {
		final Player p = e.getPlayer();

			Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(this, new Runnable() {
				@Override
				public void run() {
					try {
						MobBarAPI.getInstance().setStatus(p, ((String) getConfig().get("bossbar.text")).replace("{player}", p.getName()), 100, true);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}, 4);
		}
	
	@EventHandler
	public void onPlayerDeath(PlayerRespawnEvent e) {
		final Player p = e.getPlayer();

			Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(this, new Runnable() {
				@Override
				public void run() {
					try {
						MobBarAPI.getInstance().setStatus(p, ((String) getConfig().get("bossbar.text")).replace("{player}", p.getName()), 100, true);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}, 4);
		}
	
	 public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args){
	        final Player p = (Player) sender;
	        
	        if (cmd.getName().equalsIgnoreCase("bossbar")) {
	        	if (args.length == 0) {
	            	p.sendMessage(ChatColor.GREEN + "------------- " + ChatColor.GOLD + ChatColor.GREEN +  "---------------------");
	            	p.sendMessage(ChatColor.GREEN + "Developed by: " + ChatColor.GRAY + "jusjus112");
	            	p.sendMessage(ChatColor.GREEN + "Version: " + ChatColor.GRAY + " 0.5");
	            	p.sendMessage(ChatColor.GREEN + "Minecraft version: " + ChatColor.GRAY + "1.7.2");
	            	return true;
	                }
	            }
	        if (args.length == 1){
        		if (args[0].equalsIgnoreCase("reload")) {
        			if (!p.hasPermission("network.reload")) {
        				p.sendMessage(ChatColor.GOLD + "You dont have permissions");		
        				return true;
					}
        			p.sendMessage(ChatColor.GOLD + "Reload config........");
        			this.reloadConfig();
        			Bukkit.getScheduler().scheduleSyncDelayedTask(this, new Runnable() {
						@Override
						public void run() {
							p.sendMessage(ChatColor.GOLD + "Config reloaded!");
						}
        			}, 20L);
        		}
        	}
			return false;
	 }
}