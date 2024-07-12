package ec.edu.ups.ppw.proyectoFinal.model;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

@Entity
public class categoria {
	
	@Id
	@GeneratedValue
	private int codigo;
	
	private String nombre;
	

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@Override
	public String toString() {
		return "categoria [codigo=" + codigo + ", nombre=" + nombre + "]";
	}
	
	
}
