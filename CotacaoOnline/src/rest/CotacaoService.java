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
import javax.ws.rs.core.MediaType;

import dao.CotacaoDao;
import entity.Cotacao;

@Path("/cotacao")
public class CotacaoService {

private static final String CHARSET_UTF8 = ";charset=utf-8";
	
	private CotacaoDao cotacaoDao;
	
	@PostConstruct
	private void init() {
		cotacaoDao = new CotacaoDao();
	}
	
	@GET
	@Path("/list")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Cotacao> listarCotacao() {
		List<Cotacao> listaCotacao = null;
		try {
			listaCotacao = cotacaoDao.ListarCotacoes();
		} catch (Exception e){
			e.printStackTrace();
			e.getMessage();
		}
		
		return listaCotacao;
	}
	
	@POST
	@Path("/add")
	@Consumes(MediaType.APPLICATION_JSON + CHARSET_UTF8)
	@Produces(MediaType.TEXT_PLAIN)
	public String InserirCotacao(Cotacao cotacao) {
		String msg = "";

		try {
			if (cotacao.getId() > 0)
				cotacaoDao.EditarCotacao(cotacao);
			else {
				cotacaoDao.InserirCotacao(cotacao);
			}
			msg = "Cotação inserida com sucesso.";
		} catch (Exception e) {
			msg = "Erro ao inserir cotação.";
			e.printStackTrace();
		}

		return msg;
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
	@Path("/edit")
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
	@Path("remover/{id}")
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
