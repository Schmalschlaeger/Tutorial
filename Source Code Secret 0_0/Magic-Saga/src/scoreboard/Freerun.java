package scoreboard;

import java.util.HashMap;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

import scoreboard.MessageManager.MsgType;

public class Freerun implements Listener {
	
  HashMap<String, Location> map = new HashMap<String, Location>();

  int x = 0;
  
  @EventHandler
  private void onPlayerMoveEvent(PlayerMoveEvent event)
  {
    Player player = event.getPlayer();
    Location location = player.getLocation();
    location.setY(location.getY() - 1.5D);
    Block block = location.getBlock();
    Material material = block.getType();

    if (!player.hasPermission("magic.freerun.bypass")) {
      if ((material != Material.FENCE) && (material != Material.FENCE_GATE) && (material != Material.NETHER_FENCE) && (material != Material.LEAVES) && (material != Material.GOLD_BLOCK) && (material != Material.IRON_FENCE) && (material != Material.COBBLE_WALL)) {
        location.setY(location.getY() + 1.0D);
        this.map.put(player.getName(), location);
      }
      else if ((material == Material.FENCE) || (material == Material.FENCE_GATE) || (material == Material.NETHER_FENCE) || (material == Material.LEAVES) || (material == Material.GOLD_BLOCK) || (material == Material.IRON_FENCE) || (material == Material.COBBLE_WALL)) {
        player.sendMessage(MsgType.NORMAL + "You may not freerun here.");
        player.teleport((Location)this.map.get(player.getName()));
        this.map.remove(player.getName());
        this.x += 1;

        if (this.x == 10) {
          player.kickPlayer("We have warned you already 10 times!");
          this.x = 0;
        }
      }
    }
  }
}