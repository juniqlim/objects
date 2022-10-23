package io.github.juniqlim.object.jwt;

import org.jose4j.jws.AlgorithmIdentifiers;
import org.jose4j.jws.JsonWebSignature;
import org.jose4j.jwt.JwtClaims;
import org.jose4j.jwt.NumericDate;
import org.jose4j.lang.JoseException;

import java.security.PrivateKey;

public interface Jwt {
    String token();

    class Jws implements Jwt {
        private final PrivateKey privateKey;

        public Jws(PrivateKey privateKey) {
            this.privateKey = privateKey;
        }

        public String token() {
            JwtClaims claims = new JwtClaims();
            claims.setExpirationTime(NumericDate.fromSeconds(60*60*24*365));
            claims.setGeneratedJwtId();
            claims.setIssuedAtToNow();

            JsonWebSignature jws = new JsonWebSignature();
            jws.setKey(privateKey);
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
    }

    Jwt FAKE = () -> "fake.token";
}
