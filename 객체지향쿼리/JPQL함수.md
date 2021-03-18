## JPQL 기본 함수

- CONCAT
- SUBSTRING
- TRIM
- LOWER, UPPER
- LENGTH
- LOCATE
- ABS, SQRT, MOD
- SIZE , INDEX(JPA 용도)

### 사용자 정의 함수 호출
- 하이버네이트는 사용전 방언에 추가해야 한다.
    - 사용하는 DB 방언을 상속받고, 사용자 정의 함수를 등록한다.
    
`select function('group_concat', i.name) from Item i`



```java
    String query = "select concat('a', 'b') from Member b";

            List<String> resultList = em.createQuery(query, String.class).getResultList();
```

```java
   String query = "select substring(m.username, 2, 3) from Member b";

```

```java
 String query = "select locate('de','abcdefg') from Member b";

            List<Integer> resultList = em.createQuery(query, Integer.class).getResultList();

            for (Integer s : resultList){
                System.out.println("s = "+s);
            }
```