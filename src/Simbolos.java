
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class Simbolos {

    Map<Integer, String> mapCodigoSimbolo = new HashMap<>();

    public Simbolos() {

        mapCodigoSimbolo.put(53, "PROGRAMA");
        mapCodigoSimbolo.put(54, "BLOCO");
        mapCodigoSimbolo.put(55, "DCLROT");
        mapCodigoSimbolo.put(56, "LID");
        mapCodigoSimbolo.put(57, "REPIDENT");
        mapCodigoSimbolo.put(58, "DCLCONST");
        mapCodigoSimbolo.put(59, "LDCONST");
        mapCodigoSimbolo.put(60, "DCLVAR");
        mapCodigoSimbolo.put(61, "LDVAR");
        mapCodigoSimbolo.put(62, "TIPO");
        mapCodigoSimbolo.put(63, "DCLPROC");
        mapCodigoSimbolo.put(64, "DEFPAR");
        mapCodigoSimbolo.put(65, "CORPO");
        mapCodigoSimbolo.put(66, "REPCOMANDO");
        mapCodigoSimbolo.put(67, "COMANDO");
        mapCodigoSimbolo.put(68, "RCOMID");
        mapCodigoSimbolo.put(69, "RVAR");
        mapCodigoSimbolo.put(70, "PARAMETROS");
        mapCodigoSimbolo.put(71, "REPPAR");
        mapCodigoSimbolo.put(72, "ELSEPARTE");
        mapCodigoSimbolo.put(73, "VARIAVEL");
        mapCodigoSimbolo.put(74, "VARIAVEL1");
        mapCodigoSimbolo.put(75, "REPVARIAVEL");
        mapCodigoSimbolo.put(76, "ITEMSAIDA");
        mapCodigoSimbolo.put(77, "REPITEM");
        mapCodigoSimbolo.put(78, "EXPRESSAO");
        mapCodigoSimbolo.put(79, "REPEXPSIMP");
        mapCodigoSimbolo.put(80, "EXPSIMP");
        mapCodigoSimbolo.put(81, "REPEXP");
        mapCodigoSimbolo.put(82, "TERMO");
        mapCodigoSimbolo.put(83, "REPTERMO");
        mapCodigoSimbolo.put(84, "FATOR");
        mapCodigoSimbolo.put(85, "CONDCASE");
        mapCodigoSimbolo.put(86, "CONTCASE");
        mapCodigoSimbolo.put(87, "RPINTEIRO");
        mapCodigoSimbolo.put(88, "SEMEFEITO");

    }

    public int RecuperaCodigo(String simbolo) {
        for (Entry<Integer, String> entry : mapCodigoSimbolo.entrySet()) {
            if (entry.getValue().toUpperCase().equals(simbolo.toUpperCase())) {
                return entry.getKey();
            }
        }
        return 0;
    }
}
