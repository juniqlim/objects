package io.github.juniqlim.object.jwt;

import org.jose4j.jws.AlgorithmIdentifiers;
import org.jose4j.jws.JsonWebSignature;
import org.jose4j.jwt.JwtClaims;
import org.jose4j.jwt.NumericDate;
import org.jose4j.lang.JoseException;

import java.security.Key;

public interface Jwt {
    String token() throws JoseException;
    boolean verifiable();

    class Jws implements Jwt {
        private final Key key;
        private final String token;

        public Jws(Key key) {
            this.key = key;
            this.token = null;
        }

        public Jws(Key key, String token) {
            this.key = key;
            this.token = token;
        }

        public String token() {
            if (token != null) {
                return token;
            }

            JwtClaims claims = new JwtClaims();
            claims.setExpirationTime(NumericDate.fromSeconds(60*60*24*365));
            claims.setGeneratedJwtId();
            claims.setIssuedAtToNow();

            JsonWebSignature jws = new JsonWebSignature();
            jws.setKey(key);
            jws.setHeader("typ", "JWT");
            jws.setPayload(claims.toJson());
            jws.setAlgorithmHeaderValue(AlgorithmIdentifiers.RSA_USING_SHA512);

            try {
                return jws.getCompactSerialization();
            } catch (JoseException e) {
                e.printStackTrace();
                throw new RuntimeException(e);
            }
        }

        @Override
        public boolean verifiable() {
            JsonWebSignature jws = new JsonWebSignature();
            jws.setKey(key);

            try {
                jws.setCompactSerialization(token);
                return jws.verifySignature();
            } catch (JoseException e) {
                e.printStackTrace();
                throw new RuntimeException(e);
            }
        }
    }

    Jwt FAKE = new Jwt() {
        @Override
        public String token() {
            return "fake.token";
        }

        @Override
        public boolean verifiable() {
            return false;
        }
    };
}
