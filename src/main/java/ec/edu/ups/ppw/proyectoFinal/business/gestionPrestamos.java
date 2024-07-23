package ec.edu.ups.ppw.proyectoFinal.business;

import java.util.List;

import ec.edu.ups.ppw.proyectoFinal.DAO.prestamoDAO;
import ec.edu.ups.ppw.proyectoFinal.model.prestamo;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;

@Stateless
public class gestionPrestamos {

	@Inject
	private prestamoDAO dao;
	
	public void crear(prestamo us) {
		dao.insert(us);
	}
	
	public prestamo read(String correo) {
		return dao.read(correo);
	}
	
	public void update(prestamo us) {
		dao.update(us);
	}
	
	public void delete(String correo) {
		dao.delete(correo);
	}
	
	public List<prestamo> getAll(){
		return dao.getAll();
	}
	
	public List<prestamo> getUsuario(String usuario){
		return dao.getUsuario(usuario);
	}
	
	public List<prestamo> getLibro(String libro){
		return dao.getPrestamosByNombreLibro(libro);
	}
}
