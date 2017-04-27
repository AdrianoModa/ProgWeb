package br.unifor.aluno.ads.produto.bo;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import br.unifor.aluno.ads.produto.dao.ProdutoDAO;
import br.unifor.aluno.ads.produto.entitys.Produto;

@Stateless
public class ProdutoBO {
	
	@EJB
	private ProdutoDAO produtoDAO;
	
	public void inserirNovoProduto(Produto produto){
		produtoDAO.salvarProduto(produto);
	}
	
	public Produto atualizarProduto(Produto produto){
		return produtoDAO.atualizarProduto(produto);
	}
	
	public void removerProduto(Produto produto){
		Produto produtoARemover = produtoDAO.buscarPorId(produto.getId());
		produtoDAO.removerProduto(produtoARemover);
	}
	
	public Produto buscarPorId(Long id){
		return produtoDAO.buscarPorId(id);
	}
	
	public List<Produto> verProdutos(){
		return produtoDAO.buscarTodos();
	}
	
}
