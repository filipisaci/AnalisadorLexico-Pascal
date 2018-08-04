
import java.util.ArrayList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author artur.dolzanneto
 */
public class AnalisadorSemantico {
    
    public class ErrosSemanticos
    {
        public String Mensagem;
        public Integer Linha;
    }
    
    public ArrayList<ErrosSemanticos> listaErrosSemanticos = new ArrayList<>();

    public ArrayList<ErrosSemanticos> AnalisarSemantica(ArrayList<String[]> tabelaToken) {
        
        System.out.println("---- ANÁLISE SEMÂNTICA ----");
        
        VerificaVariaveis(tabelaToken);        
        
        for (ErrosSemanticos item : listaErrosSemanticos) {
            System.out.println(item.Mensagem + " Linha: " + item.Linha);
        }
        
        return listaErrosSemanticos;
    }
    
    public void VerificaVariaveis(ArrayList<String[]> tabelaToken){
        Boolean aposBegin = false;
        
        for (int i = 0; i < tabelaToken.size() - 1; i++) {
            String ident = tabelaToken.get(i)[0];
            String tip = tabelaToken.get(i)[1];
            String linha = tabelaToken.get(i)[2];

            if (aposBegin) {                                
                if (tip.toUpperCase().equals("25")) {                    
                    
                    Boolean existe = VerificaSeExisteBlocoVariaveis(tabelaToken, ident);
                    
                    //-- Analisa se a variável está declarada
                    if (!existe) {
                        ErrosSemanticos errosSemanticos = new ErrosSemanticos();
                        errosSemanticos.Linha = Integer.valueOf(linha);
                        errosSemanticos.Mensagem = ident + " não foi declarado! " ;
                        
                        listaErrosSemanticos.add(errosSemanticos);
                        //System.out.println(errosSemanticos.Mensagem);
                    }else{
                        //-- Analisa o tipo da variável
                        
                        String tipoVariavel = VerificaTipoVariavel(tabelaToken, ident);
                        Integer classifVar = Integer.valueOf(tabelaToken.get(i+2)[1]);
                        
                        if (tipoVariavel.toUpperCase().trim().equals("INTEGER") && classifVar != 26) {
                            ErrosSemanticos errosSemanticos = new ErrosSemanticos();
                            errosSemanticos.Linha = Integer.valueOf(linha);
                            errosSemanticos.Mensagem = ident + " foi declarado como INTEGER! " ;
                        
                            listaErrosSemanticos.add(errosSemanticos);
                            //System.out.println(errosSemanticos.Mensagem);
                        }
                        
                        if (tipoVariavel.toUpperCase().trim().equals("STRING") && classifVar != 48) {
                            ErrosSemanticos errosSemanticos = new ErrosSemanticos();
                            errosSemanticos.Linha = Integer.valueOf(linha);
                            errosSemanticos.Mensagem = ident + " foi declarado como STRING! " ;
                        
                            listaErrosSemanticos.add(errosSemanticos);
                            //System.out.println(errosSemanticos.Mensagem);
                        }
                        
                        //System.out.println("TipoVar " + tipoVariavel + " class " + tabelaToken.get(i+2)[1]);
                        
                    }
                    
                    
                }
            }
            
            if (ident.toUpperCase().trim().equals("BEGIN")) {
                aposBegin = true;
            }               
        }
    }
    
    public boolean VerificaSeExisteBlocoVariaveis(ArrayList<String[]> tabelaToken, String identificador){
        Boolean iniciaGet = false;
        
        for (int i = 0; i < tabelaToken.size() - 1; i++) {
            String ident = tabelaToken.get(i)[0];
            String tip = tabelaToken.get(i)[1];
            
            if (ident.toUpperCase().trim().equals("VAR")) {
                iniciaGet = true;
            }
            
            if (ident.toUpperCase().trim().equals("BEGIN")) {
                break;
            }
            
            if (iniciaGet && tip.toString().equals("25")) {
                if (identificador.toUpperCase().trim().equals(ident.toUpperCase().trim())) {
                    return true;
                }
            }            
        }
        
        return false;
    }
    
    public String VerificaTipoVariavel(ArrayList<String[]> tabelaToken, String identificador){
        Boolean iniciaGet = false;
        
        for (int i = 0; i < tabelaToken.size() - 1; i++) {
            String ident = tabelaToken.get(i)[0];
            String tip = tabelaToken.get(i)[1];
            
            if (ident.toUpperCase().trim().equals("VAR")) {
                iniciaGet = true;
            }
            
            if (ident.toUpperCase().trim().equals("BEGIN")) {
                break;
            }
            
            if (iniciaGet && tip.toString().equals("25")) {
                if (identificador.toUpperCase().trim().equals(ident.toUpperCase().trim())) {
                    return tabelaToken.get(i+2)[0];
                    //System.out.println("Tipo : " + tabelaToken.get(i+2)[0] + " - " + ident);
                }
            }            
        }
        
        return "Não identificado";
    }
}
