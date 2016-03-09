package dad.jfx.services;

import java.sql.SQLException;
import java.util.List;

import dad.jfx.model.Turno;

public interface ITurnoService {
	public List<Turno> listarTurnos() throws ServiceException, SQLException;
	public List<Turno> listarTurnosDeCentro(Integer id_centro) throws ServiceException, SQLException;
}
