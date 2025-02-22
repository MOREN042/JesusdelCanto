/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package servidortcp;

import cantos.cifrarmensaje.CifrarMensaje;
import java.io.*;
import java.net.*;
import javax.crypto.SecretKey;

public class ServidorTCP {
    public static void main(String[] args) throws Exception {
        int puerto = 5000; // Puerto en el que escucha el servidor

        try (ServerSocket servidor = new ServerSocket(puerto)) {
            System.out.println("Servidor esperando conexiones en el puerto " + puerto + "...");

            while (true) {
                // Espera una conexión
                Socket socket = servidor.accept();
                System.out.println("Cliente conectado desde " + socket.getInetAddress());

                // Recibir mensaje
                BufferedReader entrada = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                String mensaje = entrada.readLine();
                System.out.println("Mensaje encriptado recibido: " + mensaje);
                
                //Desencripto y muestro el mensaje
                String StringKey="122333";
                CifrarMensaje cifrado = new CifrarMensaje();
                SecretKey clave=cifrado.generarClave(StringKey);
                String peticionCliente = cifrado.descifrar(mensaje, clave);
                
                //Aquí podríamos lanzar la clase de tipo thread que queremos que resuelva la petición del cliente, o filtrar las peticiones para lanzar distintas clases.
                
                    System.out.println("Mensaje desencriptado: "+peticionCliente);
                
                    // Encripto la respuesta
                    for (int i=0; i<10; i++){
                        String respuesta = "Respuesta nº "+i+" -- Mensaje recibido correctamente";
                        String respuestaCifrada= cifrado.cifrar(respuesta, clave);

                        // Envío la respuesta encriptada
                        PrintWriter salida = new PrintWriter(socket.getOutputStream(), true);
                        salida.println(respuestaCifrada);

                    }
                // Cerrar conexión con el cliente
                socket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}