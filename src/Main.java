
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;


public class Main {
    
    public static ArrayList<AnalisadorLexico.ErrosLexicos> listErrosLexicos = new ArrayList<>();
    public static ArrayList<AnalisadorSintaticoAlfa.ErrosSintaticos> listaErrosSintaticos = new ArrayList<>();
    public static ArrayList<AnalisadorSemantico.ErrosSemanticos> listaErrosSemanticos = new ArrayList<>();
    
    public static void main(String[] args) {

        try {
            ArrayList<String[]> lexic_table;
            FileReader file = new FileReader("codigofonte1.txt");
            BufferedReader arquivo = new BufferedReader(file);

            AnalisadorLexico analisador = new AnalisadorLexico(arquivo);
            listErrosLexicos = analisador.analisar();
            //analisador.show_table();
            lexic_table = analisador.return_table();

            arquivo.close();
            file.close();
                
            AnalisadorSintaticoAlfa as = new AnalisadorSintaticoAlfa();
            listaErrosSintaticos = as.AnalisarSintaxe(lexic_table);
            
            AnalisadorSemantico analisadorSemantico = new AnalisadorSemantico();
            listaErrosSemanticos = analisadorSemantico.AnalisarSemantica(lexic_table);
            
            TelaComp telaComp = new TelaComp();
            telaComp.show();
            
            telaComp.ListarTokens(lexic_table);
            telaComp.ResumoLexico(listErrosLexicos);
            telaComp.ResumoSintatico(listaErrosSintaticos);
            telaComp.ResumoSemantico(listaErrosSemanticos);
            
            

        } catch (FileNotFoundException fnf) {
            System.out.println("Arquivo de entrada nao encontrado");
        } catch (IOException ioe) {
            System.out.println("Erro");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
