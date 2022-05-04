
import java.util.Scanner;

/** 
 * Esse analisador le a entrada do usuario e tenta interpreta-la como um
 * comando "Adventure". Cada vez que eh chamado ele le uma linha do terminal
 * e tenta interpretar a linha como um comando de duas palavras. Ele retorna
 * o comando como um objeto da classe Comando.
 *
 * O analisador tem um conjunto de palavras de comando conhecidas. Ele compara
 * a entrada do usuario com os comandos conhecidos, e se a entrada nao eh um
 * dos comandos conhecidos, ele retorna um objeto comando que eh marcado como
 * um comando desconhecido.
 * 
 * @author  Miguel Piedade Veiga 
 * @version 2022.04.23
 */
public class Analisador 
{
    private PalavrasComando palavrasDeComando;  // guarda todas as palavras de comando validas
    private InterfaceUsuario inter2;
    private static Analisador instanciaUnica;
    
    /**
     * Cria um analisador para ler do terminal.
     */
    private Analisador(InterfaceUsuario inter2) 
    {
        palavrasDeComando = PalavrasComando.getInstancia();
        this.inter2 = inter2;
        
    }
    /**
        método para conseguir instanciar um punico objeto da classe Analisador
     */
    public static Analisador getInstancia(InterfaceUsuario i){
        if (instanciaUnica == null){
            instanciaUnica = new Analisador(i);
        }
        return instanciaUnica;
    }
    
    /**
     * @return O proximo comando do usuario
     */
    public Comando pegarComando() 
    {
        String linha;   // guardara uma linha inteira
        String palavra1 = null;
        String palavra2 = null;

        System.out.print("> ");     // imprime o prompt

        linha = inter2.obterComando();
        System.out.println("");
        // Tenta encontrar ate duas palavras na linha
        Scanner tokenizer = new Scanner(linha);
        if(tokenizer.hasNext()) {
            palavra1 = tokenizer.next();      // pega a primeira palavra
            if(tokenizer.hasNext()) {
                palavra2 = tokenizer.next();      // pega a segunda palavra
                // obs: nos simplesmente ignoramos o resto da linha.
            }
        }

        // Agora verifica se esta palavra eh conhecida. Se for, cria um
        // com ela. Se nao, cria um comando "null" (para comando desconhecido)
        if(palavrasDeComando.ehComando(palavra1)) {
            return new Comando(palavra1, palavra2);
        }
        else {
            return new Comando(null, palavra2); 
        }
    }
    public String getComandos() {
        return PalavrasComando.getComandos();
    }
}
