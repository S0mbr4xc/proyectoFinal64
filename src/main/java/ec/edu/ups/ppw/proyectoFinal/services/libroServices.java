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
	public List<libro> list(){
		return gl.getAll();
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("{nombre}")
	public libro read(@PathParam("nombre") String nombre) {
		libro li;
		try {
			li = gl.getLibro(nombre);
			return li;
		} catch (Exception e) {
			return null;
		}
	}
	
	@DELETE
	public Response delete(@QueryParam("nombre") String ci) {
		try {
			gl.borrar(ci);
			return Response.ok().build();
		} catch (Exception e) {
			message error = new message(101, e.getMessage());
			return Response.status(Response.Status.NOT_FOUND)
					.entity(error)
					.build();
		}
	}
}
