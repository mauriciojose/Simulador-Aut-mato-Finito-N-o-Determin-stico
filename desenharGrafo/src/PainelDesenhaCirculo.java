
import java.awt.Color;
import java.awt.Container;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
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
public class PainelDesenhaCirculo extends javax.swing.JPanel {

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

    public PainelDesenhaCirculo() {
        // Iniciando as variáveis
        setBackground(Color.WHITE);
        circulos = new ArrayList<Circulo>();
        desenha = false;
        mouseSobreDesenho = false;
        selecionado = false;
        
        /*addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                    //System.out.println("aquiaaaaaaaaaa");
                    desenha(e.getX(), e.getY());
                    repaint();
            }    
        });*/
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

}
