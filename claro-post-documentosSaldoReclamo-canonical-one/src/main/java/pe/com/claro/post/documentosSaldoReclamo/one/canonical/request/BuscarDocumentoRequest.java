/**
 * 
 */
package pe.com.claro.post.documentosSaldoReclamo.one.canonical.request;

import javax.ws.rs.core.Response;

import pe.com.claro.common.property.PropertiesExternos;
import pe.com.claro.common.resource.exception.NotFoundException;

/**
 * @author everis
 *
 */
public class BuscarDocumentoRequest {

  private String codCliente;
  private String tipoDoc;
  private String numeroDocu;

  /**
   * @return the tipoDoc
   */
  public String getTipoDoc() {
    return tipoDoc;
  }

  /**
   * @param tipoDoc
   *          the tipoDoc to set
   */
  public void setTipoDoc(String tipoDoc) {
    this.tipoDoc = tipoDoc;
  }

  /**
   * @return the codCliente
   */
  public String getCodCliente() {
    return codCliente;
  }

  /**
   * @param codCliente
   *          the codCliente to set
   */
  public void setCodCliente(String codCliente) {
    this.codCliente = codCliente;
  }

  /**
   * @return the numeroDocu
   */
  public String getNumeroDocu() {
    return numeroDocu;
  }

  /**
   * @param numeroDocu
   *          the numeroDocu to set
   */
  public void setNumeroDocu(String numeroDocu) {
    this.numeroDocu = numeroDocu;
  }

  public void isValid(PropertiesExternos propertiesExternos) {
    String camposFaltantes = "";

    if (codCliente == null || codCliente.equalsIgnoreCase("")) {
      camposFaltantes += " codCliente";
    }
    if (tipoDoc == null || tipoDoc.equalsIgnoreCase("")) {
      camposFaltantes += " tipoDoc";
    }
    if (numeroDocu == null || numeroDocu.equalsIgnoreCase("")) {
      camposFaltantes += " numeroDocu";
    }
    if (camposFaltantes.length() > 0) {
      throw new NotFoundException(Response.Status.OK.getStatusCode(),
          Integer.parseInt(propertiesExternos.getCodigoRespuestaIdf1().toString()), propertiesExternos
              .getMensajeRespuestaIdf1().toString().replace("replace", "Request Body: " + camposFaltantes.trim()),
          null);
    }

  }
}
