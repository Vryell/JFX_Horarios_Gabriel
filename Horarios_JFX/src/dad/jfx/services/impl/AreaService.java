package dad.jfx.services.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dad.jfx.db.DataBase;
import dad.jfx.model.Area;
import dad.jfx.model.Centro;
import dad.jfx.model.Turno;
import dad.jfx.services.IAreaService;
import dad.jfx.services.ServiceException;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class AreaService implements IAreaService {
	
	private Connection conn;
	
	public AreaService() {
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
	public List<Area> listarAreas() throws ServiceException, SQLException {
		List<Area> areas = new ArrayList<Area>();
		Connection conn = DataBase.connect();
		Area area;
			PreparedStatement sentencia= conn.prepareStatement(
					"select * from Area");
			ResultSet rs = sentencia.executeQuery();
			while (rs.next()) {
				area = new Area();
				area.setId_area(rs.getInt("id_area"));
				area.setId_centro(obtenerCentro(rs.getInt("id_centro"), conn));
				area.setNombre_area(rs.getString("nombre_area"));
				areas.add(area);
			}		
			conn.close();
		
		
		return areas;
	}
	
	public Centro obtenerCentro(int id, Connection conn) throws SQLException {
		Centro centro = new Centro();
			PreparedStatement sentencia = conn.prepareStatement("select * from Centro_De_Trabajo where id_centro = ?");
			sentencia.setInt(1, id);
			ResultSet resultado = sentencia.executeQuery();
			while (resultado.next()) {
				centro.setId_centro(resultado.getInt("id_centro"));
				centro.setNombre_centro(resultado.getString("nombre_centro"));
				centro.setDireccion_centro(resultado.getString("direccion_centro"));
			}
		
		return centro;
	}

	@Override
	public List<Area> listarAreasDeCentro(Integer id_centro) throws ServiceException, SQLException {
		List<Area> areas = new ArrayList<Area>();
		Connection conn = DataBase.connect();
		Area area;
			PreparedStatement sentencia= conn.prepareStatement(
					"select * from Area where id_centro = ?");
			sentencia.setInt(1,id_centro);
			ResultSet rs = sentencia.executeQuery();
			while (rs.next()) {
				area = new Area();
				area.setId_area(rs.getInt("id_area"));
				area.setId_centro(obtenerCentro(rs.getInt("id_centro"), conn));
				area.setNombre_area(rs.getString("nombre_area"));
				areas.add(area);
			}		
			conn.close();
		return areas;
	}

}
