import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Main {
    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory =  Persistence.createEntityManagerFactory("inheritance");
        EntityManager em = entityManagerFactory.createEntityManager();

        em.getTransaction().begin();
        em.getTransaction().commit();

        em.close();
    }
}
