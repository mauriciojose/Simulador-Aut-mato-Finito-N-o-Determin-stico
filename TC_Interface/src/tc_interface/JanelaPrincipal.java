//Definições do pacote
package tc_interface;

//Importações
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 * 
 *  @author Jefferson Ricardo Santos        <jeff.debian2016@gmail.com>
 *  @author José Carlos dos Santos Junior   <juninho_santos19_9@live.com>
 *  @author Mauricio José Miranga Guimarães <mauriciojosemirandaguimaraes@gmail.com>
 *  @author Paulo David Almeida da Silva    <pdavidalmeida@hotmail.com>
 * 
 */

/*********** DEFINIÇÃO DA CLASSE ****************/
/*
 * CLASSE PRINCIPAL, RESPONSÁVEL POR GERAR E EXIBIR
 * TODA A PARTE VISUAL, BEM COMO A CHAMADA DE ACTIONS
 * QUE SERÃO EXECUTADAS SOBRE O AFND CRIADO, ALÉM DO MÉTODO MAIN
 *	
 ************************************************/

//Inicio da classe JanelaPrincipal
public class JanelaPrincipal extends JFrame{    
    
    /******************************* COMPONENTES ******************************/
    //Menu
    JMenu     menuArq;
    JMenuBar  barraMenu;
    JMenuItem itemNovo, itemAbrir, itemSalvar, itemFechar;  
    //Painel
    JPanel    painel, painelNorte,painelLeste,painelCenter,painelOeste,painelSul;
    //Label
    JLabel    planoFundo;
    //Botões 
    JButton   btNovoEstado, btNovaTransicao, btExibirTabela, btValidaPalavra;
    //Tabelas
    JTable    tbPalavras, tbEstados;
    /****************************** EVENTOS ***********************************/
    //Eventos
    Eventos  e  ;
    
    /******************************* CONSTRUTOR *******************************/
    public JanelaPrincipal(){
        
        //Model das tabelas
        DefaultTableModel modelEstados = new DefaultTableModel();
        DefaultTableModel modelPalavras = new DefaultTableModel();
        
        //Inicialização das tabelas
        tbPalavras = new JTable(modelPalavras);
        tbEstados = new JTable(modelEstados);
        
        //Inicialização - Menu (Arquivo)
        barraMenu = new JMenuBar();
        menuArq = new JMenu("  Arquivo");
        barraMenu.add(menuArq);
        itemNovo = new JMenuItem(" Novo",new ImageIcon(getClass().getResource("/imagens/icone/novoArq.png")));
        itemAbrir = new JMenuItem("Abrir",new ImageIcon(getClass().getResource("/imagens/icone/abrirArq.png")));
        itemSalvar = new JMenuItem("Salvar",new ImageIcon(getClass().getResource("/imagens/icone/salvar.png")));
        itemFechar = new JMenuItem("Fechar",new ImageIcon(getClass().getResource("/imagens/icone/fechar.png")));
        
        //Inicialização - Painel Principal
        painel = new JPanel();
        painel.setLayout(new BorderLayout());
        painel.setBackground(Color.yellow);
        painel.setBounds(0,0,900,40);         
        painel.setOpaque(false);              
        
        //Inicialização - Painel Norte
        painelNorte = new JPanel();
        painelNorte.setLayout(new FlowLayout());
        painelNorte.setOpaque(false);         
            
        //Inicialização - Painel Sul
        painelSul = new JPanel();
        painelSul.setLayout(new FlowLayout());
        painelSul.setOpaque(false);              
        
        //Inicialização - Painel Leste
        painelLeste = new JPanel();    
        painelLeste.setLayout(new BorderLayout());
        painelLeste.setOpaque(false);
        
        //Inicialização - Painel Oeste
        painelOeste = new JPanel();    
        painelOeste.setLayout(new BorderLayout());
        painelOeste.setOpaque(false);
            
        //Painel Centro
        painelCenter = new JPanel();    
        painelCenter.setLayout(new BorderLayout());
        painelCenter.setOpaque(false);
            
        //Inicialização dos eventos
        e = new Eventos(tbEstados,tbPalavras);
        
        //Inicialização - Label(Background)
        planoFundo = new JLabel(new ImageIcon(getClass().getResource("/imagens/background/background.jpg")));        
        planoFundo.setBounds(0, 0, 800, 600);        
        
        //Inicialização - Botões
        btNovoEstado = new JButton("Estado",new ImageIcon(getClass().getResource("/imagens/icone/addEstado.png")));        //Inserir imagens aqui
        btNovaTransicao = new JButton("Transição",new ImageIcon(getClass().getResource("/imagens/icone/addTransicao.png")));
        btExibirTabela = new JButton("Tabela",new ImageIcon(getClass().getResource("/imagens/icone/exibirTabela.png")));
        btValidaPalavra = new JButton("Validação",new ImageIcon(getClass().getResource("/imagens/icone/validaPalavra.png")));
        
        //Ações - Botões Automato
        btNovoEstado.addActionListener(e);
        btNovaTransicao.addActionListener(e);
        btExibirTabela.addActionListener(e);
        btValidaPalavra.addActionListener(e);
        
        //Ações - Botões Barra Menu
        itemNovo.addActionListener(e);
        itemAbrir.addActionListener(e);
        itemSalvar.addActionListener(e);
        itemFechar.addActionListener(e);
        
        //Inclusão de itens - Menu
        menuArq.add(itemNovo);
        menuArq.addSeparator();
        menuArq.add(itemAbrir);
        menuArq.addSeparator();
        menuArq.add(itemSalvar);
        menuArq.addSeparator();
        menuArq.add(itemFechar);
        
        //Inclusão de componentes - Painel Norte
        painelNorte.add(btNovoEstado);
        painelNorte.add(btNovaTransicao);
        painelNorte.add(btExibirTabela);
        painelNorte.add(btValidaPalavra);
        
        //Inclusao de componentes - Painel Oeste
        JScrollPane scrollPane = new JScrollPane(tbEstados);
        scrollPane.setSize(100,50);
        painelOeste.add(scrollPane,BorderLayout.CENTER);
        //painelOeste.add(new JButton("Estados"),BorderLayout.CENTER);
        
        //Inclusão de componentes - Painel Centro
        painelCenter.add(new JButton("TABELA"),BorderLayout.CENTER);
        
        //Inclusão de componentes - Painel Leste
        JScrollPane scrollPane2 = new JScrollPane(tbPalavras);
        scrollPane2.setSize(100,50);
        painelLeste.add(scrollPane2,BorderLayout.CENTER);
        //painelLeste.add(new JButton("Palavras Testadas"));
        
        //Inclusão de componentes - Painel Sul
        painelSul.add(new JLabel("Teoria da Computação - UFS Campus Itabaiana - 2016.2"));

        //Inclusão de componentes - Painel Principal
        painel.add(painelNorte,BorderLayout.NORTH);
        painel.add(painelOeste,BorderLayout.WEST);
        painel.add(painelCenter,BorderLayout.CENTER);
        painel.add(painelLeste,BorderLayout.EAST);
        painel.add(painelSul,BorderLayout.SOUTH);
        
        //Inclusão de componentes - Frame
        getContentPane().add(painel);
        
        //Configurações da Tela Principal
        setJMenuBar(barraMenu);
        setSize(817,450);
        setTitle("AFND - TC 2016.2");
        setLocationRelativeTo(null);
        setResizable(true);             
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
    //Fim da Classe JanelaPrincipal
    
    /******************************* PRINCIPAL ********************************/
    public static void main(String[] args){
        
        JanelaPrincipal jPrincipal = new JanelaPrincipal();
        jPrincipal.setVisible(true);
    }
    
    

}
