/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package trabajovoluntario;

import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

/**
 *
 * @author jesus
 */
 

public class Trabajovoluntario {
    
    static boolean  bancarrotaj1;
    static boolean bancarrotaj2;
    static int orden=1;
    static String[]nombres;
    JFrame ventana;
    jugador jugador1;
    jugador jugador2;
    NuevoTablero tablero;
    static double[][]casillasn;
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        bancarrotaj1=false;
        bancarrotaj2=false;
        
        
        String fichero1;
        String fichero2;
        
        int[] totalcasillas=new int[1];
        JFrame ventana = new JFrame("Chulilópolis");
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventana.setSize(1400, 960);
        
        PanelOpciones opciones=new PanelOpciones(ventana);
        opciones.setVisible(true);
        
        fichero1=opciones.Recogeravatar1();
        fichero2=opciones.Recogeravatar2();
        totalcasillas[0]=opciones.Recogertamano();
        String nombre1=opciones.RecogerJugador1();
        String nombre2=opciones.RecogerJugador2();
        nombres= new String[2];
        nombres[0]=opciones.RecogerJugador1();;
        nombres[1]=opciones.RecogerJugador2();
        
        NuevoTablero tablero;
        switch(totalcasillas[0]){
            case 8:
                tablero = new NuevoTablero (totalcasillas[0],7,2,3,12,31);
                break;
            case 16:
                tablero = new NuevoTablero (totalcasillas[0],8,14,3,12,31);
                break;
            case 32:
                tablero = new NuevoTablero (totalcasillas[0],16,25,3,12,31);
                break;
            default:
                tablero = new NuevoTablero (totalcasillas[0],16,25,10,12,31);
                break;
        }
        
        if (totalcasillas[0]==8){ventana.setSize(1040, 600);}
        if (totalcasillas[0]==16){ventana.setSize(1040, 800);}
        
        actualizacasillasn();
        tablero.setLayout(null);

        jugador jugador1=new jugador(nombres[0], 1, tablero, 25,40, totalcasillas[0], fichero1 );
        jugador jugador2=new jugador(nombres[1], 2, tablero, 500, 40, totalcasillas[0], fichero2);
       
        Dado nuevodado=new Dado(tablero, totalcasillas[0] );
       
       
        //Creo un botón invisible que está sobre el dado para que cuando se pulse ejecute el programa.
        JButton boton = new JButton("");
        
        boton.setOpaque(false);
        boton.setContentAreaFilled(false);
        boton.setBorderPainted(false);
        boton.setFocusPainted(false);
        
        if (totalcasillas[0]==8){boton.setBounds(400,150,200,200);}
        if (totalcasillas[0]==16){boton.setBounds(310,320,200,200);}
        if (totalcasillas[0]==32){boton.setBounds(600,350,200,200);}
        tablero.add(boton);
        
        // Creo un botón para guardar las partidas y lo posiciono según el tamaño del tablero.
        JButton botongrabar = new JButton("");
        ImageIcon iconograbar = new ImageIcon("imagenes/guardar100px.jpg");
        botongrabar.setIcon(iconograbar);
        if (totalcasillas[0]==32){botongrabar.setBounds(950,40,100,50);}
        if (totalcasillas[0]==16){botongrabar.setBounds(730,140,100,50);}
        if (totalcasillas[0]==8){botongrabar.setBounds(650,140,100,50);}
        tablero.add(botongrabar);
        // Creo un botón para cargar las partidas y lo posiciono según el tamaño del tablero
        JButton botoncargar = new JButton("");
        ImageIcon iconocargar = new ImageIcon("imagenes/cargar100px.jpg");
        botoncargar.setIcon(iconocargar);
        if (totalcasillas[0]==32){botoncargar.setBounds(1100,40,100,50);}
        if (totalcasillas[0]==16){botoncargar.setBounds(850,140,100,50);}
        if (totalcasillas[0]==8){botoncargar.setBounds(800,140,100,50);}
        
        tablero.add(botoncargar);
        
        ventana.add(tablero);
        
        //al hacer click en el botón creamos un objeto partida y llamamos al método guardar
        botongrabar.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                Partidas partida1=new Partidas();
               try{ 
                partida1.Guardar(jugador1,jugador2, tablero,orden);
                JOptionPane.showMessageDialog(null,"Partida grabada correctamente", "Grabar partida",JOptionPane.INFORMATION_MESSAGE);
               }
               catch(IOException a){
                   a.printStackTrace();
                   JOptionPane.showMessageDialog(null,"Error al grabar partida", "Grabar partida",JOptionPane.INFORMATION_MESSAGE);
               }
            
            }
        });
        
        botoncargar.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                //Creamos el objeto 
                Partidas partida2=new Partidas();
                //Reseteamos los objetos por si venimos de otra partida
                Resetear(ventana,jugador1,jugador2,tablero);
                //Cargamos los valores de la partida y los pasamos a cada clase
                
                //Recoogemos los datos necesarios para seguir el juego y posicionar los componentes según el tamaño del tablero.
                try {
                    orden=partida2.Cargar(jugador1,jugador2, tablero);

                    actualizacasillasn();
                    int tamano=tablero.getTamano();
                    totalcasillas[0]=tamano;
                    //posiciono de nuevo los botones

                    nuevodado.posicionDado(tamano);
                    if (tamano==8){ventana.setSize(1040, 600);}
                    if (tamano==16){ventana.setSize(1040, 800);}
                    if (tamano==32){ventana.setSize(1400, 960);}
                    if (tamano==32){botongrabar.setBounds(950,40,100,50);}
                    if (tamano==16){botongrabar.setBounds(730,140,100,50);}
                    if (tamano==8){botongrabar.setBounds(650,140,100,50);}
                    if (tamano==32){botoncargar.setBounds(1100,40,100,50);}
                    if (tamano==16){botoncargar.setBounds(850,140,100,50);}
                    if (tamano==8){botoncargar.setBounds(800,140,100,50);}
                    if (totalcasillas[0]==8){boton.setBounds(400,150,200,200);}
                    if (totalcasillas[0]==16){boton.setBounds(310,320,200,200);}
                    if (totalcasillas[0]==32){boton.setBounds(600,350,200,200);}
                    JOptionPane.showMessageDialog(null,"Partida cargada correctamente", "Cargar partida",JOptionPane.INFORMATION_MESSAGE); 
                }
                catch (IOException b){
            
                    b.printStackTrace();
                    JOptionPane.showMessageDialog(null,"Error al cargar la partida. Inténtelo de nuevo.", "Cargar partida",JOptionPane.INFORMATION_MESSAGE);
        }
            }
        });
        
        // Al hacer click en el botón del dado comenzamos a Jugar
        boton.addActionListener(new ActionListener() {
        
            
            @Override
            public void actionPerformed(ActionEvent e) {
                
                
                
                if (bancarrotaj1==false && bancarrotaj2==false){
                    
                    if (orden==1){

                        jugador1.ColorMarcador("azul", tablero);
                        jugador2.ColorMarcador("gris", tablero);
                        jugador1.ActualizaMarcador();
                        jugador2.ActualizaMarcador();
                        jugador1.ActualizarIcono();
                        jugador2.ActualizarIcono();
                        tablero.RecargaTablero();
                        ventana.repaint();
                        
                        jugador1.MoverFicha(tablero, casillasn, totalcasillas[0], nuevodado.TirarDado(), ventana, 1);
                        //jugador1.MoverFicha(tablero, casillasn, totalcasillas[0], 24, ventana);
                        jugador1.ComprobarCasilla(casillasn, jugador1.getCasillaactual(),1,tablero, jugador1, jugador2);
                        bancarrotaj1=jugador1.bancarrota();
                        
                        jugador1.ColorMarcador("gris", tablero);
                        jugador2.ColorMarcador("azul", tablero);
                    }
                }
                if (bancarrotaj1==false && bancarrotaj2==false){
                    if (orden==2){
                        System.out.println("casillasn="+casillasn+"casillaactual="+jugador2.getCasillaactual());
                        jugador1.ColorMarcador("gris", tablero);
                        jugador2.ColorMarcador("azul", tablero);
                        jugador1.ActualizaMarcador();
                        jugador2.ActualizaMarcador();
                        jugador1.ActualizarIcono();
                        jugador2.ActualizarIcono();
                        tablero.RecargaTablero();
                        ventana.repaint();
                        
                        jugador2.MoverFicha(tablero, casillasn, totalcasillas[0], nuevodado.TirarDado(), ventana, 2);
                        System.out.println("casillasn="+casillasn+"casillaactual="+jugador2.getCasillaactual());
                        jugador2.ComprobarCasilla(casillasn, jugador2.getCasillaactual(),2,tablero, jugador1, jugador2);
                        bancarrotaj2=jugador2.bancarrota();
                        
                        jugador1.ColorMarcador("azul", tablero);
                        jugador2.ColorMarcador("gris", tablero);
                        System.out.println(tablero.verCasillas(6,5));
                        
                    }
                }
                if (orden==1){orden=2;}
                else if (orden==2){orden=1;}
               
                if (bancarrotaj1==true){
                   JOptionPane.showMessageDialog(null,"Lo siento " + jugador1.getNombre()+", te has quedado sin dinero y has perdido la partida", "FIN DE JUEGO",JOptionPane.INFORMATION_MESSAGE);
                   ventana.dispose();
                   Resetear(ventana, jugador1, jugador2, tablero);
                   Trabajovoluntario.main(new String[] {});
                }
                 if (bancarrotaj2==true){
                   JOptionPane.showMessageDialog(null,"Lo siento " + jugador2.getNombre()+", te has quedado sin dinero y has perdido la partida", "FIN DE JUEGO",JOptionPane.INFORMATION_MESSAGE);
                   ventana.dispose();
                   Resetear(ventana, jugador1, jugador2, tablero);
                   Trabajovoluntario.main(new String[] {});
                 }  
            }
        });
        
        ventana.setVisible(true);
        
        
   }
   public static void Resetear (Frame ventana,jugador jugador1, jugador jugador2, NuevoTablero tablero){
        ventana=null;
        jugador1=null;
        jugador2=null;
        tablero=null;
        
        } 
   public static void actualizacasillasn(){
       casillasn= NuevoTablero.getCasillas();
   }
        
}
       
       
        
        
