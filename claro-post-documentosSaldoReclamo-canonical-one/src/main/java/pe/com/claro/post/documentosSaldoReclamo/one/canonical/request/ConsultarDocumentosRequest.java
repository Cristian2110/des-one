package pe.com.claro.post.documentosSaldoReclamo.one.canonical.request;

import javax.ws.rs.core.Response;

import pe.com.claro.common.property.PropertiesExternos;
import pe.com.claro.common.resource.exception.NotFoundException;

public class ConsultarDocumentosRequest {

  private String customerId;
  private String tipoDocumento;
  private String numDocumento;
  private String tipoMoneda;

  /**
   * @return the tipoMoneda
   */
  public String getTipoMoneda() {
    return tipoMoneda;
  }

  /**
   * @param tipoMoneda
   *          the tipoMoneda to set
   */
  public void setTipoMoneda(String tipoMoneda) {
    this.tipoMoneda = tipoMoneda;
  }

  /**
   * @return the tipoDocumento
   */
  public String getTipoDocumento() {
    return tipoDocumento;
  }

  /**
   * @param tipoDocumento
   *          the tipoDocumento to set
   */
  public void setTipoDocumento(String tipoDocumento) {
    this.tipoDocumento = tipoDocumento;
  }

  /**
   * @return the numDocumento
   */
  public String getNumDocumento() {
    return numDocumento;
  }

  /**
   * @param numDocumento
   *          the numDocumento to set
   */
  public void setNumDocumento(String numDocumento) {
    this.numDocumento = numDocumento;
  }

  /**
   * @return the customerId
   */
  public String getCustomerId() {
    return customerId;
  }

  /**
   * @param customerId
   *          the customerId to set
   */
  public void setCustomerId(String customerId) {
    this.customerId = customerId;
  }

  public void isValid(PropertiesExternos propertiesExternos) {
    String camposFaltantes = "";

    if (customerId == null || customerId.equalsIgnoreCase("")) {
      camposFaltantes += " customerId";
    }
    if (tipoMoneda == null || tipoMoneda.equalsIgnoreCase("")) {
      camposFaltantes += " tipoMoneda";
    }
    if (numDocumento == null || numDocumento.equalsIgnoreCase("")) {
      camposFaltantes += " numDocumento";
    }
    if (tipoDocumento == null || tipoDocumento.equalsIgnoreCase("")) {
      camposFaltantes += " tipoDocumento";
    }
    if (camposFaltantes.length() > 0) {
      throw new NotFoundException(Response.Status.OK.getStatusCode(),
          Integer.parseInt(propertiesExternos.getCodigoRespuestaIdf1().toString()), propertiesExternos
              .getMensajeRespuestaIdf1().toString().replace("replace", "Request Body: " + camposFaltantes.trim()),
          null);
    }

  }
}
