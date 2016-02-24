package simple.essentials.Commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import simple.essentials.Main;
import simple.essentials.Utils.MessageManager.MsgType;


public class GamemodeCommand implements CommandExecutor{
	
	 @SuppressWarnings("deprecation")
	@Override
		public boolean onCommand(CommandSender s, Command cmd, String CommandLabel, String[] args) {
		 if (s instanceof Player) {
			 Player p = (Player) s;
			 if (cmd.getName().equalsIgnoreCase("gamemode")) {
				 if (p.hasPermission(Main.gamemodePerms)) {
				 if (args.length == 0) {
					 p.sendMessage(MsgType.NO_PERMS + "Use /gm 0/1/2/3 or /gamemode c/s/a/spec");
					 return true;
				 }
				 if (args.length == 1) {
					 if (args[0].equalsIgnoreCase("creative") || args[0].equalsIgnoreCase("c") || args[0].equalsIgnoreCase("1")) {
						 if (p.getGameMode().equals(GameMode.CREATIVE)) {
							 p.sendMessage(MsgType.WARNING + "You are already in this gamemode!");
							 return true;
						 }
						 p.setGameMode(GameMode.CREATIVE);
						 p.sendMessage(MsgType.SENDMESSAGE + "Changed gamemode to Creative!");
						 return true;
					 }else if (args[0].equalsIgnoreCase("survival") || args[0].equalsIgnoreCase("s") || args[0].equalsIgnoreCase("0")) {
						 if (p.getGameMode().equals(GameMode.SURVIVAL)) {
							 p.sendMessage(MsgType.WARNING + "You are already in this gamemode!");
							 return true;
						 }
						 p.setGameMode(GameMode.SURVIVAL);
						 p.sendMessage(MsgType.SENDMESSAGE + "Changed gamemode to Survival!");
						 return true;
					 }else if (args[0].equalsIgnoreCase("adventure") || args[0].equalsIgnoreCase("a") || args[0].equalsIgnoreCase("2")) {
						 if (p.getGameMode().equals(GameMode.ADVENTURE)) {
							 p.sendMessage(MsgType.WARNING + "You are already in this gamemode!");
							 return true;
						 }
						 p.setGameMode(GameMode.ADVENTURE);
						 p.sendMessage(MsgType.SENDMESSAGE + "Changed gamemode to Adventure!");
						 return true;
					 }else if (args[0].equalsIgnoreCase("spectator") || args[0].equalsIgnoreCase("spec") || args[0].equalsIgnoreCase("3")) {
						 if (p.getGameMode().equals(GameMode.SPECTATOR)) {
							 p.sendMessage(MsgType.WARNING + "You are already in this gamemode!");
							 return true;
						 }
						 p.setGameMode(GameMode.SPECTATOR);
						 p.sendMessage(MsgType.SENDMESSAGE + "Changed gamemode to Spectator!");
						 return true;
					 }
				 }
				 if (args.length == 2) {
					 Player target = Bukkit.getServer().getPlayer(args[1]);
						if (target == null) {
							p.sendMessage(MsgType.WARNING + "We cant find player " + args[0] + " for you. Im sorry!");
							return true;
						}
						if (target.getName() == p.getName()) {
							p.sendMessage(MsgType.WARNING + "We have changed your gamemode, but in the future use /gamemode");
						}
						
						
						
					 if (args[0].equalsIgnoreCase("creative") || args[0].equalsIgnoreCase("c") || args[0].equalsIgnoreCase("1")) {
						 if (target.getGameMode().equals(GameMode.CREATIVE)) {
							 p.sendMessage(MsgType.WARNING + target.getName() + " is are already in this gamemode!");
							 return true;
						 }
						 target.setGameMode(GameMode.CREATIVE);
						 p.sendMessage(MsgType.SENDMESSAGE + "Changed " + ChatColor.RED + target.getName() + "'s" + ChatColor.GOLD + " gamemode to Creative!");
						 target.sendMessage(MsgType.SENDMESSAGE + "Your gamemode has been updated to creative!");
						 return true;
					 }else if (args[0].equalsIgnoreCase("survival") || args[0].equalsIgnoreCase("s") || args[0].equalsIgnoreCase("0")) {
						 if (target.getGameMode().equals(GameMode.SURVIVAL)) {
							 p.sendMessage(MsgType.WARNING + target.getName() + " is are already in this gamemode!");
							 return true;
						 }
						 target.setGameMode(GameMode.SURVIVAL);
						 p.sendMessage(MsgType.SENDMESSAGE + "Changed " + ChatColor.RED + target.getName() + "'s" + ChatColor.GOLD + " gamemode to Survival!");
						 target.sendMessage(MsgType.SENDMESSAGE + "Your gamemode has been updated to Survival!");
						 return true;
					 }else if (args[0].equalsIgnoreCase("adventure") || args[0].equalsIgnoreCase("a") || args[0].equalsIgnoreCase("2")) {
						 if (p.getGameMode().equals(GameMode.ADVENTURE)) {
							 p.sendMessage(MsgType.WARNING + target.getName() + " is are already in this gamemode!");
							 return true;
						 }
						 target.setGameMode(GameMode.ADVENTURE);
						 p.sendMessage(MsgType.SENDMESSAGE + "Changed " + ChatColor.RED + target.getName() + "'s" + ChatColor.GOLD + " gamemode to Adventure!");
						 target.sendMessage(MsgType.SENDMESSAGE + "Your gamemode has been updated to Adventure!");
						 return true;
					 }else if (args[0].equalsIgnoreCase("spectator") || args[0].equalsIgnoreCase("spec") || args[0].equalsIgnoreCase("3")) {
						 if (p.getGameMode().equals(GameMode.SPECTATOR)) {
							 p.sendMessage(MsgType.WARNING + target.getName() + " is are already in this gamemode!");
							 return true;
						 }
						 target.setGameMode(GameMode.SPECTATOR);
						 p.sendMessage(MsgType.SENDMESSAGE + "Changed " + ChatColor.RED + target.getName() + "'s" + ChatColor.GOLD + " gamemode to Spectator!");
						 target.sendMessage(MsgType.SENDMESSAGE + "Your gamemode has been updated to Spectator!");
						 return true;
					 }
				 }
			 }
		 }
		 }
		 return false;
	 }
}
