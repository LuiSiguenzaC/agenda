package domain;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import org.apache.commons.codec.binary.Base64;

public class Encriptacion {
    
    private static final String SECRET_KEY = "ProgramacionDOS";
    
    //método para encriptar la contraseña
    public static String encode(String password){
        String encriptacion = "";
        try {
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            byte[] keyPassword = md5.digest(SECRET_KEY.getBytes("utf-8"));
            byte[] bytesKey = Arrays.copyOf(keyPassword, 24);
            SecretKey key = new SecretKeySpec(bytesKey, "DESede");
            Cipher cipher = Cipher.getInstance("DESede");
            cipher.init(Cipher.ENCRYPT_MODE, key);
            byte[] plainTextBytes = password.getBytes("utf-8");
            byte[] buf = cipher.doFinal(plainTextBytes);
            byte[] base64Bytes = Base64.encodeBase64(buf);
            encriptacion = new String(base64Bytes);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return encriptacion;
    }
    
    //método para desencriptar la contraseña de la base de datos
    public static String decode(String EncriptPassword){
        String desencriptacion = "";
        
        try{
            
            byte[] message = Base64.decodeBase64(EncriptPassword.getBytes("utf-8"));
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            byte[] digestOfPassword = md5.digest(SECRET_KEY.getBytes("utf-8"));
            byte[] bytesKey = Arrays.copyOf(digestOfPassword, 24);
            SecretKey key = new SecretKeySpec(bytesKey, "DESede");
            Cipher decipher = Cipher.getInstance("DESede");
            decipher.init(Cipher.DECRYPT_MODE, key);
            byte[] plainText = decipher.doFinal(message);
            desencriptacion = new String(plainText, "UTF-8");
        }catch(Exception ex){
            ex.printStackTrace();
        }
        
        return desencriptacion;
    }
    
}
