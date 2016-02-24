package jusjus112.topia;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.server.ServerCommandEvent;

public class AliasListener
  implements Listener
{
  @EventHandler(priority=EventPriority.LOWEST)
  public void handleCommandEvent(PlayerCommandPreprocessEvent e)
  {
    SudoAlias plugin = SudoAlias.getInstance();
    for (Alias alias : plugin.aliases)
      if (alias.isMatch(e.getMessage())) {
        plugin.getServer().getScheduler().runTaskAsynchronously(plugin, new AliasExecutor(alias, e.getMessage(), e.getPlayer(), e.getPlayer().getName()));

        e.setCancelled(true);
        return;
      }
  }

  @EventHandler(priority=EventPriority.LOWEST)
  public void handleServerCommandEvent(ServerCommandEvent e)
  {
    SudoAlias plugin = SudoAlias.getInstance();
    String cmd = "/" + e.getCommand();
    for (Alias alias : plugin.aliases)
      if (alias.isMatch(cmd)) {
        plugin.getServer().getScheduler().runTaskAsynchronously(plugin, new AliasExecutor(alias, cmd, e.getSender(), e.getSender().getName()));

        e.setCommand("sudoalias nothing");
        return;
      }
  }
}