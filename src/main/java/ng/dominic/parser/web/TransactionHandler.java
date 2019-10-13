package ng.dominic.parser.web;

import ng.dominic.parser.model.Transaction;
import ng.dominic.parser.service.TransactionServiceImpl;
import ng.dominic.parser.service.ValidationServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.time.LocalDateTime;
import java.util.List;

@RestController
public class TransactionHandler {

    @Autowired
    private TransactionServiceImpl transactionService;

    @Autowired
    private ValidationServiceImpl validationService;

    // TODO: 12/10/2019 sanity check, remove when up and running
    @RequestMapping("/hello")
    public String hello() throws Exception {
        return "hello , it is " + LocalDateTime.now();
    }

    @PostMapping("/csv")
    public String handleCsv(@RequestParam("file") MultipartFile multipartFile) throws Exception {
        File file = transactionService.convertToFile(multipartFile);
        List<Transaction> importedTransactions = transactionService.parseFile(file);
        List<Transaction> rejectedTransactions = validationService.validateAll(importedTransactions);
        return validationService.isValidated(rejectedTransactions)
                ? "All transactions ok"
                : validationService.reportValidationFailures(rejectedTransactions);
    }

//    @PostMapping("/txt")
}
