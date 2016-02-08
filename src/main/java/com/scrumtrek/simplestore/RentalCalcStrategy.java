package com.scrumtrek.simplestore;

public abstract class RentalCalcStrategy {

    /**
     * calculates total amount for customer all rentals
     */
    public double calcTotalAmount(Customer customer) {
        double amount = 0;
        for(Rental rental: customer.getRentals()) {
            amount += calcAmount(rental);
        }
        return amount;
    }

    /**
     * calculates total bonus points for customer all rentals
     */
    public int calcTotalPoints(Customer customer) {
        int points = 0;
        for(Rental rental: customer.getRentals()) {
            points += calcPoints(rental);
        }
        return points;
    }

    /**
     * calculates amount of a rental
     */
    public abstract double calcAmount(Rental rental);

    /**
     * calculates bonus points for a rental
     */
    public abstract int calcPoints(Rental rental);
}
