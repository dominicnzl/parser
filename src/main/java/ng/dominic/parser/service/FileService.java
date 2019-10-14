package ng.dominic.parser.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

public interface FileService {

    // Takes an uploaded file that Spring expects and converts it a java.io.File
    public File convertToFile(MultipartFile multipartFile) throws IOException;
}
