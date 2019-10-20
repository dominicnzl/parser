package ng.dominic.parser.service;

import ng.dominic.parser.model.Record;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;

public class RecordServiceCsvImplTest {

    private File recordsFile;

    private RecordServiceCsvImpl recordServiceCsv;

    @Before
    public void setup() {
        recordServiceCsv = new RecordServiceCsvImpl();
        recordsFile = new File(Objects.requireNonNull(
                getClass().getClassLoader().getResource("testfiles/testRecords.csv")).getFile()
        );
    }

    @Test
    public void parseFile() throws Exception {
        List<Record> expectedRecords = List.of(
                new Record(111111,
                        "NL11RABO11111111",
                        "testdescription 1",
                        new BigDecimal("10.00"),
                        new BigDecimal("5.00"),
                        new BigDecimal("15.00")),
                new Record(222222,
                        "NL22RABO22222222",
                        "testdescription 2",
                        new BigDecimal("33.33"),
                        new BigDecimal("-13.33"),
                        new BigDecimal("20.00")));
        List<Record> testRecords = recordServiceCsv.parseFile(recordsFile);
        assertThat(testRecords, hasSize(2));
        assertThat(testRecords.get(0).getReference(), is(equalTo(expectedRecords.get(0).getReference())));
        assertThat(testRecords.get(0).getDescription(), is(equalTo(expectedRecords.get(0).getDescription())));
        assertThat(testRecords.get(1).getReference(), is(equalTo(expectedRecords.get(1).getReference())));
        assertThat(testRecords.get(1).getDescription(), is(equalTo(expectedRecords.get(1).getDescription())));
    }
}