package io.github.juniqlim.maskingjson;

import io.github.juniqlim.maskingjson.FindedStringValueInJson.Result;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class FindedStringValueInJsonTest {
    @Test
    void test() {
        String sourceJson = "{\n" + "  \"articles\": [\n" + "    {\n" + "      \"slug\": \"how-to-train-your-dragon\",\n"
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

        String target = "title";
        Result value = new FindedStringValueInJson(new StringBuffer(sourceJson), target).value();
        Assertions.assertEquals(value.startIndex(), 82);
        Assertions.assertEquals(value.endIndex(), 106);
        Assertions.assertEquals(value.findedString(), "How to train your dragon");
    }

    @Test
    void test2() {
        String sourceJson = "{\"title\": \"dragon\"}";

        String target = "title";
        Result value = new FindedStringValueInJson(new StringBuffer(sourceJson), target).value();
        Assertions.assertEquals(value.startIndex(), 11);
        Assertions.assertEquals(value.endIndex(), 17);
        Assertions.assertEquals(value.findedString(), "dragon");

        System.out.println("value = " + sourceJson.substring(11, 17));
    }
}
