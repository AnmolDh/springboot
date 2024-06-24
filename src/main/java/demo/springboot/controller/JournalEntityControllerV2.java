package demo.springboot.controller;

import demo.springboot.entity.JournalEntity;
import demo.springboot.service.JournalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<JournalEntity> createEntry(@RequestBody JournalEntity entity){
        try{
            journalService.saveEntity(entity);
            return new ResponseEntity<>(entity, HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }

    }

    @GetMapping("/find/{id}")
    public ResponseEntity<JournalEntity> findEntryById(@PathVariable int id){
        JournalEntity byId = journalService.findById(id);
        if (byId == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(byId, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteEntryById(@PathVariable int id){
        journalService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<JournalEntity> updateEntryById(@PathVariable int id, @RequestBody JournalEntity newEntry){
        JournalEntity oldEntry = journalService.findById(id);
        if (oldEntry != null) {
            oldEntry.setTitle(newEntry.getTitle() != null ? newEntry.getTitle() : oldEntry.getTitle());
            oldEntry.setContent(newEntry.getContent() != null ? newEntry.getContent() : oldEntry.getContent());
            journalService.saveEntity(oldEntry);
            return new ResponseEntity<>(oldEntry, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
