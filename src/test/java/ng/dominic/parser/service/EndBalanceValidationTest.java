package ng.dominic.parser.service;

import ng.dominic.parser.model.Record;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
@SpringBootTest
public class EndBalanceValidationTest {

    private ValidationServiceImpl validationService;

    // true when the List of failures is empty
    @Parameter(value = 0)
    public boolean expectValidationSuccess;

    @Parameter(value = 1)
    public BigDecimal startBalance;

    @Parameter(value = 2)
    public BigDecimal mutation;

    @Parameter(value = 3)
    public BigDecimal endBalance;

    @Before
    public void setup() {
        validationService = new ValidationServiceImpl();
    }

    public EndBalanceValidationTest() {
    }

    private Record testEndBalanceRecord(BigDecimal start, BigDecimal mutation, BigDecimal end) {
        return new Record(123456,
                "NL12RABO34567890",
                "Description",
                start,
                mutation,
                end);
    }

    @Parameters(name = "testing: {index} : Start balance {1} + Mutation {2} == End balance {3} ?")
    public static Collection<Object[]> getTestData() {
        return Arrays.asList(new Object[][]{
                // happy flow
                {true, new BigDecimal("10.00"), new BigDecimal("20.00"), new BigDecimal("30.00")},

                // testing negative value
                {true, new BigDecimal("10.00"), new BigDecimal("-5.00"), new BigDecimal("5.00")},

                // edge case
                {false, new BigDecimal("10.00"), new BigDecimal("20.00"), new BigDecimal("29.99")},

                // two negatives
                {false, new BigDecimal("-10.00"), new BigDecimal("-10.00"), new BigDecimal("0.00")},

                // ref 187997, Clothes for Rik King -> should succeed
                {true, new BigDecimal("57.6"), new BigDecimal("-32.98"), new BigDecimal("24.62")},

                // ref 154270, Candy for Peter de Vries -> should fail
                {false, new BigDecimal("5429"), new BigDecimal("-939"), new BigDecimal("6368")}
        });
    }

    @Test
    public void validateEndBalanceTest() {
        List<Record> testRecords = Collections.singletonList(
                testEndBalanceRecord(startBalance, mutation, endBalance)
        );
        assertEquals(expectValidationSuccess, validationService.validateEndBalance(testRecords).isEmpty());
    }
}
