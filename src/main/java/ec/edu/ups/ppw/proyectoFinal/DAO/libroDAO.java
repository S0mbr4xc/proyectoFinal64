package ec.edu.ups.ppw.proyectoFinal.DAO;

import java.util.List;

import ec.edu.ups.ppw.proyectoFinal.model.libro;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;

@Stateless
public class libroDAO {
	
	@PersistenceContext
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
	
	public libro read(String nombre) {
		TypedQuery<libro> query = em.createQuery("SELECT l FROM libro l WHERE l.nombre = :nombre", libro.class);
        query.setParameter("nombre", nombre);
        try {
            return query.getSingleResult();
        } catch (Exception e) {
            return null;
        }
	}
	
	public List<libro> getAll(){
		String jpql = "SELECT c FROM libro c";//Nombre de la entidad asi se haya cambiado el nombre
		Query query = em.createQuery(jpql, libro.class);
		return query.getResultList();
	}
}
