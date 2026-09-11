package com.example.FirstProject.Controllers;

import com.example.FirstProject.Entity.Journal;
import com.example.FirstProject.Service.JournalService;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/journal")
public class JournalControllerV2 {

    @Autowired
    private JournalService  journalService;

    @GetMapping("{username}")
    public ResponseEntity<List<Journal>> getJournal(@PathVariable String username) {
        List<Journal> allEntries = journalService.findAll(username);
        if(allEntries.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(allEntries, HttpStatus.OK);
    }

    @GetMapping("{username}/journalId/{id}")
    public ResponseEntity<Journal> getJournalById(@PathVariable String username, @PathVariable ObjectId id) {

        Optional<Journal> journalEntry = journalService.findById(username, id);
        return journalEntry.map(journal -> new ResponseEntity<>(journal, HttpStatus.OK))
                           .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("{username}")
    public ResponseEntity<Journal> postJournal(@PathVariable String username, @RequestBody Journal journal) {
        try {
            journal.setDate(LocalDateTime.now());
            journalService.saveEntry(username, journal);
            return new ResponseEntity<>(journal, HttpStatus.CREATED);
        } catch (Exception e) {
            return  new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("id/{id}")
    public void deleteJournal(@PathVariable ObjectId id) {
        journalService.removeEntry(id);
    }

    @PutMapping
    public boolean putJournal(@RequestBody Journal journal) {
        journalService.putEntry(journal);
        return true;
    }

}
