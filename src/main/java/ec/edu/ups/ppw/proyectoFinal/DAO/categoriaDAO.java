package ec.edu.ups.ppw.proyectoFinal.DAO;

import java.util.List;

import ec.edu.ups.ppw.proyectoFinal.model.categoria;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;

@Stateless
public class categoriaDAO {

	@PersistenceContext
	private EntityManager em;
	
	public void insert(categoria cat) {
		em.persist(cat);
	}
	
	public void update(categoria cat) {
		em.persist(cat);
	}
	
	public void delete(categoria li) {
		em.remove(li);
	}
	
	public categoria read(String nombre) {
		TypedQuery<categoria> query = em.createQuery("SELECT l FROM categoria l WHERE l.nombre = :nombre", categoria.class);
        query.setParameter("nombre", nombre);
        try {
            return query.getSingleResult();
        } catch (Exception e) {
            return null;
        }
	}
	
	public List<categoria> getAll(){
		String jpql = "SELECT c FROM categoria c";//Nombre de la entidad asi se haya cambiado el nombre
		Query query = em.createQuery(jpql, categoria.class);
		return query.getResultList();
	}
}
