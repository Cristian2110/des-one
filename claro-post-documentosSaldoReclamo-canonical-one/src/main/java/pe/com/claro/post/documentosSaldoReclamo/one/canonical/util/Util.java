/**
 * 
 */
package pe.com.claro.post.documentosSaldoReclamo.one.canonical.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import pe.com.claro.common.property.Constantes;
import pe.com.claro.post.documentosSaldoReclamo.one.LogUtil;

/**
 * @author everis
 *
 */
public class Util {

  private final static LogUtil logUtil = new LogUtil(Util.class);

  public static XMLGregorianCalendar stringToXmlGregorianCalendar(String fechaString, String formato) {

    SimpleDateFormat formatoDelTexto = new SimpleDateFormat(formato);
    XMLGregorianCalendar response = null;
    Date fecha = null;

    try {
      GregorianCalendar c = new GregorianCalendar();
      fecha = formatoDelTexto.parse(fechaString);
      c.setTime(fecha);
      response = DatatypeFactory.newInstance().newXMLGregorianCalendar(c);

    } catch (Exception e) {
      logUtil.error(Constantes.MSG_ERROR_FECHAS, e);
    }

    return response;
  }

  public static String obtenerFechaConFormatoPersonalizado(Date fecha, String formatoFecha) {
    String fechaFormateada = null;
    try {
      if (fecha == null) {
        fecha = new Date();
      }
      SimpleDateFormat formato = new SimpleDateFormat(formatoFecha);
      fechaFormateada = formato.format(fecha);
    } catch (Exception e) {
      logUtil.error(Constantes.MSG_ERROR_FECHAS, e);
    }
    return fechaFormateada;
  }

  public static String[] converStringToStringArray(String input) {
    return input.split(Constantes.PALOTE_SEPARADOR_SPLIT.toString());
  }

  public static String isContentArray(String[] listRequest, String input) {
    String output = Constantes.EMPTY;
    for (String cadena : listRequest) {
      if (cadena.equalsIgnoreCase(input)) {
        output = cadena;
      }
    }
    return output;
  }

  public static String remove(String input) {
    // Cadena de caracteres original a sustituir.
    String original = "·‡‰ÈËÎÌÏÔÛÚˆ˙˘uÒ¡¿ƒ…»ÀÕÃœ”“÷⁄Ÿ‹—Á«";
    // Cadena de caracteres ASCII que reemplazarÔøΩn los originales.
    String ascii = "aaaeeeiiiooouuunAAAEEEIIIOOOUUUNcC";
    String output = input;
    for (int i = 0; i < original.length(); i++) {
      // Reemplazamos los caracteres especiales.
      output = output.replace(original.charAt(i), ascii.charAt(i));
    }
    return output;
  }

  public static String remove1(String input) {
    String output = input;
    for (int i = 0; i < input.length(); i++) {
      // Reemplazamos los espacios en blanco
      output = input.replace(Constantes.ESPACIO, Constantes.EMPTY);
    } // for i
    return output;
  }// remove1

  public static String identificarMoneda(String input) {
    String moneda = Constantes.EMPTY;
    switch (input) {
    case Constantes.MONEDA_PEN:
        moneda = Constantes.SIMBOLO_SOLES;
        break;
    case Constantes.MONEDA_MN:
        moneda = Constantes.SIMBOLO_SOLES;
        break;
    case Constantes.MONEDA_US:
        moneda = Constantes.SIMBOLO_DOLAR;
        break;
    case Constantes.MONEDA_USD:
        moneda = Constantes.SIMBOLO_DOLAR;
        break;
    default:
        moneda = Constantes.EMPTY;
        break;
    }
    return moneda;
  }

  public static Double sumaMontos(Double monto) {
    if (monto > Constantes.CERO_INT) {
      return monto;
    }
    return Double.parseDouble(Constantes.CERO);
  }

}
