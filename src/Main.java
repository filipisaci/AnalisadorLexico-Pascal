
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {

        try {
            ArrayList<String[]> lexic_table;
            FileReader file = new FileReader("codigofonte10.txt");
            BufferedReader arquivo = new BufferedReader(file);

            AnalisadorLexico analisador = new AnalisadorLexico(arquivo);
            analisador.analisar();
            analisador.show_table();
            lexic_table = analisador.return_table();

            arquivo.close();
            file.close();
            
            //AnalisadorSintatico as2 = new AnalisadorSintatico(lexic_table);
            //as2.analisar();
                
            AnalisadorSintaticoAlfa as = new AnalisadorSintaticoAlfa();
            as.AnalisarSintaxe(lexic_table);

        } catch (FileNotFoundException fnf) {
            System.out.println("Arquivo de entrada nao encontrado");
        } catch (IOException ioe) {
            System.out.println("Erro");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
