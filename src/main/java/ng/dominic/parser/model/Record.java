package ng.dominic.parser.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import java.math.BigDecimal;

@XmlAccessorType(XmlAccessType.FIELD)
public class Record {

    @XmlAttribute(name = "reference")
    private int reference;

    @XmlElement(name = "accountNumber")
    private String accountNumber;

    @XmlElement(name = "description")
    private String description;

    @XmlElement(name = "startBalance")
    private BigDecimal startBalance;

    @XmlElement(name = "mutation")
    private BigDecimal mutation;

    @XmlElement(name = "endBalance")
    private BigDecimal endBalance;

    // This no-arg constructor required for JAXB
    private Record() {
    }

    public Record(int reference, String accountNumber, String description, BigDecimal startBalance,
                  BigDecimal mutation, BigDecimal endBalance) {
        this.reference = reference;
        this.accountNumber = accountNumber;
        this.description = description;
        this.startBalance = startBalance;
        this.mutation = mutation;
        this.endBalance = endBalance;
    }

    public int getReference() {
        return reference;
    }

    public void setReference(int reference) {
        this.reference = reference;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getStartBalance() {
        return startBalance;
    }

    public void setStartBalance(BigDecimal startBalance) {
        this.startBalance = startBalance;
    }

    public BigDecimal getMutation() {
        return mutation;
    }

    public void setMutation(BigDecimal mutation) {
        this.mutation = mutation;
    }

    public BigDecimal getEndBalance() {
        return endBalance;
    }

    public void setEndBalance(BigDecimal endBalance) {
        this.endBalance = endBalance;
    }
}
