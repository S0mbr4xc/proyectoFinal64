package ec.edu.ups.ppw.proyectoFinal.model;

import java.time.LocalDate;
import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.persistence.Transient;

@Entity
public class prestamo {
	
	@Id
	@GeneratedValue
	private int codigo;
	@Temporal(TemporalType.DATE)
    private LocalDate fechaInicio;
    @Temporal(TemporalType.DATE)
    private LocalDate fechaFin;
	private String estado;
	
	@ManyToOne
    @JoinColumn(name = "usuario") // FK hacia Usuario
    private usuario usuario;

    @ManyToOne
    @JoinColumn(name = "libro") // FK hacia Libro
    private libro libro;
    
    @Transient
    private String usuarioEmail; // Transient para el email del usuario
    
    @Transient
    private String libroNombre; // Transient para el nombre del libro
	
	public String getUsuarioEmail() {
		return usuarioEmail;
	}
	public void setUsuarioEmail(String usuarioEmail) {
		this.usuarioEmail = usuarioEmail;
	}
	public String getLibroNombre() {
		return libroNombre;
	}
	public void setLibroNombre(String libroNombre) {
		this.libroNombre = libroNombre;
	}
	public LocalDate getFechaInicio() {
		return fechaInicio;
	}
	public void setFechaInicio(LocalDate fechaInicio) {
		this.fechaInicio = fechaInicio;
	}
	public LocalDate getFechaFin() {
		return fechaFin;
	}
	public void setFechaFin(LocalDate fechaFin) {
		this.fechaFin = fechaFin;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(usuario usuario) {
		this.usuario = usuario;
	}
	public libro getLibro() {
		return libro;
	}
	public void setLibro(libro libro) {
		this.libro = libro;
	}
	@Override
	public String toString() {
		return "prestamo [codigo=" + codigo + ", fechaInicio=" + fechaInicio + ", fechaFin=" + fechaFin + ", estado="
				+ estado + ", usuario=" + usuario + ", libro=" + libro + "]";
	}
	
	
}
