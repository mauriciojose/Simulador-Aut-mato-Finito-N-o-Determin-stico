//Definições de pacote
package tc_interface;

/**
 * 
 *  @author Jefferson Ricardo Santos        <jeff.debian2016@gmail.com>
 *  @author José Carlos dos Santos Junior   <juninho_santos19_9@live.com>
 *  @author Mauricio José Miranga Guimarães <mauriciojosemirandaguimaraes@gmail.com>
 *  @author Paulo David Almeida da Silva    <pdavidalmeida@hotmail.com>
 * 
 */

/******************* DEFINIÇÃO DA CLASSE ******************/
/* 
 * OS OBJETOS DA CLASSE TRANSICAO
 * RECEBEM OS DADOS DE CADA TRANSICAO,
 * UM OBJETO DO TIPO ESTADO, PARA ORIGEM, OUTRO 
 * PARA DESTINO, E O SIMBOLO A SER CONSUMIDO NA TRANSICAO
 *
 *********************************************************/

//Inicio da classe TabelaTransicao
public class TabelaTransicao {

    //Atributos
    private Estado origem;           //Origem 
    private Estado destino;          //Destino 
    private char simboloConsumido;   //Simbolo consumido na transicao
    
    //Construtor
    public TabelaTransicao(Estado origem,char simbolo ,Estado destino){
        setOrigem(origem);
        setDestino(destino);
        setSimboloConsumido(simbolo);
    }
    
    //Getters
    public Estado getOrigem(){
        return origem;
    }

    public Estado getDestino() {
        return destino;
    }

    public char getSimboloConsumido() {
        return simboloConsumido;
    }
    
    //Setters
    public void setOrigem(Estado origem) {
        this.origem = origem;
    }

    public void setDestino(Estado destino) {
        this.destino = destino;
    }

    public void setSimboloConsumido(char simboloConsumido) {
        this.simboloConsumido = simboloConsumido;
    }
    
}
//Fim da classe TabelaTransicao
