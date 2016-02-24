package youtube.bungee;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

public class PingPongCommand extends Command{

	public PingPongCommand() {
		super("ping");
	}

	@SuppressWarnings("deprecation")
	@Override
	public void execute(CommandSender sender, String[] args) {
		ProxiedPlayer p = (ProxiedPlayer) sender;
		if (p.hasPermission("ping.command")) {
			p.sendMessage(ChatColor.GOLD + "!PONG");
			return;
		}
	}
}
