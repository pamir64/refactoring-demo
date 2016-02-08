package com.scrumtrek.simplestore.test;

import com.scrumtrek.simplestore.*;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class RentalCalcStrategyTest {

    private static final String testCustomerName = "Customer1";

    private Movie movie1;
    private Movie movie2;
    private Movie movie3;
    private Customer customer;
    private Rental rental1;
    private Rental rental2;
    private Rental rental3;

    private RentalCalcStrategy calcStrategy = new DefaultRentalCalcStrategy();

    @Before
    public void prepareTestData() {
        customer = new Customer("Customer1");

        movie1 = new Movie("Movie1", PriceCodes.Regular);
        movie2 = new Movie("Movie2", PriceCodes.NewRelease);
        movie3 = new Movie("Movie3", PriceCodes.Childrens);

        rental1 = new Rental(movie1, 3);
        rental2 = new Rental(movie2, 2);
        rental3 = new Rental(movie3, 3);

        customer.addRental(rental1);
        customer.addRental(rental2);
        customer.addRental(rental3);
    }

    private void cleanRentals() {
        customer = new Customer(testCustomerName);
    }

    @Test
    public void zeroAmountTest() {
        cleanRentals();
        double amount = calcStrategy.calcRentalAmount(customer);
        Assert.assertEquals(0, amount, 0.0001);
    }

    @Test
    public void statementTest() {
        double amount = calcStrategy.calcRentalAmount(customer);
        Assert.assertEquals(11, amount, 0.0001);
    }

    @Test
    public void priceCodeChangeTest() {
        final double amount1 = calcStrategy.calcRentalAmount(customer);
        movie1.setPriceCode(PriceCodes.Childrens);
        final double amount2 = calcStrategy.calcRentalAmount(customer);
        Assert.assertNotEquals(amount1, amount2);
    }

    private void rentalDaysTest(PriceCodes priceCode, int days, double expectedAmount) {
        final double amount1 = calcStrategy.calcRentalAmount(customer);
        Movie movie = new Movie("extra Movie", priceCode);
        Rental extraRental = new Rental(movie, days);
        customer.addRental(extraRental);
        final double amount2 = calcStrategy.calcRentalAmount(customer);
        Assert.assertEquals(expectedAmount, amount2 - amount1, 0.0001);
    }

    @Test
    public void regularDaysTest() {
        rentalDaysTest(PriceCodes.Regular, 1, 2);
        rentalDaysTest(PriceCodes.Regular, 2, 2);
        rentalDaysTest(PriceCodes.Regular, 3, 3.5);
        rentalDaysTest(PriceCodes.NewRelease, 2, 6);
        rentalDaysTest(PriceCodes.NewRelease, 3, 9);
        rentalDaysTest(PriceCodes.Childrens, 1, 1.5);
        rentalDaysTest(PriceCodes.Childrens, 3, 1.5);
        rentalDaysTest(PriceCodes.Childrens, 4, 1.5);
        rentalDaysTest(PriceCodes.Childrens, 5, 3);
    }

    @Test
    public void regularPointsTest() {
        Rental rental = new Rental(movie1, 1);
        int points = calcStrategy.calcRentalPoints(rental);
        Assert.assertEquals(0, points);

        rental = new Rental(movie1, 3);
        points = calcStrategy.calcRentalPoints(rental);
        Assert.assertEquals(0, points);
    }

    @Test
    public void newReleasePointsTest() {
        Rental rental = new Rental(movie2, 1);
        int points = calcStrategy.calcRentalPoints(rental);
        Assert.assertEquals(0, points);

        rental = new Rental(movie2, 3);
        points = calcStrategy.calcRentalPoints(rental);
        Assert.assertEquals(1, points);
    }

    @Test
    public void childrenPointsTest() {
        Rental rental = new Rental(movie3, 1);
        int points = calcStrategy.calcRentalPoints(rental);
        Assert.assertEquals(0, points);

        rental = new Rental(movie3, 3);
        points = calcStrategy.calcRentalPoints(rental);
        Assert.assertEquals(0, points);
    }
}
