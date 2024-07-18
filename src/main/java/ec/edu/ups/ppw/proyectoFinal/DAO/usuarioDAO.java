package ec.edu.ups.ppw.proyectoFinal.DAO;

import java.util.List;

import ec.edu.ups.ppw.proyectoFinal.model.usuario;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;

@Stateless
public class usuarioDAO {
	
	@PersistenceContext
	private EntityManager em;
	
	public void insert(usuario us) {
		em.persist(us);
	}
	
	public void update(usuario us) {
		em.merge(us);
	}
	
	public usuario read(String usuario) {
		TypedQuery<usuario> query = em.createQuery("SELECT l FROM usuario l WHERE l.usuario = :usuario", usuario.class);
        query.setParameter("usuario", usuario);
        try {
            return query.getSingleResult();
        } catch (Exception e) {
            return null;
        }
	}
	
	public List<usuario> getAll(){
		String jpql = "SELECT c FROM usuario c ORDER BY codigo";//Nombre de la entidad asi se haya cambiado el nombre
		Query query = em.createQuery(jpql, usuario.class);
		return query.getResultList();
	}
	
	public void delete(String correo) {
		usuario us = this.read(correo);
		em.remove(us);
	}
}




