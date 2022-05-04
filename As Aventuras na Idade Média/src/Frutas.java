
/**
 * @author  Miguel Piedade Veiga
 * @version 2022.04.23
 */
public class Frutas extends Item
{
    private String conteudo;
 public Frutas(String nome,String descricao,double peso,String conteudo,String caminho)
    {
    super(nome,descricao,peso, caminho);
    this.conteudo = conteudo;
    
    }
    @Override 
    public String getDescricao(){
        String descricao = super.getDescricao();
        String descricaoLonga;
        descricaoLonga = descricao+", que contém: "+conteudo;
        return descricaoLonga;
    }
    }