

import java.util.HashMap;
public class Ambiente extends EntidadeGrafica
/**

 *
 * Um "Ambiente" representa uma localizacao no cenario do jogo. Ele eh
 * conectado aos outros ambientes atraves de saidas. As saidas sao
 * nomeadas como norte, sul, leste , oeste, cima e baixo. Para cada direcao, o ambiente
 * guarda uma referencia para o ambiente vizinho, ou null se nao ha
 * saida naquela direcao.
 * 
 * @author  Miguel Piedade Veiga 
 * @version 2022.04.23
 */
{
    private String descricao;
    private HashMap<String, Ambiente> saidas;
    private Item item;
    
    
    public Ambiente getSaida(String direcao){
    return saidas.get(direcao);
    }
    
    /**
     * Cria um ambiente com a "descricao" passada. Inicialmente, ele
     * nao tem saidas. "descricao"
     */
    public Ambiente(String descricao, String caminho) 
    {
        super(caminho);
        this.descricao = descricao;
        saidas = new HashMap<String, Ambiente>();
    }
    
    public Ambiente(String descricao,String caminho , Item item){
        this(descricao, caminho);
        this.item = item;
    }
    
    /**
     * Define as saidas do ambiente. Cada direcao ou leva a um
     * outro ambiente ou eh null (nenhuma saida para la).
     * @param norte A saida norte.
     * @param leste A saida leste.
     * @param sul A saida sul.
     * @param oeste A saida oeste.
     */
    public void ajustarSaidas(String direcao, Ambiente ambiente) 
    {
        saidas.put(direcao, ambiente);
    }

    /**
     * @return A descricao do ambiente.
     */
    public String getDescricao()
    {
        return descricao;
    }
    /**
        Retorna a descrição do ambiente com o item, caso o ambiente tenha um ítem.
     */
    public String getDescricaoLonga(){
        
        String desc = "";
        if(temItem()){
            //desc += item.getNome()+", "+ item.getDescricao()+", peso = "+item.getPeso() ;
            desc = item.getDescricao();
        }
        else{
            desc += "Não há nada aqui";
        }
        return desc;
    }
    /**
    Retorna todas as saídas que o ambiente possui.
     */
    public String getSaidas(){
        String saidasTexto = "";
        for(String direcao : saidas.keySet()) {
            saidasTexto = saidasTexto + direcao + "\n";
        }
        return saidasTexto;
    }
    /**
        Verifica se tem ítem no ambiente.
     */
    public boolean temItem(){
        if (item != null){
            return true;
        }
        else{
            return false;
        }
    }
    /**
        Retorna o nome do ítem se o ambiente tiver item.
     */
    public String consultaItem(){
        if(temItem()){
            return item.getNome();
        }
        else{
            return "";
        }
    }
    /**
        Remove o item do ambiente.
     */
    public void removeItem(){
        item = null;
        
    }
    /**
        Retorna o ítem do ambiente
     */
    public Item retornaItem(){
        return item;
    }
    /**
        Larga o ítem no ambiente, se o ambiente não tiver ítem. 
        Retorna true se conseguiu largar
     */
    public boolean largarItem(Item item){
        if (temItem()){
            return false;
        }
        else{
        this.item = item;
        return true;
    }
    }
    @Override
     public String getNome(){
         return descricao;
     }
    }
    

