package com.calculatorpro.expression.contractInterface;

import org.springframework.stereotype.Component;


public interface OperatorPrecedence {
    /**
     * <p>
     *     Determine the order of execution of operators.
     * </p>
     * */
    boolean hasHigherPrecedence(String op1,String op2);
}
