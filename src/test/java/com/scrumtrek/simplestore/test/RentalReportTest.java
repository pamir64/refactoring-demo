package com.scrumtrek.simplestore.test;

import com.scrumtrek.simplestore.Customer;
import com.scrumtrek.simplestore.DefaultRentalCalcStrategy;
import com.scrumtrek.simplestore.RentalReporter;
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
}
