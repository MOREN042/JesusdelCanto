/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.programafabricacion;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
/**
 *
 * @author jesus
 */

    
public class ventanaDobleBufer extends JPanel {
    private BufferedImage buffer;
    
    public ventanaDobleBufer(int ventanax, int ventanay, int posicionx, int posiciony) {
        // Tamaño inicial del panel
        super();
        setBounds(posicionx, posiciony, ventanax, ventanay);
        setOpaque(true);
        setLayout(null);
        }
    public void cambiarColor(Color color){
        setBackground(color);
        repaint();
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        // Inicializar el búfer si es necesario
        if (buffer == null || buffer.getWidth() != getWidth() || buffer.getHeight() != getHeight()) {
            buffer = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_ARGB);
        }

        // Obtener el contexto de gráficos del búfer
        Graphics bufferGraphics = buffer.getGraphics();

        // Limpiar el búfer
        bufferGraphics.clearRect(0, 0, getWidth(), getHeight());

        // Dibujar los componentes en el búfer
        super.paintComponent(bufferGraphics); // Cambio aquí

        // Dibujar el búfer en el panel
        g.drawImage(buffer, 0, 0, null);
        }
}
