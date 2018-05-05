
import java.io.BufferedReader;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Filipi
 */
public class TestaExpressoes {

    public static void main(String[] args) {

        boolean nome = "Joao Maria".matches(".*Maria");
        System.out.println("Retorno = " + nome);

        boolean palavra = "Java322".matches("^Java.*");
        System.out.println(palavra);
        
        String entrada = "'Filipi Souza'";
        String word = "";
        System.out.println(entrada);
        
        for (int i = 0; i < entrada.length(); i++) {
            //System.out.println(entrada.charAt(i));
            if (entrada.charAt(i) == 39) {
                i++;
                while(entrada.charAt(i) != 39){
                    word = word.concat(Character.toString(entrada.charAt(i)));
                    i++;
                }
            }
        }
        System.out.println("Coza linda: " + word);
    }
}
