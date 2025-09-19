package com.calculatorpro.services;

import com.calculatorpro.models.AdvancedCalculatorRequest;
import org.springframework.stereotype.Component;

@Component
public class AdvancedCalculator {

    /**
     * Compute advanced operations based on request object
     */
    public double compute(AdvancedCalculatorRequest request) {
        String operation = request.getOperation();

        if (operation == null || operation.isEmpty()) {
            throw new IllegalArgumentException("Operation cannot be null or empty");
        }

        switch (operation.toLowerCase()) {
            case "sqrt":
                return sqrt(request.getA());
            case "sin":
                return sin(request.getA());
            case "log":
                return log(request.getA());
            case "power":
                if (request.getA() == null || request.getB() == null) {
                    throw new IllegalArgumentException("Power operation requires both 'a' and 'b'");
                }
                return power(request.getA(), request.getB());
            default:
                throw new IllegalArgumentException("Invalid advanced operation: " + operation);
        }
    }

    // --- Helper methods ---

    private double sqrt(Double a) {
        if (a == null) throw new IllegalArgumentException("Input for sqrt cannot be null");
        return Math.sqrt(a);
    }

    private double sin(Double a) {
        if (a == null) throw new IllegalArgumentException("Input for sin cannot be null");
        return Math.sin(a);
    }

    private double log(Double a) {
        if (a == null) throw new IllegalArgumentException("Input for log cannot be null");
        return Math.log(a);
    }

    private double power(Double base, Double exponent) {
        return Math.pow(base, exponent);
    }
}
