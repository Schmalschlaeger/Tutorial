package tuts;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;
 
public class Tutorial extends JavaPlugin implements Listener {
       
        public static Inventory inv = Bukkit.createInventory(null, 9, ChatColor.RED + "Tutorial Inventory");
       
        public void onEnable() {
                Bukkit.getServer().getPluginManager().registerEvents(this, this);
        }
       
        static {
                ItemStack stack = new ItemStack(Material.DIAMOND);
                ItemMeta meta = stack.getItemMeta();
                meta.setDisplayName(ChatColor.GOLD + "This is the best test ever!");
                ArrayList<String> lore = new ArrayList<String>();
                lore.add(ChatColor.GRAY + "Lore 1");
                lore.add(ChatColor.GRAY + "Lore 2");
                lore.add(ChatColor.GRAY + "Lore 3");
                lore.add(ChatColor.GRAY + "Lore 4");
                meta.setLore(lore);
                stack.setItemMeta(meta);
               
                inv.contains(stack);
                inv.setItem(8, stack);
        }
       
        @EventHandler
        public void onItemClick(InventoryClickEvent e) {
                Player player = (Player) e.getWhoClicked();
                Inventory clickedInv = e.getInventory();
                ItemStack item = e.getCurrentItem();
                if (clickedInv.getName().equals(inv.getName())) {
                        if (item.hasItemMeta() && item.getItemMeta().getDisplayName().contains(ChatColor.GOLD + "This is the best test ever!")) {
                                if (item.getType() == Material.DIAMOND) {
                                        e.setCancelled(true);
                                        player.closeInventory();
                                        player.sendMessage("IT WORKS!");
                                }
                        }
                }      
        }
       
        @EventHandler
        public void onPlayerClick(PlayerInteractEvent e) {
                Player p = e.getPlayer();
                ItemStack item = e.getItem();
                if (p.getItemInHand() != null) {
                        if (e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK) {
                                        if (item.getType() == Material.STICK) {
                                                e.setCancelled(true);
                                                p.openInventory(inv);
                                        }
                                }
                }
        }
       
}