
import java.util.ArrayList;

public class AnalisadorSintaticoGama {

    public class ErrosSintaticos {

        public String Mensagem;
        public String Linha;

        public ErrosSintaticos() {
        }

        public String getMensagem() {
            return this.Mensagem;
        }

        public String getLinha() {
            return this.Linha;
        }
    }

    public ArrayList<ErrosSintaticos> listaErros = new ArrayList<>();

    public void AnalisarSintaxe(ArrayList<String[]> tabelaToken) {
        
        System.out.println("Inicio da análise sintática");
        
        String instrucao = "";
        String lexema = "";
        String linha = "";

        for (int i = 0; i < tabelaToken.size() - 1; i++) {
            
            instrucao = tabelaToken.get(i)[0];
            lexema = tabelaToken.get(i + 1)[1];
            linha = tabelaToken.get(i)[2];
            
            if (instrucao.equals("program")) {
                ValidaProducao01(instrucao, tabelaToken.get(i + 1)[1], tabelaToken.get(i + 2)[1], tabelaToken.get(i)[2]);
                i++;
                i++;
            }
            if (instrucao.equals("var")) {
                while (!tabelaToken.get(i)[1].equals("47")) {
                    i++;
                }
                ValidaProducaoVar(instrucao, tabelaToken.get(i-1)[1], tabelaToken.get(i)[2]);
            }
            //o que validar no begin???
            if(lexema.equals("38")){
                ValidaAtribuicao(tabelaToken.get(i)[1], tabelaToken.get(i+2)[1], linha);
            }
        }

        for (ErrosSintaticos item : listaErros) {
            System.out.println(item.getMensagem() + "  " + item.getLinha());
        }

    }

    public void ValidaProducao01(String instrucao, String seqClassificacao01, String seqClassificacao02, String linha) {

        if (!instrucao.toLowerCase().trim().equals("program")) {
            AddErroSintatico("token", linha);
        }

        //-- Verifica o nome do programa
        if (!seqClassificacao01.equals("25")) {
            AddErroSintatico("token", linha);
        }

        //-- Valida ponto e virgula, caracter terminal
        if (!seqClassificacao02.equals("47")) {
            AddErroSintatico("token", linha);
        }

    }

    public void ValidaProducaoVar(String instrucao, String terminal, String linha) {
        if (!instrucao.toLowerCase().trim().equals("var")) {
            AddErroSintatico("var", linha);
        }
        //-- Valida label ID
        if (!terminal.equals("8")) {
            AddErroSintatico("var", linha);
        }
    }
    
    public void ValidaAtribuicao(String anterior, String proximo, String linha) {
        if(!anterior.equals("25")) {
            AddErroSintatico("atribuicao", linha);
        }
        if(!(proximo.equals("48") || proximo.equals("26") || proximo.equals("36"))){
            AddErroSintatico("atribuicao", linha);
        }
    }

    public void AddErroSintatico(String tipo, String linha) {
        ErrosSintaticos errosSintaticos = new ErrosSintaticos();
        errosSintaticos.Mensagem = "Erro de sintax (" + tipo + ") na linha: ";
        errosSintaticos.Linha = linha;
        listaErros.add(errosSintaticos);
    }
}
