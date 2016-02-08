package com.scrumtrek.simplestore;

public abstract class RentalCalcStrategy {

    public double calcTotalAmount(Customer customer) {
        double amount = 0;
        for(Rental rental: customer.getRentals()) {
            amount += calcAmount(rental);
        }
        return amount;
    }

    public int calcTotalPoints(Customer customer) {
        int points = 0;
        for(Rental rental: customer.getRentals()) {
            points += calcPoints(rental);
        }
        return points;
    }

    public abstract double calcAmount(Rental rental);
    public abstract int calcPoints(Rental rental);
}
