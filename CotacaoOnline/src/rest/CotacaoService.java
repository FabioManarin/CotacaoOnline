package rest;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Named;
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
import entity.Cotacao;
import entity.Produto;

@Path("/cotacao")
public class CotacaoService {

private static final String CHARSET_UTF8 = ";charset=utf-8";
	
	private CotacaoDao cotacaoDao;
	
	@PostConstruct
	private void init() {
		cotacaoDao = new CotacaoDao();
	}
		
	@GET
	@Path("/listarCotacao")
	@Produces(MediaType.APPLICATION_JSON)
	public Response listarCotacao(@QueryParam("prod") int idProduto) {
		List<Cotacao> listaCotacao = null;
		Gson jsonParse = new Gson();
		JsonObject retorno = new JsonObject();
		
		try {
			listaCotacao = cotacaoDao.ListarCotacoesPorProduto(idProduto);
		} catch (Exception e){
			e.printStackTrace();
			e.getMessage();
		}
		
		if(listaCotacao.isEmpty()){
			retorno.addProperty("success", false);
			retorno.addProperty("message", "Não a registros para exibir.");
			return Response.status(Status.OK).entity(retorno.toString()).build();
		}
		
		return Response.status(Status.OK).entity(jsonParse.toJson(listaCotacao)).build();
	}
	
	@POST
	@Path("/inserirCotacao")
	@Consumes(MediaType.APPLICATION_JSON + CHARSET_UTF8)
	@Produces(MediaType.TEXT_PLAIN)
	public Response inserirCotacao(Cotacao cotacao) {
		JsonObject retorno = new JsonObject();
		
		if (cotacao == null){
			retorno.addProperty("success", false);
			retorno.addProperty("message", "Erro ao inserir cotação. Tente novamente.");
			return Response.status(Status.OK).entity(retorno.toString()).build();
		}
		try {
			cotacaoDao.InserirCotacao(cotacao);
			retorno.addProperty("success", true);
			retorno.addProperty("message", "Cotação inserida com sucesso.");
		} catch (Exception e) {
			e.getMessage();
		}

		return Response.status(Status.OK).entity(retorno.toString()).build();
	}
	
	@GET
	@Path("{id}")
	@Consumes(MediaType.TEXT_PLAIN)
	@Produces(MediaType.APPLICATION_JSON)
	public Cotacao recuperarPorId(@PathParam("id") int idCotacao) {
		Cotacao cotacao = null;
		try {
			cotacao = cotacaoDao.PesquisarPorId(idCotacao);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return cotacao;
	}
	
	@PUT
	@Path("/alterarCotacao")
	@Consumes(MediaType.APPLICATION_JSON + CHARSET_UTF8)
	@Produces(MediaType.TEXT_PLAIN)
	public String editarCotacao(Cotacao cotacao) {
		String msg = "";
		
		try {
			cotacaoDao.EditarCotacao(cotacao);
			msg = "Cotação alterada com sucesso.";
		} catch (Exception e) {
			msg = "Erro ao alterar cotação.";
			e.printStackTrace();
		}
		
		return msg;
	}
	
	@DELETE
	@Path("removerCotacao/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String removerCotacao(@PathParam("id") int idCotacao) {
		String msg = "";
		
		try {
			cotacaoDao.RemoverCotacao(idCotacao);
			msg = "Cotação removida com sucesso.";
		} catch (Exception e) {
			msg = "Erro ao remover cotação.";
			e.printStackTrace();
		}
		
		return msg;
	}
}
