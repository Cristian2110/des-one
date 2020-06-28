package pe.com.claro.common.property;

public class Constantes {


	/* Repository */
	public final static String PERSISTENCE_CONTEXT1 = "pe.com.claro.post.documentosSaldoReclamo.one.cobs";
	public final static String PERSISTENCE_CONTEXT2 = "pe.com.claro.post.documentosSaldoReclamo.one.mgr";

	public final static String METODO_CONSULTAR_DOC = "consultarDocumentos";
	public final static String METODO_BUSCAR_DOC = "buscarDocumento";
	public final static String METODO_VALIDAR_RECIBO = "validarRecibo";
	public final static String METODO_CONSULTAR_FACTURA = "consultarFacturaCliente";
	public final static String METODO_BUSCAR_PARAMETROS = "buscarParametros";

	public final static String METODO_OBTENER_CONCEPTOS_COBS = "obtenerConceptosCobs";
	public final static String METODO_OBTENER_EQUIV_COBS = "obtenerEquivConceptos";
	public final static String METODO_CONSULTA_CUENTA = "consultaEstadoCuenta";

	public final static String REPOSITORY = " - REPOSITORY(DAO)";
	public final static String PROPERTIES_BASE = "claro-post-documentosSaldoReclamo-one";
	public final static String PROPERTIES_CLARO = "claro.properties";
	public final static String PROPERTIES = "/.properties";

	public final static String PATH_BASE = "post/interaccion/v1.0.0/documentosSaldoReclamo-one";
	public final static String PATH_METODO1 = "/documentosSaldoReclamo";
	public final static String APPLICATION_JSON = "application/json;charset=UTF-8";
	public final static String WS_NOMBRE1 = "ConsultaEstadoCuentaWS";
	public final static String WS_NOMBRE2 = "ReclamoPostpagoWS";
	public final static String WS_NOMBRE3 = "ConsultarFacturasWS";

	public static final String FORMATO_FECHA_DDMMYYYY = "dd/MM/yyyy";
	public static final String ACT_1 = "Actividad 1. Validar Parametros de entrada";

	
	/* Utilitarios */
	public final static String RAWTYPES = "rawtypes";
	public final static String UNCHECKED = "unchecked";
	public final static String ERROR_JAXB_CONTEXT = "Error creando JAXBContext: ";
	public final static String ERROR_PARSE_XML = "Error parseando object to xml: ";
	public final static String HEADER_PARAM_ID_TRANSACCION = "idTransaccion";
	public final static String HEADER_PARAM_MSG_ID = "msgid";
	public final static String HEADER_PARAM_TIMESTAMP = "timestamp";
	public final static String HEADER_PARAM_USER_ID = "userId";
	public final static String HEADER_PARAM_ACCEPT = "Accept";
	public final static String ID_TXT = " idTx=";
	public final static String MSG_ID = " msgid=";
	public final static String TIMESTAMP = " timestamp=";
	public final static String USER_ID = " userId=";
	public final static String ACCEPT = " Accept=";
	public final static String INI_CORCHETE = "[";
	public final static String FIN_CORCHETE = "] ";
	public final static String INI_LLAVE = "{";
	public final static String FIN_LLAVE = "} ";
	public final static String GUION = "-";
	public final static String INICIO_METODO = "[ INICIO de m�todo ";
	public final static String INI_CORCHETE1 = " 1. [";
	public final static String EJECUTANDO = " Ejecutando SP: ";
	public final static String RESPONSE_RETORNAR = " RESPONSE a retornar: \n ";
	public final static String FIN_METODO = " FIN de m�todo ";
	public final static String TIEMPO_TOTAL = " ] Tiempo total de proceso (ms):";
	public final static String MILISEG = " milisegundos.";
	public final static String ERROR_EXCEPCION = "ERROR: [Exception] ";
	public final static String CONSULTANDO_BD = "Consultando BD ";
	public final static String CONSULTANDO_WS = "Consultando WS ";

	public final static String CON_JNDI = ", con JNDI=";
	public final static String TIEMPO_EJECUCION = "Tiempo permitido de ejecuci�n = ";
	public final static String PUNTO = ".";
	public final static String PUNTO_ESPACIO = ". ";
	public final static String EMPTY = "";
	public final static String MSG_PO_CURSOR = "[PO_CURSOR]";
	public final static String MSG_PO_CURSOR_ELE = "[PO_CURSOR] Elementos .....  \n";
	public final static String MSG_LLAVES = "[{}] = [{}]";
	public final static String TXT_VACIO = "VACIO";
	public final static String ESPACIO = " ";
	public final static String TXT_NULL = "NULL";
	public final static String CALL = "call ";
	public final static String CALL_2 = " (?,?)";
	
	public final static String CERO = "0";
	public final static String SUNO = "1";
	public final static String STRES = "3";
	public final static String MIL = "1000";

	public static final String NOMBREDB = "[nombreDB]";
	public static final String NOMBRESP = "[nombreSP]";
	public static final String FORMATOFECHADEFAULT = "dd/MM/yyyy HH:mm:ss";
	public final static String EXITO_SP = "Se invoc� con �xito el SP:  ";
	public final static String TIEMPO_EJECUCION_SP = "Tiempo de ejecutar SP: ";

	public final static String MSG_ERROR_FECHAS = "Error parseando fechas.";

	public static final String SQL_TIMEOUTEXCEPTION = "SQLTIMEOUTEXCEPTION";
	public final static String K_RESULTADO = "Lista size:";
	public static final String PALOTE_SEPARADOR_SPLIT = "\\|";
	public static final String DOS_PUNTOS = ":";
	public static final String CORCHETE_LLAVE = "[{}] = [{}]";
	public static final String LLAMADA_SP = "call ";

	public static final String DATOS_SALIDA = " Datos de salida WS: \n ";
	public static final String DATOS_ENTRADA = " Datos de entradaWS: \n ";

	public static final String CONSULTA_EXITOSA = "Consulta exitosa";
	public static final String NO_DATA = "No se encontraron datos";

	public static final String TIMEOUTEXCEPTION = "Timeout";
	public static final String TIMEOUTEXCEPTION2 = "Timed out";
	public final static int STATUS_DISPONIBILIDAD = 404;
	public final static int STATUS_TIME_OUT = 504;
	
	/* ENCODING */
	public static final String DEFAULT_ENCODING_PROPERTIES = "ISO-8859-1";
	public static final String DEFAULT_ENCODING_API = "UTF-8";
	public static final String ERROR_PARSEO_XML = "Error parseando object to xml:";
	
	public final static int VALOR_CERO = 0;
	public static final int UNO = 1;
	public static final int DOS = 2;
	public static final int TRES = 3;
	public static final int CUATRO = 4;
	public static final int CINCO = 5;
	public static final int SEIS = 6;
	public static final int SIETE = 7;
	public static final int OCHO = 8;
	public static final int NUEVE = 9;
	public static final String TRECE = "13";

	public static final Integer CERO_INT = 0;

	public static final String MONEDA_PEN = "PEN";
	public static final String MONEDA_MN = "MN";
	public static final String MONEDA_USD = "USD";
	public static final String MONEDA_US = "US";
	public static final String SIMBOLO_DOLAR = "$";
	public static final String SIMBOLO_SOLES = "S/.";
	public static final String DOC_RECIBO = "REC";
	public static final String DOC_ND = "ND";
	public static final String DOC_R = "R";

	// SP
	public static final String PI_K_CUSTOMERID = "K_CUSTOMERID";
	public static final String PI_K_FECHA_INICIO = "K_FECHA_INICIO";
	public static final String PI_K_FECHA_FIN = "K_FECHA_FIN";
	public static final String PO_K_FLAG_CONSUL = "K_FLAG_CONSUL";
	public static final String PO_K_MSG_CONSUL = "K_MSG_CONSUL";
	public static final String PO_K_CURSOR = "K_CURSOR";

	// SP PKG_SIAC_RECLAMOS_WEB.RECSS_MONTORECLAMO
	public static final String PI_K_DOC_RECLA = "K_DOC_RECLA";
	public static final String PI_K_TIPO_DOC = "K_TIPO_DOC";
	public static final String PI_K_COD_APLI = "K_COD_APLI";
	public static final String PO_K_CODIGO = "K_CODIGO";
	public static final String PO_K_ERROR_MSG = "K_ERROR_MSG";
	public static final String PO_CURSOR = "PO_CURSOR";

	// SP PKG_SIAC_GENERICO.SP_OBTIENE_LISTAS
	public static final String P_ID_LISTA = "P_ID_LISTA";
	public static final String P_CURSOR = "P_CURSOR";

	// SP PKG_SIAC_GENERICO.SP_OBTIENE_LISTAS
	public static final String PI_ESTADO = "PI_ESTADO";
	public static final String PO_CURSOR_PARAM = "PO_CURSOR";
	
	// CONSTANTES_NUEVAS
	public static final String PARAM = "{} = {}";
	public static final String TIEMPO = " tiempo: ";
	public static final String ERROR = "Error";
	public static final String CORCHETE = "] - [";
	
	//CONSTANTES ONE
	public static final String ID_TRANSACCION = "idTransaccion";
	public static final String MSGID = "msgid";
	public static final String TIMES_TAMP = "timestamp";
	public static final String USERID = "userId";
	public static final String VACIO = "";
	public static final String TIMEOUT = "Timeout";
	public final static String CONNECT_TIMEOUT = "com.sun.xml.ws.connect.timeout";
    public final static String REQUEST_TIMEOUT = "com.sun.xml.ws.request.timeout";
    public final static String NO_EXISTEN_REGISTROS = " No existen registros.";
}
