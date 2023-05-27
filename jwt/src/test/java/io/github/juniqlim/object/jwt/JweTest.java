package io.github.juniqlim.object.jwt;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class JweTest {
    @Test
    void test() {
        String token = new Jwe.RsaJwe(Fixture.publicKey).token("plainText");
        assertEquals("plainText", new DecodedJwe.DecodedRsaJwe(Fixture.privateKey, token).plainText());
    }
}