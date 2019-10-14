package ng.dominic.parser.model;

public enum FailureReason {
    DUPLICATE_REFERENCE_CODE("\"Reference code already exists\""),
    END_BALANCE_INCORRECT("\"Start balance and mutation do not add up to end balance\"");

    public String label;

    FailureReason(String label) {
        this.label = label;
    }

    @Override
    public String toString() {
        return this.label;
    }
}
