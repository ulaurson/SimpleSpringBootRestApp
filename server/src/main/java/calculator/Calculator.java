package calculator;


public class Calculator {

    public Calculator() {};

    public double calculate(double num1, double num2, String op) {
        double result;
        switch(op) {
            case "sum": result = num1 + num2;
                break;
            case "sub": result = num1 - num2;
                break;
            case "prod": result = num1 * num2;
                break;
            case "div": result = num1 / num2;
                break;
            default: result = 0;
                break;
        }
        return result;
    }
}
