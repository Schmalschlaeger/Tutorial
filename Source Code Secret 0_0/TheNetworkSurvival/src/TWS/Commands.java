package TWS;

import net.minecraft.util.com.google.common.io.ByteArrayDataOutput;
import net.minecraft.util.com.google.common.io.ByteStreams;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Commands implements CommandExecutor {

	static Main plugin;

	public Commands(Main pl) {
		plugin = pl;
	}

	public boolean onCommand(CommandSender sender, Command cmd,
			String commandLabel, String[] args) {
		final Player p = (Player) sender;
		if (cmd.getName().equalsIgnoreCase("user")) {
			if (!p.hasPermission(Main.OwnerPerms)) {
				return true;
			}
			if (args.length == 0) {
				sender.sendMessage(ChatColor.RED
						+ "Unknown command! Use /user <name>");
				return true;
			}
			if (args.length > 0) {
				Player target = Bukkit.getPlayer(args[0]);
				if (target == null) {
					sender.sendMessage("Player not availible!");
					return true;
				}
				sender.sendMessage(ChatColor.GOLD
						+ "Here you have your info about " + ChatColor.GOLD
						+ target.getName());
				sender.sendMessage(ChatColor.GOLD + "Name: " + ChatColor.GRAY
						+ info.getName(target));
				sender.sendMessage(ChatColor.GOLD + "UUID: " + ChatColor.GRAY
						+ info.getUUIDFromPlayer(target));
				sender.sendMessage(ChatColor.GOLD + "Rank: " + ChatColor.GRAY
						+ info.getRank(target));
				sender.sendMessage(ChatColor.GOLD + "Faction: "
						+ ChatColor.GRAY + info.getFactionFromPlayer(target));
				sender.sendMessage(ChatColor.GOLD + "Money: " + ChatColor.GRAY
						+ info.getBalance(target));
				sender.sendMessage(ChatColor.GOLD + "Location: " + "X: "
						+ ChatColor.GRAY + target.getLocation().getBlockX()
						+ ChatColor.GOLD + " Y: " + ChatColor.GRAY
						+ target.getLocation().getBlockY() + ChatColor.GOLD
						+ " Z: " + ChatColor.GRAY
						+ target.getLocation().getBlockZ());
			}
		}
		if (cmd.getName().equalsIgnoreCase("ragequit")) {
			serverConnect("lobby", p);
			Bukkit.broadcastMessage(ChatColor.GRAY + p.getName() + ChatColor.DARK_AQUA + "" + ChatColor.BOLD + " has ragequit the The-Network Survival!?");
		}

		if (cmd.getName().equalsIgnoreCase("shop")) {
			// TODO: Openinventory shop p.openInventory(menu);
			return true;
		}
		return false;
	}

	public void serverConnect(String server, Player p) {
		ByteArrayDataOutput out = ByteStreams.newDataOutput();
		out.writeUTF("Connect");
		out.writeUTF(server);
		p.sendPluginMessage(plugin, "BungeeCord", out.toByteArray());
	}

}
