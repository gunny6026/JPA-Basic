package hellojpa;

public class ValueMain {

    public static void main(String[] args) {

        Address a = new Address("서울");
        Address b = new Address("서울");

        System.out.println("a == b : "+(a==b));
        System.out.println("a.equals(b) : " +(a.equals(b)));



    }
}
