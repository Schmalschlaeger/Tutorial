package RealPVPGame.Addons;

import net.minecraft.util.com.google.common.io.ByteArrayDataOutput;
import net.minecraft.util.com.google.common.io.ByteStreams;

import org.bukkit.entity.Player;

import RealPVPGame.Main;

public class BungeeCoord {
	
	public Main plugin;
	
	public BungeeCoord(Main plugin) {
		this.plugin = plugin;
	}
	
	public void connectToServer(String server, Player p) {
    	ByteArrayDataOutput out = ByteStreams.newDataOutput();
        out.writeUTF("Connect");
        out.writeUTF(server);
		p.sendPluginMessage(plugin, "BungeeCord", out.toByteArray());
    }

}
