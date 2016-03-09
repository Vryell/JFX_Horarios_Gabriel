package dad.jfx.services;

import java.sql.SQLException;
import java.util.List;

import dad.jfx.model.Centro;

public interface ICentroService {
	public List<Centro> listarCentros() throws ServiceException, SQLException;
}
