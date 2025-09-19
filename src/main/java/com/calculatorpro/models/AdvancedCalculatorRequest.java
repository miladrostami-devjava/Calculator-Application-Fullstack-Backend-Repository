package com.calculatorpro.models;

public class AdvancedCalculatorRequest {

    private Double a;
    private Double b; // فقط برای power
    private String operation;

    public Double getA() {
        return a;
    }

    public void setA(Double a) {
        this.a = a;
    }

    public Double getB() {
        return b;
    }

    public void setB(Double b) {
        this.b = b;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }
}
