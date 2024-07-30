package ec.edu.ups.ppw.proyectoFinal.business;

import java.util.List;

import ec.edu.ups.ppw.proyectoFinal.DAO.libroDAO;
import ec.edu.ups.ppw.proyectoFinal.model.libro;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;

@Stateless
public class gestionLibros {
	@Inject
	private libroDAO dao;
	
	public void setLibro(libro li) {
		dao.insertLibro(li);
	}
	
	public void actualiar(libro cliente) {
		dao.update(cliente);
	}
	
	public void borrar(String ci) {
		libro cli = dao.read(ci);
		dao.delete(cli);
	}
	
	public List<libro> getAll(){
		return dao.getAll();
	}
	
	public libro getLibro(String nombre) throws Exception {
		libro li =  dao.read(nombre);
		if(nombre.length()<1) {
			throw new Exception("Nombre Incorrecto");
		}
		if(li==null) {
			throw new Exception("Libro no existe");
		}
		return li;
	}
	
	public List<libro> getCategoria(String cat){
		return dao.getxCategoria(cat);
	}
	
	public List<libro> getAutor(String autor){
		return dao.getLibrosByAutor(autor);
	}
	
	public List<libro> getDisponibilidad(boolean dispo){
		return dao.getLibrosByDisponibilidad(dispo);
	}
	
	public void reducirStock(libro libro) throws Exception {
        if (libro.getStock() > 0) {
            libro.setStock(libro.getStock() - 1);
            dao.update(libro);
        } else {
            throw new Exception("No hay suficiente stock para este libro");
        }
    }
    
    public void aumentarStock(libro libro) {
        libro.setStock(libro.getStock() + 1);
        dao.update(libro);
    }
}
