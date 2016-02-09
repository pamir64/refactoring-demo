package com.scrumtrek.simplestore.test;

import com.scrumtrek.simplestore.*;
import org.junit.Assert;
import org.junit.Test;

import java.util.Collection;

public class RentalReportTest {

    private SimpleRentalReporter reporter = new SimpleRentalReporter();

    @Test
    public void testCustomerNameInStatement() {
        Customer customer = new Customer("FooCustomer");
        String statement = reporter.buildStatement(customer, new DefaultRentalCalcStrategy());
        Assert.assertNotNull(statement);
        Assert.assertTrue(statement.contains("Rental record for " + customer.getName()));
    }

    @Test
    public void testStatementAmount() {
        final double testAmount = 123456789.876543210;
        final int testPoints = 1234567890;
        RentalCalcStrategy calcStrategy = new RentalCalcStrategy() {
            @Override
            public double calcTotalAmount(Collection<Rental> rentals) {
                return testAmount;
            }

            @Override
            public int calcTotalPoints(Collection<Rental> rentals) {
                return testPoints;
            }

            @Override
            public double calcAmount(Rental rental) {
                return 0;
            }

            @Override
            public int calcPoints(Rental rental) {
                return 0;
            }
        };
        String statement = reporter.buildStatement(new Customer("Foo"), calcStrategy);
        Assert.assertNotNull(statement);
        Assert.assertTrue(statement.contains(String.valueOf(testAmount)));
        Assert.assertTrue(statement.contains(String.valueOf(testPoints)));
    }
}
