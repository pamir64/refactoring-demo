package com.scrumtrek.simplestore;

public class DefaultRentalCalcStrategy implements RentalCalcStrategy {

    @Override
    public double calcRentalAmount(Customer customer) {
        double amount = 0;
        for(Rental rental: customer.getRentals()) {
            amount += rental.getMovie().getPriceCode().calcAmount(rental.getDaysRented());
        }
        return amount;
    }

    @Override
    public int calcRentalPoints(Customer customer) {
        int points = 0;
        for(Rental rental: customer.getRentals()) {
            points += 1 + rental.getMovie().getPriceCode().calcPoints(rental.getDaysRented());
        }
        return points;
    }
}
