

/** 
 * Essa classe guarda uma enumeracao de todos os comandos conhecidos do
 * jogo. Ela eh usada no reconhecimento de comandos como eles sao digitados.
 *
 * * @author  Miguel Piedade Veiga 
 *   @version 2022.04.23
 */

public class PalavrasComando
{
    // um vetor constante que guarda todas as palavras de comandos validas
    private static final String[] comandosValidos = {
        "ir", "sair", "ajuda", "observar", "pegar","listar","largar","usar"
    };
    private static PalavrasComando instanciaUnica; 
        
    public static String getComandos() {
        String comandos = "";
     for (String comando : comandosValidos){
         comandos += comando + " ";
     }
     return comandos;
    }

    /**
     * Construtor - inicializa as palavras de comando.
     */
    private PalavrasComando()
    {
        // nada a fazer no momento...
    }
    /**
       Método para conseguir instanciar apenas um objeto da classe PalavrasComando.
     */
    public static PalavrasComando getInstancia(){
        if (instanciaUnica == null){
            instanciaUnica = new PalavrasComando();
        }
        return instanciaUnica;
    }
    
    /**
     * Verifica se uma dada String eh uma palavra de comando valida. 
     * @return true se a string dada eh um comando valido,
     * false se nao eh.
     */
    public boolean ehComando(String umaString)
    {
        for(int i = 0; i < comandosValidos.length; i++) {
            if(comandosValidos[i].equals(umaString))
                return true;
        }
        // se chegamos aqui, a string nao foi encontrada nos comandos.
        return false;
    }
}
