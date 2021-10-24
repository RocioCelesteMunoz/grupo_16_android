package com.example.app.utils;

import java.math.BigInteger;
import java.security.SecureRandom;

public class Configuration {

    //Constantes para generación de código para doble factor
    private static final int RANDOM_CODE_NUM_BITS = 30;
    private static final int RANDOM_CODE_STRING_RADIX = 32;

    public static final String VERIFICATION_EMAIL = "mailsoagrupo16@gmail.com";
    public static final String VERIFICATION_PASSWORD = "prueba123";

    /**
     * Método que se encarga de generar un código aleatorio para
     * la autenticación de doble factor
     * @return código aleatorio
     */
    public static String generateRandomCode() {
        SecureRandom random = new SecureRandom();

        /*
        Esta línea se encarga de generar un número aleatorio para
        la autenticación de doble factor
         */
        return new BigInteger(RANDOM_CODE_NUM_BITS, random).toString(RANDOM_CODE_STRING_RADIX);
    }
}
