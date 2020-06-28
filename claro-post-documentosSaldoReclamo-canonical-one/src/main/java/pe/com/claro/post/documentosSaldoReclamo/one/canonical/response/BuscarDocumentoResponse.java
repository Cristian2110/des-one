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
public class BuscarDocumentoResponse extends Response {

	private String tipoDoc;
	private String numDoc;
	private XMLGregorianCalendar fecEmi;
	private XMLGregorianCalendar fecVenc;
	private String moneda;
	private BigDecimal montoTotal;

	
	/**
	 * @return the fecVenc
	 */
	public XMLGregorianCalendar getFecVenc() {
		return fecVenc;
	}

	/**
	 * @param fecVenc the fecVenc to set
	 */
	public void setFecVenc(XMLGregorianCalendar fecVenc) {
		this.fecVenc = fecVenc;
	}

	/**
	 * @return the tipoDoc
	 */
	public String getTipoDoc() {
		return tipoDoc;
	}

	/**
	 * @param tipoDoc
	 *            the tipoDoc to set
	 */
	public void setTipoDoc(String tipoDoc) {
		this.tipoDoc = tipoDoc;
	}

	/**
	 * @return the numDoc
	 */
	public String getNumDoc() {
		return numDoc;
	}

	/**
	 * @param numDoc
	 *            the numDoc to set
	 */
	public void setNumDoc(String numDoc) {
		this.numDoc = numDoc;
	}

	/**
	 * @return the fecEmi
	 */
	public XMLGregorianCalendar getFecEmi() {
		return fecEmi;
	}

	/**
	 * @param fecEmi
	 *            the fecEmi to set
	 */
	public void setFecEmi(XMLGregorianCalendar fecEmi) {
		this.fecEmi = fecEmi;
	}

	/**
	 * @return the moneda
	 */
	public String getMoneda() {
		return moneda;
	}

	/**
	 * @param moneda
	 *            the moneda to set
	 */
	public void setMoneda(String moneda) {
		this.moneda = moneda;
	}

	/**
	 * @return the montoTotal
	 */
	public BigDecimal getMontoTotal() {
		return montoTotal;
	}

	/**
	 * @param montoTotal
	 *            the montoTotal to set
	 */
	public void setMontoTotal(BigDecimal montoTotal) {
		this.montoTotal = montoTotal;
	}

}
