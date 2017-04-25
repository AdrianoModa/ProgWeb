package br.com.progweb.evento.dao;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import br.com.progweb.evento.entitys.Evento;

@Stateless
public class EventoDAO {
	
	@PersistenceContext
	private EntityManager em;
	
	public void salvar(Evento evento){
		em.persist(evento);
	}
	
	public Evento atualizar(Evento evento){
		return em.merge(evento);
	}
	
	public void remover(Evento evento){
		em.remove(evento);
	}
	
	public Evento buscarPorId(Long id){
		return em.find(Evento.class, id);
	}
	
	public List<Evento> buscarTodos(){
		String jpql = "select c from Evento c";
		TypedQuery<Evento> query = em.createQuery(jpql, Evento.class);		
		return query.getResultList();
	}	
}
