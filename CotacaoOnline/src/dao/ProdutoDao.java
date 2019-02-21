package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import bean.ResourceBean;
import entity.Produto;

public class ProdutoDao {

	public void InserirProduto(Produto produto)throws Exception {
		EntityManager em = ResourceBean.getEntityManagerFactory().createEntityManager();

        try {
            em.getTransaction().begin();
            em.persist(produto);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();

            throw new Exception(e);
        }finally {
            em.close();
        }
	}
	
	@SuppressWarnings("unchecked")
	public List<Produto> ListarProdutos(boolean comCotacao) throws Exception {
        EntityManager em = ResourceBean.getEntityManagerFactory().createEntityManager();
        List<Produto> listaProdutos = null;
        Query query = comCotacao? 
        			em.createQuery("from Produto p where exists (select 1 from Cotacao c where c.produto.id = p.id) ") :
        			em.createQuery("from Produto");
        
        try {
        	listaProdutos = query.getResultList();
        } catch (Exception e) {
            throw new Exception();
        } finally {
        	em.close();
        }
        return listaProdutos;
    }
	
	@SuppressWarnings("unchecked")
	public List<Produto> listarProdutosDisponiveis() throws Exception {
        EntityManager em = ResourceBean.getEntityManagerFactory().createEntityManager();
        List<Produto> listaProdutos = null;
        
        try {
        	Query query = em.createQuery("from Produto where dataInicialCotacao <= curdate() and dataFinalCotacao >= curdate()");
        	listaProdutos = query.getResultList();
        } catch (Exception e) {
            throw new Exception();
        } finally {
        	em.close();
        }
        return listaProdutos;
    }
	
	public void EditarProduto(Produto produto) throws Exception {
        EntityManager em = ResourceBean.getEntityManagerFactory().createEntityManager();

        try {
            em.getTransaction().begin();
            em.merge(produto);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();

            throw new Exception(e);
        } finally {
            em.close();
        }
    }
	
	public String RemoverProduto(int id) throws Exception {
        EntityManager em = ResourceBean.getEntityManagerFactory().createEntityManager();
        String msg = "";
        try {
            em.getTransaction().begin();
            Produto produto = em.find(Produto.class, id);
            em.remove(produto);
            em.getTransaction().commit();
        } catch (Exception e)  {
            em.getTransaction().rollback();
            throw new Exception(e);
        } finally {
            em.close();
        }
        
        return msg;
    }
	
	public Produto PesquisarPorId(int id) throws Exception {
    	Produto produto;

        EntityManager em = ResourceBean.getEntityManagerFactory().createEntityManager();

        try {
        	produto = em.find(Produto.class, id);
        } finally {
            em.close();
        }
        return produto;
    }
}
