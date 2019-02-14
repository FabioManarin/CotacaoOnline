package bean;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class ResourceBean {

    private static EntityManagerFactory entityManagerFactory;

    public  static EntityManagerFactory getEntityManagerFactory() { 
        if (entityManagerFactory == null) {
            entityManagerFactory = Persistence.createEntityManagerFactory("CotacaoOnline");
        }
        return entityManagerFactory;
    }
}
