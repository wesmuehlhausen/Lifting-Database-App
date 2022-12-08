package Helper;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.SecureRandom;
import java.util.Arrays;


//https://howtodoinjava.com/java/java-security/how-to-generate-secure-password-hash-md5-sha-pbkdf2-bcrypt-examples/
public class PasswordHelper {

    public static String hashPassword(String rawPassword, String salt){

        String hashedPassword = hashPassword(rawPassword);
        String saltedHashedPassword = hashPassword(hashedPassword + salt);

        return saltedHashedPassword;
    }

    private static String hashPassword(String password){
        String generatedPassword = "";

        try{
            // Create MessageDigest instance for MD5
            MessageDigest md = MessageDigest.getInstance("MD5");

            // Hash the password Get the hash's bytes
            byte[] bytes = md.digest(password.getBytes());

            //Password to string
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < bytes.length; i++)
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            generatedPassword = sb.toString();

        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
        return generatedPassword;
    }

    public static String getSalt()
    {
        String generatedSalt = "";
        try {
            // Always use a SecureRandom generator
            SecureRandom sr = SecureRandom.getInstance("SHA1PRNG", "SUN");
            // Create array for salt
            byte[] salt = new byte[16];

            // Get a random salt
            sr.nextBytes(salt);

            // Turn salt into a string
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < salt.length; i++)
                sb.append(Integer.toString((salt[i] & 0xff) + 0x100, 16).substring(1));
            generatedSalt = sb.toString();
        } catch (NoSuchAlgorithmException | NoSuchProviderException e) {
            throw new RuntimeException(e);
        }
        return generatedSalt;
    }
}
