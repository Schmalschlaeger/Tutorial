package simple.essentials.Commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import simple.essentials.Utils.MessageManager.MsgType;

public class HealCommand implements CommandExecutor {
	
	@SuppressWarnings("deprecation")
	@Override
	public boolean onCommand(CommandSender s, Command cmd, String CommandLabel, String[] args) {
		 if (s instanceof Player) {
			 Player p = (Player) s;
			 if (cmd.getName().equalsIgnoreCase("heal")) {
				 if (args.length == 0) {
					 p.setHealth(20.0);
					 p.setFoodLevel(20);
					 p.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 1, 999999999));
					 p.sendMessage(MsgType.SENDMESSAGE + "Succesfull healed yourself!");
					 return true;
				 }
				 if (args.length == 1) {
					 Player target = Bukkit.getServer().getPlayer(args[0]);
						if (target == null) {
							p.sendMessage(MsgType.WARNING + "We cant find player " + args[0] + " for you. Im sorry!");
							return true;
						}
						if (target.getName() == p.getName()) {
							p.sendMessage(MsgType.WARNING + "Did you know you can type /heal for yourself ;)");
						}
						target.setHealth(20.0);
						target.setFoodLevel(20);
						target.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 99999999, 2));
						p.sendMessage(MsgType.SENDMESSAGE + "Succesfull healed " + ChatColor.RED + target.getName());
						return true;
				 }
			 }
			 if (cmd.getName().equalsIgnoreCase("feed")) {
				 if (args.length == 0) {;
					 p.setFoodLevel(20);
					 p.sendMessage(MsgType.SENDMESSAGE + "Succesfull feed yourself!");
					 return true;
				 }
				 if (args.length == 1) {
					 Player target = Bukkit.getServer().getPlayer(args[0]);
						if (target == null) {
							p.sendMessage(MsgType.WARNING + "We cant find player " + args[0] + " for you. Im sorry!");
							return true;
						}
						if (target.getName() == p.getName()) {
							p.sendMessage(MsgType.WARNING + "Did you know you can type /feed for yourself ;)");
						}
						target.setHealth(20.0);
						target.setFoodLevel(20);
						target.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 99999999, 2));
						p.sendMessage(MsgType.SENDMESSAGE + "Succesfull healed " + ChatColor.RED + target.getName());
						return true;
				 }
			 }
		 }
		return false;
	}

}
