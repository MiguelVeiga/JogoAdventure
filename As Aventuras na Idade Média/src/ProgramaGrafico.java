
/**
Cria um objeto da classe Tela que é usado como parâmetro no construtor da classe jogo.
* @author  Miguel Piedade Veiga
* @version 2022.04.23
    */
public class ProgramaGrafico
{
    public static void main(String[] args){
    {
       Tela tela = new Tela("As Aventuras na Idade Média");
        Jogo jogoGrafico = new Jogo(tela);
        jogoGrafico.jogar();
    }
}
}
