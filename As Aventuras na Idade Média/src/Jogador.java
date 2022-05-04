
import java.util.ArrayList;

/**
 * Classe Jogador.
 * Possui atributos e métodos referentes ao jogador.
 * 
 * @author  Miguel Piedade Veiga 
 * @version 2022.04.23
 */
public class Jogador

{
     private ArrayList<Item> listaItem;
    

    /**
     * Construtor para objetos da classe Jogador
     */
    public Jogador()
    {
        listaItem = new ArrayList<>();
       
    }
       /**
     Retorna o peso da mochila do jogador.
     */
    private double somarPeso(){
        double soma=0;
         for(Item i: listaItem){
             soma += i.getPeso();
             }
            return soma;
    }
         /**
     Retorna true se a mochila estiver com peso abaixo de 10 e false se estiver maior que 10.
     */
     public boolean controleMochila(double peso){
        
        double pesoTotal = peso + somarPeso();
        if (pesoTotal>10){
            return false;
        }
        else{
            return true;
        }
    }
        /**
     Retorna os ítens que está com o jogador e o peso total.
     */
    public String listarItens(){
        String itens = "";
        if (listaItem.isEmpty()){
            return "Você não possui nenhum ítem";
            
        }
        String desc = "Você possui:\n";
        for(Item i: listaItem){
            itens += i.exibir()+"\n";
            
        }
       
        itens += ("Total: "+somarPeso());
        return itens;
    }
         /**
     Adiciona o ítem na mochila do jogador.
     */
    public void adicionarItem(Item item){
        listaItem.add(item);
    }
         /**
     Remove o ítem da mochila do jogador.
     */
    public void removerItem (Item item){
           listaItem.remove(item);
           
        }
             /**
     Consulta se tem o ítem na mochila do jogador.
     */
    public Item contemItem(String chave){
             for(Item i : listaItem){
            if(i.getNome().toLowerCase().equals(chave.toLowerCase())){
                return i;
                }
         }
            return null;
            }
        
    }

