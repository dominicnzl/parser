package ng.dominic.parser.service;

import ng.dominic.parser.model.Transaction;
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
    public boolean isValidated(List<Transaction> rejectedTransactions) {
        return rejectedTransactions.isEmpty();
    }

    @Override
    public String reportValidationFailures(List<Transaction> transactions) {
        return transactions.stream()
                .map(transaction -> "Failed record with reference: "
                        + transaction.getReference()
                        + " and description: "
                        + transaction.getDescription())
                .collect(Collectors.joining("\n"));
    }

    @Override
    public List<Transaction> validateAll(List<Transaction> transactions) {
        List<Transaction> rejectedTransactions = new ArrayList<>();
        rejectedTransactions.addAll(validateReferenceCode(transactions));
        rejectedTransactions.addAll(validateEndBalance(transactions));
        return rejectedTransactions;
    }

    /**
     * For each transaction, try to add the reference to a Set. Any reference that could not be added indicates that the
     * corresponding transaction is not unique.
     * @param transactions
     * @return return a List of transactions which have non-unique reference codes
     */
    @Override
    public List<Transaction> validateReferenceCode(List<Transaction> transactions) {
        Set<Integer> uniqueRefs = new HashSet<>();
        Predicate<Transaction> nonUniqueTransaction = transaction -> !uniqueRefs.add(transaction.getReference());
        return transactions.stream()
                .filter(nonUniqueTransaction)
                .collect(Collectors.toList());
    }

    /**
     * Check each transaction to make sure that the start balance corrected by the mutation is equal to the end balance,
     * and return any transactions that fail this test.
     * @param transactions
     * @return
     */
    @Override
    public List<Transaction> validateEndBalance(List<Transaction> transactions) {
        Predicate<Transaction> endBalanceIncorrect = transaction -> !transaction.getStartBalance()
                .add(transaction.getMutation())
                .equals(transaction.getEndBalance());
        return transactions.stream()
                .filter(endBalanceIncorrect)
                .collect(Collectors.toList());
    }
}
