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
	private RetMessage retMessage;
	
	@PostConstruct
	private void init() {
		cotacaoDao = new CotacaoDao();
		retMessage = new RetMessage();
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
			return retMessage.returnMessage(false, "Não a registros para exibir.");
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
			return retMessage.returnMessage(false, "Erro ao inserir cotação. Tente novamente.");
		}
		try {
			if (cotacaoDao.VerificaSeJaPossuiCotacao(cotacao)){
				return retMessage.returnMessage(false, "Produto já possui cotação do fornecedor.");
			}
			cotacaoDao.InserirCotacao(cotacao);
			retorno.addProperty("success", true);
			retorno.addProperty("message", "Cotação inserida com sucesso.");
		} catch (Exception e) {
			e.getMessage();
		}

		return Response.status(Status.OK).entity(retorno.toString()).build();
	}
}
