
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
public class PainelDesenhaLinha extends javax.swing.JPanel implements ActionListener, MouseListener, MouseMotionListener {

    ArrayList<Circulo> circulos;
    ArrayList<Linha> linhas;
    Circulo circulo;
    Linha linha;
    private int pontoLinhaX = 0;
    private int pontoLinhaY = 0;
    //int circ = -1;
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
    boolean desenhaLinha = false;
    boolean bola = false;
    boolean aqui = false;
    int id = -1;

    public PainelDesenhaLinha() {
        // Iniciando as variáveis

        setBackground(Color.WHITE);
        circulos = new ArrayList<Circulo>();
        linhas = new ArrayList();
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

        for (Linha linha : linhas) {
            linha.draw((Graphics2D) g);
        }

        for (Circulo circulo : circulos) {
            circulo.draw(desenha, selecionado, (Graphics2D) g);
        }

        g2d.dispose();
    }

    public void desenha(int id, int x, int y) {
        //id++;
        bola = true;
        desenhaLinha = false;
        circulos.add(new Circulo(id, x, y));
        posX = x;
        posY = y;
        desenha = true;
        repaint();
        //System.out.println("passou");
    }

    public void desenhaLinha(int x, int y) {
        id++;
        linhas.add(new Linha(x, y));
        
        desenhaLinha = true;
        bola = false;
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

        System.out.println("SIZE: " + circulos.size());

        for (int i = 0; i < circulos.size(); i++) {

            circulo = new Circulo(circulos.get(i).id, circulos.get(i).x, circulos.get(i).y);
            //System.out.println("x: " + circulos.get(i).x + "y: " + circulos.get(i).y + "e.x: " + e.getX() + " e.y: " + e.getY());
            if (circulo.verificaMouseDentroDoCirculo(e.getX(), e.getY())) {
                desenhaLinha(e.getX(), e.getY());
                
                repaint();
                aqui = true;
                System.out.println("Painel2.mousePressed()");
                //circ = i;
            }

        }
        
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        aqui = false;
        boolean remover = true;
        for (int i = 0; i < circulos.size(); i++) {
            System.out.println("SIZE: " + circulos.size());
            System.out.println("linhasComeço: " + linhas.size());
            circulo = new Circulo(circulos.get(i).id, circulos.get(i).x, circulos.get(i).y);

            //System.out.println("x: " + circulos.get(i).x + "y: " + circulos.get(i).y + "e.x: " + e.getX() + " e.y: " + e.getY());
            if (circulo.verificaMouseDentroDoCirculo(e.getX(), e.getY())) {
                //repaint();
                //System.out.println("linhas: " + linhas.size());
                remover = false;
                linhas.get(linhas.size() - 1).width = e.getX();
                linhas.get(linhas.size() - 1).heigth = e.getY();

                repaint();

            } 
            //System.out.println("linhas: " + linhas.size());
        }
        if (remover) {
            int remove = linhas.size()-1;
            linhas.remove(remove);
            repaint();
        } 

    }

    @Override
    public void mouseDragged(MouseEvent e) {

        // Somente faço isso caso eu tenha clicado em cima do círculo e não tenha soltado o mesmo
        //mouseSobreDesenho = true;
        if (aqui) {

            linhas.get(linhas.size() - 1).width = e.getX();
            linhas.get(linhas.size() - 1).heigth = e.getY();

            //System.out.println("SizeLINHASDRAGGED: " + linhas.size());
            repaint();
        }

    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }

}
