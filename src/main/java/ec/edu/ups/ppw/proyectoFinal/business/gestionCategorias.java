package ec.edu.ups.ppw.proyectoFinal.business;

import java.util.List;

import ec.edu.ups.ppw.proyectoFinal.DAO.categoriaDAO;
import ec.edu.ups.ppw.proyectoFinal.model.categoria;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;

@Stateless
public class gestionCategorias {

	@Inject
	private categoriaDAO dao;
	
	public void agregarCategoria(categoria cat) {
		dao.insert(cat);
	}
	
	public void actualizar(categoria cat) {
		dao.update(cat);
	}
	
	public void eliminar(String nombre) {
		dao.delete(nombre);
	}
	
	public categoria getCategoria(String nombre) {
		return dao.read(nombre);
	}
	
	public List<categoria> getAll(){
		return dao.getAll();
	}
}
