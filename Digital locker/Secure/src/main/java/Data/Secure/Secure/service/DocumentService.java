package Data.Secure.Secure.service;

import Data.Secure.Secure.model.Document;
import Data.Secure.Secure.repository.DocumentRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

@Service
public class DocumentService {

    private final String uploadDir; // Directory for storing uploaded files
    private final DocumentRepository repository;

    // Constructor with injection for upload directory and repository
    public DocumentService(
            @Value("${file.upload-dir}") String uploadDir,
            DocumentRepository repository) {
        this.uploadDir = uploadDir;
        this.repository = repository;

        // Ensure the upload directory exists
        try {
            Files.createDirectories(Paths.get(uploadDir));
        } catch (IOException e) {
            throw new RuntimeException("Could not create upload directory", e);
        }
    }

    // Save a document
    public Document saveDocument(org.springframework.web.multipart.MultipartFile file, String password) throws IOException {
        String key = UUID.randomUUID().toString(); // Generate a unique key
        String filePath = uploadDir + "/" + file.getOriginalFilename(); // Resolve file path

        // Save the file to the upload directory
        Files.copy(file.getInputStream(), Paths.get(filePath), StandardCopyOption.REPLACE_EXISTING);

        // Save metadata to the database
        Document document = new Document();
        document.setName(file.getOriginalFilename());
        document.setKey(key);
        document.setPassword(password);
        document.setUploadDate(LocalDate.now());
        document.setFilePath(filePath);

        return repository.save(document); // Save the document entity
    }

    // Get a document by key and password
    public Document getDocument(String key, String password) {
        Optional<Document> optionalDoc = repository.findByKey(key);
        if (optionalDoc.isEmpty() || !optionalDoc.get().getPassword().equals(password)) {
            throw new RuntimeException("Invalid key or password");
        }
        return optionalDoc.get();
    }
}
