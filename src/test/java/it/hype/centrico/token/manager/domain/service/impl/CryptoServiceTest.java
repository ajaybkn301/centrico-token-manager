//package it.hype.centrico.token.manager.domain.service.impl;
//
//import it.hype.centrico.token.manager.domain.exception.CryptoUtilsException;
//import org.junit.jupiter.api.Test;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//class CryptoServiceTest {
//
//    @Test
//    void test() {
//        CryptoService service = new CryptoService();
//
//        String encrypted = service.encrypt("key", "content");
//        String decrypted = service.decrypt("key", encrypted);
//        assertEquals("content", decrypted);
//    }
//
//    @Test
//    void testErrorEncrypting() {
//        CryptoService service = new CryptoService();
//
//        CryptoUtilsException ex = assertThrows(CryptoUtilsException.class,
//                () -> service.encrypt("key", null));
//        System.out.println(ex.getMessage());
//        assertTrue(ex.getMessage().startsWith("Error during sso token encryption for key: "));
//    }
//
//    @Test
//    void testErrorDecrypting() {
//        CryptoService service = new CryptoService();
//
//        CryptoUtilsException ex = assertThrows(CryptoUtilsException.class,
//                () -> service.decrypt("key", null));
//        System.out.println(ex.getMessage());
//        assertTrue(ex.getMessage().startsWith("Error decrypting user key: "));
//    }
//
//    @Test
//    void testErrorEncryptingWithEmptyKey() {
//        CryptoService service = new CryptoService();
//
//        CryptoUtilsException ex = assertThrows(CryptoUtilsException.class,
//                () -> service.encrypt("", "content"));
//
//        assertEquals("Empty secret", ex.getMessage());
//    }
//
//    @Test
//    void testErrorDecryptingWithEmptyKey() {
//        CryptoService service = new CryptoService();
//
//        CryptoUtilsException ex = assertThrows(CryptoUtilsException.class,
//                () -> service.decrypt("", "content"));
//
//        assertEquals("Empty secret", ex.getMessage());
//    }
//}
