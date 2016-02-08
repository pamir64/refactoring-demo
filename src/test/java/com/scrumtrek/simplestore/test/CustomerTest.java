package com.scrumtrek.simplestore.test;

import com.google.common.base.Preconditions;
import com.scrumtrek.simplestore.Customer;
import com.scrumtrek.simplestore.Movie;
import com.scrumtrek.simplestore.PriceCodes;
import com.scrumtrek.simplestore.Rental;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class CustomerTest {

    private Movie movie1;
    private Movie movie2;
    private Movie movie3;
    private Customer customer;
    private Rental rental1;
    private Rental rental2;
    private Rental rental3;

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
        customer = new Customer("Customer1");
    }

    private double getStatementTotalAmount(String statement) {
        Preconditions.checkNotNull(statement);
        String amountStr = "Amount owed is ";
        int i = statement.indexOf(amountStr);
        int ni = statement.indexOf("\n", i);

        return Double.parseDouble(statement.substring(i + amountStr.length(), ni));
    }

    @Test
    public void statementZeroTest() {
        cleanRentals();
        String statement = customer.Statement();
        System.out.println(statement);
        Assert.assertNotNull(statement);
        Assert.assertTrue(statement.contains("Amount owed is 0.0"));
    }

    @Test
    public void statementTest() {
        String statement = customer.Statement();
        System.out.println(statement);
        Assert.assertNotNull(statement);
        Assert.assertTrue(statement.contains("Amount owed is 11.0"));
    }

    @Test
    public void priceCodeChangeTest() {
        String statement = customer.Statement();
        final double amount1 = getStatementTotalAmount(statement);
        movie1.setPriceCode(PriceCodes.Childrens);
        statement = customer.Statement();
        final double amount2 = getStatementTotalAmount(statement);
        Assert.assertNotEquals(amount1, amount2);
    }

    private void rentalDaysTest(PriceCodes priceCode, int days, double expectedAmount) {
        String statement = customer.Statement();
        final double amount1 = getStatementTotalAmount(statement);
        Movie movie = new Movie("extra Movie", priceCode);
        Rental extraRental = new Rental(movie, days);
        customer.addRental(extraRental);
        statement = customer.Statement();
        final double amount2 = getStatementTotalAmount(statement);
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
}
