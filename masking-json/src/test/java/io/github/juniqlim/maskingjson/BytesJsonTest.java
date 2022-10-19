package io.github.juniqlim.maskingjson;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class BytesJsonTest {
    @Test
    void test() {
        String value = new BytesJson("{\"title\":\"How to train your dragon\"}".getBytes()).valueOfName("title");
        assertEquals(value, "How to train your dragon");
    }

    @Test
    void test2() {
        BytesJson bytesJson = new BytesJson("{\"title\":\"How to train your dragon\"}".getBytes());
        bytesJson.maskValueByName("title", 4);
        assertEquals(new String(bytesJson.bytes()), "{\"title\":\"****to train your dragon\"}");
    }

    @Test
    void test3() {
        BytesJson bytesJson = new BytesJson("{\"title\":\"How to train your dragon\", \"name\":\"juniq\", \"title\":\"123123\"}".getBytes());
        bytesJson.maskValueByName("title", 4);
        assertEquals(new String(bytesJson.bytes()), "{\"title\":\"****to train your dragon\"}");
    }
}
