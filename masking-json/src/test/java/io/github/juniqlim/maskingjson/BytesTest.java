package io.github.juniqlim.maskingjson;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class BytesTest {
    String shortJson;
    String longJson;

    @BeforeEach
    void setUp() {
        shortJson = "{\"title\": \"How to train your dragon\"}";

        longJson = "{\n" + "  \"articles\": [\n" + "    {\n" + "      \"slug\": \"how-to-train-your-dragon\",\n"
            + "      \"title\": \"How to train your dragon\",\n" + "      \"description\": \"Ever wonder how?\",\n"
            + "      \"body\": \"It takes a Jacobian\",\n" + "      \"tagList\": [\n" + "        \"dragons\",\n"
            + "        \"training\"\n" + "      ],\n" + "      \"createdAt\": \"2016-02-18T03:22:56.637Z\",\n"
            + "      \"updatedAt\": \"2016-02-18T03:48:35.824Z\",\n" + "      \"favorited\": false,\n"
            + "      \"favoritesCount\": 0,\n" + "      \"author\": {\n" + "        \"username\": \"jake\",\n"
            + "        \"bio\": \"I work at statefarm\",\n"
            + "        \"image\": \"https://i.stack.imgur.com/xHWG8.jpg\",\n" + "        \"following\": false\n"
            + "      }\n" + "    },\n" + "    {\n" + "      \"slug\": \"how-to-train-your-dragon-2\",\n"
            + "      \"title\": \"How to train your dragon 2\",\n" + "      \"description\": \"So toothless\",\n"
            + "      \"body\": \"It a dragon\",\n" + "      \"tagList\": [\n" + "        \"dragons\",\n"
            + "        \"training\"\n" + "      ],\n" + "      \"createdAt\": \"2016-02-18T03:22:56.637Z\",\n"
            + "      \"updatedAt\": \"2016-02-18T03:48:35.824Z\",\n" + "      \"favorited\": false,\n"
            + "      \"favoritesCount\": 0,\n" + "      \"author\": {\n" + "        \"username\": \"jake\",\n"
            + "        \"bio\": \"I work at statefarm\",\n"
            + "        \"image\": \"https://i.stack.imgur.com/xHWG8.jpg\",\n" + "        \"following\": false\n"
            + "      }\n" + "    }\n" + "  ],\n" + "  \"articlesCount\": 2\n" + "}";
    }

    @Test
    void shortStringIndexOf() {
        int indexOf = new Bytes(shortJson.getBytes()).indexOf("\"title\"".getBytes());
        assertEquals(indexOf, 1);
        assertEquals(shortJson.getBytes()[indexOf], '"');
    }

    @Test
    void longStringIndexOf() {
        int indexOf = new Bytes(longJson.getBytes()).indexOf("title".getBytes());
        assertEquals(indexOf, 73);
        assertEquals(longJson.getBytes()[indexOf], 't');
    }

    @Test
    void longStringIndexOfFromIndex() {
        Bytes bytes = new Bytes(longJson.getBytes());
        int indexOf = bytes.indexOf("title".getBytes());
        assertEquals(indexOf, 73);
        assertEquals(longJson.getBytes()[indexOf], 't');

        int nextIndexOf = bytes.indexOf("title".getBytes(), indexOf + 1);
        assertEquals(nextIndexOf, 639);
        assertEquals(longJson.getBytes()[nextIndexOf], 't');

        int next2IndexOf = bytes.indexOf("title".getBytes(), nextIndexOf + 1);
        assertEquals(next2IndexOf, -1);
    }
}
