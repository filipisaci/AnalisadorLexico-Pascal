/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

/**
 *
 * @author artur.271811
 */
public class Token {
    
    Map<Integer, String> mapTokenTerminal = new HashMap<>();
    Map<Integer, String> mapTokenNaoTerminal = new HashMap<>();
    
    public Token() {
        
        mapTokenTerminal.put(1,"Program");
        mapTokenTerminal.put(2,"Label");
        mapTokenTerminal.put(3,"Const");
        mapTokenTerminal.put(4,"Var");
        mapTokenTerminal.put(5,"Procedure");
        mapTokenTerminal.put(6,"Begin");
        mapTokenTerminal.put(7,"End");
        mapTokenTerminal.put(8,"Integer");
        mapTokenTerminal.put(9,"Array");
        mapTokenTerminal.put(10,"Of");
        mapTokenTerminal.put(11,"Call");
        mapTokenTerminal.put(12,"Goto");
        mapTokenTerminal.put(13,"If");
        mapTokenTerminal.put(14,"Then");
        mapTokenTerminal.put(15,"Else");
        mapTokenTerminal.put(16,"While");
        mapTokenTerminal.put(17,"Do");
        mapTokenTerminal.put(18,"Repeat");
        mapTokenTerminal.put(19,"Until");
        mapTokenTerminal.put(20,"Readln");
        mapTokenTerminal.put(21,"Writeln");
        mapTokenTerminal.put(22,"Or");
        mapTokenTerminal.put(23,"And");
        mapTokenTerminal.put(24,"Not");
        mapTokenTerminal.put(25,"Identificador");
        mapTokenTerminal.put(26,"Inteiro");
        mapTokenTerminal.put(27,"For");
        mapTokenTerminal.put(28,"To");
        mapTokenTerminal.put(29,"Case");
        mapTokenTerminal.put(30,"+");
        mapTokenTerminal.put(31,"-");
        mapTokenTerminal.put(32,"*");
        mapTokenTerminal.put(33,"/");
        mapTokenTerminal.put(34,"[");
        mapTokenTerminal.put(35,"]");
        mapTokenTerminal.put(36,"(");
        mapTokenTerminal.put(37,")");
        mapTokenTerminal.put(38,":=");
        mapTokenTerminal.put(39,":");
        mapTokenTerminal.put(40,"=");
        mapTokenTerminal.put(41,">");
        mapTokenTerminal.put(42,">=");
        mapTokenTerminal.put(43,"<");
        mapTokenTerminal.put(44,"<=");
        mapTokenTerminal.put(45,"< >");
        mapTokenTerminal.put(46,",");
        mapTokenTerminal.put(47,";");
        mapTokenTerminal.put(48,"literal");
        mapTokenTerminal.put(49,".");
        mapTokenTerminal.put(50,"..");
        mapTokenTerminal.put(51,"$");
        
        mapTokenNaoTerminal.put(53,"PROGRAMA");
        mapTokenNaoTerminal.put(54,"BLOCO");
        mapTokenNaoTerminal.put(55,"DCLROT");
        mapTokenNaoTerminal.put(56,"LID");
        mapTokenNaoTerminal.put(57,"REPIDENT");
        mapTokenNaoTerminal.put(58,"DCLCONST");
        mapTokenNaoTerminal.put(59,"LDCONST");
        mapTokenNaoTerminal.put(60,"DCLVAR");
        mapTokenNaoTerminal.put(61,"LDVAR");
        mapTokenNaoTerminal.put(62,"TIPO");
        mapTokenNaoTerminal.put(63,"DCLPROC");
        mapTokenNaoTerminal.put(64,"DEFPAR");
        mapTokenNaoTerminal.put(65,"CORPO");
        mapTokenNaoTerminal.put(66,"REPCOMANDO");
        mapTokenNaoTerminal.put(67,"COMANDO");
        mapTokenNaoTerminal.put(68,"RCOMID");
        mapTokenNaoTerminal.put(69,"RVAR");
        mapTokenNaoTerminal.put(70,"PARAMETROS");
        mapTokenNaoTerminal.put(71,"REPPAR");
        mapTokenNaoTerminal.put(72,"ELSEPARTE");
        mapTokenNaoTerminal.put(73,"VARIAVEL");
        mapTokenNaoTerminal.put(74,"VARIAVEL1");
        mapTokenNaoTerminal.put(75,"REPVARIAVEL");
        mapTokenNaoTerminal.put(76,"ITEMSAIDA");
        mapTokenNaoTerminal.put(77,"REPITEM");
        mapTokenNaoTerminal.put(78,"EXPRESSAO");
        mapTokenNaoTerminal.put(79,"REPEXPSIMP");
        mapTokenNaoTerminal.put(80,"EXPSIMP");
        mapTokenNaoTerminal.put(81,"REPEXP");
        mapTokenNaoTerminal.put(82,"TERMO");
        mapTokenNaoTerminal.put(83,"REPTERMO");
        mapTokenNaoTerminal.put(84,"FATOR");
        mapTokenNaoTerminal.put(85,"CONDCASE");
        mapTokenNaoTerminal.put(86,"CONTCASE");
        mapTokenNaoTerminal.put(87,"RPINTEIRO");
        mapTokenNaoTerminal.put(88,"SEM EFEITO");
      
    }

    public int RecuperaCodigoTerminal(String simbolo){
        for (Entry<Integer, String> entry : mapTokenTerminal.entrySet()) {
            if (entry.getValue().toUpperCase().equals(simbolo.toUpperCase())) {
                return entry.getKey();
            }
        }
      return 0;
    }
    
    public int RecuperaCodigoNaoTerminal(String simbolo){
        for (Entry<Integer, String> entry : mapTokenNaoTerminal.entrySet()) {
            if (entry.getValue().toUpperCase().equals(simbolo.toUpperCase())) {
                return entry.getKey();
            }
        }
      return 0;
    }
}
