package ng.dominic.parser.service;

import ng.dominic.parser.model.Record;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.math.BigDecimal;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Service
public class RecordServiceCsvImpl implements RecordService {

    // The .csv file contains a header line which we do not want to have processed when creating records
    private Predicate<String> isNotHeader = str -> !str.startsWith("Reference");

    @Override
    public List<Record> parseFile(File file) throws Exception {
        try (FileReader fileReader = new FileReader(file);
             BufferedReader bufferedReader = new BufferedReader(fileReader)) {
            return bufferedReader.lines()
                    .filter(isNotHeader)
                    .map(line -> line.split(","))
                    .map(this::createRecordDTO)
                    .collect(Collectors.toList());
        }
    }

    private Record createRecordDTO(String[] columns) {
        int ref = Integer.parseInt(columns[0]);
        String acc = columns[1];
        String desc = columns[2];
        BigDecimal start = new BigDecimal(columns[3]);
        BigDecimal mut = new BigDecimal(columns[4]);
        BigDecimal end = new BigDecimal(columns[5]);
        return new Record(ref, acc, desc, start, mut, end);
    }
}
