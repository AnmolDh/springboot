package demo.springboot.service;

import demo.springboot.entity.JournalEntity;
import demo.springboot.repository.JournalRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JournalService {
    @Autowired
    private JournalRepo journalRepo;

    public void saveEntity(JournalEntity journalEntity){
        journalRepo.save(journalEntity);
    }

    public List<JournalEntity> findAll(){
        return journalRepo.findAll();
    }

    public JournalEntity findById(int id){
        return journalRepo.findById(id).get();
    }

    public void deleteById(int id){
        journalRepo.deleteById(id);
    }
}
