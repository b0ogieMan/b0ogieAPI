package ch.b0ogieman.b0ogieapi.listeners;

import ch.b0ogieman.b0ogieapi.B0ogieAPI;
import ch.b0ogieman.b0ogieapi.B0ogiePlayer;
import ch.b0ogieman.b0ogieapi.utils.Utils;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import java.util.UUID;

public class PlayerChatListener implements Listener {

	private B0ogieAPI b0ogieAPI;

	public PlayerChatListener(B0ogieAPI b0ogieAPI) {
		this.b0ogieAPI = b0ogieAPI;
	}

	@EventHandler
	public void onPlayerChat(AsyncPlayerChatEvent event) {
		final Player player = event.getPlayer();
		final UUID uuid = player.getUniqueId();

		Utils utils = new Utils();

		event.setFormat(utils.getRankString(b0ogieAPI.getPlayers().get(uuid).getRank()) + " " + player.getDisplayName()
				  + "§r: " + event.getMessage());
	}
}
