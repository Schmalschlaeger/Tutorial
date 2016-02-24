package hubpl;

import hubpl.MessageManager.MsgType;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import net.minecraft.util.com.google.common.io.ByteArrayDataOutput;
import net.minecraft.util.com.google.common.io.ByteStreams;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.DyeColor;
import org.bukkit.FireworkEffect;
import org.bukkit.FireworkEffect.Type;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Firework;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.weather.ThunderChangeEvent;
import org.bukkit.event.weather.WeatherChangeEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.FireworkMeta;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.material.Wool;
import org.bukkit.plugin.java.JavaPlugin;

public class Hub extends JavaPlugin implements Listener {
	
	API h = new API(this);
    
    public ArrayList<Player> fly = new ArrayList<Player>();
    public ArrayList<Player> gamemode = new ArrayList<Player>();
    public ArrayList<Player> trail = new ArrayList<Player>();
    public ArrayList<Player> freeze = new ArrayList<Player>();
    public ArrayList<Player> walkspeed = new ArrayList<Player>();
    public ArrayList<Player> flyspeed = new ArrayList<Player>();
    public ArrayList<Player> clickdelay = new ArrayList<Player>();
    private static int instance;
    
    public static String serverName;
    
    private int id;
    
    public void setId(int id) {
        this.id = id;
    }
    
    private int score;
    
    public void setId1(int score) {
        this.score = score;
    }
    
    File newConfig;
    FileConfiguration newConfigz;
    
    File newConfig1;
    FileConfiguration newConfigz1;
    
    public Player pl;
	
    @Override
	public void onEnable() {
		Bukkit.getServer().getPluginManager().registerEvents(new AutoRespawn(this), this);
		Bukkit.getServer().getPluginManager().registerEvents(new bossbar(this), this);
		Bukkit.getServer().getPluginManager().registerEvents(new Menu(this), this);
		Bukkit.getServer().getPluginManager().registerEvents(new Maintenance(this), this);
		Bukkit.getServer().getPluginManager().registerEvents(this, this);

		newConfig = new File(getDataFolder(), "lobbys.yml"); // set the file location
    	newConfigz = YamlConfiguration.loadConfiguration(newConfig); // this will give you all the functions such as .getInt, getString ect..
    	saveNewConfig();
    	
		newConfig1 = new File(getDataFolder(), "maintenance.yml"); // set the file location
    	newConfigz1 = YamlConfiguration.loadConfiguration(newConfig1); // this will give you all the functions such as .getInt, getString ect..
    	saveNewConfig1();
    	
		getCommand("maintenance").setExecutor(new Maintenance(this)); 
    	
	    saveDefaultConfig();

	    if (!getConfig().getBoolean("maintenance_on")) {
	      String getMOTD = Bukkit.getMotd();
	      getConfig().set("normal_motd", getMOTD);
	    }
    	
        FileConfiguration config = this.getConfig();
    	config.options().copyDefaults(true);
    	config.addDefault("bossbar.text", "§6Welcome to this server {player}");
    	saveConfig();
    	
    	getServer().getMessenger().registerOutgoingPluginChannel(this, "BungeeCord");
    	
    	score = Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(this, new Runnable() {
    		@Override
    		public void run() {
    			Bukkit.getServer().getWorld("TheNetwork").setTime(6000);
    		}
    	}, 1, 1);

	}
	
	public void saveNewConfig(){
    	try{
    	newConfigz.save(newConfig);
    	 
    	}catch(Exception e){
    	e.printStackTrace();
    	}
    	}
	
	public void saveNewConfig1(){
    	try{
    	newConfigz1.save(newConfig1);
    	 
    	}catch(Exception e){
    	e.printStackTrace();
    	}
    	}
	
	public void serverConnect(String server, Player p) {
    	ByteArrayDataOutput out = ByteStreams.newDataOutput();
        out.writeUTF("Connect");
        out.writeUTF(server);
		p.sendPluginMessage(this, "BungeeCord", out.toByteArray());
    }
	
	public int getPlayerCount(String server, Player p) {
    	ByteArrayDataOutput out = ByteStreams.newDataOutput();
        out.writeUTF("PlayerCount");
        out.writeUTF(server);
		p.sendPluginMessage(this, "BungeeCord", out.toByteArray());
		return instance;
    }
	
	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent e) throws IOException {
		final Player p = e.getPlayer();
		int online = getPlayerCount("lobby", p);
		e.setJoinMessage(null);
		
    	ByteArrayDataOutput out = ByteStreams.newDataOutput();
    	out.writeUTF("GetServers");
        out.writeUTF("PlayerCount");
        out.writeUTF("lobby");
		p.sendPluginMessage(this, "BungeeCord", out.toByteArray());
		
		if (p.hasPermission("network.join")) {
			p.setWalkSpeed((float) 0.3);
		}else {
	    	h.teleportToHub(p);
	    	p.setWalkSpeed((float) 0.3);
	    	p.setGameMode(GameMode.ADVENTURE);
			p.setFlying(false);
		}
		for (int i = 0; i < 44; i++) {
			p.sendMessage(" ");
			p.sendMessage(" ");
		}
		p.sendMessage(ChatColor.GOLD + "" + ChatColor.BOLD + "============================================");
		p.sendMessage(ChatColor.RED + "            Welcome to " + ChatColor.AQUA + "The" + ChatColor.DARK_AQUA + "Network " + ChatColor.RED + p.getName());
		p.sendMessage(ChatColor.BLUE + "     We have: " + ChatColor.RED + "Minigames, Faction/Survival, Funpark");
		p.sendMessage(ChatColor.BLUE + "     Choose an server with your compass");
		p.sendMessage(ChatColor.BLUE + "     or use an portal to go to an server you like.");
		p.sendMessage(ChatColor.BLUE + "     You can also subscribe to our youtube channel");
		p.sendMessage(ChatColor.RED + "     http://www.youtube.com/user/JusJusCrafti");
		p.sendMessage(ChatColor.BLUE + "     Or donate:" + ChatColor.RED + " www.buycraft.nl/thenetwork");
		p.sendMessage(ChatColor.BLUE + "     Now online at the server: " + ChatColor.GOLD + online);
		p.sendMessage(ChatColor.GOLD + "" + ChatColor.BOLD + "============================================");{
				
			}
			
			final Location loc = p.getLocation();
			Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(this, new Runnable() {
				@Override
				public void run() {
					if (!p.hasPermission("network.join")) {
					ParticleEffect.ENCHANTMENT_TABLE.display(loc, 0.0f, 1.0f, 0.0f, 1.5F, 60);
					ParticleEffect.EXPLODE.display(loc, 0.0f, 1.0f, 0.0f, 1.7F, 50);
					ParticleEffect.FIREWORKS_SPARK.display(loc, 0.0f, 1.0f, 0.0f, 1.5F, 50);
					ParticleEffect.CLOUD.display(loc, 0.0f, 1.0f, 0.0f, 1.5F, 50);
					p.playSound(loc, Sound.ENDERDRAGON_GROWL, 1, 1);
					}
				}
			}, 5);
		}
	
	@EventHandler
	public void onPlayerMEssgae(final PlayerDeathEvent e) {
		final Player p = e.getEntity();
		
		e.setDeathMessage(null);	
		Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(this, new Runnable() {
			@Override
			public void run() {
				h.teleportToHub(p);
				p.setSprinting(true);
				e.getDrops().clear();
			}
		}, 4);
	}
	
	@EventHandler
	public void onTntExplode(EntityExplodeEvent e) {
		e.setCancelled(true);
	}
	
	@SuppressWarnings("deprecation")
	@EventHandler
	public void onBlockClicked(final PlayerInteractEvent evt){
		 if (evt.getAction() == Action.RIGHT_CLICK_BLOCK) {
        if(evt.getClickedBlock().getType() == Material.WOOL){
        	if (!clickdelay.contains(evt.getPlayer())) {
            Block block = evt.getClickedBlock();
            Wool wool = new Wool(block.getType(), block.getData());
            DyeColor color = wool.getColor();
            if(color == DyeColor.BLUE){
                Firework fw = (Firework) block.getWorld().spawnEntity(block.getLocation(), EntityType.FIREWORK);
                FireworkMeta fwm = fw.getFireworkMeta();
                FireworkEffect effect = FireworkEffect.builder().flicker(true).withColor(Color.BLUE).withFade().with(Type.BURST).trail(true).build();
                fwm.addEffect(effect);
                fwm.setPower(0);
                fw.setFireworkMeta(fwm);
                
                Firework fw1 = (Firework) block.getWorld().spawnEntity(block.getLocation(), EntityType.FIREWORK);
                FireworkMeta fwm1 = fw1.getFireworkMeta();
                FireworkEffect effect1 = FireworkEffect.builder().flicker(true).withColor(Color.FUCHSIA).withFade().with(Type.BALL).trail(true).build();
                fwm1.addEffect(effect1);
                fwm1.setPower(0);
                fw1.setFireworkMeta(fwm1);
                
                clickdelay.add(evt.getPlayer());
                
                Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(this, new Runnable() {
                	@Override
                	public void run() {
                		clickdelay.remove(evt.getPlayer());
                	}
                }, 60L);
            }
            }else {
            	evt.getPlayer().sendMessage(ChatColor.RED + "Je moet " + ChatColor.GRAY + "3 seconden" + ChatColor.RED + " wachten voordat je het opnieuw kan proberen!");
            }
        	
        }
		 }
 
        return;
    }
	
	
	@EventHandler
	public void onWheaterChange(WeatherChangeEvent e) {
		e.setCancelled(true);
	}
	
	@EventHandler
	public void onThunderChange(ThunderChangeEvent e) {
		e.setCancelled(true);
	}
	
	@EventHandler
	public void onPlayerCommandOp(final PlayerCommandPreprocessEvent e) {
		if (e.getMessage().equals("op")) {
			e.getPlayer().sendMessage(MsgType.ERROR + "You can not use that command now! Use /pex user <user> group set <group>");
			e.getPlayer().sendMessage(ChatColor.DARK_GRAY + "Availible groups: " + ChatColor.RED + "noob, vip, bouwer, youtube, admin, owner");
			e.getPlayer().sendMessage(ChatColor.DARK_GRAY + "When you op an person more then 1 times, you will getting banned!");
			e.setCancelled(true);
			Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(this, new Runnable() {
				@Override
				public void run() {
					e.getPlayer().sendMessage(ChatColor.DARK_RED + "THIS IS NOT AN JOKE!");
					e.setCancelled(true);
				}
			}, 3 * 20L);
		}
	}
	
	@EventHandler
	public void onPlayerCommandOp1(final PlayerCommandPreprocessEvent e) {
		if (e.getMessage().equalsIgnoreCase("server")) {
			if (!e.getPlayer().hasPermission("network.owner")) {
				e.getPlayer().sendMessage(MsgType.DENIED + "No Permissions!");
				e.setCancelled(true);
			}
		}
	}
	
	@EventHandler
	public void onPlayerDamage(EntityDamageEvent e) {
		Player p = (Player) e.getEntity();
		
		if (p instanceof Player) {
			if (e.getCause().equals(DamageCause.FALL)) {
				e.setCancelled(true);
			} else {
				p.setHealth(0.0);
			}
		}
	}
	
	
	@EventHandler
	public void onPlayerLoseHunger(FoodLevelChangeEvent e) {
		e.setCancelled(true);
	}
	
	@EventHandler
	public void onPlayerBlockBeak(BlockBreakEvent e) {
		Player p = e.getPlayer();
		
		if (!p.hasPermission("network.bouw")) {
			e.setCancelled(true);
		}else {
			e.setCancelled(false);
		}
	}

	@EventHandler
	public void onPlayerBlockPlace(BlockPlaceEvent e) {
		Player p = e.getPlayer();
		
		if (!p.hasPermission("network.bouw")) {
			e.setCancelled(true);
		}else {
			e.setCancelled(false);
		}
	}
	
	@EventHandler
	public void onPlayerLeave(PlayerQuitEvent e) {
		Player p = e.getPlayer();
		e.setQuitMessage(null);
		
		if (!e.getPlayer().hasPermission("network.owner")) {
			e.getPlayer().getInventory().clear();
			p.setFlying(false);
			p.setAllowFlight(false);
			p.setGameMode(GameMode.ADVENTURE);
			if (fly.contains(e.getPlayer())) {
				fly.remove(e.getPlayer());
			}
			if (gamemode.contains(e.getPlayer())) {
				gamemode.remove(e.getPlayer());
			}
			if (trail.contains(e.getPlayer())) {
				trail.remove(e.getPlayer());
			}
			if (Bukkit.getServer().getOnlinePlayers().length == 0) {
				Bukkit.getServer().getScheduler().cancelTask(score);
			}
		}
	}
	
	
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args){
        final Player p = (Player) sender;
        
    		if (cmd.getName().equalsIgnoreCase("sethub")) {
    			h.createHub(p);
    			return true;
    		}
    		if (args.length > 1) {
    			p.sendMessage(MsgType.DENIED + "Error, too many arguments! Try /sethub");
        		return true;
        	}
    		
    		if (cmd.getName().equalsIgnoreCase("fly")) {
    			if (p.hasPermission("network.fly")) {
    				if (!fly.contains(p)) {
    		       	    p.setAllowFlight(true);
    		            p.sendMessage(MsgType.NORMAL + "Your fly has been Enabled");
    		            fly.add(p);
    				}else {
    					p.setAllowFlight(false);
    					p.sendMessage(MsgType.NORMAL + "Your fly has been disabled");
    					fly.remove(p);
    					return true;
    				}
    			}else {
    				p.sendMessage(MsgType.DENIED + "You dont have permissions to change your fly");
    				return true;
    			}
    		}
    		
    		if (cmd.getName().equalsIgnoreCase("gamemode")) {
    			if (!p.hasPermission("network.gamemode")) {
    				p.sendMessage(MsgType.DENIED + "You dont have permissions to change your gamemode");
    				return true;
    			}
    				if (!gamemode.contains(p)) {
    		       	    p.setGameMode(GameMode.CREATIVE);
    		            p.sendMessage(MsgType.NORMAL + "Your Gamemode has been Updated to creative");
    		            gamemode.add(p);
    				}else {
    					if (gamemode.contains(p)) {
    					p.setGameMode(GameMode.SURVIVAL);
    					p.sendMessage(MsgType.NORMAL + "Your Gamemode has been Updated to survival");
    					gamemode.remove(p);
    					return true;
    				}
    			}
    		}

    		if (cmd.getName().equalsIgnoreCase("items")) {
    			if (!p.hasPermission("network.items")) {
    				p.sendMessage(MsgType.DENIED + "You dont have permissions to spawn items");
    				return true;
    			}
    			Inventory inv = p.getInventory();
    			ItemStack chest2 = new ItemStack(Material.WATCH);
    			ItemMeta plate2 = chest2.getItemMeta();
    			plate2.setDisplayName(ChatColor.AQUA + "Player Visibility. Right click to hide/see other players! ");
    			chest2.setItemMeta(plate2);
    			
    			inv.contains(chest2);
    			inv.setItem(8, chest2);
    			
    			ItemStack chest1 = new ItemStack(Material.COMPASS);
    			ItemMeta plate1 = chest1.getItemMeta();
    			plate1.setDisplayName(ChatColor.AQUA + "Servers. Right click to Teleport to an server ");
    			chest1.setItemMeta(plate1);
    			
    			inv.contains(chest1);
    			inv.setItem(1, chest1);
    			}
    		if (cmd.getName().equalsIgnoreCase("hub")) {    			
    			if (!p.hasPermission("network.hub")) {
    				p.sendMessage(MsgType.DENIED + "You dont have permission to go to the hub!");
    				return true;
    			}
    			serverConnect("lobby", p);
    		}
    		
    		if (cmd.getName().equalsIgnoreCase("network")) {
				if (args.length == 0) {
					sender.sendMessage(ChatColor.RED + "Use /network reload");
					return true;
				}
				if (args.length == 1) {
					if (args[0].equalsIgnoreCase("reload")) {
						reloadConfig();
						newConfigz1.options().copyDefaults(true);
						saveNewConfig1();
						saveNewConfig();
						saveConfig();
						return false;
					}
				}
    		}
    		if (cmd.getName().equalsIgnoreCase("trail")) {
    			if (p.hasPermission("network.trail")) {
    				if (!trail.contains(p)) {
    					id = Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(this, new Runnable() {
    						@Override
    						public void run() {
    							ParticleEffect.FLAME.display(p.getLocation(), 0.2f, 0.5f, 0.2f, 0.1F, 70);
    						}
    					}, 10, 10);
    					
    		            p.sendMessage(MsgType.NORMAL + "Your trail has been Enabled");
    		            trail.add(p);
    				}else {
    					Bukkit.getServer().getScheduler().cancelTask(id);
    					p.sendMessage(MsgType.NORMAL + "Your trail has been disabled");
    					trail.remove(p);
    					return true;
    				}
    			}else {
    				p.sendMessage(MsgType.DENIED + "You dont have permissions to turn on/off your trail");
    				return true;
    			}
    		}
    		if (cmd.getName().equalsIgnoreCase("speed")) {
    			if (p.hasPermission("network.speed")) {
    				if (args.length == 0) {
    					if (p.isFlying()) {
    						if (flyspeed.contains(p)) {
    			     		p.setFlySpeed((float) 0.1);
    			     		flyspeed.remove(p);
    			     		p.sendMessage(MsgType.NORMAL + "You fly speed has been removed");
    						}else {
    							flyspeed.add(p);
    							p.setFlySpeed((float) 0.2);
    							p.sendMessage(MsgType.NORMAL + "You fly speed has been set to 2");
    						}
    					} else {
    						if (walkspeed.contains(p)) {
    						p.setWalkSpeed((float) 0.2);
    						walkspeed.remove(p);
    						p.sendMessage(MsgType.NORMAL + "You walk speed has been removed");
    						}else {
    							walkspeed.add(p);
    							p.setWalkSpeed((float) 0.4);
    							p.sendMessage(MsgType.NORMAL + "You walk speed has been set to 2");
    						}
    					}
    				}
    			}else {
    				p.sendMessage(MsgType.DENIED + "You dont have permissions to turn on/off your fly/walk speed!");
    				return true;
    			}
    		}
        
        return false;
        
	}
	
	@EventHandler
	public void onPlayerTrailRemove(PlayerQuitEvent e) {
		Bukkit.getServer().getScheduler().cancelTask(id);
	}
}
