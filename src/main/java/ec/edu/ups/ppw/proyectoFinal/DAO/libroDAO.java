package ec.edu.ups.ppw.proyectoFinal.DAO;

import java.util.List;

import ec.edu.ups.ppw.proyectoFinal.model.categoria;
import ec.edu.ups.ppw.proyectoFinal.model.libro;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;

@Stateless
public class libroDAO {
	
	@PersistenceContext
	private EntityManager em;
	
	public void insertLibro(libro li) {
		TypedQuery<categoria> query = em.createQuery("SELECT c FROM categoria c WHERE c.nombre = :nombre", categoria.class);
        query.setParameter("nombre", li.getCategoriaNombre());
        categoria managedCategoria;
        try {
            managedCategoria = query.getSingleResult();
        } catch (Exception e) {
            throw new IllegalArgumentException("La categoría con el nombre " + li.getCategoriaNombre() + " no existe.");
        }

        // Asignar la categoría gestionada al libro
        li.setCategoria(managedCategoria);

        // Persistir el libro
        em.persist(li);
    }
	
	public void update(libro li) {

        // 1. Buscar la categoría en la base de datos usando el nombre de la categoría recibido
        TypedQuery<categoria> query = em.createQuery("SELECT c FROM categoria c WHERE c.nombre = :nombre", categoria.class);
        query.setParameter("nombre", li.getCategoriaNombre());
        categoria managedCategoria;
        try {
            managedCategoria = query.getSingleResult();
        } catch (Exception e) {
            throw new IllegalArgumentException("La categoría con el nombre " + li.getCategoriaNombre() + " no existe.");
        }
        
        li.setCategoria(managedCategoria);

        // 3. Actualizar el libro
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
		String jpql = "SELECT c FROM libro c ORDER BY codigo";//Nombre de la entidad asi se haya cambiado el nombre
		Query query = em.createQuery(jpql, libro.class);
		return query.getResultList();
	}
	
	public List<libro> getxCategoria(String cat){
		String jpql = "SELECT l FROM libro l JOIN l.categoria c WHERE c.nombre = :categoriaNombre ORDER BY l.codigo";
	    TypedQuery<libro> query = em.createQuery(jpql, libro.class);
	    query.setParameter("categoriaNombre", cat);
	    return query.getResultList();
	}
	
	public List<libro> getLibrosByAutor(String autor) {
	    String jpql = "SELECT l FROM libro l WHERE l.autor = :autor ORDER BY l.codigo";
	    TypedQuery<libro> query = em.createQuery(jpql, libro.class);
	    query.setParameter("autor", autor);
	    return query.getResultList();
	}
	
	public List<libro> getLibrosByDisponibilidad(boolean disponibilidad) {
	    String jpql = "SELECT l FROM libro l WHERE l.disponibilidad = :disponibilidad ORDER BY l.codigo";
	    TypedQuery<libro> query = em.createQuery(jpql, libro.class);
	    query.setParameter("disponibilidad", disponibilidad);
	    return query.getResultList();
	}
}
