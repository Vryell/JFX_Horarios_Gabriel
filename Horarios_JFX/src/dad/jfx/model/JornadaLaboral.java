package dad.jfx.model;


import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class JornadaLaboral {
	private StringProperty fecha = new SimpleStringProperty(this, "fecha");
	private ObjectProperty<Centro> id_centro = new SimpleObjectProperty<Centro>(this, "id_centro");
	private ObjectProperty<Turno> id_turno = new SimpleObjectProperty<Turno>(this, "id_turno");
	private ObjectProperty<Area> id_area = new SimpleObjectProperty<Area>(this, "id_area");


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((fecha == null) ? 0 : fecha.hashCode());
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		JornadaLaboral other = (JornadaLaboral) obj;
		if (fecha == null) {
			if (other.fecha != null)
				return false;
		} else if (!fecha.equals(other.fecha))
			return false;
		return true;
	}

	public final ObjectProperty<Centro> id_centroProperty() {
		return this.id_centro;
	}
	

	public final dad.jfx.model.Centro getId_centro() {
		return this.id_centroProperty().get();
	}
	

	public final void setId_centro(final dad.jfx.model.Centro id_centro) {
		this.id_centroProperty().set(id_centro);
	}
	

	public final ObjectProperty<Turno> id_turnoProperty() {
		return this.id_turno;
	}
	

	public final dad.jfx.model.Turno getId_turno() {
		return this.id_turnoProperty().get();
	}
	

	public final void setId_turno(final dad.jfx.model.Turno id_turno) {
		this.id_turnoProperty().set(id_turno);
	}
	

	public final ObjectProperty<Area> id_areaProperty() {
		return this.id_area;
	}
	

	public final dad.jfx.model.Area getId_area() {
		return this.id_areaProperty().get();
	}
	

	public final void setId_area(final dad.jfx.model.Area id_area) {
		this.id_areaProperty().set(id_area);
	}


	public final StringProperty fechaProperty() {
		return this.fecha;
	}
	


	public final java.lang.String getFecha() {
		return this.fechaProperty().get();
	}
	


	public final void setFecha(final java.lang.String fecha) {
		this.fechaProperty().set(fecha);
	}


	@Override
	public String toString() {
		return fecha.get();
	}
	
	
}
