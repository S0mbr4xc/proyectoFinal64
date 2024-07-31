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
		li.setCategoriaNombre("Romance");
		li.setImagen("https://firebasestorage.googleapis.com/v0/b/project-biblioteca.appspot.com/o/images%2F50%20Sombras.jpg?alt=media&token=8494f548-bfe7-40c5-99e5-0541bbb24a42");
		li.setDisponibilidad(false);
		li.setAutor("Andres Chabla");
		li.setStock(10); // Stock inicial
		System.out.println(li.toString());
		gs.insertLibro(li);

		li = new libro();

		li.setAutor("Pedro Castillo");
		li.setNombre("El Poder Es Ahora");
		li.setCategoriaNombre("Poesia");
		li.setDisponibilidad(true);
		li.setImagen("https://firebasestorage.googleapis.com/v0/b/project-biblioteca.appspot.com/o/images%2Flibro4.jpg?alt=media&token=19ba240c-afa4-48ca-af87-048db7db4c51");
		li.setPrecio(65.00);
		li.setStock(5); // Stock inicial
		System.out.println(li.toString());
		gs.insertLibro(li);

		li = new libro();
		li.setAutor("Laura Esquivel");
		li.setNombre("Como Agua Para Chocolate");
		li.setCategoriaNombre("Drama");
		li.setDisponibilidad(true);
		li.setImagen("https://firebasestorage.googleapis.com/"
				+ "v0/b/project-biblioteca.appspot.com/o/"
				+ "images%2FComo%20agua%20para%20chocolate.webp?alt"
				+ "=media&token=ff32a52e-6fe4-4e1a-a998-4a56e7d0b38a");
		li.setPrecio(30.00);
		li.setStock(7); // Stock inicial
		System.out.println(li.toString());
		gs.insertLibro(li);

		li = new libro();
		li.setAutor("Isaac Asimov");
		li.setNombre("Fundación");
		li.setCategoriaNombre("Ciencia Ficcion");
		li.setDisponibilidad(true);
		li.setImagen("https://firebasestorage.googleapis.com/v0/b/"
				+ "project-biblioteca.appspot.com/o/images%2Ffundacion"
				+ ".webp?alt=media&token=50ddcc7f-e4bd-4d10-8f5d-17e34fd5a10a");
		li.setPrecio(45.00);
		li.setStock(12); // Stock inicial
		System.out.println(li.toString());
		gs.insertLibro(li);

		li = new libro();
		li.setAutor("J.R.R. Tolkien");
		li.setNombre("El Señor de los Anillos");
		li.setCategoriaNombre("Fantasia");
		li.setDisponibilidad(false);
		li.setImagen("https://firebasestorage.googleapis.com/v0/b/"
				+ "project-biblioteca.appspot.com/o/images%2FSr%"
				+ "20Anillos.webp?alt=media&token=a6ef1522-eca7-4858"
				+ "-afa1-f67913abd2e2");
		li.setPrecio(50.00);
		li.setStock(3); // Stock inicial
		System.out.println(li.toString());
		gs.insertLibro(li);

		li = new libro();
		li.setAutor("Walter Isaacson");
		li.setNombre("Steve Jobs");
		li.setCategoriaNombre("Biografia");
		li.setDisponibilidad(true);
		li.setImagen("https://firebasestorage.googleapis.com/v0/b/"
				+ "project-biblioteca.appspot.com/o/images%2FSteve"
				+ ".webp?alt=media&token=e43ba160-b997-4c7e-a84d-4aa0816de951");
		li.setPrecio(40.00);
		li.setStock(8); // Stock inicial
		System.out.println(li.toString());
		gs.insertLibro(li);

		li = new libro();
		li.setAutor("Agatha Christie");
		li.setNombre("Asesinato en el Orient Express");
		li.setCategoriaNombre("Misterio");
		li.setDisponibilidad(false);
		li.setImagen("https://firebasestorage.googleapis.com/"
				+ "v0/b/project-biblioteca.appspot.com/o/"
				+ "images%2FOrient.jpeg?alt=media&token="
				+ "e3a3325b-e72e-407a-bb09-4ae2a2e5b8af");
		li.setPrecio(35.00);
		li.setStock(4); // Stock inicial
		System.out.println(li.toString());
		gs.insertLibro(li);

		li = new libro();
		li.setAutor("Gabriel García Márquez");
		li.setNombre("Cien Años de Soledad");
		li.setCategoriaNombre("Aventura");
		li.setDisponibilidad(true);
		li.setImagen("https://firebasestorage.googleapis.com"
				+ "/v0/b/project-biblioteca.appspot.com/o/"
				+ "images%2Fcien%20a%C3%B1os%20de%20soledad."
				+ "jpg?alt=media&token=6acbff07-5c01-4f13-b4ac-ff1ac26a177b");
		li.setPrecio(25.00);
		li.setStock(9); // Stock inicial
		System.out.println(li.toString());
		gs.insertLibro(li);

		li = new libro();
		li.setAutor("Stephen King");
		li.setNombre("El Resplandor");
		li.setCategoriaNombre("Terror");
		li.setDisponibilidad(true);
		li.setImagen("https://firebasestorage.googleapis.com/v0/b/project-biblioteca.appspot.com/"
				+ "o/images%2Fresplandor.webp?alt=media&token=e684ca76-b369-4522-9f17-cc2580a94484");
		li.setPrecio(60.00);
		li.setStock(6); // Stock inicial
		System.out.println(li.toString());
		gs.insertLibro(li);

		li = new libro();
		li.setAutor("Pablo Neruda");
		li.setNombre("Veinte Poemas de Amor y una Canción Desesperada");
		li.setCategoriaNombre("Poesia");
		li.setDisponibilidad(false);
		li.setImagen("https://firebasestorage.googleapis.com/"
				+ "v0/b/project-biblioteca.appspot.com/o/"
				+ "images%2Fveinte.webp?alt=media&token=1ca62524"
				+ "-d77c-4497-92ed-689156a68498");
		li.setPrecio(20.00);
		li.setStock(10); // Stock inicial
		System.out.println(li.toString());
		gs.insertLibro(li);

		li = new libro();
		li.setAutor("Julio Cortazar");
		li.setNombre("Rayuela");
		li.setPrecio(15.00);
		li.setImagen("https://firebasestorage.googleapis.com/"
				+ "v0/b/project-biblioteca.appspot.com/o/"
				+ "images%2FRayuela.webp?alt=media&token=e5090523-f39f-4351-ae8d-7b2f42880ab5");
		li.setDisponibilidad(false);
		li.setCategoriaNombre("Romance");
		li.setStock(8); // Stock inicial
		System.out.println(li.toString());
		gs.insertLibro(li);

        
		usuario us = new usuario();
		us.setUsuario("loljaxdlololo@gmail.com");
		us.setRol("admin");
		
		gu.insert(us);
		
		us = new usuario();
		us.setUsuario("chablaandres@gmail.com");
		us.setRol("admin");
		
		gu.insert(us);
		
		prestamo pre = new prestamo();

		pre.setFechaInicio(LocalDate.now());

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDate fechaFin = LocalDate.parse("2024-07-17", formatter);

		pre.setFechaFin(fechaFin);
		pre.setLibroNombre("Rayuela");
		pre.setUsuarioEmail("chablaandres@gmail.com");
		pre.setEstado("Reservado");

		gp.insert(pre);

		pre = new prestamo();

		pre.setFechaInicio(LocalDate.now());
		fechaFin = LocalDate.parse("2024-07-23", formatter);
		pre.setFechaFin(fechaFin);

		pre.setLibroNombre("50 Sombras de Diego");
		pre.setUsuarioEmail("loljaxdlololo@gmail.com");
		pre.setEstado("Reservado");
		gp.insert(pre);

		pre = new prestamo();

		pre.setFechaInicio(LocalDate.parse("2024-05-13", formatter));
		fechaFin = LocalDate.parse("2024-06-13", formatter);
		pre.setFechaFin(fechaFin);

		pre.setLibroNombre("Rayuela");
		pre.setUsuarioEmail("loljaxdlololo@gmail.com");
		pre.setEstado("Atrasado");
		gp.insert(pre);

	}
}
