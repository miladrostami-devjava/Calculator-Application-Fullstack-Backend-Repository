package com.calculatorpro.expression.impl;

import com.calculatorpro.expression.contractInterface.Calculator;
import com.calculatorpro.expression.contractInterface.ExpressionRules;
import com.calculatorpro.expression.contractInterface.OperatorPrecedence;
import com.calculatorpro.expression.tokenizer.Tokenizer;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

@Component
public class ExpressionCalculator implements Calculator {


    private final Tokenizer tokenizer;
    private final ExpressionRules expressionRules;
    private final OperatorPrecedence operatorPrecedence;

    public ExpressionCalculator(Tokenizer tokenizer, ExpressionRules expressionRules, OperatorPrecedence operatorPrecedence) {
        this.tokenizer = tokenizer;
        this.expressionRules = expressionRules;
        this.operatorPrecedence = operatorPrecedence;
    }

    private double performOperation(double operand1, double operand2, String operator) {
        switch (operator) {
            case "+":
                return operand1 + operand2;
            case "-":
                return operand1 - operand2;
            case "*":
                return operand1 * operand2;
            case "/":
                if (operand2 == 0) {
                    throw new ArithmeticException("Division by zero");
                }
                return operand1 / operand2;
            default:
                throw new IllegalArgumentException("Invalid operator: " + operator);
        }
    }


    @Override
    public double compute(String expression) {
        //step1::validation expression
        System.out.println("Input Expression: " + expression);

        if (!expressionRules.isValidExpression(expression)) {
            throw new IllegalArgumentException(" Invalid Expression :" + expression);
        }

        //step2::create list for tokens and apply tokenize
        List<String> tokens = tokenizer.tokenize(expression);

        System.out.println("Tokens :" + tokens);

        //To store tokens in Postfix (Reverse Polish Notation) mode
        List<String> outputQueue = new ArrayList<>();
        //To hold operators and manage priorities
        Stack<String> operatorStack = new Stack<>();

        //step3::Processing each token
        for (String token : tokens) {
            System.out.println("Processing Token:" + token);
            if (expressionRules.isNumber(token)) {
                outputQueue.add(token);
                System.out.println("Added to output queue :" + token);
            } else if (expressionRules.isOperator(token)) {

                while (!operatorStack.isEmpty() && operatorPrecedence.hasHigherPrecedence(operatorStack.peek(), token) && expressionRules.isOperator(operatorStack.peek())) {
                    outputQueue.add(operatorStack.pop());
                    System.out.println("Moved operator from stack to outputQueue.");
                }
                operatorStack.push(token);
                System.out.println("Pushed operator to stack :" + token);
            } else if (token.equals("(")) {
                operatorStack.push(token);
                System.out.println("Pushed ( to stack");
            } else if (token.equals(")")) {
                while (!operatorStack.isEmpty() && !operatorStack.peek().equals("(")) {
                    outputQueue.add(operatorStack.pop());
                    System.out.println("Moved Operator from stack to outputQueue (inside) processing)");
                }
                if (!operatorStack.isEmpty() && operatorStack.peek().equals("(")) {
                    operatorStack.pop();
                    System.out.println("Popped ( from stack");
                } else {
                    throw new IllegalArgumentException("Mismatched parentheses");
                }

            } else {
                System.out.println("Output Queue: " + " " + outputQueue);
                System.out.println("Operator Stack: " + " " + operatorStack);
            }


        }

        //step4:: Move the rest of the stack to the output queue.
        while (!operatorStack.isEmpty()) {
            if (operatorStack.peek().equals("(")) {
                throw new IllegalArgumentException("Mismatch parentheses");
            }
            outputQueue.add(operatorStack.pop());
            System.out.println("Moved remaining operator from stack to outputQueue");
        }
        System.out.println("final output Queue: " + outputQueue);


//step5:: Postfix output calculation
        Stack<Double> evaluationStack = new Stack<>();
        for (String token : outputQueue){

            if (expressionRules.isNumber(token)){
                evaluationStack.push(Double.parseDouble(token));
                System.out.println("Pushed value to evaluation stack :" + token);
            } else if (expressionRules.isOperator(token)) {

/*
  if (evaluationStack.size() < 2){ :

* In Postfix (or RPN) evaluation, when we encounter a binary operator like +, -, *, /, we need to pop two operands from the stack to execute the operator:

double operand2 = evaluationStack.pop();

double operand1 = evaluationStack.pop();

result = operand1 <op> operand2;

evaluationStack.push(result);

If the number of elements in the stack is less than 2, that is, there are not enough operands to execute the binary operator → the expression is invalid.

So the condition evaluationStack.size() < 2 is to prevent "stack underflow" and to detect a syntax error.
*
* */

                if (evaluationStack.size() < 2){
                    throw new IllegalArgumentException("Invalid expression: Not enough operands for operator " + token);
                }else {
                    double operand2 = evaluationStack.pop();
                    double operand1 = evaluationStack.pop();
                    double result = performOperation(operand1,operand2,token);
                    evaluationStack.push(result);
                    System.out.println("Performed operation " +  " " + token  + " " + "with" + " " + operand1 + " " + "and" + " " + operand2 + " " + "and pushed result" +  " " + result );

                }
                System.out.println("Evaluation stack :" + " " + evaluationStack);
            }


        }

            //step6::After calculation, only one number should remain on the stack → final result.
            if (evaluationStack.size() != 1){
                throw new IllegalArgumentException("Invalid expression");
            }

        return evaluationStack.pop();
    }


}
