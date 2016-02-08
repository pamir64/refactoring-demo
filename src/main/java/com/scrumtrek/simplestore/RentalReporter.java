package com.scrumtrek.simplestore;

/**
 * Statement reporter - builds a statement of customer all rentals
 */
public interface RentalReporter {

    /**
     * builds statement of customer all rentals
     * @param customer      customer with rentals
     * @param calcStrategy  strategy, that calculates rental amount/points
     */
    String buildStatement(Customer customer, RentalCalcStrategy calcStrategy);
}
