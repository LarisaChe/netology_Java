import lache.BinOps;

public class Main {
    public static void main(String[] args) {
        BinOps bins = new BinOps();
        //демонстрация
        System.out.println("Сложение: ");
        System.out.println(bins.sum("101","100"));
        System.out.println(bins.sum("111","111"));

        System.out.println("Умножение: ");
        System.out.println(bins.mult("110","100"));
        System.out.println(bins.mult("1000","10000"));
    }
}