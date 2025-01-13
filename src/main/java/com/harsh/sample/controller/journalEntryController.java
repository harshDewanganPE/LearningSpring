package com.harsh.sample.controller;

import java.time.LocalDateTime;
// import java.util.ArrayList;
// import java.util.HashMap;
import java.util.List;
import java.util.Optional;

// import org.apache.catalina.User;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.harsh.sample.entity.JournalEntry;
// import org.springframework.web.bind.annotation.RequestParam;
import com.harsh.sample.service.JournalEntryService;
import com.harsh.sample.service.UserService;
import com.harsh.sample.entity.*;

// import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequestMapping("/journal")
public class journalEntryController {

    // private HashMap<Long, JournalEntry> journal = new HashMap<>();

    @Autowired
    private JournalEntryService service;

    @Autowired
    private UserService userService;

    @GetMapping("{userName}")
    public ResponseEntity<?> getAllJournalEntriesOfUser(@PathVariable String userName) {

         User user = userService.findByUserName(userName);

         List<JournalEntry> all = user.getJournalEntries(); 
        if(all != null && !all.isEmpty()){  
            return new ResponseEntity<>(all, HttpStatus.ACCEPTED);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


    @PostMapping("{userName}")
    public ResponseEntity<JournalEntry> createEntry(@PathVariable String userName, @RequestBody JournalEntry entry) {

        try { 

            // User user = userService.findByUserName(userName);
            service.saveEntry(entry, userName);

            // System.out.println("hi i am Harsh");
            // entry.setDate(LocalDateTime.now());
            // service.saveEntry(entry);
            // journal.put(entry.getId(), entry);
            return new ResponseEntity<>(entry, HttpStatus.CREATED);

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

    }


    @GetMapping
    public ResponseEntity<?> getAll() {
        // return service.getAll();
        List<JournalEntry> all = service.getAll();
        if(all != null && !all.isEmpty()){
            return new ResponseEntity<>(all, HttpStatus.ACCEPTED);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity<JournalEntry> createEntry(@RequestBody JournalEntry entry) {
        try {
            System.out.println("hi i am Harsh");
            entry.setDate(LocalDateTime.now());
            service.saveEntry(entry);
            // journal.put(entry.getId(), entry);
            return new ResponseEntity<>(entry, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("id/{myId}")
    public ResponseEntity<JournalEntry> getbyid(@PathVariable ObjectId myId) {
        // System.out.println("till here ir runs");
        Optional<JournalEntry> journalEntry = service.findById(myId);
        if (journalEntry.isPresent()) {
            return new ResponseEntity<>(journalEntry.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("id/{userName}/{myId}")
    public ResponseEntity<?> deleteById(@PathVariable ObjectId myId, @PathVariable String userName) {
        try {
        service.deleteById(myId, userName);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("id/{userName}/{myId}")
    public ResponseEntity<?> updateById(@PathVariable ObjectId myId, @RequestBody JournalEntry newEntry, @PathVariable String userName){
        JournalEntry old = service.findById(myId).orElse(null);
        if (old != null) {
            System.out.println("old is found");
            old.setContent(newEntry.getContent() != null && !newEntry.getContent().equals("") ? newEntry.getContent()
                    : old.getContent());
            old.setTitle(newEntry.getTitle() != null && !newEntry.getTitle().equals("") ? newEntry.getTitle()
                    : old.getTitle());
            service.saveEntry(old);
            // return old;
            return new ResponseEntity<>(old, HttpStatus.OK);   
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
