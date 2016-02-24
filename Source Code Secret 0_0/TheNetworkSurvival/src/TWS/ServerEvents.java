package TWS;

import java.util.ArrayList;
import java.util.UUID;

import net.minecraft.server.v1_7_R4.AttributeInstance;
import net.minecraft.server.v1_7_R4.AttributeModifier;
import net.minecraft.server.v1_7_R4.EntityInsentient;
import net.minecraft.server.v1_7_R4.GenericAttributes;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_7_R4.entity.CraftLivingEntity;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Villager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class ServerEvents implements Listener {

	static Inventory rooms = Bukkit.createInventory(null, 27, ChatColor.AQUA + "" + ChatColor.BOLD + "Random Teleporter");
	
	static {
		createCustomItem(Material.ENDER_PEARL, ChatColor.AQUA + "Teleport to the overworld", ChatColor.GRAY + "This item, teleport you to the world", 11, rooms);
	}
	
	private Main plugin;
	 
	public ServerEvents(Main instance) {
	    this.plugin = instance;
	}
	
	@EventHandler
	public void onPlayerJoinMessage(PlayerJoinEvent e) {
		e.setJoinMessage(ChatColor.RED + "+ " + ChatColor.GOLD
				+ e.getPlayer().getName() + " has joined" + ChatColor.AQUA
				+ " " + ChatColor.BOLD + "The Network Survival");
	}

	@EventHandler
	public void onPlayerLeave(PlayerQuitEvent e) {
		e.setQuitMessage(ChatColor.RED + "- " + ChatColor.GOLD
				+ e.getPlayer().getName() + " has leaved" + ChatColor.AQUA
				+ " " + ChatColor.BOLD + "The Network Survival");
	}
	
	public static void createCustomItem(Material mat, String displayName, String lore, int itemPlace, Inventory inv) {
		ItemStack menu1 = new ItemStack(mat);
		ItemMeta shop1 = menu1.getItemMeta();
		shop1.setDisplayName(displayName);
		ArrayList<String> iron3 = new ArrayList<String>();
		iron3.add(ChatColor.GRAY + " ");
		iron3.add(lore);
		shop1.setLore(iron3);
		menu1.setItemMeta(shop1);

		inv.contains(menu1);
		inv.setItem(itemPlace, menu1);
	}
	
	@EventHandler
	public void onVipClick(InventoryClickEvent e) {
		Player player = (Player) e.getWhoClicked();
		Inventory inv = e.getInventory();
		ItemStack cl = e.getCurrentItem();
		if (inv.getName().equals(rooms.getName())) {
			if (e.getCurrentItem().hasItemMeta()
					&& e.getCurrentItem().getItemMeta().getDisplayName()
							.contains(ChatColor.AQUA + "Teleport to the overworld")) {
				if (cl.getType() == Material.ENDER_PEARL) {
					e.setCancelled(true);
					player.closeInventory();
					player.sendMessage(ChatColor.RED + "You cant teleport right now! Its coming soom, or on maintenance!");
				}
			}
		}
	}
	
	@EventHandler
	public void onPlayerInteractEntityEvent(PlayerInteractEntityEvent e) {		
	    Player p = e.getPlayer();
	    Entity entity = e.getRightClicked();
	       if (entity instanceof Villager && ((LivingEntity) entity).getCustomName().equals(ChatColor.GOLD + "" + ChatColor.BOLD + "Random Teleporter!")) {
	           e.setCancelled(true);
	           p.openInventory(rooms);
	           
    	}
	}
	
	public void spawnVillagers(double x, double y, double z) {
		final Location loc = new Location(Bukkit.getServer().getWorld("GeneratedWorld"), x, y, z);
        final Villager v = (Villager) Bukkit.getServer().getWorld("GeneratedWorld").spawnEntity(loc, EntityType.VILLAGER);

        UUID movementSpeedUID = UUID.randomUUID();
        EntityInsentient nmsEntity = (EntityInsentient) ((CraftLivingEntity) v).getHandle();
        AttributeInstance attributes = nmsEntity.getAttributeInstance(GenericAttributes.d);
        AttributeModifier modifier = new AttributeModifier(movementSpeedUID, "Villger", -100D, 1);
        attributes.b(modifier);
        attributes.a(modifier);
        
        v.setCustomName(ChatColor.GOLD + "" + ChatColor.BOLD + "Random Teleporter!");
        v.setCustomNameVisible(true);
        
        Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(plugin, new Runnable() {
			@Override
			public void run() {
				v.teleport(loc);
			}
        }, 0, 10L);
	}

}
