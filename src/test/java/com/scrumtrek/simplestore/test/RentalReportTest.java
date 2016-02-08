package com.scrumtrek.simplestore.test;

import com.scrumtrek.simplestore.*;
import org.junit.Assert;
import org.junit.Test;

public class RentalReportTest {

    private RentalReporter reporter = new RentalReporter();

    @Test
    public void testCustomerNameInStatement() {
        Customer customer = new Customer("FooCustomer");
        String statement = reporter.buildRentalsStatement(customer, new DefaultRentalCalcStrategy());
        Assert.assertNotNull(statement);
        Assert.assertTrue(statement.contains("Rental record for " + customer.getName()));
    }

    @Test
    public void testStatementAmount() {
        final double testAmount = Math.random() * Double.MAX_VALUE;
        final int testPoints = (int) (Math.random() * Integer.MAX_VALUE);
        RentalCalcStrategy calcStrategy = new RentalCalcStrategy() {
            @Override
            public double calcRentalAmount(Customer customer) {
                return testAmount;
            }

            @Override
            public int calcRentalsPoints(Customer customer) {
                return testPoints;
            }

            @Override
            public int calcRentalPoints(Rental rental) {
                return 0;
            }
        };
        String statement = reporter.buildRentalsStatement(new Customer("Foo"), calcStrategy);
        Assert.assertNotNull(statement);
        Assert.assertTrue(statement.contains(String.valueOf(testAmount)));
        Assert.assertTrue(statement.contains(String.valueOf(testPoints)));
    }
}
