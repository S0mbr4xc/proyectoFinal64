package ec.edu.ups.ppw.proyectoFinal.model;

import java.time.LocalDate;
import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
public class prestamo {
	
	@Id
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
	
	
}
