/**
 * 
 */
package pe.com.claro.post.documentosSaldoReclamo.one.canonical.response;

import java.math.BigDecimal;

import javax.xml.datatype.XMLGregorianCalendar;

/**
 * @author everis
 *
 */
public class DetalleEstadoCta {

	private String tipoDocumento;
	private String nroDocumento;
	private XMLGregorianCalendar fechaEmision;
	private BigDecimal montoReclamado;
	private String tipoMoneda;

	
	/**
	 * @return the tipoMoneda
	 */
	public String getTipoMoneda() {
		return tipoMoneda;
	}
	/**
	 * @param tipoMoneda the tipoMoneda to set
	 */
	public void setTipoMoneda(String tipoMoneda) {
		this.tipoMoneda = tipoMoneda;
	}
	/**
	 * @return the montoReclamado
	 */
	public BigDecimal getMontoReclamado() {
		return montoReclamado;
	}
	/**
	 * @param montoReclamado the montoReclamado to set
	 */
	public void setMontoReclamado(BigDecimal montoReclamado) {
		this.montoReclamado = montoReclamado;
	}
	/**
	 * @return the tipoDocumento
	 */
	public String getTipoDocumento() {
		return tipoDocumento;
	}
	/**
	 * @param tipoDocumento the tipoDocumento to set
	 */
	public void setTipoDocumento(String tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}
	/**
	 * @return the nroDocumento
	 */
	public String getNroDocumento() {
		return nroDocumento;
	}
	/**
	 * @param nroDocumento the nroDocumento to set
	 */
	public void setNroDocumento(String nroDocumento) {
		this.nroDocumento = nroDocumento;
	}
	/**
	 * @return the fechaEmision
	 */
	public XMLGregorianCalendar getFechaEmision() {
		return fechaEmision;
	}
	/**
	 * @param fechaEmision the fechaEmision to set
	 */
	public void setFechaEmision(XMLGregorianCalendar fechaEmision) {
		this.fechaEmision = fechaEmision;
	}
	
	

}
