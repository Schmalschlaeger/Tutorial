package simple.essentials.Utils;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public class KickAndBanMenu {
	
	@SuppressWarnings("deprecation")
	public static void createKickMenu(Player p, Inventory inv) {
		createItems.createCustomItemLores(p, inv, Material.FEATHER, (byte) 0, ChatColor.GOLD + "" + ChatColor.BOLD + "FlyHacks (Hacked Client)", 1,
				ChatColor.GRAY + "Kick " + p.getName() + " for flyhacks!", ChatColor.GREEN + "Flyhacks, flying no perms, water/lava walk");
		
		createItems.createCustomItemLores(p, inv, Material.DIAMOND_SWORD, (byte) 0, ChatColor.GOLD + "" + ChatColor.BOLD + "KillAura (Hacked Client)", 3,
				ChatColor.GRAY + "Kick " + p.getName() + " for killaura!", ChatColor.GREEN + "Forefield, kills 7 block range");
		
		createItems.createCustomItemLores(p, inv, Material.DIAMOND_ORE, (byte) 0, ChatColor.GOLD + "" + ChatColor.BOLD + "X-Ray (Hack Mod)", 11,
				ChatColor.GRAY + "Kick " + p.getName() + " for X-Ray!", ChatColor.GREEN + "X-Ray, tracers, chest finder");
		
		createItems.createCustomItemLores(p, inv, Material.GOLDEN_APPLE, (byte) 0, ChatColor.GOLD + "" + ChatColor.BOLD + "Banned Mods", 15,
				ChatColor.GRAY + "Kick " + p.getName() + " for Banned Mods!", ChatColor.GREEN + "X-Ray, Auto-Shift, other hack mods");
		
		createItems.createCustomItemLores(p, inv, Material.REDSTONE, (byte) 0, ChatColor.GOLD + "" + ChatColor.BOLD + "Hacked Client", 20,
				ChatColor.GRAY + "Kick " + p.getName() + " for flyhacks!", ChatColor.GREEN + "Hacks, hacked client");
		
		createItems.createCustomItemLores(p, inv, Material.getMaterial(383), (byte) 50, ChatColor.GOLD + "" + ChatColor.BOLD + "Teaming", 25,
				ChatColor.GRAY + "Kick " + p.getName() + " for teaming!", ChatColor.GREEN + "Teaming with friends");
		
	}
	
	@SuppressWarnings("deprecation")
	public static void createBanMenu(Player p, Inventory inv) {
		createItems.createCustomItem(p, inv, Material.getMaterial(160), (byte) 4, "[]", 4);
		createItems.createCustomItem(p, inv, Material.getMaterial(160), (byte) 4, "[]", 13);
		createItems.createCustomItem(p, inv, Material.getMaterial(160), (byte) 4, "[]", 22);
		createItems.createCustomItem(p, inv, Material.getMaterial(160), (byte) 4, "[]", 31);
		
		createItems.createCustomItemLores3(p, inv, Material.DIAMOND_SWORD, (byte) 0, ChatColor.GOLD + "" + ChatColor.BOLD + "KillAura (Hacked Client)", 23,
				ChatColor.GRAY + "Ban " + p.getName() + " for KillAura!", ChatColor.GREEN + "Forefield, Kill Assist, 7 Range hits", 
				ChatColor.GRAY + "Time ban: " + ChatColor.DARK_AQUA + "Permanent");
		
		createItems.createCustomItemLores3(p, inv, Material.FEATHER, (byte) 0, ChatColor.GOLD + "" + ChatColor.BOLD + "FlyHacks (Hacked Client)", 1,
				ChatColor.GRAY + "Ban " + p.getName() + " for FlyHacks!", ChatColor.GREEN + "Flyhacks, flying no perms, water/lava walk", 
				ChatColor.GRAY + "Time ban: " + ChatColor.DARK_AQUA + "6 Months");
		
		
		createItems.createCustomItemLores3(p, inv, Material.DIAMOND_ORE, (byte) 0, ChatColor.GOLD + "" + ChatColor.BOLD + "X-Ray (Hack Mod)", 15,
				ChatColor.GRAY + "Ban " + p.getName() + " for X-Ray!", ChatColor.GREEN + "X-Ray, tracers, chest finder", 
				ChatColor.GRAY + "Time ban: " + ChatColor.DARK_AQUA + "Permanent");
		
		
		createItems.createCustomItemLores3(p, inv, Material.getMaterial(322), (byte) 1, ChatColor.GOLD + "" + ChatColor.BOLD + "Banned Mods", 10,
				ChatColor.GRAY + "Ban " + p.getName() + " for Banned Mods!", ChatColor.GREEN + "Minimap, Auto-Shift, other hack mods", 
				ChatColor.GRAY + "Time ban: " + ChatColor.DARK_AQUA + "4 Months");
		
		
		createItems.createCustomItemLores3(p, inv, Material.REDSTONE, (byte) 0, ChatColor.GOLD + "" + ChatColor.BOLD + "Hacked Client", 25,
				ChatColor.GRAY + "Ban " + p.getName() + " for Hack Client!", ChatColor.GREEN + "Kill Assist, Step, NoRender, NameTags", 
				ChatColor.GRAY + "Time ban: " + ChatColor.DARK_AQUA + "Permanent");
		
		createItems.createCustomItemLores3(p, inv, Material.getMaterial(383), (byte) 50, ChatColor.GOLD + "" + ChatColor.BOLD + "Teaming", 20,
				ChatColor.GRAY + "Ban " + p.getName() + " for Teaming!", ChatColor.GREEN + "Teaming with friends", 
				ChatColor.GRAY + "Time ban: " + ChatColor.DARK_AQUA + "1 Month");
	}

}
