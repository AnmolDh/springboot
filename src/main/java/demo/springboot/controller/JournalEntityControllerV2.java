package demo.springboot.controller;

import demo.springboot.entity.JournalEntity;
import demo.springboot.service.JournalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v2")
public class JournalEntityControllerV2 {

    @Autowired
    private JournalService journalService;

    @GetMapping("/getEntries")
    public List<JournalEntity> getAll(){
        return journalService.findAll();
    }

    @PostMapping("/createEntry")
    public void createEntry(@RequestBody JournalEntity entity){
        journalService.saveEntity(entity);
    }

    @GetMapping("/find/{id}")
    public JournalEntity findEntryById(@PathVariable int id){
        return journalService.findById(id);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteEntryById(@PathVariable int id){
        journalService.deleteById(id);
    }

    @PutMapping("/update/{id}")
    public void updateEntryById(@PathVariable int id, @RequestBody JournalEntity newEntry){
        JournalEntity oldEntry = journalService.findById(id).orElse(null);
        if (oldEntry != null) {
            oldEntry.setTitle(newEntry.getTitle() != null ? newEntry.getTitle() : oldEntry.getTitle());
            oldEntry.setContent(newEntry.getContent() != null ? newEntry.getContent() : oldEntry.getContent());
        }
        journalService.saveEntity(oldEntry);
    }
}
