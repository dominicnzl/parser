package ng.dominic.parser.web;

import ng.dominic.parser.model.Transaction;
import ng.dominic.parser.service.TransactionServiceImpl;
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

    // TODO: 12/10/2019 sanity check, remove when up and running
    @RequestMapping("/hello")
    public String helloWorld() throws Exception {
        return "hello world, it is " + LocalDateTime.now();
    }

    @PostMapping("/csv")
    public List<Transaction> handleCsv(@RequestParam("file") MultipartFile multipartFile) throws Exception {
        File file = transactionService.convertMultipartFile(multipartFile);
        return transactionService.parseFile(file);
    }

//    @PostMapping("/txt")
}
