/**
 * 
 */
package pe.com.claro.post.documentosSaldoReclamo.one.canonical.response;

import java.util.List;


/**
 * @author everis
 *
 */
public class ConsultarDocumentosResponse extends Response{

	private String tipoDocumento;
	private String numDocumento;
	private String codigoInterno;
	private String estadoDocumento;
	private String tipoMoneda;
	private Double montoFijo;
	private String fechaEmision;
	private String fechaVencimiento;
	private List<DetalleConceptos> detalleConceptos;

	/**
	 * @return the codigoInterno
	 */
	public String getCodigoInterno() {
		return codigoInterno;
	}

	/**
	 * @param codigoInterno the codigoInterno to set
	 */
	public void setCodigoInterno(String codigoInterno) {
		this.codigoInterno = codigoInterno;
	}

	/**
	 * @return the fechaVencimiento
	 */
	public String getFechaVencimiento() {
		return fechaVencimiento;
	}

	/**
	 * @param fechaVencimiento the fechaVencimiento to set
	 */
	public void setFechaVencimiento(String fechaVencimiento) {
		this.fechaVencimiento = fechaVencimiento;
	}

	/**
	 * @return the fechaEmision
	 */
	public String getFechaEmision() {
		return fechaEmision;
	}

	/**
	 * @param fechaEmision
	 *            the fechaEmision to set
	 */
	public void setFechaEmision(String fechaEmision) {
		this.fechaEmision = fechaEmision;
	}

	/**
	 * @return the tipoDocumento
	 */
	public String getTipoDocumento() {
		return tipoDocumento;
	}

	/**
	 * @param tipoDocumento
	 *            the tipoDocumento to set
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
	 * @return the estadoDocumento
	 */
	public String getEstadoDocumento() {
		return estadoDocumento;
	}

	/**
	 * @return the tipoMoneda
	 */
	public String getTipoMoneda() {
		return tipoMoneda;
	}

	/**
	 * @return the montoFijo
	 */
	public Double getMontoFijo() {
		return montoFijo;
	}

	/**
	 * @return the detalleConceptos
	 */
	public List<DetalleConceptos> getDetalleConceptos() {
		return detalleConceptos;
	}

	/**
	 * @param numDocumento
	 *            the numDocumento to set
	 */
	public void setNumDocumento(String numDocumento) {
		this.numDocumento = numDocumento;
	}

	/**
	 * @param estadoDocumento
	 *            the estadoDocumento to set
	 */
	public void setEstadoDocumento(String estadoDocumento) {
		this.estadoDocumento = estadoDocumento;
	}

	/**
	 * @param tipoMoneda
	 *            the tipoMoneda to set
	 */
	public void setTipoMoneda(String tipoMoneda) {
		this.tipoMoneda = tipoMoneda;
	}

	/**
	 * @param montoFijo
	 *            the montoFijo to set
	 */
	public void setMontoFijo(Double montoFijo) {
		this.montoFijo = montoFijo;
	}

	/**
	 * @param detalleConceptos
	 *            the detalleConceptos to set
	 */
	public void setDetalleConceptos(List<DetalleConceptos> detalleConceptos) {
		this.detalleConceptos = detalleConceptos;
	}

}
