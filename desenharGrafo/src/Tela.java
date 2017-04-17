
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.Scanner;
import javax.swing.*;

public class Tela extends JFrame implements ActionListener, MouseListener, MouseMotionListener {

    private ArrayList<Circulo> circulos;
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
    
    public Tela() {
        // Iniciando as variáveis
        circulos = new ArrayList<Circulo>();
        desenha = false;
        mouseSobreDesenho = false;
        selecionado = false;
        // Iniciano Container
        getContentPane().setBackground(Color.WHITE);
        caixa = getContentPane();
        caixa.setLayout(null);
        
        setSize(500, 500);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        // Adicionando eventos do mouse
        addMouseListener(this);
        addMouseMotionListener(this);
        //Scanner s = new Scanner(System.in);
        //System.out.println("");
        //s.nextLine();
        
        //run();
        


    }
    public void start()
    {
        desenha(50, 60);
            selecionado = false;
            repaint();
    }

    /*public void run() {

        System.out.println("aqui, argggggggggg");
        while (true) {

            Point ponto = getMousePosition();

            if (mouseSobreDesenho == true) {
                for (int i = 0; i < circulos.size(); i++) {
                    System.out.println("aqui11");
                    Circulo circulo = new Circulo(circulos.get(i).x, circulos.get(i).y);
                    if (circulo.verificaMouseDentroDoCirculo(ponto.x, ponto.y)) {
                        mouseSobreDesenho = true;
                        circulos.get(i).setPosx(ponto.x);
                        circulos.get(i).setPosy(ponto.y);
                        repaint();
                        System.out.println("aqui2");
                    }
                }
                try {
                    Thread.sleep(1000 / 30);
                } catch (Exception e) {
                    System.out.println("Thread interrompida!");
                }
            }
        }
    }*/
    @Override
    public void paint(Graphics g) {
        super.paintComponents(g);
        Graphics2D g2d = (Graphics2D) g.create();
        for (Circulo circulo : circulos) {
            circulo.draw(desenha, selecionado, (Graphics2D) g);
                        
        }
        g2d.dispose();
    }
    // Controla os eventos do clique do mouse

    public void mouseClicked(MouseEvent e) {
        if (quantCirculos == 0) {
            quantCirculos++;
            System.out.println("aquiaaaaaaaaaa");
            desenha(e.getX(), e.getY());
            selecionado = false;
            repaint();
        } else {
            System.out.println("ELSE");
            for (int i = 0; i < circulos.size(); i++) {
                //System.out.println("SIZE: " + circulos.size());

                Circulo circulo = new Circulo(circulos.get(i).id,circulos.get(i).x, circulos.get(i).y);

                //System.out.println("x: " + circulos.get(i).x + "y: " + circulos.get(i).y + "e.x: " + e.getX() + " e.y: " + e.getY());
                if (circulo.verificaMouseDentroDoCirculo(e.getX(), e.getY())) {

                    aqui = true;
                } else {

                }
            }
            if(!aqui)
            {
                System.out.println("aqui");
                desenha(e.getX(), e.getY());
                
                repaint();
            }
            else{aqui = false;}

        }

        //if(cont == 0)
        //{
        //desenha(e.getX(), e.getY());
        //mouseSobreDesenho = true;
        // }
        /*else
                {
                    quantCirculos++;
                }*/
        System.out.println(e.getX() + "   " + e.getY());
        
    }

    // Método que é acionado quando você passa com o mouse por cima do componente
    // que está sendo implementado o MouseListener
    public void mouseEntered(MouseEvent e) {
    }

    // Método que é acionado quando você retira o mouse de cima do componente
    // que está sendo implementado o MouseListener
    public void mouseExited(MouseEvent e) {
    }

    // Método que é acionado quando você pressiona o mouse em cima do componente
    // que está sendo implementado o MouseListener
    public void mousePressed(MouseEvent e) {
        //aqui = true;
        for (int i = 0; i < circulos.size(); i++) {
                //System.out.println("SIZE: " + circulos.size());

                circulo = new Circulo(circulos.get(i).id,circulos.get(i).x, circulos.get(i).y);

                //System.out.println("x: " + circulos.get(i).x + "y: " + circulos.get(i).y + "e.x: " + e.getX() + " e.y: " + e.getY());
                if (circulo.verificaMouseDentroDoCirculo(e.getX(), e.getY())) {
                    aqui = true;
                    circ = i;
                } 
        }

        //for(Circulo circulo : circulos){
        /*for(int i = 0; i < quantCirculos; i++){
    		if(circulos.get(i).verificaMouseDentroDoCirculo(e.getX(), e.getY())){
    			mouseSobreDesenho = true;
                        index = i;
                        posX = e.getX();
                        posY = e.getY();
                        System.out.println("aquuiiiiiiiiii");
    			repaint();
    		}
    	}*/
 /*for(int i = 0; i < quantCirculos; i++){
                    //System.out.println("i: "+i);
                    Circulo circulo = new Circulo(circulos.get(i).x, circulos.get(i).y);
                    if(circulo.verificaMouseDentroDoCirculo(e.getX(), e.getY())){
                            mouseSobreDesenho = true;
                            circulos.get(i).x = e.getX();
                            circulos.get(i).y = e.getY();
                            repaint();
                            System.out.println("aqui2");
                    }*/
 /*else{
                        System.out.println("aqui");
                        desenha(e.getX()-25, e.getY()-25);
                        selecionado = true;
                        repaint();
                    }*/
        // }
    }

    // Método que é acionado quando você solta o click do mouse
    public void mouseReleased(MouseEvent e) {
        aqui = false;
        System.out.println("aquireleased");
        /*for (int i = 0; i < circulos.size(); i++) {
            if (circulos.get(i).verificaMouseDentroDoCirculo(e.getX(), e.getY())) {
                System.out.println("aquireleased");
                posX = e.getX();
                posY = e.getY();

                //circulos.get(index).setPosx(posX);
                //circulos.get(index).setPosy(posY);
                repaint();
            }
        }*/

        // Indica que soltei o mouse seja onde eu estiver...
        //mouseSobreDesenho = false;
        //repaint();*/
    }

    public void mouseDragged(MouseEvent e) {
        // Somente faço isso caso eu tenha clicado em cima do círculo e não tenha soltado o mesmo
        //mouseSobreDesenho = true;
         if(aqui)
        {
            System.out.println("PosX: "+e.getX()+" PosY"+e.getY());
            circulos.get(circ).x = e.getX();
            circulos.get(circ).y = e.getY();
            System.out.println("Size: "+circulos.size());
            repaint();
        }
                    
             

    }

    public void mouseMoved(MouseEvent e) {
       

    }

    public void desenha(int x, int y) {
        //x = x - 25;
        //y = y - 25;
        id++;
        circulos.add(new Circulo(id,x, y));
        posX = x;
        posY = y;
        desenha = true;
        repaint();
        System.out.println("passou");
    }

    /*public void actionPerformed(ActionEvent e)
	{
		// Desenho caso eu aperte este botão, colocando nas posições x e y pré-definidas
		
		}	
	}*/
    public static void main(String args[]) {
        Tela obj = new Tela();

        Scanner s = new Scanner(System.in);
        System.out.println("");
        s.nextLine();
        obj.start();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
