package Hotel;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.SignChangeEvent;

public class SignListener implements Listener {
	
	private String line1 = "§8[§bGunShop§8]";
	
	private int room1 = 0;
	private int room2 = 0;
	private int room3 = 0;
	private int room4 = 0;
	private int room5 = 0;
	private int room6 = 0;
	private int room7 = 0;
	private int room8 = 0;
	private int room9 = 0;
	private int room10 = 0;
	
	@EventHandler
	public void onSignChange(SignChangeEvent e) {
		if (e.getLine(0).equalsIgnoreCase("[hotel]")) {
			if (e.getLine(1).equalsIgnoreCase("1")) {
				e.setLine(0, line1);
		        e.setLine(1, "§oBuy Room");
		        e.setLine(2, "§o" + e.getLine(1));
		        e.setLine(3, room1 + " §oPoints");
			}else if (e.getLine(1).equalsIgnoreCase("2")) {
				e.setLine(0, line1);
		        e.setLine(1, "§oBuy Room");
		        e.setLine(2, "§o" + e.getLine(1));
		        e.setLine(3, room2 + " §oPoints");
			}else if (e.getLine(1).equalsIgnoreCase("3")) {
				e.setLine(0, line1);
		        e.setLine(1, "§oBuy Room");
		        e.setLine(2, "§o" + e.getLine(1));
		        e.setLine(3, room3 + " §oPoints");
			}else if (e.getLine(1).equalsIgnoreCase("4")) {
				e.setLine(0, line1);
		        e.setLine(1, "§oBuy Room");
		        e.setLine(2, "§o" + e.getLine(1));
		        e.setLine(3, room4 + " §oPoints");
			}else if (e.getLine(1).equalsIgnoreCase("5")) {
				e.setLine(0, line1);
		        e.setLine(1, "§oBuy Room");
		        e.setLine(2, "§o" + e.getLine(1));
		        e.setLine(3, room5 + " §oPoints");
			}else if (e.getLine(1).equalsIgnoreCase("6")) {
				e.setLine(0, line1);
		        e.setLine(1, "§oBuy Room");
		        e.setLine(2, "§o" + e.getLine(1));
		        e.setLine(3, room6 + " §oPoints");
			}else if (e.getLine(1).equalsIgnoreCase("7")) {
				e.setLine(0, line1);
		        e.setLine(1, "§oBuy Room");
		        e.setLine(2, "§o" + e.getLine(1));
		        e.setLine(3, room7 + " §oPoints");
			}else if (e.getLine(1).equalsIgnoreCase("8")) {
				e.setLine(0, line1);
		        e.setLine(1, "§oBuy Room");
		        e.setLine(2, "§o" + e.getLine(1));
		        e.setLine(3, room8 + " §oPoints");
			}else if (e.getLine(1).equalsIgnoreCase("9")) {
				e.setLine(0, line1);
		        e.setLine(1, "§oBuy Room");
		        e.setLine(2, "§o" + e.getLine(1));
		        e.setLine(3, room9 + " §oPoints");
			}else if (e.getLine(1).equalsIgnoreCase("10")) {
				e.setLine(0, line1);
		        e.setLine(1, "§oBuy Room");
		        e.setLine(2, "§o" + e.getLine(1));
		        e.setLine(3, room10 + " §oPoints");
			}else {
				e.getBlock().breakNaturally();
				e.getPlayer().sendMessage("Sorry, but there are only 10 rooms to create!");
			}
		}
	}
	
}
