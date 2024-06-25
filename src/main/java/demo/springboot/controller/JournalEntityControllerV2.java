package demo.springboot.controller;

import demo.springboot.entity.JournalEntity;
import demo.springboot.entity.UserEntity;
import demo.springboot.service.JournalService;
import demo.springboot.service.UserService;
import org.bson.types.ObjectId;
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

    @Autowired
    private UserService userService;

    @GetMapping("/getEntries/{username}")
    public ResponseEntity<List<JournalEntity>> getAllEntriesOfUser(@PathVariable String username){
        UserEntity user = userService.findByUsername(username);
        List<JournalEntity> fa = user.getJournalEntities();
        if (fa != null && !fa.isEmpty()) {
            return new ResponseEntity<>(fa, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/createEntry/{username}")
    public ResponseEntity<JournalEntity> createEntry(@RequestBody JournalEntity entity, @PathVariable String username){
        try{
            journalService.saveEntity(entity, username);
            return new ResponseEntity<>(entity, HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<JournalEntity> findEntryById(@PathVariable ObjectId id){
        JournalEntity byId = journalService.findById(id);
        if (byId == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(byId, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{username}/{id}")
    public ResponseEntity<?> deleteEntryById(@PathVariable ObjectId id, @PathVariable String username){
        journalService.deleteById(id, username);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/update/{username}/{id}")
    public ResponseEntity<JournalEntity> updateEntryById(@PathVariable ObjectId id, @PathVariable String username, @RequestBody JournalEntity newEntry){
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
