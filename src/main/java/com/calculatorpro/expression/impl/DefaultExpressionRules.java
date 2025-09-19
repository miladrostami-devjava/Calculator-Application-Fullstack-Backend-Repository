package com.calculatorpro.expression.impl;

import com.calculatorpro.expression.contractInterface.ExpressionRules;
import org.springframework.stereotype.Component;

@Component
public class DefaultExpressionRules implements ExpressionRules {
    @Override
    public boolean isValidExpression(String expression) {
        if (expression == null || expression.trim().isEmpty()){
        return false;
        }
        String trimmedExpression = expression.trim();
        // More robust validation needed (parentheses, operator placement, etc.)
        return trimmedExpression.matches("[0-9+\\-*/(). ]+");
    }

    @Override
    public boolean isNumber(String token) {
        try {
            Double.parseDouble(token);
            return true;
        }catch (NumberFormatException e){
        return false;
        }
    }

    @Override
    public boolean isOperator(String token) {
        if (token.equals("+") || token.equals("-") || token.equals("*") || token.equals("/")){
        return true;
        }else {
            return false;
        }
    }
}
