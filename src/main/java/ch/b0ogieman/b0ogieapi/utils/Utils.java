package ch.b0ogieman.b0ogieapi.utils;

public class Utils {

	public enum RANKS{
		VAGABOND, HELPER, MODERATOR,ADMIN;

	};

	public String getRankString(RANKS rank) {
		switch (rank) {
			case HELPER:
				return "§5[Helper]";
			case MODERATOR:
				return "§6[Moderateur]";
			case ADMIN:
				return "§c[Admin]";
			default:
				return "§f[Vagabond]";
		}
	}

	public Utils.RANKS getRankEnum(Integer value) {
		switch (value) {
			case 1:
				return RANKS.HELPER;
			case 5:
				return RANKS.MODERATOR;
			case 10:
				return RANKS.ADMIN;
			default:
				return RANKS.VAGABOND;
		}
	}


}
