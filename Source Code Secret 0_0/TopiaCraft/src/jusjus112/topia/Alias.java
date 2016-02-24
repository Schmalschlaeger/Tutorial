package jusjus112.topia;

import java.util.Arrays;
import java.util.List;

public class Alias
{
  private String command;
  private List<String> commandsToRun;
  private String successMsg;
  private String permNode;
  private AliasRunAs runAs;
  private int amountOfArgs;

  public Alias(String command, int amountOfArgs, List<String> commandToRun, String successMsg, String permNode, AliasRunAs runAs)
  {
    this.command = command;
    this.amountOfArgs = amountOfArgs;
    this.commandsToRun = commandToRun;
    this.successMsg = (successMsg == null ? "" : successMsg);
    this.permNode = permNode;
    this.runAs = runAs;
  }

  public String getCommand() {
    return this.command;
  }

  public void setCommand(String command) {
    this.command = command;
  }

  public int getAmountOfArgs() {
    return this.amountOfArgs;
  }

  public void setAmountOfArgs(int amountOfArgs) {
    this.amountOfArgs = amountOfArgs;
  }

  public List<String> getCommandsToRun() {
    return this.commandsToRun;
  }

  public void setCommandsToRun(List<String> commandsToRun) {
    this.commandsToRun = commandsToRun;
  }

  public String getSuccessMessage() {
    return this.successMsg;
  }

  public void setSuccessMessage(String successMsg) {
    this.successMsg = successMsg;
  }

  public String getPermNode() {
    return this.permNode;
  }

  public void setPermNode(String permNode) {
    this.permNode = permNode;
  }

  public AliasRunAs getRunAs() {
    return this.runAs;
  }

  public void setRunAs(AliasRunAs runAs) {
    this.runAs = runAs;
  }

  public boolean isMatch(String cmd) {
    String aliasCmd = getCommand();
    if (cmd.startsWith("/" + aliasCmd)) {
      String[] args = getArgs(cmd);
      int argAmt = args.length;
      if (argAmt != getAmountOfArgs()) {
        return false;
      }
      return true;
    }
    return false;
  }

  public String[] getArgs(String cmd) {
    String[] arr = cmd.substring(("/" + getCommand()).length(), cmd.length()).split(" ");
    return (String[])Arrays.copyOfRange(arr, 1, arr.length);
  }

  public String toString()
  {
    String info = "Alias Information\n";
    info = info + "=================\n";
    info = info + "command: " + this.command + "\n";
    info = info + "amountOfArgs: " + this.amountOfArgs + "\n";
    info = info + "commandToRun: " + this.commandsToRun + "\n";
    info = info + "successMsg: " + this.successMsg + "\n";
    info = info + "permNode: " + this.permNode + "\n";
    info = info + "runAs: " + this.runAs.name() + "";
    return info;
  }
}