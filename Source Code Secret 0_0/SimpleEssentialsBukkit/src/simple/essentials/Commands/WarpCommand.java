package simple.essentials.Commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import simple.essentials.Main;
import simple.essentials.Utils.MessageManager.MsgType;
import simple.essentials.Utils.WarpUtil;

public class WarpCommand implements CommandExecutor {
	
	WarpUtil h = new WarpUtil();
	
    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args){
        if (!(sender instanceof Player)) {
        	sender.sendMessage("Sorry, but only players can use this command ingame! If this is an error, ask the developer!");
        	return true;
        }
        final Player p = (Player) sender;
        if (cmd.getName().equalsIgnoreCase("warp")) {
        	if (args.length == 0) {
        		if (p.hasPermission(Main.warpListPerms)) {
        		h.listWarps(p);
        		return true;
        		}else {
        			p.sendMessage(MsgType.NO_PERMS + "Sorry, no permissions for you!");
        			return true;
        		}
        	}
        	if (args.length >= 2) {
        		p.sendMessage(MsgType.WARNING + "Too many arguments. Try /warp <warpname>");
        		return true;
        	}
        	if (args.length == 1) {
        		//if (p.hasPermission(Main.warpCustomPerms)) {
        		h.teleportToWarp(p, args[0]);
        		return true;
        		}else {
        			p.sendMessage(MsgType.NO_PERMS + "Sorry, no permissions for this warp!");
        			return true;
        		}
        	//}
        }
        if (cmd.getName().equalsIgnoreCase("createwarp")) {
        	if (p.hasPermission(Main.warpPerms)) {
        	if (args.length == 0) {
        		p.sendMessage(MsgType.WARNING + "Not enough arguments. Try /createwarp <warpname>");
        		return true;
        	}
        	if (args.length == 1) {
        		h.createWarpy(p, args[0]);
        	}
        }
        }
        return false;
    }

}
