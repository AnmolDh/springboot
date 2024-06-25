package demo.springboot.service;

import demo.springboot.entity.JournalEntity;
import demo.springboot.entity.UserEntity;
import demo.springboot.repository.JournalRepo;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JournalService {
    @Autowired
    private JournalRepo journalRepo;

    @Autowired
    private UserService userService;

    public void saveEntity(JournalEntity journalEntity, String username){
        UserEntity user = userService.findByUsername(username);
        JournalEntity saved = journalRepo.save(journalEntity);
        user.getJournalEntities().add(saved);
        userService.saveEntity(user);
    }

    public void saveEntity(JournalEntity journalEntity){
        journalRepo.save(journalEntity);
    }

    public List<JournalEntity> findAll(){
        return journalRepo.findAll();
    }

    public JournalEntity findById(ObjectId id){
        return journalRepo.findById(id).get();
    }

    public void deleteById(ObjectId id, String username){
        UserEntity user = userService.findByUsername(username);
        user.getJournalEntities().removeIf(x -> x.getId().equals(id));
        userService.saveEntity(user);
        journalRepo.deleteById(id);
    }
}
