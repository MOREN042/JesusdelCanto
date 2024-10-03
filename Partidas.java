/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package trabajovoluntario;

import java.io.File;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import javax.swing.*;
import javax.swing.filechooser.*;
import javax.swing.JFrame;

/**
 *
 * @author jesus
 */
public class Partidas {
    private int tamano;
    private int carcel;
    private int iracarcel;
    private int tarjeta1;
    private int tarjeta2;
    private int tarjeta3;
    private double[][] casillas= new double[32][6];
    private int dinero1;
    private String nombre1;
    private int avatarficha1;
    private int posicionactual1;
    private String fichero1;
    private int dinero2;
    private String nombre2;
    private int avatarficha2;
    private int posicionactual2;
    private String fichero2;
    
    private String rutaArchivo="";
    
    public Partidas() {
        
        //Creo la ventana para elegir el fichero y su ruta
        String rutaactual=System.getProperty("user.dir")+"/saves";
       
        JFileChooser elegirfichero=new JFileChooser(rutaactual);
        FileNameExtensionFilter filtroextension=new FileNameExtensionFilter("Archivos .sav", "sav");
        elegirfichero.setFileFilter(filtroextension);
        int returnValue = elegirfichero.showOpenDialog(null);
        
        if (returnValue== JFileChooser.APPROVE_OPTION){
            
            rutaArchivo=elegirfichero.getSelectedFile().getPath();
            //Compruebo que el fichero lleve la extension .sav y si no es así se la añado
            String extension=new String();
            extension=rutaArchivo.substring(rutaArchivo.length()-4);
            String extcorrecta=".sav";
             
            if (extension.equals(extcorrecta)){}else{
                rutaArchivo=rutaArchivo+".sav";
            }
        }
        
    }
    
    public void Guardar (jugador jugador1, jugador jugador2, NuevoTablero tablero, int orden) throws IOException{
        
       
        this.tamano=tablero.getTamano();
        this.carcel=tablero.getCarcel();
        this.iracarcel=tablero.getIracarcel();
        this.tarjeta1=tablero.getTarjeta1();
        this.tarjeta2=tablero.getTarjeta2();
        this.tarjeta3=tablero.getTarjeta3();
        this.casillas=tablero.getCasillas();
        
        this.dinero1=jugador1.getDinero();
        this.nombre1=jugador1.getNombre();
        this.avatarficha1=jugador1.getavatarficha();
        this.posicionactual1=jugador1.getCasillaactual();
        this.fichero1=jugador1.getFichero();
        
        this.dinero2=jugador2.getDinero();
        this.nombre2=jugador2.getNombre();
        this.avatarficha2=jugador2.getavatarficha();
        this.posicionactual2=jugador2.getCasillaactual();
        this.fichero2=jugador2.getFichero();
        
        
        
        String contenido = new String(""+tamano+"·"+carcel+"·"+iracarcel+"·"+tarjeta1+"·"+tarjeta2+"·"+tarjeta3+"·");
        
        for (int i=0;i<32;i++){
            for (int j=0;j<=5;j++){
                contenido=contenido+(casillas[i][j]+"·");
            }
        }
        
        contenido=contenido+(""+dinero1+"·"+nombre1+"·"+avatarficha1+"·"+fichero1+"·"+posicionactual1+"·"+dinero2+"·"+nombre2+"·"+avatarficha2+"·"+fichero2+"·"+posicionactual2+"·"+orden);
        
        
            File archivo = new File(rutaArchivo);
            FileWriter grabacion = new FileWriter(archivo);
            grabacion.write(contenido);
            grabacion.close();
                                   
        
        
        
    }
    public int Cargar (jugador jugador1, jugador jugador2, NuevoTablero tablero) throws IOException{
        
        //Abro el archivo y copio el contenido en un String llamado contenido
        
            File archivo = new File(rutaArchivo);
            FileReader lectura = new FileReader(archivo);
            BufferedReader leido=new BufferedReader(lectura);
            String contenidoleido= new String();
            contenidoleido=leido.readLine();
            
            //Separo el String en partes con el caracter especial elegido y me da un array llamado variables con cada una de las partes.
               String[] variables = contenidoleido.split("·");

            // ahora asigno el valor dentro del array a las variables
           tamano=Integer.parseInt(variables[0]);
           carcel=Integer.parseInt(variables[1]);
           iracarcel=Integer.parseInt(variables[2]);
           tarjeta1=Integer.parseInt(variables[3]);
           tarjeta2=Integer.parseInt(variables[4]);
           tarjeta3=Integer.parseInt(variables[5]);


           tablero.setTamano(Integer.parseInt(variables[0]));
           tablero.setCarcel(Integer.parseInt(variables[1]));
           tablero.setIracarcel(Integer.parseInt(variables[2]));
           tablero.setTarjeta1(Integer.parseInt(variables[3]));
           tablero.setTarjeta2(Integer.parseInt(variables[4]));
           tablero.setTarjeta3(Integer.parseInt(variables[5]));

           //para poder asignar los valores al array casillas de la clase NuevoTablero me genero un array local que tambien se llama casillas donde introduzco la informacion.
           int k=6;

           for (int i=0;i<tamano;i++){
               for (int j=0;j<6;j++){
                  // borro todos los nombres de propiedades del tablero y los vuelvo a escribir según los datos del archivo cargado.
                   if (j==5){
                           String nombre=new String();
                           jugador1.borrarImpresionNombre(i,tablero);
                           jugador2.borrarImpresionNombre(i,tablero);
                           // si la casilla pertenece al jugador 1
                           if (Double.parseDouble(variables[k])==1){
                               nombre=variables[199];
                           }
                           // si la casilla pertenece al jugador 2
                           else if (Double.parseDouble(variables[k])==2) {
                               nombre=variables[204];
                           }
                           jugador1.setImpresionNombre(i,nombre, tablero, (int)casillas[i][1], (int)casillas[i][2],Double.parseDouble(variables[k]));
                           jugador2.setImpresionNombre(i,nombre, tablero, (int)casillas[i][1], (int)casillas[i][2],Double.parseDouble(variables[k]));



                   }
                   casillas[i][j]=(Double.parseDouble(variables[k]));
                   k++;

               }

           }
           //ahora asigno el array local casillas al array de la clase NuevoTablero a través de su método set.
           tablero.setCasillas(casillas);
           //continuo con el resto de variables
           jugador1.setDinero(Integer.parseInt(variables[198]));
           jugador1.setNombre(variables[199]);
           jugador1.setavatarficha(Integer.parseInt(variables[200]));
           jugador1.setFichero(variables[201]);
           jugador1.setFicheroIcono(variables[201]);
           jugador1.setCasillaactual(Integer.parseInt(variables[202]));

           int casillaactual1=Integer.parseInt(variables[202]);
           //muevo la ficha 1 a la nueva posición de la partida guardada
           int x= (int)casillas [casillaactual1-1][1];
           int y=(int)casillas[casillaactual1-1][2];
           jugador1.MoverIcono(x,y);

           jugador2.setDinero(Integer.parseInt(variables[203]));
           jugador2.setNombre(variables[204]);
           jugador2.setavatarficha(Integer.parseInt(variables[205]));
           jugador2.setFichero(variables[206]);
           jugador2.setFicheroIcono(variables[206]);
           jugador2.setCasillaactual(Integer.parseInt(variables[207]));

           int casillaactual2=Integer.parseInt(variables[207]);
           //muevo la ficha 2 a la nueva posición de la partida guardada
           int x2= (int)casillas [casillaactual2-1][1];
           int y2=(int)casillas[casillaactual2-1][2];
           jugador2.MoverIcono(x2,y2);

           int orden1= (Integer.parseInt(variables[208]));

           tablero.RecargaTablero();
           jugador1.ActualizarIcono();
           jugador1.ActualizaMarcador();
           jugador2.ActualizarIcono();
           jugador2.ActualizaMarcador();
           
           lectura.close();
           leido.close();

           
           
        
        
        return orden1;
        
        
    }

    public int RecogerTamano (){
        return tamano;
    }
}
