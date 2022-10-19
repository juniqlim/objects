package io.github.juniqlim.object.jwt;

import org.jose4j.keys.RsaKeyUtil;
import org.jose4j.lang.JoseException;
import org.junit.jupiter.api.Test;

import java.security.KeyPair;
import java.util.Base64;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class CreateKeyTest {
    @Test
    void test() throws JoseException {
        KeyPair keyPair = new RsaKeyUtil().generateKeyPair(2048);
        String publicKey = Base64.getEncoder().encodeToString(keyPair.getPublic().getEncoded());
        String privateKey = Base64.getEncoder().encodeToString(keyPair.getPrivate().getEncoded());

        assertTrue(publicKey.length() > 0);
        assertTrue(privateKey.length() > 0);
    }
}
