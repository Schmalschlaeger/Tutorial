package mainp.thenetwork;

public class PermissionsManager {
	
	public final String[] staffPermissions = {
		"network.rank.owner", "network.rank.crew", "network.rank.moderator", "network.rank.builder", 
		"network.rank.support", "network.rank.sponsor"
	};
	
	private String ownerPermissions = "network.rank.owner";
	private String hydraPermissions = "network.rank.hydra";
	private String crewPermissions = "network.rank.crew";
	private String ModeratorPermissions = "network.rank.moderator";
	private String BuilderPermissions = "network.rank.builder";
	private String vipPermissions = "network.rank.vip";
	private String DonatorPermissions = "network.rank.donator";
	private String DeveloperPermissions = "network.rank.developer";
	private String helperPermissions = "network.rank.helper";
	private String SupportPermissions = "network.rank.support";
	private String SponsorPermissions = "network.rank.sponsor";
	
	public String getStaffPermissions() {
		StringBuilder builder = new StringBuilder();
		for (String s : staffPermissions) {
		       builder.append(s);
		}
		 
		String combinedStrings = builder.toString();
		return combinedStrings;
	}
	
	public String getOwnerPermissions() {
		return ownerPermissions;
	}
	
	public String getCrewPermissions() {
		return crewPermissions;
	}
	
	public String getModeratorPermissions() {
		return ModeratorPermissions;
	}
	
	public String getBuilderrPermissions() {
		return BuilderPermissions;
	}
	
	public String getVipPermissions() {
		return vipPermissions;
	}

	public String getDonatorPermissions() {
		return DonatorPermissions;
	}
	
	public String getDeveloperPermissions() {
		return DeveloperPermissions;
	}
	
	public String getHydraPermissions() {
		return hydraPermissions;
	}
	
	public String getHelperPermissions() {
		return helperPermissions;
	}
	
	public String getSupportPermissions() {
		return SupportPermissions;
	}
	
	public String getSponsorPermissions() {
		return SponsorPermissions;
	}

}
