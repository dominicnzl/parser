package ng.dominic.parser.service;

import ng.dominic.parser.model.Transaction;

import java.util.List;

public interface ValidationService {

    // Receives a list of rejected transactions, and returns true if empty.
    public boolean isValidated(List<Transaction> transactions);

    // Receives a list of transactions and returns a message with the reference and description of the transactions.
    public String reportValidationFailures(List<Transaction> transactions);

    // Runs all specific validations and returns a list of rejected transactions.
    public List<Transaction> validateAll(List<Transaction> transactions);

    // Each reference code should be unique. This method returns a list of non-unique reference codes.
    public List<Transaction> validateReferenceCode(List<Transaction> transactions);

    // Each End balance needs to be validated. This method returns a list of transactions that fail the validation.
    public List<Transaction> validateEndBalance(List<Transaction> transactions);
}
