## JPQL 타입 표현

- 문자 : 'HELLO', 'She"s'
- 숫자 : 10L(Long) , 10D(Double), 10F(Float)
- Boolean : TRUE, FALSE
- ENUM : jpabook.MemberType.Admin(패키지명 포함)
- 엔티티 타입 : TYPE(m) = Member (상속 관계에서 사용)


`예시 `
```java
 Team team = new Team();
            team.setName("파리 생제르망");
            em.persist(team);


            Member member = new Member();
            member.setUsername("음바페" );
            member.setAge(23);
            member.setTeam(team);

            em.persist(member);


            em.flush();
            em.clear();

          String query = "select m.username , 'HELLO' , TRUE  from Member m ";
            List<Object[]> resultList
                    = em.createQuery(query).getResultList();

            for (Object[] objects : resultList){
                System.out.println("objects = " + objects[0]);
                System.out.println("objects = " + objects[1]);
                System.out.println("objects = " + objects[2]);
            }

            tx.commit();
```

결과 !!

```
objects = 음바페
objects = HELLO
objects = true
```
********

`ENUM 타입 예시`

```java
 Member member = new Member();
            member.setUsername("음바페" );
            member.setAge(23);
            member.setTeam(team);
            member.setType(MemberType.ADMIN);

            em.persist(member);


            em.flush();
            em.clear();

          String query = "select m.username , 'HELLO' , TRUE  from Member m " +
                  "where m.type = jpql.MemberType.ADMIN ";
            List<Object[]> resultList
                    = em.createQuery(query).getResultList();

            for (Object[] objects : resultList){
                System.out.println("objects = " + objects[0]);
                System.out.println("objects = " + objects[1]);
                System.out.println("objects = " + objects[2]);
            }

            tx.commit();

```

패키징명을 쓰는 게 귀찮으면

```java

          String query = "select m.username , 'HELLO' , TRUE  from Member m " +
                  "where m.type =:userType ";
            List<Object[]> resultList
                    = em.createQuery(query)
                    .setParameter("userType", MemberType.ADMIN)
                    .getResultList();

```

parameter 를 이용하면 된다.


`엔티티 타입 예시`

```java
em.createQuert("select i from Item i where type(i) = Book", Item.class )
.getResultList();
```

### JPQL 기타

- SQL과 문법이 같은 식
- EXISTS ,IN
- AND , OR , NOT
- = , > , >= , <, <= , <>
- BETWEEN , LIKE , IS NULL

```java

          String query = "select m.username , 'HELLO' , TRUE  from Member m " +
                  "where m.username is not null";
            List<Object[]> resultList
                    = em.createQuery(query)
                    .setParameter("userType", MemberType.ADMIN)
                    .getResultList();
```

```java

          String query = "select m.username , 'HELLO' , TRUE  from Member m " +
                  "where m.age between 0 and 10";
```

등등 