package io.github.juniqlim.object.jwt;

import org.jose4j.jws.AlgorithmIdentifiers;
import org.jose4j.jws.JsonWebSignature;
import org.jose4j.jwt.JwtClaims;
import org.jose4j.jwt.NumericDate;
import org.jose4j.lang.JoseException;

import java.security.PrivateKey;
import java.util.Map;

public interface Jws {
    String token();
    String token(Map<String, Object> payload);

    class RsaJws implements Jws {
        private final PrivateKey privateKey;

        public RsaJws(PrivateKey privateKey) {
            this.privateKey = privateKey;
        }

        public String token() {
            return jwsString(claims());
        }

        public String token(Map<String, Object> payload) {
            return jwsString(claims(payload));
        }

        private JwtClaims claims(Map<String, Object> payload) {
            JwtClaims claims = new JwtClaims();
            payload.forEach(claims::setClaim);
            return claims;
        }

        private static JwtClaims claims() {
            JwtClaims claims = new JwtClaims();
            claims.setIssuedAtToNow();
            claims.setExpirationTime(NumericDate.fromSeconds(60*60*24));
            return claims;
        }

        private String jwsString(JwtClaims claims) {
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
    Jws FAKE = new Jws() {
        @Override
        public String token() {
            return "FAKE";
        }

        @Override
        public String token(Map<String, Object> payload) {
            return "FAKE";
        }
    };
}
