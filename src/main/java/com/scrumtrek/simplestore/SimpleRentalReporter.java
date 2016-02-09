package com.scrumtrek.simplestore;

/**
 * Builds a plain text statement of customer all rentals
 * @see com.scrumtrek.simplestore.RentalReporter
 */
public class SimpleRentalReporter implements RentalReporter {

    @Override
    public String buildStatement(Customer customer, RentalCalcStrategy calcStrategy) {
        double amount = calcStrategy.calcTotalAmount(customer.getRentals());
        int points = calcStrategy.calcTotalPoints(customer.getRentals());
        StringBuilder stmt = new StringBuilder()
                .append("Rental record for ").append(customer.getName()).append("\n");

        for(Rental rental: customer.getRentals()) {
            stmt.append("\t").append(rental.getMovie().getTitle())
                    .append("\t").append(rental.getMovie().getPriceCode().calcAmount(rental.getDaysRented())).append("\n");
        }

        stmt = stmt.append("Amount owed is ").append(amount).append("\n");
        stmt = stmt.append("You earned ").append(points).append(" frequent renter points.");
        return stmt.toString();
    }
}
