package dad.jfx.services;

import java.sql.SQLException;
import java.util.List;

import dad.jfx.model.Area;

public interface IAreaService {
	public List<Area> listarAreas() throws ServiceException, SQLException;
	public List<Area> listarAreasDeCentro(Integer id_centro) throws ServiceException, SQLException;
}
