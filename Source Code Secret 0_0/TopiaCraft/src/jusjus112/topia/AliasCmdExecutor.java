package jusjus112.topia;

import org.bukkit.command.CommandSender;

public class AliasCmdExecutor
  implements Runnable
{
  private CommandSender sender;
  private String cmd;

  public AliasCmdExecutor(CommandSender sender, String cmd) {
    this.sender = sender;
    this.cmd = cmd;
  }

  public void run()
  {
    SudoAlias.getInstance().getServer().dispatchCommand(this.sender, this.cmd);
  }
}