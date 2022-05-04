
import java.util.ArrayList;
import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.FileReader;

/**
 *  Essa é a classe principa do jogo "As Aventuras do Filho Perdido".
 * 
 *  Para jogar esse jogo, crie uma instancia dessa classe e chame o metodo
 *  "jogar".
 * 
 *  Essa classe principal cria e inicializa todas as outras: ela cria os
 *  ambientes, cria o analisador e comeca o jogo. Ela tambeme avalia e 
 *  executa os comandos que o analisador retorna.
 * 
* @author  Miguel Piedade Veiga 
 * @version 2022.04.23
 */

public class Jogo 
{
    private Analisador analisador;
    private Ambiente ambienteAtual;
    private Jogador jogador;      
    private int contAmbientes = 0;
    private InterfaceUsuario inter;
    private Martelo martelo;
    private Chave chave;
    private Item cerveja;
    private Item caixa;
    private Frutas frutas;
    private Ambiente floresta, caverna, castelo, feira, calabouco, taberna, arena, montanha, ferreiro, prisao;
    /**
     * Cria o jogo e incializa seu mapa interno.
     * Cria um ArrayList de Itens
     */
    public Jogo(InterfaceUsuario inter) 
    {
        this.inter = inter;
        criarAmbientes();
        analisador = Analisador.getInstancia(inter);
        jogador = new Jogador();
    }

    /**
     * Cria todos os ambientes e ítens e liga as saidas deles
     */
    private void criarAmbientes()
    {
        
      
        // cria os ambientes
        floresta = new Ambiente("na Floresta","imagens/floresta.jpg");
        caverna = new Ambiente("na Caverna","imagens/caverna.jpg"); 
        castelo = new Ambiente("no Castelo","imagens/castelo.jpg");
        frutas = new Frutas("Frutas", "Uma sacola de frutas",3,"maçã,banana e laranja","imagens/frutas.jpg");
        feira = new Ambiente("na Feira","imagens/feira.jpg", frutas);
        caixa = new Item ("Caixa", " A caixa pode ser destruida",5,"imagens/caixa.jpg");
        calabouco = new Ambiente("no Calabouço","imagens/calabouco.jpg",caixa);
        cerveja = new Item("Cerveja", "Cerveja medieval",4,"imagens/cerveja.jpg");
        taberna = new Ambiente("na Taberna","imagens/taberna.jpg",cerveja );
        arena = new Ambiente("na Arena de Batalha","imagens/arena.jpg");
        montanha = new Ambiente("na Montanha","imagens/montanha.jpg");
        martelo = new Martelo("Martelo"," Um martelo comum",7,"aço","imagens/martelo.jpg");
        ferreiro = new Ambiente("no Ferreiro","imagens/ferreiro.jpg",martelo); 
        prisao = new Ambiente("na Prisão","imagens/prisao.jpg");
        
        
        // inicializa as saidas dos ambientes
        prisao.ajustarSaidas("sul",arena);
        
        
        arena.ajustarSaidas("leste",ferreiro);
        arena.ajustarSaidas("sul",floresta);
        arena.ajustarSaidas("norte",prisao);
        
        floresta.ajustarSaidas("leste", feira);
        floresta.ajustarSaidas("norte", arena);
        floresta.ajustarSaidas("sul",caverna);
        
        caverna.ajustarSaidas("cima",montanha);
        caverna.ajustarSaidas("leste", taberna);
        caverna.ajustarSaidas("norte", floresta);
        
        montanha.ajustarSaidas("baixo", caverna);
        
        ferreiro.ajustarSaidas("sul", feira);
        ferreiro.ajustarSaidas("oeste", arena);
        
        feira.ajustarSaidas("norte", ferreiro);
        feira.ajustarSaidas("oeste", floresta);
        feira.ajustarSaidas("leste", castelo);
        feira.ajustarSaidas("sul", taberna);
        
        castelo.ajustarSaidas("oeste", feira);
        castelo.ajustarSaidas("baixo", calabouco);
        
        calabouco.ajustarSaidas("cima", castelo);
        
        taberna.ajustarSaidas("norte", feira);
        taberna.ajustarSaidas("oeste", caverna);
        
        ambienteAtual = floresta;  // o jogo comeca na floresta
        inter.ambienteAtualMudou(ambienteAtual);
    }

    /**
     *  Rotina principal do jogo. Fica em loop ate terminar o jogo.
     */
    public void jogar() 
    {            
        imprimirBoasVindas();

        // Entra no loop de comando principal. Aqui nos repetidamente lemos
        // comandos e os executamos ate o jogo terminar.
                
        boolean terminado = false;
        while (! terminado) {
            Comando comando = analisador.pegarComando();
            terminado = processarComando(comando);
        }
        
        inter.continuarMensagem("Obrigado por jogar. Ate mais!");
    }

    /**
     * Imprime a mensagem de abertura para o jogador.
     */
    private void imprimirBoasVindas()
    {
        inter.exibirMensagem("");
        inter.exibirMensagem("Bem-vindo ao Jogo");
        inter.continuarMensagem("Você está na Idade Média.");
        inter.continuarMensagem("Digite 'ajuda' se voce precisar de ajuda.");
        inter.continuarMensagem("");
        
        imprimirSaida();
    }
    private void imprimirSaida(){
        inter.exibirMensagem("Você esta " + ambienteAtual.getDescricao());
        inter.continuarMensagem("Saidas:\n" + ambienteAtual.getSaidas());
        }
    /**
     * Dado um comando, processa-o (ou seja, executa-o)
     * @param comando O Comando a ser processado.
     * @return true se o comando finaliza o jogo.
     */
    private boolean processarComando(Comando comando) 
    {
        boolean querSair = false;

        if(comando.ehDesconhecido()) {
            inter.continuarMensagem("Eu nao entendi o que voce disse...");
            return false;
        }

        String palavraDeComando = comando.getPalavraDeComando();
        if (palavraDeComando.equals("ajuda")) {
            imprimirAjuda();
        }
        else if (palavraDeComando.equals("ir")) {
            irParaAmbiente(comando);
        }
        else if (palavraDeComando.equals("sair")) {
            querSair = sair(comando);
        }
        else if (palavraDeComando.equals("observar")){
            observar(comando);
        }
        else if (palavraDeComando.equals("pegar")){
            pegarItem(comando);
        }
        else if (palavraDeComando.equals("listar")){
            listarItens();
        }
        else if (palavraDeComando.equals("largar")){
            largar(comando);
        }
        else if (palavraDeComando.equals("usar")){
            querSair = usarItem(comando);
        }
        return querSair;
    }
    /**
     * Observa qual item tem no local e mostra sua descricao.
     */
    private void observar(Comando comando){
        imprimirSaida();
        inter.continuarMensagem("Item no local: ");
        inter.continuarMensagem(ambienteAtual.getDescricaoLonga());
    }

    // Implementacoes dos comandos do usuario

    /**
     * Printa informacoes de ajuda.
     * Aqui nos imprimimos instruções e a lista de 
     * palavras de comando
     */
    private void imprimirAjuda() 
    {
       inter.exibirMensagem("Você está na Idade Média. Você precisa libertar seu pai da prisão");
       inter.continuarMensagem("Sua mochila não suporta um peso maior que 10");
       inter.continuarMensagem("Suas palavras de comando sao:");
       inter.continuarMensagem("   " + analisador.getComandos());
    }

    /** 
     * Tenta ir em uma direcao. Se existe uma saida entra no 
     * novo ambiente, caso contrario imprime mensagem de erro.
     */
    private void irParaAmbiente(Comando comando) 
    {
        if(!comando.temSegundaPalavra()) {
            // se nao ha segunda palavra, nao sabemos pra onde ir...
            inter.continuarMensagem("Ir pra onde?");
            return;
        }

        String direcao = comando.getSegundaPalavra();

        // Tenta sair do ambiente atual
        Ambiente proximoAmbiente = ambienteAtual.getSaida(direcao);
        

        if (proximoAmbiente == null) {
            inter.continuarMensagem("Nao ha passagem!");
        }
        else {
            ambienteAtual = proximoAmbiente;
            inter.ambienteAtualMudou(ambienteAtual);
            imprimirSaida();
            salvarAmbientes();
        }
    }

    /** 
     * "Sair" foi digitado. Verifica o resto do comando pra ver
     * se nos queremos realmente sair do jogo.
     * @return true, se este comando sai do jogo, false, caso contrario
     */
    private boolean sair(Comando comando) 
    {
        if(comando.temSegundaPalavra()) {
            inter.continuarMensagem("Sair o que?");
            return false;
        }
        else {
            return true;  // sinaliza que nos queremos sair
        }
    }
    /**
     * Se o item informado estiver no local, pega o item. Se não estiver, imprime4
     * mensagem de erro. Se a mochila não suportar o peso, não realiza a ação.
     */
    private void pegarItem(Comando comando){
        if(!comando.temSegundaPalavra()){
            inter.continuarMensagem("Pegar o que?");
            return;
        }
        String pegar = comando.getSegundaPalavra();
        if(ambienteAtual.temItem()){
        if (pegar.toLowerCase().equals(ambienteAtual.consultaItem().toLowerCase())){
            if (jogador.controleMochila(ambienteAtual.retornaItem().getPeso())){
                inter.continuarMensagem("Você pegou "+ ambienteAtual.consultaItem());
                inter.jogadorPegouItem(ambienteAtual.retornaItem());
                jogador.adicionarItem(ambienteAtual.retornaItem());
                ambienteAtual.removeItem();   
        }
        else{
            inter.continuarMensagem("Sua mochila não suporta tanto peso!");
        }
        }
        else{
            inter.continuarMensagem("Esse ítem não foi identificado");
        }
    }
        else{
            inter.continuarMensagem("Não há ítem nesse local");
        }
    }
   
    /**
     * Lista os item do jogador que estão na mochila e informam o peso total
     */
    private void listarItens(){
       inter.exibirMensagem(jogador.listarItens());
    }
    /**
     * Larga o objeto que está com você somente se não há objeto no ambiente.
     */
    private void largar(Comando comando){
        if(!comando.temSegundaPalavra()){
            inter.continuarMensagem("Largar o que?");
            return;
        }
        String largar = comando.getSegundaPalavra();
        if(jogador.contemItem(largar)!= null){
            Item i = jogador.contemItem(largar);
            if(ambienteAtual.largarItem(i)){
                   jogador.removerItem(i);
                    inter.continuarMensagem("Você largou "+i.getNome());
                   inter.jogadorDescartouItem(ambienteAtual.retornaItem());
                    return;
               }
             else{
                 inter.continuarMensagem("O ambiente ja possui um ítem");
                 return;
             }
           }
        inter.continuarMensagem("Você não possui esse ítem");
    }
    
    /**
     * Usa o item informado para realizar uma ação. Alguns itens não fazem nada quando usados,
     * outros são usados para realizar missões no jogo
     */
    private boolean usarItem (Comando comando){
         if(!comando.temSegundaPalavra()){
            inter.continuarMensagem("Usar o que?");
            return false;
            }
            String usar = comando.getSegundaPalavra();
            if(jogador.contemItem(usar) != null){
                Item itemUsar = jogador.contemItem(usar);
            
            if(itemUsar == martelo){
                return usarMartelo();
                }
                else if(itemUsar == cerveja){
                    return usarCerveja();
                }
                else if(itemUsar == chave){
                    return usarChave();
                    }
                else{
                    inter.continuarMensagem("Você não pode usar esse item aqui");
                    return false;
                }
            }
                inter.continuarMensagem("Você não possui esse ítem");
                 return false;
               }
        /**
        salva quantos ambientes o jogador entrou em um arquivo de texto. Caso haja algum erro, é exibida a mensagem de erro.
     */
        public void salvarAmbientes() {
            try {
         FileWriter arq = new FileWriter("pontuacao.txt");
        contAmbientes++;
        arq.write("O jogador entrou em "+contAmbientes+ " ambientes. Pontuação: "+contAmbientes);
        arq.close();
    }
     catch (Exception e) {
        inter.exibirMensagem(e.getMessage());
        }
    }
     /**
        Lê no arquivo de texto quantos ambiente o jogador entrou e exibe no fim do jogo, caso o jogador vença.
     */
    public void lerAmbientes() {
         try {
             try (BufferedReader arq = new BufferedReader(new FileReader("pontuacao.txt"))) {
                String linha = arq.readLine();
                inter.continuarMensagem(linha);
            }
            }
            catch (Exception e) {
            inter.exibirMensagem(e.getMessage());
            }
         }   
          /**
        método para usar o martelo.
     */
    public boolean usarMartelo(){
     if(ambienteAtual.retornaItem() == caixa){
                    inter.continuarMensagem("Você destruiu a caixa");
                    ambienteAtual.removeItem();
                    chave = new Chave("Chave", "Uma chave preciosa",4,"imagens/chave.jpg");
                    ambienteAtual.largarItem(chave);
                    return false;
                    }
                    inter.continuarMensagem("Você não pode usar esse item aqui");
                    return false;
                }
    /**
        método para usar a cerveja.
     */
    public boolean usarCerveja(){
        inter.continuarMensagem("\nVocê não pode beber durante a sua aventura.");
        inter.continuarMensagem("Você perdeu o jogo.");
        return true;
       }
           /**
        método para usar a chave.
     */
    public boolean usarChave(){
       if(ambienteAtual == prisao){
                 inter.exibirMensagem("Você conseguiu libertar seu pai.");
                inter.continuarMensagem("Você ganhou o jogo.");
                lerAmbientes();
                return true;
            }
                inter.continuarMensagem("Você não pode usar esse item aqui");
                return false;
                    }
}
        
    


