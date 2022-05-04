
/**
        Cria um objeto da classe terminal que é usado como parâmetro no construtor da classe jogo.
        * @author  Miguel Piedade Veiga 
        * @version 2022.04.23
     */
   public class Programa {
     public static void main(String[] args) {
        Terminal terminal = new Terminal();
        Jogo jogo = new Jogo(terminal);
        jogo.jogar();
    }
}
