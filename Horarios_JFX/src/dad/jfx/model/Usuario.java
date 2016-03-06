package dad.jfx.model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Usuario {
	private IntegerProperty id_usuario = new SimpleIntegerProperty(this, "id_usuario");
	private StringProperty nombre_usuario = new SimpleStringProperty(this,"nombre_usuario");
	private StringProperty correo_electronico = new SimpleStringProperty(this, "correo_electronico");
	private StringProperty clave = new SimpleStringProperty(this, "clave");
	public final IntegerProperty id_usuarioProperty() {
		return this.id_usuario;
	}
	
	public final int getId_usuario() {
		return this.id_usuarioProperty().get();
	}
	
	public final void setId_usuario(final int id_usuario) {
		this.id_usuarioProperty().set(id_usuario);
	}
	
	public final StringProperty nombre_usuarioProperty() {
		return this.nombre_usuario;
	}
	
	public final java.lang.String getNombre_usuario() {
		return this.nombre_usuarioProperty().get();
	}
	
	public final void setNombre_usuario(final java.lang.String nombre_usuario) {
		this.nombre_usuarioProperty().set(nombre_usuario);
	}
	
	public final StringProperty correo_electronicoProperty() {
		return this.correo_electronico;
	}
	
	public final java.lang.String getCorreo_electronico() {
		return this.correo_electronicoProperty().get();
	}
	
	public final void setCorreo_electronico(final java.lang.String correo_electronico) {
		this.correo_electronicoProperty().set(correo_electronico);
	}
	
	public final StringProperty claveProperty() {
		return this.clave;
	}
	
	public final java.lang.String getClave() {
		return this.claveProperty().get();
	}
	
	public final void setClave(final java.lang.String clave) {
		this.claveProperty().set(clave);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id_usuario == null) ? 0 : id_usuario.hashCode());
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
		Usuario other = (Usuario) obj;
		if (id_usuario == null) {
			if (other.id_usuario != null)
				return false;
		} else if (!id_usuario.equals(other.id_usuario))
			return false;
		return true;
	}
}
