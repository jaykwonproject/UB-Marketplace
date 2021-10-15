package com.ubmarketplace.app.repository;

import com.ubmarketplace.app.model.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import java.util.ArrayList;
import java.util.List;

import static com.ubmarketplace.app.Static.TEST_PASSWORD_1;
import static com.ubmarketplace.app.Static.TEST_PASSWORD_2;
import static com.ubmarketplace.app.Static.TEST_USER_NAME_1;
import static com.ubmarketplace.app.Static.TEST_USER_NAME_2;

@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class UserRepositoryTest {
    @Autowired
    UserRepository userRepository;

    @Test
    void GIVEN_validUser_WHEN_insert_THEN_insertIntoDatabase() {
        // If it doesn't fail, see as a success
        Assertions.assertDoesNotThrow(() -> userRepository.insert(User.builder()
                .username(TEST_USER_NAME_1)
                .password(TEST_PASSWORD_1)
                .build()));
    }

    @Test
    void GIVEN_validUser_WHEN_remove_THEN_removeFromDatabase() {
        // If it doesn't fail, see as a success
        Assertions.assertDoesNotThrow(() -> userRepository.insert(User.builder()
                .username(TEST_USER_NAME_1)
                .build()));
    }

    @Test
    void GIVEN_usersInDatabase_WHEN_findAll_THEN_findAllItems() {
        userRepository.insert(User.builder()
                .username(TEST_USER_NAME_1)
                .password(TEST_PASSWORD_1)
                .build());
        userRepository.insert(User.builder()
                .username(TEST_USER_NAME_2)
                .password(TEST_PASSWORD_2)
                .build());

        List<String> validItemId = new ArrayList<String>(){{
            add(TEST_USER_NAME_1);
            add(TEST_USER_NAME_2);
        }};

        List<User> result = userRepository.findAll();
        for(User user : result){
            Assertions.assertTrue(validItemId.contains(user.getUsername()));
        }
    }

    @Test
    void GIVEN_usersInDatabase_WHEN_findByItemID_THEN_returnTheItem() {
        User user1 = User.builder()
                .username(TEST_USER_NAME_1)
                .password(TEST_PASSWORD_1)
                .build();
        userRepository.insert(user1);
        userRepository.insert(User.builder()
                .username(TEST_USER_NAME_2)
                .password(TEST_PASSWORD_2)
                .build());

        Assertions.assertEquals(user1, userRepository.findByUsername(TEST_USER_NAME_1));
    }
}
