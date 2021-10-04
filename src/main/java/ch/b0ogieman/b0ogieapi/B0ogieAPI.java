package ch.b0ogieman.b0ogieapi;

import ch.b0ogieman.b0ogieapi.database.DatabaseManager;
import ch.b0ogieman.b0ogieapi.listeners.PlayerChatListener;
import ch.b0ogieman.b0ogieapi.listeners.PlayerJoinListener;
import ch.b0ogieman.b0ogieapi.listeners.PlayerQuitListener;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.UUID;

/**
 * TODO : Notification sonore et surlignage dans le chat quand mention
 */

public final class B0ogieAPI extends JavaPlugin {

	public static B0ogieAPI INSTANCE;
	private HashMap<UUID, B0ogiePlayer> players;
	private DatabaseManager databaseManager;


	@Override
	public void onEnable() {
		// Plugin startup logic
		INSTANCE = this;
		databaseManager = new DatabaseManager();
		players = new HashMap<>();

		this.getServer().getPluginManager().registerEvents(new PlayerJoinListener(this), this);
		this.getServer().getPluginManager().registerEvents(new PlayerQuitListener(this), this);
		this.getServer().getPluginManager().registerEvents(new PlayerChatListener(this), this);

	}

	@Override
	public void onDisable() {
		// Plugin shutdown logic
		databaseManager.close();
	}

	public DatabaseManager getDatabaseManager() {
		return databaseManager;
	}
	public HashMap<UUID, B0ogiePlayer> getPlayers() { return players; }

}



