import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Main {

	public static void main(String[] args) {
            
		
		try{
			ArrayList <String[]> lexic_table;
			FileReader file = new FileReader("codigofonte.txt");
			BufferedReader arquivo = new BufferedReader(file);
			
			AnalisadorLexico analisador = new AnalisadorLexico(arquivo);
			analisador.analisar();
			analisador.show_table();
			lexic_table = analisador.return_table();

			arquivo.close();
			file.close();

		}catch(FileNotFoundException fnf){
			System.out.println("Arquivo nao encontrado");
		}catch(IOException ioe){
			System.out.println("Erro");
		}catch(Exception e){
			e.printStackTrace();
		}

	}

}
