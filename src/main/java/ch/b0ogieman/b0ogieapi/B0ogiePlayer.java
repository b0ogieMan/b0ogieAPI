package ch.b0ogieman.b0ogieapi;

import ch.b0ogieman.b0ogieapi.utils.Utils;

import java.util.UUID;

public class B0ogiePlayer {

	private UUID uuid;
	private String displayName;
	private Integer coins;
	private Utils.RANKS rank;

	public B0ogiePlayer(UUID uuid, String displayName, Integer coins, Utils.RANKS rank) {
		this.uuid = uuid;
		this.displayName = displayName;
		this.coins = coins;
		this.rank = rank;
	}

	public UUID getUuid() {
		return uuid;
	}

	public String getDisplayName() {
		return displayName;
	}

	public Integer getCoins() {
		return coins;
	}

	public Utils.RANKS getRank() {
		return rank;
	}

}
