package lache;

public class IntsCalculator implements Ints {
    protected final Calculator target;

    public IntsCalculator() {
        this.target = new Calculator();
    }

    @Override
    public int sum(int arg0, int arg1) {
        //считаем через target
       Calculator.Formula calc = target.newFormula();
       calc.addOperand(arg0);
       calc.addOperand(arg1);
       calc.calculate(Calculator.Operation.SUM);
       return (int) calc.result();
    }

    @Override
    public int mult(int arg0, int arg1) {
        //считаем через target
        Calculator.Formula calc = target.newFormula();
        calc.addOperand(arg0);
        calc.addOperand(arg1);
        calc.calculate(Calculator.Operation.MULT);
        return (int) calc.result();
    }

    @Override
    public int pow(int a, int b) {
        //считаем через target
        Calculator.Formula calc = target.newFormula();
        calc.addOperand(a);
        calc.addOperand(b);
        calc.calculate(Calculator.Operation.POW);
        return (int) calc.result();
    }

    @Override
    public int sub(int arg0, int arg1) {
        Calculator.Formula calc = target.newFormula();
        calc.addOperand(arg0);
        calc.addOperand(arg1);
        calc.calculate(Calculator.Operation.SUB);
        return (int) calc.result();
    }

    @Override
    public int div(int arg0, int arg1) {
        Calculator.Formula calc = target.newFormula();
        calc.addOperand(arg0);
        calc.addOperand(arg1);
        calc.calculate(Calculator.Operation.DIV);
        return (int) calc.result();
    }
}
