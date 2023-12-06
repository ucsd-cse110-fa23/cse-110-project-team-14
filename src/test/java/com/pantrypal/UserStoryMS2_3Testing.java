package com.pantrypal;
import com.pantrypal.model.CreateAccount;
import com.pantrypal.model.DatabaseOPS;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.io.IOException;
import java.net.URISyntaxException;




public class UserStoryMS2_3Testing {

    //Tests if creation works for a new user
    @Test
    public void testCreateAccount() throws IOException, URISyntaxException {
        DatabaseOPS db = new DatabaseOPS("users_test");
        db.deleteAllUsers();
        CreateAccount ca = new CreateAccount();
        ca.setCollectionName("users_test");
        boolean result = ca.createAccount("test", "test");
        assertEquals(true, result);

        result = ca.createAccount("test", "test");
        assertEquals(false, result);
    }

    //Tests if creation works for an existing user
    @Test
    public void testCreateAccount2() throws IOException, URISyntaxException {
        DatabaseOPS db = new DatabaseOPS("users_test");
        db.deleteAllUsers();
        CreateAccount ca = new CreateAccount();
        ca.setCollectionName("users_test");
        boolean result = ca.createAccount("test", "test");
        assertEquals(true, result);

        result = ca.createAccount("test", "test");
        assertEquals(false, result);
    }
}
