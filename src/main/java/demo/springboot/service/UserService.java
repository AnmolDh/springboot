package demo.springboot.service;

import demo.springboot.entity.JournalEntity;
import demo.springboot.entity.UserEntity;
import demo.springboot.repository.UserRepo;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class UserService {
    @Autowired
    UserRepo userRepo;

    public void saveEntity(UserEntity userEntity){
        userRepo.save(userEntity);
    }

    public List<UserEntity> findAll(){
        return userRepo.findAll();
    }

    public UserEntity findById(ObjectId id){
        return userRepo.findById(id).get();
    }

    public void deleteById(ObjectId id){
        userRepo.deleteById(id);
    }

    public UserEntity findByUsername(String username){
        return userRepo.findByUsername(username);
    }
}
