/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package trabajovoluntario;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.util.Random;

/**
 * Esta clase representa al jugador
 * @version 1.0
 * @author Jesús del Canto Hidalgo
 */
public class jugador {
    private JLabel icono;
    private int dinero;
    private String nombre;
    public int posicionactual;
    private int avatarficha;
    public JLabel marcador;
    ImageIcon imagen;
    String directorioactual= (System.getProperty("user.dir")+"/iconos/");
    ImageIcon imagenavatar;
    int opcion;
    public JLabel[] propietario=new JLabel[32];
    String nombrejugador;
    String fichero;
    
    //Constructor de la clase que inicializa variables y genera una ficha y un marcador para el objeto jugador.
    public jugador(String nnombre, int navatarficha, NuevoTablero tablero,int posx, int posy, int tamano, String nfichero)
    {
        nombre=nnombre;
        avatarficha=navatarficha;
        dinero = 1000;
        posicionactual=1;
        fichero=nfichero;
        
        NuevaFicha(tablero,avatarficha, tamano, fichero);
        NuevoMarcador(tablero, posx,posy);
        
        
    }
    /** @return los siguientes metodos devuelven los valores de dinero, nombre , posicionactual y avatarficha respectivamente */
    public int getDinero() {
        return dinero;
    }
    public String getNombre() {
        return nombre;
    }
    public int getCasillaactual() {
        return posicionactual;
    }
    public int getavatarficha() {
        return avatarficha;
    }
    public String getFichero() {
        return fichero;
    }
    
     /** @param ndinero asigna el valor a la variable dinero*/
    public void setDinero(int ndinero) {
        dinero = ndinero;
    }
    /** @param nnombre asigna el valor a la variable dinero*/
    public void setNombre(String nnombre) {
        nombre = nnombre;
    }
    /** @param nposicionactual asigna el valor a la variable posicionactual*/
    public void setCasillaactual(int nposicionactual) {
        posicionactual = nposicionactual;
    }
    /** @param navatarficha asigna el valor a la variable avatarficha*/
    public void setavatarficha(int navatarficha) {
        avatarficha = navatarficha;
    }
    public void setFichero(String nfichero) {
        
        fichero = nfichero;
    }
    
        
    /**
     * El siguiente método comprueba si el jugador está en bancarrota
     * @return 
     */
    public boolean bancarrota(){
        boolean bancarrota=false;
        if (dinero<0){
            bancarrota=true;
        }
        return bancarrota;     
        
    }
    
    /**
     * @param suma nos dice la cantidad a sumar a dinero en el metodo
     */
    
    public void sumar(int suma){
        dinero=dinero+suma;
        ActualizaMarcador();
        }
    
    /**
     * @param resta nos dice la cantidad a restar a dinero en el metodo
     */
    
    public void restar(int resta){
        dinero=dinero-resta;
        ActualizaMarcador();
        
        }
    
    //Método para crear una ficha nueva con la imagen elegida
    public void NuevaFicha (NuevoTablero tablero,int avatar, int tamano, String archivo){
       
        if (avatar==1){

            imagenavatar =new ImageIcon (directorioactual + archivo + ".png");


        icono = new JLabel (imagenavatar);
        tablero.add(icono);
             if (tamano==16){icono.setBounds(170, 100, 50, 51);}
             else if(tamano==8 || tamano==32){
             icono.setBounds(10, 100, 50, 51);
             }
        }
        if (avatar==2){
        imagenavatar =new ImageIcon (directorioactual + archivo+ ".png");

        icono = new JLabel (imagenavatar);
        tablero.add(icono);
            if (tamano==16){icono.setBounds(220, 100, 50, 51);}
            else{

            icono.setBounds(60, 100, 50, 51);
            }
        }
        
    }
    
    public void setFicheroIcono(String fichero){
        imagenavatar= new ImageIcon (directorioactual+fichero+".png");
        icono.setIcon(imagenavatar);
        ActualizarIcono();
        
    }
    //Método para repintar el icono
    public void ActualizarIcono(){
        icono.repaint();
        
    }
    
    //Método para mover la posición del icono
    public void MoverIcono(int x, int y){
        
        icono.setLocation(x, y);
        icono.repaint();
    }
    
    //Método que creea la etiqueta de marcador
    public void NuevoMarcador (NuevoTablero tablero, int posx, int posy){
       
       marcador = new JLabel (nombre +": " + dinero+" monedas");
       Font tipofuente = new Font("Arial", Font.BOLD, 28);
       marcador.setFont(tipofuente);
       tablero.add(marcador);
       marcador.setBounds(posx, posy, 400, 50);
        
    }
    
    //Método para actualizar el contenido del marcador
    public void ActualizaMarcador (){
        marcador.setText(nombre +": " + dinero+" monedas");
        
    }
    
    // Creamos un método para establecer el color del fondo del marcador
    public void ColorMarcador (String ncolor, NuevoTablero tablero){
        SwingUtilities.invokeLater(() -> { 
        switch (ncolor) {
        case "gris":
            marcador.setBackground(Color.LIGHT_GRAY);
            break;
        case "azul":
            marcador.setBackground(Color.CYAN);
            break;
        }
        marcador.setOpaque(true);
        marcador.repaint();
        tablero.repaint();
        });
    }
    
    //Creamos el método MoverFicha, el cuál hace que se mueva sobre el tablero la imagen. Le pasamos como parámetro el  el objeto tablero(JPanel), el array con los datos de las casillas, el tamaño, cuantas casillas vamos a vanzar y el objeto JFrame para actualizar.
    
    public void MoverFicha(NuevoTablero tablero, double[][]casillas,int tamano, int movimientos, JFrame ventana, int player){
        
    // Sumamos los movimientos a la posición actual y según el tamaño del tablero vemos si sobrepasa los límites para que vuelva a empezar en la casilla 1.
        
        int casilladestino=posicionactual+movimientos;
        switch (tamano){
        
            case 8:
                if (casilladestino>8){
                casilladestino=casilladestino-8;
                }
                break;
            
            case 16:
                if (casilladestino>16){
                casilladestino=casilladestino-16;
                }
                
                break;
            case 32:
                if (casilladestino>32){
                    casilladestino=casilladestino-32;
                }
            break;
        
        }
            //Declaro variables de apoyo para calcular la posición de las casillas especiales
            double o;
            double p;
            double casilla;
            int tipocasilla;
            int carcel=-1;
            int iracarcel=-1;
            int tarjeta1=-1;
            int tarjeta2=-1;
            int tarjeta3=-1;
            
            //Localizo la posición en el tablero de las casillas especiales carcel, ir a carcel y las tarjetas, para efectuar las acciones específicas de cada una.
            
            for (int z=0;z<tamano;z++){
                
                casilla=casillas[z][0];
                tipocasilla=(int)Math.round(casilla);
                
                if (tipocasilla==1){
                    carcel=z+1;
                }
                else if(tipocasilla==2){
                    iracarcel=z+1;
                    
                }
                else if(tipocasilla==3){
                    if (tarjeta1==-1){
                        tarjeta1=z+1;}
                    else if (tarjeta2==-1){
                        tarjeta2=z+1;}
                    else {
                        tarjeta3=z+1;
                                
                    }
                }
            }
            // si la posición en la que está es la carcel debe sacar un 5 para poder moverse
            if (posicionactual==carcel && movimientos!=5){
                
                JOptionPane.showMessageDialog(null,"¡Lo siento " + nombre +" ! Para salir de la cárcel debes sacar un 5", "Carcel",JOptionPane.INFORMATION_MESSAGE);
                casilladestino=carcel;
                
            }
                
            // Como necesito hacer un swing timer y ahí no me deja utilizar variables que no sean finales uso un array para guardar las variables que necesito.
                
            int[]posiciones=new int[6];
            posiciones[0]=posicionactual;//se va incrementando conforme paso por las casillas
            posiciones[1]=tamano; //la uso para saber el tamaño total del tablero
            posiciones[2]=casilladestino;
            posiciones[3]=posicionactual; // la  uso para saber la casilla original de la que parto
            posiciones[4]=carcel;
            posiciones[5]=iracarcel;
            int velocidadmovimiento=200;
            
            //Creo el timer 
            
            Timer timer = new Timer(velocidadmovimiento, e -> {

                //Declaro k para que tenga el valor de posición actual. Recojo del array los valores de posición de la siguiente casilla
                int k=posiciones[0];

                double m=casillas[k-1][1];
                double n=casillas[k-1][2];
                int x=(int)Math.round(m);
                int y=(int)Math.round(n);

                // Actualizo las coordenadas del icono con la posición de la casilla siguiente
                   if (player==2){x=x+50;}
                    icono.setBounds(x,y,51,51);
                    tablero.RecargaTablero();
                    ventana.repaint();
                
                
                //  Si pasamos por la posición 1 pero no era nuestra casilla de partida en el movimiento (para evitar duplicidad) sumamos 100 monedas por paso de meta.
                if (posiciones[0]==1 && posiciones[3] != 1){
                    sumar(100);
                     ActualizaMarcador();
                    JOptionPane.showMessageDialog(null,"¡Felicidades " + nombre +" !Pasas por meta y te regala la banca 100 monedas", "Banca",JOptionPane.INFORMATION_MESSAGE);

                }
                //Si hemos llegado a la casilla de destino final salgo del timer
                if (posiciones[0]==posiciones[2]){
                    //Si hemos caido en la casilla ir a carcel muevo la ficha a la carcel
                    if (posiciones[2] == posiciones[5]){
                        
                        m=casillas[posiciones[4]-1][1];
                        n=casillas[posiciones[4]-1][2];
                        x=(int)Math.round(m);
                        y=(int)Math.round(n);
                        JOptionPane.showMessageDialog(null,"¡Ups " + nombre +"! Ibas muy rápido... Esta vez duermes entre rejas...", "Banca",JOptionPane.INFORMATION_MESSAGE);
                        if (player==2){x=x+50;} 
                        MoverIcono(x,y);
                        
                        ventana.repaint();
                        tablero.repaint();
                        icono.repaint();
                        
                    }
                    if (posiciones[2] == posiciones[4] && posiciones[3] != posiciones[4]){
                        
                        JOptionPane.showMessageDialog(null,nombre +", descargar software pirata es delito... Una temporada entre rejas te vendrá bien", "Banca",JOptionPane.INFORMATION_MESSAGE);
                        
                    }
                    ((Timer) e.getSource()).stop();
                }
                // aumento la posición en 1.
                posiciones[0]++;
                //Si la posición actual sobrepasa el tamaño máximo del tablero vuelvo a la casilla 1
                if (posiciones[0]>posiciones[1]){

                    posiciones[0]=1;

                    }
                
                ventana.repaint();
                tablero.repaint();
                icono.repaint();
                
            // Aquí termina el timer
            }); 
            // Comenzamos el timer
            timer.start();
                
            
            // Una vez que la ficha se ha movido actualizamos la posición en el objeto del jugador.
            if (casilladestino==iracarcel){
                posicionactual=carcel;
            } 
            else {        
                posicionactual=casilladestino;
            }
                        
            // Comprobamos que la nueva posición sea de casillas especiales y ejecutamos las acciones oportunas
            
            // Si la casilla final coincide con alguna tarjeta especial ejecutamos el método de tarjeta especial
            if (posicionactual==tarjeta1 || posicionactual==tarjeta2 || posicionactual==tarjeta3){
                
                TarjetaEspecial(tablero);
            }
    }
   
      
    
    // El método tarjeta especial elige una opcion aleatoria entre 6, sumando o restando la cantidad y mostrando un mensaje.
    public void TarjetaEspecial(NuevoTablero tablero){
        
        Random aleatorio = new Random();
        
        int numeroAleatorio = aleatorio.nextInt(10)+1;
        
        switch (numeroAleatorio){
        
        case 1:
            JOptionPane.showMessageDialog(null,"Miras hacia el suelo y encuentras un billete.Has ganado 100 monedas", "Tarjeta de la Suerte",JOptionPane.INFORMATION_MESSAGE);
            sumar(100);
            break;
        case 2:
            JOptionPane.showMessageDialog(null,"Se te cae la cartera y pierdes 50 monedas", "Tarjeta de la Suerte",JOptionPane.INFORMATION_MESSAGE);
            restar(50);
            break;
        case 3:
            JOptionPane.showMessageDialog(null,"Tu tio Gilito ha muerto y heredas 200 monedas", "Tarjeta de la Suerte",JOptionPane.INFORMATION_MESSAGE);
            sumar(200);
            break;
        case 4:
            JOptionPane.showMessageDialog(null,"¿Por qué engañas a Hacienda? Te multan con 250 monedas", "Tarjeta de la Suerte",JOptionPane.INFORMATION_MESSAGE);
            restar(250);
            break;
        case 5:
            JOptionPane.showMessageDialog(null,"Por fin el Euromillones se fija en tí. Ganas 500 monedas", "Tarjeta de la Suerte",JOptionPane.INFORMATION_MESSAGE);
            sumar(500);
            break;
        case 6:
            JOptionPane.showMessageDialog(null,"¿Bitcoins? Te han estafado y has perdido 450 monedas", "Tarjeta de la Suerte",JOptionPane.INFORMATION_MESSAGE);
            restar(450);
            break;
        case 7:
            JOptionPane.showMessageDialog(null,"Tu mejor amig@ se ha dejado la cartera en casa... Pierdes 150 monedas en invitarlo a cenar.", "Tarjeta de la Suerte",JOptionPane.INFORMATION_MESSAGE);
            restar(150);
            break;
        case 8:
            JOptionPane.showMessageDialog(null,"Tu abuela te ve demasiado delgad@. Te da 200 monedas para que compres comida en condiciones.", "Tarjeta de la Suerte",JOptionPane.INFORMATION_MESSAGE);
            sumar(200);
            break;
        case 9:
            JOptionPane.showMessageDialog(null,"Upsss... Has dejado el grifo abierto... Te llega la factura del agua por valor de 300 monedas", "Tarjeta de la Suerte",JOptionPane.INFORMATION_MESSAGE);
            restar(300);
            break;
        case 10:
            JOptionPane.showMessageDialog(null,"Ganas el campeonato del universo de Counter Strike. Ganas 350 monedas y una pegatina.", "Tarjeta de la Suerte",JOptionPane.INFORMATION_MESSAGE);
            sumar(350);
            break;
        }
        tablero.repaint();
        tablero.RecargaTablero();
        ActualizaMarcador();
        
    }
    //El siguiente método comprueba el estado de la casilla (Si no está comprada llama a la opcion de comprar, si ya tiene dueño comprueba si es del contrario y llama a pagar alquiler).
    
    public void  ComprobarCasilla (double[][] casillasn,int casilla, int jugadorcomprueba, NuevoTablero tablero,jugador jugador1, jugador jugador2){
        
        
        if (casillasn[casilla-1][5]==9){
            
            
            if (casillasn[casilla-1][3]<=dinero){  
                int precioalquiler=(int)casillasn[casilla-1][3];
                opcion = JOptionPane.showConfirmDialog(null, nombre + "¿Deseas comprar esta casilla por " + precioalquiler + " monedas", "Compra de Casilla", JOptionPane.YES_NO_OPTION);
                if (opcion == JOptionPane.YES_OPTION) {
                    ComprarCasilla(casilla,jugadorcomprueba, casillasn, tablero, jugador1, jugador2);
                }
            }
        }

        else if(casillasn[casilla-1][5]==jugadorcomprueba){

        }
        else if (casillasn[casilla-1][5]!=jugadorcomprueba && casillasn[casilla-1][5]!=0){

            PagarAlquiler(casilla,jugadorcomprueba, casillasn, tablero,  jugador1,  jugador2);
        }
    }
    
    // El siguiente método actualiza el comprador en el array de casillas y muestra el nombre del propietario en la casilla.
    public void ComprarCasilla(int casilla, int jugador, double[][] casillasn, NuevoTablero tablero, jugador jugador1, jugador jugador2){
         
        int coste=(int)casillasn[casilla-1][3];
        
        
        if (jugador==1){
            
            nombrejugador=jugador1.getNombre();
            jugador1.restar(coste);
            
        }
        else if (jugador==2){
            
            nombrejugador=jugador2.getNombre();
            jugador2.restar(coste);
            
        }    
        
        casillasn[casilla-1][5]=jugador;
        
        int x=(int)casillasn[casilla-1][1];
        int y=(int)casillasn[casilla-1][2];
        
               
        propietario[casilla-1] = new JLabel();
        propietario[casilla-1].setText(nombrejugador);
        propietario[casilla-1].setHorizontalAlignment(SwingConstants.CENTER);
        tablero.add(propietario[casilla-1],4);
        propietario[casilla-1].setBounds(x, y+90, 100, 30);
        
    }
    public void setImpresionNombre(int numero, String texto, NuevoTablero tablero, int x, int y, Double comprador){
        propietario[numero] = new JLabel();
        if (comprador==0){
            propietario[numero].setText("");
            propietario[numero].setHorizontalAlignment(SwingConstants.CENTER);
        
        tablero.add(propietario[numero],4);
        propietario[numero].setBounds(x, y+90, 100, 30);
        }
        else if (comprador==1){
        propietario[numero].setText(texto);
        propietario[numero].setHorizontalAlignment(SwingConstants.CENTER);
        
        tablero.add(propietario[numero],4);
        propietario[numero].setBounds(x, y+90, 100, 30);
        }
        else if (comprador==2){
        propietario[numero].setText(texto);
        propietario[numero].setHorizontalAlignment(SwingConstants.CENTER);
        
        tablero.add(propietario[numero],4);
        propietario[numero].setBounds(x, y+90, 100, 30);
        }
        
    
}
    public void borrarImpresionNombre(int numero, NuevoTablero tablero){
        //propietario[numero]=null;
        if (propietario[numero]!=null){
        propietario[numero].setText("");
        tablero.revalidate();
        tablero.repaint();
        }
        
    }
    // El siguiente método paga al dueño de la casilla la cantidad establecida como alquiler en el array de casillas.
    public void PagarAlquiler(int casilla, int jugador, double[][] casillasn, NuevoTablero tablero, jugador jugador1, jugador jugador2){
        
        int alquiler=(int)casillasn[casilla-1][4];
            
        if (jugador==1){
            jugador1.restar(alquiler);
            jugador2.sumar(alquiler);
        }
        else if (jugador==2){
            jugador2.restar(alquiler);
            jugador1.sumar(alquiler);
        }    
        
        JOptionPane.showMessageDialog(null,"Querid@ "+nombre+" debes pagar "+alquiler+" monedas en concepto de alquiler", "Alquiler de habitación",JOptionPane.INFORMATION_MESSAGE);
    }
}
