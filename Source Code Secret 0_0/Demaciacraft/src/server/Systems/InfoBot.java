package server.Systems;

import java.util.ArrayList;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

import server.Main;
import server.MessageManager.MsgType;
import server.Achievements.Achievements;
import server.Inventory.createItems;

public class InfoBot implements CommandExecutor, Listener{
	
	private Main plugin;
	
	public InfoBot(Main plugin) {
		this.plugin = plugin;
	}
	
	public static String infoBotMessage = "             " + ChatColor.AQUA + "◄" + ChatColor.DARK_AQUA + "DemaciaCraft - InfoBot" + ChatColor.AQUA + "►";
	private ArrayList<UUID> infoBotDisable = new ArrayList<UUID>();
	
	@SuppressWarnings("deprecation")
	@Override
		public boolean onCommand(CommandSender s, Command cmd, String CommandLabel, String[] args) {
		 if (s instanceof Player) {
			 final Player p = (Player) s;
			 if (cmd.getName().equalsIgnoreCase("infobot")) {
				 if (args.length == 0) {
				 p.sendMessage(infoBotMessage);
				 p.sendMessage(ChatColor.GOLD + "Hey " + p.getName() + " How u doing my friend? " + 
				 "Did you know i have watched everything you have done.");
				 p.sendMessage(ChatColor.GOLD + "But, im here to help and support you, so i will explain it as short as possible!" 
				 + ChatColor.GOLD + "Everything what you are doing, im gone help you. So if you need help to get to an server or something else. I am always with you." 
				 + ChatColor.GOLD + "If you look at" + ChatColor.DARK_AQUA + " slot 9" + ChatColor.GOLD + ", with that item you can ask me info.");
				 p.sendMessage(ChatColor.GOLD + "But, I can not do anything for you because I am sometimes grumpy or because I'm tired. But I try as much as possible to do for you!");
				 p.sendMessage("   ");
				 p.sendMessage(ChatColor.GOLD + "So, " + p.getName() + " i have to say it from this server. But you can disable me with " + ChatColor.RED + "/infobot disable");
				 p.sendMessage(ChatColor.GOLD + "REMEMBER! IF YOU DISABLE ME, MHUHAHAHA JUST SEE THE CONSEQUENCES.");
				 p.sendMessage("   ");
				 p.sendMessage(ChatColor.GOLD + "Oh yes, before i forget. You " + ChatColor.RED + "CAN'T" + ChatColor.GOLD + " enable me again. If you need me, just ask a staff.");
				 p.sendMessage(ChatColor.GOLD + "You ask why? Because if you leave me, we can't be friends anymore. Only my creator can let me make friends!");
				 p.sendMessage("   ");
				 }
				 if (args.length == 1) {
					 if (args[0].equalsIgnoreCase("disable")) {
						 if (isDisabled(p.getUniqueId()) == false) {
						 if (!infoBotDisable.contains(p.getUniqueId())) {
						 p.sendMessage(MsgType.SENDMESSAGE + "NOOOOOOOO, Why are you turning me off? Are you sure? Typ it again, i dont believe you. I give you 20 seconds!");
						 infoBotDisable.add(p.getUniqueId());
						 
						 Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
							 @Override
							 public void run() {
								 //Check if he is in arraylist
								 infoBotDisable.remove(p.getUniqueId());
								 p.sendMessage(MsgType.SENDMESSAGE + "I was right. You cant without me! Are are you thinking about it?");
							 	}
							 }, 20 * 20);
						 
						 }else {
						 p.sendMessage(MsgType.WARNING + "OMMGG, you think you are better than me. HAHAHA, just wait. Im gone ruin your life! HAHAHAHAHA YOUR GONE D....");
						 Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
							 @Override
							 public void run() {
								 infoBotDisable.remove(p.getUniqueId());
								 Main.infoBotConfig.set("players." + p.getUniqueId() + ".disabled", true);
								 Main.saveCustomPlayerConfig();
								 p.sendMessage(ChatColor.DARK_AQUA + "InfoBot " + ChatColor.GOLD + "succesfull disabled!");
							 }
						 }, 60);
						 return true;
						 } 
						 }else {
							 p.sendMessage(MsgType.WARNING + "Sorry im busy to ruin your life. Just wait for it....");
							 return true;
						 }
					 }
				 }
				 if (args.length == 2) {
					 if (args[0].equalsIgnoreCase("enable")) {
						 
						 Player target = Bukkit.getServer().getPlayer(args[1]);
							if (target == null) {
								p.sendMessage(MsgType.WARNING + "I cant find player " + args[0] + " for you. Im sorry!");
								return true;
							}
							if (InfoBot.isDisabled(target.getUniqueId()) == false) {
								p.sendMessage(MsgType.WARNING + "Sorry, but " + target.getName() + " hasn't disabled me!");
								return true;
							}
							Main.infoBotConfig.set("players." + p.getUniqueId() + ".disabled", false);
							Main.saveCustomPlayerConfig();
							p.sendMessage(ChatColor.DARK_AQUA + "InfoBot " + ChatColor.GOLD + "for player " + ChatColor.RED 
									+ target.getName() + ChatColor.GOLD + " enabled!");
							return true;
					 }
				 }
			 }
		 }
		 return false;
	 }
	
	
	public static boolean isDisabled(UUID uuid) {
		return Main.infoBotConfig.getBoolean("players." + uuid + ".disabled");
	}
	
	@SuppressWarnings("deprecation")
	public static void createInvMenuHoofdMenu(Player p, Inventory inv) {
		createItems.createCustomItem(p, inv, Material.getMaterial(160), (byte) 7, "[]", 0);
		createItems.createCustomItem(p, inv, Material.getMaterial(160), (byte) 7, "[]", 2);
		createItems.createCustomItem(p, inv, Material.getMaterial(160), (byte) 7, "[]", 4);
		createItems.createCustomItem(p, inv, Material.getMaterial(160), (byte) 7, "[]", 6);
		createItems.createCustomItem(p, inv, Material.getMaterial(160), (byte) 7, "[]", 8);
		createItems.createCustomItem(p, inv, Material.getMaterial(160), (byte) 7, "[]", 10);
		createItems.createCustomItem(p, inv, Material.getMaterial(160), (byte) 7, "[]", 16);
		createItems.createCustomItem(p, inv, Material.getMaterial(160), (byte) 7, "[]", 18);
		createItems.createCustomItem(p, inv, Material.getMaterial(160), (byte) 7, "[]", 20);
		createItems.createCustomItem(p, inv, Material.getMaterial(160), (byte) 7, "[]", 22);
		createItems.createCustomItem(p, inv, Material.getMaterial(160), (byte) 7, "[]", 24);
		createItems.createCustomItem(p, inv, Material.getMaterial(160), (byte) 7, "[]", 26);
		//###
		createItems.createCustomItem(p, inv, Material.getMaterial(160), (byte) 0, "[]", 1);
		createItems.createCustomItem(p, inv, Material.getMaterial(160), (byte) 0, "[]", 3);
		createItems.createCustomItem(p, inv, Material.getMaterial(160), (byte) 0, "[]", 5);
		createItems.createCustomItem(p, inv, Material.getMaterial(160), (byte) 0, "[]", 7);
		createItems.createCustomItem(p, inv, Material.getMaterial(160), (byte) 0, "[]", 9);
		createItems.createCustomItem(p, inv, Material.getMaterial(160), (byte) 0, "[]", 11);
		createItems.createCustomItem(p, inv, Material.getMaterial(160), (byte) 0, "[]", 15);
		createItems.createCustomItem(p, inv, Material.getMaterial(160), (byte) 0, "[]", 17);
		createItems.createCustomItem(p, inv, Material.getMaterial(160), (byte) 0, "[]", 19);
		createItems.createCustomItem(p, inv, Material.getMaterial(160), (byte) 0, "[]", 21);
		createItems.createCustomItem(p, inv, Material.getMaterial(160), (byte) 0, "[]", 23);
		createItems.createCustomItem(p, inv, Material.getMaterial(160), (byte) 0, "[]", 25);
		//###		
		createItems.createCustomItemLores(p, inv, Material.getMaterial(322), (byte) 1, ChatColor.GOLD + "Achievements", 12, ChatColor.GRAY + "See all your achievements", 
				ChatColor.GRAY + "Total done: " + ChatColor.RED + Integer.toString(Achievements.getAchievementFromUser(p)));
		createItems.createCustomItem(p, inv, Material.getMaterial(175), (byte) 14, ChatColor.GOLD + "Your friends", 14);

		createInfoHead(inv, p);
	}
	
	public static void createInfoHead(Inventory inv, Player p) {
		ItemStack skull = createItems.getPlayerHead(p.getName());
        
        SkullMeta meta = (SkullMeta) skull.getItemMeta();
        meta.setDisplayName(ChatColor.RED + p.getName() + "'s " + ChatColor.GOLD + "info");
        ArrayList<String> iron21 = new ArrayList<String>();
		iron21.add(ChatColor.DARK_AQUA + "Name: " + ChatColor.GOLD + p.getDisplayName());
		iron21.add(ChatColor.DARK_AQUA + "Rank: " + ChatColor.GRAY + Stats.getRank(p));
		iron21.add(ChatColor.DARK_AQUA + "Friends with InfoBot: " + ChatColor.RED + "NO");
		iron21.add(ChatColor.DARK_AQUA + "Friends: " + ChatColor.GOLD + "0");
		iron21.add(ChatColor.DARK_AQUA + "Achievements Completed: " + ChatColor.GOLD + Achievements.getAchievementFromUser(p));
		iron21.add(ChatColor.DARK_AQUA + "Lobby Coins: " + ChatColor.GOLD + "0");
		iron21.add(ChatColor.DARK_AQUA + "XP: " + ChatColor.GOLD + "0");
		meta.setLore(iron21);
        skull.setItemMeta(meta);
        
        inv.setItem(13, skull);
	}
	
	@EventHandler
	public void onPlayerClick(PlayerInteractEvent e) {
		Inventory menu = Bukkit.createInventory(null, 27, ChatColor.DARK_AQUA + "InfoBot " + ChatColor.GOLD + "Menu");
		createInvMenuHoofdMenu(e.getPlayer(), menu);
		
		Player p = e.getPlayer();
		ItemStack cl = e.getItem();
		if (p.getItemInHand() != null) {
			if (e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK) {
				if (p.getItemInHand().hasItemMeta() && p.getItemInHand().getItemMeta().hasDisplayName()) {
					if (cl.getType() == Material.SIGN) {
						e.setCancelled(true);
						p.openInventory(menu);
					}
    			}
        	}
		}
	}

}
