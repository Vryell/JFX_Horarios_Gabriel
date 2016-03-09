package dad.jfx.services.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dad.jfx.db.DataBase;
import dad.jfx.model.Centro;
import dad.jfx.model.JornadaLaboral;
import dad.jfx.services.ICentroService;
import dad.jfx.services.ServiceException;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class CentroService implements ICentroService {
private Connection conn;
	
	public CentroService() {
		String driverClass = "org.sqlite.JDBC";
		String urlConn = "jdbc:sqlite:db/App_JFX.sqlite";
		try {
			Class.forName(driverClass);
			conn = DriverManager.getConnection(urlConn);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	@Override
	public List<Centro> listarCentros() throws ServiceException, SQLException {
		List<Centro> centros = new ArrayList<Centro>();
		Connection conn = DataBase.connect();
		Centro centro;
			PreparedStatement sentencia= conn.prepareStatement(
					"select * from Centro_De_Trabajo");
			ResultSet rs = sentencia.executeQuery();
			while (rs.next()) {
				centro = new Centro();
				centro.setId_centro(rs.getInt("id_centro"));
				centro.setNombre_centro(rs.getString("nombre_centro"));
				centro.setDireccion_centro(rs.getString("direccion_centro"));
				centros.add(centro);
			}		
			conn.close();
		return centros;
	}

}
