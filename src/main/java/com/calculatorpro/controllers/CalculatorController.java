package com.calculatorpro.controllers;

import com.calculatorpro.expression.impl.ExpressionCalculator;
import com.calculatorpro.models.AdvancedCalculatorRequest;
import com.calculatorpro.models.CalculatorRequest;
import com.calculatorpro.services.AdvancedCalculator;
import com.calculatorpro.services.BasicCalculator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping("/calculate")
public class CalculatorController {

    @Autowired
    private BasicCalculator basicCalculator;

    @Autowired
    private AdvancedCalculator advancedCalculator;

    @Autowired
    private ExpressionCalculator expressionCalculator;



    @PostMapping("/basic") // GET http://localhost:1383/calculate/basic
    public ResponseEntity<?> calculateBasic(@RequestBody CalculatorRequest request) {
        try {
            double result = basicCalculator.compute(request.getExpression());
         //   Map<String, Double> response = new HashMap<>();
          //  response.put("result", result);
            return ResponseEntity.ok(result);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(createErrorResponse(e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(createErrorResponse("An unexpected error occurred: " + e.getMessage()));
        }
    }

    @PostMapping("/advanced") // GET http://localhost:1383/calculate/advanced
    public ResponseEntity<?> calculateAdvanced(@RequestBody AdvancedCalculatorRequest request) {
        try {
            double result = advancedCalculator.compute(request);
          //  Map<String, Double> response = new HashMap<>();
         //   response.put("result", result);
            return ResponseEntity.ok(result);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(createErrorResponse(e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(createErrorResponse("An unexpected error occurred: " + e.getMessage()));
        }
    }

    @PostMapping("/expression") // GET http://localhost:1383/calculate/expression
    public ResponseEntity<?> calculateExpression(@RequestBody CalculatorRequest request) {
        try {
            double result = expressionCalculator.compute(request.getExpression());
           Map<String, Double> response = new HashMap<>();
        response.put("result", result);
            return ResponseEntity.ok(response);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(createErrorResponse(e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(createErrorResponse("An unexpected error occurred: " + e.getMessage()));
        }
    }

    private Map<String, String> createErrorResponse(String errorMessage) {
        Map<String, String> errorResponse = new HashMap<>();
        errorResponse.put("error", errorMessage);
        return errorResponse;
    }




}
