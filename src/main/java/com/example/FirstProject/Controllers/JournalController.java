package com.example.FirstProject.Controllers;

import com.example.FirstProject.Entity.Journal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/journal_v1")
public class JournalController {

    private final Map<Long, Journal> journalMap = new HashMap<>();

    @GetMapping
    public List<Journal> getJournal() {
        return new ArrayList<>(journalMap.values());
    }
//
//    @GetMapping("/id/{id}")
//    public Journal getJournalById(@PathVariable long id) {
//        return journalMap.get(id);
//    }
//
//    @PostMapping
//    public boolean postJournal(@RequestBody Journal journal) {
//        this.journalMap.put(journal.getId(), journal);
//        return true;
//    }
//
//    @DeleteMapping("id/{id}")
//    public void deleteJournal(@PathVariable long id) {
//        this.journalMap.remove(id);
//    }
//
//    @PutMapping("id/{id}")
//    public boolean putJournal(@PathVariable long id, @RequestBody Journal journal) {
//        this.journalMap.put(id, journal);
//        return true;
//    }

}
