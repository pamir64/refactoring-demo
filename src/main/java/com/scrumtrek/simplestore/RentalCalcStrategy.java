package com.scrumtrek.simplestore;

public interface RentalCalcStrategy{

    double calcRentalAmount(Customer customer);
    int calcRentalPoints(Customer customer);
}
