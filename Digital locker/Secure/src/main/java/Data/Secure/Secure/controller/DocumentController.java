package Data.Secure.Secure.controller;

import Data.Secure.Secure.model.Document;
import Data.Secure.Secure.service.DocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
@RequestMapping("/documents")
public class DocumentController {

    @Autowired
    private DocumentService service;

    @PostMapping("/upload")
    public ResponseEntity<Document> uploadDocument(
            @RequestParam("file") MultipartFile file,
            @RequestParam("password") String password) throws IOException {
        return ResponseEntity.ok(service.saveDocument(file, password));
    }

    @GetMapping("/{key}")
    public ResponseEntity<Resource> getDocument(
            @PathVariable String key,
            @RequestParam String password) throws IOException {
        Document document = service.getDocument(key, password);
        Path path = Paths.get(document.getFilePath());
        Resource resource = new UrlResource(path.toUri());

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + document.getName())
                .body(resource);
    }
}

