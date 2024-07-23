package ec.edu.ups.ppw.proyectoFinal.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Transient;


@Entity
public class libro {
	
	@Id
	@GeneratedValue
	private int codigo;
	
	@Column(unique = true)
	private String nombre;
	private Double precio;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "categoria")
	private categoria categoria;
	
	private String autor;
	private String imagen;
	private boolean disponibilidad;
	
	@Transient
    private String categoriaNombre;
	
	/*@OneToMany(mappedBy = "libro", fetch = FetchType.EAGER)
	@JsonManagedReference
    private List<prestamo> prestamos;*/
	
	public String getCategoriaNombre() {
		return categoriaNombre;
	}
	public void setCategoriaNombre(String categoriaNombre) {
		this.categoriaNombre = categoriaNombre;
	}
	public int getCodigo() {
		return codigo;
	}
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	public boolean isDisponibilidad() {
		return disponibilidad;
	}
	public void setDisponibilidad(boolean disponibilidad) {
		this.disponibilidad = disponibilidad;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public Double getPrecio() {
		return precio;
	}
	public void setPrecio(Double precio) {
		this.precio = precio;
	}
	public categoria getCategoria() {
		return categoria;
	}
	public void setCategoria(categoria categoria) {
		this.categoria = categoria;
	}
	public String getAutor() {
		return autor;
	}
	public void setAutor(String autor) {
		this.autor = autor;
	}
	public String getImagen() {
		return imagen;
	}
	public void setImagen(String imagen) {
		this.imagen = imagen;
	}
	/*public List<prestamo> getPrestamos() {
		return prestamos;
	}
	public void setPrestamos(List<prestamo> prestamos) {
		this.prestamos = prestamos;
	}*/
	@Override
	public String toString() {
		return "libro [codigo=" + codigo + ", nombre=" + nombre + ", precio=" + precio + ", categoria=" + categoria
				+ ", autor=" + autor + ", imagen=" + imagen + ", disponibilidad=" + disponibilidad + ", prestamos="
				+ "]";
	}
	
	/*public void addPrestamo(prestamo pre) {
		if(prestamos==null) {
			prestamos = new ArrayList<prestamo>();
		}
		prestamos.add(pre);
	}*/
	
}
