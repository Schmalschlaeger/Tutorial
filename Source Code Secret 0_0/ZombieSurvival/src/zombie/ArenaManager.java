package zombie;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import zombie.MessageManager.MsgType;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ArenaManager{
	
	 static Main plugin;
	    public ArenaManager(Main arenaPVP) {
	        plugin = arenaPVP;
	    }
	
	API h = new API(plugin);

    //save where the player teleported
    public Map<String, Location> locs = new HashMap<String, Location>();
    //make a new instance of the class
    public static ArenaManager am = new ArenaManager();
    private int timer = 60*5;
    //a few other fields
    Map<String, ItemStack[]> inv = new HashMap<String, ItemStack[]>();
    Map<String, ItemStack[]> armor = new HashMap<String, ItemStack[]>();
    private ArrayList<String> players = new ArrayList<String>();
    //list of arenas
    List<Arena> arenas = new ArrayList<Arena>();
    int arenaSize = 0;

    public ArenaManager(){

    }

    //we want to get an instance of the manager to work with it statically
    public static ArenaManager getManager(){
        return am;
    }

    //get an Arena object from the list
    public Arena getArena(int i){
        for(Arena a : arenas){
            if(a.getId() == i){
                return a;
            }
        }
        return null;
    }

    //add players to the arena, save their inventory
    public void addPlayer(Player p, int i){
        Arena a = getArena(i);//get the arena you want to join
        if(a == null){//make sure it is not null
            p.sendMessage(MsgType.ERROR + "Invalid arena!");
            return;
        }

        a.getPlayers().add(p.getName());//add them to the arena list of players
        inv.put(p.getName(), p.getInventory().getContents());//save inventory
        armor.put(p.getName(), p.getInventory().getArmorContents());

        p.getInventory().setArmorContents(null);
        p.getInventory().clear();
        players.add(p.getName());

        locs.put(p.getName(), p.getLocation());
        p.setGameMode(GameMode.SURVIVAL);
        p.setFlying(false);
        p.setHealth(10.0);
        p.setFireTicks(0);
    	p.setExp(1);
        p.teleport(a.spawn);
        p.sendMessage(MsgType.NORMAL + "Succesfull joined the arena");
        sendArenaMessage(MsgType.NORMAL + p.getName() + ChatColor.GRAY + " has joined the game! " + ChatColor.RED + a.getPlayers().size() + "/20");
        if (a.getPlayers().size() == 2) {
			a.getPlayers()..sendMessage(MsgType.NORMAL + "CountDown has started because there are " + players.size() + " players online!");
	    	Bukkit.getPlayer(s).setLevel(240);
	    	xp = Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(plugin, new Runnable() {
	    		@Override
	    		public void run() {
	    			if (Bukkit.getPlayer(s).getLevel() != 0) {
	    				Bukkit.getPlayer(s).setLevel(Bukkit.getPlayer(s).getLevel() -1);
	    			}else {
	    				Bukkit.getServer().getScheduler().cancelTask(xp);
	    			}
	    		}
	    	}, 0L, 20L);
	    }
	    }
    }
}
    
    public int xp;
    
    public void setId(int xp) {
        this.xp = xp;
    }
    
    public void startTimer() {
           sendArenaMessage(MsgType.NORMAL + "Starting the countdown for 5 minuts!");
    	new Runnable() {
    		public int id = Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(plugin, this, 0L, 20L);
    		@Override
    		public void run() {
    			if (timer > 0) {
    				timer--;
    				switch (timer) {
    				case 60*4:
    					sendArenaMessage(MsgType.NORMAL + "There are 4 minuts remaining");
    					break;
    				case 60*3:
    					sendArenaMessage(MsgType.NORMAL + "There are 3 minuts remaining");
    					break;
    				case 60*2:
    					sendArenaMessage(MsgType.NORMAL + "There are 2 minuts remaining");
    					break;
    				case 60:
    					sendArenaMessage(MsgType.NORMAL + "There are 1 minut remaining");
    					break;
    				case 30:
    					sendArenaMessage(MsgType.NORMAL + "There are 30 seconds remaining");
    					break;
    				case 10:
    					sendArenaMessage(MsgType.NORMAL + "There are 10 seconds remaining");
    					break;
    				case 5:
    					sendArenaMessage(MsgType.NORMAL + "There are 5 seconds remaining");
    					break;
    				case 4:
    					sendArenaMessage(MsgType.NORMAL + "There are 4 seconds remaining");
    					break;
    				case 3:
    					sendArenaMessage(MsgType.NORMAL + "There are 3 seconds remaining");
    					break;
    				case 2:
    					sendArenaMessage(MsgType.NORMAL + "There are 2 seconds remaining");
    					break;
    				case 1:
    					sendArenaMessage(MsgType.NORMAL + "There are 1 second remaining");
    					break;   					
    				}
    			}else {
    				if (players.size() != 2) {
    					Bukkit.getServer().getScheduler().cancelTask(id);
    					sendArenaMessage(MsgType.NORMAL + "There are not enough players online to start the game!");
    					sendArenaMessage(MsgType.NORMAL + "There are " + ChatColor.RED + players.size() + ChatColor.LIGHT_PURPLE + " players in the arena!");
    					Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
    						@Override
    						public void run() {
    							startTimer();
    							
    						}
    					}, 40);
    					
    				}else {
        				Bukkit.getServer().getScheduler().cancelTask(id);
        				sendArenaMessage(MsgType.NORMAL + "GAME STARTED!!!");
    				}
    			}
    		}
    	};
    }

    //remove players
    public void removePlayer(Player p){
        Arena a = null;//make an arena
        for(Arena arena : arenas){
            if(arena.getPlayers().contains(p.getName())){
                a = arena;//if the arena has the player, the arena field would be the arena containing the player
            }
            //if none is found, the arena will be null
        }
        if(a == null || !a.getPlayers().contains(p.getName())){//make sure it is not null
            p.sendMessage(MsgType.ERROR + "Invalid operation!");
            return;
        }

        a.getPlayers().remove(p.getName());//remove from arena

        p.getInventory().clear();
        p.getInventory().setArmorContents(null);
        

        p.getInventory().setContents(inv.get(p.getName()));//restore inventory
        p.getInventory().setArmorContents(armor.get(p.getName()));

        inv.remove(p.getName());//remove entries from hashmaps
        armor.remove(p.getName());
        p.teleport(locs.get(p.getName()));
        locs.remove(p.getName());
        players.remove(p.getName());
        
        p.setExp(0);
        p.setLevel(0);
        p.setFireTicks(0);
        p.setGameMode(GameMode.ADVENTURE);
        if (a.getPlayers().size() != 2) {
        	Bukkit.getServer().getScheduler().cancelTask(xp);
        	sendArenaMessage(MsgType.NORMAL + "Countdown has been canncelled, there are not enough players in the arena!");
        	p.setLevel(0);
        }
    }

    //create arena
    public Arena createArena(Location l){
        int num = arenaSize + 1;
        arenaSize++;

        Arena a = new Arena(l, num);
        arenas.add(a);

        plugin.getConfig().set("Arenas." + num, serializeLoc(l));
        List<Integer> list = plugin.getConfig().getIntegerList("Arenas.Arenas");
        list.add(num);
        plugin.getConfig().set("Arenas.Arenas", list);
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

        if(plugin.getConfig().getIntegerList("Arenas.Arenas").isEmpty()){
            return;
        }
                
        for(int i : plugin.getConfig().getIntegerList("Arenas.Arenas")){
            Arena a = reloadArena(deserializeLoc(plugin.getConfig().getString("Arenas." + i)));
            a.id = i;
        }
    }
    
    public ArrayList<String> getPlayers() {
		return this.players;
	}
    
    public void sendArenaMessage(String message) {
		for (String s : players) {
			Bukkit.getPlayer(s).sendMessage(message);
		}
	}

    public String serializeLoc(Location l){
        return l.getWorld().getName()+","+l.getBlockX()+","+l.getBlockY()+","+l.getBlockZ();
    }
    public Location deserializeLoc(String s){
        String[] st = s.split(",");
        return new Location(Bukkit.getWorld(st[0]), Integer.parseInt(st[1]), Integer.parseInt(st[2]), Integer.parseInt(st[3]));
    }
}