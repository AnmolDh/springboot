package demo.springboot.controller;

import demo.springboot.entity.JournalEntity;
import org.bson.types.ObjectId;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/v1")
public class JournalEntityController {
    private final Map<ObjectId, JournalEntity> journalEntries = new HashMap<>();

    @GetMapping("/getEntries")
    public List<JournalEntity> getAll(){
        return new ArrayList<>(journalEntries.values());
    }

    @PostMapping("/createEntry")
    public void createEntry(@RequestBody JournalEntity entry){
        journalEntries.put(entry.getId(), entry);
    }

    @GetMapping("/find/{id}")
    public JournalEntity findEntryById(@PathVariable long id){
        return journalEntries.get(id);
    }
}
