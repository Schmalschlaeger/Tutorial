package cock.topia.tvg;

import java.io.File;

import net.milkbowl.vault.economy.Economy;
import net.milkbowl.vault.permission.Permission;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

import ru.tehkode.permissions.PermissionUser;
import ru.tehkode.permissions.bukkit.PermissionsEx;
import cock.topia.tvg.Inventory.ItemShop;
import cock.topia.tvg.Inventory.Scoreboard;
import cock.topia.tvg.Kits.DonatorKits;
import cock.topia.tvg.Listeners.ClickEvents;
import cock.topia.tvg.Listeners.JoinListener;
import cock.topia.tvg.Utils.ChatPrefix;
import cock.topia.tvg.Utils.MoneyManager;
import cock.topia.tvg.Utils.drops;


public class Main extends JavaPlugin{
	
	public static Permission perms = null;
    public static Economy econ = null;
    public Scoreboard sb = null;
    public MoneyManager mm = null;
    
    public static File dataF;
    public static FileConfiguration data;
	public static World lobbyWorld = Bukkit.getWorld("world");
	
	public void onEnable() {
        if (!setupEconomy() ) {
            System.out.print(String.format("[%s] - Disabled due to no Vault dependency found!", getDescription().getName()));
            getServer().getPluginManager().disablePlugin(this);
            return;
        }
        setupPermissions();
    	dataF = new File(getDataFolder(), "data.yml");
    	data = YamlConfiguration.loadConfiguration(dataF);
    	saveCustomConfig();
        
        sb = new Scoreboard(this);
        mm = new MoneyManager(this);
        
        Bukkit.getServer().getPluginManager().registerEvents(new JoinListener(this), this);
        Bukkit.getServer().getPluginManager().registerEvents(new ClickEvents(this), this);
        Bukkit.getServer().getPluginManager().registerEvents(new DonatorKits(this), this);
        Bukkit.getServer().getPluginManager().registerEvents(new ItemShop(this), this);
        Bukkit.getServer().getPluginManager().registerEvents(new drops(), this);
        //Bukkit.getServer().getPluginManager().registerEvents(new RankScoreboard(this), this);
        Bukkit.getServer().getPluginManager().registerEvents(new ChatPrefix(this), this);
	}
	
    public static void saveCustomConfig(){
    	try{
    		data.save(dataF);
    	}catch(Exception e){
    		e.printStackTrace();
    		}
    	}
	
	private boolean setupEconomy() {
        if (getServer().getPluginManager().getPlugin("Vault") == null) {
            return false;
        }
        RegisteredServiceProvider<Economy> rsp = getServer().getServicesManager().getRegistration(Economy.class);
        if (rsp == null) {
            return false;
        }
        econ = rsp.getProvider();
        return econ != null;
	}
    
	
	private boolean setupPermissions() {
        RegisteredServiceProvider<Permission> rsp = getServer().getServicesManager().getRegistration(Permission.class);
        perms = rsp.getProvider();
        return perms != null;
    }
	
	  public boolean playerInGroup(String worldName, OfflinePlayer op, String groupName)
	  {
	    PermissionUser user = getUser(op);
	    if (user == null) {
	      return false;
	    }
	    return user.inGroup(groupName, worldName);
	  }
	  public PermissionUser getUser(OfflinePlayer op) {
	    return PermissionsEx.getPermissionManager().getUser(op.getUniqueId());
	  }
	
}
