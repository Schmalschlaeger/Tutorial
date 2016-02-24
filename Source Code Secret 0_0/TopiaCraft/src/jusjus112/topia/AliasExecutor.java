package jusjus112.topia;

import org.bukkit.ChatColor;
import org.bukkit.Server;
import org.bukkit.command.CommandSender;

public class AliasExecutor
  implements Runnable
{
  private Alias alias;
  private String cmd;
  private CommandSender sender;
  private String pName;

  public AliasExecutor(Alias alias, String cmd, CommandSender sender, String pName)
  {
    this.alias = alias;
    this.cmd = cmd;
    this.sender = sender;
    this.pName = pName;
  }

  public void run()
  {
    if (!this.sender.hasPermission(this.alias.getPermNode())) {
      this.sender.sendMessage(ChatColor.RED + "Sorry, you don't have permission to do that.");
      return;
    }
    String[] args = this.alias.getArgs(this.cmd);
    int argAmt = args.length;
    for (String command : this.alias.getCommandsToRun()) {
      if (command.startsWith("$wait:")) {
        String[] data = command.split(":");
        if (data.length == 2)
        {
          long time = 1000L;
          try {
            time = Long.parseLong(data[1]);
          } catch (NumberFormatException ex) {
            SudoAlias.getInstance().getLogger().warning("Invalid long time value " + data[1] + " defaulting to 1000.");
          }
          try {
            Thread.sleep(time);
          } catch (InterruptedException e) {
            e.printStackTrace();
          }
        }
      } else { if ((this.pName != null) && (!this.pName.isEmpty())) {
          command = command.replace("$player", this.pName);
        }
        for (int i = 0; i < argAmt; i++) {
          command = command.replace("$" + i, args[i]);
        }
        Server server = SudoAlias.getInstance().getServer();
        if (this.alias.getRunAs() == AliasRunAs.CONSOLE) {
          server.getScheduler().scheduleSyncDelayedTask(SudoAlias.getInstance(), new AliasCmdExecutor( server.getConsoleSender(), command));
        }
        else if (this.alias.getRunAs() == AliasRunAs.PLAYER) {
          server.getScheduler().scheduleSyncDelayedTask(SudoAlias.getInstance(), new AliasCmdExecutor( this.sender, command));
        }
      }
    }

    String successMsg = this.alias.getSuccessMessage();
    if ((!successMsg.isEmpty()) && (successMsg != null))
      this.sender.sendMessage(successMsg);
  }
}