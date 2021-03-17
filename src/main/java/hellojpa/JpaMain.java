package hellojpa;


import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.time.LocalDateTime;
import java.util.List;

public class JpaMain {
    public static void main(String[] args) {
        EntityManagerFactory emf =
                Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {

            Member mm = new Member();
            mm.setUsername("거니");
            mm.setHomeAddress(new Address("부산","1111","대성래미안"));
            mm.getFavoriteFoods().add("치킨");
            mm.getFavoriteFoods().add("삼겹살");
            mm.getFavoriteFoods().add("피자");
            mm.getAddressHistory().add(new AddressEntity("서울","1111","청담동"));
            mm.getAddressHistory().add(new AddressEntity("부산 신평","1111","한신 아파트"));

            em.persist(mm);

            em.flush();
            em.clear();

            System.out.println("================= START ============");
            Member findMember = em.find(Member.class, mm.getId());

            // 부산 -> 서울
//            findMember.getHomeAddress().setCity("서울");

            Address a = findMember.getHomeAddress();
            findMember.setHomeAddress(new Address("서울",a.getZipcode(),a.getStreet()));

            //치킨 -> 한식
            findMember.getFavoriteFoods().remove("치킨");
            findMember.getFavoriteFoods().add("한식");

//            findMember.getAddressHistory().remove(new Address("부산 신평","1111","한신 아파트"));
//            findMember.getAddressHistory().add(new Address("신평","1111","한신 아파트"));


            tx.commit();


        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }
        emf.close();
    }

//    private static void printMember(Member member) {
//        System.out.println("member = " +member.getUsername());
//    }
//
//    private static void printMemberAndTeam(Member member) {
//        String username = member.getUsername();
//        System.out.println("username = " + username);
//
//        Team team = member.getTeam();
//        System.out.println("team = " + team.getName());
//    }
}
