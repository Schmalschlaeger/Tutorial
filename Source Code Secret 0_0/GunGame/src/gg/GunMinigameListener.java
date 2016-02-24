package gg;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;

public class GunMinigameListener {
	
	GameState g;
	public int totaalPlayers = 0;
	public int maxPlayers = 12;
	public int minPlayers = 2;
	public boolean timerIsBusy = false; 
	public int levelScheduler;
	public static Scoreboard scoreboard;
	public static Objective objective;
	public int lobbyCountDownCount = 0;
	public int sb;
	
	public int scheduler = 0;
    
	public int id;
    
    public void setId2(int id) {
        this.id = id;
    }
    
	public int checkPlayers;
    
    public void setId3(int id) {
        this.checkPlayers = id;
    }
    
	public int timerInLobby;
    
	public boolean isStartedCountDown = false;
	
	public enum GameState {
		STARTING, INGAME, LOBBY
	}
	
	public void setState(GameState gs) {
	    g = gs;
	}
	 
	public GameState getState() {
	    return g;
	}
	 
    public Map<String, Location> locs = new HashMap<String, Location>();
    public boolean isIngame = false;

    public static GunMinigameListener am = new GunMinigameListener();

    Map<String, ItemStack[]> inv = new HashMap<String, ItemStack[]>();
    Map<String, ItemStack[]> armor = new HashMap<String, ItemStack[]>();

    List<Arena> arenas = new ArrayList<Arena>();
    int arenaSize = 0;

    static Main plugin;
    public GunMinigameListener(Main arenaPVP) {
        plugin = arenaPVP;
    }

    public GunMinigameListener(){

    }


    public static GunMinigameListener getManager(){
        return am;
    }


    public Arena getArena(int i){
        for(Arena a : arenas){
            if(a.getId() == i){
                return a;
            }
        }
        return null;
    }
    
	public static void setupScoreboardLobby(Player player) {
		scoreboard = Bukkit.getScoreboardManager().getNewScoreboard();

		objective = scoreboard.registerNewObjective("lobby", "dummy");
		objective.setDisplaySlot(DisplaySlot.SIDEBAR);
		objective.setDisplayName(ChatColor.GOLD + "" + ChatColor.BOLD + "Magica GunGame");
	}
	
	private void updateLobbyScoreboard(Arena a) {
        for(String s : a.getPlayers()) {
            Player arenaPl = Bukkit.getServer().getPlayer(s);
            if(arenaPl != null) {
			if (arenaPl.getScoreboard().getObjective("lobby") == null) {
				//chatManager.debugMessage("scoreboardLobby null for " + players.getDisplayName());
				setupScoreboardLobby(arenaPl);
				arenaPl.setScoreboard(scoreboard);
			}
			if (arenaPl.getScoreboard().getObjective("lobby") != null){
				scoreboard.resetScores(Bukkit.getOfflinePlayer(ChatColor.GREEN + Integer.toString(lobbyCountDownCount + 2)));
				scoreboard.resetScores(Bukkit.getOfflinePlayer(ChatColor.GREEN + Integer.toString(lobbyCountDownCount)));
				//chatManager.debugMessage("scoreboardLobby not(?) null for " + players.getDisplayName());
				arenaPl.setScoreboard(scoreboard);//Get scoreboardLobby
				scoreboard.getObjective(DisplaySlot.SIDEBAR).getScore(Bukkit.getOfflinePlayer(ChatColor.WHITE + "" + 
						ChatColor.BOLD + "Time Left:")).setScore(15);
				scoreboard.getObjective(DisplaySlot.SIDEBAR).getScore(Bukkit.getOfflinePlayer(ChatColor.GREEN + 
						Integer.toString(lobbyCountDownCount))).setScore(14);
				scoreboard.getObjective(DisplaySlot.SIDEBAR).getScore(Bukkit.getOfflinePlayer(ChatColor.BOLD + 
						"  ")).setScore(13);
				scoreboard.getObjective(DisplaySlot.SIDEBAR).getScore(Bukkit.getOfflinePlayer(ChatColor.WHITE + "" + 
						ChatColor.BOLD + "In Lobby:")).setScore(12);
				scoreboard.getObjective(DisplaySlot.SIDEBAR).getScore(Bukkit.getOfflinePlayer(ChatColor.GREEN + 
						Integer.toString(a.getPlayers().size()))).setScore(11);
				scoreboard.getObjective(DisplaySlot.SIDEBAR).getScore(Bukkit.getOfflinePlayer(ChatColor.BOLD + 
						"  ")).setScore(10);
				scoreboard.getObjective(DisplaySlot.SIDEBAR).getScore(Bukkit.getOfflinePlayer(ChatColor.WHITE + "" + 
						ChatColor.BOLD + "Min Players:")).setScore(9);
				scoreboard.getObjective(DisplaySlot.SIDEBAR).getScore(Bukkit.getOfflinePlayer(ChatColor.GREEN + 
						Integer.toString(getMinPlayers()))).setScore(8);
				scoreboard.getObjective(DisplaySlot.SIDEBAR).getScore(Bukkit.getOfflinePlayer(ChatColor.BOLD + 
						"   ")).setScore(7);
				scoreboard.getObjective(DisplaySlot.SIDEBAR).getScore(Bukkit.getOfflinePlayer(ChatColor.WHITE + "" +
						ChatColor.BOLD + "Max Players:")).setScore(6);
				scoreboard.getObjective(DisplaySlot.SIDEBAR).getScore(Bukkit.getOfflinePlayer(ChatColor.GREEN + 
						Integer.toString(getMaxPlayers()))).setScore(5);
			}
			}
		}
	}

    @SuppressWarnings("deprecation")
	public void addPlayer(Player p, int i){
		ItemStack chest2 = new ItemStack(Material.EMERALD);
		ItemMeta plate2 = chest2.getItemMeta();
		plate2.setDisplayName(ChatColor.GOLD + "" + ChatColor.BOLD + "Your gun's");
		ArrayList<String> iron2 = new ArrayList<String>();
		iron2.add(ChatColor.GRAY + "");
		iron2.add(ChatColor.GRAY + "Open the menu to see your gun's!");
		iron2.add(ChatColor.GRAY + "You can only use this item in minigames!");
		plate2.setLore(iron2);
		chest2.setItemMeta(plate2);
    	
        final Arena a = getArena(i);
        if(a == null){
        	p.sendMessage(ChatColor.RED + "Error Type: Invalid Arena! What are you doing?");
            return;
        }       
        if (!isInGame(p)) {

        a.getPlayers().add(p.getName());
        locs.put(p.getName(), p.getLocation());
        p.teleport(a.spawn);
        inv.put(p.getName(), p.getInventory().getContents());
        armor.put(p.getName(), p.getInventory().getArmorContents());
        totaalPlayers = totaalPlayers+1;
        
        p.setGameMode(GameMode.ADVENTURE);
        p.getInventory().setArmorContents(null);
        p.getInventory().clear();
        p.updateInventory();
        p.sendMessage(ChatColor.GOLD + "Joined arena 1");
        
        p.getInventory().setItem(0, chest2);
        p.updateInventory();
        
        if (a.getPlayers().size() == 1) {
        sb = Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(plugin, new Runnable() {
        	@Override
        	public void run() {
        		updateLobbyScoreboard(a);
        	}
        }, 0, 20L);
        }
        
        
        for(String s : a.getPlayers()) {
            Player arenaPl = Bukkit.getServer().getPlayer(s);
            if(arenaPl != null) {
            	arenaPl.sendMessage(ChatColor.GOLD + "+ " + ChatColor.RED + p.getName() + ChatColor.GRAY + " has joined the arena!");
            	if (getTotaalPlayers() == getMinPlayers()) {
            		//startRandomLobbyTimer(arenaPl);
            		//isStartedCountDown = true;
            		startScheduler(arenaPl);
            		
            	}
            }
        }
        
        }else {
        	p.sendMessage(ChatColor.RED + "Sorry but you have already joined the gunGame, if this is an error. Rejoin the server, and you can player again!");
        }
    }
    
    public void startScheduler(final Player arenaPl) {
    	scheduler = 20;
        timerIsBusy = true;
    	levelScheduler = Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(plugin, new Runnable() {
    				@Override
    				public void run() {
    					if (timerIsBusy = true) {
    						scheduler--;
    						arenaPl.sendMessage("TIMER ! IT WORKS");
    						arenaPl.setLevel(scheduler);
    					}else
    					if (getTotaalPlayers() == 0 || scheduler == 0) {
    						Bukkit.getServer().getScheduler().cancelTask(levelScheduler);
    						timerIsBusy = false;
    						arenaPl.sendMessage("TIMER STOPPED! IT WORKS");
    					}
    				}
    	}, 20L, 20L);
    }

	
    //int cd = 30;
    //public void startRandomLobbyTimer(final Player arenaPl) {
    //	final BukkitRunnable task = new BukkitRunnable() {
	//		@Override
	//		public void run() {
	//			if (isStartedCountDown = true) {
	//			cd--;
	//			arenaPl.setLevel(cd);
	//			
	//			if (cd < 0) {	
	//				this.cancel();
	///				cd = 30;
	//			}
	//			}
	//		}
    //	};
    //	task.runTaskTimer(plugin, 0L, 20L);
   // }

    public void removePlayer(Player p){			
        Arena a = null;
        for(Arena arena : arenas){
            if(arena.getPlayers().contains(p.getName())){
                a = arena;
            }

        }
        if(a == null || !a.getPlayers().contains(p.getName())){
            p.sendMessage(ChatColor.RED + "Error detected! What are you doing?");
            return;
        }
        
        p.getInventory().clear();
        p.getInventory().setArmorContents(null);

        a.getPlayers().remove(p.getName());
        p.teleport(locs.get(p.getName()));
        locs.remove(p.getName());
        p.getInventory().setContents(inv.get(p.getName()));
        p.getInventory().setArmorContents(armor.get(p.getName()));
        inv.remove(p.getName());
        armor.remove(p.getName());
        
        if (a.getPlayers().size() == 0) {
        	Bukkit.getServer().getScheduler().cancelTask(sb);
            scoreboard.clearSlot(DisplaySlot.SIDEBAR);
            
        }
        
        totaalPlayers = totaalPlayers-1;
        p.setFireTicks(0);
        p.setLevel(0);
        p.sendMessage(ChatColor.GOLD + "Succesfull removed you from the arena!");
        for(String s : a.getPlayers()) {
            Player arenaPl = Bukkit.getServer().getPlayer(s);
            if(arenaPl != null) {
            	arenaPl.sendMessage(ChatColor.GOLD + "- " + ChatColor.RED + p.getName() + ChatColor.GRAY + " has leaved the arena!");        
        
        if (p.isOp()) p.setGameMode(GameMode.CREATIVE);
        if (!p.isOp()) p.setGameMode(GameMode.ADVENTURE);
        if (getTotaalPlayers() < getMinPlayers()) {
        	isStartedCountDown = false;
        	arenaPl.sendMessage(ChatColor.GOLD + "Not enough player to start the game! There must be at least " + getMinPlayers() + " player's");
        }
            }
        }
    }


    public Arena createArena(Location l){
        int num = arenaSize + 1;
        arenaSize++;

        Arena a = new Arena(l, num);
        arenas.add(a);

        plugin.getConfig().set("Arenas." + num, serializeLoc(l));
        List<Integer> list = plugin.getConfig().getIntegerList("Arenas.Arenas");
        list.add(num);
        plugin.getConfig().set("Arenas.Arenas.Totaal", list);
        plugin.saveConfig();

        return a;
    }

    public Arena reloadArena(Location l) {
        int num = arenaSize + 1;
        arenaSize++;
 
        Arena a = new Arena(l, num);
        arenas.add(a);
 
        return a;
    }
    
    public void removeArena(int i) {
        Arena a = getArena(i);
        if(a == null) {
            return;
        }
        arenas.remove(a);

        plugin.getConfig().set("Arenas." + i, null);
        List<Integer> list = plugin.getConfig().getIntegerList("Arenas.Arenas");
        list.remove(i);
        plugin.getConfig().set("Arenas.Arenas", list);
        plugin.saveConfig();    
    }

    public boolean isInGame(Player p){
        for(Arena a : arenas){
            if(a.getPlayers().contains(p.getName()))
                return true;
        }
        return false;
    }

    public void loadGames(){
        arenaSize = 0;      

        if(plugin.getConfig().getIntegerList("Arenas.Arenas.Totaal").isEmpty()){
            return;
        }
                
        for(int i : plugin.getConfig().getIntegerList("Arenas.Arenas.Totaal")){
            Arena a = reloadArena(deserializeLoc(plugin.getConfig().getString("Arenas." + i)));
            a.id = i;
        }
    }
     
    public int getMaxPlayers() {
    	return maxPlayers;
    }
    
    public int getTotaalPlayers() {
    	return totaalPlayers;
    }
    
    public int getMinPlayers() {
    	return minPlayers;
    }

    public String serializeLoc(Location l){
        return l.getWorld().getName()+","+l.getBlockX()+","+l.getBlockY()+","+l.getBlockZ();
    }
    public Location deserializeLoc(String s){
        String[] st = s.split(",");
        return new Location(Bukkit.getWorld(st[0]), Integer.parseInt(st[1]), Integer.parseInt(st[2]), Integer.parseInt(st[3]));
    }
}
