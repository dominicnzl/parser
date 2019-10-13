package ng.dominic.parser.service;

import ng.dominic.parser.TransactionTestData;
import ng.dominic.parser.model.Transaction;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.Assert;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ValidationServiceImplTest {

    @Autowired
    private ValidationServiceImpl validationService;

    @Test
    public void validateReferenceCodeTestNotEmpty() {
        List<Transaction> test = validationService.validateReferenceCode(TransactionTestData.referenceCodeDuplicated());
        Assert.notEmpty(test, "The test should not be empty because it has a duplicate ref code");
    }

    @Test
    public void validateReferenceCodeTestExactlyOne() {
        List<Transaction> test = validationService.validateReferenceCode(TransactionTestData.referenceCodeDuplicated());
        Assert.isTrue(test.size() == 1, "There is one duplicate record");
    }
}