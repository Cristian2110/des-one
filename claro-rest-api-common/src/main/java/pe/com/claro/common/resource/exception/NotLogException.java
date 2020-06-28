
package pe.com.claro.common.resource.exception;

public class NotLogException extends ComunesExceptionBean {
	/**
	 *
	 */
	private static final long serialVersionUID = 8992570382210399390L;


	public NotLogException(int code, String msg) {
		super(code, msg);
	}

	public NotLogException(int status, int code, String msg, String developerMessage) {
		super(status, code, msg, developerMessage);
	}

	public NotLogException(int code, String mensajeRespuesta, Exception developerMessage) {
		super(code, mensajeRespuesta, developerMessage);
	}
	public NotLogException(int code, String mensajeRespuesta, String developerMessage) {
		super(code, mensajeRespuesta, developerMessage);
	}


}
