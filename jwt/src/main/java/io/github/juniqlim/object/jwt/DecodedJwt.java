package io.github.juniqlim.object.jwt;

import org.jose4j.jws.JsonWebSignature;
import org.jose4j.lang.JoseException;

import java.security.PublicKey;

public interface DecodedJwt {
    boolean verifiable();
    String payload();

    class DecodedJws implements DecodedJwt {
        private final PublicKey publicKey;
        private final String token;

        public DecodedJws(PublicKey publicKey, String token) {
            this.publicKey = publicKey;
            this.token = token;
        }

        @Override
        public boolean verifiable() {
            try {
                return jws().verifySignature();
            } catch (JoseException e) {
                throw new RuntimeException(e);
            }
        }

        @Override
        public String payload() {
            try {
                return jws().getPayload();
            } catch (JoseException e) {
                throw new RuntimeException(e);
            }
        }

        private JsonWebSignature jws() {
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

    DecodedJwt FAKE = new DecodedJwt() {
        @Override
        public boolean verifiable() {
            return true;
        }

        @Override
        public String payload() {
            return "{\"exp\":31536000,\"jti\":\"24yR3vTibMA4dP5r8TSaDw\",\"iat\":1666185451}";
        }
    };
}
