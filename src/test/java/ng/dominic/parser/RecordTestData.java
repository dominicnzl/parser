package ng.dominic.parser;

import ng.dominic.parser.model.Record;
import org.apache.commons.lang3.RandomStringUtils;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

public class RecordTestData {

    private static BigDecimal randomBigDecimal() {
        BigDecimal bigDecimal = new BigDecimal(Math.random());
        return bigDecimal.multiply(new BigDecimal(5)).setScale(2, RoundingMode.DOWN);
    }

    private static Record createRecord(int ref, String accountNumber, String description, BigDecimal start,
                                       BigDecimal mutation, BigDecimal end) {
        return new Record(ref, accountNumber, description, start, mutation, end);
    }

    private static Record withRef123() {
        return createRecord(123,
            RandomStringUtils.randomAlphanumeric(34),
            RandomStringUtils.randomAlphabetic(10),
            randomBigDecimal(),
            randomBigDecimal(),
            randomBigDecimal());
    }

    // Mock two records with a duplicate reference code "123".
    public static List<Record> referenceCodeDuplicated() {
        List<Record> records = new ArrayList<>();
        Record ref123 = withRef123();
        records.add(ref123);
        records.add(ref123);
        return records;
    }

    public static Record withCorrectEndBalance() {
        return createRecord(11111,
                RandomStringUtils.randomAlphanumeric(34),
                RandomStringUtils.randomAlphabetic(10),
                new BigDecimal("33.33"),
                new BigDecimal("66.67"),
                new BigDecimal("100.00"));
    }

    public static Record withIncorrectEndBalance() {
        return createRecord(11111,
                RandomStringUtils.randomAlphanumeric(34),
                RandomStringUtils.randomAlphabetic(10),
                new BigDecimal("33.33"),
                new BigDecimal("66.67"),
                new BigDecimal("100.01"));
    }
}
