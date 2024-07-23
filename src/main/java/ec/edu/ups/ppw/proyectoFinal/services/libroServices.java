package ec.edu.ups.ppw.proyectoFinal.services;

import java.util.List;

import ec.edu.ups.ppw.proyectoFinal.business.gestionLibros;
import ec.edu.ups.ppw.proyectoFinal.model.libro;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/libros")
public class libroServices {
	
	@Inject
	private gestionLibros gl;
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response create(libro libro) {
		try {
			gl.setLibro(libro);
			return Response.ok(libro).build();
		} catch (Exception e) {
			message error = new message(1, e.getMessage());
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
					.entity(error)
					.build();
		}
	}
	
	@PUT
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response update(libro libro) {
		try {
			gl.actualiar(libro);
			return Response.ok(libro).build();
		} catch (Exception e) {
			message error = new message(100, e.getMessage());
			return Response.status(Response.Status.NOT_FOUND)
					.entity(error)
					.build();
		}
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response list(){
		List<libro> libros = gl.getAll();
		if(libros.size()>0) {
			return Response.ok(libros).build();
		}else {
			message em = new message(10, "No se registran libros");
			return Response.status(Response.Status.NOT_FOUND)
					.entity(em)
					.build();
		}
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("{nombre}")
	public Response read(@PathParam("nombre") String nombre) {
		libro li;
		try {
			li = gl.getLibro(nombre);
			return Response.ok(li).build();
		} catch (Exception e) {
			message em = new message(11, "No se encuentra el libro");
			return Response.status(Response.Status.NOT_FOUND)
					.entity(em)
					.build();
		}
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/categorias/{nombre}")
	public Response readCategoria(@PathParam("nombre") String nombre) {
		List<libro> li;
		try {
			li = gl.getCategoria(nombre);
			return Response.ok(li).build();
		} catch (Exception e) {
			message em = new message(11, "No se encuentra el libro");
			return Response.status(Response.Status.NOT_FOUND)
					.entity(em)
					.build();
		}
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/autor/{nombre}")
	public Response readAutor(@PathParam("nombre") String nombre) {
		List<libro> li;
		try {
			li = gl.getAutor(nombre);
			return Response.ok(li).build();
		} catch (Exception e) {
			message em = new message(11, "No se encuentra el libro");
			return Response.status(Response.Status.NOT_FOUND)
					.entity(em)
					.build();
		}
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/disponibilidad/{nombre}")
	public Response readDisponibilidad(@PathParam("nombre") Boolean nombre) {
		List<libro> li;
		try {
			li = gl.getDisponibilidad(nombre);
			return Response.ok(li).build();
		} catch (Exception e) {
			message em = new message(11, "No se encuentra el libro");
			return Response.status(Response.Status.NOT_FOUND)
					.entity(em)
					.build();
		}
	}
	
	@DELETE
	@Produces(MediaType.APPLICATION_JSON)
	public Response delete(@QueryParam("nombre") String ci) {
		try {
			if(gl.getLibro(ci)==null) {
				throw new Exception("Libro no encontrado");
			}else {
				gl.borrar(ci);
				return Response.ok().build();
			}
		} catch (Exception e) {
			message error = new message(101, "Libro no encontrado");
			return Response.status(Response.Status.NOT_FOUND)
					.entity(error)
					.build();
		}
	}
}
