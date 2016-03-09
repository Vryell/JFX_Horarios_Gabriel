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
import dad.jfx.model.JornadaLaboral;
import dad.jfx.model.Turno;
import dad.jfx.services.IJornadaService;

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
	public List<JornadaLaboral> listarJornadas() throws SQLException {
		List<JornadaLaboral> jornadas = new ArrayList<JornadaLaboral>();
		Connection conn = DataBase.connect();
		JornadaLaboral jornada;
			PreparedStatement sentencia= conn.prepareStatement(
					"select * from Jornada_Laboral");
			ResultSet rs = sentencia.executeQuery();
			while (rs.next()) {
				jornada = new JornadaLaboral();
				jornada.setFecha(rs.getString("fecha"));
				jornada.setId_centro(obtenerCentro(rs.getInt("id_centro"), conn));
				jornada.setId_turno(obtenerTurno(rs.getInt("id_turno"), conn));
				jornada.setId_area(obtenerArea(rs.getInt("id_area"), conn));
				jornadas.add(jornada);
			}	
			conn.close();
		return jornadas;
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
	
	public Turno obtenerTurno(int id, Connection conn) throws SQLException {
		Turno turno = new Turno();
			PreparedStatement sentencia = conn.prepareStatement("select * from Turno where id_turno = ?");
			sentencia.setInt(1, id);
			ResultSet resultado = sentencia.executeQuery();
			while (resultado.next()) {
				turno.setId_turno(resultado.getInt("id_turno"));
				turno.setAlias(resultado.getString("alias"));
				turno.setId_centro(obtenerCentro(resultado.getInt("id_centro"),conn));
				turno.setNum_horas(resultado.getInt("num_horas"));
			}
		return turno;
	}
	
	public Area obtenerArea(int id, Connection conn) throws SQLException {
		Area area = new Area();
			PreparedStatement sentencia = conn.prepareStatement("select * from Area where id_area = ?");
			sentencia.setInt(1, id);
			ResultSet resultado = sentencia.executeQuery();
			while (resultado.next()) {
				area.setId_area(resultado.getInt("id_area"));
				area.setId_centro(obtenerCentro(resultado.getInt("id_centro"),conn));
				area.setNombre_area(resultado.getString("nombre_area"));
			}
		return area;
	}

	@Override
	public void insertarJornadaLaboral(String fecha, Centro centro, Area area, Turno turno) throws SQLException {
		Connection conn = DataBase.connect();
			PreparedStatement sentencia= conn.prepareStatement(
					"insert into Jornada_Laboral(fecha, id_centro, id_area, id_turno)"
					+ "Values(?,?,?,?)");
			sentencia.setString(1, fecha);
			sentencia.setInt(2, centro.getId_centro());
			sentencia.setInt(3, area.getId_area());
			sentencia.setInt(4, turno.getId_turno());
			sentencia.executeUpdate();
			sentencia.close();
			conn.close();
	}

	@Override
	public void eliminarJornadaLaboral(String fecha) throws SQLException {
		Connection conn = DataBase.connect();
			PreparedStatement sentencia= conn.prepareStatement(
					"delete from Jornada_Laboral where fecha = ?");
			sentencia.setString(1, fecha);
			sentencia.executeUpdate();
			sentencia.close();
			conn.close();
	}

	@Override
	public void editarJornadaLaboral(JornadaLaboral jornada) throws SQLException {
		Connection conn = DataBase.connect();
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
	}
}
