package coins.jusjus;

import java.sql.SQLException;
import java.sql.Statement;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Commands implements CommandExecutor {
	
	private Main plugin;
	 
	public Commands(Main instance) {
	    this.plugin = instance;
	}
	
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args){
		Player p = (Player) sender;
		String name = p.getName();
		
		if (!(sender instanceof Player)) {
			sender.sendMessage("You can use this command only ingame!");
			return true;
		}else {
		if (cmd.getName().equalsIgnoreCase("test")) {
			try {
				Statement statement = plugin.c.createStatement();
				statement.executeUpdate("UPDATE Coins SET Coins=" + 2 + " WHERE PlayerName='" + name + "';");
				System.out.println("Row Updated!");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		}
		return false;		
	}
	
}
