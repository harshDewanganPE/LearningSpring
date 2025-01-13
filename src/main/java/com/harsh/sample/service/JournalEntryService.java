package com.harsh.sample.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

// import javax.management.RuntimeErrorException;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
// import org.springframework.validation.ObjectError;
import org.springframework.transaction.annotation.Transactional;

import com.harsh.sample.entity.JournalEntry;
import com.harsh.sample.entity.*;

import com.harsh.sample.repository.JournalEntryRepository;

@Component
public class JournalEntryService {

    @Autowired
    private JournalEntryRepository repository;

    @Autowired
    private UserService userService;

//    @Transactional
    public void saveEntry(JournalEntry entry, String userName) {

        try {
            User user = userService.findByUserName(userName);
            entry.setDate(LocalDateTime.now());
            JournalEntry saved = repository.save(entry);
            user.getJournalEntries().add(saved);
            userService.saveEntry(user);

        } catch (Exception e) {
            System.out.println(e);
            throw new RuntimeException("an error occured" , e);
        }
        // repository.save(entry);
        // 14th 31:27
    }

    public void saveEntry(JournalEntry entry) {
        repository.save(entry);
    }

    public List<JournalEntry> getAll() {
        return repository.findAll();
    }

    public Optional<JournalEntry> findById(ObjectId id) {
        return repository.findById(id);
    }

    public void deleteById(ObjectId id) {
        repository.deleteById(id);
    }

    public void deleteById(ObjectId myId, String userName) {
        User user = userService.findByUserName(userName);
        user.getJournalEntries().removeIf(x -> x.getId().equals(myId));
        userService.saveEntry(user);
        repository.deleteById(myId);
        // throw new UnsupportedOperationException("Unimplemented method 'deleteById'");
    }
}
