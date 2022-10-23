package io.github.juniqlim.object.jwt;

import org.jose4j.jws.JsonWebSignature;
import org.jose4j.lang.JoseException;

import java.security.Key;
import java.security.PublicKey;

public interface VerifiedJwt {
    boolean verifiable();

    class VerifiedJws implements VerifiedJwt {
        private final PublicKey publicKey;
        private final String token;

        public VerifiedJws(PublicKey publicKey, String token) {
            this.publicKey = publicKey;
            this.token = token;
        }

        @Override
        public boolean verifiable() {
            JsonWebSignature jws = new JsonWebSignature();
            jws.setKey(publicKey);

            try {
                jws.setCompactSerialization(token);
                return jws.verifySignature();
            } catch (JoseException e) {
                e.printStackTrace();
                throw new RuntimeException(e);
            }
        }
    }

    VerifiedJwt FAKE = () -> true;
}
