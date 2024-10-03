/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package trabajovoluntario;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Hashtable;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.text.AbstractDocument;

/**
 *
 * @author jesus
 */
public class PanelOpciones extends JDialog {
    
   
    private JLabel tamanotablero;
    public int elecciontamano;
    public String cadenaelecciontamano;
    public JTextField nombre1;
    public JTextField nombre2;
    public JSlider eleccionslider;
    String directorioactual= System.getProperty("user.dir");
    String returnavatar1;
    String returnavatar2;
    ButtonGroup grupoavatar1;
    ButtonGroup grupoavatar2;
    
    public PanelOpciones(JFrame ventana){
        
        super(ventana,"Elije las opciones",true);
        setSize(730, 950);
        
        
        JPanel opciones=new JPanel();
        opciones.setLayout(null);
        opciones.setBackground(Color.white);
        
        ImageIcon imagetitulo = new ImageIcon(directorioactual + "/imagenes/tituloopciones.jpg");
        JLabel titulo = new JLabel(imagetitulo);
        titulo.setBounds(0,0,700,350);
        opciones.add(titulo);
        
        
        
        
        
        
        
        // Creo el campo para el nombre del Jugador 1
        ImageIcon imagejugador1 = new ImageIcon(directorioactual + "/imagenes/jugador1.jpg");
        JLabel nombrejugador1= new JLabel(imagejugador1);
        opciones.add(nombrejugador1);
        nombrejugador1.setBounds(150,400,150,35);
        
        nombre1=new JTextField();
        nombre1.setText("Player 1");
        nombre1.setBounds(150,450,150,30);
        opciones.add(nombre1);
        
        // Creo el grupo, los botones de tipo radio y las imagenes para elegir el icono.
        grupoavatar1 = new ButtonGroup();
        
        ImageIcon avatar1 = new ImageIcon(directorioactual + "/iconos/ficharoja.png");
        ImageIcon avatar2 = new ImageIcon(directorioactual + "/iconos/fichaazul.png");
        ImageIcon avatar3 = new ImageIcon(directorioactual + "/iconos/fichaamarilla.png");
        ImageIcon avatar4 = new ImageIcon(directorioactual + "/iconos/fichaverde.png");
        
        JRadioButton jug1avatar1 = new JRadioButton("");
        jug1avatar1.setActionCommand("ficharoja");
        JRadioButton jug1avatar2 = new JRadioButton("");
        jug1avatar2.setActionCommand("fichaazul");
        JRadioButton jug1avatar3 = new JRadioButton("");
        jug1avatar3.setActionCommand("fichaamarilla");
        JRadioButton jug1avatar4 = new JRadioButton("");
        jug1avatar4.setActionCommand("fichaverde");
        
        jug1avatar1.setSelected(true);
        
        jug1avatar1.setBounds(150,500,18,15);
        jug1avatar2.setBounds(230,500,18,15);
        jug1avatar3.setBounds(150,560,18,15);
        jug1avatar4.setBounds(230,560,18,15);
        
        grupoavatar1.add(jug1avatar1);
        grupoavatar1.add(jug1avatar2);
        grupoavatar1.add(jug1avatar3);
        grupoavatar1.add(jug1avatar4);
        
        opciones.add(jug1avatar1);
        opciones.add(jug1avatar2);
        opciones.add(jug1avatar3);
        opciones.add(jug1avatar4);
        
        JLabel imagenavatar1 = new JLabel(avatar1);
        
        JLabel imagenavatar2 = new JLabel(avatar2);
        JLabel imagenavatar3 = new JLabel(avatar3);
        JLabel imagenavatar4 = new JLabel(avatar4);
        
        imagenavatar1.setBounds(170,500,51,51);
        imagenavatar2.setBounds(250,500,51,51);
        imagenavatar3.setBounds(170,560,51,51);
        imagenavatar4.setBounds(250,560,51,51);
        
        
        
        opciones.add(imagenavatar1);
        opciones.add(imagenavatar2);
        opciones.add(imagenavatar3);
        opciones.add(imagenavatar4);
        // Creo el campo para el jugador 2
        ImageIcon imagejugador2 = new ImageIcon(directorioactual + "/imagenes/jugador2.jpg");
        JLabel nombrejugador2= new JLabel(imagejugador2);
        opciones.add(nombrejugador2);
        nombrejugador2.setBounds(350,400,150,35);
        
        nombre2=new JTextField();
        nombre2.setText("Player 2");
        nombre2.setBounds(350,450,150,30);
        opciones.add(nombre2);
        
        //Creo el grupo, los radiobotones y los iconos para elegir del jugador 2
        
        grupoavatar2 = new ButtonGroup();
        
        JRadioButton jug2avatar1 = new JRadioButton("");
        jug2avatar1.setActionCommand("ficharoja");
        JRadioButton jug2avatar2 = new JRadioButton("");
        jug2avatar2.setActionCommand("fichaazul");
        JRadioButton jug2avatar3 = new JRadioButton("");
        jug2avatar3.setActionCommand("fichaamarilla");
        JRadioButton jug2avatar4 = new JRadioButton("");
        jug2avatar4.setActionCommand("fichaverde");
        
        jug2avatar2.setSelected(true);
        
        jug2avatar1.setBounds(350,500,18,15);
        jug2avatar2.setBounds(430,500,18,15);
        jug2avatar3.setBounds(350,560,18,15);
        jug2avatar4.setBounds(430,560,18,15);
        
        grupoavatar2.add(jug2avatar1);
        grupoavatar2.add(jug2avatar2);
        grupoavatar2.add(jug2avatar3);
        grupoavatar2.add(jug2avatar4);
        
        opciones.add(jug2avatar1);
        opciones.add(jug2avatar2);
        opciones.add(jug2avatar3);
        opciones.add(jug2avatar4);
        
        JLabel imagen2avatar1 = new JLabel(avatar1);
        JLabel imagen2avatar2 = new JLabel(avatar2);
        JLabel imagen2avatar3 = new JLabel(avatar3);
        JLabel imagen2avatar4 = new JLabel(avatar4);
        
        imagen2avatar1.setBounds(370,500,51,51);
        imagen2avatar2.setBounds(450,500,51,51);
        imagen2avatar3.setBounds(370,560,51,51);
        imagen2avatar4.setBounds(450,560,51,51);
        
        opciones.add(imagen2avatar1);
        opciones.add(imagen2avatar2);
        opciones.add(imagen2avatar3);
        opciones.add(imagen2avatar4);
        
        //Creo la barra deslizante para elegir el tamaño del tablero
        tamanotablero= new JLabel("Elige el tamaño del tablero");
        opciones.add(tamanotablero);
        tamanotablero.setBounds(250,650,200,30);
        
        
        eleccionslider = new JSlider (JSlider.HORIZONTAL,1,3,3);
        eleccionslider.setBounds(180,700,300,50);
        eleccionslider.setMajorTickSpacing(1);
        eleccionslider.setPaintLabels(true);
        eleccionslider.setSnapToTicks(true);
        eleccionslider.setBackground(Color.white);
        Hashtable<Integer, JLabel> labels = new Hashtable<>();
        labels.put(1, new JLabel("8"));
        labels.put(2, new JLabel("16"));
        labels.put(3, new JLabel("32"));
        eleccionslider.setLabelTable(labels);
        
        opciones.add(eleccionslider);
        
        //Creo el botón para que se cierre el JPanel y comience la partida
        ImageIcon comenzar = new ImageIcon(directorioactual + "/imagenes/comienza.jpg");
        
        JButton botonRecoger = new JButton(comenzar);
        botonRecoger.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose(); 
                
            }
        });
        botonRecoger.setBounds(230,750,200,60);
        opciones.add(botonRecoger); // Agrega el botón al panel
        
        getContentPane().add(opciones);
        
    }
    public int Recogertamano(){
        
        String datos= String.valueOf(eleccionslider.getValue());
        int recogertamano=0;
        if (datos.equals("1")){recogertamano=8;}
        else if (datos.equals("2")){recogertamano=16;}
        else if (datos.equals("3")){recogertamano=32;}
        
        return recogertamano;
    
    }
    public String RecogerJugador1(){
        String datos=nombre1.getText();
        if (datos.equals("")){datos="Player 1";}
        return datos;
    }
    public String RecogerJugador2(){
        String datos=nombre2.getText();
        if (datos.equals("")){datos="Player 2";}
        return datos;
    }
    public String Recogeravatar1(){
        ButtonModel botonseleccionado=grupoavatar1.getSelection();
        returnavatar1=botonseleccionado.getActionCommand();
        return returnavatar1;
    }
    public String Recogeravatar2(){
        ButtonModel botonseleccionado=grupoavatar2.getSelection();
        returnavatar2=botonseleccionado.getActionCommand();
        
        return returnavatar2;
    }
}
