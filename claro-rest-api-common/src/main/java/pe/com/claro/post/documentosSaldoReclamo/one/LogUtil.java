package pe.com.claro.post.documentosSaldoReclamo.one;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Created by jvasquee on 11/07/2017.
 */

public class LogUtil {

	private Logger log;
	private String nombreMetodo = "";
	private ObjectMapper mapper = new ObjectMapper();
	private long fInicial;

	public LogUtil(Class nombreClase) {

		log = LoggerFactory.getLogger(nombreClase);
	}

	public void inicioMetodo(String nombreMetodo) {
		fInicial = System.currentTimeMillis();
		this.nombreMetodo = nombreMetodo;
		log.info("inicioMetodo metodo: " + nombreMetodo);
	}

	public void inicioMetodoDebug(String nombreMetodo) {
		fInicial = System.currentTimeMillis();
		this.nombreMetodo = nombreMetodo;
		log.debug("inicioMetodo metodo: " + nombreMetodo);
	}

	public void finMetodo() {
		log.info("fin metodo: " + this.nombreMetodo + " tiempo: " + (System.currentTimeMillis() - fInicial));
	}

	public void finMetodoDebug() {
		log.debug("fin metodo: " + this.nombreMetodo + " tiempo: " + (System.currentTimeMillis() - fInicial));
	}

	public void finMetodoDebug(String message) {
		log.debug("message:" + message + " fin metodo:" + this.nombreMetodo + " tiempo: "
				+ (System.currentTimeMillis() - fInicial));
	}

	public void info(String message) {
		log.info(message);
	}

	public void debug(String message) {
		log.debug(message);
	}

	public void error(String message, Exception e) {
		log.info(message, e);
	}

	public void requestToJson(Object request) {
		try {
			log.info("parametros request: " + nombreMetodo + ":" + mapper.writeValueAsString(request));
		} catch (JsonProcessingException ignore) {
		}
	}

	public void toJson(Object response) {
		try {
			log.info("parametros response: " + nombreMetodo + ":" + mapper.writeValueAsString(response));
		} catch (JsonProcessingException ignore) {
		}
	}

	public void response(String response) {
		log.info("response " + nombreMetodo + ":" + response);
	}

}
