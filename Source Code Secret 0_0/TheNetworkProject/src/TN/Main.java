package TN;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.FireworkEffect;
import org.bukkit.FireworkEffect.Type;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Firework;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.meta.FireworkMeta;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.plugin.messaging.PluginMessageListener;
import org.bukkit.scheduler.BukkitTask;

import TN.Tasks.Ranks;

import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;

import de.inventivegames.util.title.TitleManager;

public class Main extends JavaPlugin implements Listener, PluginMessageListener {
	
    public static boolean chatDisabled = false;
    public ArrayList<String> usingClock;
    private boolean isAlwaysDay = false;
	public static ArrayList<String> trailFire = new ArrayList<String>();
	public static ArrayList<String> trailCloud = new ArrayList<String>();
    
	HashMap<String, Integer> cooldown1;
    
    private int id;
    
    public void getID(int id) {
    	this.id = id;
    }
	
	API h = new API(this);
	
	File newConfig;
    FileConfiguration newConfigz;
    
    File lobby;
    FileConfiguration lobbys;
	
	@SuppressWarnings("unused")
	public void onEnable() {
		createEffects();
    	this.getServer().getMessenger().registerOutgoingPluginChannel(this, "BungeeCord");
        this.getServer().getMessenger().registerIncomingPluginChannel(this, "BungeeCord", this);
        
		Bukkit.getServer().getPluginManager().registerEvents(this, this);
		Bukkit.getServer().getPluginManager().registerEvents(new Chat(), this);
		Bukkit.getServer().getPluginManager().registerEvents(new PlayerVisibility(this), this);
		Bukkit.getServer().getPluginManager().registerEvents(new Menu(this), this);
		Bukkit.getServer().getPluginManager().registerEvents(new Kick_Ban(this), this);
		Bukkit.getServer().getPluginManager().registerEvents(new Ranks(this), this);
		
		FileConfiguration config = this.getConfig();
    	config.options().copyDefaults(true);
    	config.addDefault("bossbar.text", "§6Welcome to §cThe-Network §6{player}");
    	saveConfig();
    	
    	newConfig = new File(getDataFolder(), "bannedplayers.yml"); // set the file location
    	newConfigz = YamlConfiguration.loadConfiguration(newConfig); // this will give you all the functions such as .getInt, getString ect..
    	saveNewConfig();
    	
    	lobby = new File(getDataFolder(), "lobbys.yml"); // set the file location
    	lobbys = YamlConfiguration.loadConfiguration(lobby); // this will give you all the functions such as .getInt, getString ect..
    	saveLobbyConfig();
    	
    	StartTimer();
    	this.usingClock = new ArrayList<String>();
    	
    	getCommand("ban").setExecutor(new Kick_Ban(this));
    	getCommand("kick").setExecutor(new Kick_Ban(this));
    	getCommand("unban").setExecutor(new Kick_Ban(this));
    	getCommand("chatenable").setExecutor(new Chat());
    	getCommand("chatclear").setExecutor(new Chat());
    	getCommand("chatdisable").setExecutor(new Chat());
		
		BukkitTask Cooldown = new Ranks(this).runTaskTimer(this, 0, 100);
	}
	
	public void serverConnect(String server, Player p) {
    	ByteArrayDataOutput out = ByteStreams.newDataOutput();
        out.writeUTF("Connect");
        out.writeUTF(server);
		p.sendPluginMessage(this, "BungeeCord", out.toByteArray());
    }
	
	/*public static void checkPlayerVersion(Player p) {
		if(((CraftPlayer) p).getHandle().playerConnection.networkManager.getVersion() >= 47) {
			p.sendMessage(ChatColor.GOLD + "We see that you are usign 1.8 or higher, some plugins are disabled for you! And new special cool stuff are added for you!");
		}else if (((CraftPlayer) p).getHandle().playerConnection.networkManager.getVersion() <= 47) {
			p.sendMessage(ChatColor.GOLD + "Hey, thnx for joining us with 1.7!");
		}	
	}
	*/
	
	public void createEffects() {
		Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(this, new Runnable() {
			@SuppressWarnings("deprecation")
			@Override
			public void run() {
				for (Player all : Bukkit.getOnlinePlayers()) {
				if (trailCloud.contains(all.getName())) {
			ParticleEffect.CLOUD.display(40, 40, 40, 40, 40, all.getLocation(), all);
		}else if (trailFire.contains(all.getName())) {
			ParticleEffect.FLAME.display(40, 40, 40, 40, 40, all.getLocation(), all);
		}
			}
			}
		}, 0, 20);
		
	}
	
	public static void removeEffects(Player all) {
		String name = all.getName();
		if (trailCloud.contains(name)) {
			trailCloud.remove(name);
		}else if (trailFire.contains(name)) {
			trailFire.remove(name);
		}else {
			all.sendMessage(ChatColor.RED + "You have currently no effects enabled!");
		}
	}
	
	 @Override
	  public void onPluginMessageReceived(String channel, Player player, byte[] message) {
	    if (!channel.equals("BungeeCord")) {
	      return;
	    }
	    ByteArrayDataInput in = ByteStreams.newDataInput(message);
	    String subchannel = in.readUTF();
	    if (subchannel.equals("SomeSubChannel")) {
	      // Use the code sample in the 'Response' sections below to read
	      // the data.
	    }
	}
	
	/*public void spawnBoat() {
		final Location loc2 = new Location(Bukkit.getServer().getWorld("world"), 86, 146, 320, -180, 10);
		WitherSkull skull = (WitherSkull) Bukkit.getWorld("world").spawn(loc2, WitherSkull.class);
		skull.setDirection(new Vector(0, 0, 0));
		skull.setVelocity(new Vector(0, 0, 0));
		Horse h2 = (Horse) Bukkit.getServer().getWorld("world").spawnEntity(loc2, EntityType.HORSE);
		skull.setPassenger(h2);
		}*/
	
	/*public void spawnHorses() {
		final Location loc = new Location(Bukkit.getServer().getWorld("world"), 84, 146, 320, -180, 10);
		final Location loc2 = new Location(Bukkit.getServer().getWorld("world"), 86, 146, 320, -180, 10);
		 
		final Horse h = (Horse) Bukkit.getServer().getWorld("world").spawnEntity(loc, EntityType.HORSE);
		final Horse h2 = (Horse) Bukkit.getServer().getWorld("world").spawnEntity(loc2, EntityType.HORSE);
		
		h.setVariant(Horse.Variant.UNDEAD_HORSE);
		h2.setVariant(Horse.Variant.UNDEAD_HORSE);
		
		UUID movementSpeedUID = UUID.randomUUID();
        EntityInsentient nmsEntity = (EntityInsentient) ((CraftLivingEntity) h).getHandle();
        AttributeInstance attributes = nmsEntity.getAttributeInstance(GenericAttributes.d);
        AttributeModifier modifier = new AttributeModifier(movementSpeedUID, "Horse", -100D, 1);
        attributes.b(modifier);
        attributes.a(modifier);
        
        UUID movementSpeedUID1 = UUID.randomUUID();
        EntityInsentient nmsEntity1 = (EntityInsentient) ((CraftLivingEntity) h2).getHandle();
        AttributeInstance attributes1 = nmsEntity1.getAttributeInstance(GenericAttributes.d);
        AttributeModifier modifier1 = new AttributeModifier(movementSpeedUID1, "Horse", -100D, 1);
        attributes1.b(modifier1);
        attributes1.a(modifier1);
	}
	*/
	@EventHandler
	public void onPlayerInteractEntityEvent(EntityDamageByEntityEvent e) {
	    e.setCancelled(true);
	}
	
	public void StartTimer() {
	    Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(this, new Runnable() {
	        @Override
	        public void run() {
	            Bukkit.getServer().broadcastMessage(ChatColor.RED + "[INFO] " + ChatColor.GRAY
	            		+ "Welcome to " + ChatColor.DARK_AQUA + "The-Network." + ChatColor.GRAY + " Enjoy your stay on the server, reading your book for more info!");
	            AnotherTimer(); // Call the other timer
	        }
	    }, 6000); // 1 minuut
	}
	
	public void AnotherTimer() {
	    Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(this, new Runnable() {
	        @Override
	        public void run() {
	            Bukkit.getServer().broadcastMessage(ChatColor.RED + "[INFO] " + ChatColor.GRAY
	            		+ "Want to buy " + ChatColor.GOLD + "VIP " + ChatColor.GRAY + "or special cool stuff? Visit the shop: "
	            		+ ChatColor.DARK_AQUA + "\n http://shop.thenetwork-mc.net" + ChatColor.GRAY + "! Use coupon code: " + ChatColor.RED + "" + ChatColor.BOLD + "\nSERVEROPENING " 
	            		+ ChatColor.GRAY + "to get 10% discount!");
	            StartTimer();
	        }
	    }, 6000); //1 minuut
	}
	
	  /*public boolean isPlayerRightVersion(Player player) {
	    return ((CraftPlayer)player).getHandle().playerConnection.networkManager.getVersion() >= 47;
	}
	  
	  @EventHandler
	  public void onPlayerServerJoin(PlayerJoinEvent e) {
		  Player p = e.getPlayer();
		  if (isPlayerRightVersion(p)) {
			//the components like you did, and the packet which is responsible for the header
	            IChatBaseComponent titleInTab = ChatSerializer.a("{\"text\": \"Insert words here\"}");
	            IChatBaseComponent footInTab = ChatSerializer.a("{\"text\": \"Words for the footer\"}");
	            PacketPlayOutPlayerListHeaderFooter header = new PacketPlayOutPlayerListHeaderFooter(titleInTab);
	           
	            //This is where reflection begins, it is needed mainly for the footer since .b() is a private class
	            Field f;

	           //Sorry that it looks messy with all of the try/catch statements
	            try {
	                f = header.getClass().getDeclaredField("b");
	                f.setAccessible(true);
	                try {
	                    f.set(header, footInTab);
	                } catch (IllegalArgumentException | IllegalAccessException e1) {
	                    e1.printStackTrace();
	                }
	            } catch (NoSuchFieldException | SecurityException e1) {
	                e1.printStackTrace();
	            }

	            p.sendPacket(header);
	            //The pc is just the player connection and the packet is sent
		  }
	  }
	*/
	public void saveNewConfig(){
    	try{
    	newConfigz.save(newConfig);
    	 
    	}catch(Exception e){
    	e.printStackTrace();
    	}
    	}
	
	public void saveLobbyConfig(){
    	try{
    	lobbys.save(lobby);
    	 
    	}catch(Exception e){
    	e.printStackTrace();
    	}
    	}
	
	@EventHandler
	public void onBlockBreak(BlockBreakEvent e) {
		if (!e.getPlayer().hasPermission("network.block.bypass")) {
		e.setCancelled(true);
		}else {
			e.setCancelled(false);
		}
	}
	
	@EventHandler
	public void onPlayerBlockPlace(BlockPlaceEvent e) {
		if (!e.getPlayer().hasPermission("network.block.bypass")) {
			e.setCancelled(true);
			}else {
				e.setCancelled(false);
			}
	}
	
	@EventHandler
	public void onPluginTyp(PlayerCommandPreprocessEvent e) {
		ArrayList<String> cmds = new ArrayList<String>();
		cmds.add("gm");
		cmds.add("money");
		cmds.add("bal");
		cmds.add("clear");
		cmds.add("ci");
		cmds.add("home");
		cmds.add("sethome");
		cmds.add("delhome");
		cmds.add("warp");
		cmds.add("invsee");
		cmds.add("me");
		cmds.add("list");
		cmds.add("pay");
		cmds.add("sell");
		cmds.add("sudo");
		cmds.add("tpa");
		cmds.add("tpaccept");
		
		for (String commands : cmds) {
		if (e.getMessage().equals(commands)) {
			if (!e.getPlayer().hasPermission("network.rank.owner")) {
			e.setCancelled(true);
			}
		}else if (e.getMessage().equals("plugins") || e.getMessage().equals("pl")) {
			e.setCancelled(true);
		}
		}
	}
	
	public void spawnFirework(Player p) {		
		Location l1 = new Location(p.getWorld(), 53, 145, 300);
		Location l2 = new Location(p.getWorld(), 51, 145, 351);
		Location l3 = new Location(p.getWorld(), 11, 145, 303);
		Location l4 = new Location(p.getWorld(), 21, 145, 341);
		
		createFireWork(p, l1);
		createFireWork(p, l1);
		createFireWork(p, l1);
		createFireWork(p, l1);
		createFireWork(p, l1);
		createFireWork(p, l1);
		
		createFireWork(p, l2);
		createFireWork(p, l2);
		createFireWork(p, l2);
		createFireWork(p, l2);
		createFireWork(p, l2);
		createFireWork(p, l2);

		createFireWork(p, l3);
		createFireWork(p, l3);
		createFireWork(p, l3);
		createFireWork(p, l3);
		createFireWork(p, l3);
		createFireWork(p, l3);
		
		createFireWork(p, l4);
		createFireWork(p, l4);
		createFireWork(p, l4);
		createFireWork(p, l4);
		createFireWork(p, l4);
		createFireWork(p, l4);
	}
	
	public void createFireWork(Player p, Location loc) {
		Firework fw = (Firework) loc.getWorld().spawn(loc, Firework.class);
		FireworkMeta fm = fw.getFireworkMeta();
		Random r = new Random();
		int fType = r.nextInt(5) + 1;
		Type type = null;
		switch (fType) {
		default:
		case 1:
			type = Type.BALL;
			break;
		case 2:
			type = Type.BALL_LARGE;
			break;
		case 3:
			type = Type.BURST;
			break;
		case 4:
			type = Type.CREEPER;
			break;
		case 6:
			type = Type.STAR;
			break;
		}
		int c1i = r.nextInt(16) +1;
		int c2i = r.nextInt(16) +1;
		Color c1 = getColour(c1i);
		Color c2 = getColour(c2i);
		FireworkEffect effect = FireworkEffect.builder().flicker(r.nextBoolean()).withColor(c1).withFade(c2)
				.with(type).trail(r.nextBoolean()).build();
		fm.addEffect(effect);
		int power = r.nextInt(2) +1;
		fm.setPower(power);
		fw.setFireworkMeta(fm);
	}
	
	public Color getColour(int c) {
		switch (c) {
		default:
		case 1:return Color.AQUA;
		case 2:return Color.BLACK;
		case 3:return Color.BLUE;
		case 4:return Color.FUCHSIA;
		case 5:return Color.GRAY;
		case 6:return Color.GREEN;
		case 7:return Color.LIME;
		case 8:return Color.MAROON;
		case 9:return Color.NAVY;
		case 10:return Color.OLIVE;
		case 11:return Color.PURPLE;
		case 12:return Color.RED;
		case 13:return Color.SILVER;
		case 14:return Color.TEAL;
		case 15:return Color.WHITE;
		case 16:return Color.YELLOW;
		}
	}
	
	@EventHandler
	public void onPlayerQuit(PlayerQuitEvent e) {
		Player p = e.getPlayer();
			p.getInventory().clear();
			e.setQuitMessage(null);
	}
	
	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent e) {
		final Player p = e.getPlayer();
		
		p.setWalkSpeed((float) 0.3);
		
		p.setGameMode(GameMode.ADVENTURE);
		p.setFlying(false);
		p.teleport(p.getWorld().getSpawnLocation());
		
		if (!p.hasPlayedBefore()) {
			spawnFirework(p);
		}
		
		TitleManager.sendTimings(e.getPlayer(), 20, 40, 20);
		TitleManager.sendTitle(p, "{\"text\":\"\",\"extra\":[{\"text\":\"THE-NETWORK!\",\"color\":\"blue\"}]}");
		TitleManager.sendSubTitle(p, "{\"text\":\"\",\"extra\":[{\"text\":\"Welcome to the offcial LOBBY!\",\"color\":\"white\"}]}");
		
		e.setJoinMessage(null);
		//e.setJoinMessage(ChatColor.GOLD + e.getPlayer().getName() + ChatColor.RED + " has joined the lobby! " + ChatColor.GRAY + "(tijdelijke message)");

	}
	
    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args){
        final Player p = (Player) sender;
        String no_perms = ChatColor.RED + "Sorry, but you dont have permissions!";
        if (sender instanceof ConsoleCommandSender || sender instanceof Player) {
        if (cmd.getName().equalsIgnoreCase("alwaysday")) {
        	if (!sender.hasPermission("network.rank.owner")) {
        		sender.sendMessage(no_perms);
        		return true;
        	}
        	if (args.length == 0) {
        		if (!isAlwaysDay == false) {
        			sender.sendMessage(ChatColor.RED + "Its already always day, so use /alwaysday stop!");
        			return true;
        		}else {
        			sender.sendMessage(ChatColor.GOLD + "Task started! Enjoy your day!");
        		isAlwaysDay = true;
        	id = Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(this, new Runnable() {
        		@Override
        		public void run() {
        			Bukkit.getServer().getWorld("world").setTime(6000);
        		}
        	}, 1, 1);
        		}
        	return true;
        	}else if (args.length == 1) {
        		if (args[0].equalsIgnoreCase("stop")) {
        			if (!isAlwaysDay == true) {
        				sender.sendMessage(ChatColor.RED + "Its already stopped, so use /alwaysday, to enable it!");
            			return true;
        			}else {
        			Bukkit.getServer().getScheduler().cancelTask(id);
        			sender.sendMessage(ChatColor.GOLD + "Task stopped! Enjoy your night/day!");
        			isAlwaysDay = false;
        			}
        		}else {
        			sender.sendMessage(ChatColor.RED + "Not a valid command, use /alwaysday [stop]");
        			return true;
        		}
        	}
        }
        }
        if (cmd.getName().equalsIgnoreCase("firework")) {
        	if (!p.hasPermission("network.rank.owner")) {
        		p.sendMessage(no_perms);
        		return true;
        	}
        	spawnFirework(p);
        	p.sendMessage(ChatColor.GOLD + "Succesfull spawned firework! ENJOY!");
        	return true;
        }
        if (cmd.getName().equalsIgnoreCase("lobby")) {
       		if (!p.hasPermission("network.teleport.lobby")) {
       			p.sendMessage(ChatColor.RED + "You dont have permissions to teleport to a lobby");
       			return true;
       		}
       		if (args.length == 1) {
                h.teleportToLobby(p, args[0]);
                return true;
       		}
       		if (args.length < 1) {
       			p.sendMessage(ChatColor.RED + "No lobby has found! Try /lobby <lobbyName>"); 
                return true;
      	}
       		
            else{
            	p.sendMessage(ChatColor.RED + "No lobby has found!");  
            	
            }
      	}
        if (cmd.getName().equalsIgnoreCase("removelobby")) {
       		if (!p.hasPermission("network.teleport.removelobby")) {
       			p.sendMessage(ChatColor.RED + "You dont have permissions to remove a lobby");
       			return true;
       		}
                h.removeLobby(p, args[0]);
                return true;
   		}
   		if (args.length > 1) {
			p.sendMessage(ChatColor.RED + "Error, too many arguments! Try /lobbyhub|lh help");
    		return true;
    	}
        if (cmd.getName().equalsIgnoreCase("listlobby")) {
			if (!p.hasPermission("network.teleport.list")) {
				p.sendMessage(ChatColor.RED + "You dont have permissions to see a list of lobbys");
				return true;
			}
			h.listLobbies(p);
			return true;
		}
   		if (cmd.getName().equalsIgnoreCase("createlobby")) {
       		if (!p.hasPermission("network.teleport.createlobby")) {
       			p.sendMessage(ChatColor.RED + "You dont have permissions to create a lobby");
       			return true;
           	}
       		if (args.length > 1) {
    			p.sendMessage(ChatColor.RED + "Error, too many arguments! Try /lobbyhub|lh help");
        		return true;
        	}
       		if (args.length == 1){
                h.createLobby(p, args[0]);
                return true;
            }else if (args.length < 1){
            	p.sendMessage(ChatColor.RED + "Please specify an lobby name! /createlobby <lobbyname>");
            	return true;
            }
   		}
	return false;
    }

}
