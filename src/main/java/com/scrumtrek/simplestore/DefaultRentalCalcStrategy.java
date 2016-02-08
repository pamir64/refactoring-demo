package com.scrumtrek.simplestore;

public class DefaultRentalCalcStrategy extends RentalCalcStrategy {

    @Override
    public double calcRentalAmount(Customer customer) {
        double amount = 0;
        for(Rental rental: customer.getRentals()) {
            amount += rental.getMovie().getPriceCode().calcAmount(rental.getDaysRented());
        }
        return amount;
    }

    @Override
    public int calcRentalPoints(Rental rental) {
        return rental.getMovie().getPriceCode().calcPoints(rental.getDaysRented());
    }
}
