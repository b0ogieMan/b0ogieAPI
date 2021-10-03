package ch.b0ogieman.b0ogieapi.listeners;

import ch.b0ogieman.b0ogieapi.B0ogieAPI;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.sql.*;
import java.util.UUID;

public class PlayJoinListener implements Listener {

	private B0ogieAPI b0ogieAPI;

	public PlayJoinListener(B0ogieAPI b0ogieAPI) {
		this.b0ogieAPI = b0ogieAPI;
	}

	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent event) {
		final UUID uuid = event.getPlayer().getUniqueId();
		final Connection connection;

		try {

			connection = b0ogieAPI.getDatabaseManager().getConnection();

			final PreparedStatement preparedStatement = connection.prepareStatement(
					  "SELECT uuid, nickname FROM \"b0ogiePlayers\" WHERE uuid=?"
			);

			preparedStatement.setString(1, uuid.toString());


			final ResultSet resultSet = preparedStatement.executeQuery();

			if(resultSet.next()) {

				final PreparedStatement updateStatement = connection.prepareStatement(
						  "UPDATE \"b0ogiePlayers\" SET last_connection=? WHERE uuid=?"
				);

				final long time = System.currentTimeMillis();
				updateStatement.setTimestamp(1, new Timestamp(time));
				updateStatement.setString(2, uuid.toString());
				updateStatement.executeUpdate();

			} else {
				createUser(connection, uuid, event.getPlayer().getDisplayName());
			}



		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	private void createUser(Connection connection, UUID uuid, String displayName) {

		try {
			final PreparedStatement preparedStatement = connection.prepareStatement(
					  "INSERT INTO \"b0ogiePlayers\"" +
								 "(uuid, nickname, rank, coins, first_connection,last_connection)" +
								 " VALUES (?,?,?,?,?,?)");

			preparedStatement.setString(1, uuid.toString());
			preparedStatement.setString(2, displayName);
			preparedStatement.setInt(3,0);
			preparedStatement.setInt(4,0);
			final long time = System.currentTimeMillis();
			preparedStatement.setTimestamp(5, new Timestamp(time));
			preparedStatement.setTimestamp(6, new Timestamp(time));
			preparedStatement.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}


	}
}
