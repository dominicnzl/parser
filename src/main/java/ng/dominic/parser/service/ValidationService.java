package ng.dominic.parser.service;

import ng.dominic.parser.model.FailureReason;
import ng.dominic.parser.model.Record;
import org.apache.commons.lang3.tuple.Pair;

import java.util.List;

public interface ValidationService {

    // Receives a list of rejected records, and returns true if empty.
    public boolean isValidated(List<Pair<Record, FailureReason>> records);

    // Receives a list of records and returns a message with their failure reason, reference and description.
    public String reportValidationFailures(List<Pair<Record, FailureReason>> records);

    // Runs all specific validations and returns a message with the result of the validation.
    public String validateAll(List<Record> records);

    // Each reference code should be unique. Returns a list of non-unique reference codes and the failure reason.
    public List<Pair<Record, FailureReason>> validateReferenceCode(List<Record> records);

    // Each End balance needs to be validated. Returns the records that fail the validation and the failure reason.
    public List<Pair<Record, FailureReason>> validateEndBalance(List<Record> records);
}
