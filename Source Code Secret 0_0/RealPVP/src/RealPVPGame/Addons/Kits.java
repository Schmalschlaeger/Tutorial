package RealPVPGame.Addons;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public enum Kits {
	
		DEFAULT,
		PREMIUM;
		
		public static void removeKits(Player p) {
			if (KitSelector.kitPremium.contains(p.getName())) {
				KitSelector.kitPremium.remove(p.getName());
			}else if (KitSelector.kitDefault.contains(p.getName())) {
				KitSelector.kitDefault.remove(p.getName());
			}
		}
	
	public static void setKit(Player p, Kits kit) {
		switch(kit) {
		case DEFAULT:
			if (!KitSelector.kitDefault.contains(p.getName())) {
				removeKits(p);
				KitSelector.kitDefault.add(p.getName());
				p.sendMessage(ChatColor.GRAY + "You have chosen for kit default!");
				}else {
					p.sendMessage(ChatColor.GRAY + "You have already chosen for this kit!");
				}
			break;
		case PREMIUM:
			if (!KitSelector.kitPremium.contains(p.getName())) {
				removeKits(p);
				KitSelector.kitPremium.add(p.getName());
				p.sendMessage(ChatColor.GRAY + "You have chosen for kit Premium!");
				}else {
					p.sendMessage(ChatColor.GRAY + "You have already chosen for this kit!");
				}
			break;
		}
	}

}
