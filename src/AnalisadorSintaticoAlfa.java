
import java.util.ArrayList;

public class AnalisadorSintaticoAlfa {

    public class ErrosSintaticos {

        public String Mensagem;
        public Integer Linha;

        public ErrosSintaticos() {
        }

        public String getMensagem() {
            return this.Mensagem;
        }

        public Integer identifica() {
            return this.Linha;
        }
    }

    public class TabelaProducao {

        public String Simbolo;
        public Integer CodigoSimbolo;

        public String Lexama;
        public Integer CodigoLexema;
    }

    public ArrayList<ErrosSintaticos> listaErros = new ArrayList<>();
    public ArrayList<TabelaProducao> listaTabelaProducao = new ArrayList<>();

    public ArrayList<ErrosSintaticos> AnalisarSintaxe(ArrayList<String[]> tabelaToken) {
        Integer linha = 0;
        Integer sequencial = 0;

        for (int i = 0; i < tabelaToken.size() - 1; i++) {

            linha = Integer.valueOf(tabelaToken.get(i)[2]);
            sequencial++;

            // valida a primeira linha
            if (sequencial == 1) {
                ValidaProducao01(
                        tabelaToken.get(i)[0],
                        Integer.valueOf(tabelaToken.get(i + 1)[1]),
                        Integer.valueOf(tabelaToken.get(i + 2)[1]),
                        linha);
            }

            // Valida as declarações das variáveis
            if (sequencial == 4) {
                ValidaVariavel(
                        tabelaToken.get(i)[0],
                        Integer.valueOf(tabelaToken.get(i + 1)[1]),
                        Integer.valueOf(tabelaToken.get(i + 2)[1]),
                        Integer.valueOf(tabelaToken.get(i + 3)[1]),
                        Integer.valueOf(tabelaToken.get(i + 4)[1]),
                        linha);
            }

            // valida atribuicao de variavel
            if (tabelaToken.get(i)[1].equals("38")) {
                ValidaAtribuicao(
                        tabelaToken.get(i - 1)[1], 
                        tabelaToken.get(i + 1)[1], 
                        tabelaToken.get(i + 2)[1], 
                        linha);
            }

            // valida writeln
            if (tabelaToken.get(i)[1].equals("21")) {
                validaWriteln(
                        tabelaToken.get(i + 1)[1], 
                        tabelaToken.get(i + 2)[1], 
                        linha);
            }

            // Valida o IF
            if (tabelaToken.get(i)[1].equals("13")) {
                validaIf(
                        tabelaToken.get(i + 1)[1],
                        tabelaToken.get(i + 2)[1],
                        tabelaToken.get(i + 3)[1],
                        tabelaToken.get(i + 4)[1],
                        linha);
            }

            // valida o Begin
            if (tabelaToken.get(i)[1].equals("6")) {
                validaBegin(
                        tabelaToken.get(tabelaToken.size() - 2)[1],
                        tabelaToken.get(tabelaToken.size() - 1)[1],
                        linha);
            }

            TabelaProducao tabelaProducao = new TabelaProducao();

            tabelaProducao.Lexama = tabelaToken.get(i)[0];
            tabelaProducao.CodigoLexema = Integer.valueOf(tabelaToken.get(i)[1]);

            listaTabelaProducao.add(tabelaProducao);

        }

        for (ErrosSintaticos item : listaErros) {
            System.out.println(item.getMensagem() + "  " + item.identifica());
        }
        
        return listaErros;
  
    }

    public void ValidaProducao01(String instrucao, int seqClassificacao01, int seqClassificacao02, int linha) {

        if (!instrucao.toLowerCase().trim().equals("program")) {
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

    public void ValidaProducao02(String instrucao, int seqClassificacao01, int seqClassificacao02, int linha) {

    }

    public void ValidaVariavel(String instrucao, int seqClassificacao01,
            int seqClassificacao02, int seqClassificacao03, int seqClassificacao04, int linha) {

        if (!instrucao.toLowerCase().trim().equals("var")) {
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
        if (!anterior.equals("25")) {
            AddErroSintatico("Erro de sintax (atribuição) na linha: ", linha);
        }
        if (!(proximo.equals("48") || proximo.equals("25") || proximo.equals("26") || proximo.equals("36"))) {
            AddErroSintatico("Erro de sintax (atribuição) na linha: ", linha);
        }

        if (!terminador.equals("47") && !terminador.equals("30") && !terminador.equals("31")
                && !terminador.equals("32") && !terminador.equals("33")) {
            AddErroSintatico("Erro de sintax (atribuição) na linha: ", linha);
        }
    }

    public void validaWriteln(String proximo, String terminador, Integer linha) {
        if (!proximo.equals("48")) {
            AddErroSintatico("Erro de sintax (writeln) na linha: ", linha);
        }
        if (!terminador.equals("47")) {
            AddErroSintatico("Erro de sintax (writeln) na linha: ", linha);
        }
    }

    public void validaBegin(String proximo, String terminador, Integer linha) {
        if (!proximo.equals("7")) {
            AddErroSintatico("Erro de sintax (Begin) na linha:", linha);
        }
        if (!terminador.equals("49")) {
            AddErroSintatico("Erro de sintax (Begin) na linha:", linha);
        }
    }

    /*
    public void validaReadln(String proximo, String terminador, Integer linha) {
        if(!proximo.equals("48")) {
            AddErroSintatico("Readln", linha);
        }
        if(!terminador.equals("47")) {
            AddErroSintatico("Readln", linha);
        }
    }*/
    public void validaIf(String seqClassificacao01,
            String seqClassificacao02, String seqClassificacao03, String seqClassificacao04, Integer linha) {

        //-- Valida label ID
        if (!(seqClassificacao01.equals("25") || seqClassificacao03.equals("26"))) {
            AddErroSintatico("Erro de sintax (IF) na linha: ", linha);
        }

        //-- Valida operador
        if (!(seqClassificacao02.equals("40") || seqClassificacao02.equals("41")
                || seqClassificacao02.equals("42") || seqClassificacao02.equals("43")
                || seqClassificacao02.equals("44") || seqClassificacao02.equals("45"))) {
            AddErroSintatico("Erro de sintax (IF) na linha: ", linha);
        }

        if (!seqClassificacao03.equals("26")) {
            AddErroSintatico("Erro de sintax (IF) na linha: ", linha);
        }

        if (!seqClassificacao04.equals("14")) {
            AddErroSintatico("Erro de sintax (IF) na linha: ", linha);
        }
    }

    public void AddErroSintatico(String mensagem, int linha) {
        ErrosSintaticos errosSintaticos = new ErrosSintaticos();
        errosSintaticos.Mensagem = mensagem;
        errosSintaticos.Linha = linha;
        listaErros.add(errosSintaticos);
    }

    public TabelaProducao RecuperaSimbolo(String s) {
        for (TabelaProducao tabelaProducao : listaTabelaProducao) {
            if (tabelaProducao.Simbolo == s) {
                return tabelaProducao;
            }
        }
        return null;
    }
}
