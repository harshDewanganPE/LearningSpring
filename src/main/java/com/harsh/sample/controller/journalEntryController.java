package com.harsh.sample.controller;

import java.time.LocalDateTime;
// import java.util.ArrayList;
// import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

// import org.apache.catalina.User;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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

//    3:50 video num -> 19

    @GetMapping
    public ResponseEntity<?> getAllJournalEntriesOfUser() {

//         User user = userService.findByUserName(userName);

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
        User user = userService.findByUserName(userName);

         List<JournalEntry> all = user.getJournalEntries(); 
        if(all != null && !all.isEmpty()){  
            return new ResponseEntity<>(all, HttpStatus.ACCEPTED);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


    @PostMapping
    public ResponseEntity<JournalEntry> createEntry(@RequestBody JournalEntry entry) {

        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String userName = authentication.getName();


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


//    @GetMapping
//    public ResponseEntity<?> getAll() {
//        // return service.getAll();
//        List<JournalEntry> all = service.getAll();
//        if(all != null && !all.isEmpty()){
//            return new ResponseEntity<>(all, HttpStatus.ACCEPTED);
//        }
//
//        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//    }

//    @PostMapping
//    public ResponseEntity<JournalEntry> createEntry(@RequestBody JournalEntry entry) {
//        try {
//            System.out.println("hi i am Harsh");
//            entry.setDate(LocalDateTime.now());
//            service.saveEntry(entry);
//            // journal.put(entry.getId(), entry);
//            return new ResponseEntity<>(entry, HttpStatus.CREATED);
//        } catch (Exception e) {
//            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//        }
//    }

    @GetMapping("id/{myId}")
    public ResponseEntity<?> getbyid(@PathVariable ObjectId myId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
        User user = userService.findByUserName(userName);
        List<JournalEntry> collect = user.getJournalEntries().stream().filter(entry -> entry.getId().equals(myId)).collect(Collectors.toList());
        if(!collect.isEmpty()){
            Optional<JournalEntry> journalEntry = service.findById(myId);
            if (journalEntry.isPresent()) {
                return new ResponseEntity<>(journalEntry.get(), HttpStatus.OK);
            }
        }
        // System.out.println("till here ir runs")
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("id/{myId}")
    public ResponseEntity<?> deleteById(@PathVariable ObjectId myId) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();

        try {
        boolean removed = service.deleteById(myId, userName);

        if(removed){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }


        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("id/{myId}")
    public ResponseEntity<?> updateById(@PathVariable ObjectId myId, @RequestBody JournalEntry newEntry){

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
        User user = userService.findByUserName(userName);
        List<JournalEntry> collect = user.getJournalEntries().stream().filter(entry -> entry.getId().equals(myId)).collect(Collectors.toList());

        if(!collect.isEmpty()){
            Optional<JournalEntry> journalEntry = service.findById(myId);
            if(journalEntry.isPresent()){
                JournalEntry old = journalEntry.get();

                old.setContent(newEntry.getContent() != null && !newEntry.getContent().equals("") ? newEntry.getContent()
                    : old.getContent());
            old.setTitle(newEntry.getTitle() != null && !newEntry.getTitle().equals("") ? newEntry.getTitle()
                    : old.getTitle());
            service.saveEntry(old);
            return new ResponseEntity<>(old, HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);

//        JournalEntry old = service.findById(myId).orElse(null);
//        if (old != null) {
//            System.out.println("old is found");
//            old.setContent(newEntry.getContent() != null && !newEntry.getContent().equals("") ? newEntry.getContent()
//                    : old.getContent());
//            old.setTitle(newEntry.getTitle() != null && !newEntry.getTitle().equals("") ? newEntry.getTitle()
//                    : old.getTitle());
//            service.saveEntry(old);
//            // return old;
//            return new ResponseEntity<>(old, HttpStatus.OK);
//        }
//        OKreturn new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
