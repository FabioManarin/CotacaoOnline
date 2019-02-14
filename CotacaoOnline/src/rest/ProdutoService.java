package rest;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import dao.ProdutoDao;
import entity.Produto;

@Path("/produto")
public class ProdutoService {

	private static final String CHARSET_UTF8 = ";charset=utf-8";
	
	private ProdutoDao produtoDao;
	
	@PostConstruct
	private void init() {
		produtoDao = new ProdutoDao();
	}
	
	@GET
	@Path("/listarProduto")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Produto> listarProduto() {
		List<Produto> listaProduto = null;
		try {
			listaProduto = produtoDao.ListarProdutos();
		} catch (Exception e){
			e.printStackTrace();
			e.getMessage();
		}
		
		return listaProduto;
	}
	
	@POST
	@Path("/inserirProduto")
	@Consumes(MediaType.APPLICATION_JSON + CHARSET_UTF8)
	@Produces(MediaType.TEXT_PLAIN)
	public String InserirProduto(Produto produto) {
		String msg = "";

		try {
			if (produto.getId() > 0)
				produtoDao.EditarProduto(produto);
			else {
				produtoDao.InserirProduto(produto);
			}
			msg = "Produto inserido com sucesso.";
		} catch (Exception e) {
			msg = "Erro ao inserir produto.";
			e.printStackTrace();
		}

		return msg;
	}
	
	@GET
	@Path("{id}")
	@Consumes(MediaType.TEXT_PLAIN)
	@Produces(MediaType.APPLICATION_JSON)
	public Produto recuperarPorId(@PathParam("id") int idProduto) {
		Produto produto = null;
		try {
			produto = produtoDao.PesquisarPorId(idProduto);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return produto;
	}
	
	@PUT
	@Path("/alterarProduto")
	@Consumes(MediaType.APPLICATION_JSON + CHARSET_UTF8)
	@Produces(MediaType.TEXT_PLAIN)
	public String editarProduto(Produto produto) {
		String msg = "";
		
		try {
			produtoDao.EditarProduto(produto);
			msg = "Produto alterado com sucesso.";
		} catch (Exception e) {
			msg = "Erro ao alterar produto..";
			e.printStackTrace();
		}
		
		return msg;
	}
	
	@DELETE
	@Path("removerProduto/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String removerProduto(@PathParam("id") int idProduto) {
		String msg = "";
		
		try {
			produtoDao.RemoverProduto(idProduto);
			msg = "Produto removido com sucesso.";
		} catch (Exception e) {
			msg = "Erro ao remover o produto.";
			e.printStackTrace();
		}
		
		return msg;
	}
}
