package Data.Secure.Secure.repository;

import Data.Secure.Secure.model.Document;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DocumentRepository extends JpaRepository<Document, Long> {
    Optional<Document> findByKey(String key);
}
