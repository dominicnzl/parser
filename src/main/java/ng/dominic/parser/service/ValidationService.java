package ng.dominic.parser.service;

import ng.dominic.parser.model.Record;

import java.util.List;

public interface ValidationService {

    // Receives a list of rejected records, and returns true if empty.
    public boolean isValidated(List<Record> records);

    // Receives a list of records and returns a message with the reference and description of the records.
    public String reportValidationFailures(List<Record> records);

    // Runs all specific validations and returns a message with the result of the validation.
    public String validateAll(List<Record> records);

    // Each reference code should be unique. This method returns a list of non-unique reference codes.
    public List<Record> validateReferenceCode(List<Record> records);

    // Each End balance needs to be validated. This method returns a list of records that fail the validation.
    public List<Record> validateEndBalance(List<Record> records);
}
