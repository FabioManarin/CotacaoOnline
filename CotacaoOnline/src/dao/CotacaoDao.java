package dao;

import java.util.List;

import javax.persistence.EntityManager;

import bean.ResourceBean;
import entity.Cotacao;
import entity.Produto;

public class CotacaoDao {

	public Boolean VerificaSeJaPossuiCotacao(Cotacao cotacao) throws Exception{
		
		List<Cotacao> lista = ListarCotacoesPorProduto(cotacao.getProduto().getId());
		
		for (Cotacao item : lista) {
			if (item.getEmailFornec().equals(cotacao.getEmailFornec())){
				return true;
			}
		}
		return false;
	}
	
	public void InserirCotacao(Cotacao cotacao)throws Exception {
		EntityManager em = ResourceBean.getEntityManagerFactory().createEntityManager();

        try {
            em.getTransaction().begin();
            em.persist(cotacao);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();

            throw new Exception(e);
        }finally {
            em.close();
        }
	}
	
	@SuppressWarnings("unchecked")
	public List<Cotacao> ListarCotacoesPorProduto(int idProduto) throws Exception {
        EntityManager em = ResourceBean.getEntityManagerFactory().createEntityManager();
        List<Cotacao> listaCotacao = null;

        try {
        	listaCotacao = em.createQuery("from Cotacao where idproduto = " + idProduto).getResultList();
        } catch (Exception e) {
            throw new Exception();
        } finally {
            em.close();
        }
        return listaCotacao;
    }
	
	@SuppressWarnings("unchecked")
	public List<Cotacao> ListarCotacoes() throws Exception {
        EntityManager em = ResourceBean.getEntityManagerFactory().createEntityManager();
        List<Cotacao> listaCotacao = null;

        try {
        	listaCotacao = em.createQuery("from Cotacao").getResultList();
        } catch (Exception e) {
            throw new Exception();
        } finally {
            em.close();
        }
        return listaCotacao;
    }
	
	public void EditarCotacao(Cotacao cotacao) throws Exception {
        EntityManager em = ResourceBean.getEntityManagerFactory().createEntityManager();

        try {
            em.getTransaction().begin();
            em.merge(cotacao);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();

            throw new Exception(e);
        } finally {
            em.close();
        }
    }
	
	public void RemoverCotacao(int id) throws Exception {
        EntityManager em = ResourceBean.getEntityManagerFactory().createEntityManager();

        try {
            em.getTransaction().begin();
            Cotacao cotacao = em.find(Cotacao.class, id);
            em.remove(cotacao);
            em.getTransaction().commit();
        } catch (Exception e)  {
            em.getTransaction().rollback();

            throw new Exception(e);
        } finally {
            em.close();
        }
    }
	
	public Cotacao PesquisarPorId(int id) throws Exception {
		Cotacao cotacao;

        EntityManager em = ResourceBean.getEntityManagerFactory().createEntityManager();

        try {
        	cotacao = em.find(Cotacao.class, id);
        } finally {
            em.close();
        }
        return cotacao;
    }
}
