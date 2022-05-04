


/**
 * 
 * 
 * @author  Miguel Piedade Veiga 
 * @version 2022.04.23
 */
public class Martelo extends Item
{   
    private String material;
    private int forca;
 /**
     * Construtor para objetos da classe martelo
     */
    public Martelo(String nome,String descricao,double peso,String material,String caminho)
    {
    super(nome,descricao,peso,caminho);
    this.material = material;
    forca = 8;
    }
 @Override
      public String getDescricao(){
          String descricao = super.getDescricao();
        String descricaoLonga = descricao+", feito de "+material+" e força = "+forca;
        
        return descricaoLonga;
    }
    }
