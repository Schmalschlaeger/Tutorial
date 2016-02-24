package simple.essentials.Commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import simple.essentials.Utils.MessageManager.MsgType;

public class HelpCommand implements CommandExecutor {
	
	public static String infoBotMessage = "             " + ChatColor.AQUA + "◄" + ChatColor.DARK_AQUA + "DemaciaCraft - InfoBot" + ChatColor.AQUA + "►";
	
	@Override
	public boolean onCommand(CommandSender s, Command cmd, String CommandLabel, String[] args) {
		if (s instanceof Player) {
			Player p = (Player) s;
		if (cmd.getName().equalsIgnoreCase("help")) {
			if (args.length == 0) {
				p.sendMessage("             " + ChatColor.AQUA + "◄" + ChatColor.DARK_AQUA + "DemaciaCraft - InfoBot" + ChatColor.AQUA + "►");
				p.sendMessage(ChatColor.GOLD + "First of all, welcome " + p.getName() + " on DemaciaCraft. We hope you enjoy your stay and of course we hope you make a"
						+ " lot of fun ;)");
				p.sendMessage(ChatColor.GOLD + "I know you don't understand all of our systems or commands and it is hard to understand if you don't get info. "
						+ "So, im here to help you. I will always stay with you and helping you out if you are stuck.");
				p.sendMessage(ChatColor.GOLD + " Use " + ChatColor.RED + "/infobot" + ChatColor.GOLD + " for more info."
						+ " And you can also turn me off if you are better then me with " + ChatColor.RED + "/infobot disable");
				p.sendMessage(ChatColor.GOLD + "My creator has made a list of help options to understand basic stuff. See below");
				p.sendMessage("    ");
				p.sendMessage(ChatColor.GOLD + "" + ChatColor.DARK_AQUA + "See all commands with: " + ChatColor.RED + "/help commands");
				p.sendMessage(ChatColor.GOLD + "" + ChatColor.DARK_AQUA + "See all systems info with: " + ChatColor.RED + "/help systems");
				p.sendMessage(ChatColor.GOLD + "" + ChatColor.DARK_AQUA + "See all servers info with: " + ChatColor.RED + "/help servers");
				p.sendMessage("    ");
				return true;
			}
			if (args.length == 1) {
				if (args[0].equalsIgnoreCase("commands")) {
					p.sendMessage(infoBotMessage);
					if (p.isOp()) {
					p.sendMessage(ChatColor.GOLD + "Here are all the commands for " + ChatColor.RED + "OP");
					p.sendMessage(ChatColor.DARK_RED + "You can do EVERYTHING because you are OP.");
					p.sendMessage(ChatColor.GOLD + "But here are the all of the commands for this plugin!");
					p.sendMessage(ChatColor.DARK_AQUA + "");
					p.sendMessage(ChatColor.DARK_AQUA + "/infobot" + ChatColor.WHITE + " - " + ChatColor.GOLD + "Shows info for info bot");
					p.sendMessage(ChatColor.DARK_AQUA + "/gm 0/1/2/3 [player]" + ChatColor.WHITE + " - " + ChatColor.GOLD + "Sets your gamemode");
					p.sendMessage(ChatColor.DARK_AQUA + "/fly [player]" + ChatColor.WHITE + " - " + ChatColor.GOLD + "Toggle fly");
					p.sendMessage(ChatColor.DARK_AQUA + "/kick <player> <reason>" + ChatColor.WHITE + " - " + ChatColor.GOLD + "Kicks a player");
					p.sendMessage(ChatColor.DARK_AQUA + "/tp <player> [player]" + ChatColor.WHITE + " - " + ChatColor.GOLD + "Teleport yourself of someone else");
					p.sendMessage(ChatColor.DARK_AQUA + "/ban <player> <reason>" + ChatColor.WHITE + " - " + ChatColor.GOLD + "Bans a player");
					p.sendMessage(ChatColor.DARK_AQUA + "/unban <player> <reason>" + ChatColor.WHITE + " - " + ChatColor.GOLD + "Unbans a player");
					p.sendMessage(ChatColor.DARK_AQUA + "/day" + ChatColor.WHITE + " - " + ChatColor.GOLD + "Make it day");
					p.sendMessage(ChatColor.DARK_AQUA + "/sun" + ChatColor.WHITE + " - " + ChatColor.GOLD + "Make it stop the rain");
					p.sendMessage(ChatColor.DARK_AQUA + "/night" + ChatColor.WHITE + " - " + ChatColor.GOLD + "Make it night");
					p.sendMessage(ChatColor.DARK_AQUA + "/warp [warpname]" + ChatColor.WHITE + " - " + ChatColor.GOLD + "See all warps, and teleport");
					p.sendMessage(ChatColor.DARK_AQUA + "/createwarp <warpname>" + ChatColor.WHITE + " - " + ChatColor.GOLD + "Create a warp");
					p.sendMessage(ChatColor.DARK_AQUA + "/delwarp <warpname>" + ChatColor.WHITE + " - " + ChatColor.GOLD + "Delete a warp");
					p.sendMessage(ChatColor.DARK_AQUA + "/heal [player]" + ChatColor.WHITE + " - " + ChatColor.GOLD + "Heals yourself");
					p.sendMessage(ChatColor.DARK_AQUA + "/feed [player]" + ChatColor.WHITE + " - " + ChatColor.GOLD + "Feed yourself");
					p.sendMessage(ChatColor.DARK_AQUA + "/msg <player> <message>" + ChatColor.WHITE + " - " + ChatColor.GOLD + "Send a private message");
					p.sendMessage(ChatColor.DARK_AQUA + "/watch <player>" + ChatColor.WHITE + " - " + ChatColor.GOLD + "Watch a player");
					p.sendMessage(ChatColor.DARK_AQUA + "/tpall" + ChatColor.WHITE + " - " + ChatColor.GOLD + "Teleport everyone");
					p.sendMessage(ChatColor.DARK_AQUA + "/broadcast" + ChatColor.WHITE + " - " + ChatColor.GOLD + "Broadcast a message in this server");
					p.sendMessage(ChatColor.DARK_AQUA + "/report <player>" + ChatColor.WHITE + " - " + ChatColor.GOLD + "Report a player");
					}else {
						p.sendMessage(ChatColor.GOLD + "Here are all the commands for " + ChatColor.RED + "a normal player");
						p.sendMessage(ChatColor.DARK_AQUA + "");
						p.sendMessage(ChatColor.DARK_AQUA + "/infobot" + ChatColor.WHITE + " - " + ChatColor.GOLD + "Shows info for info bot");
						p.sendMessage(ChatColor.DARK_AQUA + "/msg <player> <message>" + ChatColor.WHITE + " - " + ChatColor.GOLD + "Send a private message");
						p.sendMessage(ChatColor.DARK_AQUA + "/report <player>" + ChatColor.WHITE + " - " + ChatColor.GOLD + "Report a player");
						
					}
					return true;
				}
				if (args[0].equalsIgnoreCase("systems")) {
					p.sendMessage(MsgType.WARNING + "ERROR: Nothing here yet!");
					return true;
				}
			}
			if (args.length > 1) {
				p.sendMessage(MsgType.WARNING + "Sorry, no help argument found! Try /help");
				return true;
			}
			return true;
		}
		}
		return false;
	}

}
