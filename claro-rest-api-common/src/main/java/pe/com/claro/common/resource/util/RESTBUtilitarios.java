/** 
* @author: Jhon Egoavil
* @clase: RESTBUtilitarios.java 
* @descripción: Clase util para el servicio.
* @fecha_de_creación: 25-05-2017 
* @fecha_de_ultima_actualización: 05-06-2017
* @versión 1.0
* @copyright
*/ 

package pe.com.claro.common.resource.util;

import java.io.StringWriter;
import java.util.HashMap;

import javax.ws.rs.core.HttpHeaders;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.Marshaller;
import javax.xml.namespace.QName;

import org.apache.log4j.Logger;

import pe.com.claro.common.property.Constantes;

public class RESTBUtilitarios {

	private static transient Logger logger = Logger.getLogger(RESTBUtilitarios.class);  

	@SuppressWarnings( Constantes.RAWTYPES )
    private static HashMap<Class, JAXBContext> mapContexts = new HashMap<Class, JAXBContext>();

    /**
     * Contexto de la clase
     * @param Clase java
     * @return JAXBContext
     */
    @SuppressWarnings( Constantes.RAWTYPES )
    private static JAXBContext obtainJaxBContextFromClass(Class clas) {
        JAXBContext context = mapContexts.get(clas);

        if (context == null) {
            try {
                context = JAXBContext.newInstance(clas);
                mapContexts.put(clas, context);
            } catch (Exception e) {
            	logger.error(Constantes.ERROR_JAXB_CONTEXT + e);
            }
        }
        return context;
    }

    /**
     * Convertir request/response a Texto
     * @param Objeto Java
     * @return String
     */
    @SuppressWarnings( { Constantes.UNCHECKED, Constantes.RAWTYPES } )
    public static String anyObjectToXmlText(Object objJaxB) {
        String commandoRequestEnXml = null;
        JAXBContext context;

        try {
            context = obtainJaxBContextFromClass(objJaxB.getClass());
            Marshaller marshaller = context.createMarshaller();
            StringWriter xmlWriter = new StringWriter();
            marshaller.marshal(new JAXBElement(new QName(Constantes.EMPTY, objJaxB
            		.getClass().getName()), objJaxB.getClass(), objJaxB), xmlWriter);
        } catch (Exception e) {
        	logger.error(Constantes.ERROR_PARSE_XML + e);
        }
        return commandoRequestEnXml;
    }

    public static String getTransaccion(String nomMetodo, 
    		HttpHeaders httpHeaders){
    	StringBuffer stringBuffer = new StringBuffer();

    	String idTransaccion = httpHeaders.getRequestHeader(Constantes.HEADER_PARAM_ID_TRANSACCION)!=null?httpHeaders.getRequestHeader(
						Constantes.HEADER_PARAM_ID_TRANSACCION).get(0):Constantes.EMPTY;
		String msgid = httpHeaders.getRequestHeader(Constantes.HEADER_PARAM_MSG_ID)!=null?httpHeaders.getRequestHeader(
										Constantes.HEADER_PARAM_MSG_ID).get(0):Constantes.EMPTY;
		
		stringBuffer.append(Constantes.INI_CORCHETE);
		stringBuffer.append(nomMetodo);
		stringBuffer.append(Constantes.ID_TXT);
		stringBuffer.append(idTransaccion);
		stringBuffer.append(Constantes.MSG_ID);
		stringBuffer.append(msgid);
		stringBuffer.append(Constantes.FIN_CORCHETE);

		return stringBuffer.toString();
    }

    public static String loggerInicio(String mensajeTransaccion, String metodo){
    	StringBuffer stringBuffer = new StringBuffer();

    	stringBuffer.append(mensajeTransaccion);
        stringBuffer.append(Constantes.INICIO_METODO);
        stringBuffer.append(metodo);
        stringBuffer.append(Constantes.FIN_CORCHETE);
        return stringBuffer.toString();
    }

    public static String loggerParametros(String mensajeTransaccion, 
    		String msgDatos, String input){
    	StringBuffer stringBuffer = new StringBuffer();

    	stringBuffer.append(mensajeTransaccion);
        stringBuffer.append(msgDatos);
        stringBuffer.append(input);
        return stringBuffer.toString();
    }    
    
    public static String loggerResultado(String mensajeTransaccion, 
    		String msgDatos, Object result){
    	StringBuffer stringBuffer = new StringBuffer();

    	stringBuffer.append(mensajeTransaccion);
        stringBuffer.append(msgDatos);
        stringBuffer.append(result);
        return stringBuffer.toString();
    }
    
    public static String loggerMetodo(String mensajeTransaccion, String descripcion){
    	StringBuffer stringBuffer = new StringBuffer();

    	stringBuffer.append(mensajeTransaccion);
    	stringBuffer.append(Constantes.EJECUTANDO);
        stringBuffer.append(Constantes.INI_CORCHETE1);
        stringBuffer.append(descripcion);
        stringBuffer.append(Constantes.FIN_CORCHETE);
        return stringBuffer.toString();
    }

    public static String loggerFin(String mensajeTransaccion, String actFinal, String metodo, Double tiempoInicio){
    	StringBuffer stringBuffer = new StringBuffer();

		stringBuffer.append(mensajeTransaccion);
		stringBuffer.append(actFinal);
		stringBuffer.append(Constantes.FIN_METODO);
		stringBuffer.append(metodo);
		stringBuffer.append(Constantes.TIEMPO_TOTAL);
		stringBuffer.append((System.currentTimeMillis() - tiempoInicio));
		stringBuffer.append(Constantes.MILISEG);
		return stringBuffer.toString();
    }
	
	public static Object loggerException(String mensajeTransaccion, String error) {
	
		StringBuffer stringBuffer = new StringBuffer();
    	stringBuffer.append(mensajeTransaccion);
    	stringBuffer.append(Constantes.ERROR_EXCEPCION);
		stringBuffer.append(Constantes.GUION);
		stringBuffer.append(Constantes.INI_CORCHETE);
		stringBuffer.append(error);
		stringBuffer.append(Constantes.FIN_CORCHETE);
		return stringBuffer.toString();
	}
	
	public static Object loggerException2(String nomMetodo, HttpHeaders httpHeaders, Exception e) {
		StringBuffer stringBuffer = new StringBuffer();
    	String idTransaccion = httpHeaders.getRequestHeader(Constantes
				.HEADER_PARAM_ID_TRANSACCION)!=null?httpHeaders.getRequestHeader(
						Constantes.HEADER_PARAM_ID_TRANSACCION).get(0):Constantes.EMPTY;

		stringBuffer.append(Constantes.INI_CORCHETE);
		stringBuffer.append(nomMetodo);
		stringBuffer.append(Constantes.ID_TXT);
		stringBuffer.append(idTransaccion);
		stringBuffer.append(Constantes.FIN_CORCHETE);
		stringBuffer.append(Constantes.GUION);
		stringBuffer.append(Constantes.INI_CORCHETE);
		stringBuffer.append(e.toString());
		stringBuffer.append(Constantes.FIN_CORCHETE);
		return stringBuffer.toString();
	}
	
	public static Object loggerIdt(String mensajeTransaccion, String mensajeError) {
		
		StringBuffer stringBuffer = new StringBuffer();
    	stringBuffer.append(mensajeTransaccion);
		stringBuffer.append(Constantes.GUION);
		stringBuffer.append(Constantes.INI_CORCHETE);
		stringBuffer.append(mensajeError);
		stringBuffer.append(Constantes.FIN_CORCHETE);
		return stringBuffer.toString();
	}
	
	public static String loggerConsultaSP(String mensajeTransaccion, String nombreBd, String jndi,
			int tiempoEjecucion) {
		
		StringBuffer stringBuffer = new StringBuffer();
    	stringBuffer.append(mensajeTransaccion);
    	stringBuffer.append(Constantes.CONSULTANDO_BD);
    	stringBuffer.append(nombreBd);
    	stringBuffer.append(Constantes.CON_JNDI);
    	stringBuffer.append(jndi);
		stringBuffer.append(Constantes.PUNTO_ESPACIO);
		stringBuffer.append(ClaroUtil.convertProperties(Constantes.TIEMPO_EJECUCION));
		stringBuffer.append(tiempoEjecucion);
		stringBuffer.append(Constantes.MILISEG);
		return stringBuffer.toString();
	}
	
}
