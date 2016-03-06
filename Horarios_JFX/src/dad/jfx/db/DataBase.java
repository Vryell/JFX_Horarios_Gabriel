package dad.jfx.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class DataBase {
	public static Connection connect(){
		Connection conn = null;
		ResourceBundle bundle = ResourceBundle.getBundle("dad.jfx.db.db");
		String driverClass = bundle.getString("db.driver");
		String urlConn = bundle.getString("db.url");
		String user = bundle.getString("db.user");
		String pass = bundle.getString("db.pass");
		try {
			Class.forName(driverClass);
			conn = DriverManager.getConnection(urlConn,user,pass);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	
	return conn;
	}
	public void disconnect(Connection conn){
		try {
			if(!conn.isClosed()){
				conn.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
