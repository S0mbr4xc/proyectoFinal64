package ec.edu.ups.ppw.proyectoFinal.business;

import java.util.List;

import ec.edu.ups.ppw.proyectoFinal.DAO.usuarioDAO;
import ec.edu.ups.ppw.proyectoFinal.model.usuario;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;

@Stateless
public class gestionUsuarios {

	@Inject
	private usuarioDAO dao;
	
	public void crear(usuario us) {
		dao.insert(us);
	}
	
	public usuario read(String correo) {
		return dao.read(correo);
	}
	
	public void update(usuario us) {
		dao.update(us);
	}
	
	public void delete(String correo) {
		dao.delete(correo);
	}
	
	public List<usuario> getAll(){
		return dao.getAll();
	}
}
