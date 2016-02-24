package me.jusjus112;

import java.io.File;

import org.bukkit.entity.Player;

public class setupConfig {
	
	private Main plugin;
	 
	public setupConfig(Main instance) {
	    this.plugin = instance;
	}
	
	private File dir;
	public Config c;
	
	public void setUpPerPlayerConfig(Player p) {
		dir = new File(plugin.getDataFolder() + File.separator + "players");
		c = new Config(dir, p.getName(), plugin);

		setConfigPerPlayer("House1", false);
		setConfigPerPlayer("House2", false);
		setConfigPerPlayer("House3", false);
		setConfigPerPlayer("House4", false);
	}

	public boolean getBoolean(String name) {
		if (c.getConfig().get(name) == null) {
			return false;
		} else {
			return c.getConfig().getBoolean(name);
		}
	}

	public void updateConfigPerPlayer(String name, Object obj) {
		c.getConfig().set(name, obj);
		c.save();
	}

	public void setConfigPerPlayer(String name, Object obj) {
		String str = c.getConfig().getString(name);
		if (str == null) {
			c.set(name, obj);
			c.save();
		}
	}

}
