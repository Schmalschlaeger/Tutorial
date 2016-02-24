package shop;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

import shop.MessageManager.MsgType;

public class Events implements Listener, CommandExecutor {
    
	private Shop plugin;
	 
	public Events(Shop instance) {
	    this.plugin = instance;
	}
	
    public int id;
    
    public void setId(int id) {
        this.id = id;
    }
	
    public void startTimer() {
    	Shop.eventTimer = 60;
 		id = Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(plugin, new Runnable() {
 		@Override
 		public void run() {
 			if (Shop.eventTimer > 0) {
 				Shop.eventTimer--;
 				switch (Shop.eventTimer) {
 				case 50:
 					Bukkit.getServer().broadcastMessage(MsgType.NORMAL + "50 seconds before it starts!");
 					
 					break;
 				case 30:
 					Bukkit.getServer().broadcastMessage(MsgType.NORMAL + "30 seconds before it starts!");
 					break;
 				case 10:
 					Bukkit.getServer().broadcastMessage(MsgType.NORMAL + "10 seconds before it starts!");
 					break;
 				case 5:
 					Bukkit.broadcastMessage(MsgType.NORMAL + "5 seconds before it starts!");
 					break;
 				case 4:
 					Bukkit.broadcastMessage(MsgType.NORMAL + "4 seconds before it starts!");
 					break;
 				case 3:
 					Bukkit.broadcastMessage(MsgType.NORMAL + "3 seconds before it starts!");
 					break;
 				case 2:
 					Bukkit.broadcastMessage(MsgType.NORMAL + "2 seconds before it starts!");
 					break;
 				case 1:
 					Bukkit.getServer().broadcastMessage(MsgType.NORMAL + "1 second before it starts!");
 					break;   
 				}
 				}else {
 					Bukkit.getServer().getScheduler().cancelTask(id);
     				Bukkit.getServer().broadcastMessage(MsgType.NORMAL + "Event Started!!");
     				
 				}
 		}
 			}, 0L, 20L);
 	}
	
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args){
		Player p = (Player) sender;
		if (cmd.getName().equalsIgnoreCase("event")) {
			if (args.length == 0) {
				p.sendMessage(MsgType.NORMAL + "Wrong type arguments! Try /event start [type]");
				p.sendMessage(ChatColor.RED + "If you dont know what the types are? Types: " + ChatColor.BLUE + "dropparty, ?, ?, ?");
				return true;
			}
			if (args.length == 1) {
				if (args[0].equalsIgnoreCase("start")) {
					p.sendMessage(MsgType.NORMAL + "Wrong type arguments! Try /event start [type]");
					p.sendMessage(ChatColor.RED + "If you dont know what the types are? Types: " + ChatColor.BLUE + "dropparty, ?, ?, ?");
					return true;
				}
			}
				if (args.length == 2) {
					if (args[1].equalsIgnoreCase("dropparty")) {
						Bukkit.getServer().broadcastMessage("[" + ChatColor.GRAY + "TheNetwork" + ChatColor.WHITE + "]" + ChatColor.RED + " There is an" + ChatColor.BLUE + " DropParty" + ChatColor.RED + " starting!");
						Bukkit.getServer().broadcastMessage(ChatColor.RED + "Event starting in 1 minut! Join with " + ChatColor.GREEN + "/warp dropparty" + ChatColor.RED + "!");
						startTimer();
						return true;
					}else {
						p.sendMessage(MsgType.NORMAL + "Could not find the event: " + ChatColor.RED + args[1]);
						return true;
					}
				}
			}
		return false;
		
	}

}
