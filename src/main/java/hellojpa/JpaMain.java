package hellojpa;


import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class JpaMain {
    public static void main(String[] args) {
      EntityManagerFactory emf =
              Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try{

          Team barsa = new Team();
          barsa.setName("바르셀로나");


          Member messi = new Member();
          messi.setUsername("메시");

          Member pique = new Member();
          pique.setUsername("피케");

          barsa.getMembers().add(messi);
          barsa.getMembers().add(pique);

          em.persist(barsa);
          em.persist(messi);
          em.persist(pique);
          tx.commit();


        }catch (Exception e){
          tx.rollback();
        }finally {
          em.close();
        }
      emf.close();
    }
}
