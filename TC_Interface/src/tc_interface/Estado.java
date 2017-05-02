//	Definições de pacote
package tc_interface;

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
 * OS OBJETOS DA CLASSE ESTADO RECEBEM OS 
 * DADOS DE CADA ESTADO QUE FOI CRIADO NO AFND
 *	
 ************************************************/

//Inicio da classe Estado
public class Estado {
    
    //Atributos 
    private int id;          			    //id para auxiliar no nome do estado
    private String nome;                            //nome do estado
    private boolean estadoFinal;                    //identificação de estado Final
    private boolean estadoInicial;                  //identificação de estado Inicial
    
    //Construtor
    public Estado(boolean estadoInicial,int id, boolean estadoFinal){
        setNome(id);
        setEstadoFinal(estadoFinal);
        setEstadoInicial(estadoInicial);
    }
    
    //Getters
    public int getId() {
        return id;
    }
	
	public String getNome() {
        return nome;
    }
	
    public boolean isEstadoFinal() {
        return estadoFinal;
    }
	
    public boolean isEstadoInicial() {
        return estadoInicial;
    }
   
    //Setters
    public void setId(int id) {
        this.id = id;
    }
	
    public void setNome(int id) {
        this.nome = "q"+(id);
    }
		
	public void setEstadoFinal(boolean estadoFinal) {
        this.estadoFinal = estadoFinal;
    }	
		
    public void setEstadoInicial(boolean estadoInicial) {
        this.estadoInicial = estadoInicial;
    }
}
//Fim da classe Estado