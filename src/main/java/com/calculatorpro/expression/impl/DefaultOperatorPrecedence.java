package com.calculatorpro.expression.impl;

import com.calculatorpro.expression.contractInterface.OperatorPrecedence;
import org.springframework.stereotype.Component;

@Component
public class DefaultOperatorPrecedence implements OperatorPrecedence {
    @Override
    public boolean hasHigherPrecedence(String op1, String op2) {

        // BODMAS/PEMDAS precedence:
        // 1. Parentheses (handled in the main algorithm)
        // 2. Exponents (not implemented in this example)
        // 3. Multiplication and Division (same precedence)
        // 4. Addition and Subtraction (same precedence)
        if (isHighPrecedence(op1) && isLowPrecedence(op2)){
            return true;
        }else {
            return false;
        }

    }

    private boolean isLowPrecedence(String op) {
        if (op.equals("*") || op.equals("/")){
            return true;
        }else {
            return false;
        }
    }

    private boolean isHighPrecedence(String op) {
        if (op.equals("+") || op.equals("-")){
            return true;
        }else {
        return false;
        }
    }
}
