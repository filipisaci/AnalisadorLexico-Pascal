import java.util.ArrayList;

public class AnalisadorSintaticoAlfa {

    public class ErrosSintaticos{
        public String Mensagem;
        public Integer Linha;

        public ErrosSintaticos() {            
        }
        
        public String getMensagem(){
            return this.Mensagem;
        }
        
        public Integer getLinha(){
            return this.Linha;
        }
    }
    
    public ArrayList<ErrosSintaticos> listaErros = new ArrayList<>();
    
    public void AnalisarSintaxe(ArrayList <String[]> tabelaToken) {
        String instrucao = "";    
        int identificadorToken = 0;
        int proximoIdentificadorToken = 0;
        int aux = 0;
        int aux2 = 0;
        int linha = 0;
        int sequencial = 0;
            
        for (int i = 0; i < tabelaToken.size() - 1; i++) {
            
            linha = Integer.valueOf(tabelaToken.get(i)[2]);            
            sequencial++;
            
            if (sequencial == 1) {
                instrucao = tabelaToken.get(i)[0];
                identificadorToken = Integer.valueOf(tabelaToken.get(i+2)[1]);
                proximoIdentificadorToken = Integer.valueOf(tabelaToken.get(i + 1)[1]);
                ValidaProducao01(instrucao, proximoIdentificadorToken , identificadorToken, linha);
            }
            
            if (sequencial == 4) {
                instrucao = tabelaToken.get(i)[0];
                identificadorToken = Integer.valueOf(tabelaToken.get(i+2)[1]);
                proximoIdentificadorToken = Integer.valueOf(tabelaToken.get(i + 1)[1]);
                aux = Integer.valueOf(tabelaToken.get(i + 3)[1]);
                aux2 = Integer.valueOf(tabelaToken.get(i + 4)[1]);
                ValidaVariavel(instrucao, proximoIdentificadorToken, identificadorToken, aux, aux2, linha);
            }
            
            // valida atribuicao de variavel
            if(tabelaToken.get(i)[1].equals("38")){
                ValidaAtribuicao(tabelaToken.get(i-1)[1], tabelaToken.get(i+1)[1], tabelaToken.get(i+2)[1], linha);
            }
            
        }    
        
        for (ErrosSintaticos item : listaErros) {
            System.out.println(item.getMensagem() + "  " + item.getLinha());
        }
        
    }   
    
    public void ValidaProducao01( String instrucao, int seqClassificacao01, int seqClassificacao02, int linha ){
        
        if (!instrucao.toLowerCase().trim().equals("program") ) {           
            AddErroSintatico("Erro! O programa deve iniciar com o token program na linha: ", linha);
        }
        
        //-- Verifica o nome do programa
        if (seqClassificacao01 != 25) {
            AddErroSintatico("Erro! Esperado o nome do programa na linha:", linha);
        }
        
        //-- Valida ponto e virgula, caracter terminal
        if (seqClassificacao02 != 47) {
            AddErroSintatico("Erro! Esperado o token ; na linha: ", linha);
        }
        
    }
    
    public void ValidaProducao02( String instrucao, int seqClassificacao01, int seqClassificacao02, int linha ){
        
    }
    
    public void ValidaVariavel(String instrucao, int seqClassificacao01, 
            int seqClassificacao02, int seqClassificacao03, int seqClassificacao04, int linha ){
        
        if ( !instrucao.toLowerCase().trim().equals("var")) {
            AddErroSintatico("Erro de sintax (var) na linha: ", linha);
        }
        
        //-- Valida label ID
        if (seqClassificacao01 != 25) {
            AddErroSintatico("Erro de sintax (var) na linha: ", linha);
        }
        
        //-- Valida operador
        if (seqClassificacao02 != 39) {
            AddErroSintatico("Erro de sintax (var) na linha: ", linha);
        }
        
        if (seqClassificacao03 != 8) {
            AddErroSintatico("Erro de sintax (var) na linha: ", linha);
        }
        
        if (seqClassificacao04 != 47) {
            AddErroSintatico("Erro de sintax (var) na linha: ", linha);
        }
        
    }
    
    public void ValidaAtribuicao(String anterior, String proximo, String terminador, Integer linha) {
        if(!anterior.equals("25")) {
            AddErroSintatico("atribuicao", linha);
        }
        if(!(proximo.equals("48") || proximo.equals("26") || proximo.equals("36"))){
            AddErroSintatico("atribuicao", linha);
        }
        
        if(!terminador.equals("47") && !terminador.equals("30") && !terminador.equals("31")
                && !terminador.equals("32") && !terminador.equals("33")){
            AddErroSintatico("atribuicao", linha);
        }
    }
        
    public void AddErroSintatico(String mensagem, int linha){
        ErrosSintaticos errosSintaticos  = new ErrosSintaticos();
        errosSintaticos.Mensagem = mensagem;
        errosSintaticos.Linha = linha;
        listaErros.add(errosSintaticos);
    }
}
