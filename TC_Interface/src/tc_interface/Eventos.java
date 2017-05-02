//Definições de pacote
package tc_interface;

//Importações
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.table.DefaultTableModel;

/**
 * 
 *  @author Jefferson Ricardo Santos        <jeff.debian2016@gmail.com>
 *  @author José Carlos dos Santos Junior   <juninho_santos19_9@live.com>
 *  @author Mauricio José Miranga Guimarães <mauriciojosemirandaguimaraes@gmail.com>
 *  @author Paulo David Almeida da Silva    <pdavidalmeida@hotmail.com>
 * 
 */

/****************** DEFINIÇÃO DA CLASSE *******************/
/*
 * CLASSE RESPONSÁVEL POR TODOS OS EVENTOS RELATIVOS AO 
 * AUTOMATOS: GERAÇÃO DE ESTADOS, TRANSICAO, DEF. FORMAL
 * E VALIDAÇÃO, ALÉM DOS ACTION DOS COMPONENTES UTILIZADOS
 *	
 **********************************************************/

public class Eventos implements ActionListener {
    
    //Atributos
    private  Automatos afnd                                = new Automatos();   
    private  ArrayList modelComboBox                       = new ArrayList <>();
    private  ArrayList <TabelaTransicao> tabelaTransicao   = new ArrayList <>();
    static   HashMap   <String, ArrayList<Transicao>> map  = new HashMap();
    static   HashMap   <String, Boolean> existe            = new HashMap();
    static   HashMap   <String, Boolean> existeEpslonFinal = new HashMap();
    ArrayList<String> estadosFinais                        = new ArrayList();
    ArrayList<String> estados                              = new ArrayList();
    ArrayList<String> estadosFinaisProcesso                = new ArrayList();
    boolean temEpslon = false;
    int percorrePalavra = 0;
    private char caracterAtual;
    
    //TABELA
    private JTable tbEstados,tbPalavras;
    DefaultTableModel modelEstados = new DefaultTableModel();
    DefaultTableModel modelPalavras = new DefaultTableModel();
    
    
    //Construtor
    public Eventos(JTable tbEstados, JTable tbPalavras){
        this.tbPalavras = tbPalavras;
        this.tbEstados = tbEstados;
        modelEstados.addColumn("Estado");
        modelPalavras.addColumn("Palavras");
        modelPalavras.addColumn("Aceita?");
    }
    
    //Getters
    public char getCaracterAtual() {
        return caracterAtual;
    }
    
    public Automatos getAfnd(){
        return afnd;
    }
    
    //Setters
    public void setCaracterAtual(char caracterAtual) {
        this.caracterAtual = caracterAtual;
    }
    
    /****************************** METODOS ***********************************/
    
    @Override
    public void actionPerformed(ActionEvent e) {        //Efetua a chamada dos métodos de acordo com o evento na janela Principal
        
        String palavra;
        
        switch (e.getActionCommand()) {
            case "Estado":
                gerarEstado();
            break;
            case "Transição":
                criarTransicao();
            break;
            case "Tabela":
                tabelaTransicao();
            break;
            case "Validação":
                palavra = JOptionPane.showInputDialog(null,"Qual a palavra a validar?","Informe a palavra a validar",JOptionPane.QUESTION_MESSAGE);
                run(palavra);
            break;
            case " Novo":
                JOptionPane.showMessageDialog(null,"Novo");
            break;
            case "Abrir":
                JOptionPane.showMessageDialog(null,"Abrir");
            break;
            case "Salvar":
                JOptionPane.showMessageDialog(null,"Salvar");
            break;
            case "Fechar":
                System.exit(0);
            break;
            default:
                //Criar throw para dar erro, apesar que isso nunca deverá acontecer;
            break;
        }
        
    }  //Fim do actionPerformed()
    
    //Criação dos estados
    public void gerarEstado(){    
        
        //Atributos
        int n;
        String t;
        boolean isInicial,isFinal;
        String message = "O estado é inicial/final?";
        String nome =  "q" + afnd.getEstadosView().size();
        //Inicialização componentes
        JCheckBox checkboxInicial = new JCheckBox("Inicial");
        JCheckBox checkboxFinal = new JCheckBox("Final");
       
        Object[] params = {message, checkboxInicial,checkboxFinal};
        n = JOptionPane.showConfirmDialog(null, params, "Inicial/Final", JOptionPane.YES_NO_OPTION);
        
        //Leitura das entradas
        isInicial = checkboxInicial.isSelected();
        isFinal   = checkboxFinal.isSelected();
        
        for(Estado e : afnd.getEstadosView()){                                   //Verifica se já existe estado inicial que deixa de ser 
            if(isInicial)                                                        //para ser o ultimo estado inicial adicionado 
                if(e.isEstadoInicial())
                    e.setEstadoInicial(false);       
        }
        if(n == 0){
            t = afnd.addEstado(isInicial, afnd.getEstadosView().size() ,isFinal);   //Criação do estado e adiciona ao ArrayList de EstadosView
            modelComboBox.add(modelComboBox.size(), t);                             //Adiciona o nome do estado ao ComboBox
            String[] linhas = {nome};
            modelEstados.addRow(linhas);
            tbEstados.setModel(modelEstados);
        }
    }//Fim do gerarEstado()
    
    //Criar Transicão para ser exibida e adicionada ao HashMap
    public void criarTransicao(){
        
        //Atributos
        String simbolo;
        String estadoOrigem, estadoDestino;
        
        //Inicialização de componentes
        JComboBox listOrigem    = new JComboBox(modelComboBox.toArray());
        JComboBox listDestino   = new JComboBox(modelComboBox.toArray());
        JTextArea recebeSimbolo = new JTextArea(); 
        JPanel    plist         = new JPanel();
        JLabel textOrigem       = new JLabel("Origem:",JLabel.CENTER);
        JLabel textSimbolo      = new JLabel("Simbolo:",JLabel.CENTER);
        JLabel textDestino      = new JLabel("Destino:",JLabel.CENTER);
        
        //Definições do JTextArea
        recebeSimbolo.setLineWrap(true);
        
        //Definições do Painel
        plist.setLayout(new GridLayout());
        plist.setOpaque(false);
        
        //Adiciona os componentes ao painel
        plist.add(textOrigem);
        plist.add(listOrigem);
        plist.add(textSimbolo);
        plist.add(recebeSimbolo);
        plist.add(textDestino);
        plist.add(listDestino);
        
        int n = JOptionPane.showConfirmDialog(null, plist, "Transicao", JOptionPane.YES_NO_OPTION);
        
        //Leitura das entradas
        simbolo = recebeSimbolo.getText();
        estadoOrigem = (String)listOrigem.getSelectedItem();
        estadoDestino = (String)listDestino.getSelectedItem();
        
        //Gera a transicao e adiciona ao ArrayList de Transição
        if(n == 0)
            gerarTransicao(estadoOrigem,simbolo.charAt(0),estadoDestino);
    } //Fim do criarTransição()
    
    //Gerar a transição criada no método criar 
    public void gerarTransicao(String origem, char simbolo, String destino){
        tabelaTransicao.add(new TabelaTransicao(selectOrigem(origem),simbolo,selectDestino(destino))); //Criar transição para exibir na tabela
        controlaTransicao(selectOrigem(origem),new Transicao(simbolo,selectDestino(destino)));         //Adiciona Transição ao HashMap
    } //Fim do gerarTransicao()
    
    public Estado selectOrigem(String origem){          //Busca o estado de origem a partir de seu nome 
        for(Estado e: afnd.getEstadosView())
            if(origem.equalsIgnoreCase(e.getNome())){   
                return e;
            }
        return null;
    } //Fim do selectOrigem()
        
    public Estado selectDestino(String destino){       //Busca o estado de destino a partir de seu nome
        for(Estado e: afnd.getEstadosView())
            if(destino.equalsIgnoreCase(e.getNome())){
                return e;
            }
        return null;
    } //Fim do selectDestino()
    
    public Estado buscaInicial(){                               //Busca o estado Inicial
        for(Estado e:afnd.getEstadosView()){
            if(e.isEstadoInicial())
                return e;
        }
        return null;
    } //Fim do buscaInicial()

    public void run(String palavra) {                   //Executa a validação da palavra
        
        //Inicialização de Atributos
        //estadosFinais.clear();
        existe.put("%", Boolean.TRUE);
        existeEpslonFinal.clear();
        estadosFinaisProcesso.clear();
        boolean aceita = percorrePalavra(buscaInicial(), palavra);
        
        //Adiciona todos os estados Finais ao ArrayList
        for(Estado e:afnd.getEstadosView())
            if(e.isEstadoFinal())
                estadosFinais.add(e.getNome());
        
        //Limpa o HashMap
        existe.clear();
        
        if (aceita) {
            boolean loopVEstadosFinais;
            do {
                loopVEstadosFinais = verificaEpslonFinal(estados);
            } while (loopVEstadosFinais);
            
            estadosFinaisProcesso = new ArrayList(new HashSet(estadosFinaisProcesso));
            /*
            for (int i = 0; i < estadosFinaisProcesso.size(); i++) {
                System.out.println("estados finais: " + estadosFinaisProcesso.get(i));
            }
            */
            
            for (int i = 0; i < estadosFinais.size(); i++) {
                if (estadosFinaisProcesso.contains(estadosFinais.get(i))) {
                    String[] linhas = {palavra,"valida"};
                    modelPalavras.addRow(linhas);
                    tbPalavras.setModel(modelPalavras);
                    return;
                } else {
                    String[] linhas = {palavra,"invalida"};
                    modelPalavras.addRow(linhas);
                    tbPalavras.setModel(modelPalavras);
                    return;
                }
            }
        }
        else{
            String[] linhas = {palavra,"invalida"};
            modelPalavras.addRow(linhas);
            tbPalavras.setModel(modelPalavras);
            return;
        }

    } //Fim do run()
    
    // Adiciona as transições no HashMap 
    public void controlaTransicao(Estado estado, Transicao transicao) {
        if (map.containsKey(estado.getNome())) {
            ((ArrayList) map.get(estado.getNome())).add(transicao);
        } else {
            map.put(estado.getNome(), new ArrayList());
            ((ArrayList) map.get(estado.getNome())).add(transicao);
        }

    } //Fim do controlaTransicao()

    // percorre a palavra caracter por caracter verificando os estados atuais e as transições
    public boolean percorrePalavra(Estado inicial, String palavra) {
        //adiciona estado(s) inicial para poder iniciar a verificação
        percorrePalavra = 0;
        estados.add(inicial.getNome());
        // seta tamanho para poder saber se existe epslon no estados
        // percorrendo a palavra
        while (percorrePalavra < palavra.length()) {
            //verifica se o estados atuais tem epslon e atualiza os estados atuais através da função
            //se não tiver 0, percorre os estados e as transições e atualiza os estados
            if (!verificaEpslon(estados)) {
                existe.clear();
                System.out.println("NÃO EXISTE EPSLON");
                // caracter atual 
                setCaracterAtual(palavra.charAt(percorrePalavra));
                System.out.println("Caracter: "+ getCaracterAtual());
                // verifica a existência de transição
                // se não existir transição, para a execução do programa
                if (!verificaCaracter(getCaracterAtual(), estados)) {
                    System.out.println("Não tem transições: " + estados.size());
                    return false;
                }else {
                    System.out.println("Tem transições");
                }
            }  
            estados.forEach((a) -> System.out.println("estados: " + a));
            System.out.println();
        }
        return true;
    }//fim percorrePalavra()

    // verifica se o caracter tem transição dentre os estados atuais
    public boolean verificaCaracter(char recebeCaracter, ArrayList<String> estadosAtuais) {
        // array list auxiliar para receber os novos estados a partir dos caracteres de transições
        ArrayList<String> auxiliar = new ArrayList();
        //percorre os estados atuais 
        for (int percorreEstados = 0; percorreEstados < estadosAtuais.size(); percorreEstados++) {
            //verifica se os estados atuais tem transições
            if (map.containsKey(estadosAtuais.get(percorreEstados))) {
                // array com as transições do estado percorrido
                ArrayList<Transicao> TransicoesEstados = map.get(estadosAtuais.get(percorreEstados));
                // percorre as transições do estado em questão
                for (int percorreTransicoes = 0; percorreTransicoes < TransicoesEstados.size(); percorreTransicoes++) {
                    // verifica se a transição é igual ao caracter de transição
                    if (TransicoesEstados.get(percorreTransicoes).getSimboloConsumido() == recebeCaracter) {
                        //recebe o estado de ida que será um dos novos estados atuais
                        auxiliar.add(TransicoesEstados.get(percorreTransicoes).getDestino().getNome());

                    }
                }
            }
        }

        // incrementa percorre palavra para que possa passar para o próximo caracter
        percorrePalavra++;
        //se auxiliar for 'false' quer dizer que não existe estados possíveis e programa vai retornar 'false' e parar
        if (auxiliar.isEmpty()) {
            return false;
        } else {
            estados = auxiliar;
            return true;
        }
    }// fim verificaCaracter()

    // verifica se os existe epslon para escorregar
    public boolean verificaEpslon(ArrayList<String> estadosAtuais) {
       // auxiliar para saber se os estados tem epslon 
        ArrayList<String> auxiliar = new ArrayList();
        //percorre os estados atuais em busca do epslon
        for (int percorreEstadosAtuais = 0; percorreEstadosAtuais < estadosAtuais.size(); percorreEstadosAtuais++) {
            //verifica se o estado já foi percorrido
            boolean b = existe.containsKey(estadosAtuais.get(percorreEstadosAtuais));
            //verifica se o estado tem transicao e se já foi percorrido
            //evitando o loop infinito
            if ((map.containsKey(estadosAtuais.get(percorreEstadosAtuais))) && b == false) {
                // Array de transicoes do estado atual
                ArrayList<Transicao> transicoes = map.get(estadosAtuais.get(percorreEstadosAtuais));
                // percorre transicoes do estado em busca do epslon
                for (int percorreTransicoes = 0; percorreTransicoes < transicoes.size(); percorreTransicoes++) {
                    // verifica se existe epslon nas transicoes e adiciona o estado de ida aos estados atuais
                    // e marca o estado como percorrido a fim de evitar o loop infinito
                    if (transicoes.get(percorreTransicoes).getSimboloConsumido() == 'E') {
                        auxiliar.add(transicoes.get(percorreTransicoes).getDestino().getNome());
                        existe.put(estadosAtuais.get(percorreEstadosAtuais), true);
                    }

                }

            }
        }
        // verifica se auxiliar recebe alguma coisa, se recebeu é pq existe epslon
        // então ele retorna true para informa que ainda precisa ser verificado a existencia do epslon
        // envia false se não existir epslon
        
        if (auxiliar.isEmpty()) {
            return false;
        } else {
            estados.addAll(auxiliar);
            estados = new ArrayList(new HashSet(estados));
            return true;
        }

    }//Fim do verificaEpslon()

    // verifica se ao final de percorrer a palavra existe epslon
    public boolean verificaEpslonFinal(ArrayList<String> estadosAtuais) {
        // auxiliar para saber se os estados tem epslon 
        ArrayList<String> auxiliar = new ArrayList();
        //verifica a existencia de epslon no estado
        temEpslon = false;
        //percorre os estados atuais em busca do epslon
        for (int percorreEstadosAtuais = 0; percorreEstadosAtuais < estadosAtuais.size(); percorreEstadosAtuais++) {
             //verifica se o estado já foi percorrido
            boolean b = existeEpslonFinal.containsKey(estadosAtuais.get(percorreEstadosAtuais));
            //verifica se o estado tem transicao e se já foi percorrido
            //evitando o loop infinito
            if ((map.containsKey(estadosAtuais.get(percorreEstadosAtuais))) && b == false) {
                // percorre transicoes do estado em busca do epslon
                ArrayList<Transicao> transicoes = map.get(estadosAtuais.get(percorreEstadosAtuais));
                 // verifica se existe epslon nas transicoes e adiciona o estado de ida aos estados atuais
                 // e marca o estado como percorrido a fim de evitar o loop infinito
                for (int percorreTransicoes = 0; percorreTransicoes < transicoes.size(); percorreTransicoes++) {
                    // verifica a existencia de epslon nos estados se existir, adiciona estado de ida aos estados atuais
                    //e seta que foi verificado
                    if (transicoes.get(percorreTransicoes).getSimboloConsumido() == 'E') {
                        auxiliar.add(transicoes.get(percorreTransicoes).getDestino().getNome());
                        existeEpslonFinal.put(estadosAtuais.get(percorreEstadosAtuais), true);
                    }
                }
                // adiciona estado como percorrido, com tem epslon ou não, se tiver epslon ele ainda eh final pq epslon implica que está no estado atual e no estado que escorrega
                // verificado representa um estado que já foi percorrido, ele foi percorrido e adicionado a lista dos ultimos estados
                   estadosFinaisProcesso.add(estadosAtuais.get(percorreEstadosAtuais));
                
            }
            // se ele não conter transições será adicionado aos últimos estados
            else{ 
                estadosFinaisProcesso.add(estadosAtuais.get(percorreEstadosAtuais));
            }
            
        }
        // se auxiliar esta vazio quer dizer que não existem mais estados a percorrer
        //então retorna false
        //se tiver mais estados a serem percorridos, atualiza estados finais e realiza o mesmo procedimento
        if (auxiliar.isEmpty()) {
            return false;
        } else {
           estados = auxiliar;
           estados = new ArrayList(new HashSet(estados));
            return true;
        }

    }//Fim do verificaEpslonFinal()
    
    /*
    public void imprimeTransicao() {
        map.keySet().stream().forEach((entry) -> {
            ArrayList<Transicao> p_index = map.get(entry);
            for (int i = 0; i < p_index.size(); i++) {
                System.out.println(entry + "," + p_index.get(i).getSimboloConsumido() + "," + p_index.get(i).getDestino().getNome());
            }
        });
    }//fim imprimeTransicao()
    */
    
    //Captura todos os simbolos adicionados ...inclusive 'E'(epslon)
    public void gerarAlfabeto(){ 
        for(TabelaTransicao t: tabelaTransicao){
            afnd.addSimbolo(t.getSimboloConsumido());
        }
    } //Fim do gerarAlfabeto()
    
    //Gerar definição formal
    public void tabelaTransicao(){
        
        int letra = 0;
        
        //Recuperar todos simbolos lidos
        gerarAlfabeto();
        
        // Concatenar alfabeto removendo epslon
        String al[] = new String[afnd.getAlfabeto().size()];
        for(Character c: afnd.getAlfabeto()){
            if(c != 'E' && c != null)
                al[letra++] = c + ",";
        }
        
        //Formar conjunto alfabeto
        String alfabetoAFND = "{";
        for (int i = 0; i < al.length; i++) {
            alfabetoAFND += al[i];
        }
        alfabetoAFND += "}";
        
        //Buscar estado inicial
        String inicial = "";
        for(Estado e: afnd.getEstadosView())
            if(e.isEstadoInicial())
                inicial = e.getNome();
            
            
        //Concatenar nome de estados
        String nome = "{";
        for(Estado e: afnd.getEstadosView())
            nome += e.getNome() + ",";
        nome += "}";
        
        //Concatenar estados finais
        String finais = "{";
        for (Estado e: afnd.getEstadosView()) {
            if(e.isEstadoFinal())
                finais += e.getNome() + ",";
        }
        finais += "}";
        
        JPanel estados = new JPanel();
        estados.setLayout(new FlowLayout());
        estados.add(new JLabel("Q:"));
        estados.add(new JLabel(nome));
        
        JPanel alfabeto = new JPanel();
        alfabeto.setLayout(new FlowLayout());
        alfabeto.add(new JLabel("Alfabeto:"));
        alfabeto.add(new JLabel(alfabetoAFND));
        
        JPanel estInicial = new JPanel();
        estInicial.setLayout(new FlowLayout());
        estInicial.add(new JLabel("Q0:"));
        estInicial.add(new JLabel(inicial));
        
        JPanel estFinais = new JPanel();
        estFinais.setLayout(new FlowLayout());
        estFinais.add(new JLabel("Qf:"));
        estFinais.add(new JLabel(finais));
        
        //Prencher título das colunas da tabela
        String[] colunas = new String[afnd.getAlfabeto().size() + 1];
        int pos = 0;
        colunas[pos] = String.format("%c", 167);
        for (Character a : afnd.getAlfabeto()) {
            colunas[++pos] = a.toString();
        }
        
        //Gerar tabela definição formal
        String [][] dados = new String[afnd.getEstadosView().size()][afnd.getAlfabeto().size()+1];
        
        for (int i = 0; i < afnd.getEstadosView().size(); i++) {
            for (int j = 0; j < colunas.length; j++) {
                if(j == 0)
                    dados[i][j] = afnd.getEstadosView().get(i).getNome();
                else{
                    for(TabelaTransicao t: tabelaTransicao){
                        if(afnd.getEstadosView().get(i).getNome().equalsIgnoreCase(t.getOrigem().getNome())){
                            if(colunas[j].charAt(0) == t.getSimboloConsumido()){
                                if(dados[i][j] != null)
                                    dados[i][j] = dados[i][j] + t.getDestino().getNome() + ",";
                                else
                                    dados[i][j] = t.getDestino().getNome() + ",";
                            }else{
                                dados[i][j] = "";
                            }   
                        }
                    }
                }
            }
        }
        
        JFrame p = new JFrame();
        p.setVisible(true);
        p.setTitle("Definição formal");
        p.setSize(400, 400);
        p.setBackground(Color.gray);
        p.setLocationRelativeTo(null);
        //p.setDefaultCloseOperation(EXIT_ON_CLOSE);
        // Cria o painel
        JPanel topPanel = new JPanel();
        topPanel.setLayout(new BorderLayout());
        p.getContentPane().add(topPanel);
        
        // Cria o JTable
        JTable table = new JTable(dados, colunas);
        
        JPanel t = new JPanel();
        t.setLayout(new GridLayout());
        t.add(estInicial);
        t.add(estados);
        t.add(estFinais);
        t.add(alfabeto);
        
        // Confirua  o JTable com JScrollPane e este últino é adicionado dento do JPanel
        JScrollPane scrollPane = new JScrollPane(table);
        topPanel.add(t, BorderLayout.NORTH);
        topPanel.add(scrollPane, BorderLayout.CENTER);
        
    } //Fim do tabelaTransicao()
    
}// Fim da classe Eventos

