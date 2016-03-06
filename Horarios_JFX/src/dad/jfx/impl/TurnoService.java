package dad.jfx.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.omg.Messaging.SyncScopeHelper;

import dad.jfx.db.DataBase;
import dad.jfx.model.Centro;
import dad.jfx.model.Turno;
import dad.jfx.services.ITurnoService;
import dad.jfx.services.ServiceException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class TurnoService implements ITurnoService {
	
	private Connection conn;
	
	public TurnoService() {
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
	public List<Turno> listarTurnos() {
		List<Turno> turnos = new ArrayList<Turno>();
		Connection conn = DataBase.connect();
		Turno turno;
		try {
			PreparedStatement sentencia= conn.prepareStatement(
					"select * from Turno");
			ResultSet rs = sentencia.executeQuery();
			while (rs.next()) {
				turno = new Turno();
				turno.setId_turno(rs.getInt("id_turno"));
				turno.setAlias(rs.getString("alias"));
				turno.setId_centro(obtenerCentro(rs.getInt("id_centro"), conn));
				turno.setNum_horas(rs.getInt("num_horas"));
				turnos.add(turno);
			}	
			conn.close();
		} catch (SQLException e) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error");
			alert.setContentText("No se han podido listar los turnos.");
			alert.showAndWait();
		}
		return turnos;
	}
	
	public Centro obtenerCentro(int id, Connection conn) {
		Centro centro = new Centro();
		
		try {
			PreparedStatement sentencia = conn.prepareStatement("select * from Centro_De_Trabajo where id_centro = ?");
			sentencia.setInt(1, id);
			ResultSet resultado = sentencia.executeQuery();
			while (resultado.next()) {
				centro.setId_centro(resultado.getInt("id_centro"));
				centro.setNombre_centro(resultado.getString("nombre_centro"));
				centro.setDireccion_centro(resultado.getString("direccion_centro"));
			}
		} catch (SQLException e) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error");
			alert.setContentText("No se ha podido acceder a los centros de trabajo.");
			alert.showAndWait();
		}
		return centro;
	}

	@Override
	public List<Turno> listarTurnosDeCentro(Integer id_centro) {
		List<Turno> turnos = new ArrayList<Turno>();
		Connection conn = DataBase.connect();
		Turno turno;
		try {
			PreparedStatement sentencia= conn.prepareStatement(
					"select * from Turno where id_centro =?");
			sentencia.setInt(1, id_centro);
			ResultSet rs = sentencia.executeQuery();
			while (rs.next()) {
				turno = new Turno();
				turno.setId_turno(rs.getInt("id_turno"));
				turno.setAlias(rs.getString("alias"));
				turno.setId_centro(obtenerCentro(rs.getInt("id_centro"), conn));
				turno.setNum_horas(rs.getInt("num_horas"));
				turnos.add(turno);
			}		
			conn.close();
		} catch (SQLException e) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error");
			alert.setContentText("No se han podido listar los turnos.");
			alert.showAndWait();
		}
		return turnos;
	}

}
