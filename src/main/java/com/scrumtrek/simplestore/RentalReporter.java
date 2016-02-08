package com.scrumtrek.simplestore;

public interface RentalReporter {
    String buildStatement(Customer customer, RentalCalcStrategy calcStrategy);
}
