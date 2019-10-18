package ng.dominic.parser.service;

import ng.dominic.parser.model.Record;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.runners.Parameterized.Parameter;
import static org.junit.runners.Parameterized.Parameters;

@RunWith(Parameterized.class)
@SpringBootTest
public class ReferenceValidationTest {

    private ValidationServiceImpl validationService;

    @Parameter(value = 0)
    public int expectedFailures;

    @Parameter(value = 1)
    public int reference1;

    @Parameter(value = 2)
    public int reference2;

    @Parameter(value = 3)
    public int reference3;

    @Before
    public void setup() {
        validationService = new ValidationServiceImpl();
    }

    public ReferenceValidationTest() {
    }

    private Record testReferenceRecord(int reference) {
        return new Record(reference,
                "NL12RABO34567890",
                "Description",
                new BigDecimal("10.00"),
                new BigDecimal("20.00"),
                new BigDecimal("30.00"));
    }

    @Parameters(name = "testing: {index} : Reference {1} and Reference {2} should not be the same")
    public static Collection<Object[]> getTestData() {
        return Arrays.asList(new Object[][]{
                {0, 123456, 234567, 345678}, // 0 failures expected because references are not the same
                {1, 123456, 123456, 234567}, // 1 failure expected because there is one duplicate
                {2, 123456, 123456, 123456} // 2 failures expected because there are two duplicates
        });
    }

    @Test
    public void validateReferenceCodeTest() {
        List<Record> testRecords = Arrays.asList(
                testReferenceRecord(reference1),
                testReferenceRecord(reference2),
                testReferenceRecord(reference3));
        assertEquals(expectedFailures, validationService.validateReferenceCode(testRecords).size());
    }
}