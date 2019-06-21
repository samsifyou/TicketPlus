package db.mysql;

public class MySQLDBUtil {
	private static final String HOSTNAME = "localhost";
	private static final String PORT_NUM = "3307"; // change it to your mysql port number
	public static final String DB_NAME = "laiprojectc";
	private static final String USERNAME = "root";
	private static final String PASSWORD = "root";
	public static final String URL = "jdbc:mysql://" + HOSTNAME + ":" + PORT_NUM + "/" + DB_NAME + "?user=" + USERNAME
			+ "&password=" + PASSWORD + "&autoReconnect=true&serverTimezone=UTC";
	// Connecting to jdbc:mysql://localhost:3307/laiprojectc?user=root&password=root&autoReconnect=true&serverTimezone=UTC

}
