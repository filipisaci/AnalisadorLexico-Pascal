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
    }
}
