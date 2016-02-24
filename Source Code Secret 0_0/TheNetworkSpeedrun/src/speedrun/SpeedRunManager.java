package speedrun;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class SpeedRunManager{
	
    static Main plugin;
    
    public SpeedRunManager(Main m) {
        plugin = m;
    }

    private static SpeedRunManager am;
    
    public final List<Player> players = new ArrayList<Player>();
    
    public final List<Player> normal = new ArrayList<Player>();
    public final List<Player> nether = new ArrayList<Player>();
    public final List<Player> jungle = new ArrayList<Player>();
    public final List<Player> speedy = new ArrayList<Player>();

    private final List<Arena> arenas = new ArrayList<Arena>();
    private int arenaSize = 0;
 
    private SpeedRunManager() {}
 
    public static SpeedRunManager getManager() {
        if (am == null)
            am = new SpeedRunManager();
 
        return am;
    }

    public Arena getArena(int i){ 
        for (Arena a : this.arenas) {
            if (a.getId() == i) {
                return a;
                
            }
        }

        return null;
    }
    
    

    @SuppressWarnings("deprecation")
	public void addPlayer(Player p, Location loc) {
		ItemStack clock = new ItemStack(Material.WATCH);
		ItemMeta head1 = clock.getItemMeta();
		head1.setDisplayName(ChatColor.GOLD + "Player Visibility! " + ChatColor.GRAY + "(Right click to toggle)");
		clock.setItemMeta(head1);
		
		ItemStack clock1 = new ItemStack(Material.FEATHER);
		ItemMeta head11 = clock1.getItemMeta();
		head11.setDisplayName(ChatColor.GOLD + "Back to begin! " + ChatColor.GRAY + "(Right click to tp)");
		clock1.setItemMeta(head11);
    	
        if (isInGame(p)) {
            p.sendMessage("Cannot join more than 1 game!");
            return;
        } 

        players.add(p);
        
        p.getInventory().clear();
        
        p.teleport(loc);
        p.setGameMode(GameMode.ADVENTURE);
        p.setWalkSpeed((float) 0.3);
        
        p.sendMessage(ChatColor.GOLD + "Welcome to speedrun " + p.getName() + "! Let's see your parkour skills!");
        
        	p.getInventory().contains(clock1);
        	p.getInventory().setItem(8, clock1);
        	p.updateInventory();
        
        p.getInventory().contains(clock);
        p.getInventory().addItem(clock);
        p.updateInventory();
    }
 
    @SuppressWarnings("deprecation")
	public void removePlayerWhenDefect(Player p) {
    	
    	ItemStack clock = new ItemStack(Material.WATCH);
		ItemMeta head1 = clock.getItemMeta();
		head1.setDisplayName(ChatColor.GOLD + "Player Visibility! " + ChatColor.GRAY + "(Right click to toggle)");
		clock.setItemMeta(head1);
    	
        players.remove(p);

        p.setWalkSpeed((float) 0.2);
        p.setFireTicks(0);
        
        p.teleport(p.getWorld().getSpawnLocation());
        
        p.getInventory().clear();
        p.updateInventory();
      
        p.sendMessage(ChatColor.RED + "" + ChatColor.BOLD + "Sorry, but this map is defect! We have send you to the lobby! Sorry for the inconvenience");
    }
    
	@SuppressWarnings("deprecation")
	public void removePlayer(Player p) {    	
        players.remove(p);

        p.setWalkSpeed((float) 0.2);
        p.setFireTicks(0);
        
        p.teleport(p.getWorld().getSpawnLocation());
        
        p.getInventory().clear();
        p.updateInventory();

        removePlayerFromAllMaps(p);
    }
	
	public void removePlayerFromAllMaps(Player p) {
		SpeedRunManager.getManager().normal.remove(p);
		SpeedRunManager.getManager().nether.remove(p);
		SpeedRunManager.getManager().jungle.remove(p);
		SpeedRunManager.getManager().speedy.remove(p);
	}
    
    public void teleportToBeginPoint(Location loc, Player p) {
    	p.teleport(loc);
    }

    public Arena createArena(Location l) {        
        this.arenaSize++;
 
        Arena a = new Arena(l, this.arenaSize);
        this.arenas.add(a);
 
        return a;
    }
 
    /**
     * Checks if the player is currently in an arena
     *
     * @param p the player to check
     * @return {@code true} if the player is in game
     */
    public boolean isInGame(Player p) {
        for (Arena a : this.arenas) {
            if (a.getPlayers().contains(p.getUniqueId()))
                return true;
        }
        return false;
    }
 
    public String serializeLoc(Location l){
        return l.getWorld().getName() + "," + l.getBlockX() + "," + l.getBlockY() + "," + l.getBlockZ();
    }

    public Location deserializeLoc(String s){
        String[] st = s.split(",");
        return new Location(Bukkit.getWorld(st[0]), Integer.parseInt(st[1]), Integer.parseInt(st[2]), Integer.parseInt(st[3]));
    }
}