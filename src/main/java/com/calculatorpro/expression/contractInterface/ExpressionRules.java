package com.calculatorpro.expression.contractInterface;

import org.springframework.stereotype.Component;
/**
 * <p>
 *    Checking the correctness of the input
 * </p>
 * */

public interface ExpressionRules {
    boolean isValidExpression(String expression);
    boolean isNumber(String token);
    boolean isOperator(String token);
}
