package jeroen.server.Economy;

import java.io.File;

import jeroen.server.Main;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

public class ECOSystem {
	
	Main plugin;
	
	public ECOSystem(Main plugin) {
		this.plugin = plugin;
	}
	
    File eco;
    FileConfiguration ecoBase;
	
	public void addBalance(Player player, int balance) {
		if (ecoBase.contains("Economy." + player.getName())) {
		ecoBase.set("Economy." + player.getName() + ".balance", getBalance(player) + balance);
		saveEcoConfig();
		}else {
			ecoBase.addDefault("Economy." + player.getName() + ".balance", balance);
			saveEcoConfig();
		}
	}
	
	public void setBalance(Player player, int balance) {
		if (ecoBase.contains("Economy." + player.getName())) {
			ecoBase.set("Economy." + player.getName() + ".balance", balance);
			saveEcoConfig();
			}else {
				ecoBase.addDefault("Economy." + player.getName() + ".balance", balance);
				saveEcoConfig();
			}
	}
	
	public void removeBalance(Player player) {
		ecoBase.set("Economy." + player.getName() + ".balance", 0);
	}
	
	public void giveBalance(Player giver, Player taker, int balance) {
		if (ecoBase.contains("Economy." + taker.getName())) {
			ecoBase.set("Economy." + taker.getName() + ".balance", getBalance(taker) +balance);
			ecoBase.set("Economy." + giver.getName() + ".balance", getBalance(giver)-balance);
			saveEcoConfig();
		}else {
			ecoBase.addDefault("Economy." + taker.getName() + ".balance", getBalance(taker) +balance);
			ecoBase.addDefault("Economy." + giver.getName() + ".balance", getBalance(giver)-balance);
			saveEcoConfig();
		}
		
	}
	
	public int getBalance(Player player) {
		return ecoBase.getInt("Economy." + player.getName() + ".balance");
	}
	
	public void loadConfig() {
		eco = new File(plugin.getDataFolder(), "Economy.yml");
		ecoBase = YamlConfiguration.loadConfiguration(eco);
		saveEcoConfig();
	}
	
	
    public void saveEcoConfig(){
   	try{
   		ecoBase.save(eco);
   	}catch(Exception e){
   	e.printStackTrace();
   	}
   	}

}
