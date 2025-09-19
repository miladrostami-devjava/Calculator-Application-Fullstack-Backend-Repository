package com.calculatorpro.services;

import com.calculatorpro.expression.contractInterface.Calculator;
import org.springframework.stereotype.Component;

@Component
public class BasicCalculator implements Calculator {


    @Override
    public double compute(String expression) {

        try {
            String[] parts = expression.trim().split(" ");
          //  String[] parts = expression.trim().split("\\s*(\\+|\\-|\\*|/)\\s*");


            if (parts.length != 3) {
                throw new IllegalArgumentException("Invalid basic expression format. Example : 2 + 3");
            }

            double a = Double.parseDouble(parts[0]);
            String operator = parts[1];
            double b = Double.parseDouble(parts[2]);

            switch (operator) {
                case "+":
                    return add(a, b);
                case "-":
                    return subtract(a, b);
                case "*":
                    return multiply(a, b);
                case "/":
                    return divide(a, b);
                default:
                    throw new IllegalArgumentException("Invalid operator: " + operator);

            }
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Invalid number format: " + e.getMessage());
        } catch (ArithmeticException e) {
            throw new IllegalArgumentException(e.getMessage());
        }
    }


    private double add(double a, double b) {
        return a + b;
    }

    private double subtract(double a, double b) {
        return a - b;
    }

    private double multiply(double a, double b) {
        return a * b;
    }

    private double divide(double a, double b) {
        if (b == 0) {
            throw new ArithmeticException("Cannot divide by zero");
        }
        return a / b;
    }

}
