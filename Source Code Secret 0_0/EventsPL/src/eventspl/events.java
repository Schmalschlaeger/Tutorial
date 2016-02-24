package eventspl;

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
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class events extends JavaPlugin implements Listener {
	
	API h = new API(this);
	
    File newConfig;
    FileConfiguration newConfigz;
    
 //   private int id;
    
 //   public void setId(int id) {
  //      this.id = id;
  //  }
    
    public boolean enabled = false;
    
    public String prefix = ChatColor.GRAY + "[" + ChatColor.RED + "Events" + ChatColor.GRAY + "] ";
	
	public void onEnable() {
		Bukkit.getServer().getPluginManager().registerEvents(this, this);

		newConfig = new File(getDataFolder(), "lobbys.yml");
    	newConfigz = YamlConfiguration.loadConfiguration(newConfig);
    	saveNewConfig();
		
		FileConfiguration conf = this.getConfig();
		conf.addDefault("eventstartdelay", 5);
		conf.addDefault("eventstartamount", 1);
		conf.addDefault("eventstartmessage", "&2An event has started! Typ &c/event join &2to join");
		conf.options().copyDefaults(true);
		saveConfig();
		
	    final int delay = this.getConfig().getInt("eventstartdelay");
	    final int amount = this.getConfig().getInt("eventstartamount");
		final FileConfiguration config = this.getConfig();
		Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(this, new Runnable() {
			@Override
			public void run() {
			if (enabled = true) {
				if (Bukkit.getServer().getOnlinePlayers().length == amount) {
					Bukkit.getServer().broadcastMessage(ChatColor.translateAlternateColorCodes('&', config.getString("eventstartmessage")));
					enabled = false;
				}else {
					Bukkit.getServer().broadcastMessage(prefix + ChatColor.RED + "No event will be started! There must atleast online:");
					Bukkit.getServer().broadcastMessage(ChatColor.GREEN + "" + amount + ChatColor.RED + " players to start an event! Time before next check: "
							+ ChatColor.GREEN + delay + ChatColor.RED + " minuts!");
					Bukkit.getServer().broadcastMessage(ChatColor.RED + "There are now: " + ChatColor.GREEN + Bukkit.getServer().getOnlinePlayers().length + ChatColor.RED 
							+ " players online!");
				}
	     			if (enabled = false) {
	     				
	     			}
				}
			}
		}, delay * 1200, delay * 1200);
	}	
	
	public void onDisable() {
    	this.reloadConfig();
    }
	
	
	public void saveNewConfig() {
    	try{
    	newConfigz.save(newConfig);
    	 
    	}catch(Exception e){
    	e.printStackTrace();
    	}
    	}
	
	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent e) {

	}
	
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args){
        final Player p = (Player) sender;
        if (cmd.getName().equalsIgnoreCase("event")) {
        	if (args.length == 1) {
            	 if (args[0].equalsIgnoreCase("join")) {
            		 if (enabled = true) {
            		 p.sendMessage(ChatColor.RED + "Events is gestart! Dat klopt :D");
            		 return true;
            		 }else {
            			 if (enabled = false) {
            				 p.sendMessage("Event isnt started yet! fout!");
            			 }
            		 }
            	}
        	}
        }
			return false;
	}

}
