/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cantos;
import java.io.*;
import java.net.*;
import cantos.cifrarmensaje.CifrarMensaje;
import javax.crypto.SecretKey;

/**
 *
 * @author jesus
 */
public class conexionTcpCliente {
    
        
    
    public void envioCifrado (String mensaje, String ipServidor, int puertoServidor, String stringKey) throws IOException, Exception {
        
        String respuesta;
        
        CifrarMensaje cifrado = new CifrarMensaje();
        SecretKey clave = cifrado.generarClave(stringKey);
               
        String mensajeCifrado= cifrado.cifrar(mensaje, clave);
        
        Socket socket = new Socket (ipServidor, puertoServidor);
        PrintWriter salida = new PrintWriter(socket.getOutputStream(),true);
        salida.println(mensajeCifrado);
        
        BufferedReader entrada = new BufferedReader (new InputStreamReader(socket.getInputStream()));
        
        String respuestaCodificada = entrada.readLine();
        respuestaCodificada=respuestaCodificada.trim();
        
        if (respuestaCodificada!=null){
            String respuesta1 = cifrado.descifrar(respuestaCodificada, clave);
             respuesta = respuesta1.trim();
        }
        else {
             respuesta="no hay respuesta";
            }
        //System.out.println("La respuesta cifrada es: "+ respuestaCodificada);
        System.out.println("La respuesta descifrada es: "+respuesta);
        
    }
}
