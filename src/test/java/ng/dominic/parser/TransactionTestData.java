package ng.dominic.parser;

import ng.dominic.parser.model.Transaction;
import org.apache.commons.lang3.RandomStringUtils;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

public class TransactionTestData {

    private static BigDecimal randomBigDecimal() {
        BigDecimal bigDecimal = new BigDecimal(Math.random());
        return bigDecimal.multiply(new BigDecimal(5)).setScale(2, RoundingMode.DOWN);
    }

    // Mock two transactions with a duplicate reference code "123".
    public static List<Transaction> referenceCodeDuplicated() {
        List<Transaction> transactions = new ArrayList<>();
        Transaction ref123 = new Transaction(123,
                RandomStringUtils.randomAlphanumeric(34),
                RandomStringUtils.randomAlphabetic(10),
                randomBigDecimal(),
                randomBigDecimal(),
                randomBigDecimal());
        transactions.add(ref123);
        transactions.add(ref123);
        return transactions;
    }
}
