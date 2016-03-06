package dad.jfx.services;

import java.util.List;

import dad.jfx.model.Area;
import dad.jfx.model.Centro;
import dad.jfx.model.JornadaLaboral;
import dad.jfx.model.Turno;
import dad.jfx.model.Usuario;

public interface IJornadaService {
	public List<JornadaLaboral> listarJornadas(Usuario usuario) throws ServiceException;
	public void	insertarJornadaLaboral(Usuario usuario, String fecha, Centro centro, Area area, Turno turno) throws ServiceException;
	public void eliminarJornadaLaboral(String fecha) throws ServiceException;
	public void	editarJornadaLaboral(JornadaLaboral jornada) throws ServiceException;
}
