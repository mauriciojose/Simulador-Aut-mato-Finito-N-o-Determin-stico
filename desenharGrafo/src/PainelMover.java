

import java.awt.Color;
import java.awt.Container;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Mauricio José
 */
// Painel do frame, e que desenha o progresso
public class PainelMover extends javax.swing.JPanel implements ActionListener, MouseListener, MouseMotionListener{

    ArrayList<Circulo> circulos;
    Circulo circulo;
    int circ = -1;
    // Container
    private Container caixa;
    // Botão da tela
    int cont = 0, index;
    int quantCirculos = 0;
    //private JButton botao;
    // Variáveis utilizadas
    private double posX;
    private double posY;
    private boolean desenha;
    private boolean mouseSobreDesenho = false;
    private boolean selecionado;
    boolean aqui = false;
    int id = -1;

    public PainelMover() {
        // Iniciando as variáveis
        
        setBackground(Color.WHITE);
        circulos = new ArrayList<Circulo>();
        desenha = false;
        mouseSobreDesenho = false;
        selecionado = false;
        addMouseListener(this);
        addMouseMotionListener(this);
    }
     @Override
    public void paint(Graphics g) {
        super.paintComponent(g);
        //super.paintComponents(g);
        Graphics2D g2d = (Graphics2D) g.create();
        for (Circulo circulo : circulos) {
            circulo.draw(desenha, selecionado, (Graphics2D) g);

        }
        g2d.dispose();
    }
    public void desenha(int x, int y) {
        id++;
        circulos.add(new Circulo(id, x, y));
        posX = x;
        posY = y;
        desenha = true;
        repaint();
        //System.out.println("passou");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        
    }

    @Override
    public void mouseExited(MouseEvent e) {
        
    }
            @Override
            public void mousePressed(MouseEvent e) {

                for (int i = 0; i < circulos.size(); i++) {
                    //System.out.println("SIZE: " + circulos.size());

                    circulo = new Circulo(circulos.get(i).id, circulos.get(i).x, circulos.get(i).y);

                    //System.out.println("x: " + circulos.get(i).x + "y: " + circulos.get(i).y + "e.x: " + e.getX() + " e.y: " + e.getY());
                    if (circulo.verificaMouseDentroDoCirculo(e.getX(), e.getY())) {
                        aqui = true;
                        circ = i;
                    }
                }
            }

    @Override
            public void mouseReleased(MouseEvent e) {
                System.out.println(".mouseReleased()");
                aqui = false;
            }

    @Override
            public void mouseDragged(MouseEvent e) {
                // Somente faço isso caso eu tenha clicado em cima do círculo e não tenha soltado o mesmo
                //mouseSobreDesenho = true;
                if (aqui) {
                    System.out.println("PosX: " + e.getX() + " PosY" + e.getY());
                    circulos.get(circ).x = e.getX();
                    circulos.get(circ).y = e.getY();
                    System.out.println("Size: " + circulos.size());
                    repaint();
                }

            }
        

    @Override
    public void mouseMoved(MouseEvent e) {
        
    }

}
