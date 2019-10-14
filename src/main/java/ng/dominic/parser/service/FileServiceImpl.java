package ng.dominic.parser.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Optional;

@Service
public class FileServiceImpl implements FileService {

    @Override
    public File convertToFile(MultipartFile multipartFile) throws IOException {
        File file = Optional.ofNullable(multipartFile)
                .map(MultipartFile::getOriginalFilename)
                .map(File::new)
                .orElseThrow(() -> new IOException("Uploaded file has no name"));
        if(file.createNewFile() && null != multipartFile) {
            try(FileOutputStream outputStream = new FileOutputStream(file)) {
                outputStream.write(multipartFile.getBytes());
            };
        }
        return file;
    }
}
