package pe.com.claro.common.resource.exception;

public class ComunesExceptionBean extends ApiException {

    /**
	 * 
	 */
	private static final long serialVersionUID = -7735540171761008154L;
	
	String mensajeRespuesta;

    ComunesExceptionBean(int codigoRespuesta, String msg) {
        super(codigoRespuesta);
        this.mensajeRespuesta = msg;
    }

    ComunesExceptionBean(int status, int codigoRespuesta, String mensajeRespuesta, String mensajeError) {
        super(status, codigoRespuesta, mensajeError);
        this.mensajeRespuesta = mensajeRespuesta;
    }

    ComunesExceptionBean(int codigoRespuesta, String msg, Exception mensajeError) {
        super(codigoRespuesta, msg, mensajeError);
        this.mensajeRespuesta = msg;
    }
    ComunesExceptionBean(int codigoRespuesta, String msg, String mensajeError) {
        super(codigoRespuesta, msg, mensajeError);
        this.mensajeRespuesta = msg;
    }

    public String getMensajeRespuesta() {
        return mensajeRespuesta;
    }

    public void setMensajeRespuesta(String mensajeError) {
        this.mensajeRespuesta = mensajeError;
    }
}
