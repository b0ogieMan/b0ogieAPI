package ch.b0ogieman.b0ogieapi.listeners;

import ch.b0ogieman.b0ogieapi.B0ogieAPI;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import java.sql.*;
import java.util.UUID;

public class PlayerQuitListener implements Listener {

	private B0ogieAPI b0ogieAPI;

	public PlayerQuitListener(B0ogieAPI b0ogieAPI) {
		this.b0ogieAPI = b0ogieAPI;
	}

	@EventHandler
	public void onPlayerQuit(PlayerQuitEvent event) {
		final UUID uuid = event.getPlayer().getUniqueId();
		final Connection connection;

		try {

			connection = b0ogieAPI.getDatabaseManager().getConnection();

			final PreparedStatement preparedStatement = connection.prepareStatement(
					  "UPDATE \"b0ogiePlayers\" SET last_connection = ? WHERE uuid=?"
			);

			final long time = System.currentTimeMillis();
			preparedStatement.setTimestamp(1, new Timestamp(time));
			preparedStatement.setString(2, uuid.toString());
			preparedStatement.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
