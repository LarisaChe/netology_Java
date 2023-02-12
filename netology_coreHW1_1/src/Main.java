public class Main {

    public static void main(String[] args) {
        Calculator calc = Calculator.instance.get();

        int a = calc.plus.apply(1, 2);
        calc.println.accept(a);
        //int b = calc.minus.apply(1,1);
        //int c = calc.devide.apply(a, b);  - ����� ������, ��� ��� b = 0, � �� ���� ������ ������

        int b = calc.minus.apply(1,1);
        calc.println.accept(b);

        int d = calc.multiply.apply(1,1);
        calc.println.accept(d);

        int c = calc.devide.apply(a, d);
        calc.println.accept(c);

        int e = calc.pow.apply(3);
        calc.println.accept(e);

        int f = calc.abs.apply(-5);
        calc.println.accept(f);

        boolean g = calc.isPositive.test(3);
        System.out.println(g);
    }
}
