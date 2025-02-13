/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package trabajovoluntario;

import java.awt.*;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
/**
 *
 * @author jesus
 */
public class NuevoTablero extends JPanel {

    
    
    //Declaro las variables que voy a recibir como parámetros del constructor
    private int tamano;
    private int carcel;
    private int iracarcel;
    private int tarjeta1;
    private int tarjeta2;
    private int tarjeta3;
    private int propietario;
    private String directorioactual= (System.getProperty("user.dir")+"/iconos/");
    
    //Array static de 2 dimensiones donde la primera es de 32 casillas y la segunda dimension contiene tipo (0 normal, 1 carcel, 2 ir a carcel, 3 suerte), posicion x, posicion y, valor inicial de compra, valor inicial de multa,propietario.
    private static double[][] casillas = new double [32][6];
    
   // Genero un array con 32 nombres de calles preestablecidos    
    private String[] calles={"Nuevo Parque", "Los Rosales", "Alameda", "Pablo Rada", "Miss Whitney", "Constitución", "Ciudad Jardín", "Santa Cruz", "Alvareda", "Betis", "Pureza", "Zaragoza", "Encarnación", "Magdalena", "Manuel Siurot", "La Palmera", "Heliópolis", "P. del Sol", "Velázquez", "Callao", "Neptuno", "Santa Justa", "Espronceda", "Las 3 mil", "Los príncipes", "Los Corrales", "Avda. Europa", "Diagonal", "Ramblas", "Rue Percebe", "Gran Poder", "San Jacinto"};
        
    // Comienzo con el constructor de la clase NuevoTablero     
    
    public NuevoTablero (int ntamano, int ncarcel, int niracarcel, int ntarjeta1, int ntarjeta2, int ntarjeta3){
        setBackground(Color.WHITE);
        this.tamano=ntamano;
        this.carcel=ncarcel;
        this.iracarcel=niracarcel;
        this.tarjeta1=ntarjeta1;
        this.tarjeta2=ntarjeta2;
        this.tarjeta3=ntarjeta3;
    
    // Declaro varias variables dentro del método que me servirán de apoyo para los distintos cálculos para llenar el array de las casillas   
        
        int y=100;
        int x=10;
        int contador=0;
        String texto= new String();
        int valorarray=1;
        int valorcomprainicial=100;
        int valormultainicial=30;
        double calculocomprainicial=0;
        double calculomultainicial=0;
        
    // Creo un switch para rellenar los datosdel array. Es distinto para tablero de 8, 16 y 32 casillas porque las posiciones x e y cambian para las distintas casillas.
        switch (tamano){
            
            case 8:
               //creo un for para rellenar el array
                for (int m=1;m<=tamano;m++){
                    
                    //contador me servirá para poner el número de la casilla y los if para controlar las posiciones donde quiero que vaya generando las casillas.
                    contador = contador+1;
                    
                    //Vamos incrementando/decrementando los valores de x e y para crear el tablero de forma secuencial en la forma que queremos.
                    
                     if (contador ==1){
                        x=10;
                    }

                    else if (contador <=3){
                        x=x+110; 
                    }
                    else if (contador <=5){

                        y=y+130;
                    }
                    else if (contador <8){
                        x=x-110;
                    }
                    else if (contador==8){
                        y=y-130;
                    }
                     
                    // comprobamos el tipo de casilla actual (carcel, iracarcel, tarjeta o casilla normal)y rellenamos el array con los datos de según el tipo de casilla.
                    if (contador==carcel){
                        casillas[m-1][0]=1;
                        casillas[m-1][1]=x;
                        casillas[m-1][2]=y;
                        casillas[m-1][3]=0;
                        casillas[m-1][4]=0;
                        casillas[m-1][5]=0;
                    }
                    else if (contador==iracarcel){
                        casillas[m-1][0]=2;
                        casillas[m-1][1]=x;
                        casillas[m-1][2]=y;
                        casillas[m-1][3]=0;
                        casillas[m-1][4]=0;
                        casillas[m-1][5]=0;
                    }
                    
                    else if (contador==tarjeta1 || contador==tarjeta2 || contador==tarjeta3){
                    
                        casillas[m-1][0]=3;
                        casillas[m-1][1]=x;
                        casillas[m-1][2]=y;
                        casillas[m-1][3]=0;
                        casillas[m-1][4]=0;
                        casillas[m-1][5]=0;
                    }
                    else{
                    // En el caso de que sea una casilla normal agregamos el valor de coste de casilla y multa de casilla, que se va incrementando conforme vamos aumentando el número de la casilla.
                    // valorarray lo usamos como multiplicador para ir aumentando el valor de coste y multa sólo en las casillas normales, sin aplicar a las casillas especiales.
                        valorarray++;
                        casillas[m-1][0]=0;
                        casillas[m-1][1]=x;
                        casillas[m-1][2]=y;
                    //Al operar salen decimales así que obligo a que el resultado sea int.
                        calculocomprainicial=valorcomprainicial*valorarray/3; 
                        calculocomprainicial=(int)calculocomprainicial;
                        casillas[m-1][3]=calculocomprainicial;
                        calculomultainicial=valormultainicial*valorarray/3;
                        calculomultainicial=(int)(calculomultainicial);
                                
                        casillas[m-1][4]=calculomultainicial;
                    // Si la casilla se puede comprar le pongo valor por defecto 9
                        casillas[m-1][5]=9;
                    }
                    
                    
                }
                                         
            break;
            // Hasta aquí relleno el Array si el tablero es de 8 casillas. Repito la operación para la opción de 16 y 32 casillas. 
            case 16:
                
                for (int m=1;m<=tamano;m++){
                    contador = contador+1;
                    texto=Integer.toString(contador);
                    //Vamos incrementando/decrementando los valores de x e y para crear el tablero de forma secuencial en forma de cuadrado.
                     if (contador ==1){
                        x=10+160;
                    }

                    else if (contador <=5){
                        x=x+110; 
                    }
                    else if (contador <=9){

                        y=y+130;
                    }
                    else if (contador <=13){
                        x=x-110;
                    }
                    else if (contador<=16){
                        y=y-130;
                    }
                  
                    // comprobamos el tipo de casilla actual (carcel, ir a carcel, tarjeta o casilla normal)y la pintamos distinta según el tipo.
                    if (contador==carcel){
                        casillas[m-1][0]=1;
                        casillas[m-1][1]=x;
                        casillas[m-1][2]=y;
                        casillas[m-1][3]=0;
                        casillas[m-1][4]=0;
                        casillas[m-1][5]=0;
                    }
                    else if (contador==iracarcel){
                        casillas[m-1][0]=2;
                        casillas[m-1][1]=x;
                        casillas[m-1][2]=y;
                        casillas[m-1][3]=0;
                        casillas[m-1][4]=0;
                        casillas[m-1][5]=0;
                    }
                    
                    else if (contador==tarjeta1 || contador==tarjeta2 || contador==tarjeta3){
                    
                        casillas[m-1][0]=3;
                        casillas[m-1][1]=x;
                        casillas[m-1][2]=y;
                        casillas[m-1][3]=0;
                        casillas[m-1][4]=0;
                        casillas[m-1][5]=0;
                    }
                    else{
                    
                        valorarray++;
                        casillas[m-1][0]=0;
                        casillas[m-1][1]=x;
                        casillas[m-1][2]=y;
                        calculocomprainicial=valorcomprainicial*valorarray/3; 
                        calculocomprainicial=(int)calculocomprainicial;
                        casillas[m-1][3]=calculocomprainicial;
                        calculomultainicial=valormultainicial*valorarray/3;
                        calculomultainicial=(int)(calculomultainicial);
                                
                        casillas[m-1][4]=calculomultainicial;
                        // Si la casilla se puede comprar le pongo valor por defecto 9
                        casillas[m-1][5]=9;
                    }
                    
                    
                }    
            break;
            
            case 32:
                
                for (int m=1;m<=tamano;m++){
                    contador = contador+1;
                    texto=Integer.toString(contador);
                    
                     if (contador ==1){
                        x=10;
                    }

                    else if (contador <=12){
                        x=x+110; 
                    }
                    else if (contador <=17){

                        y=y+130;
                    }
                    else if (contador <=28){
                        x=x-110;
                    }
                    else if (contador<=32){
                        y=y-130;
                    }

                    
                    
                    // comprobamos el tipo de casilla actual (carcel, ir a carcel, tarjeta o casilla normal)y la pintamos distinta según el tipo.
                    if (contador==carcel){
                        casillas[m-1][0]=1;
                        casillas[m-1][1]=x;
                        casillas[m-1][2]=y;
                        casillas[m-1][3]=0;
                        casillas[m-1][4]=0;
                        casillas[m-1][5]=0;
                    }
                    else if (contador==iracarcel){
                        casillas[m-1][0]=2;
                        casillas[m-1][1]=x;
                        casillas[m-1][2]=y;
                        casillas[m-1][3]=0;
                        casillas[m-1][4]=0;
                        casillas[m-1][5]=0;
                    }
                    
                    else if (contador==tarjeta1 || contador==tarjeta2 || contador==tarjeta3){
                    
                        casillas[m-1][0]=3;
                        casillas[m-1][1]=x;
                        casillas[m-1][2]=y;
                        casillas[m-1][3]=0;
                        casillas[m-1][4]=0;
                        casillas[m-1][5]=0;
                    }
                    else{
                    
                        valorarray++;
                        casillas[m-1][0]=0;
                        casillas[m-1][1]=x;
                        casillas[m-1][2]=y;
                        calculocomprainicial=valorcomprainicial*valorarray/3; 
                        calculocomprainicial=(int)calculocomprainicial;
                        casillas[m-1][3]=calculocomprainicial;
                        calculomultainicial=valormultainicial*valorarray/3;
                        calculomultainicial=(int)(calculomultainicial);
                                
                        casillas[m-1][4]=calculomultainicial;
                        // Si la casilla se puede comprar le pongo valor por defecto 9
                        casillas[m-1][5]=9;
                    
                    }
        
                }
             }
        }
      
    //Ya hemos llenado el array con todos los datos de las casillas. Hasta aquí tendríamos el array listo para recuperarlo en nuestra clase principal Trabajovoluntario.
    
    //Ahora que tenemos el array con todos los datos necesarios uso el siguiente método para dibujar el tablero, sobrecargando el método paintComponent.
    public void RecargaTablero(){
        SwingUtilities.invokeLater(() -> {
        repaint();
        revalidate();
        try {
            Thread.sleep(50); // Pausar la ejecución durante 1 segundo (1000 milisegundos)
        } catch (InterruptedException e) {
            // Manejo de excepciones
            e.printStackTrace();
        }
        });
    }
    
    @Override
    public void paintComponent(Graphics g) {
        
        super.paintComponent(g);
        //Elegimos el tipo de letra para escribir
        Font fuenteNegrita = new Font("Arial", Font.BOLD, 12);
        g.setFont(fuenteNegrita);
        
        //Declaramos las variables del método que nos servirán para los puntos de inicio de pintura de cada casilla, el contador de cada casilla, el texto donde escribiremos el nombre de la calle,
        // el auxiliar para el array de nombres a usar, y dos String que nos servirán para dibujar el valor de la compra y el valor de la multa.
        int y;
        int x;
        int contador=0;
        String texto;
        int valorarray=0;
        String valorcompra;
        String valoralquiler;
        
         
        //Exactamente de la misma forma que llenamos el array ahora usamos el switch para dibujar el tablero según las casillas.
        switch (getTamano()){
            
            case 8:
               // Creamos el for y recojemos los datos necesarios del array que ya hemos creado.
                for (int i=0;i<getTamano();i++){
                    x=(int)getCasillas()[i][1];
                    y=(int)getCasillas()[i][2];
                    
                    contador=i+1;
                    texto=String.valueOf(contador);
                    valorcompra="Coste:"+(int)getCasillas()[i][3];
                    valoralquiler="Alquiler:"+(int)getCasillas()[i][4];
                   // Recogemos el valor de la casilla 0 de la segunda dimensión donde vemos el tipo de casilla. Según el tipo la dibujamos de manera distinta.
                    if (getCasillas()[i][0]==1){
                      
                        ImageIcon icon = new ImageIcon(directorioactual + "carcel.png"); 
                        Image image = icon.getImage();
                        g.drawImage(image, x, y, this);
                        
                    }
                    else if (getCasillas()[i][0]==2){
                        ImageIcon icon = new ImageIcon(directorioactual + "vealacarcel.png"); 
                        Image image = icon.getImage();
                        g.drawImage(image, x, y, this);
                    }
                    
                    else if (getCasillas()[i][0]==3){
                    
                        ImageIcon icon = new ImageIcon(directorioactual + "tarjeta.png"); 
                        Image image = icon.getImage();
                        g.drawImage(image, x, y, this);
                    }
                    else{
                    

                        ImageIcon icon = new ImageIcon(directorioactual + "fondocasilla.png"); 
                        Image image = icon.getImage();
                        g.drawImage(image, x, y, this);
                        
                        g.setColor(Color.BLACK);
                        g.drawString(texto, x+3, y+15);
                        g.drawString(calles[i], x+20, y+15);
                        
                        valorarray++;
                        g.drawString(valorcompra, x+5, y+50);
                        g.drawString(valoralquiler, x+5, y+75);
                    }
                    
                    
                }
                                         
            break;
            
            // Repetimos el proceso para el tablero de 16 casillas y el de 32 casillas.
            
            case 16:
                
                for (int i=0;i<getTamano();i++){
                    
                                         
                    
                      
                    
                    x=(int)getCasillas()[i][1];
                    y=(int)getCasillas()[i][2];
                   
                    contador=i+1;
                    texto=String.valueOf(contador);
                    valorcompra="Coste:"+(int)getCasillas()[i][3];
                    valoralquiler="Alquiler:"+(int)getCasillas()[i][4];
                    
                    if (getCasillas()[i][0]==1){
                        ImageIcon icon = new ImageIcon(directorioactual + "carcel.png"); 
                        Image image = icon.getImage();
                        g.drawImage(image, x, y, this);
                        g.setColor(Color.BLACK);
                        g.drawString(texto, x+3, y+15);
                        
                    }
                    else if (getCasillas()[i][0]==2){
                        ImageIcon icon = new ImageIcon(directorioactual + "vealacarcel.png"); 
                        Image image = icon.getImage();
                        g.drawImage(image, x, y, this);
                        g.setColor(Color.BLACK);
                        g.drawString(texto, x+3, y+15);
                    }
                    
                    else if (getCasillas()[i][0]==3){
                    
                        ImageIcon icon = new ImageIcon(directorioactual + "tarjeta.png"); 
                        Image image = icon.getImage();
                        g.drawImage(image, x, y, this);
                        g.setColor(Color.BLACK);
                        g.drawString(texto, x+3, y+15);
                    }
                    else{
                    

                        ImageIcon icon = new ImageIcon(directorioactual + "fondocasilla.png"); 
                        Image image = icon.getImage();
                        g.drawImage(image, x, y, this);
                        
                        g.setColor(Color.BLACK);
                        g.drawString(texto, x+3, y+15);
                        g.drawString(calles[i], x+20, y+15);
                        valorarray++;
                        g.drawString(valorcompra, x+5, y+50);
                        g.drawString(valoralquiler, x+5, y+75);
                    }
                    
                    
                }    
            break;
            
            case 32:
                
                for (int i=0;i<getTamano();i++){
                    x=(int)getCasillas()[i][1];
                    y=(int)getCasillas()[i][2];
                    contador=i+1;
                    texto=String.valueOf(contador);
                    valorcompra="Coste:"+(int)getCasillas()[i][3];
                    valoralquiler="Alquiler:"+(int)getCasillas()[i][4];
                    
                   
                    if (getCasillas()[i][0]==1){
                      
                        ImageIcon icon = new ImageIcon(directorioactual + "carcel.png"); 
                        Image image = icon.getImage();
                        g.drawImage(image, x, y, this);
                        g.setColor(Color.BLACK);
                        g.drawString(texto, x+3, y+15);
                        
                    }
                    else if (getCasillas()[i][0]==2){
                        ImageIcon icon = new ImageIcon(directorioactual + "vealacarcel.png"); 
                        Image image = icon.getImage();
                        g.drawImage(image, x, y, this);
                        g.setColor(Color.BLACK);
                        g.drawString(texto, x+3, y+15);
                    }
                    
                    else if (getCasillas()[i][0]==3){
                    
                        ImageIcon icon = new ImageIcon(directorioactual + "tarjeta.png"); 
                        Image image = icon.getImage();
                        g.drawImage(image, x, y, this);
                        g.setColor(Color.BLACK);
                        g.drawString(texto, x+3, y+15);
                    }
                    else{

                        ImageIcon icon = new ImageIcon(directorioactual + "fondocasilla.png"); 
                        Image image = icon.getImage();
                        g.drawImage(image, x, y, this);
                        
                        g.setColor(Color.BLACK);
                        g.drawString(texto, x+3, y+15);
                        g.drawString(calles[valorarray], x+20, y+15);
                        valorarray++;
                        g.drawString(valorcompra, x+5, y+50);
                        g.drawString(valoralquiler, x+5, y+75);
                    
                    }
                }                   
                  
            break;
        }
    }

    /**
     * Getters y Setters de las variables que necesito para cargar y guardar partidas.
     */
    public int getTamano() {
        return tamano;
    }

    
    public void setTamano(int tamano) {
        this.tamano = tamano;
    }

    
    public int getCarcel() {
        return carcel;
    }

    
    public void setCarcel(int carcel) {
        this.carcel = carcel;
    }

    
    public int getIracarcel() {
        return iracarcel;
    }

    
    public void setIracarcel(int iracarcel) {
        this.iracarcel = iracarcel;
    }

   
    public int getTarjeta1() {
        return tarjeta1;
    }

    
    public void setTarjeta1(int tarjeta1) {
        this.tarjeta1 = tarjeta1;
    }

   
    public int getTarjeta2() {
        return tarjeta2;
    }

    
    public void setTarjeta2(int tarjeta2) {
        this.tarjeta2 = tarjeta2;
    }

   
    public int getTarjeta3() {
        return tarjeta3;
    }

    
    public void setTarjeta3(int tarjeta3) {
        this.tarjeta3 = tarjeta3;
    }
       
   
    public static double[][] getCasillas() {
        return casillas;
    }

    public static void setCasillas(double[][] aCasillas) {
        casillas = aCasillas;
    }
    
    
   public double verCasillas (int i, int j){
       return casillas[i][j];
   }
    
    
    
} 