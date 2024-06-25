package demo.springboot.repository;


import demo.springboot.entity.UserEntity;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends MongoRepository<UserEntity, ObjectId> {
    UserEntity findByUsername(String username);
}
