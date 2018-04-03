import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;

public class AnalisadorLexico {

    private ArrayList<String[]> token_table = new ArrayList<String[]>();
    private BufferedReader bf;
    private int nLine;
    private Integer inicio = 0;
    private String palavra = "";

    public AnalisadorLexico(BufferedReader file) {
        bf = file;
        nLine = 1;
    }

    public void analisar() throws IOException {

        char c;

        System.out.println("Inicio da analise Léxica\n");

        while (bf.ready()) {
            c = (char) bf.read();
            if (c == ' ' || c == '\t' || c == '\r') { //Desconsidera espaço em branco
                continue;
            }
            if (c == '\n') {
                nLine++;
                continue;
            }

            if (c == 39) {
                while (c != 39) {
                    c = (char) bf.read();
                    if (!bf.ready()) {
                        System.out.println("Erro: String não fechada. Linha " + nLine);
                        break;
                    }
                }
                continue;
            }

            bf.mark(2);
            if (c == '/') {
                char aux = (char) bf.read();
                if (c == '/' && aux == '/') {
                    bf.readLine();
                    continue;
                } else {
                    bf.reset();
                    String[] s = {Character.toString(c), RecuperaToken("*").toString(), Integer.toString(nLine)};
                    token_table.add(s);
                    continue;
                }
            }

            if ((65 <= c && c <= 90) || (97 <= c && c <= 122)) {
                if (check_token(c))
					;
                else {
                    System.out.println("Erro: identificador inválido. Linha " + nLine);
                }
            } else if (48 <= c && c <= 57) {
                if (check_number(c)); else {
                    System.out.println("Erro, Número inválido. Linha " + nLine);
                    break;
                }

            } else if (c == ';' || c == '.' || c == '(' || c == ')' || c == ',' || c == ':') { //Verifica se C é um delimitador
                if (c == ':') { //Verifica se é o operador de atribuição
                    bf.mark(2);
                    char aux = (char) bf.read();
                    if (aux == '=') { //Operador de atribuição
                        String[] s = {Character.toString(c) + Character.toString(aux), RecuperaToken(Character.toString(c) + Character.toString(aux)).toString(), Integer.toString(nLine)};
                        token_table.add(s);
                        continue;
                    }
                }
                String[] s = {Character.toString(c), RecuperaToken(Character.toString(c)).toString(), Integer.toString(nLine)};
                token_table.add(s);
            } else if (c == '+' || c == '-') {
                String[] s = {Character.toString(c), RecuperaToken(Character.toString(c)).toString(), Integer.toString(nLine)};
                token_table.add(s);
            } else if (c == '*' || c == '/') {
                String[] s = {Character.toString(c), RecuperaToken(Character.toString(c)).toString(), Integer.toString(nLine)};
                token_table.add(s);
            } else if (c == '<' || c == '>' || c == '=') {
                bf.mark(2);
                char aux = (char) bf.read();
                if ((c == '<' && aux == '=') || (c == '<' && aux == '>') || (c == '>' && aux == '=')) {
                    String[] s = {Character.toString(c) + Character.toString(aux), RecuperaToken(Character.toString(c) + Character.toString(c)).toString(), Integer.toString(nLine)};
                    token_table.add(s);
                } else {
                    bf.reset();
                    String[] s = {Character.toString(c), RecuperaToken(Character.toString(c)).toString(), Integer.toString(nLine)};
                    token_table.add(s);
                }

            } else {
                System.out.println("Erro desconhecido. Linha " + nLine);
                break;
            }

        }

    }

    /**
     * ----------------------------------------------------------------------------------------------------------------------------------------------*
     */
    public void show_table() {
        System.out.println("\nTabela de Símbolos");
        System.out.println("Token\t\t\tClassificação\t\t\tLinha");
        int size = token_table.size();
        for (int i = 0; i < size; i++) {
            String l1 = token_table.get(i)[0], l2 = token_table.get(i)[1], l3 = token_table.get(i)[2];
            System.out.println(l1 + "\t\t\t" + l2 + "\t\t\t" + l3);
        }
        System.out.println();
    }

    public ArrayList<String[]> return_table() {
        return this.token_table;
    }

    private boolean check_token(char c) throws IOException {
        String word = Character.toString(c).toLowerCase();
        //System.out.println(word);

        while (bf.ready()) {
            bf.mark(2);
            c = (char) bf.read();

            if (c == ' ' || c == '\t' || c == '\r') {
                bf.reset();
                break;
            }
            if ((40 <= c && c <= 47) || (58 <= c && c <= 63)) {
                bf.reset();
                break;
            }
            if (c == 39) {
                while (c != 39) {
                    c = (char) bf.read();
                }
                if (!bf.ready()) {
                    System.out.println("Erro: Comentário não fechado");
                    break;
                }
                continue;
            }

            word = word.concat(Character.toString(c).toLowerCase());
        }

        if (word.matches("\\w[\\w|\\d|\\_]*")) { 

            InicioDeLinha(word, nLine);

            if (word.matches("[Bb]oolean") || word.matches("[Bb]egin") || word.matches("[Ii]nteger") || word.matches("[Ii]f") || word.matches("[Ee]lse")
                    || word.matches("[Ee]nd") || word.matches("[Vv]ar") || word.matches("[Rr]eal") || word.matches("[Tt]hen") || word.matches("[Pp]rogram")
                    || word.matches("[Rr]eadln") || word.matches("[Ww]riteln") || word.matches("[Pp]rocedure") || word.matches("[Ww]hile") || word.matches("[Dd]o") || word.matches("[Nn]ot")) {
                add_key_word(word);
            } else if (word.matches("and")) {
                String[] s = {word, RecuperaToken(word).toString(), Integer.toString(nLine)};
                token_table.add(s);
            } else if (word.matches("or")) {
                String[] s = {word, RecuperaToken(word).toString(), Integer.toString(nLine)};
                token_table.add(s);
            } else if (word.matches("[Tt]rue") || word.matches("[Ff]alse")) { //Linha adicionada (não está na especificação do léxico) para não classificar as palavras True e False como Identificadores 
                String[] s = {word, RecuperaToken(word).toString(), Integer.toString(nLine)};
                token_table.add(s);
            } else {
                add_id(word);
            }

            return true;
        } else {
            return false;
        }
    }

    /**
     * ----------------------------------------------------------------------------------------------------------------------------------------------*
     */
    private void add_key_word(String word) {

        String[] s = {word, RecuperaToken(word).toString(), Integer.toString(nLine)};
        token_table.add(s);
    }

    private void add_id(String id) {
        String[] s = {id, RecuperaToken("Identificador").toString(), Integer.toString(nLine)};
        token_table.add(s);
    }

    private boolean check_number(char c) throws IOException {

        String word = Character.toString(c);
        boolean final_state = false;
        char aux = '\0';

        while (bf.ready()) {
            bf.mark(2);
            c = (char) bf.read();

            if (c == ' ' || c == '\t' || c == '\r') {
                bf.reset();
                break;
            }
            if ((40 <= c && c <= 42) || c == 44 || c == 47 || (58 <= c && c <= 63)) {
                bf.reset();
                break;
            }
            if (c == 39) {
                while (c != 39) {
                    c = (char) bf.read();
                }
                if (!bf.ready()) {
                    System.out.println("Erro: String não fechada");
                    break;
                }
                continue;
            }

            if ((65 <= c && c <= 90) || (97 <= c && c <= 122)) {
                if (c == 'i') {
                    bf.mark(5);
                    aux = (char) bf.read();
                    System.out.println(c);
                    System.out.println(aux);
                    if (aux == '+' || aux == '-') {
                        char aux2 = (char) bf.read();
                        if (48 <= aux2 && aux2 <= 57) {
                            word = word.concat(Character.toString(c) + Character.toString(aux) + Character.toString(aux2));
                            continue;
                        } else {
                            aux = c;
                            bf.reset();
                            break;
                        }
                    } else {
                        bf.reset();
                        aux = c;
                        System.out.println(c);
                        System.out.println(aux);
                        break;
                    }
                } else {
                    aux = c;
                    break;
                }
            }

            word = word.concat(Character.toString(c));
        }

        if (word.matches("\\d+\\.\\d+")) {
            String[] s = {word, "Número Real", Integer.toString(nLine)};
            token_table.add(s);
            final_state = true;
        } else if (word.matches("\\d+")) {
            String[] s = {word, RecuperaToken("Inteiro").toString(), Integer.toString(nLine)};
            token_table.add(s);
            final_state = true;
        }

        if (aux == c) {
            if (check_token(aux)) {
                final_state = true;
            } else {
                final_state = false;
            }
        }

        return final_state;

    }

    public Integer RecuperaToken(String word) {
        Token token = new Token();
        Integer codigoToken = 0;
        codigoToken = token.RecuperaCodigoTerminal(word);

        if (codigoToken == 0) {
            codigoToken = token.RecuperaCodigoNaoTerminal(word);
        }
        return codigoToken;
    }

    public void InicioDeLinha(String word, Integer nLine) {
        if (nLine == 1 ) {
            palavra = palavra.concat(word);
            if (palavra.matches("^program.*") && (inicio == 0)) {
                inicio++;
            } else if (inicio  == 0) {
                System.out.println("Erro, token esperado: Program");
                inicio++;
            }
        }
    }
}
