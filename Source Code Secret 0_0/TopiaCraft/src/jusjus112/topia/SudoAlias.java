package jusjus112.topia;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.logging.Logger;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public class SudoAlias extends JavaPlugin
{
  private static SudoAlias instance;
  public List<Alias> aliases;
  public Logger log;

  @SuppressWarnings({ "unchecked", "rawtypes" })
public void onEnable()
  {
    instance = this;
    this.aliases = new ArrayList();
    this.log = getLogger();

    if (!getDataFolder().exists()) {
      getDataFolder().mkdir();
    }
    FileConfiguration config = getConfig();
    if (!new File(getDataFolder(), "config.yml").exists()) {
      config.options().copyDefaults(true);
      config.options().copyHeader(true);
      saveConfig();
    }
    this.aliases = load(config);

    getServer().getPluginManager().registerEvents(new AliasListener(), this);
    getCommand("sudoalias").setExecutor(new SudoAliasCommandExecutor());
  }

  @SuppressWarnings({ "rawtypes", "unchecked" })
private List<Alias> load(FileConfiguration config) {
    List aliasList = new ArrayList();
    Set<String> keys = config.getConfigurationSection("aliases").getKeys(false);
    for (String key : keys) {
      String path = "aliases." + key;
      String perm = "SudoAlias.alias." + key;

      String commandOrig = config.getString(path + ".command");
      int argCount = 0;
      String command = null;
      if (commandOrig.contains("?")) {
        String command1 = commandOrig.substring(0, commandOrig.indexOf(63) - 1);
        String args = commandOrig.substring(command1.length() + 1);
        argCount = args.length() - args.replace("?", "").length();
      } else {
        command = commandOrig;
      }

      List<String> commandsToRun = config.getStringList(path + ".runCommand");
      String successMsg = config.getString(path + ".successMessage");
      String runAsString = config.getString(path + ".runAs");
      AliasRunAs runAs = null;
      if (runAsString != null) {
        runAs = AliasRunAs.valueOf(runAsString.toUpperCase().trim());
      }
      if ((runAs == null) || (command.isEmpty()) || (commandsToRun == null)) {
        this.log.warning("Alias " + key + " skipped due to missing/incorrect information");
      }
      else
        aliasList.add(new Alias(command, argCount, commandsToRun, successMsg, perm, runAs));
    }
    return aliasList;
  }

  public void reload() {
    reloadConfig();
    FileConfiguration config = getConfig();
    this.aliases = load(config);
  }

  public static SudoAlias getInstance() {
    return instance;
  }

  public void onDisable()
  {
    super.onDisable();
  }
}