
/**
 * @author  Miguel Piedade Veiga 
 * @version 2022.04.23
 */
public class Chave extends Item

{
 private String funcao;
 public Chave(String nome,String descricao,double peso,String caminho)
    {
    super(nome,descricao,peso, caminho);
    funcao = "pode ser usada para abrir algo";
    }
 @Override
    public String getDescricao(){
      String descricao = super.getDescricao();
        String descricaoLonga = descricao+", "+funcao;
        
        return descricaoLonga;
    }
    }