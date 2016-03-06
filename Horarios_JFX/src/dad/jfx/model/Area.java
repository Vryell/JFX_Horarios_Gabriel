package dad.jfx.model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Area {
	private IntegerProperty id_area = new SimpleIntegerProperty(this, "id_area");
	private StringProperty nombre_area = new SimpleStringProperty(this, "nombre_area");
	private ObjectProperty<Centro> id_centro = new SimpleObjectProperty<Centro>(this, "id_centro");
	public final IntegerProperty id_areaProperty() {
		return this.id_area;
	}
	
	public final int getId_area() {
		return this.id_areaProperty().get();
	}
	
	public final void setId_area(final int id_area) {
		this.id_areaProperty().set(id_area);
	}
	
	public final StringProperty nombre_areaProperty() {
		return this.nombre_area;
	}
	
	public final java.lang.String getNombre_area() {
		return this.nombre_areaProperty().get();
	}
	
	public final void setNombre_area(final java.lang.String nombre_area) {
		this.nombre_areaProperty().set(nombre_area);
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id_area == null) ? 0 : id_area.hashCode());
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
		Area other = (Area) obj;
		if (id_area == null) {
			if (other.id_area != null)
				return false;
		} else if (!id_area.equals(other.id_area))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return nombre_area.get();
	}
	
		
}
