/**
 * 
 */
package pe.com.claro.post.documentosSaldoReclamo.one.canonical.response;

import java.util.Locale;

import pe.com.claro.common.property.Constantes;
import pe.com.claro.common.property.PropertiesExternos;
import pe.com.claro.common.resource.util.ClaroUtil;

/**
 * @author everis
 *
 */

public class Response {

  private String codigoRpta;
  private String mensajeRpta;

  /**
   * @return the codigoRpta
   */
  public String getCodigoRpta() {
    return codigoRpta;
  }

  /**
   * @return the mensajeRpta
   */
  public String getMensajeRpta() {
    return mensajeRpta;
  }

  /**
   * @param codigoRpta
   *          the codigoRpta to set
   */
  public void setCodigoRpta(String codigoRpta) {
    this.codigoRpta = codigoRpta;
  }

  /**
   * @param mensajeRpta
   *          the mensajeRpta to set
   */
  public void setMensajeRpta(String mensajeRpta) {
    this.mensajeRpta = mensajeRpta;
  }

  public Response controlException(String descripcionError, String nombreSp, String nombreBD, String nombreWS,
      String metodo, PropertiesExternos properties) {
    Response response = new Response();
    Integer codigoRespuesta = Constantes.VALOR_CERO;
    String mensajeRespuesta = Constantes.EMPTY;
    if (!nombreSp.equals(Constantes.EMPTY) && !nombreBD.equals(Constantes.EMPTY)) {
      if (descripcionError.toUpperCase(Locale.getDefault()).contains(Constantes.SQL_TIMEOUTEXCEPTION)) {
        codigoRespuesta = Integer.parseInt(properties.getCodigoProcedureGenericoErrorIdt1());
        mensajeRespuesta = ClaroUtil.convertProperties(properties.getMensajeProcedureGenericoErrorIdt1()
            .replace(Constantes.NOMBRESP, nombreSp).replace(Constantes.NOMBREDB, nombreBD));
      } else {
        codigoRespuesta = Integer.parseInt(properties.getCodigoProcedureGenericoErrorIdt2());
        mensajeRespuesta = ClaroUtil.convertProperties(properties.getMensajeProcedureGenericoErrorIdt2())
            .replace(Constantes.NOMBREDB, nombreBD);
      }
    } else {
      codigoRespuesta = Integer.parseInt(properties.getCodigoRespuestaIdt2());
      mensajeRespuesta = properties.getMensajeRespuestaIdt2().replace("$ws", nombreWS).replace("$me", metodo);
    }
    response.setCodigoRpta(codigoRespuesta.toString());
    response.setMensajeRpta(mensajeRespuesta);
    return response;
  }

}
