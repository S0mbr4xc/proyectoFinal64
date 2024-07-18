package ec.edu.ups.ppw.proyectoFinal.model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;

@Entity
public class usuario {
	
	@Id
	@GeneratedValue
	private int codigo;
	
	@Column(unique = true)
	private String usuario;
	private String rol;
	
	/*@OneToMany(fetch = FetchType.EAGER, mappedBy = "usuario")
	@JsonManagedReference
	private List<prestamo> historial;
	
	public List<prestamo> getHistorial() {
		return historial;
	}
	public void setHistorial(List<prestamo> historial) {
		this.historial = historial;
	}*/
	public int getCodigo() {
		return codigo;
	}
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	public String isRol() {
		return rol;
	}
	public void setRol(String rol) {
		this.rol = rol;
	}
	@Override
	public String toString() {
		return "usuario [codigo=" + codigo + ", usuario=" + usuario + ", rol=" + rol + ", historial=" + "]";
	}
	public String getRol() {
		return rol;
	}
	
	/*public void addPrestamo(prestamo pre) {
		if(historial==null) {
			historial = new ArrayList<prestamo>();
		}
		historial.add(pre);
	}*/
	
	
}
