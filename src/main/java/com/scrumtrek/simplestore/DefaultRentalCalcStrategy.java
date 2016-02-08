package com.scrumtrek.simplestore;

public class DefaultRentalCalcStrategy extends RentalCalcStrategy {

    @Override
    public double calcAmount(Rental rental) {
        return rental.getMovie().getPriceCode().calcAmount(rental.getDaysRented());
    }

    @Override
    public int calcPoints(Rental rental) {
        return 1 + rental.getMovie().getPriceCode().calcPoints(rental.getDaysRented());
    }
}
