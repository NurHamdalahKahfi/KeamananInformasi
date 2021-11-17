import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class passwordHashing {
    public static void main(String[] args) throws NoSuchAlgorithmException {
        System.out.println(doHashing("P@ssw0rd"));
    }

    private static String doHashing(String password) throws NoSuchAlgorithmException {
        MessageDigest messageDigest = MessageDigest.getInstance("MD5");
        messageDigest.update(password.getBytes());

        byte[] resultByteArray = messageDigest.digest();

        StringBuilder sb = new StringBuilder();

        for (byte b : resultByteArray){
            sb.append(String.format("%02x", b));
        }

        return sb.toString();
    }
}
