package ec.edu.ups.ppw.proyectoFinal.DAO;

import java.util.List;

import ec.edu.ups.ppw.proyectoFinal.model.libro;
import ec.edu.ups.ppw.proyectoFinal.model.prestamo;
import ec.edu.ups.ppw.proyectoFinal.model.usuario;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;

@Stateless
public class prestamoDAO {
	
	@PersistenceContext
	private EntityManager em;
	
	public void insert(prestamo pre) {
		TypedQuery<usuario> userQuery = em.createQuery("SELECT u FROM usuario u WHERE u.usuario = :email", usuario.class);
        userQuery.setParameter("email", pre.getUsuarioEmail());
        usuario managedUsuario;
        try {
            managedUsuario = userQuery.getSingleResult();
        } catch (Exception e) {
            throw new IllegalArgumentException("El usuario con el email " + pre.getUsuarioEmail() + " no existe.");
        }

        // 2. Buscar el libro en la base de datos usando el nombre del libro recibido
        TypedQuery<libro> bookQuery = em.createQuery("SELECT l FROM libro l WHERE l.nombre = :nombre", libro.class);
        bookQuery.setParameter("nombre", pre.getLibroNombre());
        libro managedLibro;
        try {
            managedLibro = bookQuery.getSingleResult();
        } catch (Exception e) {
            throw new IllegalArgumentException("El libro con el nombre " + pre.getLibroNombre() + " no existe.");
        }

        // 3. Asignar el usuario y el libro gestionados al prestamo
        pre.setUsuario(managedUsuario);
        pre.setLibro(managedLibro);

        // 4. Persistir el prestamo
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
	
	public List<prestamo> getUsuario(String nombreUsuario){
		String jpql = "SELECT p FROM prestamo p JOIN p.usuario u WHERE u.usuario = :nombreUsuario ORDER BY p.codigo";
        TypedQuery<prestamo> query = em.createQuery(jpql, prestamo.class);
        query.setParameter("nombreUsuario", nombreUsuario);
        return query.getResultList();
	}
	
	public List<prestamo> getPrestamosByNombreLibro(String nombre) {
        String jpql = "SELECT p FROM prestamo p JOIN p.libro l WHERE l.nombre = :nombre ORDER BY p.codigo";
        TypedQuery<prestamo> query = em.createQuery(jpql, prestamo.class);
        query.setParameter("nombre", nombre);
        return query.getResultList();
    }
}
