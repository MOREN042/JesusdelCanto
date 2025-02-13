/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package trabajovoluntario;

import java.util.Random;
import javax.swing.JOptionPane;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

/**
 *
 * @author jesus
 */
public class Dado  {
    public JLabel dado;
    Random aleatorio = new Random();
    ImageIcon imagendado;
    ImageIcon imagen;
    String directorioactual= System.getProperty("user.dir");
    
    
    public Dado(NuevoTablero tablero, int tamano){
        
        imagendado =new ImageIcon (directorioactual + "/dado/dado_01.png");
        dado=new JLabel(imagendado);
        if (tamano==8 ){
        dado.setBounds(400, 150, 200, 200);
        }
        else if (tamano ==16){
            dado.setBounds(310, 320, 250, 200);
        }
        else if(tamano==32){
            dado.setBounds(550, 350, 200, 200);    
        }
        //Font tipofuente = new Font("Arial", Font.BOLD, 20);
       // dado.setFont(tipofuente);
         
       
       tablero.add(dado);
        
        
    }

        
    public int TirarDado()   {
    
        int numeroAleatorio = aleatorio.nextInt(6)+1;
        switch (numeroAleatorio){
            case 1:
                imagen=new ImageIcon(directorioactual + "/dado/dado_01.png");
                dado.setIcon(imagen);
                break;
            case 2:
                imagen=new ImageIcon(directorioactual + "/dado/dado_02.png");
                dado.setIcon(imagen);
                break;
            case 3:
                imagen=new ImageIcon(directorioactual + "/dado/dado_03.png");
                dado.setIcon(imagen);
                break;
            case 4:
                imagen=new ImageIcon(directorioactual + "/dado/dado_04.png");
                dado.setIcon(imagen);
                break;    
            case 5:
                imagen=new ImageIcon(directorioactual + "/dado/dado_05.png");
                dado.setIcon(imagen);
                break;    
            case 6:
                imagen=new ImageIcon(directorioactual + "/dado/dado_06.png");
                dado.setIcon(imagen);
                break;    
        }
            
        
        return  numeroAleatorio;
    }
    public void posicionDado(int casillas){
        if (casillas==8 ){
        dado.setBounds(400, 150, 200, 200);
        }
        else if (casillas ==16){
            dado.setBounds(310, 320, 250, 200);
        }
        else if(casillas==32){
            dado.setBounds(550, 350, 200, 200);    
        }
    }
}
