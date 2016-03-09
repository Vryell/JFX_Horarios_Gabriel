package dad.jfx.model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Turno {
	private IntegerProperty id_turno = new SimpleIntegerProperty(this,"id_turno");
	private ObjectProperty<Centro> id_centro = new SimpleObjectProperty<Centro>(this, "id_centro");
	private StringProperty alias = new SimpleStringProperty(this, "alias");
	private IntegerProperty num_horas = new SimpleIntegerProperty(this, "num_horas");
	private StringProperty descripcion = new SimpleStringProperty(this, "descripcion");
	public final IntegerProperty id_turnoProperty() {
		return this.id_turno;
	}
	
	public final int getId_turno() {
		return this.id_turnoProperty().get();
	}
	
	public final void setId_turno(final int id_turno) {
		this.id_turnoProperty().set(id_turno);
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
	
	public final StringProperty aliasProperty() {
		return this.alias;
	}
	
	public final java.lang.String getAlias() {
		return this.aliasProperty().get();
	}
	
	public final void setAlias(final java.lang.String alias) {
		this.aliasProperty().set(alias);
	}
	
	public final IntegerProperty num_horasProperty() {
		return this.num_horas;
	}
	
	public final int getNum_horas() {
		return this.num_horasProperty().get();
	}
	
	public final void setNum_horas(final int num_horas) {
		this.num_horasProperty().set(num_horas);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id_turno == null) ? 0 : id_turno.hashCode());
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
		Turno other = (Turno) obj;
		if (id_turno == null) {
			if (other.id_turno != null)
				return false;
		} else if (!id_turno.equals(other.id_turno))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return alias.get();
	}

	public final StringProperty descripcionProperty() {
		return this.descripcion;
	}
	

	public final java.lang.String getDescripcion() {
		return this.descripcionProperty().get();
	}
	

	public final void setDescripcion(final java.lang.String descripcion) {
		this.descripcionProperty().set(descripcion);
	}
	
	
	
}
