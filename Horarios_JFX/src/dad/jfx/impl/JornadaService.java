package dad.jfx.impl;

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
import dad.jfx.model.JornadaLaboral;
import dad.jfx.model.Turno;
import dad.jfx.model.Usuario;
import dad.jfx.services.IJornadaService;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class JornadaService implements IJornadaService {
	private Connection conn;
	
	public JornadaService() {
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
	public List<JornadaLaboral> listarJornadas(Usuario usuario) {
		List<JornadaLaboral> jornadas = new ArrayList<JornadaLaboral>();
		Connection conn = DataBase.connect();
		JornadaLaboral jornada;
		try {
			PreparedStatement sentencia= conn.prepareStatement(
					"select * from Jornada_Laboral where id_usuario = ?");
			sentencia.setInt(1,usuario.getId_usuario());
			ResultSet rs = sentencia.executeQuery();
			while (rs.next()) {
				jornada = new JornadaLaboral();
				jornada.setFecha(rs.getString("fecha"));
				jornada.setId_usuario(usuario);
				jornada.setId_centro(obtenerCentro(rs.getInt("id_centro"), conn));
				jornada.setId_turno(obtenerTurno(rs.getInt("id_turno"), conn));
				jornada.setId_area(obtenerArea(rs.getInt("id_area"), conn));
				jornadas.add(jornada);
			}	
			conn.close();
		} catch (SQLException e) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error");
			alert.setContentText("No se han podido listar las jornadas.");
			alert.showAndWait();
		}
		return jornadas;
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
	
	public Turno obtenerTurno(int id, Connection conn) {
		Turno turno = new Turno();
		try {
			PreparedStatement sentencia = conn.prepareStatement("select * from Turno where id_turno = ?");
			sentencia.setInt(1, id);
			ResultSet resultado = sentencia.executeQuery();
			while (resultado.next()) {
				turno.setId_turno(resultado.getInt("id_turno"));
				turno.setAlias(resultado.getString("alias"));
				turno.setId_centro(obtenerCentro(resultado.getInt("id_centro"),conn));
				turno.setNum_horas(resultado.getInt("num_horas"));
			}
		} catch (SQLException e) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error");
			alert.setContentText("No se ha podido acceder a los turnos.");
			alert.showAndWait();
		}
		return turno;
	}
	
	public Area obtenerArea(int id, Connection conn) {
		Area area = new Area();
		try {
			PreparedStatement sentencia = conn.prepareStatement("select * from Area where id_area = ?");
			sentencia.setInt(1, id);
			ResultSet resultado = sentencia.executeQuery();
			while (resultado.next()) {
				area.setId_area(resultado.getInt("id_area"));
				area.setId_centro(obtenerCentro(resultado.getInt("id_centro"),conn));
				area.setNombre_area(resultado.getString("nombre_area"));
			}
		} catch (SQLException e) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error");
			alert.setContentText("No se ha podido acceder a las áreas de trabajo.");
			alert.showAndWait();
		}
		return area;
	}

	@Override
	public void insertarJornadaLaboral(Usuario usuario, String fecha, Centro centro, Area area, Turno turno) {
		Connection conn = DataBase.connect();
		try {
			PreparedStatement sentencia= conn.prepareStatement(
					"insert into Jornada_Laboral(fecha, id_usuario, id_centro, id_area, id_turno)"
					+ "Values(?,?,?,?,?)");
			sentencia.setString(1, fecha);
			sentencia.setInt(2, usuario.getId_usuario());
			sentencia.setInt(3, centro.getId_centro());
			sentencia.setInt(4, area.getId_area());
			sentencia.setInt(5, turno.getId_turno());
			sentencia.executeUpdate();
			sentencia.close();
			conn.close();
		} catch (SQLException e) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error");
			alert.setContentText("No se ha podido insertar una nueva jornada laboral.");
			alert.showAndWait();
		}
	}

	@Override
	public void eliminarJornadaLaboral(String fecha) {
		Connection conn = DataBase.connect();
		try {
			PreparedStatement sentencia= conn.prepareStatement(
					"delete from Jornada_Laboral where fecha = ?");
			sentencia.setString(1, fecha);
			sentencia.executeUpdate();
			sentencia.close();
			conn.close();
		} catch (SQLException e) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error");
			alert.setContentText("No se ha podido eliminar la jornada laboral.");
			alert.showAndWait();
		}
	}

	@Override
	public void editarJornadaLaboral(JornadaLaboral jornada) {
		Connection conn = DataBase.connect();
		try {
			PreparedStatement sentencia= conn.prepareStatement(
					"update Jornada_Laboral set id_centro =?, id_area =? , id_turno =?"
					+ "where fecha = ?");
			
			sentencia.setInt(1, jornada.getId_centro().getId_centro());
			sentencia.setInt(2, jornada.getId_area().getId_area());
			sentencia.setInt(3, jornada.getId_turno().getId_turno());
			sentencia.setString(4, jornada.getFecha());
			sentencia.executeUpdate();
			sentencia.close();
			conn.close();
		} catch (SQLException e) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error");
			alert.setContentText("No se ha podido editar la jornada laboral.");
			alert.showAndWait();
		}
	}
}
