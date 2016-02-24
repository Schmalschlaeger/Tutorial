package zombie;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import zombie.MessageManager.MsgType;

import java.util.ArrayList;
import java.util.List;

public class GameListener implements Listener{

    static List<String> players = new ArrayList<String>();
    static Main plugin;

    public GameListener(Main plugin){
        GameListener.plugin = plugin;
    }
    
    @EventHandler
    public void onPlayerCommandBlock(PlayerCommandPreprocessEvent e) {
    	if (ArenaManager.getManager().isInGame(e.getPlayer())) {
    		if (!e.getMessage().contains("zombie") || !e.getMessage().contains("z")) {
    		e.getPlayer().sendMessage(MsgType.NORMAL + "You can not use commands while ingame!");
    		e.setCancelled(true);
        }
    }
}

    @EventHandler
    public void onDamange(EntityDamageByEntityEvent e){
        if(e.getEntity() instanceof Player && players.contains(((Player) e.getEntity()).getName())){
            e.setCancelled(true);
        }
    }
    
    @EventHandler
    public void onBlockBreak(BlockBreakEvent e){
    	Player p = e.getPlayer();
        if(ArenaManager.getManager().isInGame(p)){
            e.setCancelled(true);
        }
    }
    
    @EventHandler
    public void onPlayerFoodLose(FoodLevelChangeEvent e) {
    	Player p = (Player) e.getEntity();
    	if (ArenaManager.getManager().isInGame(p)) {
    		e.setCancelled(true);
    	}
    }

    @EventHandler
    public void onDeath(PlayerDeathEvent e){
        if(ArenaManager.getManager().isInGame(e.getEntity())){
            ArenaManager.getManager().removePlayer(e.getEntity());
        }
    }
    
    @EventHandler
    public void onQuit(PlayerQuitEvent e){
        if(ArenaManager.getManager().isInGame(e.getPlayer())){
            ArenaManager.getManager().removePlayer(e.getPlayer());
        }
    }

    public static void add(Player p){
        final String name = p.getName();
        players.add(name);

        Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, new Runnable(){
            @Override
            public void run(){
                players.remove(name);
            }
        }, 100L);
    }
}