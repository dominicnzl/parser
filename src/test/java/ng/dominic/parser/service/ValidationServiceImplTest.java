package ng.dominic.parser.service;

import ng.dominic.parser.model.FailureReason;
import ng.dominic.parser.model.Record;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.*;

public class ValidationServiceImplTest {

    private List<Pair<Record, FailureReason>> testFailedRecords = new ArrayList<>();

    private ValidationServiceImpl validationService;

    @Before
    public void setup() {
        validationService = new ValidationServiceImpl();
        Record record = new Record(
                123456,
                "NL12RABO34567890",
                "Test Description",
                new BigDecimal("10.00"),
                new BigDecimal("5.00"),
                new BigDecimal("15.00"));
        testFailedRecords.add(new ImmutablePair<>(record, FailureReason.DUPLICATE_REFERENCE_CODE));
    }

    @Test
    public void reportValidationFailures() {
        String failureMessage = validationService.reportValidationFailures(testFailedRecords);
        String EXPECTED_FAILURE_MESSAGE = "Record failed with reason: \"Reference code already exists\"" +
                "\n\t with reference: 123456 and description: Test Description";
        Assert.assertThat(failureMessage, is(EXPECTED_FAILURE_MESSAGE));
    }

    @Test
    public void reportValidationPairMethods() {
        Pair<Record, FailureReason> testPair = testFailedRecords.stream().findFirst().orElse(null);
        Assert.assertNotNull(testPair);
        Assert.assertThat(testPair.getLeft(), is(instanceOf(Record.class)));
        Assert.assertThat(testPair.getLeft().getReference(), equalTo(123456));
        Assert.assertThat(testPair.getLeft().getDescription(), is("Test Description"));
        Assert.assertThat(testPair.getRight(), is(FailureReason.DUPLICATE_REFERENCE_CODE));
    }
}