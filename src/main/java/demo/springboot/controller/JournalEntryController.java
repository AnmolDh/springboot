package demo.springboot.controller;

import demo.springboot.entity.JournalEntry;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/v1")
public class JournalEntryController {
    private Map<Long, JournalEntry> journalEntries = new HashMap<>();

    @GetMapping("/getEntries")
    public List<JournalEntry> getAll(){
        return new ArrayList<>(journalEntries.values());
    }

    @PostMapping("/createEntry")
    public void createEntry(@RequestBody JournalEntry entry){
        journalEntries.put(entry.getId(), entry);
    }

    @GetMapping("/find/{id}")
    public JournalEntry findEntryById(@PathVariable long id){
        return journalEntries.get(id);
    }
}
