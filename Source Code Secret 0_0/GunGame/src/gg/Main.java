package gg;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin implements Listener {
	
	@Override
    public void onEnable(){
        if(!getDataFolder().exists())
            getDataFolder().mkdir();

        if(getConfig() == null)
            saveDefaultConfig();

        new GunMinigameListener(this);
        GunMinigameListener.getManager().loadGames();

        getServer().getPluginManager().registerEvents(new SignJoinListener(), this);
        getServer().getPluginManager().registerEvents(new MiniGameEvents(), this);
    }

    @Override
    public void onDisable(){
        saveConfig();
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String args[]){
        if(!(sender instanceof Player)){
            sender.sendMessage("Whoa there buddy, only players may execute this!");
            return true;
        }

        Player p = (Player) sender;

        if(cmd.getName().equalsIgnoreCase("create")){
        	if (!p.getName().equals("jusjus112")) {
        		p.sendMessage(ChatColor.RED + "Sorry, only jusjus112 can create games");
        		return true;
        	}
        	GunMinigameListener.getManager().createArena(p.getLocation());
            p.sendMessage(ChatColor.GOLD + "Created arena at " + ChatColor.RED + p.getLocation().toString());

            return true;
        }
        if(cmd.getName().equalsIgnoreCase("join")){
        	if (!p.isOp()) {
        		p.sendMessage(ChatColor.RED + "Sorry, but you can only use this, with an sign! Find the signs at the western gungame!");
        		return true;
        	}
            if(args.length != 1){
                p.sendMessage("Insuffcient arguments!");
                return true;
            }
            int num = 0;
            try{
                num = Integer.parseInt(args[0]);
            }catch(NumberFormatException e){
                p.sendMessage("Invalid arena ID");
            }
            GunMinigameListener.getManager().addPlayer(p, num);

            return true;
        }
        if(cmd.getName().equalsIgnoreCase("leave")){
        	if (!p.isOp()) {
        		p.sendMessage(ChatColor.RED + "Sorry, but you can only use this, with an sign! Find the signs at the western gungame!");
        		return true;
        	}
        	GunMinigameListener.getManager().removePlayer(p);
            return true;
        }
        if(cmd.getName().equalsIgnoreCase("remove")){
            if(args.length != 1){
                p.sendMessage("Insuffcient arguments!");
                return true;
            }
        	if (!p.getName().equals("jusjus112")) {
        		p.sendMessage(ChatColor.RED + "Sorry, only jusjus112 can remove arena's");
        		return true;
        	}
            int num = 0;
            try{
                num = Integer.parseInt(args[0]);
            }catch(NumberFormatException e){
                p.sendMessage("Invalid arena ID");
            }
            GunMinigameListener.getManager().removeArena(num);

            return true;
        }

        return false;
    }

}
