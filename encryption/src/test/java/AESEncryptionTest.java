import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AESEncryptionTest {
    @Test
    void test() {
        AESEncryption aesEncryption = new AESEncryption("01234567890123456789012345678901");

        String encrypted = aesEncryption.encrypt("1");
        String decrypted = aesEncryption.decrypt(encrypted);

        assertEquals("1", decrypted);
    }
}