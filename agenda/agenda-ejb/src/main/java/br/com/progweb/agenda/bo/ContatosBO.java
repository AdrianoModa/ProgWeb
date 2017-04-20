package br.com.progweb.agenda.bo;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import br.com.progweb.agenda.dao.ContatosDAO;
import br.com.progweb.agenda.entitys.Contatos;

@Stateless
public class ContatosBO {
	
	@EJB
	private ContatosDAO contatoDAO;
	
	public void inserirNovoContato(Contatos contato){
		contatoDAO.salvar(contato);
	}
	
	public Contatos atualizarContato(Contatos contato){
		return contatoDAO.atualizar(contato);
	}
	
	public void removerContato(Contatos contato){
		Contatos contatoARemover = contatoDAO.buscarPorId(contato.getId());
		contatoDAO.remover(contatoARemover);
	}
	
	public Contatos buscarPorId(Long id){
		return contatoDAO.buscarPorId(id);
	}
	
	public List<Contatos> lerAgenda(){
		return contatoDAO.buscarTodos();
	}
}
