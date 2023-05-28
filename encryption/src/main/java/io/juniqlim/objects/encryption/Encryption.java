package io.juniqlim.objects.encryption;

public interface Encryption {
    String encrypt(String text);
    String decrypt(String encryptedText);
}
