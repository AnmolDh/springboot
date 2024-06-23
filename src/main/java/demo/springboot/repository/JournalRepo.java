package demo.springboot.repository;

import demo.springboot.entity.JournalEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JournalRepo extends MongoRepository<JournalEntity, Integer> {
}
