//Definições de pacote
package tc_interface;

//Importações
import java.util.ArrayList;
import java.util.TreeSet;

/**
 * 
 *  @author Jefferson Ricardo Santos        <jeff.debian2016@gmail.com>
 *  @author José Carlos dos Santos Junior   <juninho_santos19_9@live.com>
 *  @author Mauricio José Miranga Guimarães <mauriciojosemirandaguimaraes@gmail.com>
 *  @author Paulo David Almeida da Silva    <pdavidalmeida@hotmail.com>
 * 
 */

/***************** DEFINIÇÃO DA CLASSE ******************/
/*
 * OS OBJETOS DA AUTOMATOS RECEBEM OS DADOS QUE SERÃO
 * UTILIZADOS VISUALMENTE, ALÉM DO ALFABETO DO AUTOMATO
 *	
 ********************************************************/
 
//Inicio da classe Automatos
public class Automatos {

    //Atributos
    private TreeSet <Character> alfabeto; 
    private ArrayList <Estado> estadosView;
    
    //Construtor
    public Automatos(){
        estadosView = new ArrayList < > (); 
        alfabeto    = new TreeSet   < > ();
    }
    
    //Getters
    public TreeSet<Character> getAlfabeto() {
        return alfabeto;
    }

    public ArrayList<Estado> getEstadosView() {
        return estadosView;
    }
    
    //Setters
    public void setAlfabeto(TreeSet<Character> alfabeto) {
        this.alfabeto = alfabeto;
    }
	
    public void setEstadosView(ArrayList<Estado> estadosView){
	this.estadosView = estadosView;
    }
	
	//Métodos
    /**
     * @param isInicial*
     * @param isFinal********************************/
    public String addEstado(boolean isInicial,int id, boolean isFinal){			//Adiciona Estado
        estadosView.add(new Estado(isInicial,id,isFinal));
        return "q" + id; 
    }
    
    public void addSimbolo(char e){                                                     //Adiciona todos os simbolos 
        alfabeto.add(e);								//decorrentes de transições criadas
    }
	
}
//Fim da classe Automatos