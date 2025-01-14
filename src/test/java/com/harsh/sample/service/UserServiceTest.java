package com.harsh.sample.service;

import com.harsh.sample.repository.UserRepository;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
public class UserServiceTest {

    @Autowired
    private UserRepository userRepository;

//    @Disabled
//    @Test
//    public void testAdd(){
//        User user = userRepository.findByUserName("harshog");
//        assertNotNull(userRepository.findByUserName("harshog"));
//        assertTrue(!user.getJournalEntries.isEmpty());
//    }

    @ParameterizedTest
    @CsvSource({
            "1,1,1",
            "2,10,12",
            "3,3,9"
    })
    public void test(int a, int b, int expected){
        assertEquals(expected, a+b, "failed for: " +a);
    }
}
