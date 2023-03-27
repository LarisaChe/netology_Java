import lache.Calculator;
import lache.Ints;
import lache.IntsCalculator;

public class Main {
    public static void main(String[] args) {

        /*Calculator calc = new Calculator();
        System.out.println(
                calc.newFormula()
                        .addOperand(5)
                        .addOperand(15)
                        .calculate(Calculator.Operation.MULT)
                        .result()
        );*/

        Ints intsCalc = new IntsCalculator();
        System.out.println("Сложение: ");
        System.out.println(intsCalc.sum(2, 2));
        System.out.println(intsCalc.sum(10, 22));
        System.out.println("Степень: ");
        System.out.println(intsCalc.pow(2, 10));

        System.out.println("Умножение: ");
        System.out.println(intsCalc.mult(6, 17));
        System.out.println("Вычитание: ");
        System.out.println(intsCalc.sub(123, 17));
        System.out.println("Деление: ");
        System.out.println(intsCalc.div(381, 3));

    }
}