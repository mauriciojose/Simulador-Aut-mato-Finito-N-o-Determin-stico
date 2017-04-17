

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;



/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Mauricio José
 */
public class NovoJFrame extends javax.swing.JFrame {

    /**
     * Creates new form NovoJFrame
     */
    boolean aqui = false;
    int circ = -1;
    public NovoJFrame() {
        initComponents(); 
        painelDesenhaCirculo.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                    //System.out.println("aquiaaaaaaaaaa");
                    painelDesenhaCirculo.desenha(e.getX(), e.getY());
                    painelMoveCirculo.desenha(e.getX(), e.getY());
                    painelDesenhaLinha.desenha(painelDesenhaCirculo.id,e.getX(), e.getY());
                    repaint();
            }    
        });
        painelMoveCirculo.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {

                for (int i = 0; i < painelMoveCirculo.circulos.size(); i++) {
                    //System.out.println("SIZE: " + circulos.size());

                    Circulo circulo = new Circulo(painelMoveCirculo.circulos.get(i).id, painelMoveCirculo.circulos.get(i).x, painelMoveCirculo.circulos.get(i).y);

                    //System.out.println("x: " + circulos.get(i).x + "y: " + circulos.get(i).y + "e.x: " + e.getX() + " e.y: " + e.getY());
                    if (circulo.verificaMouseDentroDoCirculo(e.getX(), e.getY())) {
                        aqui = true;
                        circ = i;
                    }
                }
            }

            public void mouseReleased(MouseEvent e) {
                aqui = false;
                painelDesenhaCirculo.circulos.get(circ).x = e.getX();
                painelDesenhaCirculo.circulos.get(circ).y = e.getY();
            }

            public void mouseDragged(MouseEvent e) {
                // Somente faço isso caso eu tenha clicado em cima do círculo e não tenha soltado o mesmo
                //mouseSobreDesenho = true;
                if (aqui) {
                    //System.out.println("PosX: " + e.getX() + " PosY" + e.getY());
                    painelMoveCirculo.circulos.get(circ).x = e.getX();
                    painelMoveCirculo.circulos.get(circ).y = e.getY();
                    //System.out.println("Size: " + painel11.circulos.size());
                    painelMoveCirculo.repaint();
                }

            }
        });
	
  }
       
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        painelDesenhaCirculo = new PainelDesenhaCirculo();
        painelMoveCirculo = new PainelMover();
        painelDesenhaLinha = new PainelDesenhaLinha();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenu2 = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jTabbedPane1.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout painelDesenhaCirculoLayout = new javax.swing.GroupLayout(painelDesenhaCirculo);
        painelDesenhaCirculo.setLayout(painelDesenhaCirculoLayout);
        painelDesenhaCirculoLayout.setHorizontalGroup(
            painelDesenhaCirculoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        painelDesenhaCirculoLayout.setVerticalGroup(
            painelDesenhaCirculoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("adicionar", painelDesenhaCirculo);

        javax.swing.GroupLayout painelMoveCirculoLayout = new javax.swing.GroupLayout(painelMoveCirculo);
        painelMoveCirculo.setLayout(painelMoveCirculoLayout);
        painelMoveCirculoLayout.setHorizontalGroup(
            painelMoveCirculoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        painelMoveCirculoLayout.setVerticalGroup(
            painelMoveCirculoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("mover", painelMoveCirculo);

        javax.swing.GroupLayout painelDesenhaLinhaLayout = new javax.swing.GroupLayout(painelDesenhaLinha);
        painelDesenhaLinha.setLayout(painelDesenhaLinhaLayout);
        painelDesenhaLinhaLayout.setHorizontalGroup(
            painelDesenhaLinhaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 395, Short.MAX_VALUE)
        );
        painelDesenhaLinhaLayout.setVerticalGroup(
            painelDesenhaLinhaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 228, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("Transição", painelDesenhaLinha);

        jMenu1.setText("File");
        jMenuBar1.add(jMenu1);

        jMenu2.setText("Edit");
        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1, javax.swing.GroupLayout.Alignment.TRAILING)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(NovoJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(NovoJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(NovoJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(NovoJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new NovoJFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JTabbedPane jTabbedPane1;
    private PainelDesenhaCirculo painelDesenhaCirculo;
    private PainelDesenhaLinha painelDesenhaLinha;
    private PainelMover painelMoveCirculo;
    // End of variables declaration//GEN-END:variables
}