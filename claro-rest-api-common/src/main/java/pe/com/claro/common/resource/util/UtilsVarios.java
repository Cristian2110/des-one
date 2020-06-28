package pe.com.claro.common.resource.util;

/**
 * Created by jvasquee on 14/07/2017.
 */
public class UtilsVarios {

    public static String nullToVacio(String parametro) {
        if (parametro == null) {
            return "";
        }
        return parametro;
    }
}
