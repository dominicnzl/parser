package ng.dominic.parser.service;

import ng.dominic.parser.RecordTestData;
import ng.dominic.parser.model.FailureReason;
import ng.dominic.parser.model.Record;
import org.apache.commons.lang3.tuple.Pair;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ValidationServiceImplTest {

    @Autowired
    private ValidationServiceImpl validationService;

    @Test
    public void validateReferenceCodeTestNotEmpty() {
        List<Pair<Record, FailureReason>> test = validationService.validateReferenceCode(RecordTestData.referenceCodeDuplicated());
        Assert.notEmpty(test, "The test should not be empty because it has a duplicate ref code");
    }

    @Test
    public void validateReferenceCodeTestExactlyOne() {
        List<Pair<Record, FailureReason>>  test = validationService.validateReferenceCode(RecordTestData.referenceCodeDuplicated());
        Assert.isTrue(test.size() == 1, "There is one duplicate record");
    }

    @Test
    public void validateEndBalanceHappyFlow() {
        List<Record> records = new ArrayList<>();
        records.add(RecordTestData.withCorrectEndBalance());
        List<Pair<Record, FailureReason>>  test = validationService.validateEndBalance(records);
        Assert.isTrue(test.isEmpty(), "The test should be empty because the endbalance is correct");
    }

    @Test
    public void validateEndBalanceExpectFail() {
        List<Record> records = new ArrayList<>();
        records.add(RecordTestData.withIncorrectEndBalance());
        List<Pair<Record, FailureReason>>  test = validationService.validateEndBalance(records);
        Assert.notEmpty(test, "The test should not be empty because the endbalance is incorrect");
    }
}