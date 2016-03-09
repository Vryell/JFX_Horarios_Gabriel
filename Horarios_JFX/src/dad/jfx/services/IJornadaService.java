package dad.jfx.services;

import java.sql.SQLException;
import java.util.List;

import dad.jfx.model.Area;
import dad.jfx.model.Centro;
import dad.jfx.model.JornadaLaboral;
import dad.jfx.model.Turno;

public interface IJornadaService {
	public List<JornadaLaboral> listarJornadas() throws ServiceException, SQLException;
	public void	insertarJornadaLaboral(String fecha, Centro centro, Area area, Turno turno) throws ServiceException, SQLException;
	public void eliminarJornadaLaboral(String fecha) throws ServiceException, SQLException;
	public void	editarJornadaLaboral(JornadaLaboral jornada) throws ServiceException, SQLException;
}
