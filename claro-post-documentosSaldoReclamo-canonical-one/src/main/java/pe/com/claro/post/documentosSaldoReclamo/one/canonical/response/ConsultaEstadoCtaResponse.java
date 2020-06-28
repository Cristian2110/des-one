/**
 * 
 */
package pe.com.claro.post.documentosSaldoReclamo.one.canonical.response;

/**
 * @author everis
 *
 */
public class ConsultaEstadoCtaResponse {

	private String tipoDocWS;
	private String tipoDocSP;
	private String moneda;
	private String numDoc;

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
	 * @return the tipoDocWS
	 */
	public String getTipoDocWS() {
		return tipoDocWS;
	}

	/**
	 * @return the tipoDocSP
	 */
	public String getTipoDocSP() {
		return tipoDocSP;
	}

	/**
	 * @param tipoDocWS
	 *            the tipoDocWS to set
	 */
	public void setTipoDocWS(String tipoDocWS) {
		this.tipoDocWS = tipoDocWS;
	}

	/**
	 * @param tipoDocSP
	 *            the tipoDocSP to set
	 */
	public void setTipoDocSP(String tipoDocSP) {
		this.tipoDocSP = tipoDocSP;
	}

}
