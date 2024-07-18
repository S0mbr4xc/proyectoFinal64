package ec.edu.ups.ppw.proyectoFinal.business;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import ec.edu.ups.ppw.proyectoFinal.DAO.categoriaDAO;
import ec.edu.ups.ppw.proyectoFinal.DAO.libroDAO;
import ec.edu.ups.ppw.proyectoFinal.DAO.prestamoDAO;
import ec.edu.ups.ppw.proyectoFinal.DAO.usuarioDAO;
import ec.edu.ups.ppw.proyectoFinal.model.categoria;
import ec.edu.ups.ppw.proyectoFinal.model.libro;
import ec.edu.ups.ppw.proyectoFinal.model.prestamo;
import ec.edu.ups.ppw.proyectoFinal.model.usuario;
import ec.edu.ups.ppw.proyectoFinal.services.libroServices;
import jakarta.annotation.PostConstruct;
import jakarta.ejb.Singleton;
import jakarta.ejb.Startup;
import jakarta.inject.Inject;

@Singleton
@Startup
public class inicio {
	
	@Inject
	private categoriaDAO gc;
	
	@Inject
	private libroDAO gs;
	
	@Inject
	private usuarioDAO gu;
	
	@Inject
	private prestamoDAO gp;
	
	@SuppressWarnings("deprecation")
	@PostConstruct
	public void init() {
		System.out.println("----------------INICIANDO----------------");
		
		// Categorias de libros a insertar
		String[] categorias = {"Drama", "Ciencia Ficcion", "Fantasia", "Historia", "Biografia",
		                       "Misterio", "Romance", "Aventura", "Terror", "Poesia"};

		// Bucle para crear e insertar cada categoria
		for (String nombreCategoria : categorias) {
		    categoria cat = new categoria();  
		    cat.setNombre(nombreCategoria);   
		    gc.insert(cat);                   
		}

		
		libro li = new libro();
		
		li.setNombre("50 Sombras de Diego");
		li.setPrecio(15.0);
		li.setCategoria(gc.read("Romance"));
		li.setImagen("https://firebasestorage"
				+ ".googleapis.com/v0/b/project"
				+ "-biblioteca.appspot.com/o/"
				+ "images%2F50%20Sombras.jpg?"
				+ "alt=media&token=fe2ff5e1-441b"
				+ "-4954-b95b-d5f2772b1e2b");
		li.setDisponibilidad(false);
		li.setAutor("Andres Chabla");
		System.out.println(li.toString());
		
		gs.insertLibro(li);
		
		li = new libro();
		
		li.setAutor("Pedro Castillo");
		li.setNombre("El Poder Es Ahora");
		li.setCategoria(gc.read("Poesia"));
		li.setDisponibilidad(true);
		li.setImagen("https://firebasestorage.googleapis.com/v0/b/"	
				+ "project-biblioteca.appspot.com/o/images%2Flibro4."
				+ "jpg?alt=media&token=19ba240c-afa4-48ca-af87-048db7"
				+ "db4c51");
		li.setPrecio(65.00);
		
		System.out.println(li.toString());
		
		gs.insertLibro(li);
		
		usuario us = new usuario();
		us.setUsuario("loljaxdlololo@gmail.com");
		us.setRol("admin");
		
		gu.insert(us);
		
		us = new usuario();
		us.setUsuario("chablaandres@gmail.com");
		us.setRol("common");
		
		gu.insert(us);
		
		prestamo pre = new prestamo();
		
		pre.setFechaInicio(LocalDate.now());
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate fechaFin = LocalDate.parse("2024-07-17", formatter);
		
		pre.setFechaFin(fechaFin);
		pre.setLibro(li);
		pre.setUsuario(us);
		pre.setEstado("Reservado");
		
		gp.insert(pre);
	}
}
