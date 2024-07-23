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
		System.out.println(li.toString());

		gs.insertLibro(li);

		li = new libro();

		li.setAutor("Pedro Castillo");
		li.setNombre("El Poder Es Ahora");
		li.setCategoriaNombre("Poesia");
		li.setDisponibilidad(true);
		li.setImagen("https://firebasestorage.googleapis.com/v0/b/project-biblioteca.appspot.com/o/images%2Flibro4.jpg?alt=media&token=19ba240c-afa4-48ca-af87-048db7db4c51");
		li.setPrecio(65.00);

		System.out.println(li.toString());

		gs.insertLibro(li);

		li = new libro();
		li.setAutor("Laura Esquivel");
		li.setNombre("Como Agua Para Chocolate");
		li.setCategoriaNombre("Drama");
		li.setDisponibilidad(true);
		li.setImagen("https://www.mrbooks.com/mrbooks/portadas/9786073130288.webp");
		li.setPrecio(30.00);
		System.out.println(li.toString());
		gs.insertLibro(li);

		li = new libro();
		li.setAutor("Isaac Asimov");
		li.setNombre("Fundación");
		li.setCategoriaNombre("Ciencia Ficcion");
		li.setDisponibilidad(true);
		li.setImagen("https://images.cdn2.buscalibre.com/fit-in/360x360/e3/2b/e32bce1befb83115aaa523e239a42eab.jpg");
		li.setPrecio(45.00);
		System.out.println(li.toString());
		gs.insertLibro(li);

		li = new libro();
		li.setAutor("J.R.R. Tolkien");
		li.setNombre("El Señor de los Anillos");
		li.setCategoriaNombre("Fantasia");
		li.setDisponibilidad(false);
		li.setImagen("https://www.mrbooks.com/mrbooks/portadas/9786287574045.webp");
		li.setPrecio(50.00);
		System.out.println(li.toString());
		gs.insertLibro(li);

		li = new libro();
		li.setAutor("Walter Isaacson");
		li.setNombre("Steve Jobs");
		li.setCategoriaNombre("Biografia");
		li.setDisponibilidad(true);
		li.setImagen("https://images.cdn2.buscalibre.com/fit-in/360x360/17/00/17004a1ca6d14e56e5d295978d80d158.jpg");
		li.setPrecio(40.00);
		System.out.println(li.toString());
		gs.insertLibro(li);

		li = new libro();
		li.setAutor("Agatha Christie");
		li.setNombre("Asesinato en el Orient Express");
		li.setCategoriaNombre("Misterio");
		li.setDisponibilidad(false);
		li.setImagen("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQ26ofEPe7Lh6JHRwInp41WbawkS0GOOnw0cA&s");
		li.setPrecio(35.00);
		System.out.println(li.toString());
		gs.insertLibro(li);

		li = new libro();
		li.setAutor("Gabriel García Márquez");
		li.setNombre("Cien Años de Soledad");
		li.setCategoriaNombre("Aventura");
		li.setDisponibilidad(true);
		li.setImagen("https://www.antena3.com/newa3flash/modulos_blancos/uploader/uploads/cien%20a%C3%B1os%20de%20soledad.jpg");
		li.setPrecio(25.00);
		System.out.println(li.toString());
		gs.insertLibro(li);

		li = new libro();
		li.setAutor("Stephen King");
		li.setNombre("El Resplandor");
		li.setCategoriaNombre("Terror");
		li.setDisponibilidad(true);
		li.setImagen("https://images.cdn3.buscalibre.com/fit-in/360x360/49/66/49661480fa1f78034b80bae7ed020841.jpg");
		li.setPrecio(60.00);
		System.out.println(li.toString());
		gs.insertLibro(li);

		li = new libro();
		li.setAutor("Pablo Neruda");
		li.setNombre("Veinte Poemas de Amor y una Canción Desesperada");
		li.setCategoriaNombre("Poesia");
		li.setDisponibilidad(false);
		li.setImagen("https://www.mrbooks.com/mrbooks/portadas/9788432212796.webp");
		li.setPrecio(20.00);
		System.out.println(li.toString());
		gs.insertLibro(li);

		li = new libro();
		li.setAutor("Julio Cortazar");
		li.setNombre("Rayuela");
		li.setPrecio(15.00);
		li.setImagen("https://images.cdn2.buscalibre.com/fit-in/360x360/26/ec/26ec71a5396959c17c45d21cb4a815b3.jpg");
		li.setDisponibilidad(false);
		li.setCategoriaNombre("Romance");
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
