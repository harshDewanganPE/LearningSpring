package com.harsh.sample.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

// import javax.management.RuntimeErrorException;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
// import org.springframework.validation.ObjectError;

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
            userService.saveUser(user);
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

    public boolean deleteById(ObjectId myId, String userName) {
        boolean removed = false;
        try{
            User user = userService.findByUserName(userName);
            removed = user.getJournalEntries().removeIf(x -> x.getId().equals(myId));
            if(removed){
                userService.saveUser(user);
                repository.deleteById(myId);
            }


        }catch (Exception e){
             System.out.println(e);
             throw new RuntimeException("an error occured while deleting the entry", e);
        }

        return removed;
        // throw new UnsupportedOperationException("Unimplemented method 'deleteById'");
    }




}
