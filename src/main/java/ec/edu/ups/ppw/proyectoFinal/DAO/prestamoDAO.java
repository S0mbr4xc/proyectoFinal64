package ec.edu.ups.ppw.proyectoFinal.DAO;

import java.time.LocalDate;
import java.util.List;

import ec.edu.ups.ppw.proyectoFinal.model.libro;
import ec.edu.ups.ppw.proyectoFinal.model.prestamo;
import ec.edu.ups.ppw.proyectoFinal.model.usuario;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
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
	
	public List<usuario> getUsuariosConPrestamos() {
        String jpql = "SELECT DISTINCT p.usuario FROM prestamo p";
        TypedQuery<usuario> query = em.createQuery(jpql, usuario.class);
        return query.getResultList();
    }

    // Obtener los préstamos por usuario
    public List<prestamo> getPrestamosPorUsuario(usuario user) {
        String jpql = "SELECT p FROM prestamo p WHERE p.usuario = :usuario";
        TypedQuery<prestamo> query = em.createQuery(jpql, prestamo.class);
        query.setParameter("usuario", user);
        return query.getResultList();
    }
    
    public libro getLibroByName(String nombre) {
        TypedQuery<libro> query = em.createQuery("SELECT l FROM libro l WHERE l.nombre = :nombre", libro.class);
        query.setParameter("nombre", nombre);
        try {
            return query.getSingleResult();
        } catch (NoResultException e) {
            return null; // O lanza una excepción si prefieres manejarlo de otra forma
        }
    }
    
    public long getCountReservasByLibro(String nombreLibro) {
        TypedQuery<Long> query = em.createQuery(
            "SELECT COUNT(p) FROM prestamo p WHERE p.libro.nombre = :nombreLibro AND p.estado = 'Reservado'", Long.class);
        query.setParameter("nombreLibro", nombreLibro);
        return query.getSingleResult();
    }
    
    public List<prestamo> getReservasEntreFechas(LocalDate fechaInicio, LocalDate fechaFin) {
        TypedQuery<prestamo> query = em.createQuery(
            "SELECT p FROM prestamo p WHERE p.fechaInicio >= :fechaInicio AND p.fechaFin <= :fechaFin", 
            prestamo.class
        );
        query.setParameter("fechaInicio", fechaInicio);
        query.setParameter("fechaFin", fechaFin);
        return query.getResultList();
    }

}
