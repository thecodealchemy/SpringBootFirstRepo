package com.example.FirstProject.Service;

import com.example.FirstProject.Entity.Journal;
import com.example.FirstProject.Entity.User;
import com.example.FirstProject.repository.UsersRepository;
import java.util.List;
import java.util.Optional;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserService {

    @Autowired
    private UsersRepository  usersRepository;

    private final static Logger logger = LoggerFactory.getLogger(UserService.class);

//    private final static PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

//    public User createUser(User user) {
//        return usersRepository.save(user);
//    }

    public User addNewUser(User user) {
//        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(List.of("User"));
        return usersRepository.save(user);
    }

    public Optional<User> getUserById(ObjectId id) {
        return usersRepository.findById(id);
    }

    public List<User>  getAllUsers(){
        logger.info("getAllUsers Called through API");
        return usersRepository.findAll();
    }

    public User getUserByUserName(String username) {
        return usersRepository.getUserByUsername(username);
    }

    public void addJournal(String username, Journal journal) {
        User user = usersRepository.getUserByUsername(username);
        user.getJournalList().add(journal);
        usersRepository.save(user);
    }

    public void changeEmailOptStatus(String username, boolean flag) {
        User user = usersRepository.getUserByUsername(username);
        user.setEmailOptIn(flag);
        usersRepository.save(user);
    }
}
