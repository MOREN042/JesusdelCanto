/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package cantos.cifrarmensaje;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.Base64;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

/**
 *
 * @author jesus
 */
public class CifrarMensaje {

    //private String mensaje;
    private SecretKey clave;
    
     
    //Genero una clave AES de 256 bits
    
    public void generarClave() throws Exception{
    
        KeyGenerator generadorClave = KeyGenerator.getInstance("AES");
        generadorClave.init(256);
        clave=generadorClave.generateKey();
        
    }
    // Genero una clave AES de 256 a partir de un String con SHA-256
    public SecretKey generarClave(String claveString) throws Exception{
        
        MessageDigest sha = MessageDigest.getInstance("SHA-256");
        byte[] claveSha = sha.digest(claveString.getBytes(StandardCharsets.UTF_8));
        clave= new SecretKeySpec(claveSha, "AES");
        return clave;
        
    }
    
    // Getter de la clave
    public SecretKey getClave(){
        return clave;
    }
    
    // Getter de la clave en formato String
    public String getClaveString(){
        
        String claveString = String.valueOf(clave);
        return claveString;
    }
    
    //Ciframos mensaje
    public String cifrar (String mensaje, SecretKey clave) throws Exception{
                 
        Cipher cifrado= Cipher.getInstance("AES");
        cifrado.init(Cipher.ENCRYPT_MODE, clave);
        byte[] mensajeEncriptado= cifrado.doFinal(mensaje.getBytes());
        
        //Convertimos a Base64 t devolvemos el mensaje cifrado
        return Base64.getEncoder().encodeToString(mensajeEncriptado);
        
    }
    //Metodo para cifrar sin indicarle la clave
    public String cifrar (String mensaje) throws Exception{
        
        generarClave();
        clave= getClave();
        
        Cipher cifrado= Cipher.getInstance("AES");
        cifrado.init(Cipher.ENCRYPT_MODE, clave);
        byte[] mensajeEncriptado= cifrado.doFinal(mensaje.getBytes());
        
        //Convertimos a Base64 t devolvemos el mensaje cifrado
        return Base64.getEncoder().encodeToString(mensajeEncriptado);
        
    }
    
    //método para desencriptar mensaje
    
    public String descifrar (String mensaje, SecretKey clave) throws Exception {
        
        Cipher descifrado= Cipher.getInstance("AES");
        descifrado.init(Cipher.DECRYPT_MODE, clave);
        byte[] mensajeBytes=Base64.getDecoder().decode(mensaje);
        byte[] mensajeDesencriptado= descifrado.doFinal(mensajeBytes);
        return new String(mensajeDesencriptado,StandardCharsets.UTF_8);
    }
    
    //método para desencriptar mensaje con un String
    
    public String descifrarKeyString (String mensaje, String claveString) throws Exception {
        
        clave=generarClave(claveString);
        Cipher descifrado= Cipher.getInstance("AES");
        descifrado.init(Cipher.DECRYPT_MODE, clave);
        byte[] mensajeBytes=Base64.getDecoder().decode(mensaje);
        byte[] mensajeDesencriptado= descifrado.doFinal(mensajeBytes);
        return new String(mensajeDesencriptado,StandardCharsets.UTF_8);
    }
}
