/**
 * 
 */
package pe.com.claro.post.documentosSaldoReclamo.one.canonical.response;

/**
 * @author everis
 *
 */
public class DetalleConceptos {

	private String concepto;
	private String valor;
	private Double montoFacturado;
	private String estadoConcepto;
	
	
	/**
	 * @return the valor
	 */
	public String getValor() {
		return valor;
	}

	/**
	 * @param valor the valor to set
	 */
	public void setValor(String valor) {
		this.valor = valor;
	}

	/**
	 * @return the concepto
	 */
	public String getConcepto() {
		return concepto;
	}

	/**
	 * @return the montoFacturado
	 */
	public Double getMontoFacturado() {
		return montoFacturado;
	}
	/**
	 * @return the estadoConcepto
	 */
	public String getEstadoConcepto() {
		return estadoConcepto;
	}
	/**
	 * @param concepto the concepto to set
	 */
	public void setConcepto(String concepto) {
		this.concepto = concepto;
	}
	/**
	 * @param montoFacturado the montoFacturado to set
	 */
	public void setMontoFacturado(Double montoFacturado) {
		this.montoFacturado = montoFacturado;
	}
	/**
	 * @param estadoConcepto the estadoConcepto to set
	 */
	public void setEstadoConcepto(String estadoConcepto) {
		this.estadoConcepto = estadoConcepto;
	}
	
	
}
