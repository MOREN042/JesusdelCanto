/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.programafabricacion;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.image.BufferedImage;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;


/**
 *
 * @author jesus
 */
public class JLabelArrastrable extends JLabel {
    int modo=1;
    int primera=0;
    int draginicial=0;
    private static int[][] posiciones=new int[128][8];
    private int offsetX;
    private int offsetY;
    private int xant;
    private int yant;
    Color colordefondo;
    int alpha = 50;
    
  // Al crear el objeto le pasamos el texto que llevará, el color, la posición en el array, el JPanel al que lo añadiremos y la ventana a la que pertenece  
    public JLabelArrastrable (String texto,Color color, int posicionArray, JPanel panel, JFrame ventana){
        //llamo al constructor de la superclase
        super(texto);
        //le pongo tamaño por defecto y color
        this.setSize(50, 40);
        this.setBackground(color);
        this.setOpaque(true);
        //lleno el array de posiciones con la posición superior izda x e y, superior derecha x e y, inferior izda x e y, inferior derecha x e y. Con esto sé los puntos de las esquinas que ocupa la barra.
        
        int ancho=getWidth();
        int alto=getHeight();
        
        posiciones[posicionArray][0]=getX();
        posiciones[posicionArray][1]=getY();
        
        posiciones[posicionArray][2]=getX()+ancho;
        posiciones[posicionArray][3]=getY();
        
        posiciones[posicionArray][4]=getX();
        posiciones[posicionArray][5]=getY()+alto;
        
        posiciones[posicionArray][6]=getX()+ancho;
        posiciones[posicionArray][7]=getY()+alto;
        
        
       
        // Hago diferentes listeners para cada acción del ratón
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseExited(MouseEvent e) {
               int primera=0;
               Cursor cursor2= new Cursor(Cursor.DEFAULT_CURSOR);
               ventana.setCursor(cursor2);           
            }
        });
        
        
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
               
               if (primera==0){ 
                   traerAlFrente(panel);
                   primera=1;
               }   
               
            }
        }); 
        
              
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                traerAlFrente(panel);                 
                colordefondo=new Color(color.getRed(),color.getGreen(),color.getBlue(),alpha);
                setBackground(colordefondo);
                
                int pulsacionx = e.getXOnScreen()-ventana.getX()-10;
                int pulsaciony = e.getYOnScreen()-ventana.getY()-30;
                
                int xrangominimo=posiciones[posicionArray][2]-20;
                int xrangomaximo=posiciones[posicionArray][2];
                if (pulsacionx>=xrangominimo&&pulsacionx<=xrangomaximo&&pulsaciony>=posiciones[posicionArray][1]&&pulsaciony<=posiciones[posicionArray][5]){
                    modo=2;
                    Cursor cursor= new Cursor (Cursor.CROSSHAIR_CURSOR);
                    ventana.setCursor(cursor);
                }
                else {
                    modo=1;
                }
            }
        });        
               
            
        this.addMouseListener(new MouseAdapter() {
            @Override
             public void mouseReleased(MouseEvent e) {
                
                setBackground(color);
                draginicial=0;
                Cursor cursor2= new Cursor(Cursor.HAND_CURSOR);
                ventana.setCursor(cursor2);
                
                AjustarAlGrid(posicionArray, panel);
                for (int[] fila:posiciones){
                    if (fila!=null){
                        Colision(posicionArray, panel);
                    }
                }
                
                
                modo=1;
                traerAtras(posicionArray,panel);
            }
        
        });
        
        this.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                traerAlFrente(panel);
                
                if (draginicial==0){
                xant=e.getXOnScreen()-ventana.getX();
                yant=e.getYOnScreen()-ventana.getY();    
                draginicial=1;    
                }
                
                int difx=0;
                int dify=0;
                int xRaton=e.getXOnScreen()-ventana.getX();
                int yRaton=e.getYOnScreen()-ventana.getY();
                 
                if (modo==2){
                difx = xRaton-xant;  
                int x=getWidth()+difx;
                int y=getHeight();
                CambiarTamano(x, y,posicionArray,panel);
                modificarArray(posicionArray);
                    
                }
                else{
                difx = xRaton-xant; 
                dify = yRaton-yant;
                int x=getX();
                int y=getY();
                CambiarPosicion(x+difx, y+dify,posicionArray,panel);
                
                }
                
                xant=e.getXOnScreen()-ventana.getX();
                yant=e.getYOnScreen()-ventana.getY();
                
            }
        });
        this.addMouseMotionListener(new MouseMotionAdapter() {    
            @Override
            public void mouseMoved(MouseEvent e) {
                int pulsacionx = e.getXOnScreen()-ventana.getX()-10;;
                int pulsaciony = e.getYOnScreen()-ventana.getY()-30;;
                
                int xrangominimo=posiciones[posicionArray][2]-20;
                int xrangomaximo=posiciones[posicionArray][2];
                if (pulsacionx>=xrangominimo&&pulsacionx<=xrangomaximo&&pulsaciony>=posiciones[posicionArray][1]&&pulsaciony<=posiciones[posicionArray][5]){
                    
                    Cursor cursor= new Cursor (Cursor.CROSSHAIR_CURSOR);
                    ventana.setCursor(cursor);
                }
                else{
                    Cursor cursor2= new Cursor(Cursor.HAND_CURSOR);
                    ventana.setCursor(cursor2);
                }
              //  System.out.println("pulsacion x:"+pulsacionx);
              //  System.out.println("pulsacion y:"+pulsaciony);
              //  System.out.println("xrangominimo:"+xrangominimo);
              //  System.out.println("xrangomaximo:"+xrangomaximo);
              //  System.out.println("yrangominimo:"+posiciones[posicionArray][1]);
              //  System.out.println("yrangomaximo:"+posiciones[posicionArray][5]);
            }
        });
    
        
    }     
        
            
    public void CambiarTamano(int x, int y, int posicionArray,JPanel panel){
        if (x>=50){
            this.setSize(x, y);
        }
        else {
            this.setSize(50,y);
        }
        modificarArray(posicionArray);
        setText(x+" pixels");
       
        
    }
    public void CambiarPosicion (int x, int y,int posicionArray, JPanel panel){
        int ymax=panel.getWidth();
        if (y>=ymax){y=ymax-40;}
        
        this.setLocation(x, y);
        
        modificarArray(posicionArray);
        panel.repaint();
        
    }
    public void Colision (int posicionArray, JPanel panel){
        
        int xrect;
        int yrect;
        int xancho;
        int yalto;
        Rectangle rect1=new Rectangle (1,1,1,1);
        int xrect2;
        int yrect2;
        int xancho2;
        int yalto2;
        int x;
        int y;
        int limitexpanel=panel.getHeight()-10;
        int colisionxfinalpanel=0;
        Rectangle rect2=new Rectangle (1,1,1,1);
        int i=0;
        //for(int i=0; i<posiciones.length;i++){

        while (i<posiciones.length){
            xrect=getX();
            yrect=getY();
            xancho=getWidth();
            yalto=getHeight();
            rect1.setLocation(xrect,yrect);
            rect1.setSize(xancho, yalto);
            
            
            if (posiciones[i][0]!=0 && posiciones [i][2]!=0&&i!=posicionArray){
                xrect2=posiciones[i][0];
                yrect2=posiciones[i][1];
                xancho2=posiciones[i][2]-posiciones[i][0]; 
                yalto2=posiciones[i][5]-posiciones[i][1]; 
                rect2.setLocation(xrect2,yrect2);
                rect2.setSize(xancho2, yalto2);
                
                if (rect1.intersects(rect2)) {

                    x=posiciones[i][2]+10;
                    y=posiciones[i][3];
                    if (x>=limitexpanel){
                        x=limitexpanel;
                        colisionxfinalpanel=colisionxfinalpanel+1;
                    }
                    
                    if (colisionxfinalpanel>=2){
                        y=y+45;
                    }
                    System.out.println("x:"+x);
                    System.out.println("y:"+y);
                    System.out.println("posicionycolision:"+posiciones[i][3]);
                    CambiarPosicion(x,y,posicionArray,panel);
                    i=-1;
                    
                }
            } 
            i++;
        }
        /*
        int esqSupIzdX=posiciones[posicionArray][0];
        int esqSupIzdY=posiciones[posicionArray][1];
        int esqSupDerX=posiciones[posicionArray][2];
        int esqSupDerY=posiciones[posicionArray][3];
        int esqInfIzdX=posiciones[posicionArray][4];
        int esqInfIzdY=posiciones[posicionArray][5];
        int esqInfDerX=posiciones[posicionArray][6];
        int esqInfDerY=posiciones[posicionArray][7];
        
        boolean colision = false;
        int x;
        int y;
        for (int i=0;i<posiciones.length;i++){
            Integer valor=posiciones[i][0];
            if (valor!=null && i!=posicionArray ){
                if (esqSupIzdX>=posiciones[i][0]&&esqSupIzdX<=posiciones[i][2]&&esqSupIzdY>=posiciones[i][1]&&esqSupIzdY<=posiciones[i][5]){
                    colision=true;
                }
                
                else if (esqSupDerX>=posiciones[i][0]&&esqSupDerX<=posiciones[i][2]&&esqSupDerY>=posiciones[i][1]&&esqSupDerY<=posiciones[i][5]){
                    colision=true;
                }
                else if (esqInfIzdX>=posiciones[i][0]&&esqInfIzdX<=posiciones[i][2]&&esqInfIzdY>=posiciones[i][1]&&esqInfIzdY<=posiciones[i][5]){
                    colision=true;
                }
                else if (esqInfDerX>=posiciones[i][0]&&esqInfDerX<=posiciones[i][2]&&esqInfDerY>=posiciones[i][1]&&esqInfDerY<=posiciones[i][5]){
                    colision=true;
                }
            
            }
            
            if (colision){
                int x=posiciones[i][2]+10;
                int y=posiciones[i][3];
                CambiarPosicion(x,y,posicionArray,panel);
                colision=false;
                
            }
            */
        }
        
   // }
    
    public void modificarArray(int posicionArray){
        
        int ancho=getWidth();
        int alto=getHeight();
        posiciones[posicionArray][0]=getX();
        posiciones[posicionArray][1]=getY();
        
        posiciones[posicionArray][2]=getX()+ancho;
        posiciones[posicionArray][3]=getY();
        
        posiciones[posicionArray][4]=getX();
        posiciones[posicionArray][5]=getY()+alto;
        
        posiciones[posicionArray][6]=getX()+ancho;
        posiciones[posicionArray][7]=getY()+alto;
    }
    
    public void traerAlFrente(JPanel panel){
        panel.setComponentZOrder(this, 0);
        panel.repaint(200);
        
    }
    public void traerAtras (int posicionArray, JPanel panel){
        panel.setComponentZOrder(this, posicionArray);
        panel.repaint(200);
    }
    public void AjustarAlGrid (int posicionArray, JPanel panel){
        int margenx=10;
        int margeny=45;
        int x=getX();
        int y=getY()+20;
        int modx=0;
        int mody=0;
        if (x!=0){
            modx=x%margenx;
        }
        else {
            modx=0;
        }
        if (y!=0){
            mody=y%margeny;
        }
        else {
            mody=0;
        }
        
        //System.out.println("x:"+x);
        //System.out.println("y:"+y);
        x=x-modx;
        y=y-mody;
        
        setLocation(x,y);
        modificarArray(posicionArray);
        //CambiarPosicion(x, y, posicionArray,panel);
        //System.out.println("modx:"+modx);
        //System.out.println("mody:"+mody);
        
        //System.out.println("x:"+x);
        //System.out.println("y:"+y);
        
    } 
    
}
