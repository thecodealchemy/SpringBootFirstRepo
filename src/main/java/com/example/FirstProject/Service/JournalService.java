package com.example.FirstProject.Service;

import com.example.FirstProject.Entity.Journal;
import com.example.FirstProject.Entity.User;
import com.example.FirstProject.repository.JournalRepository;
import java.util.List;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Slf4j
public class JournalService {

    @Autowired
    private JournalRepository journalRepository;

    @Autowired
    private UserService usersService;

    @Transactional
    public void saveEntry(String username, Journal journal) {
        try {
            Journal savedJournal = journalRepository.save(journal);
            usersService.addJournal(username, savedJournal);
        } catch (Exception e) {
            log.error("Journal: {} could not be saved: ", journal, e);
            throw new RuntimeException("Journal could not be saved");
        }
    }

    public List<Journal> findAll(String username) {
        log.info("Find all Journals for Username: {}", username);
        User user = usersService.getUserByUserName(username);
        return user.getJournalList();
    }

    public Optional<Journal> findById(String username, ObjectId id) {
        User user = usersService.getUserByUserName(username);
        return user.getJournalList().stream().filter(j -> j.getId().equals(id)).findFirst();
    }

    public void removeEntry(ObjectId id) {
        journalRepository.deleteById(id);
    }

    public void putEntry(Journal journal) {
        journalRepository.save(journal);
    }
}
