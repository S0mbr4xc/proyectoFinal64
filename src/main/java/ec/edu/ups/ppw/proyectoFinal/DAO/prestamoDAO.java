package ec.edu.ups.ppw.proyectoFinal.DAO;

import java.util.List;

import ec.edu.ups.ppw.proyectoFinal.model.prestamo;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;

@Stateless
public class prestamoDAO {
	
	@PersistenceContext
	private EntityManager em;
	
	public void insert(prestamo pre) {
		em.persist(pre);
	}
	
	public void update(prestamo us) {
		em.merge(us);
	}
	
	public prestamo read(String codigo) {
		return em.find(prestamo.class, codigo);
	}
	
	public List<prestamo> getAll(){
		String jpql = "SELECT c FROM prestamo c ORDER BY codigo";//Nombre de la entidad asi se haya cambiado el nombre
		Query query = em.createQuery(jpql, prestamo.class);
		return query.getResultList();
	}
	
	public void delete(String correo) {
		prestamo us = this.read(correo);
		em.remove(us);
	}
}
