package scoreboard;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

import net.minecraft.server.v1_7_R1.ChatSerializer;
import net.minecraft.server.v1_7_R1.IChatBaseComponent;
import net.minecraft.server.v1_7_R1.PacketPlayOutChat;

import org.black_ixx.playerpoints.PlayerPoints;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.FireworkEffect;
import org.bukkit.FireworkEffect.Type;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.command.BlockCommandSender;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.craftbukkit.v1_7_R1.entity.CraftPlayer;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.FallingBlock;
import org.bukkit.entity.Firework;
import org.bukkit.entity.Player;
import org.bukkit.entity.Villager;
import org.bukkit.entity.Villager.Profession;
import org.bukkit.event.EventHandler;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.FireworkMeta;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;
import org.bukkit.scoreboard.Team;
import org.bukkit.util.Vector;

import scoreboard.MessageManager.MsgType;
import Accesoiries.Balloon;
import Accesoiries.Hat;
import Hotel.SignListener;
import Hotel.VillagerListener;
import Recreatie.GunShop;
import Recreatie.GunShopCommands;
import Recreatie.Restaurant;

import com.bergerkiller.bukkit.common.scoreboards.CommonObjective;
import com.bergerkiller.bukkit.common.scoreboards.CommonScore;
import com.bergerkiller.bukkit.common.scoreboards.CommonScoreboard;

import de.slikey.effectlib.EffectLib;
import de.slikey.effectlib.EffectManager;

public class Main extends JavaPlugin implements Listener {

	API h = new API(this);
	ColourWave color = new ColourWave(this);
	
    File newConfig;
    FileConfiguration newConfigz;
    
    File ride;
    FileConfiguration rideFile;
    
    File check;
    FileConfiguration checkList;
    
    File sudo;
    public static FileConfiguration sudoalias;
	
	public static PlayerPoints playerPoints;  
	public ArrayList<Block> blocks;
	public ArrayList<Player> blok = new ArrayList<Player>();
	EffectManager effectManager;
	Entity villager;
	
    private int id;
    
    public void setId(int id) {
        this.id = id;
    }
    
    private int water;
    private File dir;
    public Config c;
    
    public void setId1(int water) {
        this.water = water;
    }
    
	public static Inventory inventory = Bukkit.createInventory(null, 18, ChatColor.AQUA + "" + ChatColor.BOLD + "Magic-Saga Rides");
	public static Inventory shows = Bukkit.createInventory(null, 9, ChatColor.AQUA + "" + ChatColor.BOLD + "Magic-Saga Shows");
	public static Inventory checkmenu = Bukkit.createInventory(null, 45, "");
	public HashMap<ItemStack, String> hash = new HashMap<ItemStack, String>();
    public static int timer = 0;
	
	@Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(this, this);
        
		ride = new File(getDataFolder(), "Database.yml"); // set the file location
		rideFile = YamlConfiguration.loadConfiguration(ride); // this will give you all the functions such as .getInt, getString ect..
    	saveRideConfig();
    	
		sudo = new File(getDataFolder(), "Maintenance.yml"); // set the file location
		sudoalias = YamlConfiguration.loadConfiguration(sudo); // this will give you all the functions such as .getInt, getString ect..
    	saveRideConfig();
    	
		check = new File(getDataFolder(), "UNUSED.yml"); // set the file location
		checkList = YamlConfiguration.loadConfiguration(check); // this will give you all the functions such as .getInt, getString ect..
    	saveCheckListConfig();
    	
    	newConfig = new File(getDataFolder(), "lobbys.yml"); // set the file location
    	newConfigz = YamlConfiguration.loadConfiguration(newConfig); // this will give you all the functions such as .getInt, getString ect..
    	saveNewConfig();
    	
    	
    	sudoalias.options().copyDefaults(true);
    	sudoalias.addDefault("maintenance_on", false);
    	sudoalias.addDefault("maintenance_kickmessage", "Server is in maintenance, come back later!");
    	sudoalias.addDefault("maintenance_motd", "&3Magic-Saga ---- &4Maintenance &cBack soon");
    	sudoalias.addDefault("normal_motd", "&cMagic-Saga");
    	sudoalias.addDefault("ipbanned_motd", "&cMagic-Saga &4You are Banned! Dont try to join! Its usseluss");
    	saveSudoConfig();
        
		hookPlayerPoints();
		Bukkit.getServer().getPluginManager().registerEvents(new Menu(this), this);
		Bukkit.getServer().getPluginManager().registerEvents(new Trail(this), this);
		Bukkit.getServer().getPluginManager().registerEvents(new Freerun(), this);
		Bukkit.getServer().getPluginManager().registerEvents(new VillagerListener(this), this);
		Bukkit.getServer().getPluginManager().registerEvents(new SignListener(), this);
		//Bukkit.getServer().getPluginManager().registerEvents(new CommandLogger(), this);
		Bukkit.getServer().getPluginManager().registerEvents(new Hat(this), this);
		Bukkit.getServer().getPluginManager().registerEvents(new GunShop(this), this);
		Bukkit.getServer().getPluginManager().registerEvents(new DisWords(), this);
		Bukkit.getServer().getPluginManager().registerEvents(new Shows(this), this);
		Bukkit.getServer().getPluginManager().registerEvents(new Fontain(this), this);
		Bukkit.getServer().getPluginManager().registerEvents(new Restaurant(), this);
		Bukkit.getServer().getPluginManager().registerEvents(new Maintenance(this), this);
		
		getCommand("ride").setExecutor(new Menu(this)); 
		getCommand("fallingblock").setExecutor(this); 
		getCommand("status").setExecutor(new Menu(this)); 
		getCommand("show").setExecutor(new Shows(this)); 
		getCommand("balloon").setExecutor(new Balloon()); 
		getCommand("checklist").setExecutor(new Checklist(this)); 
		getCommand("maintenance").setExecutor(new Maintenance(this)); 
		getCommand("gunshop").setExecutor(new GunShopCommands(this));
        
        toInventory(rideFile, "Rides");
        toInShowventory(rideFile, "Shows");
        updateScoreBoard();
        updateRankBoard();        
        
        Bukkit.getPluginManager().registerEvents(this, this);
        EffectLib lib = EffectLib.instance();
        effectManager = new EffectManager(lib);
        
        spawnVillagerAtLocation();
    }

	  public void onDisable() {
		  effectManager.dispose();
	        HandlerList.unregisterAll((Listener) this);
		  
		  saveConfig();
	    super.onDisable();
	    c.save();
	    villager.remove();
	    
	  }
	  
	  public void setupPerPlayerFile(Player p) {
		  dir = new File(this.getDataFolder() + File.separator + "players");
		  c = new Config(dir, p.getName(), this); //Using "this" only works if it's in the main class, otherwise pass an instance of the main class into the class you're using and use the variable you set it to.
			  setConfigPerPlayer("Guns.ultrapistol", false);
			  setConfigPerPlayer("Guns.shotgun", false);
			  setConfigPerPlayer("Guns.uzi", false);
			  setConfigPerPlayer("Guns.vip.sniper", false);
			  setConfigPerPlayer("Guns.vip.rocketLauncher", false);
			  
			  setConfigPerPlayer("Hats.iceBlock", false);
			  setConfigPerPlayer("Hats.packetIce", false);
			  setConfigPerPlayer("Hats.chest", false);
			  setConfigPerPlayer("Hats.redClay", false);
			  setConfigPerPlayer("Hats.tnt", false);
			  setConfigPerPlayer("Hats.cactus", false);
			  setConfigPerPlayer("Hats.sand", false);
			  setConfigPerPlayer("Hats.redGlass", false);
			  setConfigPerPlayer("Hats.blueGlass", false);
			  setConfigPerPlayer("Hats.greenGlass", false);
			  setConfigPerPlayer("Hats.snowBlock", false);
			  setConfigPerPlayer("Hats.clay", false);
			  setConfigPerPlayer("Hats.pumpkin", false);
			  setConfigPerPlayer("Hats.wood", false);
			  setConfigPerPlayer("Hats.dispenser", false);
			  setConfigPerPlayer("Hats.beacon", false);
			  setConfigPerPlayer("Hats.grass", false);
			  setConfigPerPlayer("Hats.lapiz", false);
	  }
	  
	  public boolean getBoolean(String name) {
		    if(c.getConfig().get(name) == null) {
		        return false;
		    } else {
		        return c.getConfig().getBoolean(name);
		    }
		}
	  
	  public void updateConfigPerPlayer(String name, Object obj) {
		  c.getConfig().set(name, obj);
		  c.save();
	  }
	  
	  public void setConfigPerPlayer(String name, Object obj) {
		  String str = c.getConfig().getString(name);
		  if (str == null) {
			  c.set(name, obj);
			  c.save();
		  }
	  }
    
    public void updateRankBoard() {
    	ScoreboardManager sbManager = Bukkit.getScoreboardManager();
    	final Scoreboard sBoard = sbManager.getNewScoreboard();
    	final Team owner = sBoard.registerNewTeam("owner");
    	final Team builder = sBoard.registerNewTeam("builder");
    	final Team players = sBoard.registerNewTeam("players");
    	
    	Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(this, new Runnable() {
    		@Override
    		public void run() {
    	    	owner.addPlayer(Bukkit.getOfflinePlayer("jusjus112"));
    	    	owner.addPlayer(Bukkit.getOfflinePlayer("Paul1088_Owner"));
    	    	owner.setPrefix(ChatColor.GOLD + "");    	    
    	    	builder.addPlayer(Bukkit.getOfflinePlayer("Sennelololol"));
    	    	builder.setPrefix(ChatColor.AQUA + "");
    	    	players.addPlayer(Bukkit.getOfflinePlayer(getName()));
    	    	players.setDisplayName("Player");
    	    	for (Player all : Bukkit.getServer().getOnlinePlayers()) {
    	    		all.setScoreboard(sBoard);
    	    	}
    		}
    	}, 40L, 40L);
    }
    
    
    public void updateScoreBoard() {
        getServer().getScheduler().scheduleSyncRepeatingTask(this, new Runnable() {
            public void run() {
              int onlinePlayers = Bukkit.getOnlinePlayers().length;
              int totalPlayers = Bukkit.getOfflinePlayers().length;
              

              for (Player player : Bukkit.getOnlinePlayers()) {
                String pname = player.getName().toString();
  		        final int points = playerPoints.getAPI().look(pname);
  		          		        
                CommonScoreboard board = CommonScoreboard.get(player);
                CommonObjective sidebar = board.getObjective(CommonScoreboard.Display.SIDEBAR);

                CommonScore totonline = sidebar.getScore("totonline");
                CommonScore totplayers = sidebar.getScore("totplayers");
                CommonScore show = sidebar.getScore("Show");
                CommonScore points1 = sidebar.getScore("points");
                CommonScore noshow = sidebar.getScore("noShow");
                               
                if (totonline == null) {
                	
                  totonline = sidebar.createScore("totonline", "Now online:", onlinePlayers);
                  totplayers = sidebar.createScore("totplayers", "Unique visitors:", totalPlayers);
                  points1 = sidebar.createScore("points", "Your points:", points);
                }
                else {
      		        }
                	if (timer == 0) {
                		noshow = sidebar.createScore("noShow", ChatColor.BOLD + "No Show:", 0);
                		sidebar.removeScore("Show");
                		noshow.setValue(0);
                	} else {
                		show = sidebar.createScore("Show", ChatColor.GOLD + "Show in:", timer);
                		sidebar.removeScore("noShow");                		
                		show.setValue(timer);
                		show.update();      
                	}
                  totonline.setValue(onlinePlayers);
                  totonline.update();
                  totplayers.setValue(totalPlayers);
                  totplayers.update();
                  points1.setValue(points);
                  points1.update();
                	
                }
              }
            
          }
          , 20L, 20L); 
    }    
	
    public static Inventory toInventory(FileConfiguration config, String path) {
        for (int i = 0; i<27;i++) {
            if (config.isItemStack(path + "." + i)) {
                inventory.setItem(i, config.getItemStack(path + "." + i));
            }
        }
        return inventory;
    }
    
    public static Inventory toCheckListInventory(FileConfiguration config, String path) {
        for (int i = 0; i<27;i++) {
            if (config.isItemStack(path + "." + i)) {
                checkmenu.setItem(i, config.getItemStack(path + "." + i));
            }
        }
        return checkmenu;
    }
    
    public static Inventory toInShowventory(FileConfiguration config, String path) {
        for (int i = 0; i<27;i++) {
            if (config.isItemStack(path + "." + i)) {
                shows.setItem(i, config.getItemStack(path + "." + i));
            }
        }
        return shows;
    }
    
	
	public void saveInventory(Inventory inv, FileConfiguration config, String path) {
        for (int i = 0; i<inv.getSize();i++) {
            if (inv.getItem(i)!=null) {
                config.set(path + "." + i, inv.getItem(i));
            } else {
                if (config.isItemStack(path + "." + i)) {
                    config.set(path + "." + i, null);
                }
            }
        }
    }	
	
    public static Inventory toChecklist(FileConfiguration config, String path) {
        for (int i = 0; i<27;i++) {
            if (config.isItemStack(path + "." + i)) {
                inventory.setItem(i, config.getItemStack(path + "." + i));
            }
        }
        return inventory;
    }
	
	public void saveChecklist(Inventory inv, FileConfiguration config, String path) {
        for (int i = 0; i<inv.getSize();i++) {
            if (inv.getItem(i)!=null) {
                config.set(path + "." + i, inv.getItem(i));
            } else {
                if (config.isItemStack(path + "." + i)) {
                    config.set(path + "." + i, null);
                }
            }
        }
    }

     public void saveNewConfig(){
    	try{
    	newConfigz.save(newConfig);
    	 
    	}catch(Exception e){
    	e.printStackTrace();
    	}
    	}
     
     public void saveCheckListConfig(){
    	try{
    	checkList.save(check);
    	 
    	}catch(Exception e){
    	e.printStackTrace();
    	}
    	}
	
	public void saveRideConfig(){
    	try{
    	rideFile.save(ride);
    	 
    	}catch(Exception e){
    	e.printStackTrace();
    	}
    	}
	public void saveSudoConfig(){
    	try{
    	sudoalias.save(sudo);
    	 
    	}catch(Exception e){
    	e.printStackTrace();
    	}
    	}
	
	private boolean hookPlayerPoints() {
	    final Plugin plugin = this.getServer().getPluginManager().getPlugin("PlayerPoints");
	    playerPoints = PlayerPoints.class.cast(plugin);
	    return playerPoints != null; 
	}
	
	public PlayerPoints getPlayerPoints() {
	    return playerPoints;
	}
	
    public static Inventory toCheckListInventory(FileConfiguration config, String path, Inventory inv) {
        for (int i = 0; i<27;i++) {
            if (config.isItemStack(path + "." + i)) {
            	inv.setItem(i, config.getItemStack(path + "." + i));
            }
        }
        return inv;
    }
	//private static String playerCheckList;
    //public static PlayerData pd = new PlayerData(playerCheckList);
    
    public void setupPlayerDataFolder(Player p) {

	    
    }
    
	public void onPlayerAddChecklistItems(Player p) {
		FileConfiguration c = this.checkList;
        Set<String> rideKeys = c.getConfigurationSection("Achievements").getKeys(false); // Alle ride names (anus, testshow etc. Welke mongool noemt zijn ride nu anus!)
        for (String ridename: rideKeys) {		
        	
			ItemStack chest1 = new ItemStack(Material.WOOL, 1, (short) 14);		
			ItemMeta plate1 = chest1.getItemMeta();		
			plate1.setDisplayName(ChatColor.GRAY + "• " + ChatColor.RED + ridename + ChatColor.GRAY + " •");
			chest1.setItemMeta(plate1);
			
			ItemStack chest11 = new ItemStack(Material.WOOL, 1, (short) 14);		
			ItemMeta plate11 = chest11.getItemMeta();		
			plate11.setDisplayName(ChatColor.GRAY + "• " + ChatColor.GREEN + ridename + ChatColor.GRAY + " •");
			chest11.setItemMeta(plate11);	    
	    
	    //if (!Menu.checkMenu.contains(chest1) || !Menu.checkMenu.contains(chest11))
	    //saveInventory(checkmenu, pd.getData(), "Achievements");
	    //saveCheckListConfig();
        }
	   
	}
	
    @EventHandler
	public void onPlayerJoin(final PlayerJoinEvent e) {   
    	setupPerPlayerFile(e.getPlayer());
    	
		if (e.getPlayer().hasPlayedBefore()) {
		e.setJoinMessage(ChatColor.GREEN + "" + ChatColor.BOLD + "+ " 
	+ ChatColor.RESET + "" + ChatColor.AQUA + "" + ChatColor.BOLD + e.getPlayer().getName() + " Has Joined " + ChatColor.RESET 
	+ "" + ChatColor.RED + "" + ChatColor.BOLD + "Magic-Saga");		
		}else {
			e.setJoinMessage(ChatColor.GREEN + "" + ChatColor.BOLD + "+ " + ChatColor.RESET + "" + ChatColor.AQUA + "" + ChatColor.BOLD + " A warm welcome to "
					+ ChatColor.RESET + "" + ChatColor.AQUA + "" + ChatColor.BOLD + e.getPlayer().getName() + " to " + ChatColor.RESET
					+ "" + ChatColor.RED + "" + ChatColor.BOLD + "Magic-Saga");
			
			double x = -1411;
			double y = 61;
			double z = 1128;
			World w = e.getPlayer().getWorld();
			
			final Location l = new Location(w, x, y, z);
			
			Firework fw132 = (Firework) e.getPlayer().getWorld().spawnEntity(l , EntityType.FIREWORK);
	        FireworkMeta fwm132 = fw132.getFireworkMeta();
	        FireworkEffect effect132 = FireworkEffect.builder().flicker(true).withColor(Color.ORANGE, Color.RED).withFade().with(Type.BALL_LARGE).trail(true).build();
	        fwm132.addEffect(effect132);
	        fwm132.setPower(2);
	        fw132.setFireworkMeta(fwm132);
	        
	        Firework fw1324 = (Firework) e.getPlayer().getWorld().spawnEntity(l , EntityType.FIREWORK);
	        FireworkMeta fwm1324 = fw1324.getFireworkMeta();
	        FireworkEffect effect1324 = FireworkEffect.builder().flicker(true).withColor(Color.LIME, Color.SILVER).withFade().with(Type.BALL).trail(true).build();
	        fwm1324.addEffect(effect1324);
	        fwm1324.setPower(1);
	        fw1324.setFireworkMeta(fwm1324);
	        
	        Firework fw1325 = (Firework) e.getPlayer().getWorld().spawnEntity(l , EntityType.FIREWORK);
	        FireworkMeta fwm1325 = fw1325.getFireworkMeta();
	        FireworkEffect effect1325 = FireworkEffect.builder().flicker(true).withColor(Color.BLUE, Color.RED).withFade().with(Type.STAR).trail(true).build();
	        fwm1325.addEffect(effect1325);
	        fwm1325.setPower(2);
	        fw1325.setFireworkMeta(fwm1325);
	        
	        Firework fw1326 = (Firework) e.getPlayer().getWorld().spawnEntity(l , EntityType.FIREWORK);
	        FireworkMeta fwm1326 = fw1326.getFireworkMeta();
	        FireworkEffect effect1326 = FireworkEffect.builder().flicker(true).withColor(Color.ORANGE, Color.MAROON).withFade().with(Type.BURST).trail(true).build();
	        fwm1326.addEffect(effect1326);
	        fwm1326.setPower(1);
	        fw1326.setFireworkMeta(fwm1326);
	        
	        Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(this, new Runnable() {
	        	@Override
	        	public void run() {
	        		Firework fw132 = (Firework) e.getPlayer().getWorld().spawnEntity(l , EntityType.FIREWORK);
	                FireworkMeta fwm132 = fw132.getFireworkMeta();
	                FireworkEffect effect132 = FireworkEffect.builder().flicker(true).withColor(Color.NAVY, Color.GRAY).withFade().with(Type.BALL_LARGE).trail(true).build();
	                fwm132.addEffect(effect132);
	                fwm132.setPower(2);
	                fw132.setFireworkMeta(fwm132);
	                
	                Firework fw1327 = (Firework) e.getPlayer().getWorld().spawnEntity(l , EntityType.FIREWORK);
	                FireworkMeta fwm1327 = fw1327.getFireworkMeta();
	                FireworkEffect effect1327 = FireworkEffect.builder().flicker(true).withColor(Color.ORANGE, Color.RED).withFade().with(Type.BALL_LARGE).trail(false).build();
	                fwm1327.addEffect(effect1327);
	                fwm1327.setPower(2);
	                fw1327.setFireworkMeta(fwm1327);
	        	}
	        }, 20L);
		}
	    Player player = e.getPlayer();
	    CommonScoreboard board = CommonScoreboard.get(player);
	    final CommonObjective sidebar = board.getObjective(CommonScoreboard.Display.SIDEBAR);
	    sidebar.show();
	    sidebar.setDisplayName(ChatColor.RED + "" + ChatColor.BOLD + "Magic" + ChatColor.WHITE + "-" + ChatColor.AQUA + "" + ChatColor.BOLD + "Saga");
	    h.teleportToHub(e.getPlayer());
	    
	    id = Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(this, new Runnable() {
	    	@Override
	    	public void run() {
	    		playerPoints.getAPI().give(e.getPlayer().getName(), 5);
	    		e.getPlayer().playSound(e.getPlayer().getLocation(), Sound.LEVEL_UP, 2, 2);
	    		e.getPlayer().sendMessage(MsgType.SHOW + "You have recieved 5 points from us for being online!");
	    	}
	    }, 60 * 20L * 5, 60 * 20L * 5);
    }
    
    @EventHandler
    public void ontntExplode(EntityExplodeEvent e) {
    	e.setCancelled(true);
    }
	
	@EventHandler
	public void onPlayerQuit1(PlayerQuitEvent e) {
		e.setQuitMessage(ChatColor.RED + "" + ChatColor.BOLD + "- " 
	+ ChatColor.RESET + "" + ChatColor.AQUA + "" + ChatColor.BOLD + e.getPlayer().getName() + " Has Left " + ChatColor.RESET 
	+ "" + ChatColor.RED + "" + ChatColor.BOLD + "Magic-Saga");
		
		if (e.getPlayer().hasPermission("magic.inventory.clear")) {
		e.getPlayer().getInventory().clear();
		e.getPlayer().getInventory().setArmorContents(null);
		}
		Bukkit.getServer().getScheduler().cancelTask(id);
		
	}
	
	public void spawnVillagerAtLocation() {
		final Location loc = new Location(Bukkit.getServer().getWorld("world"), -1301, 31, 1238);
		
		villager = loc.getWorld().spawn(loc, Villager.class);
		((Villager)villager).addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 1000000, 128));
		((Villager)villager).addPotionEffect(new PotionEffect(PotionEffectType.JUMP, 1000000, 6));
		((Villager)villager).setCustomName(ChatColor.GOLD + "Hotel Medewerker");
		((Villager)villager).setCustomNameVisible(true);
		((Villager)villager).setRemoveWhenFarAway(false);
		((Villager)villager).setBreed(false);
		((Villager)villager).setProfession(Profession.BUTCHER);
		
		Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(this, new Runnable() {
			@Override
			public void run() {
				((Villager)villager).teleport(loc);
			}
		}, 20L, 20L);
	}
	
	 public static void main() {
		 Bukkit.getServer().broadcastMessage(welcome("jusjus112"));
	        Bukkit.getServer().broadcastMessage(advertisement());
	        Bukkit.getServer().broadcastMessage(gui("jusjus", 413000));
	    }
	 
	    static String welcome(String playername) {
	        return new FancyMessage("Hello, ")
	            .color(ChatColor.YELLOW)
	        .then(playername)
	            .color(ChatColor.LIGHT_PURPLE)
	            .style(ChatColor.ITALIC, ChatColor.UNDERLINE)
	        .then("!")
	            .color(ChatColor.YELLOW)
	            .style(ChatColor.ITALIC)
	        .toJSONString();
	    }
	 
	    static String advertisement() {
	        return new FancyMessage("Visit ")
	            .color(ChatColor.GREEN)
	        .then("our website")
	            .color(ChatColor.YELLOW)
	            .style(ChatColor.UNDERLINE)
	            .link("http://awesome-server.net")
	            .tooltip("AwesomeServer Forums")
	        .then(" to win ")
	            .color(ChatColor.GREEN)
	        .then("big prizes!")
	            .color(ChatColor.AQUA)
	            .style(ChatColor.BOLD)
	            .tooltip("Terms and conditions may apply. Offer not valid in Sweden.")
	        .toJSONString();
	    }
	 
	    static String gui(String playername, int blocksEdited) {
	        return new FancyMessage("Player ")
	            .color(ChatColor.DARK_RED)
	        .then(playername)
	            .color(ChatColor.RED)
	            .style(ChatColor.ITALIC)
	        .then(" changed ").color(ChatColor.DARK_RED)
	        .then(blocksEdited).color(ChatColor.AQUA)
	        .then(" blocks. ").color(ChatColor.DARK_RED)
	        .then("Roll back?")
	            .color(ChatColor.GOLD)
	            .style(ChatColor.UNDERLINE)
	            .suggest("/ride " + playername)
	            .tooltip("Be careful, this might undo legitimate edits!")
	        .then(" ")
	        .then("Ban?")
	            .color(ChatColor.RED)
	            .style(ChatColor.UNDERLINE)
	            .suggest("/ride " + playername)
	            .tooltip("Remember: only ban if you have photographic evidence of grief.")
	        .toJSONString();
	    }
	    
	    public void sayGetTexturePack(Player p) {
			IChatBaseComponent comp = ChatSerializer.a("{\"text\":\"Click to get the resource pack! \","
					+ "\"color\":\"gold\",\"extra\":[{\"text\":\"[Click to get it]\","
					+ "\"color\":\"red\",\"bold\":\"true\",\"italic\":\"true\","
					+ "\"underlined\":\"false\",\"strikethrough\":\"false\","
					+ "\"obfuscated\":\"false\","
					+ "\"clickEvent\":{\"action\":\"run_command\",\"value\":\"/texturepack\" }}]}");
	        PacketPlayOutChat packet = new PacketPlayOutChat(comp, true);
	        ((CraftPlayer) p).getHandle().playerConnection.sendPacket(packet);
	    }
	    
	    public void sayGoToShow(Player p) {
			IChatBaseComponent comp = ChatSerializer.a("{\"text\":\"Click to go to the show! \","
					+ "\"color\":\"gold\",\"extra\":[{\"text\":\"[Click to go]\","
					+ "\"color\":\"red\",\"bold\":\"true\",\"italic\":\"true\","
					+ "\"underlined\":\"false\",\"strikethrough\":\"false\","
					+ "\"obfuscated\":\"false\","
					+ "\"clickEvent\":{\"action\":\"run_command\",\"value\":\"/show Pegasus\" }}]}");
	        PacketPlayOutChat packet = new PacketPlayOutChat(comp, true);
	        ((CraftPlayer) p).getHandle().playerConnection.sendPacket(packet);
	    }
	    
	    //"{"text":"Do you want an apple? ", "extra": [{"text":"[OK]","color":"red","clickEvent": {"action":"run_command","value":"/give @p 260"}, "hoverEvent":{"action":"show_text","value":"I really do"}}]}"    
	    
	    //  /command      0 1 2   3    4   5  6
	    //  /fallingblock 0 1 0 -1374 34 1129 9
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args){ 
		
        if (cmd.getName().equalsIgnoreCase("fallingblock")) {
        	//p.sendMessage("DEBUG: COMMAND WORKING");
        	if (sender instanceof ConsoleCommandSender || sender instanceof BlockCommandSender) {
        	if (!(args.length < 6)) {
        		//p.sendMessage("DEBUG: ARGS WORKING");
        		int x = Integer.parseInt(args[3]);
        		int y = Integer.parseInt(args[4]);
        		int z = Integer.parseInt(args[5]);
        		
        		//Bukkit.broadcastMessage("Loc x: " + x);
        		//Bukkit.broadcastMessage("Loc y: " + y);
        		//Bukkit.broadcastMessage("Loc z: " + z);
        		
        		Material mat =  Material.matchMaterial(args[6]);
        		//Bukkit.broadcastMessage("Material: " + mat);
        		
        		double vector1 = Double.parseDouble(args[0]);
        		double vector2 = Double.parseDouble(args[1]);
        		double vector3 = Double.parseDouble(args[2]);
        		
        		//Bukkit.broadcastMessage("Vector1: " + vector1);
        		//Bukkit.broadcastMessage("Vector2: " + vector2);
        		//Bukkit.broadcastMessage("Vector3: " + vector3);
        		
        		Location loc = new Location(Bukkit.getServer().getWorld("world"), x, y, z);
                FallingBlock falblock = loc.getWorld().spawnFallingBlock(loc, mat, (byte) 0);
                falblock.setDropItem(false);
                falblock.setVelocity(new Vector(vector1, vector2, vector3));     
                //p.sendMessage("DEBUG: ARGS WORKING2");
                return true;
        	}
        	}
        }
        if (cmd.getName().equalsIgnoreCase("block")) {
        	if (sender instanceof ConsoleCommandSender || sender instanceof BlockCommandSender) {
        	if (args.length > 0) {    		
            		int x = Integer.parseInt(args[0]);
            		int y = Integer.parseInt(args[1]);
            		int z = Integer.parseInt(args[2]);
            		
            		Material mat =  Material.matchMaterial(args[3]);
            		
            		Location loc = new Location(Bukkit.getServer().getWorld("world"), x, y, z);
            		loc.getBlock().setType(mat);
        		}
        	}
        }
        if (cmd.getName().equalsIgnoreCase("texturepack")) {
        	Player p = (Player) sender;
        	String texturePack = "http://magicsaga.bugs3.com/resource/Pegasus.zip";
        	p.setResourcePack(texturePack);
        	
        }
		if (cmd.getName().equalsIgnoreCase("setmagicspawn")) {
			final Player p = (Player) sender;
			if (p.hasPermission("magic.setspawn")) {
			h.createHub(p);
			return true;
			}else {
				p.sendMessage(MsgType.DENIED + "You dont have permissions!");
			}
			}
		
		   if (cmd.getName().equalsIgnoreCase("omroep")){
			    if (args.length < 0){
			     sender.sendMessage(ChatColor.RED + "Probeer dit /omroep [bericht]");
			     return true;
			    }else if (args.length >0) {
			            StringBuilder b = new StringBuilder();
			            for (int i = 1; i < args.length; i++) {
			                if (i != 0)
			                    b.append(" ");
			                b.append(args[i]);
			            }
			         
			            String message = b.toString();
			    Bukkit.getServer().broadcastMessage(message);
			    }
			}
		
		if (cmd.getName().equalsIgnoreCase("watershow")) {
        	if (sender instanceof ConsoleCommandSender || sender instanceof BlockCommandSender) {
			if (args.length == 1) {
				if (args[0].equalsIgnoreCase("10")) {
				if (timer == 0) {
				timer = 10;
				Bukkit.getServer().broadcastMessage(ChatColor.GRAY + "----------{" + ChatColor.GOLD + "" + ChatColor.BOLD + "Pegasus" + ChatColor.RESET + "" + ChatColor.GRAY + "}----------");
				Bukkit.getServer().broadcastMessage(ChatColor.GOLD + "Show starts in " + 10 + " minuts!");
				for (Player all : Bukkit.getServer().getOnlinePlayers()) {
				sayGetTexturePack(all);
				sayGoToShow(all);
				}
		        water = Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(this, new Runnable() {
		        	
					@Override
					public void run() {
						if (timer == 60) {
							
						}
						if (!(timer == 1)) {
						timer --;
						}else {
							Bukkit.getServer().getScheduler().cancelTask(water);
						}
					}
				}, 0L, 20L);
				}
				}else if (args[0].equalsIgnoreCase("5")){
					if (timer == 0) {
						timer = 60;
						Bukkit.getServer().broadcastMessage(ChatColor.GRAY + "----------{" + ChatColor.GOLD + "" + ChatColor.BOLD + "Pegasus" + ChatColor.RESET + "" + ChatColor.GRAY + "}----------");
						Bukkit.getServer().broadcastMessage(ChatColor.GOLD + "Show starts in " + 5 + " minuts!");
						for (Player all : Bukkit.getServer().getOnlinePlayers()) {
						sayGetTexturePack(all);
						sayGoToShow(all);
						}
				        water = Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(this, new Runnable() {
				        	
							@Override
							public void run() {
								if (!(timer == 0)) {
								timer --;
								}else {
									Bukkit.getServer().getScheduler().cancelTask(water);
								}
							}
						}, 0L, 20L);
						}			
				}
				}
			}
		}
        return false;
	}	
}