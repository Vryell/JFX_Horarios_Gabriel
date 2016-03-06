package dad.jfx.model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Centro {
	private IntegerProperty id_centro = new SimpleIntegerProperty(this, "id_centro");
	private StringProperty nombre_centro = new SimpleStringProperty(this, "nombre_centro");
	private StringProperty direccion_centro = new SimpleStringProperty(this, "direccion_centro");
	public final IntegerProperty id_centroProperty() {
		return this.id_centro;
	}
	
	public final int getId_centro() {
		return this.id_centroProperty().get();
	}
	
	public final void setId_centro(final int id_centro) {
		this.id_centroProperty().set(id_centro);
	}
	
	public final StringProperty nombre_centroProperty() {
		return this.nombre_centro;
	}
	
	public final java.lang.String getNombre_centro() {
		return this.nombre_centroProperty().get();
	}
	
	public final void setNombre_centro(final java.lang.String nombre_centro) {
		this.nombre_centroProperty().set(nombre_centro);
	}
	
	public final StringProperty direccion_centroProperty() {
		return this.direccion_centro;
	}
	
	public final java.lang.String getDireccion_centro() {
		return this.direccion_centroProperty().get();
	}
	
	public final void setDireccion_centro(final java.lang.String direccion_centro) {
		this.direccion_centroProperty().set(direccion_centro);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id_centro == null) ? 0 : id_centro.hashCode());
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
		Centro other = (Centro) obj;
		if (id_centro == null) {
			if (other.id_centro != null)
				return false;
		} else if (!id_centro.equals(other.id_centro))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return nombre_centro.get();
	}
}
