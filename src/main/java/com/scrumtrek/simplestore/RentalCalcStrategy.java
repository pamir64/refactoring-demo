package com.scrumtrek.simplestore;

import java.util.Collection;

/**
 * Strategy use to calculate rental amount and points
 */
public abstract class RentalCalcStrategy {

    /**
     * calculates total amount for customer all rentals
     */
    public double calcTotalAmount(Collection<Rental> rentals) {
        double amount = 0;
        for(Rental rental: rentals) {
            amount += calcAmount(rental);
        }
        return amount;
    }

    /**
     * calculates total bonus points for customer all rentals
     */
    public int calcTotalPoints(Collection<Rental> rentals) {
        int points = 0;
        for(Rental rental: rentals) {
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
