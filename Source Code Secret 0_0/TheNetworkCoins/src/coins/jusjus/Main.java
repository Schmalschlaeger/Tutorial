package coins.jusjus;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import coins.jusjus.mysql.MySQL;

public class Main extends JavaPlugin{
	
	MySQL MySQL = new MySQL(this, "db.shockbyte.com", "3306", "db_414", "db_414", "17479657ef");
	Connection c = null;
	Statement statement;
	
	public void onEnable() {
		try {
			c = MySQL.openConnection();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			statement = c.createStatement();
			statement.executeUpdate("CREATE TABLE IF NOT EXISTS Coins (PlayerName VARCHAR(20), Coins INT(20));");
			System.out.println("Table created");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		getCommand("test").setExecutor(new Commands(this));
		Bukkit.getServer().getPluginManager().registerEvents(new Events(this), this);
	}

}
