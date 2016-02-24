package hubcommand;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.api.plugin.Plugin;
import net.md_5.bungee.config.Configuration;
import net.md_5.bungee.config.ConfigurationProvider;
import net.md_5.bungee.config.YamlConfiguration;

import com.google.common.io.ByteStreams;

public class Main extends Plugin implements Listener{
	
	static Configuration configuration = null;
	
	public void onEnable() {
		try {
            configuration = ConfigurationProvider.getProvider(YamlConfiguration.class).load(
                    loadResource(this, "config.yml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
		ProxyServer.getInstance().getPluginManager().registerListener(this, this);
		getProxy().getPluginManager().registerCommand(this, new HubCommand());
	}
	
	public void saveConfig() {
		try {
			ConfigurationProvider.getProvider(YamlConfiguration.class).save(Main.configuration, new File(getDataFolder(), "config.yml"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	 public static File loadResource(Plugin plugin, String resource) {
	        File folder = plugin.getDataFolder();
	        if (!folder.exists())
	            folder.mkdir();
	        File resourceFile = new File(folder, resource);
	        try {
	            if (!resourceFile.exists()) {
	                resourceFile.createNewFile();
	                try (InputStream in = plugin.getResourceAsStream(resource);
	                     OutputStream out = new FileOutputStream(resourceFile)) {
	                    ByteStreams.copy(in, out);
	                }
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        return resourceFile;
	    }
	
	public static void send(ProxiedPlayer to, String server) {
	    to.connect(ProxyServer.getInstance().getServerInfo(server));
	}

}
