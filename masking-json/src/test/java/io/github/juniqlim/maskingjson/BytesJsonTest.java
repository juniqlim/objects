package io.github.juniqlim.maskingjson;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class BytesJsonTest {
    @Test
    void test() {
        String value = new BytesJson("{\"title\":\"How to train your dragon\"}".getBytes()).valueOfName("title");
        assertEquals(value, "How to train your dragon");
    }
}