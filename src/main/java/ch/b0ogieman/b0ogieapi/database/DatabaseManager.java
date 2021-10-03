package ch.b0ogieman.b0ogieapi.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseManager {

	private final String url = "jdbc:postgresql://localhost:5432/b0ogieAPI";
	private final String user = "postgres";
	private final String password = "$ThisIsMyPSQLPwd.";
	private Connection connection;

	public DatabaseManager() {
		connect();
	}

	public Connection connect() {
		try {
			Class.forName("org.postgresql.Driver");
			this.connection = DriverManager.getConnection(url, user, password);
			System.out.println("Connected to the PostgreSQL server successfully.");
		} catch (SQLException | ClassNotFoundException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		return this.connection;
	}

	public void close() {
		try {
			if(this.connection != null) {
				if(!this.connection.isClosed()) {
					this.connection.close();
				}
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}

	public Connection getConnection() {
		try {
			if(this.connection != null) {
				if(!this.connection.isClosed()) {
					return connection;
				}
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		connect();
		return this.connection;
	}

}
