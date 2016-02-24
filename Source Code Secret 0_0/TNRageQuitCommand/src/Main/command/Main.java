package Main.command;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.plugin.messaging.PluginMessageListener;

import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;


public class Main extends JavaPlugin implements PluginMessageListener{
	
	public void onEnable() {
    	this.getServer().getMessenger().registerOutgoingPluginChannel(this, "BungeeCord");
        this.getServer().getMessenger().registerIncomingPluginChannel(this, "BungeeCord", this);
	}
	
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args){
		Player p = (Player) sender;
		if (cmd.getName().equalsIgnoreCase("ragequit")) {
			serverConnect("lobby", p);
			Bukkit.broadcastMessage(ChatColor.GRAY + p.getName() + ChatColor.DARK_AQUA + "" + ChatColor.BOLD + " has ragequit the The-Network! And has been sent to the lobby!");
			return true;
		}
		return false;
	}
	
	public void serverConnect(String server, Player p) {
    	ByteArrayDataOutput out = ByteStreams.newDataOutput();
        out.writeUTF("Connect");
        out.writeUTF(server);
		p.sendPluginMessage(this, "BungeeCord", out.toByteArray());
    }
	
	 @Override
	  public void onPluginMessageReceived(String channel, Player player, byte[] message) {
	    if (!channel.equals("BungeeCord")) {
	      return;
	    }
	    ByteArrayDataInput in = ByteStreams.newDataInput(message);
	    String subchannel = in.readUTF();
	    if (subchannel.equals("SomeSubChannel")) {
	      // Use the code sample in the 'Response' sections below to read
	      // the data.
	    }
	}

}
