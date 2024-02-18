/**
 * @auther sailesh
 *
 */
package RealWorldApp.SeleniumRWA.utility;

/**
 * 
 */
/**
*
* @author:sailesh
*
*/
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
	// Create database connection
	public static Connection connect() {
		Connection connection = null;

		String url = "jdbc:mysql://localhost:3306/demo";
		String username = "root";
		String password = "sailesh01";
		try {
			connection = DriverManager.getConnection(url, username, password);

			if (connection != null) {
				System.out.println("Connected to db");
			} else {
				System.out.println("Not connected to db");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return connection;
	}

	// Close the database connection
	public static void closeConnection(Connection connection) {
		try {
			if (connection != null) {
				connection.close();
				System.out.println("Connection closed");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}

