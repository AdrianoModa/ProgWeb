package br.unifor.aluno.ads.produto.rest;

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

import br.unifor.aluno.ads.produto.BO.ProdutoBO;
import br.unifor.aluno.ads.produto.entitys.Produto;

@RequestScoped
@Path("/produto")
public class ProdutoResourceRESTService {
	
	@Inject
	private ProdutoBO produtoBO;
	
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response exibeProduto(){
		List<Produto> listaProdutos = produtoBO.verProdutos();
		return Response.ok(listaProdutos).build();
	}
	
	@POST
	@Path("/inserir/{nome}/{descricao}/{preco}/{disponivel}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response insereProduto(@PathParam("nome") String nome, @PathParam("descricao") String descricao, @PathParam("preco") Integer preco, @PathParam("disponivel") boolean isDisponivel){
		Produto produto = new Produto();
		produto.setNome(nome);
		produto.setDescricao(descricao);
		produto.setPreco(preco);
		produto.setDisponivel(isDisponivel);
		produtoBO.inserirNovoProduto(produto);
		return Response.ok().build();
	}	
	
	@PUT
	@Path("/atualizar/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response atualizarContato(@PathParam("id") Long id, Produto produto){
		Produto produtoExistente = produtoBO.buscarPorId(id);
		if(produtoExistente == null){
			throw new WebApplicationException(Status.NOT_MODIFIED);
		}
		produtoExistente.setNome(produto.getNome());
		produtoExistente.setPreco(produto.getPreco());
		produtoBO.atualizarProduto(produtoExistente);
		return Response.ok().build();
	}
	
	@DELETE
	@Path("/remover/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response deletarContato(@PathParam("id") Long id){
		Produto produto = produtoBO.buscarPorId(id);
		if(produto == null){
			throw new WebApplicationException(Status.NOT_MODIFIED);
		}
		produtoBO.removerProduto(produto);
		return Response.ok().build();
	}
}
