package pe.com.claro.common.resource.util;

/**
 * Created by jvasquee on 19/07/2017.
 */

public class Validator {

    public static String isNumeric(String cadena, String nombreCampo) {
        try {

            Integer.parseInt(cadena);
            return "";
        } catch (NumberFormatException nfe) {
            return " "+nombreCampo;
             }


    }
}
