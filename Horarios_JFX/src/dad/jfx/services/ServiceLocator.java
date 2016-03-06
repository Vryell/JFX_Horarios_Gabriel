package dad.jfx.services;

import dad.jfx.impl.AreaService;
import dad.jfx.impl.CentroService;
import dad.jfx.impl.JornadaService;
import dad.jfx.impl.TurnoService;

public class ServiceLocator {
private static ITurnoService turnoService;
private static IJornadaService jornadaService;
private static IAreaService areaService;
private static ICentroService centroService;
	static {
		turnoService = new TurnoService();
		jornadaService = new JornadaService();
		areaService = new AreaService();
		centroService = new CentroService();
	}

	public static IJornadaService getJornadaService() {
		return jornadaService;
	}

	public static ITurnoService getTurnoService() {
		return turnoService;
	}
	
	public static IAreaService getAreaService() {
		return areaService;
	}
	
	public static ICentroService getCentroService() {
		return centroService;
	}

}
