package pe.com.claro.post.documentosSaldoReclamo.one.canonical.response;

import javax.ws.rs.core.Configuration;

/**
 * @author everis
 */
public class BaseResponse {

	private String idTransaccion;
	private Integer codigoRespuesta;
	private String mensajeRespuesta;

	public BaseResponse(Configuration configuration) {
		this.codigoRespuesta = Integer.valueOf(configuration.getProperty("codigoRespuesta.idf0").toString());
		this.mensajeRespuesta = configuration.getProperty("mensajeRespuesta.idf0").toString();
	}

	public Integer getCodigoRespuesta() {
		return codigoRespuesta;
	}

	public void setCodigoRespuesta(Integer codigoRespuesta) {
		this.codigoRespuesta = codigoRespuesta;
	}

	public String getMensajeRespuesta() {
		return mensajeRespuesta;
	}

	public void setMensajeRespuesta(String mensajeRespuesta) {
		this.mensajeRespuesta = mensajeRespuesta;
	}

	/**
	 * @return the idTransaccion
	 */
	public String getIdTransaccion() {
		return idTransaccion;
	}

	/**
	 * @param idTransaccion
	 *            the idTransaccion to set
	 */
	public void setIdTransaccion(String idTransaccion) {
		this.idTransaccion = idTransaccion;
	}

}
