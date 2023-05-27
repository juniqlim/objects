package io.github.juniqlim.object.jwt;

import org.jose4j.jwe.ContentEncryptionAlgorithmIdentifiers;
import org.jose4j.jwe.JsonWebEncryption;
import org.jose4j.jwe.KeyManagementAlgorithmIdentifiers;
import org.jose4j.lang.JoseException;

import java.security.PublicKey;

public interface Jwe {
    String token(String plainText);

    class RsaJwe implements Jwe {
        private final PublicKey publicKey;

        public RsaJwe(PublicKey publicKey) {
            this.publicKey = publicKey;
        }

        public String token(String plainText) {
            return jweString(plainText);
        }

        private String jweString(String text) {
            JsonWebEncryption senderJwe = new JsonWebEncryption();
            senderJwe.setPlaintext(text);
            senderJwe.setAlgorithmHeaderValue(KeyManagementAlgorithmIdentifiers.RSA_OAEP_256);
            senderJwe.setEncryptionMethodHeaderParameter(ContentEncryptionAlgorithmIdentifiers.AES_256_CBC_HMAC_SHA_512);
            senderJwe.setKey(publicKey);

            try {
                return senderJwe.getCompactSerialization();
            } catch (JoseException e) {
                throw new RuntimeException(e);
            }
        }
    }

    Jwe FAKE = plainText -> "fakeToken";
}