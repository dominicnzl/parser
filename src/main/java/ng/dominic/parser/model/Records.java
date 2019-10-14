package ng.dominic.parser.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(namespace = "")
@XmlAccessorType(XmlAccessType.FIELD)
public class Records {

    // This no-args constructor required for JAXB
    public Records(){
    }

    public Records(List<Record> records) {
        this.records = records;
    }

    @XmlElement(name = "record")
    private List<Record> records;

    public List<Record> getRecords() {
        return records;
    }

    public void setRecords(List<Record> records) {
        this.records = records;
    }
}
