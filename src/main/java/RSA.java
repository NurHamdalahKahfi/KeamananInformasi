import javax.crypto.Cipher;
import java.security.*;
import java.util.Base64;

public class RSA {
    private PrivateKey privateKey;
    private PublicKey publicKey;

    public RSA(){
        try {
            KeyPairGenerator generator = KeyPairGenerator.getInstance("RSA");
            generator.initialize(1024);
            KeyPair pair = generator.generateKeyPair();
            privateKey = pair.getPrivate();
            System.out.println(privateKey);
            publicKey = pair.getPublic();
            System.out.println(publicKey);

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

    public String encrypt(String message) throws Exception {
        byte[] messageToBytes = message.getBytes();
        Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);
        byte[] encryptedBytes = cipher.doFinal(messageToBytes);

        return encode(encryptedBytes);
    }

    public String encode(byte[] data){
       return Base64.getEncoder().encodeToString(data);
    }

    public byte[] decode(String data){
        return Base64.getDecoder().decode(data);
    }

    public String decrypt(String encryptedMessage)throws Exception{
        byte[] encryptedMessages = decode(encryptedMessage);
        Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
        cipher.init(Cipher.DECRYPT_MODE,privateKey);
        byte[] decryptedMessage = cipher.doFinal(encryptedMessages);

        return new String(decryptedMessage,"UTF-8");
    }

    public static void main(String[] args) {
        RSA rsa = new RSA();

        try {
            String encriptedMessage = rsa.encrypt("Nur Hamdalah Kahfi");
            String decryptedMessage = rsa.decrypt(encriptedMessage);

            System.err.println("Encrypted:\n"+encriptedMessage);
            System.err.println("Decrypted:\n"+decryptedMessage);
        } catch (Exception ignored) {
        }
    }
}
