package tuts;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.plugin.java.JavaPlugin;
 
public class Tutorial extends JavaPlugin implements Listener {
       
        public ArrayList<Player> mute = new ArrayList<Player>();
       
        public void onEnable() {       
                Bukkit.getServer().getPluginManager().registerEvents(this, this);
        }
       
        @EventHandler
        public void onPlayerMute(AsyncPlayerChatEvent e) {
                if (mute.contains(e.getPlayer())) {
                        e.setCancelled(true);
                }
        }
       
        @Override
        public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
                Player p = (Player) sender;
                if (cmd.getName().equalsIgnoreCase("mute")) {
                        if (args.length != 1) {
                                p.sendMessage(ChatColor.RED + "Try /mute <player>");
                                return true;
                        }else {
                                @SuppressWarnings("deprecation")
								Player mutePlayer = Bukkit.getPlayer(args[0]);
                                if (mutePlayer == null) {
                                        p.sendMessage(ChatColor.YELLOW + args[0] + ChatColor.RED  + " is not online");
                                        return true;
                                }
                                mute.add(mutePlayer);
                                p.sendMessage(ChatColor.RED + "Player " + mutePlayer.getName() + ChatColor.RED + " muted!");
                                return true;
                        }
                }
                if (cmd.getName().equalsIgnoreCase("unmute")) {
                        if (args.length != 1) {
                                p.sendMessage(ChatColor.RED + "Try /unmute <player>");
                                return true;
                        }else {
                                @SuppressWarnings("deprecation")
								Player mutePlayer = Bukkit.getPlayer(args[0]);
                                if (mutePlayer == null) {
                                        p.sendMessage(ChatColor.YELLOW + args[0] + ChatColor.RED  + " is not online");
                                        return true;
                                }
                                mute.remove(mutePlayer);
                                p.sendMessage(ChatColor.RED + "Player " + mutePlayer.getName() + ChatColor.RED + " unmuted!");
                                return true;
                        }
                }
                return false;
        }
       
}