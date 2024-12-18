package it.hype.centrico.token.manager.domain.service.impl;

import it.hype.centrico.token.manager.domain.exception.CryptoUtilsException;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.codec.binary.Hex;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.GCMParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;

@Log4j2
@Service
public class CryptoService {

    private static final int TAG_LENGTH = 16;
    private static final int IV_LENGTH = 16;
    private static final int ITERATIONS = 251;
    private static final int KEY_LENGTH = 16;
    private static final int SALT_LENGTH = 16;

    public String encrypt(String key, String plainText) {
        try {
            return encryptSub(key, plainText);
        } catch (CryptoUtilsException e) {
            throw e;
        } catch (Exception e) {
            throw new CryptoUtilsException("Error during sso token encryption for key: " + key, e);
        }
    }

    private String encryptSub(String key, String plainText) throws Exception {
        EncAlgorithm encAlgorithm = new EncAlgorithm(key);
        byte[] encrypt = encAlgorithm.encrypt(plainText.getBytes());
        return hexEncode(encrypt);
    }

    private String hexEncode(byte[] input) {
        StringBuilder result = new StringBuilder();
        char[] digits = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
        for (byte b : input) {
            result.append(digits[(b & 0xf0) >> 4]);
            result.append(digits[b & 0x0f]);
        }
        return result.toString();
    }

    public String decrypt(String key, String encryptedText) {
        try {
            return decryptSub(key, encryptedText);
        } catch (CryptoUtilsException e) {
            throw e;
        } catch (Exception e) {
            throw new CryptoUtilsException("Error decrypting user key: " + key, e);
        }
    }

    private String decryptSub(String key, String encryptedText) throws Exception {
        EncAlgorithm encAlgorithm = new EncAlgorithm(key);
        byte[] decode = Hex.decodeHex(encryptedText.toCharArray());
        byte[] decrypt = encAlgorithm.decrypt(decode);
        return new String(decrypt);
    }

    static class EncAlgorithm {
        public static final String CIPHER_SUITE = "AES_128/GCM/NoPadding";
        private final char[] secret;

        EncAlgorithm(String secret) {
            if (StringUtils.isBlank(secret)) {
                throw new CryptoUtilsException("Empty secret");
            }
            this.secret = secret.toCharArray();
        }

        public byte[] encrypt(byte[] plain) throws Exception {
            byte[] iv = getRandomNonce(IV_LENGTH);
            byte[] salt = getRandomNonce(SALT_LENGTH);

            SecretKey key = generateKey(this.secret, salt);
            Cipher cipher = Cipher.getInstance(CIPHER_SUITE);
            cipher.init(Cipher.ENCRYPT_MODE, key, new GCMParameterSpec(TAG_LENGTH * 8, iv));
            byte[] encrypted = cipher.doFinal(plain);

            // Combine IV and encrypted part.
            byte[] encryptedIVSaltAndText = new byte[SALT_LENGTH + IV_LENGTH + encrypted.length];
            System.arraycopy(iv, 0, encryptedIVSaltAndText, 0, IV_LENGTH);
            System.arraycopy(encrypted, 0, encryptedIVSaltAndText, IV_LENGTH, encrypted.length);
            System.arraycopy(salt, 0, encryptedIVSaltAndText, encryptedIVSaltAndText.length - SALT_LENGTH, SALT_LENGTH);
            return encryptedIVSaltAndText;
        }

        public byte[] decrypt(byte[] cipher) throws Exception {
            byte[] iv = new byte[IV_LENGTH];
            byte[] salt = new byte[SALT_LENGTH];

            Cipher dcipher;
            System.arraycopy(cipher, 0, iv, 0, iv.length);
            System.arraycopy(cipher, cipher.length - SALT_LENGTH, salt, 0, SALT_LENGTH);

            // Extract encrypted part.
            int encryptedSize = cipher.length - IV_LENGTH - SALT_LENGTH;
            byte[] encryptedBytes = new byte[encryptedSize];
            System.arraycopy(cipher, IV_LENGTH, encryptedBytes, 0, encryptedSize);

            SecretKey key = generateKey(this.secret, salt);
            dcipher = Cipher.getInstance(CIPHER_SUITE);
            dcipher.init(Cipher.DECRYPT_MODE, key, new GCMParameterSpec(TAG_LENGTH * 8, iv));

            return dcipher.doFinal(encryptedBytes);
        }

        private byte[] getRandomNonce(int length) {
            byte[] nonce = new byte[length];
            new SecureRandom().nextBytes(nonce);
            return nonce;
        }

        private SecretKey generateKey(char[] secret, byte[] salt)
                throws NoSuchAlgorithmException, InvalidKeySpecException {

            SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA512");
            KeySpec spec = new PBEKeySpec(secret, salt, ITERATIONS, KEY_LENGTH * 8);
            return new SecretKeySpec(factory.generateSecret(spec).getEncoded(), "AES");
        }
    }
}
