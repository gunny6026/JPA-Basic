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
          Member sane = new Member();
          sane.setUsername("사네");

          Member kimihi = new Member();
          kimihi.setUsername("키미히");

          Team munhen = em.find(Team.class ,3L);
          sane.setTeam(munhen);
          kimihi.setTeam(munhen);


          em.persist(sane);
          em.persist(kimihi);

          em.persist(munhen);
          em.flush();
          em.clear();

         List<Member> member = munhen.getMembers();
         for (Member  m : member){
           System.out.println("m = "+m);
         }


          tx.commit();


        }catch (Exception e){
          tx.rollback();
        }finally {
          em.close();
        }
      emf.close();
    }
}
