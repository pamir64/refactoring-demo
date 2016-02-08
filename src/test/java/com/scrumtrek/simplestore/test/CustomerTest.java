package com.scrumtrek.simplestore.test;

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
    private Customer customer;

    @Before
    public void prepareTestData() {
        movie1 = new Movie("Movie1", PriceCodes.Regular);
        movie2 = new Movie("Movie2", PriceCodes.NewRelease);

        customer = new Customer("Customer1");
    }

    @Test
    public void statementZeroTest() {
        String statement = customer.Statement();
        System.out.println(statement);
        Assert.assertNotNull(statement);
        Assert.assertTrue(statement.contains("Amount owed is 0.0"));
    }

    @Test
    public void statementTest() {
        Rental rental1 = new Rental(movie1, 3);
        Rental rental2 = new Rental(movie2, 2);

        customer.addRental(rental1);
        customer.addRental(rental2);
        String statement = customer.Statement();
        System.out.println(statement);
        Assert.assertNotNull(statement);
        Assert.assertTrue(statement.contains("Amount owed is 9.5"));
    }
}
