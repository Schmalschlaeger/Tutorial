package mainp.thenetwork;

import mainp.thenetwork.extras.Maintenance;
import mainp.thenetwork.lobby.CommandManager;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class MainManager extends JavaPlugin{
	
	private String worldLobby = "world";
	private String worldSurvivalLobby = "GeneratedWorld";
	private String worldSurvival = "world";
	private String worldSurvivalNether = "world_the_nether";
	private String worldSurvivalEnd = "world_the_end";
	private String worldSpeedrun = "world";
	
	public void onEnable() {
		Bukkit.getServer().getPluginManager().registerEvents(new CommandManager(), this);
		Bukkit.getServer().getPluginManager().registerEvents(new Listeners(), this);
		Bukkit.getServer().getPluginManager().registerEvents(new Maintenance(this), this);
	}
	/**
     * Get the config file
     * @return world lobby file
     */
	public String getLobbyWorld() {
		return worldLobby;
	}
	/**
     * Get the config file
     * @return world survival file
     */
	public String getSurvivalWorld() {
		return worldSurvival;
	}
	/**
     * Get the config file
     * @return world survival nether file
     */
	public String getSurvivalWorldNether() {
		return worldSurvivalNether;
	}
	/**
     * Get the config file
     * @return world survival end file
     */
	public String getSurvivalWorldEnd() {
		return worldSurvivalEnd;
	}
	/**
     * Get the config file
     * @return world speedrun file
     */
	public String getSpeedrunWorld() {
		return worldSpeedrun;
	}
	/**
     * Get the config file
     * @return world survival lobby file
     */
	public String getSurvivalLobbyWorld() {
		return worldSurvivalLobby;
	}

}
