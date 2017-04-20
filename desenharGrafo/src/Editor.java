
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
public class Editor extends javax.swing.JPanel implements ActionListener, MouseListener, MouseMotionListener {

    ArrayList<Circulo> circulos;
    ArrayList<Linha> linhas;

    Circulo circulo;
    Linha linha;

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
    private boolean movimentoLinha = false;
    private boolean selecionado;
    boolean aqui = false;
    int id = -1;

    private boolean desenhaLinha;
    private boolean desenhaCirculo = false;
    private boolean mover;
    
    private Circulo circuloAtual;
    private Circulo circuloIda;
    
    public Editor() {
        // Iniciando as variáveis
        setBackground(Color.WHITE);
        circulos = new ArrayList<Circulo>();
        linhas = new ArrayList();
        desenha = false;
        //mouseSobreDesenho = false;
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

    public void desenha(int x, int y) {
        id++;
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
        posX = x;
        posY = y;
        desenha = true;
        repaint();
    }
    public void verificaLado(Circulo circulo1, Circulo circulo2)
    {
        System.out.println("verificaLado()");
        if (circulo2.x  >= circulo1.x+60) {
            System.out.println("DIREITO");
            int verifica = verificaArea(circulo1.x, circulo2.x, circulo1.y, circulo2.y);
            verifica(verifica, "DIREITO");
            
        }else{
            if (circulo2.x+60  <= circulo1.x) {
                System.out.println("ESQUERDO");
                int verifica = verificaArea(circulo1.x, circulo2.x, circulo1.y, circulo2.y);
                verifica(verifica, "ESQUERDO");
            } else {
                if (circulo2.y  >= circulo1.y+60) {
                    System.out.println("EMBAIXO");
                int verifica = verificaArea(circulo1.y, circulo2.y, circulo1.x, circulo2.x);
                verifica(verifica, "EMBAIXO");
                } else {
                    if (circulo2.y+60  <= circulo1.y) {
                        System.out.println("ACIMA");
                    int verifica = verificaArea(circulo1.y, circulo2.y, circulo1.x, circulo2.x);
                    verifica(verifica, "EMCIMA");
                    } else {
                        System.out.println("NADA DE LADO");
                    }
                }
            }
        } 
    }
    public void verifica(int verifica, String lado)
    {
        if (verifica == 0) {
                System.out.println("LADO "+lado+"1...");
            } else {
                if (verifica == 1) {
                System.out.println("LADO "+lado+"2...");
                } else {
                    System.out.println("NAO EH LADO "+lado+"...");
                }
            }
    }
    public int verificaArea(double x1, double x2, double y1, double y2)
    {
            //System.out.println("circulo 1:"+circulo1.x+","+circulo1.y+"   "+"circulo 2:"+circulo2.x+","+circulo2.y);
            if(y2 >= y1 && y2 <= y1+60)
            {
                return 0;
                //System.out.println("LADO DIREITO1...");
            }else{
                if (y2+60 <= y1+60 && y2+60 >= y1) {
                    return 1;
                    //System.out.println("LADO DIREITO2...");
                } else {
                    return -1;
                    //System.out.println("NAO EH LADO DIREITO...");
                }
            }
       
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {

    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (desenhaCirculo) {

            desenha(e.getX(), e.getY());
            repaint();

        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (mover) {
            for (int i = 0; i < circulos.size(); i++) {
                circulo = new Circulo(circulos.get(i).id, circulos.get(i).x, circulos.get(i).y);

                if (circulo.verificaMouseDentroDoCirculo(e.getX(), e.getY())) {
                    aqui = true;
                    circ = i;
                }
            }
        } else {
            if (desenhaLinha) {
                for (int i = 0; i < circulos.size(); i++) {

                    circulo = new Circulo(circulos.get(i).id, circulos.get(i).x, circulos.get(i).y);

                    if (circulo.verificaMouseDentroDoCirculo(e.getX(), e.getY())) {
                        circuloAtual = circulo;
                        
                        desenhaLinha(e.getX(), e.getY());

                        repaint();
                        movimentoLinha = true;
                    }

                }
            } else {
            }
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (mover) {
            aqui = false;
        } else {
            if (desenhaLinha) {
                movimentoLinha = false;
                boolean remover = true;
                for (int i = 0; i < circulos.size(); i++) {
                    circulo = new Circulo(circulos.get(i).id, circulos.get(i).x, circulos.get(i).y);

                    if (circulo.verificaMouseDentroDoCirculo(e.getX(), e.getY())) {
                       
                        circuloIda = circulo;
                        verificaLado(circuloAtual, circuloIda);
                        
                        remover = false;
                        linhas.get(linhas.size() - 1).width = e.getX();
                        linhas.get(linhas.size() - 1).heigth = e.getY();

                        repaint();

                    }
                }
                if (remover) {
                    int remove = linhas.size() - 1;
                    linhas.remove(remove);
                    repaint();
                }
            } else {
            }
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {
        if (mover) {
            if (aqui) {
                System.out.println("PosX: " + e.getX() + " PosY" + e.getY());
                circulos.get(circ).x = e.getX();
                circulos.get(circ).y = e.getY();
                System.out.println("Size: " + circulos.size());
                repaint();
            }
        } else {
            if (desenhaLinha) {

                    linhas.get(linhas.size() - 1).width = e.getX();
                    linhas.get(linhas.size() - 1).heigth = e.getY();
                    repaint();
            } else {
            }
        }
    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }

    /*================================== MÉTODOS GETTERS E SETTS ===========================================*/
    public void setDesenhaLinha(boolean desenhaLinha) {
        this.desenhaLinha = desenhaLinha;
    }

    public void setDesenhaCirculo(boolean desenhaCirculo) {
        this.desenhaCirculo = desenhaCirculo;
    }

    public void setMover(boolean mover) {
        this.mover = mover;
    }

}
