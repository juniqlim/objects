package io.github.juniqlim.object.jwt;

import org.jose4j.jws.AlgorithmIdentifiers;
import org.jose4j.jws.JsonWebSignature;
import org.jose4j.jwt.JwtClaims;
import org.jose4j.jwt.NumericDate;
import org.jose4j.lang.JoseException;

import java.security.PrivateKey;
import java.util.Map;

public interface Jwt {
    String token();

    class Jws implements Jwt {
        private final PrivateKey privateKey;

        public Jws(PrivateKey privateKey) {
            this.privateKey = privateKey;
        }

        public String token() {
            return jwsString(claims());
        }

        public String token(Request request) {
            return jwsString(claims(request));
        }

        public String token(Map<String, Object> payload) {
            return jwsString(claims(payload));
        }

        private JwtClaims claims(Map<String, Object> payload) {
            JwtClaims claims = new JwtClaims();
            payload.forEach(claims::setClaim);
            return claims;
        }

        private static JwtClaims claims(Request request) {
            JwtClaims claims = new JwtClaims();
            claims.setIssuedAtToNow();
            claims.setExpirationTime(NumericDate.fromSeconds(request.expirationSeconds));
            request.payload.forEach(claims::setClaim);
            return claims;
        }

        private static JwtClaims claims() {
            JwtClaims claims = new JwtClaims();
            claims.setIssuedAtToNow();
            claims.setExpirationTime(NumericDate.fromSeconds(60*60*24*365));
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

        public static class Request {
            private final long expirationSeconds;
            private final Map<String, Object> payload;

            public Request(long expirationSeconds, Map<String, Object> payload) {
                this.expirationSeconds = expirationSeconds;
                this.payload = payload;
            }
        }
    }

    Jwt FAKE = () -> "fake.token";
}
