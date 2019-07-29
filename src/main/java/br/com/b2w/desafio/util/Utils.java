package br.com.b2w.desafio.util;

public class Utils {

    public static boolean isOnlyNumber(String valor){
        return valor != null && valor.matches("\\d+");
    }
}
