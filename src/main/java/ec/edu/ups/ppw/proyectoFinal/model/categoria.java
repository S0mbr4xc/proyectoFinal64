package ec.edu.ups.ppw.proyectoFinal.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class categoria {
	
	@Id
	@GeneratedValue
	private int codigo;
	@Column(unique = true)//
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
