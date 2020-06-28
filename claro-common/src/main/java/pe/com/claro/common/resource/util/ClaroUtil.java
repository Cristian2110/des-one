package pe.com.claro.common.resource.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import pe.com.claro.common.property.Constantes;

public class ClaroUtil {

	private static final Logger LOG = LoggerFactory.getLogger(ClaroUtil.class);

	public static String verifiyNull(Object object) {
		String a = null;
		if (object != null) {
			a = object.toString();
		}
		return a;
	}

	public static String verifiyNull2(Object object) {
		String a = Constantes.EMPTY;
		if (object != null) {
			a = object.toString();
		}
		return a;
	}

	public static String convertProperties(Object object) {
		String a = null;
		if (object != null) {
			a = object.toString();
			try {
				a = new String(a.getBytes(Constantes.DEFAULT_ENCODING_PROPERTIES), Constantes.DEFAULT_ENCODING_API);
			} catch (Exception e) {
				LOG.error("Error getProperties Encoding Failed, trayendo Encoding por defecto", e);
			}
		}
		return a;
	}

}