

/**
 * Classe ítem
 * Essa superclasse possui atributos e métodos dos ítem que podem
 * estar no ambiente ou com o jogador.
 * 
 * @author  Miguel Piedade Veiga 
 * @version 2022.04.23
 */
public class Item extends EntidadeGrafica
{
    // variáveis de instância - substitua o exemplo abaixo pelo seu próprio
    private String nome;
    private String descricao;
    private double peso;

    /**
     * Construtor para objetos da classe Item
     */
    public Item(String nome, String descricao,double peso, String caminho){
        super(caminho);
        this.nome = nome;
        this.descricao = descricao;
        this.peso = peso;
        
    }
    /**
        Retorna o nome do ítem.
     */
    public String getNome(){
        return nome;
    }
    /**
        Retorna a descrição do ítem.
     */
    public String getDescricao(){
        return  getNome()+", "+descricao+", peso =  "+getPeso();
    }
    /**
        Retorna o peso do ítem.
     */
    public double getPeso(){
        return peso;
    }    
    /**
        Exibe o nome com o peso do ítem.
     */
    public String exibir(){
        return "* "+nome+" peso="+peso;
    }
     }

