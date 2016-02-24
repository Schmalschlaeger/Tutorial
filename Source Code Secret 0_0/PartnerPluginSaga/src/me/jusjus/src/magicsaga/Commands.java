package me.jusjus.src.magicsaga;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.command.BlockCommandSender;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.FallingBlock;
import org.bukkit.util.Vector;

public class Commands implements CommandExecutor {
	
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
    if (cmd.getName().equalsIgnoreCase("fallingblock")) {
    	//p.sendMessage("DEBUG: COMMAND WORKING");
    	if (sender instanceof ConsoleCommandSender || sender instanceof BlockCommandSender) {
    	if (!(args.length < 7)) {
    		//p.sendMessage("DEBUG: ARGS WORKING");
    		int x = Integer.parseInt(args[3]);
    		int y = Integer.parseInt(args[4]);
    		int z = Integer.parseInt(args[5]);
    		
    		//Bukkit.broadcastMessage("Loc x: " + x);
    		//Bukkit.broadcastMessage("Loc y: " + y);
    		//Bukkit.broadcastMessage("Loc z: " + z);
    		
    		Material mat =  Material.matchMaterial(args[6]);
    		//Bukkit.broadcastMessage("Material: " + mat);
    		
    		double vector1 = Double.parseDouble(args[0]);
    		double vector2 = Double.parseDouble(args[1]);
    		double vector3 = Double.parseDouble(args[2]);
    		
    		//Bukkit.broadcastMessage("Vector1: " + vector1);
    		//Bukkit.broadcastMessage("Vector2: " + vector2);
    		//Bukkit.broadcastMessage("Vector3: " + vector3);
    		
    		Location loc = new Location(Bukkit.getServer().getWorld(args[7]), x, y, z);
            FallingBlock falblock = loc.getWorld().spawnFallingBlock(loc, mat, (byte) 0);
            falblock.setDropItem(false);
            falblock.setVelocity(new Vector(vector1, vector2, vector3));     
            //p.sendMessage("DEBUG: ARGS WORKING2");
            return true;
    	}
    	}
    }
    if (cmd.getName().equalsIgnoreCase("block")) {
    	if (sender instanceof ConsoleCommandSender || sender instanceof BlockCommandSender) {
    	if (args.length > 0) {    		
        		int x = Integer.parseInt(args[0]);
        		int y = Integer.parseInt(args[1]);
        		int z = Integer.parseInt(args[2]);
        		
        		Material mat =  Material.matchMaterial(args[3]);
        		
        		Location loc = new Location(Bukkit.getServer().getWorld(args[4]), x, y, z);
        		loc.getBlock().setType(mat);
    		}
    	}
    }
	return false;
	}

}
