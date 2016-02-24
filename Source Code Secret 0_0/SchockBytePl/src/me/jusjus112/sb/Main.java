package me.jusjus112.sb;

import java.sql.Connection;
import java.sql.SQLException;

import me.jusjus112.sb.mysgl.MySQL;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {
	
    public static FileConfiguration config;
    private MySQL db;
    
    MySQL MySQL = new MySQL(this, "mc1chi.shockbyte.com", "3306", "server_342", "server_342", "d21817031d");
    Connection c = null;
	
	public void onEnable() {
		getCommand("register").setExecutor(new CommandListener());
		
		config = getConfig();
		
		config.options().copyDefaults(true);
		saveDefaultConfig();
		
		try {
			setupDB();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void onDisable() {
		try {
			closeDB();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void setupDB() throws SQLException, ClassNotFoundException {
	    this.db = new MySQL(this, "mc1chi.shockbyte.com", "3306", "server_342", "server_342", "d21817031d");
	    this.db.openConnection();
	    
	}
	
	public void closeDB() throws SQLException {
		this.db.closeConnection();
	}

}
