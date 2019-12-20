package br.com.centraldeerro.Utils;

import java.util.Random;

public class GeradorNumeroAleatorio {

    public static String gerarNumeroRandomico(){
        Random random = new Random();
        return random.nextInt(1000000) + "";
    }
}
