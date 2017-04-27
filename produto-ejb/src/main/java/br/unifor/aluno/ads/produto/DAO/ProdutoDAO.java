package br.unifor.aluno.ads.produto.DAO;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import br.unifor.aluno.ads.produto.entitys.Produto;

@Stateless
public class ProdutoDAO {
	
	@PersistenceContext
	private EntityManager em;
	
	public void salvarProduto(Produto produto){
		em.persist(produto);
	}
	
	public Produto atualizarProduto(Produto produto){
		return em.merge(produto);
	}
	
	public void removerProduto(Produto produto){
		em.remove(produto);
	}
	
	public Produto buscarPorId(Long id){
		return em.find(Produto.class, id);
	}
	
	public List<Produto> buscarTodos(){
		String jpql = "select c from Produto c";
		TypedQuery<Produto> query = em.createQuery(jpql, Produto.class);		
		return query.getResultList();
	}
}
