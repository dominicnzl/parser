package ng.dominic.parser.service;

import ng.dominic.parser.model.Record;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Service
public class ValidationServiceImpl implements ValidationService {

    @Override
    public boolean isValidated(List<Record> rejectedRecords) {
        return rejectedRecords.isEmpty();
    }

    @Override
    public String reportValidationFailures(List<Record> records) {
        return records.stream()
                .map(record -> "Failed record with reference: "
                        + record.getReference()
                        + " and description: "
                        + record.getDescription())
                .collect(Collectors.joining("\n"));
    }

    @Override
    public List<Record> validateAll(List<Record> records) {
        List<Record> rejectedRecords = new ArrayList<>();
        rejectedRecords.addAll(validateReferenceCode(records));
        rejectedRecords.addAll(validateEndBalance(records));
        return rejectedRecords;
    }

    /**
     * For each record, try to add the reference to a Set. Any reference that could not be added indicates that the
     * corresponding record is not unique.
     * @param records
     * @return return a List of records which have non-unique reference codes
     */
    @Override
    public List<Record> validateReferenceCode(List<Record> records) {
        Set<Integer> uniqueRefs = new HashSet<>();
        Predicate<Record> nonUniqueRecord = record -> !uniqueRefs.add(record.getReference());
        return records.stream()
                .filter(nonUniqueRecord)
                .collect(Collectors.toList());
    }

    /**
     * Check each record to make sure that the start balance corrected by the mutation is equal to the end balance,
     * and return any record that fail this test.
     * @param records
     * @return
     */
    @Override
    public List<Record> validateEndBalance(List<Record> records) {
        Predicate<Record> endBalanceIncorrect = record -> !record.getStartBalance()
                .add(record.getMutation())
                .equals(record.getEndBalance());
        return records.stream()
                .filter(endBalanceIncorrect)
                .collect(Collectors.toList());
    }
}
