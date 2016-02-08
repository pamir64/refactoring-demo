package com.scrumtrek.simplestore;

/**
 * Builds a plain text statement of customer all rentals
 * @see com.scrumtrek.simplestore.RentalReporter
 */
public class SimpleRentalReporter implements RentalReporter {

    private static final String namePlaceholder = "\\{NAME\\}";
    private static final String amountPlaceholder = "\\{AMOUNT\\}";
    private static final String pointPlaceholder = "\\{POINTS\\}";
    private static final String statementTemplate = "Rental record for " + namePlaceholder + "\n" +
            "Amount owed is " + amountPlaceholder +
            "\nYou earned " + pointPlaceholder + " frequent renter points.";

    @Override
    public String buildStatement(Customer customer, RentalCalcStrategy calcStrategy) {
        double amount = calcStrategy.calcTotalAmount(customer.getRentals());
        int points = calcStrategy.calcTotalPoints(customer.getRentals());
        String statement = statementTemplate;
        statement = statement.replace(namePlaceholder, customer.getName());
        statement = statement.replace(amountPlaceholder, String.valueOf(amount));
        statement = statement.replace(pointPlaceholder, String.valueOf(points));
        return statement;
    }
}
