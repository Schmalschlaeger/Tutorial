package scoreboard;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.Sign;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.FallingBlock;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockRedstoneEvent;
import org.bukkit.event.block.SignChangeEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.util.Vector;

import scoreboard.MessageManager.MsgType;

public class Fontain implements Listener {
	
	private Main plugin;
	 
	public Fontain(Main instance) {
	    this.plugin = instance;
	}
	
    private int water;
    
    public void setId(int water) {
        this.water = water;
    }
	
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args){
		if (cmd.getName().equalsIgnoreCase("fountain")) {
			if (args.length == 2) {
				if (args[0].equalsIgnoreCase("create")) {
					
				}
			}
		}
		return false;
		}
    
    @EventHandler
    public void onSignChange(SignChangeEvent e) {
            if (e.getLine(0).equalsIgnoreCase("[Magic Block]")) {
            	if (e.getLine(1).equalsIgnoreCase(plugin.getConfig().getString("ID"))) {
                    e.setLine(0, "§3[Fauntain]");
                    e.getPlayer().sendMessage(MsgType.SIGN + "Succesfull created fountain sign! With ID: " + ChatColor.YELLOW + e.getLine(1));
            	}else {
            		e.getPlayer().sendMessage(MsgType.SIGN + "The ID " + ChatColor.YELLOW + e.getLine(1) + ChatColor.GOLD + " could not be found in the config!");
            		e.getBlock().breakNaturally();
            	}
            }
    }
    
	  @EventHandler
      public void OnRedstone6(final BlockRedstoneEvent event){
          if(event.getBlock().getState() instanceof Sign){
        	  final Block a = event.getBlock();
              Sign b = (Sign) a.getState();
              final World world = a.getWorld();
              String c = b.getLine(0);
              if(c.equalsIgnoreCase("§3[Fauntain]")){
                  if(a.isBlockPowered()){
                      final Location loc = a.getLocation();
                      water = Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(plugin, new Runnable() {
                          @Override
                          public void run() {
                              if (a.isBlockPowered()) {
                  FallingBlock yay1 = world.spawnFallingBlock(loc, Material.STATIONARY_WATER, (byte) 0);
                  yay1.setVelocity(new Vector(0.5, 1, 0));
                  yay1.setDropItem(false);
                              }else {
                            	  Bukkit.getServer().getScheduler().cancelTask(water);
                          }
                          }
                      },0L, 10L);
                  }else {
                	  Bukkit.getServer().getScheduler().cancelTask(water);
                  }
              }
          }
      }
    
    @EventHandler
    public void onPlayerMove(PlayerMoveEvent e) {
    	Player p = e.getPlayer();
    	if(p.getLocation().subtract(0, 1, 0).getBlock().getType() == Material.SPONGE) {
            p.getLocation().subtract(0, 1, 0).getBlock().setType(Material.AIR);
          }
    }

}
