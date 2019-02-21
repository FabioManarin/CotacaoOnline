package rest;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

import dao.CotacaoDao;
import dao.ProdutoDao;
import entity.Cotacao;
import entity.Produto;

@Path("/produto")
public class ProdutoService {

	private static final String CHARSET_UTF8 = ";charset=utf-8";
	
	private ProdutoDao produtoDao;
	private RetMessage retMessage;
	
	@PostConstruct
	private void init() {
		produtoDao = new ProdutoDao();
		retMessage = new RetMessage();
	}
	
	@GET
	@Path("/listarProduto")
	@Produces(MediaType.APPLICATION_JSON)
	public Response listarProduto(@QueryParam("comCotacao") boolean comCotacao) throws Exception {
		List<Produto> listaProduto = null;
		Gson jsonParse = new GsonBuilder().setDateFormat("dd-MM-yyyy").create();
		
		try {
			listaProduto = produtoDao.ListarProdutos(comCotacao);
		} catch (Exception e){
			e.printStackTrace();
			e.getMessage();
		}
		
		if(listaProduto.isEmpty()){
			return retMessage.returnMessage(false, "Não a registros para exibir.");
		}
		
		return Response.status(Status.OK).entity(jsonParse.toJson(listaProduto)).build();
	}
	
	@GET
	@Path("/listarProdutosDisponiveis")
	@Produces(MediaType.APPLICATION_JSON)
	public Response listarProdutosDisponiveis() throws Exception {
		List<Produto> listaProduto = null;
		Gson jsonParse = new GsonBuilder().setDateFormat("dd-MM-yyyy").create();

		try {
			listaProduto = produtoDao.listarProdutosDisponiveis();
		} catch (Exception e){
			e.printStackTrace();
			e.getMessage();
		}

		if(listaProduto.isEmpty()){
			return retMessage.returnMessage(false, "Não a registros para exibir.");
		}
		
		return Response.status(Status.OK).entity(jsonParse.toJson(listaProduto)).build();
	}
	
	@POST
	@Path("/inserirProduto")
	@Consumes(MediaType.APPLICATION_JSON + CHARSET_UTF8)
	@Produces(MediaType.TEXT_PLAIN)
	public Response InserirProduto(Produto produto) {
		JsonObject retorno = new JsonObject();
		
		if (produto == null){
			return retMessage.returnMessage(false, "Erro ao inserir produto. Tente novamente.");
		}
		
		try {
			produtoDao.InserirProduto(produto);
			retorno.addProperty("success", true);
			retorno.addProperty("message", "Produto inserido com sucesso.");
		} catch (Exception e) {
			e.printStackTrace();
		}

		return Response.status(Status.OK).entity(retorno.toString()).build();
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
	public Response alterarProduto(Produto produto) {
		Gson jsonParse = new Gson();
		JsonObject retorno = new JsonObject();
		
		if (produto == null){
			return retMessage.returnMessage(false, "Erro ao editar produto. Tente novamente.");
		}
		
		try {
			produtoDao.EditarProduto(produto);
			retorno.addProperty("success", true);
			retorno.addProperty("message", "Produto alterado com sucesso.");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return Response.status(Status.OK).entity(jsonParse.toJson(retorno)).build();
	}
	
	@DELETE
	@Path("removerProduto/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public Response removerProduto(@PathParam("id") int idProduto) {
		JsonObject retorno = new JsonObject();
		
		if (idProduto == 0){
			return retMessage.returnMessage(false, "Erro ao remover produto. Tente novamente.");
		}
		try {
			List<Cotacao> listacotacoes = new CotacaoDao().ListarCotacoesPorProduto(idProduto); 
			
			if (listacotacoes.size() > 0){
				return retMessage.returnMessage(false, "Não é possível remover produtos que já foram cotados.");
			}
			
			produtoDao.RemoverProduto(idProduto);
			retorno.addProperty("success", true);
			retorno.addProperty("message", "Produto removido com sucesso.");
		} catch (Exception e) {
			e.getMessage();
		}
		return Response.status(Status.OK).entity(retorno.toString()).build();
	}
}
