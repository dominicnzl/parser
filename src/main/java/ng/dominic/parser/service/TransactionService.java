package ng.dominic.parser.service;

import ng.dominic.parser.model.Transaction;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;

public interface TransactionService {

    public File convertMultipartFile(MultipartFile multipartFile) throws IOException;

    public List<Transaction> parseFile(File file) throws Exception;

    public Transaction createTransactionDTO(String[] elements);
}
