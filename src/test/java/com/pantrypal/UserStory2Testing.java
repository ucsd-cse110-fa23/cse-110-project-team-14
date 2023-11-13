package com.pantrypal;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import static org.mockito.Mockito.*;

import java.io.IOException;
import java.net.URISyntaxException;

public class UserStory2Testing {

    @Test
    public void testWhisper() {
        MockWhisper whisper = new MockWhisper();
        assertEquals("apple, cinammon, sugar and flour", whisper.getResponse(1));
    }

    @Test
    public void testWhisper2() {
        MockWhisper whisper = new MockWhisper();
        assertEquals("chicken, rice", whisper.getResponse(0));
    }

    @Test
    public void testWhisper3() {
        MockWhisper whisper = new MockWhisper();
        assertEquals("pasta, chicken, tomatoes", whisper.getResponse(5));
    }

    @Test
    public void testWhisper4() {
        MockWhisper whisper = new MockWhisper();
        assertEquals("bread, egg, tortilla", whisper.getResponse(6));
    }

    
}
