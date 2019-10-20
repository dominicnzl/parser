package ng.dominic.parser.service;

import ng.dominic.parser.model.Record;
import org.junit.Before;
import org.junit.Test;

import javax.xml.bind.JAXBException;
import java.io.File;
import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.junit.Assert.assertThat;

public class RecordServiceXmlImplTest {

    private File recordsFile;

    private RecordServiceXmlImpl recordServiceXml;

    @Before
    public void setup() {
        recordServiceXml = new RecordServiceXmlImpl();
        recordsFile = new File(Objects.requireNonNull(
                getClass().getClassLoader().getResource("testfiles/testRecords.xml")).getFile()
        );
    }

    @Test
    public void parseFile() throws JAXBException {
        List<Record> expectedRecords = List.of(
                new Record(333333,
                        "NL33RABO33333333",
                        "testdescription 3",
                        new BigDecimal("-99.99"),
                        new BigDecimal("-0.01"),
                        new BigDecimal("-100.00")),
                new Record(444444,
                        "NL44RABO4444444444",
                        "testdescription 4",
                        new BigDecimal("50"),
                        new BigDecimal("25"),
                        new BigDecimal("75")));
        List<Record> testRecords = recordServiceXml.parseFile(recordsFile);

        assertThat(testRecords, hasSize(2));
        assertThat(testRecords.get(0).getReference(), is(equalTo(expectedRecords.get(0).getReference())));
        assertThat(testRecords.get(0).getDescription(), is(equalTo(expectedRecords.get(0).getDescription())));
        assertThat(testRecords.get(1).getReference(), is(equalTo(expectedRecords.get(1).getReference())));
        assertThat(testRecords.get(1).getDescription(), is(equalTo(expectedRecords.get(1).getDescription())));
    }
}