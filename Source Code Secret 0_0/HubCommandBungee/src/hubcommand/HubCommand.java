package hubcommand;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

public class HubCommand extends Command{
	
	public HubCommand() {
		super("hub");
	}
	
	@SuppressWarnings("deprecation")
	@Override
	public void execute(CommandSender sender, String[] args) {
		ProxiedPlayer p = (ProxiedPlayer) sender;
		if (p.hasPermission("hub.denied")) {
			p.sendMessage(ChatColor.RED + "You dont have permissions!");
			return;
			}		
		Main.send(p, Main.configuration.getString("server"));
		return;
	}

}
