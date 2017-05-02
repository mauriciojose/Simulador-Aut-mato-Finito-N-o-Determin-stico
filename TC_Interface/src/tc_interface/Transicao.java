//Definições do pacote
package tc_interface;

/**
 * 
 *  @author Jefferson Ricardo Santos        <jeff.debian2016@gmail.com>
 *  @author José Carlos dos Santos Junior   <juninho_santos19_9@live.com>
 *  @author Mauricio José Miranga Guimarães <mauriciojosemirandaguimaraes@gmail.com>
 *  @author Paulo David Almeida da Silva    <pdavidalmeida@hotmail.com>
 * 
 */

/*********** DEFINIÇÃO DA CLASSE ***********/
/*
 * OS OBJETOS DA CLASSE TRANSICAO RECEBEM O 
 * SIMBOLO E O DESTINO DA TRANSIÇÃO DO AFND 
 * 
 *******************************************/

//Inicio da classe Transicao
public class Transicao {

    //Atributos
    private Estado destino;          //Destino 
    private char simboloConsumido;   //Simbolo consumido na transicao
    
    //Construtor
    public Transicao(char simbolo ,Estado destino){
        setDestino(destino);
        setSimboloConsumido(simbolo);
    }
    
    //Getters
    public Estado getDestino() {
        return destino;
    }

    public char getSimboloConsumido() {
        return simboloConsumido;
    }
    
    //Setters
    public void setDestino(Estado destino) {
        this.destino = destino;
    }

    public void setSimboloConsumido(char simboloConsumido) {
        this.simboloConsumido = simboloConsumido;
    }
    
}
//Fim da classe Transicao