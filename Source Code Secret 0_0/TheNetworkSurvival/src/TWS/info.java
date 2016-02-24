package TWS;

import java.util.UUID;

import org.bukkit.entity.Player;

import com.massivecraft.factions.entity.UPlayer;

public class info {

	public static String getName(Player target) {
		return target.getName();
	}

	public static String getRank(Player target) {
		if (target.hasPermission(Main.OwnerPerms))
			return "Owner";
		if (target.hasPermission(Main.BuilderPerms))
			return "Builder";
		return "Default";
	}

	public static String getFactionFromPlayer(Player target) {
		return UPlayer.get(target).getFactionName();
	}

	public static String getBalance(Player target) {
		return String.format(Main.econ.format(Main.econ.getBalance(target
				.getName())));
	}

	public static UUID getUUIDFromPlayer(Player target) {
		return target.getUniqueId();
	}

}
