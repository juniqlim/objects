package io.github.juniqlim.object.jwt;

import org.jose4j.jwa.AlgorithmConstraints;
import org.jose4j.jwe.ContentEncryptionAlgorithmIdentifiers;
import org.jose4j.jwe.JsonWebEncryption;
import org.jose4j.jwe.KeyManagementAlgorithmIdentifiers;
import org.jose4j.lang.JoseException;

import java.security.PrivateKey;

public interface DecodedJwe {
    String plainText();

    class DecodedRsaJwe implements DecodedJwe {
        private final PrivateKey privateKey;
        private final String token;

        public DecodedRsaJwe(PrivateKey privateKey, String token) {
            this.privateKey = privateKey;
            this.token = token;
        }

        public String plainText() {
            try {
                return jwe().getPlaintextString();
            } catch (JoseException e) {
                throw new RuntimeException(e);
            }
        }

        private JsonWebEncryption jwe() {
            try {
                JsonWebEncryption jwe = new JsonWebEncryption();
                jwe.setAlgorithmConstraints(new AlgorithmConstraints(
                        AlgorithmConstraints.ConstraintType.PERMIT,
                        KeyManagementAlgorithmIdentifiers.RSA_OAEP_256)
                );
                jwe.setContentEncryptionAlgorithmConstraints(new AlgorithmConstraints(
                        AlgorithmConstraints.ConstraintType.PERMIT,
                        ContentEncryptionAlgorithmIdentifiers.AES_256_CBC_HMAC_SHA_512)
                );
                jwe.setCompactSerialization(token);
                jwe.setKey(privateKey);
                return jwe;
            } catch (JoseException e) {
                throw new RuntimeException(e);
            }
        }
    }

    DecodedJwe FAKE = () -> "plainText";
}