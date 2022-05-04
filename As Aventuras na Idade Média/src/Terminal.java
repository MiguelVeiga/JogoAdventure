
import java.util.Scanner;
/**
 * Classe terminal usada para o usuário jogar via terminal.
 * Sobreescreve os métodos da interfaceInterfaceUsuario.
 * 
 * @author Miguel Piedade Veiga
 * @version 2022.04.23
 */
public class Terminal implements InterfaceUsuario
{
    // variáveis de instância - substitua o exemplo abaixo pelo seu próprio
    Scanner entrada;
    /**
     * Construtor para objetos da classe Terminal
     */
    public Terminal()
    {
        entrada = new Scanner(System.in);
        
    }
    /**
     * Exibe a mensagem para o usuário
     */
    @Override
    public void exibirMensagem(String mensagem){
        System.out.println(mensagem);
    }
    /**
     * Exibe a mensagem para o usuário sem apagar as mensagens anteriores
     */
     @Override
    public void continuarMensagem(String mensagem){
        System.out.println(mensagem);
    }
    /**
     * pega o comando do usuário
     */
     @Override
    public String obterComando(){
        String resposta = entrada.nextLine();
        return resposta;
    }
    /**
     * Exibe uma instrução e depois pega o comando do usuário
     */
     @Override
    public String obterInformacao(String instrucao){
        System.out.println(instrucao);
        return obterComando();
    }
    
    public void jogadorPegouItem(EntidadeGrafica item){
        
    }
    
    public void ambienteAtualMudou(EntidadeGrafica ambiente){
        
    }
     
    public void jogadorDescartouItem(EntidadeGrafica item){
        
    }
}
