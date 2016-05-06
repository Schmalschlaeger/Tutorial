package cock.topia.tvg.Utils;

import org.bukkit.entity.Player;

import cock.topia.tvg.Main;
import cock.topia.tvg.Inventory.Scoreboard;

public class MoneyManager {
	
	Main plugin;
	public MoneyManager(Main plugin) {
		this.plugin = plugin;
	}
	
	  public static int doubleToInt(Double d) {
		    return d.intValue();
		  }
	  
	  public int getMoney(Player p) {
	    if (Main.econ != null) {
	      int m = doubleToInt(Double.valueOf(Main.econ.getBalance(p.getName())));
	      return m;
	    }
	    return 0;
	  }

	  public void addMoney(Player p, int amount) {
		  Scoreboard sb = new Scoreboard(plugin);
		 Main.econ.depositPlayer(p.getName(), amount);
		 sb.scoreboard(p);
	  }
	  public void removeMoney(Player p, int amount) {
		Scoreboard sb = new Scoreboard(plugin);
		Main.econ.withdrawPlayer(p.getName(), amount);
	    sb.scoreboard(p);
	  }
}
