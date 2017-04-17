/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Transicao;

import Estado.Estado;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;

/**
 *
 * @author Mauricio José
 */
public class ControleTransicao {

    static HashMap<String, ArrayList<Transicao>> map = new HashMap();
    static HashMap<String, Boolean> existe = new HashMap();
    static HashMap<String, Boolean> existeEpslonFinal = new HashMap();
    
    Transicao transicao;
    
    Scanner input = new Scanner(System.in);
    String cont;
    
    //ArrayList<String> verificado = new ArrayList();
    ArrayList<String> estadosFinais = new ArrayList();
    
    //ArrayList<String> estadosFinaisAux = new ArrayList();
    
    ArrayList<String> estados = new ArrayList();
    ArrayList<String> estadosFinaisProcesso = new ArrayList();
    
    boolean temEpslon = false;

    int percorrePalavra = 0;

    private char caracterAtual;

    public void run() {
        existe.put("%", Boolean.TRUE);
        controlaTransicao(new Estado("0"), new Transicao('E', new Estado("1")));
        controlaTransicao(new Estado("0"), new Transicao('E', new Estado("3")));
        //controlaTransicao(new Estado("1"), new Transicao('E', new Estado("4")));
        //controlaTransicao(new Estado("2"), new Transicao('b', new Estado("3")));
        //controlaTransicao(new Estado("3"), new Transicao('E', new Estado("4")));
        
        controlaTransicao(new Estado("1"), new Transicao('a', new Estado("2")));
        controlaTransicao(new Estado("2"), new Transicao('E', new Estado("1")));
        controlaTransicao(new Estado("2"), new Transicao('E', new Estado("3")));
        controlaTransicao(new Estado("3"), new Transicao('E', new Estado("4")));
        controlaTransicao(new Estado("4"), new Transicao('E', new Estado("5")));

        controlaTransicao(new Estado("4"), new Transicao('E', new Estado("7")));
        controlaTransicao(new Estado("5"), new Transicao('b', new Estado("6")));
        controlaTransicao(new Estado("6"), new Transicao('E', new Estado("5")));
        controlaTransicao(new Estado("6"), new Transicao('E', new Estado("7")));
        //controlaTransicao(new Estado("4"), new Transicao('E', new Estado("5")));
        //controlaTransicao(new Estado("5"), new Transicao('E', new Estado("6")));
        //estadosFinais.add("1");
        estadosFinais.add("7");

        System.out.println("Digite a palavra: ");
        String palavra = input.nextLine();
        Estado e = new Estado("0");
        boolean aceita = percorrePalavra(e, palavra);
        
        ///verificado = estados;
        existe.clear();
        if (aceita) {
            boolean loopVEstadosFinais;
            do {
                
                loopVEstadosFinais = verificaEpslonFinal(estados);

            } while (loopVEstadosFinais);
            
            estadosFinaisProcesso = new ArrayList(new HashSet(estadosFinaisProcesso));

            for (int i = 0; i < estadosFinaisProcesso.size(); i++) {
                System.out.println("estados finais: " + estadosFinaisProcesso.get(i));
            }
            
            for (int i = 0; i < estadosFinais.size(); i++) {
                if (estadosFinaisProcesso.contains(estadosFinais.get(i))) {
                    System.out.println("Palavra VÁLIDA! ESTADO: "+estadosFinais.get(i));
                    break;
                } else {
                    System.out.println("Palavra INVÁLIDA");
                }
            }
        }
        else{System.out.println("Palavra INVÁLIDA FALTA TRANSIÇÃO");}

    }//fim run()
    
    // Adiciona as transições no HashMap 
    public void controlaTransicao(Estado estado, Transicao transicao) {
        if (map.containsKey(estado.estado)) {
            ((ArrayList) map.get(estado.estado)).add(transicao);
        } else {
            map.put(estado.estado, new ArrayList());
            ((ArrayList) map.get(estado.estado)).add(transicao);
        }

    }//fim controlaTransicao()

    // percorre a palavra caracter por caracter verificando os estados atuais e as transições
    public boolean percorrePalavra(Estado inicial, String palavra) {
        //adiciona estado(s) inicial para poder iniciar a verificação
        estados.add(inicial.estado);
        // seta tamanho para poder saber se existe epslon no estados
        //setTamanhoAux(-1);
        // percorrendo a palavra
        while (percorrePalavra < palavra.length()) {
            //verifica se o estados atuais tem epslon e atualiza os estados atuais através da função
            //se não tiver 0, percorre os estados e as transições e atualiza os estados
            
            if (!verificaEpslon(estados)) {
                existe.clear();
                // caracter atual 
                setCaracterAtual(palavra.charAt(percorrePalavra));
                System.out.println("Caracter: "+ getCaracterAtual());
                // verifica a existência de transição
                // se não existir transição, para a execução do programa
                if (!verificaCaracter(getCaracterAtual(), estados)) {
                    System.out.println("Não tem transições: " + estados.size());
                    return false;
                } else {
                    System.out.println("Tem transições");
                }

            } 
            // 
            //setTamanhoAux(verificaEpslon(estados));
            
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
                    if (TransicoesEstados.get(percorreTransicoes).caracterTransicao == recebeCaracter) {
                        //recebe o estado de ida que será um dos novos estados atuais
                        auxiliar.add(TransicoesEstados.get(percorreTransicoes).estadoIda.estado);

                    }
                }

            }/* else {
                return 0;
            }*/

        }

        // incrementa percorre palavra para que possa passar para o próximo caracter
        percorrePalavra++;
        //se auxiliar for 0 quer dizer que não existe estados possíveis e programa vai retornar 0 e parar
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
                    if (transicoes.get(percorreTransicoes).caracterTransicao == 'E') {
                        auxiliar.add(transicoes.get(percorreTransicoes).estadoIda.estado);
                        existe.put(estadosAtuais.get(percorreEstadosAtuais), true);
                    }

                }

            }
        }
        // verifica se auxiliar recebe alguma coisa, se recebeu eh pq existe epslon
        // então ele retorna 1 para informa que ainda precisa ser verificado a existencia do epslon
        // envia 0 se não existir epslon
        
        if (auxiliar.isEmpty()) {
            return false;
        } else {
            estados.addAll(auxiliar);
            estados = new ArrayList(new HashSet(estados));
            return true;
        }

    }//fim verificaEpslon()

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
                    if (transicoes.get(percorreTransicoes).caracterTransicao == 'E') {
                        auxiliar.add(transicoes.get(percorreTransicoes).estadoIda.estado);
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
        // se auxiliar for zero quer dizer que não existem mais estados a percorrer
        //então retorna 0
        //se tiver mais estados a serem percorridos, atualiza estados finais e realiza o mesmo procedimento
        if (auxiliar.isEmpty()) {
            return false;
        } else {
           estados = auxiliar;
           estados = new ArrayList(new HashSet(estados));
            return true;
        }

    }//fim verificaEpslonFinal()
   
    public void imprimeTransicao() {
        map.keySet().stream().forEach((entry) -> {
            ArrayList<Transicao> p_index = map.get(entry);
            for (int i = 0; i < p_index.size(); i++) {
                System.out.println(entry + "," + p_index.get(i).caracterTransicao + "," + p_index.get(i).estadoIda.estado);
            }
        });
    }//fim imprimeTransicao()
    
    //MÉTODOS GETTERS E SETTERS
   
    public char getCaracterAtual() {
        return caracterAtual;
    }

    public void setCaracterAtual(char caracterAtual) {
        this.caracterAtual = caracterAtual;
    }

    public static void main(String[] args) {
        new ControleTransicao().run();
    }
}
