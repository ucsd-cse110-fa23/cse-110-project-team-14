package com.pantrypal;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.io.IOException;
import java.net.URISyntaxException;




public class UserStoryMS2_4Testing {

    //Tests if creation works for a new user
    @Test
    public void testLogin() throws IOException, URISyntaxException {
        DatabaseOPS db = new DatabaseOPS("users_test");
        db.deleteAllUsers();
        Login lo = new Login();
        lo.setCollectionName("users_test");
        boolean result = lo.checkCredentials("test", "test");
        assertEquals(false, result);
    }

    //Tests if creation works for an existing user
    @Test
    public void testLoginAndCreate() throws IOException, URISyntaxException {
        DatabaseOPS db = new DatabaseOPS("users_test");
        db.deleteAllUsers();
        Login lo = new Login();
        lo.setCollectionName("users_test");
        boolean result = lo.checkCredentials("test", "test");
        assertEquals(false, result);

        CreateAccount ca = new CreateAccount();
        ca.setCollectionName("users_test");
        result = ca.createAccount("test", "test");
        result = lo.checkCredentials("test", "test");
        assertEquals(true, result);
    }
}
