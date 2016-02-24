package lobbyhub;

import java.io.File;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.plugin.java.JavaPlugin;
 
public class Main extends JavaPlugin implements Listener{

    API h = new API(this);
    
    File newConfig;
    FileConfiguration newConfigz;
    
    public void onEnable() {
    	Bukkit.getServer().getPluginManager().registerEvents(new API(this), this);
    	Bukkit.getServer().getPluginManager().registerEvents(this, this);	
    	
    	newConfig = new File(getDataFolder(), "lobbys.yml"); // set the file location
    	newConfigz = YamlConfiguration.loadConfiguration(newConfig); // this will give you all the functions such as .getInt, getString ect..
    	saveNewConfig();
    	
        getConfig().options().copyDefaults(true);
    	saveDefaultConfig();
    }
    
    public void onDisable() {
    	reloadConfig();
    }
    
    public void saveNewConfig() {
    	try{
    	newConfigz.save(newConfig);
    	 
    	}catch(Exception e){
    	e.printStackTrace();
    	}
    	}
    
   
    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args){
        final Player p = (Player) sender;
        final String prefix = ChatColor.translateAlternateColorCodes('&', getConfig().getString("prefix") + " ");
        String sethub = prefix + ChatColor.RED + "If you want to set the hub? Use /lh sethub!";
        if (!(sender instanceof Player)) {
        	sender.sendMessage("Sorry, but only players can use this command ingame! If this is an error, ask the developer!");
        	return true;
        }
        
        if (cmd.getName().equalsIgnoreCase("lobbyhub")) {
        	if (args.length == 0) {
            	p.sendMessage(ChatColor.GREEN + "------------- " + prefix + ChatColor.GREEN +  "---------------------");
            	p.sendMessage(ChatColor.GREEN + "Developed by: " + ChatColor.GRAY + "jusjus112");
            	p.sendMessage(ChatColor.GREEN + "Version: " + ChatColor.GRAY + " 0.2");
            	p.sendMessage(ChatColor.GREEN + "Minecraft version: " + ChatColor.GRAY + "1.7.2");
            	p.sendMessage(ChatColor.GOLD  + "Typ /lh help for an list of commands.");
            	return true;
                }
            }
        if (args.length == 1) {
    		if (args[0].equalsIgnoreCase("help")) {
    			p.sendMessage(ChatColor.GREEN + "----------- " + prefix + ChatColor.DARK_GREEN +  "[Commands]" + ChatColor.GREEN + " --------------");
    			p.sendMessage(ChatColor.AQUA + "/lobbyhub/lh: " + ChatColor.GRAY + "Info about the plugin");
    			p.sendMessage(ChatColor.AQUA + "/<lobbyhub/lh> sethub: " + ChatColor.GRAY + "Sets the hub");
    			p.sendMessage(ChatColor.AQUA + "/<lobbyhub/lh> reload: " + ChatColor.GRAY + "Reloads the config");
    			p.sendMessage(ChatColor.AQUA + "/<lobbyhub/lh> createlobby <name>: " + ChatColor.GRAY + "Create an lobby");
    			p.sendMessage(ChatColor.AQUA + "/<lobbyhub/lh> removelobby <name>: " + ChatColor.GRAY + "Remove an lobby");
    			p.sendMessage(ChatColor.AQUA + "/<lobbyhub/lh> list: " + ChatColor.GRAY + "List of all lobbies");
    			p.sendMessage(ChatColor.AQUA + "/hub: " + ChatColor.GRAY + "Teleports you to the hub");
    			p.sendMessage(ChatColor.AQUA + "/lobby <name>: " + ChatColor.GRAY + "Teleports you to the lobby");
    			return true;
    		}
    		if (args.length > 1) {
    			p.sendMessage(prefix + ChatColor.RED + "Error, too many arguments! Try /lobbyhub|lh help");
        		return true;
        	}   
        }
        if (args.length == 2) {
       		if (args[0].equalsIgnoreCase("createlobby")) {
           		if (!p.hasPermission("lh.createlobby")) {
           			p.sendMessage(prefix + ChatColor.RED + "You dont have permissions!");
           			return true;
               	}
           		if (args.length > 2) {
        			p.sendMessage(prefix + ChatColor.RED + "Error, too many arguments! Try /lobbyhub|lh help");
            		return true;
            	}
           		if (args.length == 2){
                    h.createLobby(p, args[1]);
                    return true;
                }else{
                	p.sendMessage(prefix + ChatColor.RED + "Please specify an lobby name! /createlobby <lobbyname>");
                	return true;
                }
       		}
        }
        	if (args.length == 1) {
        		if (args[0].equalsIgnoreCase("sethub")) {
        			if (!p.hasPermission("lh.sethub")) {
        				p.sendMessage(prefix + ChatColor.RED + "You dont have permissions!");
        				return true;
        			}
        			h.createHub(p);
        			return true;
        		}
        		if (args.length > 1) {
        			p.sendMessage(prefix + ChatColor.RED + "Error, too many arguments! Try /lobbyhub|l h help");
            		return true;
            	}
        	}
        	if (args.length == 1){
        		if (args[0].equalsIgnoreCase("reload")) {
        			if (!p.hasPermission("lh.reload")) {
        				p.sendMessage(prefix + ChatColor.RED + "You dont have permissions");		
        				return true;
					}
        			p.sendMessage(prefix + ChatColor.GREEN + "Reload config........");
        	    	reloadConfig();
        			saveNewConfig();
        			Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(this, new Runnable() {
						@Override
						public void run() {
							p.sendMessage(prefix + ChatColor.GREEN + "Config reloaded!");
						}
        			}, 20L);
        		}
        	}
        	if (args.length == 1) {
        		if (args[0].equalsIgnoreCase("list")) {
        			if (!p.hasPermission("lh.list")) {
        				p.sendMessage(prefix + ChatColor.RED + "You dont have permissions");
        				return true;
        			}
        			h.listLobbies(p);
        			return true;
        		}
        	}
       		if (args.length == 2) {
           		if (args[0].equalsIgnoreCase("removelobby")) {
               		if (!p.hasPermission("lh.removelobby")) {
               			p.sendMessage(prefix + ChatColor.RED + "You dont have permissions!");
               			return true;
               		}
                        h.removeLobby(p, args[1]);
                        return true;
           		}
           		if (args.length > 2) {
        			p.sendMessage(prefix + ChatColor.RED + "Error, too many arguments! Try /lobbyhub|lh help");
            		return true;
            	}
       		}
       		if (cmd.getName().equalsIgnoreCase("hub")) {
           		if (!p.hasPermission("lh.hub")) {
           			p.sendMessage(prefix + ChatColor.RED + "You dont have permissions!");
           			return true;
           		}
           		if (this.getConfig().getBoolean("enablehub", true)) {
           		h.teleportToHub(p);
       		} else {
       			Bukkit.getLogger().info("[LobbyHub] " + sender.getName() + " tried to use the /hub, EnableHub in the config is DisAbled!");
       			sender.sendMessage(prefix + ChatColor.RED + "You cant use the hub right now!");
       			return true;
       		}
       		if (args.length > 1) {
    			p.sendMessage(prefix + ChatColor.RED + "Error, too many arguments! Try /lobbyhub|lh help'");
        		return true;
        	}
       		}
       		if (cmd.getName().equalsIgnoreCase("sethub")) {
           		if (!p.hasPermission("lh.sethub")) {
           			p.sendMessage(prefix + ChatColor.RED + "You dont have permissions!");
           			return true;
           		}
           		p.sendMessage(sethub);
           		return true;
       		}
      	if (cmd.getName().equalsIgnoreCase("lobby")) {
       		if (!p.hasPermission("lh.lobby")) {
       			p.sendMessage(prefix + ChatColor.RED + "You dont have permissions!");
       			return true;
       		}
       		if (args.length == 1) {
       			if (this.getConfig().getBoolean("enablelobbys", true)) {
                h.teleportToLobby(p, args[0]);
                return true;
      	} else {
      		Bukkit.getLogger().info("[LobbyHub] " + sender.getName() + " tried to use the /lobby, EnableLobby in the config is DisAbled!");
   			sender.sendMessage(prefix + ChatColor.RED + "You cant teleport to an lobby right now!");
   			return true;
      	}
       		}
       		if (args.length < 1) {
       			p.sendMessage(prefix + ChatColor.RED + "No lobby has found! Try /lobby <lobbyName>"); 
                return true;
      	}
       		
            else{
            	p.sendMessage(prefix + ChatColor.RED + "No lobby has found!");  
            	
            }
      	}
		return false;
      	}
    
    @EventHandler
    public void onPlayerRespawn(PlayerRespawnEvent e) {
    	Player p = e.getPlayer();
    	
    	if (this.getConfig().getBoolean("enablehub", true)) {
       		h.teleportToHub(p);
   		} else {
   			Bukkit.getLogger().info("[LobbyHub] " + p.getName() + " is died and tried to to go to the hub. EnableHub in the config is DisAbled!");
   		}
    }
 }
