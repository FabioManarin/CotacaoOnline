package teste;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Test;

import dao.ProdutoDao;
import entity.Produto;

public class ProdutoTeste {

	ProdutoDao produtoDao = new ProdutoDao();
	Produto produto = new Produto();
	
	public SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
	
	@Test
	public void TestInserir () {
		
		try{
			produto.setNome("computador");
			produto.setDataInicialCotacao(formato.parse("02/02/2019"));
			produto.setDataFinalCotacao(formato.parse("02/02/2019"));
		} catch (Exception e) {
			System.out.println("Erro: " + e.getMessage());
		}
			
		try {
			produtoDao.InserirProduto(produto);
		} catch (Exception e) {
			System.out.println("Message: " + e.getMessage());
		}
	}
	
}
