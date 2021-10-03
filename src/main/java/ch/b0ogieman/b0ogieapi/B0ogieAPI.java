package ch.b0ogieman.b0ogieapi;

import ch.b0ogieman.b0ogieapi.database.DatabaseManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class B0ogieAPI extends JavaPlugin {

	public static B0ogieAPI INSTANCE;
	private DatabaseManager databaseManager;

	@Override
	public void onEnable() {
		// Plugin startup logic
		INSTANCE = this;
		databaseManager = new DatabaseManager();

	}

	@Override
	public void onDisable() {
		// Plugin shutdown logic
		databaseManager.close();
	}

	public DatabaseManager getDatabaseManager() {
		return databaseManager;
	}

}



