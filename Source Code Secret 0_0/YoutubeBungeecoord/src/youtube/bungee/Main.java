package youtube.bungee;

import net.md_5.bungee.api.plugin.Plugin;

public class Main extends Plugin{
	
	public void onEnable() {
		getProxy().getPluginManager().registerCommand(this, new PingPongCommand());
	}

}
