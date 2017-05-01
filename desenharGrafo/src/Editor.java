
import Estado.Estado;
import Transicao.ControleTransicao;
import Transicao.Transicao;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.JButton;
import javax.swing.JTextField;

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
    ControleTransicao ct = new ControleTransicao();
    
    int contar = 0;
    NovoJFrame njf;
    //ArrayList<mapearEstados> transicoes;
    static HashMap<Integer, ArrayList<Circulo>> map = new HashMap();
    static HashMap<Integer, ArrayList<mapearEstados>> transicoes = new HashMap();
    ArrayList<Circulo> circulos;
    ArrayList<Linha> linhas;

    Circulo circulo;
    Circulo circuloAux;
    Linha linha;

    int circ = -1;

    // Botão da tela
    int cont = 0, index;
    int quantCirculos = 0;
    //private JButton botao;
    // Variáveis utilizadas
    private double posX;
    private double posY;
    private boolean desenha;
    private boolean movimentoLinha = false;
    private boolean enableText = false;
    private boolean selecionado;
    boolean aqui = false;
    int id = -1;

    private boolean desenhaLinha;
    private boolean desenhaCirculo = false;
    private boolean mover;

    private static Circulo circuloAtual;
    private Circulo circuloIda;

    int indexCirculoAtual;
    int verificaLinha;

    public Editor(NovoJFrame njf) {
        this.njf = njf;
        // Iniciando as variáveis
        setBackground(Color.WHITE);
        setLayout(null);
        circulos = new ArrayList();
        linhas = new ArrayList();
        //transicoes = new ArrayList();
        desenha = false;
        //mouseSobreDesenho = false;
        selecionado = false;

        JTextField text = new JTextField(10);
        text.setLocation(10, 10);
        text.setVisible(true);
        this.add(text);
        validate();

        addMouseListener(this);
        addMouseMotionListener(this);
        enterText();

    }

    public Editor() {
        setBackground(Color.WHITE);
        setLayout(null);
        circulos = new ArrayList();
        linhas = new ArrayList();
        //transicoes = new ArrayList();
        desenha = false;
        //mouseSobreDesenho = false;
        selecionado = false;
        addMouseListener(this);
        addMouseMotionListener(this);
    }

    public void enterText() {
        njf.jTextField1.addKeyListener(new java.awt.event.KeyListener() {
            @Override
            public void keyPressed(java.awt.event.KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    if (enableText) {
                        String text = njf.jTextField1.getText();

                        //linhas.get(linhas.size()-1).lado = verificaLado(circuloAtual, circuloIda);
                        linhas.get(verificaLinha).caracter += text;
                        //linhas.get(verificaLinha).arco = true;
                        linhas.get(verificaLinha).x2 = circuloIda.x+30;
                        linhas.get(verificaLinha).y2 = circuloIda.y+30;
                        
                        ct.controlaTransicao(new Estado(Integer.toString(circuloAtual.id)), new Transicao(text, new Estado(Integer.toString(circuloIda.id))));
                        
                         ArrayList<mapearEstados> trans = transicoes.get(circuloIda.id);
                            int linhaAjuste = -1; 
                            for (int i = 0; i < trans.size(); i++) {
                                if (trans.get(i).circulo2 == circuloAtual.id && i != 0) {
                                    linhaAjuste = trans.get(i).linha;
                                        linhas.get(linhaAjuste).ajusteSeta = 4;
                                        linhas.get(verificaLinha).ajusteSeta = 4;
                                    
                                }
                            }
                        
                        repaint();

                        njf.jTextField1.setText("E");
                        njf.jTextField1.setEnabled(false);
                        enableText = false;
                    }
                }
            }

            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyReleased(KeyEvent e) {

            }

        });

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
        linhas.add(new Linha(x, y));
        desenhaLinha = true;
        posX = x;
        posY = y;
        desenha = true;
        repaint();
    }

    public String verificaLado(Circulo circulo1, Circulo circulo2) {
        System.out.println("verificaLado()");
        if (circulo2.x >= circulo1.x + 60) {
            System.out.println("DIREITO");
            int verifica = verificaArea(circulo1.x, circulo2.x, circulo1.y, circulo2.y);
            if (verifica != -1) {
                return "DIREITO";
            } else {
                return "NULL";
            }
//            verifica(verifica, "DIREITO");

        } else {
            if (circulo2.x + 60 <= circulo1.x) {
                System.out.println("ESQUERDO");
                int verifica = verificaArea(circulo1.x, circulo2.x, circulo1.y, circulo2.y);
                if (verifica != -1) {
                    return "ESQUERDO";
                } else {
                    return "NULL";
                }
//                verifica(verifica, "ESQUERDO");
            } else {
                if (circulo2.y >= circulo1.y + 60) {
                    System.out.println("EMBAIXO");
                    int verifica = verificaArea(circulo1.y, circulo2.y, circulo1.x, circulo2.x);
                    if (verifica != -1) {
                        return "BAIXO";
                    } else {
                        return "NULL";
                    }
//                verifica(verifica, "EMBAIXO");
                } else {
                    if (circulo2.y + 60 <= circulo1.y) {
                        System.out.println("ACIMA");
                        int verifica = verificaArea(circulo1.y, circulo2.y, circulo1.x, circulo2.x);
                        if (verifica != -1) {
                            return "CIMA";
                        } else {
                            return "NULL";
                        }
//                    verifica(verifica, "EMCIMA");
                    } else {
                        System.out.println("NADA DE LADO");
                        return "NULL";
                    }
                }
            }
        }
    }

    public void verifica(int verifica, String lado) {
        if (verifica == 0) {
            System.out.println("LADO " + lado + "1...");
        } else {
            if (verifica == 1) {
                System.out.println("LADO " + lado + "2...");
            } else {
                System.out.println("NAO EH LADO " + lado + "...");
            }
        }
    }

    public int verificaArea(double x1, double x2, double y1, double y2) {
        //System.out.println("circulo 1:"+circulo1.x+","+circulo1.y+"   "+"circulo 2:"+circulo2.x+","+circulo2.y);
        if (y2 >= y1 && y2 <= y1 + 60) {
            return 0;
            //System.out.println("LADO DIREITO1...");
        } else {
            if (y2 + 60 <= y1 + 60 && y2 + 60 >= y1) {
                return 1;
                //System.out.println("LADO DIREITO2...");
            } else {
                return -1;
                //System.out.println("NAO EH LADO DIREITO...");
            }
        }

    }

    /*public void ajustaSeta(Circulo circuloAtual, Circulo circuloIda) {
        if (verificaLado(circuloAtual, circuloIda).equals("DIREITO")) {
            //System.out.println("D...");
            int novoY = (int) (((circuloIda.y + 30) - (circuloAtual.y + 30)) / 2);

            linhas.get(linhas.size() - 1).x = circuloAtual.x + 30;
            linhas.get(linhas.size() - 1).y = circuloAtual.y + 30;

            linhas.get(linhas.size() - 1).width = circuloIda.x;
            linhas.get(linhas.size() - 1).heigth = (circuloIda.y + 30) - novoY;

            int ajusteX = (int) ((circuloIda.y + 30) - (linhas.get(linhas.size() - 1).heigth)) / 2;
            if (ajusteX < 0) {
                ajusteX = ajusteX * (-1);
                linhas.get(linhas.size() - 1).width = circuloIda.x + (ajusteX - 3);
            } else {
                linhas.get(linhas.size() - 1).width = circuloIda.x + (ajusteX - 3);
            }

            repaint();
        } else {
            if (verificaLado(circuloAtual, circuloIda).equals("ESQUERDO")) {
                System.out.println("E...");
                int novoY = (int) (((circuloIda.y + 30) - (circuloAtual.y + 30)) / 2);

                linhas.get(linhas.size() - 1).x = circuloAtual.x + 30;
                linhas.get(linhas.size() - 1).y = circuloAtual.y + 30;

                linhas.get(linhas.size() - 1).width = circuloIda.x + 60;
                linhas.get(linhas.size() - 1).heigth = (circuloIda.y + 30) - novoY;

                int ajusteX = (int) ((circuloIda.y + 30) - (linhas.get(linhas.size() - 1).heigth)) / 2;
                if (ajusteX < 0) {
                    ajusteX = ajusteX * (-1);
                    linhas.get(linhas.size() - 1).width -= (ajusteX - 3);
                } else {
                    linhas.get(linhas.size() - 1).width -= (ajusteX - 3);
                }
                repaint();
            } else {
                if (verificaLado(circuloAtual, circuloIda).equals("CIMA")) {
                    System.out.println("C...");
                    int novoX = (int) (((circuloIda.x + 30) - (circuloAtual.x + 30)) / 2);

                    linhas.get(linhas.size() - 1).x = circuloAtual.x + 30;
                    linhas.get(linhas.size() - 1).y = circuloAtual.y + 30;

                    linhas.get(linhas.size() - 1).heigth = circuloIda.y + 60;
                    linhas.get(linhas.size() - 1).width = (circuloIda.x + 30) - novoX;

                    int ajusteY = (int) ((circuloIda.x + 30) - (linhas.get(linhas.size() - 1).width)) / 2;
                    if (ajusteY < 0) {
                        ajusteY = ajusteY * (-1);
                        linhas.get(linhas.size() - 1).heigth -= (ajusteY - 3);
                    } else {
                        linhas.get(linhas.size() - 1).heigth -= (ajusteY - 3);
                    }
                    repaint();
                } else {
                    if (verificaLado(circuloAtual, circuloIda).equals("BAIXO")) {
                        System.out.println("B...");
                        int novoX = (int) (((circuloIda.x + 30) - (circuloAtual.x + 30)) / 2);

                        linhas.get(linhas.size() - 1).x = circuloAtual.x + 30;
                        linhas.get(linhas.size() - 1).y = circuloAtual.y + 30;

                        linhas.get(linhas.size() - 1).heigth = circuloIda.y;
                        linhas.get(linhas.size() - 1).width = (circuloIda.x + 30) - novoX;

                        int ajusteY = (int) ((circuloIda.x + 30) - (linhas.get(linhas.size() - 1).width)) / 2;
                        if (ajusteY < 0) {
                            ajusteY = ajusteY * (-1);
                            linhas.get(linhas.size() - 1).heigth += (ajusteY - 3);
                        } else {
                            linhas.get(linhas.size() - 1).heigth += (ajusteY - 3);
                        }

                        repaint();
                    } else {
                        diagonal(circuloAtual, circuloIda, linhas.size() - 1);
                    }
                }
            }
        }
    }*/
    public void ajustaMovSeta(Circulo circuloAtual, Circulo circuloIda, int posLinha) {
        if (verificaLado(circuloAtual, circuloIda).equals("DIREITO")) {
            //System.out.println("D...");
            linhas.get(posLinha).lado = "DIREITO";
            int novoY = (int) (((circuloIda.y + 30) - (circuloAtual.y + 30)) / 2);

            linhas.get(posLinha).x = (circuloAtual.x + 30);
            linhas.get(posLinha).y = (circuloAtual.y + 30);

            linhas.get(posLinha).width = circuloIda.x;
            linhas.get(posLinha).heigth = (circuloIda.y + 30) - novoY;

            int ajusteX = (int) ((circuloIda.y + 30) - (linhas.get(posLinha).heigth)) / 2;

            if (ajusteX < 0) {
                ajusteX = ajusteX * (-1);
                linhas.get(posLinha).width = circuloIda.x + (ajusteX - 3);
            } else {
                linhas.get(posLinha).width = circuloIda.x + (ajusteX - 3);
            }

            repaint();
        } else {
            if (verificaLado(circuloAtual, circuloIda).equals("ESQUERDO")) {
                linhas.get(posLinha).lado = "ESQUERDO";
                System.out.println("E...");
                int novoY = (int) (((circuloIda.y + 30) - (circuloAtual.y + 30)) / 2);

                linhas.get(posLinha).x = circuloAtual.x + 30;
                linhas.get(posLinha).y = circuloAtual.y + 30;

                linhas.get(posLinha).width = circuloIda.x + 60;
                linhas.get(posLinha).heigth = (circuloIda.y + 30) - novoY;

                int ajusteX = (int) ((circuloIda.y + 30) - (linhas.get(posLinha).heigth)) / 2;
                if (ajusteX < 0) {
                    ajusteX = ajusteX * (-1);
                    linhas.get(posLinha).width -= (ajusteX - 3);
                } else {
                    linhas.get(posLinha).width -= (ajusteX - 3);
                }
                repaint();
            } else {
                if (verificaLado(circuloAtual, circuloIda).equals("CIMA")) {
                    linhas.get(posLinha).lado = "CIMA";
                    System.out.println("C...");
                    int novoX = (int) (((circuloIda.x + 30) - (circuloAtual.x + 30)) / 2);

                    linhas.get(posLinha).x = circuloAtual.x + 30;
                    linhas.get(posLinha).y = circuloAtual.y + 30;

                    linhas.get(posLinha).heigth = circuloIda.y + 60;
                    linhas.get(posLinha).width = (circuloIda.x + 30) - novoX;

                    int ajusteY = (int) ((circuloIda.x + 30) - (linhas.get(posLinha).width)) / 2;
                    if (ajusteY < 0) {
                        ajusteY = ajusteY * (-1);
                        linhas.get(posLinha).heigth -= (ajusteY - 3);
                    } else {
                        linhas.get(posLinha).heigth -= (ajusteY - 3);
                    }
                    repaint();
                } else {
                    if (verificaLado(circuloAtual, circuloIda).equals("BAIXO")) {
                        linhas.get(posLinha).lado = "BAIXO";
                        System.out.println("B...");
                        int novoX = (int) (((circuloIda.x + 30) - (circuloAtual.x + 30)) / 2);

                        linhas.get(posLinha).x = circuloAtual.x + 30;
                        linhas.get(posLinha).y = circuloAtual.y + 30;

                        linhas.get(posLinha).heigth = circuloIda.y;
                        linhas.get(posLinha).width = (circuloIda.x + 30) - novoX;

                        int ajusteY = (int) ((circuloIda.x + 30) - (linhas.get(posLinha).width)) / 2;
                        if (ajusteY < 0) {
                            ajusteY = ajusteY * (-1);
                            linhas.get(posLinha).heigth += (ajusteY - 3);
                        } else {
                            linhas.get(posLinha).heigth += (ajusteY - 3);
                        }

                        repaint();
                    } else {
                        linhas.get(posLinha).lado = "DIAGONAL";
                        diagonal(circuloAtual, circuloIda, posLinha);
                        repaint();
                    }
                }
            }
        }
    }

    public void diagonal(Circulo circulo1, Circulo circulo2, int posLinha) {
        if (circulo2.x > circulo1.x + 60 && circulo2.y + 60 < circulo1.y) {
            //System.out.println("CD");
            linhas.get(posLinha).width = circulo2.x + 5;
            linhas.get(posLinha).heigth = circulo2.y + 50;
            repaint();
        } else {
            if (circulo1.y + 60 < circulo2.y && circulo1.x + 60 < circulo2.x) {
                //System.out.println("BD");
                linhas.get(posLinha).width = circulo2.x + 7;
                linhas.get(posLinha).heigth = circulo2.y + 7;
                repaint();
            } else {
                if (circulo2.y > circulo1.y + 60 && circulo2.x + 60 < circulo1.x) {
                    //System.out.println("BE");
                    linhas.get(posLinha).width = circulo2.x + 50;
                    linhas.get(posLinha).heigth = circulo2.y + 7;
                    repaint();
                } else {
                    if (circulo2.y + 60 < circulo1.y + 60 && circulo2.x + 60 < circulo1.x) {
                        //System.out.println("CE");
                        linhas.get(posLinha).width = circulo2.x + 53;
                        linhas.get(posLinha).heigth = circulo2.y + 50;
                        repaint();
                    } else {
                        repaint();
                    }
                }
            }
        }
    }

    public boolean verificaTransicao(ArrayList<Circulo> circulos, int numeroEstado) {
        System.out.println("Circulos: " + circulos.size());
        for (int i = 0; i < circulos.size(); i++) {
            if (circulos.get(i).id == numeroEstado) {
                System.out.println("CirculosVerifica: " + circulos.get(i).id);
                return true;
            }
        }
        return false;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (desenhaCirculo) {

            desenha(e.getX(), e.getY());
            repaint();
        } else {
            if (desenhaLinha) {
                if (enableText) {
                    String text = njf.jTextField1.getText();
                    linhas.get(verificaLinha).caracter += text;
                    repaint();
                    //System.out.println("TEXTFIELD: " + text);
                    njf.jTextField1.setText("E");
                    njf.jTextField1.setEnabled(false);
                    enableText = false;
                }
            }
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
                        indexCirculoAtual = i;
                        //System.out.println("Index: "+indexCirculoAtual);
                        if (map.containsKey(circuloAtual.id)) {
                            System.out.println("CONTEM ");

                        } else {
                            map.put(circuloAtual.id, new ArrayList());
                        }
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

                        if (verificaTransicao(map.get(circuloAtual.id), circuloIda.id) == false) {
                           
                            ((ArrayList) map.get(circuloAtual.id)).add(circuloIda);

                            if (!transicoes.containsKey(circuloIda.id)) {
                                transicoes.put(circuloIda.id, new ArrayList());
                                ((ArrayList) transicoes.get(circuloIda.id)).add(new mapearEstados(linhas.size() - 1, indexCirculoAtual, false));
                                System.out.println("SIZE2: "+transicoes.get(circuloIda.id).size());
                            } else {
                                ((ArrayList) transicoes.get(circuloIda.id)).add(new mapearEstados(linhas.size() - 1, indexCirculoAtual, false));
                                System.out.println("SIZE3: "+transicoes.get(circuloIda.id).size());
                            }
                            if (!transicoes.containsKey(circuloAtual.id)) {
                                transicoes.put(circuloAtual.id, new ArrayList());
                                ((ArrayList) transicoes.get(circuloAtual.id)).add(new mapearEstados(linhas.size() - 1, circuloIda.id, true));
                                System.out.println("SIZEatual: "+transicoes.get(circuloAtual.id).size());
                            } else {
                                ((ArrayList) transicoes.get(circuloAtual.id)).add(new mapearEstados(linhas.size() - 1, circuloIda.id, true));
                                System.out.println("SIZEatual1: "+transicoes.get(circuloAtual.id).size());
                            }
                            //ArrayList<mapearEstados> trans = transicoes.get(circuloAtual.id);
                            ajustaMovSeta(circuloAtual, circuloIda, linhas.size() - 1);
                            
                            enableText = true;
                            njf.jTextField1.setEnabled(true);
                            njf.jTextField1.requestFocusInWindow();
                            njf.jTextField1.selectAll();
                            verificaLinha = linhas.size() - 1;
                            remover = false;
                        } else {
                            enableText = true;
                            njf.jTextField1.setEnabled(true);
                            njf.jTextField1.requestFocusInWindow();
                            njf.jTextField1.selectAll();
                            
                             ArrayList<mapearEstados> trans = transicoes.get(circuloAtual.id);
                            for (int j = 0; j < trans.size(); j++) {
                                
                                if (trans.get(j).circulo2 == circuloIda.id) {
                                      if (trans.get(j).ponta) {
                                           verificaLinha = trans.get(j).linha;
                                      }
                                }
                            }
//                            ArrayList<mapearEstados> transIda = transicoes.get(circuloAtual.id);
//                            for (int k = 0; k < transIda.size(); k++) {
//                                
//                                if (transIda.get(k).circulo2 == circuloIda.id) {
//                                    if (!transIda.get(k).ponta) {
//                                        verificaLinha = transIda.get(k).linha;
//                                    }
//                                    //verificaLinha = transIda.get(k).linha;
//                                    //break;
//                                }
//                            }
                            
                        }
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
                if (transicoes.containsKey(circulos.get(circ).id)) {
                    ArrayList<mapearEstados> trans = transicoes.get(circulos.get(circ).id);
                    //System.out.println("Size: "+trans.get(0).circulo2);
                    circulos.get(circ).x = e.getX();
                    circulos.get(circ).y = e.getY();
                    for (int i = 0; i < trans.size(); i++) {
                        if (trans.get(i).ponta) {
                            linhas.get(trans.get(i).linha).x = circulos.get(circ).x + 30;
                            linhas.get(trans.get(i).linha).y = circulos.get(circ).y + 30;
                            ajustaMovSeta(circulos.get(circ), circulos.get(trans.get(i).circulo2), trans.get(i).linha);
                            repaint();
                        } else {
                            ajustaMovSeta(circulos.get(trans.get(i).circulo2), circulos.get(circ), trans.get(i).linha);
//                            linhas.get(trans.get(i).linha).x2 = circulos.get(circ).x + 30;
//                            linhas.get(trans.get(i).linha).y2 = circulos.get(circ).y + 30;
                        }
                        
                    }
                    //System.out.println("Size: "+trans.size());

                } else {
                    //System.out.println("PosX: " + e.getX() + " PosY" + e.getY());
                    circulos.get(circ).x = e.getX();
                    circulos.get(circ).y = e.getY();
                    //System.out.println("Size: " + circulos.size());
                    repaint();
                }
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
    public void percorrePalavra(String palavra)
    {
        ct.estados.clear();
        ct.existe.clear();
        ct.existeEpslonFinal.clear();
        ct.estadosFinaisProcesso.clear();
        ct.run(new Estado("0"), palavra);
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
