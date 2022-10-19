package io.github.juniqlim.object.jwt;

import org.junit.jupiter.api.Test;

import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

import static org.junit.jupiter.api.Assertions.assertTrue;

class VerifiedJwtTest {
    @Test
    void test() throws NoSuchAlgorithmException, InvalidKeySpecException {
        String stringPublicKey = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAh6ql6hhIxPChFZTXdDhVhjKhFF5BOTw9YwvoeP2CvtZq7atG+TFx+E6MucPVgns8RzRTQ+8dURtrYnR5KmzU4FGv/IN33oelbDd57kj5YDz0IjWUhYHgfVSXREv4oMBPKoz09E/3lOxtdC/WpRrYhoA7w+tudrw+Ah51CM4i1CQMXr1d44R1xyEsFXCl2tmfx9GEc25RIRgxVXhY0IzrKw7y6umG1gehhJgpEAtIFGKKhIkUJqjDpfw3kkHtcTzzKZDp6aZVtNJ5FW2pqURjhGu/I1KFd+9ARixdOR6s/E1Gk0Ak4TU6QOo41T5ZNbC66sbXl85vw1eePVmAkUtegQIDAQAB";
        String token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzUxMiJ9.eyJleHAiOjMxNTM2MDAwLCJqdGkiOiIyNHlSM3ZUaWJNQTRkUDVyOFRTYUR3IiwiaWF0IjoxNjY2MTg1NDUxfQ.diSDEaE9IZ90hmuGKZtmybZ-q_Nl-Kj8R-YZ0E1tsk2v8dh7LTusB7udWmVxypn-dOiUFp9G9PswAumLLXr2BODsQpxo1y3AZJKtko1LVXWKa7FRb1EuKmcQxy0qzBc71kva45T-zBqqaGOk4NQ7iaSjwPHPAn6JQ3Blp6jlzPGjBRjtpv0VzrLLmUN4s4YlBXu3lZMVYebXCU1Ml_sdeMimIt4CDGQPRjlexhWkDZHplraTWDE0eUMDsfw10ZEFcoeJfy7EZQ9dMtQ7H-3WhvJrPlW3Nz7egqRnDKFHhhDOBZiDmNrvp2Dde050wd3dthsVto5UjyCPZRP6xjFsVg";

        PublicKey publicKey = KeyFactory.getInstance("RSA").generatePublic(new X509EncodedKeySpec(Base64.getDecoder().decode(stringPublicKey)));
        assertTrue(new VerifiedJwt.VerifiedJws(publicKey, token).verifiable());
    }
}
