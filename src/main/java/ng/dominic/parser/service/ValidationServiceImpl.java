package ng.dominic.parser.service;

import ng.dominic.parser.model.FailureReason;
import ng.dominic.parser.model.Record;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;
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
    public boolean isValidated(List<Pair<Record, FailureReason>> rejectedRecords) {
        return rejectedRecords.isEmpty();
    }

    @Override
    public String reportValidationFailures(List<Pair<Record, FailureReason>> records) {
        return records.stream()
                .map(record -> "Record failed with reason: "
                        + record.getRight()
                        + "\n\t with reference: "
                        + record.getLeft().getReference()
                        + " and description: "
                        + record.getLeft().getDescription())
                .collect(Collectors.joining("\n\n"));
    }

    @Override
    public String validateAll(List<Record> records) {
        List<Pair<Record, FailureReason>> rejectedRecords = new ArrayList<>();
        rejectedRecords.addAll(validateReferenceCode(records));
        rejectedRecords.addAll(validateEndBalance(records));
        return isValidated(rejectedRecords)
                ? "All records ok"
                : reportValidationFailures(rejectedRecords);
    }

    /**
     * For each record, try to add the reference to a Set. Any reference that could not be added indicates that the
     * corresponding record is not unique.
     * @param records
     * @return a List of records which have non-unique reference codes and the corresponding failure reason
     */
    @Override
    public List<Pair<Record, FailureReason>> validateReferenceCode(List<Record> records) {
        Set<Integer> uniqueRefs = new HashSet<>();
        Predicate<Record> nonUniqueRecord = record -> !uniqueRefs.add(record.getReference());
        return records.stream()
                .filter(nonUniqueRecord)
                .map(record -> new ImmutablePair<>(record, FailureReason.DUPLICATE_REFERENCE_CODE))
                .collect(Collectors.toList());
    }

    /**
     * Check each record to make sure that the start balance corrected by the mutation is equal to the end balance,
     * and return any record that fail this test.
     * @param records
     * @return a list of records for which the end balance is incorrect, and the corresponding failure reason
     */
    @Override
    public List<Pair<Record, FailureReason>> validateEndBalance(List<Record> records) {
        Predicate<Record> endBalanceIncorrect = record -> !record.getStartBalance()
                .add(record.getMutation())
                .equals(record.getEndBalance());
        return records.stream()
                .filter(endBalanceIncorrect)
                .map(record -> new ImmutablePair<>(record, FailureReason.END_BALANCE_INCORRECT))
                .collect(Collectors.toList());
    }
}
