package com.scrumtrek.simplestore;

public class RentalReporter {

    private static final String amountPlaceholder = "{AMOUNT}";
    private static final String pointPlaceholder = "{pointPlaceholder}";
    private static final String statementTemplate = "Amount owed is " + amountPlaceholder +
            "\nYou earned " + pointPlaceholder + " frequent renter points.";

    public String buildRentalsStatement(Customer customer, RentalCalcStrategy calcStrategy) {
        double amount = calcStrategy.calcRentalAmount(customer);
        int points = calcStrategy.calcRentalPoints(customer);
        String statement = statementTemplate;
        statement.replaceAll(amountPlaceholder, String.valueOf(amount));
        statement.replaceAll(pointPlaceholder, String.valueOf(points));
        return statement;
    }
}
