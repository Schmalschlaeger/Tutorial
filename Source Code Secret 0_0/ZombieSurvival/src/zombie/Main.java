package zombie;

import java.io.File;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;

import zombie.MessageManager.MsgType;

public class Main extends JavaPlugin implements Listener{
	
	API h = new API(this);
	
    File newConfig;
    FileConfiguration newConfigz;

    @Override
    public void onEnable(){
        if(!getDataFolder().exists())
            getDataFolder().mkdir();

        if(getConfig() == null)
            saveDefaultConfig();

        new ArenaManager(this);
        ArenaManager.getManager().loadGames();
        
        newConfig = new File(getDataFolder(), "lobbys.yml"); // set the file location
    	newConfigz = YamlConfiguration.loadConfiguration(newConfig); // this will give you all the functions such as .getInt, getString ect..
    	saveNewConfig();

        getServer().getPluginManager().registerEvents(new GameListener(this), this);
    }

    @Override
    public void onDisable(){
        saveConfig();
    }
    
    public void saveNewConfig(){
    	try{
    	newConfigz.save(newConfig);
    	 
    	}catch(Exception e){
    	e.printStackTrace();
    	}
    	}
    
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e) {
    	h.teleportToHub(e.getPlayer());
    }
    

    public boolean onCommand(CommandSender sender, Command cmd, String label, String args[]){    	
        if(!(sender instanceof Player)){
            sender.sendMessage(MsgType.CONSOLE + "Only players can use this command!");
            return true;
        }
        
        final Player p = (Player) sender;
        
        if (cmd.getName().equalsIgnoreCase("zombie")) {
        	if (args.length == 0) {
        	p.sendMessage(ChatColor.GOLD + "------------- " + MsgType.NORMAL + "---------------------");
        	p.sendMessage(ChatColor.GOLD + "Developed by: " + ChatColor.GRAY + "jusjus112");
        	p.sendMessage(ChatColor.GOLD + "Version: " + ChatColor.GRAY + " 0.5");
        	p.sendMessage(ChatColor.GOLD + "Minecraft version: " + ChatColor.GRAY + "1.7.2");
        	p.sendMessage(ChatColor.YELLOW  + "Typ /zombie help for an list of commands.");
        	return false;
        }
        	if (args.length == 1) {
        		if (args[0].equalsIgnoreCase("help")) {
        			if (p.hasPermission("gm.admin")) {
        			p.sendMessage(ChatColor.GOLD + "----------- " + MsgType.NORMAL + ChatColor.RED +  "[Commands]" + ChatColor.GOLD + " --------------");
        			p.sendMessage(ChatColor.GOLD + "/zombie|z: " + ChatColor.GRAY + "Info about the plugin");
        			p.sendMessage(ChatColor.GOLD + "/zombie|z: create: " + ChatColor.GRAY + "Creates an arena");
        			p.sendMessage(ChatColor.GOLD + "/zombie|z: delete <arenaID>: " + ChatColor.GRAY + "Deletes an arena");
        			p.sendMessage(ChatColor.GOLD + "/zombie|z: join <arenaID>: " + ChatColor.GRAY + "Joins an game");
        			p.sendMessage(ChatColor.GOLD + "/zombie|z: leave: " + ChatColor.GRAY + "Leave an game");
        			p.sendMessage(ChatColor.GOLD + "/zombie|z: reload: " + ChatColor.GRAY + "Reloads the config");
        			return true;
        		}
        	}
        }
        	if (args.length == 1) {
        		if (p.hasPermission("gm.admin")) {
        		}
        		if (args[0].equalsIgnoreCase("create")) {
        			ArenaManager.getManager().createArena(p.getLocation());
                    p.sendMessage(MsgType.ARENA + "" + ChatColor.GREEN + "Created arena at " + ChatColor.GOLD + "'" + ChatColor.AQUA + p.getLocation().getWorld().getName() + ChatColor.GOLD + "'");
                    return true;
        		}
        	}      	
        	if (args.length == 2) {
        		if (args[0].equalsIgnoreCase("join")) {
        			if (p.hasPermission("gm.user")) {
        			}
        			if(args.length > 2){
     	                p.sendMessage(MsgType.ERROR + "Invalid section! Try /gunmaster [join] <arena>");
     	                return true;
     	            }
        				
        			 if(args.length != 2){
        	                p.sendMessage(MsgType.ERROR + "Invalid section! Try /gunmaster [join] <arena>");
        	                return true;
        	            }
        	            int num = 0;
        	            try{
        	                num = Integer.parseInt(args[1]);
        	            }catch(NumberFormatException e){
        	                return true;
        	            }
        	            ArenaManager.getManager().addPlayer(p, num);
        	            return true;
        		}
        	}
        	if (args.length == 1) {
        		if (args[0].equalsIgnoreCase("leave")) {
        			if (p.hasPermission("gm.user")) {
        			}
        			if (ArenaManager.getManager().isInGame(p)) {
        			ArenaManager.getManager().removePlayer(p);
                    p.sendMessage(MsgType.NORMAL + "" + ChatColor.YELLOW + "You have left the arena!");
                    return true;
        		}else {
        			p.sendMessage(MsgType.ERROR + "You are not ingame");
        		}
        	}
        }
        	if (args.length == 1) {
        		if (args[0].equalsIgnoreCase("setlobby")) {
        			if (p.hasPermission("gm.setlobby")) {
        			}
        			h.createHub(p);
                    return true;
        		}
        	}
        	if (args.length == 1) {
        		if (args[0].equalsIgnoreCase("reload")) {
        			if (p.hasPermission("gm.admin")) {
        			p.sendMessage(MsgType.NORMAL + "Reload config........");
        			this.reloadConfig();
        			p.sendMessage(MsgType.NORMAL + "Succesfull reloaded the config!");
        			return true;
        		}
        	}
        }
        	if (args.length == 1) {
        		if (args[0].equalsIgnoreCase("force")) {
        			if (p.hasPermission("gm.admin")) {
        			// TODO: adds force end and start
        				return true;
        		}
        	}
        }
        	
        	if (args.length == 2) {
        		if (args[0].equalsIgnoreCase("delete")) {
        			if (p.hasPermission("gm.admin")) {
        			if (args.length == 1) {
        				p.sendMessage(MsgType.NORMAL + "Please specify an arena ID");
        				return true;
        			}if(args.length > 2){
        	                p.sendMessage(MsgType.ERROR + "Invalid section! Try /gunmaster [delete] <arenaID>");
        	                return true;
        	        	}
        			}
        		}
        		int num = 0;
                    try{
                        num = Integer.parseInt(args[1]);
                    }catch(NumberFormatException e){
                        p.sendMessage(MsgType.DENIED + "Invalid arena " + num);
                        
                    }
                    ArenaManager.getManager().removeArena(num);
                    p.sendMessage(MsgType.NORMAL + "Removed arena " + num);
                    return true;
        		}
        }
        return false;
    }

}