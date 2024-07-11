package ec.edu.ups.ppw.proyectoFinal.model;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;

@Entity
public class usuario {
	
	@Id
	private int codigo;
	private String usuario;
	private boolean rol;
	
	@OneToMany(cascade =  CascadeType.ALL)
	@JoinColumn(name = "historial")
	private List<prestamo> historial;
	
	public List<prestamo> getHistorial() {
		return historial;
	}
	public void setHistorial(List<prestamo> historial) {
		this.historial = historial;
	}
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
	public boolean isRol() {
		return rol;
	}
	public void setRol(boolean rol) {
		this.rol = rol;
	}
}
