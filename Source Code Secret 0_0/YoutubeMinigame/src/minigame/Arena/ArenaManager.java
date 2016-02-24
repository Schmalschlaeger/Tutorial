package minigame.Arena;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import minigame.Main;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class ArenaManager {
	
	private Map<UUID, ItemStack[]> inv = new HashMap<UUID, ItemStack[]>();
	private Map<UUID, ItemStack[]> armor = new HashMap<UUID, ItemStack[]>();
	private Map<UUID, Location> location = new HashMap<UUID, Location>();
    
	private int arenaID = 0;
	
	private List<Arena> arenas = new ArrayList<Arena>();
	
	private static ArenaManager am;
	
	static Main plugin;
	public ArenaManager(Main pl) {
		plugin = pl;
	}
	
	private ArenaManager() {}
	
	public static ArenaManager getManager() {
		if (am == null)
			am = new ArenaManager();
			
			return am;
	}
	
	/*
	 * Arenas:
	 *   Arenas:
	 *   - 1
	 *   - 2
	 *   - 3
	 *   - 4
	 *     
	 */
	
	public void addPlayer(Player p, int i) {
		Arena a = getArena(i);
		if (a == null) {
			p.sendMessage(ChatColor.RED + "Wrong arena ID");
			return;
		}
		
		a.getPlayers().add(p.getUniqueId());
		
		inv.put(p.getUniqueId(), p.getInventory().getContents());
		armor.put(p.getUniqueId(), p.getInventory().getArmorContents());
		location.put(p.getUniqueId(), p.getLocation());
		
		p.getInventory().setArmorContents(null);
		p.getInventory().clear();
		
		p.teleport(a.getSpawn());
	}
	
	public void removePlayer(Player p) {
		Arena a = null;
		
		for(Arena arena : arenas) {
			if (arena.getPlayers().contains(p.getUniqueId())) {
				a = arena;
			}
		}
		if (a == null) {
			p.sendMessage(ChatColor.RED + "There is something wrong!");
			return;
		}
		
		a.getPlayers().remove(p.getUniqueId());
		
		p.getInventory().clear();
		p.getInventory().setArmorContents(null);
		
		p.getInventory().setContents(inv.get(p.getUniqueId()));
		p.getInventory().setArmorContents(armor.get(p.getUniqueId()));
		
		p.setFireTicks(0);
		p.setHealth(20.0);
		p.setFoodLevel(20);
		
		inv.remove(p.getUniqueId());
		armor.remove(p.getUniqueId());
		
		p.teleport(location.get(p.getUniqueId()));
		location.remove(p.getUniqueId());
	}
	
	public Arena getArena(int id) {
		for(Arena a : arenas) {
			if (a.getId() == id) {
				return a;
			}
		}
		return null;
	}
	
	public Arena createArena(Location loc) {
		int num = arenaID + 1;
		arenaID++;
		
		Arena a = new Arena(loc, num);
		arenas.add(a);
		plugin.getConfig().set("Arenas." + num, serializeLocation(loc));
		
		List<Integer> list = plugin.getConfig().getIntegerList("Arenas.Arenas");
		list.add(num);
		plugin.getConfig().set("Arenas.Arenas", list);
		plugin.saveConfig();
		
		return a;
	}
	
	public void removeArena(int id) {
		Arena a = getArena(id);
		if (a == null) {
			return;
		}
		
		arenas.remove(a);
		
		plugin.getConfig().set("Arenas." + id, null);
		List<Integer> list = plugin.getConfig().getIntegerList("Arenas.Arenas");
		list.remove(id);
		plugin.getConfig().set("Arenas.Arenas", list);
		plugin.saveConfig();
	}
	
	public boolean isPlayerIngame(Player p) {
		for (Arena a : arenas) {
			if (a.getPlayers().contains(p.getUniqueId()))
				return true;
			}
			return false;
	}
	
	public Arena reloadArena(Location loc) {
		int num = arenaID + 1;
		arenaID++;
		
		Arena a = new Arena(loc, num);
		arenas.add(a);
		
		return a;
	}
	
	public void loadGames() {
		arenaID = 0;
		
		if (plugin.getConfig().getIntegerList("Arenas.Arenas").isEmpty()) {
			return;
		}
		
		for (int i : plugin.getConfig().getIntegerList("Arenas.Arenas")) {
			Arena a = reloadArena(deserializeLoc(plugin.getConfig().getString("Arenas." + i)));
			a.id = i;
		}
	}
	
	
	
	
	
	public Location deserializeLoc(String s){
        String[] st = s.split(",");
        return new Location(Bukkit.getWorld(st[0]), Integer.parseInt(st[1]), Integer.parseInt(st[2]), Integer.parseInt(st[3]));
    }
	
	public String serializeLocation(Location loc) {
		return loc.getWorld().getName()+","+loc.getBlockX()+","+loc.getBlockY()+","+loc.getBlockZ();
	}
}
