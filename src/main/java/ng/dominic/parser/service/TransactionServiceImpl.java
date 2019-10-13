package ng.dominic.parser.service;

import ng.dominic.parser.model.Transaction;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Service
public class TransactionServiceImpl implements TransactionService {

    // The .csv file contains a header line which we do not want to have processed when creating transactions
    private Predicate<String> isNotHeader = str -> !str.startsWith("Reference");

    // Takes an uploaded file that Spring expects and converts it a java.io.File
    @Override
    public File convertToFile(MultipartFile multipartFile) throws IOException {
        File file = Optional.ofNullable(multipartFile)
                .map(MultipartFile::getOriginalFilename)
                .map(File::new)
                .orElseThrow(() -> new IOException("Uploaded file has no name"));
        if(file.createNewFile() && null != multipartFile) {
            FileOutputStream outputStream = new FileOutputStream(file);
            outputStream.write(multipartFile.getBytes());
        }
        return file;
    }

    @Override
    public List<Transaction> parseFile(File file) throws Exception {
        try (FileReader fileReader = new FileReader(file);
             BufferedReader bufferedReader = new BufferedReader(fileReader)) {
            return bufferedReader.lines()
                    .filter(isNotHeader)
                    .map(line -> line.split(","))
                    .map(this::createTransactionDTO)
                    .collect(Collectors.toList());
        }
    }

    @Override
    public Transaction createTransactionDTO(String[] columns) {
        int ref = Integer.parseInt(columns[0]);
        String acc = columns[1];
        String desc = columns[2];
        BigDecimal start = new BigDecimal(columns[3]);
        BigDecimal mut = new BigDecimal(columns[4]);
        BigDecimal end = new BigDecimal(columns[5]);
        return new Transaction(ref, acc, desc, start, mut, end);
    }
}
