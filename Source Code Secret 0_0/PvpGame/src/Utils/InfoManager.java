package Utils;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class InfoManager {

	private String titleSlot = null;
	private String infoSlot1 = "";
	private String infoSlot2 = "";
	private String infoSlot4 = "";
	private String infoSlot5 = "";
	private String infoSlot6 = "";

	public void showInfo() {
		for (Player all : Bukkit.getServer().getOnlinePlayers()) {
			if (TeamManager.getPlayerTeam(all) == "red") {
				all.sendMessage(ChatColor.DARK_GRAY + "" + ChatColor.BOLD + "█    " + ChatColor.BOLD + "");
				all.sendMessage(ChatColor.DARK_GRAY + "" + ChatColor.BOLD + "█ " + ChatColor.GREEN + ChatColor.BOLD + "+ " + titleSlot);
				all.sendMessage(ChatColor.DARK_GRAY + "" + ChatColor.BOLD + "█    " + ChatColor.WHITE + ChatColor.BOLD + "");
				all.sendMessage(ChatColor.DARK_GRAY + "" + ChatColor.BOLD + "█    " + ChatColor.WHITE + infoSlot1);
				all.sendMessage(ChatColor.DARK_GRAY + "" + ChatColor.BOLD + "█    " + ChatColor.WHITE + infoSlot2);
				all.sendMessage(ChatColor.DARK_GRAY + "" + ChatColor.BOLD + "█----------------------------");
				all.sendMessage(ChatColor.DARK_GRAY + "" + ChatColor.BOLD + "█    " + ChatColor.RED + "" + ChatColor.BOLD + "Your slender!");
				all.sendMessage(ChatColor.DARK_GRAY + "" + ChatColor.BOLD + "█----------------------------");
				all.sendMessage(ChatColor.DARK_GRAY + "" + ChatColor.BOLD + "█    " + ChatColor.WHITE + infoSlot4);
				all.sendMessage(ChatColor.DARK_GRAY + "" + ChatColor.BOLD + "█    " + ChatColor.WHITE + infoSlot5);
				all.sendMessage(ChatColor.DARK_GRAY + "" + ChatColor.BOLD + "█    " + ChatColor.WHITE + infoSlot6);
				all.sendMessage(ChatColor.DARK_GRAY + "" + ChatColor.BOLD + "█████████████████████████████");
			}else {
		all.sendMessage(ChatColor.DARK_GRAY + "" + ChatColor.BOLD + "█    " + ChatColor.BOLD + "");
		all.sendMessage(ChatColor.DARK_GRAY + "" + ChatColor.BOLD + "█ " + ChatColor.GREEN + ChatColor.BOLD + "+ " + titleSlot);
		all.sendMessage(ChatColor.DARK_GRAY + "" + ChatColor.BOLD + "█    " + ChatColor.WHITE + ChatColor.BOLD + "");
		all.sendMessage(ChatColor.DARK_GRAY + "" + ChatColor.BOLD + "█    " + ChatColor.WHITE + infoSlot1);
		all.sendMessage(ChatColor.DARK_GRAY + "" + ChatColor.BOLD + "█    " + ChatColor.WHITE + infoSlot2);
		all.sendMessage(ChatColor.DARK_GRAY + "" + ChatColor.BOLD + "█----------------------------");
		all.sendMessage(ChatColor.DARK_GRAY + "" + ChatColor.BOLD + "█    " + ChatColor.AQUA + "" + ChatColor.BOLD + "Your an survivor");
		all.sendMessage(ChatColor.DARK_GRAY + "" + ChatColor.BOLD + "█----------------------------");
		all.sendMessage(ChatColor.DARK_GRAY + "" + ChatColor.BOLD + "█    " + ChatColor.WHITE + infoSlot4);
		all.sendMessage(ChatColor.DARK_GRAY + "" + ChatColor.BOLD + "█    " + ChatColor.WHITE + infoSlot5);
		all.sendMessage(ChatColor.DARK_GRAY + "" + ChatColor.BOLD + "█    " + ChatColor.WHITE + infoSlot6);
		all.sendMessage(ChatColor.DARK_GRAY + "" + ChatColor.BOLD + "█████████████████████████████");
			}
		}
	}

	public void setTitleSlot(String titleSlot) {
		this.titleSlot = titleSlot;
	}
	public void setInfoSlot1(String infoSlot1) {
		this.infoSlot1 = infoSlot1;
	}
	public void setInfoSlot2(String infoSlot2) {
		this.infoSlot2 = infoSlot2;
	}
	public void setInfoSlot4(String infoSlot4) {
		this.infoSlot4 = infoSlot4;
	}
	public void setInfoSlot5(String infoSlot5) {
		this.infoSlot5 = infoSlot5;
	}
	public void setInfoSlot6(String infoSlot6) {
		this.infoSlot6 = infoSlot6;
	}
}
