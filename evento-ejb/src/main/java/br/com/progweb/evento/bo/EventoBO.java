package br.com.progweb.evento.bo;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import br.com.progweb.evento.dao.EventoDAO;
import br.com.progweb.evento.entitys.Evento;

@Stateless
public class EventoBO {
	
	@EJB
	private EventoDAO eventoDAO;
	
	public void inserirNovoEvento(Evento evento){
		eventoDAO.salvar(evento);
	}
	
	public Evento atualizarEvento(Evento evento){
		return eventoDAO.atualizar(evento);
	}
	
	public void removerEvento(Evento evento){
		Evento eventoARemover = eventoDAO.buscarPorId(evento.getId());
		eventoDAO.remover(eventoARemover);
	}
	
	public Evento buscarPorId(Long id){
		return eventoDAO.buscarPorId(id);
	}
	
	public List<Evento> verEvento(){
		return eventoDAO.buscarTodos();
	}
}
