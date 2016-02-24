package mainp.thenetwork.extras;

import mainp.thenetwork.MainManager;

import org.bukkit.entity.Player;

import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;

public class BungeeCord {
	
	private MainManager plugin;
	 
	public BungeeCord(MainManager instance) {
	    this.plugin = instance;
	}
	
	public void connectToServer(String server, Player p) {
    	ByteArrayDataOutput out = ByteStreams.newDataOutput();
        out.writeUTF("Connect");
        out.writeUTF(server);
		p.sendPluginMessage(plugin, "BungeeCord", out.toByteArray());
    }

}
