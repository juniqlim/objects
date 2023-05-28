package io.github.juniqlim.object.jwt;

import org.jose4j.jws.JsonWebSignature;
import org.jose4j.lang.JoseException;

import java.security.PublicKey;

public interface DecodedJws {
    boolean verifiable(String token);
    String payload(String token);

    class DecodedRsaJws implements DecodedJws {
        private final PublicKey publicKey;

        public DecodedRsaJws(PublicKey publicKey) {
            this.publicKey = publicKey;
        }

        @Override
        public boolean verifiable(String token) {
            try {
                return jws(token).verifySignature();
            } catch (JoseException e) {
                throw new RuntimeException(e);
            }
        }

        @Override
        public String payload(String token) {
            try {
                return jws(token).getPayload();
            } catch (JoseException e) {
                throw new RuntimeException(e);
            }
        }

        private JsonWebSignature jws(String token) {
            try {
                JsonWebSignature jws = new JsonWebSignature();
                jws.setKey(publicKey);
                jws.setCompactSerialization(token);
                return jws;
            } catch (JoseException e) {
                throw new RuntimeException(e);
            }
        }
    }

    DecodedJws FAKE = new DecodedJws() {
        @Override
        public boolean verifiable(String token) {
            return true;
        }

        @Override
        public String payload(String token) {
            return "{\"exp\":31536000,\"jti\":\"24yR3vTibMA4dP5r8TSaDw\",\"iat\":1666185451}";
        }
    };
}
