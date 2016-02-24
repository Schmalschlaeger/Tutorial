package simple.essentials;

import java.io.File;
import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import simple.essentials.Commands.FlyCommand;
import simple.essentials.Commands.GamemodeCommand;
import simple.essentials.Commands.HealCommand;
import simple.essentials.Commands.HelpCommand;
import simple.essentials.Commands.KickBanCommand;
import simple.essentials.Commands.KillallCommand;
import simple.essentials.Commands.MsgCommand;
import simple.essentials.Commands.SunCommand;
import simple.essentials.Commands.TeleportCommand;
import simple.essentials.Commands.TimeCommand;
import simple.essentials.Commands.WarpCommand;
import simple.essentials.Commands.WatchCommand;
import simple.essentials.Utils.Listeners;

public class Main extends JavaPlugin {
	
	public static HashMap<Player, Player> watcher =  new HashMap<Player, Player>();
	
	public static File newConfig;
	public static FileConfiguration newConfigz;
    
    public static File PConfig;
    public static FileConfiguration playerConfig;
	
	public static String flyPerms = "simpleEssentials.command.fly";
	public static String gamemodePerms = "simpleEssentials.command.gamemode";
	public static String BackPerms = "simpleEssentials.command.back";
	public static String kickPerms = "simpleEssentials.command.kick";
	public static String banPerms = "simpleEssentials.command.ban";
	public static String unbanPerms = "simpleEssentials.command.unban";
	
	public static String warpPerms = "simpleEssentials.command.warp";
	public static String warpListPerms = "simpleEssentials.command.warplist";
	//public static String warpCustomPerms = "simpleEssentials.command.warp." + newConfigz.getString("warps.");
	public static String teleportAllPerms = "simpleEssentials.command.tpAll";
	
	public void onEnable() {
		Bukkit.getServer().getPluginManager().registerEvents(new Listeners(), this);
		
    	newConfig = new File(getDataFolder(), "data.yml");
    	newConfigz = YamlConfiguration.loadConfiguration(newConfig);
    	saveCustomConfig();
    	
    	PConfig = new File(getDataFolder(), "playerData.yml");
    	playerConfig = YamlConfiguration.loadConfiguration(PConfig);
    	saveCustomPlayerConfig();
    	
		getCommand("gamemode").setExecutor(new GamemodeCommand());
		getCommand("fly").setExecutor(new FlyCommand());
		getCommand("sun").setExecutor(new SunCommand());
		getCommand("warp").setExecutor(new WarpCommand());
		getCommand("kick").setExecutor(new KickBanCommand());
		getCommand("ban").setExecutor(new KickBanCommand());
		getCommand("heal").setExecutor(new HealCommand());
		getCommand("feed").setExecutor(new HealCommand());
		getCommand("createwarp").setExecutor(new WarpCommand());
		getCommand("teleportall").setExecutor(new TeleportCommand(this));
		getCommand("tp").setExecutor(new TeleportCommand(this));
		getCommand("msg").setExecutor(new MsgCommand());
		getCommand("watch").setExecutor(new WatchCommand());
		getCommand("day").setExecutor(new TimeCommand());
		getCommand("night").setExecutor(new TimeCommand());
		getCommand("killall").setExecutor(new KillallCommand());
		getCommand("help").setExecutor(new HelpCommand());
		
	}
	
    public static void saveCustomConfig(){
    	try{
    		newConfigz.save(newConfig);
    	}catch(Exception e){
    		e.printStackTrace();
    		}
    	}	
    
    public static void saveCustomPlayerConfig(){
    	try{
    		playerConfig.save(PConfig);
    	}catch(Exception e){
    		e.printStackTrace();
    		}
    	}

}
