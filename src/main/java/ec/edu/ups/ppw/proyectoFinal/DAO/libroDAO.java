package ec.edu.ups.ppw.proyectoFinal.DAO;

import ec.edu.ups.ppw.proyectoFinal.model.libro;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;

public class libroDAO {
	@Inject
	private EntityManager em;
	
	public void insertLibro(libro li) {
		em.persist(li);
	}
	
	public void update(libro li) {
		em.merge(li);
	}
	
	public void delete(libro li) {
		em.remove(li);
	}
	
	public void read(String li) {
		
	}
}
