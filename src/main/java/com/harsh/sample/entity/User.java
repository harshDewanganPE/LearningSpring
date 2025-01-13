package com.harsh.sample.entity;

import java.util.ArrayList;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
// import org.springframework.data.mongodb.core.index.CompoundIndex;
// import org.springframework.data.mongodb.core.annotation.Collation;
// import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import com.mongodb.lang.NonNull;

// import lombok.AllArgsConstructor;
import lombok.Data;
// import lombok.Getter;
import lombok.NoArgsConstructor;

@Document(collection  = "users")
// @Getter
// @CompoundIndex(def = "{'userName' : 1}")
@Data
@NoArgsConstructor // Lombok will generate a no-args constructor
// @AllArgsConstructor
public class User {

    @Id
    private ObjectId id;
    
    @Indexed(unique = true)
    @NonNull
    private String userName;

    @NonNull
    private String password;

    @DBRef
    private ArrayList<JournalEntry> journalEntries = new ArrayList<>();

}
 