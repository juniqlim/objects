package io.github.juniqlim.object.jwt;

import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class JwsTest {
    @Test
    void create() {
        String token = new Jws.RsaJws(Fixture.privateKey).token();
        assertTrue(new DecodedJws.DecodedRsaJws(Fixture.publicKey).verifiable(token));
    }

    @Test
    void withClaimsMap() {
        String token = new Jws.RsaJws(Fixture.privateKey).token(
            new HashMap<String, Object>() {{
                put("iat", 1666185451); // System.currentTimeMillis() / 1000L
                put("exp", 60 * 60 * 24);
                put("id", "1234567890");
            }}
        );
        assertEquals(
                "{\"id\":\"1234567890\",\"exp\":86400,\"iat\":1666185451}",
                new DecodedJws.DecodedRsaJws(Fixture.publicKey).payload(token)
        );
    }
}
