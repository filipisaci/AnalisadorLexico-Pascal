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
        String valorToken = "";    
        int identificadorToken = 0;
        int proximoIdentificadorToken = 0;
        int linha = 0;
        int sequencial = 0;
        
        for (int i = 0; i < tabelaToken.size() - 1; i++) {
            proximoIdentificadorToken = 0;
            valorToken = tabelaToken.get(i)[0];
            identificadorToken = Integer.valueOf(tabelaToken.get(i)[1]);
            
            if ( i <= tabelaToken.size()) {
                proximoIdentificadorToken = Integer.valueOf(tabelaToken.get(i + 1)[1]);
            }            
            
            linha = Integer.valueOf(tabelaToken.get(i)[2]);            
            sequencial++;
            
           // if (sequencial == 1) {
              //  ValidaProducao01(valorToken, proximoIdentificadorToken , linha);
           // }                        
        }    
        
        for (ErrosSintaticos item : listaErros) {
            System.out.println(item.getMensagem() + "  " + item.getLinha());
        }
        
    }   
    
    public void ValidaProducao01( String instrucao, int seqClassificacao01, int seqClassificacao02, int linha ){
        
        if (!instrucao.toLowerCase().trim().equals("program") ) {           
            AddErroSintatico("O programa deve iniciar com program. Linha: ", linha);
        }
        
        //-- Verifica o nome do programa
        if (seqClassificacao01 != 25) {
            AddErroSintatico("Esperado o nome do programa, encontrado outra coisa =D", linha);
        }
        
        //-- Valida ponto e virgula, caracter terminal
        if (seqClassificacao02 != 47) {
            AddErroSintatico("Esperado o ; encontrado outra coisa =D", linha);
        }
        
    }
    
    public void AddErroSintatico(String mensagem, int linha){
        ErrosSintaticos errosSintaticos  = new ErrosSintaticos();
        errosSintaticos.Mensagem = mensagem;
        errosSintaticos.Linha = linha;
        listaErros.add(errosSintaticos);
    }
    
}