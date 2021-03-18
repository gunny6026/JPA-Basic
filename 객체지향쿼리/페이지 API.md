# 페이징 API

- JPA는 페이징을 다음 두 API로 추상화
- setFirstResult(int startPosition) : 조회 시작 위치(0부터 시작)
- setMaxResults(int maxResult) : 조회할 데이터 수

```java
     for (int i = 1; i <100; i ++){

            Member member = new Member();
            member.setUsername("member"  +i);
            member.setAge(i);
            em.persist(member);
            }

            em.flush();
            em.clear();

            List<Member> result = em.createQuery("select m from Member m order by m.age desc", Member.class)
                    .setFirstResult(5)
                    .setMaxResults(10)
                    .getResultList();

            System.out.println("result.size = " + result.size());
            for (Member m : result){
                System.out.println("m = " + m);
            }

            tx.commit();
```