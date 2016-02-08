package com.scrumtrek.simplestore;

public abstract class RentalCalcStrategy{

    public abstract double calcRentalAmount(Customer customer);

    public int calcRentalsPoints(Customer customer) {
        int points = 0;
        for(Rental rental: customer.getRentals()) {
            points += 1 + rental.getMovie().getPriceCode().calcPoints(rental.getDaysRented());
        }
        return points;
    }

    public abstract int calcRentalPoints(Rental rental);
}
