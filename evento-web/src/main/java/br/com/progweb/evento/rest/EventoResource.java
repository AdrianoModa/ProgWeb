package br.com.progweb.evento.rest;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import br.com.progweb.evento.bo.EventoBO;
import br.com.progweb.evento.entitys.Evento;

@RequestScoped
@Path("/evento/contatos")
public class EventoResource {
	
	@Inject
	private EventoBO eventoBO;
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response listaEventos(){
		List<Evento> eventos = eventoBO.verEvento();
		return Response.ok(eventos).build();
	}
	
	@POST
	@Path("/evento/{nomeLocal}/{duracao}/{data}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response novoEvento(@PathParam("nomeLocal") String nomeLocal, @PathParam("duracao") Integer duracao, @PathParam("data") String data){
		Evento evento = new Evento();
		evento.setNomeLocal(nomeLocal);
		evento.setDuracao(duracao);
		evento.setData(data);
		eventoBO.inserirNovoEvento(evento);
		return Response.ok().build();
	}
	
	@PUT
	@Path("/evento/{id}/{nomeLocal}/{duracao}/{data}")
	public Response atualizarEvento(@PathParam("id") 
	Long id, @PathParam("nomeLocal") String nomeLocal, @PathParam("duracao") 
	Integer duracao, @PathParam("data") String data){
		Evento evento = eventoBO.buscarPorId(id);
		evento.setNomeLocal(nomeLocal);
		evento.setDuracao(duracao);
		evento.setData(data);
		eventoBO.atualizarEvento(evento);
		return Response.ok().build();
	}
	
	@PUT
	@Path("/evento/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response atualizarEvento(@PathParam("id") Long id, Evento evento){
		Evento eventoExistente = eventoBO.buscarPorId(id);
		if(eventoExistente == null){
			throw new WebApplicationException(Status.NOT_MODIFIED);
		}
		eventoExistente.setNomeLocal(evento.getNomeLocal());
		eventoExistente.setDuracao(evento.getDuracao());
		eventoBO.atualizarEvento(eventoExistente);
		return Response.ok().build();
	}
	
	@DELETE
	@Path("/evento/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response deletarEvento(@PathParam("id") Long id){
		Evento evento = eventoBO.buscarPorId(id);
		if(evento == null){
			throw new WebApplicationException(Status.NOT_MODIFIED);
		}
		eventoBO.removerEvento(evento);
		return Response.ok().build();
	}
}
