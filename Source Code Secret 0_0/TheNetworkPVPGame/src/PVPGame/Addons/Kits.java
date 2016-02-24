package PVPGame.Addons;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import PVPGame.Listeners.KitSelector;

public enum Kits {
	
	//TODO:
	//Kit idea's:
	// - Stunner: Stun a player by hitting a arrow (1 arrow) When hit get 1 arrow back) Custom arrows
	
		DEFAULT,
		VIP,
		HYDRA,
		CHAMPION;
		//TODO: Add more kits
		
		public static void removeKits(Player p) {
			if (KitSelector.kitChampion.contains(p.getName())) {
				KitSelector.kitChampion.remove(p.getName());
			}else if (KitSelector.kitDefault.contains(p.getName())) {
				KitSelector.kitDefault.remove(p.getName());
			}else if (KitSelector.kitHydra.contains(p.getName())) {
				KitSelector.kitHydra.remove(p.getName());
			}else if (KitSelector.kitVIP.contains(p.getName())) {
				KitSelector.kitVIP.remove(p.getName());
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
		case CHAMPION:
			if (!KitSelector.kitChampion.contains(p.getName())) {
				removeKits(p);
				KitSelector.kitChampion.add(p.getName());
				p.sendMessage(ChatColor.GRAY + "You have chosen for kit champion!");
				}else {
					p.sendMessage(ChatColor.GRAY + "You have already chosen for this kit!");
				}
			break;
		case HYDRA:
			if (!KitSelector.kitHydra.contains(p.getName())) {
				removeKits(p);
				KitSelector.kitHydra.add(p.getName());
				p.sendMessage(ChatColor.GRAY + "You have chosen for kit hydra!");
				}else {
					p.sendMessage(ChatColor.GRAY + "You have already chosen for this kit!");
				}
			break;
		case VIP:
			if (!KitSelector.kitVIP.contains(p.getName())) {
				removeKits(p);
				KitSelector.kitVIP.add(p.getName());
				p.sendMessage(ChatColor.GRAY + "You have chosen for kit VIP!");
				}else {
					p.sendMessage(ChatColor.GRAY + "You have already chosen for this kit!");
				}
			break;
		}
	}

}
