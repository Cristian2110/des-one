/**
 * 
 */
package pe.com.claro.common.property;

import javax.ws.rs.core.Configuration;

import pe.com.claro.common.resource.util.ClaroUtil;

/**
 * @author everis
 *
 */
public class PropertiesExternos {

	private String bdCobsNombre;
	private String bdCobsJndi;
	private String bdCobsOwner;
	private String bdCobsExecuteTimeout;
	private String bdCobsPkgSiacReclamos;
	private String bdCobsSpRecssMontoreclamo;
	private String bdCobsPkgSiacGenerico;
	private String bdCobsSpObtieneListas;

	private String bdMgrNombre;
	private String bdMgrJndi;
	private String bdMgrOwner;
	private String bdMgrExecuteTimeout;
	private String bdMgrPkgParamReclamos;
	private String bdMgrSpListaParametro;

	private String estadoParametro;

	private String consultaEstadoCuentaOsbConsultaCuentaEndpointUrl;
	private String consultaEstadoCuentaOsbConsultaCuentaConexionTimeout;
	private String consultaEstadoCuentaOsbConsultaCuentaExecutionTimeout;

	private String consultaFacturasOsbConsultaFacturasClienteEndpointUrl;
	private String consultaFacturasOsbConsultaFacturasClienteConexionTimeout;
	private String consultaFacturasOsbConsultaFacturasClienteExecutionTimeout;

	private String reclamoPostpagoWsValidarReciboEndpointUrl;
	private String reclamoPostpagoWsValidarReciboConexionTimeout;
	private String reclamoPostpagoWsValidarReciboExecutionTimeout;

	private String codigoProcedureGenericoErrorIdt1;
	private String mensajeProcedureGenericoErrorIdt1;
	private String codigoProcedureGenericoErrorIdt2;
	private String mensajeProcedureGenericoErrorIdt2;
	private String codigoProcedureGenericoErrorIdt3;
	private String mensajeProcedureGenericoErrorIdt3;

	private String usuarioValido;
	private String aplicacionValida;
	private String ipValida;
	private String nombreAplicativo;

	private String idListaSp;
	private String aplicativoSp;

	private String tiposDocumentosRecBscs;
	private String tiposDocumentosNdBscs;

	private String conceptoCargoFijo;
	private String conceptoClaroServicios;
	private String conceptoOcargo;
	private String conceptoOcargoIgv;
	private String conceptoOcargoMora;
	private String conceptoTraficoAdicional;
	private String conceptoTraficoConsumo;
	private String conceptoLdi;
	private String conceptoLdn;
	private String conceptoRoaming;

	private String codigoRespuestaIdf0;
	private String mensajeRespuestaIdf0;
	private String codigoRespuestaIdf1;
	private String mensajeRespuestaIdf1;
	private String codigoRespuestaIdf2;
	private String mensajeRespuestaIdf2;
	private String codigoRespuestaIdt1;
	private String mensajeRespuestaIdt1;
	private String codigoRespuestaIdt2;
	private String mensajeRespuestaIdt2;
	private String codigoRespuestaIdt3;
	private String mensajeRespuestaIdt3;
	private String codigoRespuestaIdt4;
	private String mensajeRespuestaIdt4;
	//VARIABLE ONE
	public final String flagConvivencia;
	private String consultaLineaCuentaWSTipoConsulta;
	private String consultaLineaCuentaWSEndpointUrl;
	private String consultaLineaCuentaWSConexionTimeout;
    private String consultaLineaCuentaWSExecutionTimeout;
    public final String idt1Codigo;
    public final String idt1Mensaje;
    public final String idt2Codigo;
    public final String idt2Mensaje;
    public final String idt3Codigo;
    public final String idt3Mensaje;
    public final String consultaLineaCuentaWS;
    public final String consultaLineaCuentaWSMetodo;
    public final String consultarDocumentosIDF3Cod;
    public final String consultarDocumentosIDF3Msj;
    public final String consultarDocumentosIDF4Cod;
    public final String consultarDocumentosIDF4Msj;
    public final String consultarDocumentosIDF5Cod;
    public final String consultarDocumentosIDF5Msj;
    public final String wsFacturaFlagBusqueda;
    private String obtenerCategoriaEndpointUrlBasePath;
    public final String obtenerCategoriaMetodo;
    public final String obtenerCategoriaNombre;
    public final String cbioKey;
    public final String cbioValue;
    public final String wsContractsSearchServiceURL;
    public final String wsContractsSearchServiceMetodo;
    public final String wsContractsSearchServiceNombre;
    public final String wsContractsSearchServiceTimeoutConnect;
    public final String wsContractsSearchServiceTimeoutRequest;
    public final String wsFacturaURL;
    public final String wsFacturaTimeoutConnection;
    public final String wsFacturaTimeoutRequest;
    public final String wsFacturaNombreServicio;
    public final String wsFacturaNombreMetodo;
    //Cambios one Junio
    public final String cs_inputAttributes_searcher;
    public final String cs_sessionchange_key;
    public final String cs_sessionchange_value;
    
    public final String wsContractsSearchNombre;
    public final String wsContractsSearchMetodo;
    public final String wsContractsSearchWsdl;
    public final String wsContractsSearchTimeoutConnect;
    public final String wsContractsSearchTimeoutRequest;
    
    public final String flagBusquedaFact;
    public final String obtenerFacturaNombre;
    private String obtenerFacturaEndpointUrlBasePath;
    public final String obtenerFacturaMetodo;
    private String obtenerFacturaConexionTimeout;
    private String obtenerFacturaExecutionTimeout;
    
    private String obtenerCategoriaConexionTimeout;
    private String obtenerCategoriaExecutionTimeout;
    
    public final String consultarDocumentosIDF1Cod;
    public final String consultarDocumentosIDF1Msj;
    public final String consultarDocumentosIDF2Cod;
    public final String consultarDocumentosIDF2Msj;
    
    
    
    
    
    public String getConsultarDocumentosIDF1Cod() {
      return consultarDocumentosIDF1Cod;
    }

    public String getConsultarDocumentosIDF1Msj() {
      return consultarDocumentosIDF1Msj;
    }

    public String getConsultarDocumentosIDF2Cod() {
      return consultarDocumentosIDF2Cod;
    }

    public String getConsultarDocumentosIDF2Msj() {
      return consultarDocumentosIDF2Msj;
    }
    
	public String getObtenerCategoriaExecutionTimeout() {
      return obtenerCategoriaExecutionTimeout;
    }

    public void setObtenerCategoriaExecutionTimeout(String obtenerCategoriaExecutionTimeout) {
      this.obtenerCategoriaExecutionTimeout = obtenerCategoriaExecutionTimeout;
    }

  public String getObtenerCategoriaConexionTimeout() {
      return obtenerCategoriaConexionTimeout;
    }

    public void setObtenerCategoriaConexionTimeout(String obtenerCategoriaConexionTimeout) {
      this.obtenerCategoriaConexionTimeout = obtenerCategoriaConexionTimeout;
    }

  public String getObtenerFacturaEndpointUrlBasePath() {
      return obtenerFacturaEndpointUrlBasePath;
    }

    public void setObtenerFacturaEndpointUrlBasePath(String obtenerFacturaEndpointUrlBasePath) {
      this.obtenerFacturaEndpointUrlBasePath = obtenerFacturaEndpointUrlBasePath;
    }

    public String getObtenerFacturaConexionTimeout() {
      return obtenerFacturaConexionTimeout;
    }

    public void setObtenerFacturaConexionTimeout(String obtenerFacturaConexionTimeout) {
      this.obtenerFacturaConexionTimeout = obtenerFacturaConexionTimeout;
    }

    public String getObtenerFacturaExecutionTimeout() {
      return obtenerFacturaExecutionTimeout;
    }

    public void setObtenerFacturaExecutionTimeout(String obtenerFacturaExecutionTimeout) {
      this.obtenerFacturaExecutionTimeout = obtenerFacturaExecutionTimeout;
    }

    public String getObtenerFacturaNombre() {
      return obtenerFacturaNombre;
    }

    public String getObtenerFacturaMetodo() {
      return obtenerFacturaMetodo;
    }

  public String getFlagBusquedaFact() {
      return flagBusquedaFact;
    }

  public String getWsContractsSearchNombre() {
      return wsContractsSearchNombre;
    }

    public String getWsContractsSearchMetodo() {
      return wsContractsSearchMetodo;
    }
    public String getWsContractsSearchWsdl() {
      return wsContractsSearchWsdl;
    }
    public String getWsContractsSearchTimeoutConnect() {
      return wsContractsSearchTimeoutConnect;
    }

    public String getWsContractsSearchTimeoutRequest() {
      return wsContractsSearchTimeoutRequest;
    }

  public String getCs_sessionchange_key() {
      return cs_sessionchange_key;
    }

    public String getCs_sessionchange_value() {
      return cs_sessionchange_value;
    }

  public String getCs_inputAttributes_searcher() {
      return cs_inputAttributes_searcher;
    }

  public String getConsultaLineaCuentaWS() {
      return consultaLineaCuentaWS;
    }

    public String getConsultaLineaCuentaWSMetodo() {
      return consultaLineaCuentaWSMetodo;
    }

  public String getIdt1Codigo() {
      return idt1Codigo;
    }

    public String getIdt1Mensaje() {
      return idt1Mensaje;
    }

    public String getIdt2Codigo() {
      return idt2Codigo;
    }

    public String getIdt2Mensaje() {
      return idt2Mensaje;
    }

    public String getIdt3Codigo() {
      return idt3Codigo;
    }

    public String getIdt3Mensaje() {
      return idt3Mensaje;
    }

    public void setConsultaLineaCuentaWSTipoConsulta(String consultaLineaCuentaWSTipoConsulta) {
      this.consultaLineaCuentaWSTipoConsulta = consultaLineaCuentaWSTipoConsulta;
    }

    public void setConsultaLineaCuentaWSEndpointUrl(String consultaLineaCuentaWSEndpointUrl) {
      this.consultaLineaCuentaWSEndpointUrl = consultaLineaCuentaWSEndpointUrl;
    }

    public void setConsultaLineaCuentaWSConexionTimeout(String consultaLineaCuentaWSConexionTimeout) {
      this.consultaLineaCuentaWSConexionTimeout = consultaLineaCuentaWSConexionTimeout;
    }

    public void setConsultaLineaCuentaWSExecutionTimeout(String consultaLineaCuentaWSExecutionTimeout) {
      this.consultaLineaCuentaWSExecutionTimeout = consultaLineaCuentaWSExecutionTimeout;
    }
    
    public String getObtenerCategoriaEndpointUrlBasePath() {
      return obtenerCategoriaEndpointUrlBasePath;
  }
    
    public String getObtenerCategoriaMetodo() {
      return obtenerCategoriaMetodo;
  }
    
    public String getObtenerCategoriaNombre() {
      return obtenerCategoriaNombre;
  }

  public PropertiesExternos(Configuration configuration) {
		this.bdCobsNombre = ClaroUtil.convertProperties(configuration.getProperty("bd.cobs.nombre"));
		this.bdCobsJndi = ClaroUtil.convertProperties(configuration.getProperty("bd.cobs.jndi"));
		this.bdCobsOwner = ClaroUtil.convertProperties(configuration.getProperty("bd.cobs.owner"));
		this.bdCobsExecuteTimeout = ClaroUtil.convertProperties(configuration.getProperty("bd.cobs.execute.timeout"));

		this.bdCobsPkgSiacReclamos = ClaroUtil
				.convertProperties(configuration.getProperty("bd.cobs.pkg.siac.reclamos"));
		this.bdCobsSpRecssMontoreclamo = ClaroUtil
				.convertProperties(configuration.getProperty("bd.cobs.sp.recss.montoreclamo"));

		this.bdCobsPkgSiacGenerico = ClaroUtil
				.convertProperties(configuration.getProperty("bd.cobs.pkg.siac.generico"));
		this.bdCobsSpObtieneListas = ClaroUtil
				.convertProperties(configuration.getProperty("bd.cobs.sp.obtiene.listas"));

		this.bdMgrNombre = ClaroUtil.convertProperties(configuration.getProperty("bd.mgr.nombre"));
		this.bdMgrJndi = ClaroUtil.convertProperties(configuration.getProperty("bd.mgr.jndi"));
		this.bdMgrOwner = ClaroUtil.convertProperties(configuration.getProperty("bd.mgr.owner"));
		this.bdMgrExecuteTimeout = ClaroUtil.convertProperties(configuration.getProperty("bd.mgr.execute.timeout"));
		this.bdMgrPkgParamReclamos = ClaroUtil.convertProperties(configuration.getProperty("bd.mgr.pkg.param.reclamo"));
		this.bdMgrSpListaParametro = ClaroUtil
				.convertProperties(configuration.getProperty("bd.mgr.sp.lista.parametro"));

		this.estadoParametro = ClaroUtil.convertProperties(configuration.getProperty("estado.parametro"));

		this.consultaEstadoCuentaOsbConsultaCuentaEndpointUrl = ClaroUtil
				.convertProperties(configuration.getProperty("consultaEstadoCuenta.osb.consultaCuenta.endpoint.url"));
		this.consultaEstadoCuentaOsbConsultaCuentaConexionTimeout = ClaroUtil.convertProperties(
				configuration.getProperty("consultaEstadoCuenta.osb.consultaCuenta.conexion.timeout"));
		this.consultaEstadoCuentaOsbConsultaCuentaExecutionTimeout = ClaroUtil.convertProperties(
				configuration.getProperty("consultaEstadoCuenta.osb.consultaCuenta.execution.timeout"));

		this.consultaFacturasOsbConsultaFacturasClienteEndpointUrl = ClaroUtil.convertProperties(
				configuration.getProperty("consultaFacturas.osb.consultaFacturasCliente.endpoint.url"));
		this.consultaFacturasOsbConsultaFacturasClienteConexionTimeout = ClaroUtil.convertProperties(
				configuration.getProperty("consultaFacturas.osb.consultaFacturasCliente.conexion.timeout"));
		this.consultaFacturasOsbConsultaFacturasClienteExecutionTimeout = ClaroUtil.convertProperties(
				configuration.getProperty("consultaFacturas.osb.consultaFacturasCliente.execution.timeout"));
		
		this.consultaLineaCuentaWSEndpointUrl = ClaroUtil
				.convertProperties(configuration.getProperty("consultaLineaCuenta.ws.consultarLineaCuenta.endpoint.url"));
		this.consultaLineaCuentaWSConexionTimeout = ClaroUtil
				.convertProperties(configuration.getProperty("consultaLineaCuenta.ws.consultarLineaCuenta.conexion.timeout"));
		this.consultaLineaCuentaWSExecutionTimeout = ClaroUtil
				.convertProperties(configuration.getProperty("consultaLineaCuenta.ws.consultarLineaCuenta.execution.timeout"));
		this.consultaLineaCuentaWSTipoConsulta = ClaroUtil
				.convertProperties(configuration.getProperty("consultaLineaCuenta.ws.consultarLineaCuenta.tipoConsulta"));

		this.reclamoPostpagoWsValidarReciboEndpointUrl = ClaroUtil
				.convertProperties(configuration.getProperty("reclamoPostpago.ws.validarRecibo.endpoint.url"));
		this.reclamoPostpagoWsValidarReciboConexionTimeout = ClaroUtil
				.convertProperties(configuration.getProperty("reclamoPostpago.ws.validarRecibo.conexion.timeout"));
		this.reclamoPostpagoWsValidarReciboExecutionTimeout = ClaroUtil
				.convertProperties(configuration.getProperty("reclamoPostpago.ws.validarRecibo.execution.timeout"));

		this.codigoProcedureGenericoErrorIdt1 = ClaroUtil
				.convertProperties(configuration.getProperty("codigo.procedure.generico.error.idt1"));
		this.mensajeProcedureGenericoErrorIdt1 = ClaroUtil
				.convertProperties(configuration.getProperty("mensaje.procedure.generico.error.idt1"));
		this.codigoProcedureGenericoErrorIdt2 = ClaroUtil
				.convertProperties(configuration.getProperty("codigo.procedure.generico.error.idt2"));
		this.mensajeProcedureGenericoErrorIdt2 = ClaroUtil
				.convertProperties(configuration.getProperty("mensaje.procedure.generico.error.idt2"));
		this.codigoProcedureGenericoErrorIdt3 = ClaroUtil
				.convertProperties(configuration.getProperty("codigo.procedure.generico.error.idt3"));
		this.mensajeProcedureGenericoErrorIdt3 = ClaroUtil
				.convertProperties(configuration.getProperty("mensaje.procedure.generico.error.idt3"));
		
		this.consultarDocumentosIDF1Cod = ClaroUtil
	        .convertProperties(configuration.getProperty("consultarDocumentos.idf1.cod"));
	    this.consultarDocumentosIDF1Msj = ClaroUtil
	        .convertProperties(configuration.getProperty("consultarDocumentos.idf1.msj"));
	    this.consultarDocumentosIDF2Cod = ClaroUtil
	        .convertProperties(configuration.getProperty("consultarDocumentos.idf2.cod"));
	    this.consultarDocumentosIDF2Msj = ClaroUtil
	        .convertProperties(configuration.getProperty("consultarDocumentos.idf2.msj"));
	    
	    
		this.cs_inputAttributes_searcher = ClaroUtil
            .convertProperties(configuration.getProperty("cs.inputAttributes.searcher"));		
		this.cs_sessionchange_key = ClaroUtil.convertProperties(configuration.getProperty("cs.sessionchange.key"));
	    this.cs_sessionchange_value = ClaroUtil.convertProperties(configuration.getProperty("cs.sessionchange.value"));
	   
	    this.wsContractsSearchNombre = ClaroUtil.convertProperties(configuration.getProperty("ws.contractssearch.nombre"));
	    this.wsContractsSearchMetodo = ClaroUtil.convertProperties(configuration.getProperty("ws.contractssearch.metodo"));
	    this.wsContractsSearchWsdl = ClaroUtil.convertProperties(configuration.getProperty("ws.contractssearch.wsdl"));
	    this.wsContractsSearchTimeoutConnect = ClaroUtil
	        .convertProperties(configuration.getProperty("ws.contractssearch.timeout.connect"));
	    this.wsContractsSearchTimeoutRequest = ClaroUtil
	        .convertProperties(configuration.getProperty("ws.contractssearch.timeout.request"));
	    
	    this.flagBusquedaFact = ClaroUtil
	        .convertProperties(configuration.getProperty("factura.ws.listarDocReferencia.flagbusqueda"));
	    this.obtenerFacturaNombre = ClaroUtil
	        .convertProperties(configuration.getProperty("factura.ws.listarDocReferencia.nombre"));
	    this.obtenerFacturaEndpointUrlBasePath = ClaroUtil
	        .convertProperties(configuration.getProperty("factura.ws.listarDocReferencia.url.base"));
	    this.obtenerFacturaMetodo = ClaroUtil
	        .convertProperties(configuration.getProperty("factura.ws.listarDocReferencia.metodo"));
	    this.obtenerFacturaConexionTimeout = ClaroUtil
	        .convertProperties(configuration.getProperty("factura.ws.listarDocReferencia.conexion.timeout"));
	    this.obtenerFacturaExecutionTimeout = ClaroUtil
	        .convertProperties(configuration.getProperty("factura.ws.listarDocReferencia.execution.timeout"));
	    
	    
	    
	    this.usuarioValido = ClaroUtil.convertProperties(configuration.getProperty("usuario.valido"));
	    this.aplicacionValida = ClaroUtil.convertProperties(configuration.getProperty("aplicacion.valida"));
		this.ipValida = ClaroUtil.convertProperties(configuration.getProperty("ip.valida"));
		this.nombreAplicativo = ClaroUtil.convertProperties(configuration.getProperty("nombre.aplicativo"));

		this.idListaSp = ClaroUtil.convertProperties(configuration.getProperty("id.lista.sp"));
		this.aplicativoSp = ClaroUtil.convertProperties(configuration.getProperty("aplicativo.sp"));
		this.tiposDocumentosNdBscs = ClaroUtil
				.convertProperties(configuration.getProperty("tipo.documentos.notadebi.bscs"));
		this.tiposDocumentosRecBscs = ClaroUtil
				.convertProperties(configuration.getProperty("tipo.documentos.recibo.bscs"));

		this.conceptoCargoFijo = ClaroUtil.convertProperties(configuration.getProperty("concepto.cargo.fijo"));
		this.conceptoClaroServicios = ClaroUtil
				.convertProperties(configuration.getProperty("concepto.claro.servicios"));
		this.conceptoOcargo = ClaroUtil.convertProperties(configuration.getProperty("concepto.ocargos"));
		this.conceptoOcargoIgv = ClaroUtil.convertProperties(configuration.getProperty("concepto.ocargos.igv"));
		this.conceptoOcargoMora = ClaroUtil.convertProperties(configuration.getProperty("concepto.ocargos.mora"));
		this.conceptoTraficoAdicional = ClaroUtil
				.convertProperties(configuration.getProperty("concepto.trafico.adicional"));
		this.conceptoTraficoConsumo = ClaroUtil
				.convertProperties(configuration.getProperty("concepto.trafico.consumo"));
		this.conceptoLdi = ClaroUtil.convertProperties(configuration.getProperty("concepto.ldi"));
		this.conceptoLdn = ClaroUtil.convertProperties(configuration.getProperty("concepto.ldn"));
		this.conceptoRoaming = ClaroUtil.convertProperties(configuration.getProperty("concepto.roaming"));

		this.codigoRespuestaIdf0 = ClaroUtil.convertProperties(configuration.getProperty("codigoRespuesta.idf0"));
		this.mensajeRespuestaIdf0 = ClaroUtil.convertProperties(configuration.getProperty("mensajeRespuesta.idf0"));
		this.codigoRespuestaIdf1 = ClaroUtil.convertProperties(configuration.getProperty("codigoRespuesta.idf1"));
		this.mensajeRespuestaIdf1 = ClaroUtil.convertProperties(configuration.getProperty("mensajeRespuesta.idf1"));
		this.codigoRespuestaIdf2 = ClaroUtil.convertProperties(configuration.getProperty("codigoRespuesta.idf2"));
		this.mensajeRespuestaIdf2 = ClaroUtil.convertProperties(configuration.getProperty("mensajeRespuesta.idf2"));

		this.codigoRespuestaIdt1 = ClaroUtil.convertProperties(configuration.getProperty("codigoRespuesta.idt1"));
		this.mensajeRespuestaIdt1 = ClaroUtil.convertProperties(configuration.getProperty("mensajeRespuesta.idt1"));
		this.codigoRespuestaIdt2 = ClaroUtil.convertProperties(configuration.getProperty("codigoRespuesta.idt2"));
		this.mensajeRespuestaIdt2 = ClaroUtil.convertProperties(configuration.getProperty("mensajeRespuesta.idt2"));
		this.codigoRespuestaIdt3 = ClaroUtil.convertProperties(configuration.getProperty("codigoRespuesta.idt3"));
		this.mensajeRespuestaIdt3 = ClaroUtil.convertProperties(configuration.getProperty("mensajeRespuesta.idt3"));
		this.codigoRespuestaIdt4 = ClaroUtil.convertProperties(configuration.getProperty("codigoRespuesta.idt4"));
		this.mensajeRespuestaIdt4 = ClaroUtil.convertProperties(configuration.getProperty("mensajeRespuesta.idt4"));
		//INICIALIZANCION DE LA VARIABLE ONE
		this.flagConvivencia = ClaroUtil.convertProperties(configuration.getProperty("flag.convivencia.valor"));
		this.idt1Codigo = ClaroUtil.convertProperties(configuration.getProperty("idt1.codigo"));
        this.idt1Mensaje = ClaroUtil.convertProperties(configuration.getProperty("idt1.mensaje"));
        this.idt2Codigo = ClaroUtil.convertProperties(configuration.getProperty("idt2.codigo"));
        this.idt2Mensaje = ClaroUtil.convertProperties(configuration.getProperty("idt2.mensaje"));
        this.idt3Codigo = ClaroUtil.convertProperties(configuration.getProperty("idt3.codigo"));
        this.idt3Mensaje = ClaroUtil.convertProperties(configuration.getProperty("idt3.mensaje"));
        this.consultaLineaCuentaWS = ClaroUtil.convertProperties(configuration.getProperty("consultaLineaCuenta.ws"));
        this.consultaLineaCuentaWSMetodo = ClaroUtil.convertProperties(configuration.getProperty("consultaLineaCuenta.ws.consultarLineaCuenta"));
        this.consultarDocumentosIDF3Msj = ClaroUtil.convertProperties(configuration.getProperty("consultarDocumentos.idf3.msj"));
        this.consultarDocumentosIDF3Cod = ClaroUtil.convertProperties(configuration.getProperty("consultarDocumentos.idf3.cod"));
        this.consultarDocumentosIDF4Cod = ClaroUtil.convertProperties(configuration.getProperty("consultarDocumentos.idf4.cod"));
        this.consultarDocumentosIDF4Msj = ClaroUtil.convertProperties(configuration.getProperty("consultarDocumentos.idf4.msj"));
        this.consultarDocumentosIDF5Cod = ClaroUtil.convertProperties(configuration.getProperty("consultarDocumentos.idf5.cod"));
        this.consultarDocumentosIDF5Msj = ClaroUtil.convertProperties(configuration.getProperty("consultarDocumentos.idf5.msj"));
        this.wsFacturaFlagBusqueda = ClaroUtil.convertProperties(configuration.getProperty("ws.factura.flagBusqueda"));
        this.obtenerCategoriaEndpointUrlBasePath = ClaroUtil.convertProperties(configuration.getProperty("categoriaFactura.ws.obtenerCategoria.url.base"));
        this.obtenerCategoriaMetodo = ClaroUtil.convertProperties(configuration.getProperty("categoriaFactura.ws.obtenerCategoria.metodo"));
        this.obtenerCategoriaNombre = ClaroUtil.convertProperties(configuration.getProperty("categoriaFactura.ws.obtenerCategoria.nombre"));
        this.obtenerCategoriaConexionTimeout = ClaroUtil
            .convertProperties(configuration.getProperty("categoriaFactura.ws.obtenerCategoria.conexion.timeout"));
        this.obtenerCategoriaExecutionTimeout = ClaroUtil
            .convertProperties(configuration.getProperty("categoriaFactura.ws.obtenerCategoria.execution.timeout"));
        
        
        this.cbioKey = ClaroUtil.convertProperties(configuration.getProperty("cbio.key"));
        this.cbioValue = ClaroUtil.convertProperties(configuration.getProperty("cbio.value"));
  
        this.wsContractsSearchServiceURL = ClaroUtil.convertProperties(configuration.getProperty("ws.contractsSearchService.url"));
        this.wsContractsSearchServiceMetodo = ClaroUtil.convertProperties(configuration.getProperty("ws.contractsSearchService.metodo"));
        this.wsContractsSearchServiceNombre = ClaroUtil.convertProperties(configuration.getProperty("ws.contractsSearchService.nombre"));
        this.wsContractsSearchServiceTimeoutConnect = ClaroUtil.convertProperties(configuration.getProperty("ws.contractsSearchService.timeout.connect"));    
        this.wsContractsSearchServiceTimeoutRequest = ClaroUtil.convertProperties(configuration.getProperty("ws.contractsSearchService.timeout.request"));
       
        this.wsFacturaURL = ClaroUtil.convertProperties(configuration.getProperty("ws.factura.url"));
        this.wsFacturaTimeoutConnection = ClaroUtil.convertProperties(configuration.getProperty("ws.factura.timeout.connection"));
        this.wsFacturaTimeoutRequest = ClaroUtil.convertProperties(configuration.getProperty("ws.factura.timeout.request"));
        this.wsFacturaNombreServicio = ClaroUtil.convertProperties(configuration.getProperty("ws.factura.nombre.servicio"));
        this.wsFacturaNombreMetodo = ClaroUtil.convertProperties(configuration.getProperty("ws.factura.nombre.metodo"));
  }

	public String getFlagConvivencia() {
    return flagConvivencia;
  }

  /**
	 * @return the nombreAplicativo
	 */
	public String getNombreAplicativo() {
		return nombreAplicativo;
	}

	/**
	 * @param nombreAplicativo
	 *            the nombreAplicativo to set
	 */
	public void setNombreAplicativo(String nombreAplicativo) {
		this.nombreAplicativo = nombreAplicativo;
	}

	/**
	 * @return the tiposDocumentosRecBscs
	 */
	public String getTiposDocumentosRecBscs() {
		return tiposDocumentosRecBscs;
	}

	/**
	 * @param tiposDocumentosRecBscs
	 *            the tiposDocumentosRecBscs to set
	 */
	public void setTiposDocumentosRecBscs(String tiposDocumentosRecBscs) {
		this.tiposDocumentosRecBscs = tiposDocumentosRecBscs;
	}

	/**
	 * @return the tiposDocumentosNdBscs
	 */
	public String getTiposDocumentosNdBscs() {
		return tiposDocumentosNdBscs;
	}

	/**
	 * @param tiposDocumentosNdBscs
	 *            the tiposDocumentosNdBscs to set
	 */
	public void setTiposDocumentosNdBscs(String tiposDocumentosNdBscs) {
		this.tiposDocumentosNdBscs = tiposDocumentosNdBscs;
	}

	/**
	 * @return the estadoParametro
	 */
	public String getEstadoParametro() {
		return estadoParametro;
	}

	/**
	 * @param estadoParametro
	 *            the estadoParametro to set
	 */
	public void setEstadoParametro(String estadoParametro) {
		this.estadoParametro = estadoParametro;
	}

	/**
	 * @return the bdMgrNombre
	 */
	public String getBdMgrNombre() {
		return bdMgrNombre;
	}

	/**
	 * @param bdMgrNombre
	 *            the bdMgrNombre to set
	 */
	public void setBdMgrNombre(String bdMgrNombre) {
		this.bdMgrNombre = bdMgrNombre;
	}

	/**
	 * @return the bdMgrJndi
	 */
	public String getBdMgrJndi() {
		return bdMgrJndi;
	}

	/**
	 * @param bdMgrJndi
	 *            the bdMgrJndi to set
	 */
	public void setBdMgrJndi(String bdMgrJndi) {
		this.bdMgrJndi = bdMgrJndi;
	}

	/**
	 * @return the bdMgrOwner
	 */
	public String getBdMgrOwner() {
		return bdMgrOwner;
	}

	/**
	 * @param bdMgrOwner
	 *            the bdMgrOwner to set
	 */
	public void setBdMgrOwner(String bdMgrOwner) {
		this.bdMgrOwner = bdMgrOwner;
	}

	/**
	 * @return the bdMgrExecuteTimeout
	 */
	public String getBdMgrExecuteTimeout() {
		return bdMgrExecuteTimeout;
	}

	/**
	 * @param bdMgrExecuteTimeout
	 *            the bdMgrExecuteTimeout to set
	 */
	public void setBdMgrExecuteTimeout(String bdMgrExecuteTimeout) {
		this.bdMgrExecuteTimeout = bdMgrExecuteTimeout;
	}

	/**
	 * @return the bdMgrPkgParamReclamos
	 */
	public String getBdMgrPkgParamReclamos() {
		return bdMgrPkgParamReclamos;
	}

	/**
	 * @param bdMgrPkgParamReclamos
	 *            the bdMgrPkgParamReclamos to set
	 */
	public void setBdMgrPkgParamReclamos(String bdMgrPkgParamReclamos) {
		this.bdMgrPkgParamReclamos = bdMgrPkgParamReclamos;
	}

	/**
	 * @return the bdMgrSpListaParametro
	 */
	public String getBdMgrSpListaParametro() {
		return bdMgrSpListaParametro;
	}

	/**
	 * @param bdMgrSpListaParametro
	 *            the bdMgrSpListaParametro to set
	 */
	public void setBdMgrSpListaParametro(String bdMgrSpListaParametro) {
		this.bdMgrSpListaParametro = bdMgrSpListaParametro;
	}

	/**
	 * @param bdCobsNombre
	 *            the bdCobsNombre to set
	 */
	public void setBdCobsNombre(String bdCobsNombre) {
		this.bdCobsNombre = bdCobsNombre;
	}

	/**
	 * @param bdCobsJndi
	 *            the bdCobsJndi to set
	 */
	public void setBdCobsJndi(String bdCobsJndi) {
		this.bdCobsJndi = bdCobsJndi;
	}

	/**
	 * @param bdCobsOwner
	 *            the bdCobsOwner to set
	 */
	public void setBdCobsOwner(String bdCobsOwner) {
		this.bdCobsOwner = bdCobsOwner;
	}

	/**
	 * @param bdCobsExecuteTimeout
	 *            the bdCobsExecuteTimeout to set
	 */
	public void setBdCobsExecuteTimeout(String bdCobsExecuteTimeout) {
		this.bdCobsExecuteTimeout = bdCobsExecuteTimeout;
	}

	/**
	 * @param bdCobsPkgSiacReclamos
	 *            the bdCobsPkgSiacReclamos to set
	 */
	public void setBdCobsPkgSiacReclamos(String bdCobsPkgSiacReclamos) {
		this.bdCobsPkgSiacReclamos = bdCobsPkgSiacReclamos;
	}

	/**
	 * @param bdCobsSpRecssMontoreclamo
	 *            the bdCobsSpRecssMontoreclamo to set
	 */
	public void setBdCobsSpRecssMontoreclamo(String bdCobsSpRecssMontoreclamo) {
		this.bdCobsSpRecssMontoreclamo = bdCobsSpRecssMontoreclamo;
	}

	/**
	 * @param bdCobsPkgSiacGenerico
	 *            the bdCobsPkgSiacGenerico to set
	 */
	public void setBdCobsPkgSiacGenerico(String bdCobsPkgSiacGenerico) {
		this.bdCobsPkgSiacGenerico = bdCobsPkgSiacGenerico;
	}

	/**
	 * @param bdCobsSpObtieneListas
	 *            the bdCobsSpObtieneListas to set
	 */
	public void setBdCobsSpObtieneListas(String bdCobsSpObtieneListas) {
		this.bdCobsSpObtieneListas = bdCobsSpObtieneListas;
	}

	/**
	 * @param consultaEstadoCuentaOsbConsultaCuentaEndpointUrl
	 *            the consultaEstadoCuentaOsbConsultaCuentaEndpointUrl to set
	 */
	public void setConsultaEstadoCuentaOsbConsultaCuentaEndpointUrl(
			String consultaEstadoCuentaOsbConsultaCuentaEndpointUrl) {
		this.consultaEstadoCuentaOsbConsultaCuentaEndpointUrl = consultaEstadoCuentaOsbConsultaCuentaEndpointUrl;
	}

	/**
	 * @param consultaEstadoCuentaOsbConsultaCuentaConexionTimeout
	 *            the consultaEstadoCuentaOsbConsultaCuentaConexionTimeout to
	 *            set
	 */
	public void setConsultaEstadoCuentaOsbConsultaCuentaConexionTimeout(
			String consultaEstadoCuentaOsbConsultaCuentaConexionTimeout) {
		this.consultaEstadoCuentaOsbConsultaCuentaConexionTimeout = consultaEstadoCuentaOsbConsultaCuentaConexionTimeout;
	}

	/**
	 * @param consultaEstadoCuentaOsbConsultaCuentaExecutionTimeout
	 *            the consultaEstadoCuentaOsbConsultaCuentaExecutionTimeout to
	 *            set
	 */
	public void setConsultaEstadoCuentaOsbConsultaCuentaExecutionTimeout(
			String consultaEstadoCuentaOsbConsultaCuentaExecutionTimeout) {
		this.consultaEstadoCuentaOsbConsultaCuentaExecutionTimeout = consultaEstadoCuentaOsbConsultaCuentaExecutionTimeout;
	}

	/**
	 * @param consultaFacturasOsbConsultaFacturasClienteEndpointUrl
	 *            the consultaFacturasOsbConsultaFacturasClienteEndpointUrl to
	 *            set
	 */
	public void setConsultaFacturasOsbConsultaFacturasClienteEndpointUrl(
			String consultaFacturasOsbConsultaFacturasClienteEndpointUrl) {
		this.consultaFacturasOsbConsultaFacturasClienteEndpointUrl = consultaFacturasOsbConsultaFacturasClienteEndpointUrl;
	}

	/**
	 * @param consultaFacturasOsbConsultaFacturasClienteConexionTimeout
	 *            the consultaFacturasOsbConsultaFacturasClienteConexionTimeout
	 *            to set
	 */
	public void setConsultaFacturasOsbConsultaFacturasClienteConexionTimeout(
			String consultaFacturasOsbConsultaFacturasClienteConexionTimeout) {
		this.consultaFacturasOsbConsultaFacturasClienteConexionTimeout = consultaFacturasOsbConsultaFacturasClienteConexionTimeout;
	}

	/**
	 * @param consultaFacturasOsbConsultaFacturasClienteExecutionTimeout
	 *            the consultaFacturasOsbConsultaFacturasClienteExecutionTimeout
	 *            to set
	 */
	public void setConsultaFacturasOsbConsultaFacturasClienteExecutionTimeout(
			String consultaFacturasOsbConsultaFacturasClienteExecutionTimeout) {
		this.consultaFacturasOsbConsultaFacturasClienteExecutionTimeout = consultaFacturasOsbConsultaFacturasClienteExecutionTimeout;
	}

	/**
	 * @param reclamoPostpagoWsValidarReciboEndpointUrl
	 *            the reclamoPostpagoWsValidarReciboEndpointUrl to set
	 */
	public void setReclamoPostpagoWsValidarReciboEndpointUrl(String reclamoPostpagoWsValidarReciboEndpointUrl) {
		this.reclamoPostpagoWsValidarReciboEndpointUrl = reclamoPostpagoWsValidarReciboEndpointUrl;
	}

	/**
	 * @param reclamoPostpagoWsValidarReciboConexionTimeout
	 *            the reclamoPostpagoWsValidarReciboConexionTimeout to set
	 */
	public void setReclamoPostpagoWsValidarReciboConexionTimeout(String reclamoPostpagoWsValidarReciboConexionTimeout) {
		this.reclamoPostpagoWsValidarReciboConexionTimeout = reclamoPostpagoWsValidarReciboConexionTimeout;
	}

	/**
	 * @param reclamoPostpagoWsValidarReciboExecutionTimeout
	 *            the reclamoPostpagoWsValidarReciboExecutionTimeout to set
	 */
	public void setReclamoPostpagoWsValidarReciboExecutionTimeout(
			String reclamoPostpagoWsValidarReciboExecutionTimeout) {
		this.reclamoPostpagoWsValidarReciboExecutionTimeout = reclamoPostpagoWsValidarReciboExecutionTimeout;
	}

	/**
	 * @param codigoProcedureGenericoErrorIdt1
	 *            the codigoProcedureGenericoErrorIdt1 to set
	 */
	public void setCodigoProcedureGenericoErrorIdt1(String codigoProcedureGenericoErrorIdt1) {
		this.codigoProcedureGenericoErrorIdt1 = codigoProcedureGenericoErrorIdt1;
	}

	/**
	 * @param mensajeProcedureGenericoErrorIdt1
	 *            the mensajeProcedureGenericoErrorIdt1 to set
	 */
	public void setMensajeProcedureGenericoErrorIdt1(String mensajeProcedureGenericoErrorIdt1) {
		this.mensajeProcedureGenericoErrorIdt1 = mensajeProcedureGenericoErrorIdt1;
	}

	/**
	 * @param codigoProcedureGenericoErrorIdt2
	 *            the codigoProcedureGenericoErrorIdt2 to set
	 */
	public void setCodigoProcedureGenericoErrorIdt2(String codigoProcedureGenericoErrorIdt2) {
		this.codigoProcedureGenericoErrorIdt2 = codigoProcedureGenericoErrorIdt2;
	}

	/**
	 * @param mensajeProcedureGenericoErrorIdt2
	 *            the mensajeProcedureGenericoErrorIdt2 to set
	 */
	public void setMensajeProcedureGenericoErrorIdt2(String mensajeProcedureGenericoErrorIdt2) {
		this.mensajeProcedureGenericoErrorIdt2 = mensajeProcedureGenericoErrorIdt2;
	}

	/**
	 * @param codigoProcedureGenericoErrorIdt3
	 *            the codigoProcedureGenericoErrorIdt3 to set
	 */
	public void setCodigoProcedureGenericoErrorIdt3(String codigoProcedureGenericoErrorIdt3) {
		this.codigoProcedureGenericoErrorIdt3 = codigoProcedureGenericoErrorIdt3;
	}

	/**
	 * @param mensajeProcedureGenericoErrorIdt3
	 *            the mensajeProcedureGenericoErrorIdt3 to set
	 */
	public void setMensajeProcedureGenericoErrorIdt3(String mensajeProcedureGenericoErrorIdt3) {
		this.mensajeProcedureGenericoErrorIdt3 = mensajeProcedureGenericoErrorIdt3;
	}

	/**
	 * @param usuarioValido
	 *            the usuarioValido to set
	 */
	public void setUsuarioValido(String usuarioValido) {
		this.usuarioValido = usuarioValido;
	}

	/**
	 * @param aplicacionValida
	 *            the aplicacionValida to set
	 */
	public void setAplicacionValida(String aplicacionValida) {
		this.aplicacionValida = aplicacionValida;
	}

	/**
	 * @param ipValida
	 *            the ipValida to set
	 */
	public void setIpValida(String ipValida) {
		this.ipValida = ipValida;
	}

	/**
	 * @param idListaSp
	 *            the idListaSp to set
	 */
	public void setIdListaSp(String idListaSp) {
		this.idListaSp = idListaSp;
	}

	/**
	 * @param aplicativoSp
	 *            the aplicativoSp to set
	 */
	public void setAplicativoSp(String aplicativoSp) {
		this.aplicativoSp = aplicativoSp;
	}

	/**
	 * @param conceptoCargoFijo
	 *            the conceptoCargoFijo to set
	 */
	public void setConceptoCargoFijo(String conceptoCargoFijo) {
		this.conceptoCargoFijo = conceptoCargoFijo;
	}

	/**
	 * @param conceptoClaroServicios
	 *            the conceptoClaroServicios to set
	 */
	public void setConceptoClaroServicios(String conceptoClaroServicios) {
		this.conceptoClaroServicios = conceptoClaroServicios;
	}

	/**
	 * @param conceptoOcargo
	 *            the conceptoOcargo to set
	 */
	public void setConceptoOcargo(String conceptoOcargo) {
		this.conceptoOcargo = conceptoOcargo;
	}

	/**
	 * @param conceptoOcargoIgv
	 *            the conceptoOcargoIgv to set
	 */
	public void setConceptoOcargoIgv(String conceptoOcargoIgv) {
		this.conceptoOcargoIgv = conceptoOcargoIgv;
	}

	/**
	 * @param conceptoOcargoMora
	 *            the conceptoOcargoMora to set
	 */
	public void setConceptoOcargoMora(String conceptoOcargoMora) {
		this.conceptoOcargoMora = conceptoOcargoMora;
	}

	/**
	 * @param conceptoTraficoAdicional
	 *            the conceptoTraficoAdicional to set
	 */
	public void setConceptoTraficoAdicional(String conceptoTraficoAdicional) {
		this.conceptoTraficoAdicional = conceptoTraficoAdicional;
	}

	/**
	 * @param conceptoTraficoConsumo
	 *            the conceptoTraficoConsumo to set
	 */
	public void setConceptoTraficoConsumo(String conceptoTraficoConsumo) {
		this.conceptoTraficoConsumo = conceptoTraficoConsumo;
	}

	/**
	 * @param conceptoLdi
	 *            the conceptoLdi to set
	 */
	public void setConceptoLdi(String conceptoLdi) {
		this.conceptoLdi = conceptoLdi;
	}

	/**
	 * @param conceptoLdn
	 *            the conceptoLdn to set
	 */
	public void setConceptoLdn(String conceptoLdn) {
		this.conceptoLdn = conceptoLdn;
	}

	/**
	 * @param conceptoRoaming
	 *            the conceptoRoaming to set
	 */
	public void setConceptoRoaming(String conceptoRoaming) {
		this.conceptoRoaming = conceptoRoaming;
	}

	/**
	 * @param codigoRespuestaIdf0
	 *            the codigoRespuestaIdf0 to set
	 */
	public void setCodigoRespuestaIdf0(String codigoRespuestaIdf0) {
		this.codigoRespuestaIdf0 = codigoRespuestaIdf0;
	}

	/**
	 * @param mensajeRespuestaIdf0
	 *            the mensajeRespuestaIdf0 to set
	 */
	public void setMensajeRespuestaIdf0(String mensajeRespuestaIdf0) {
		this.mensajeRespuestaIdf0 = mensajeRespuestaIdf0;
	}

	/**
	 * @param codigoRespuestaIdf1
	 *            the codigoRespuestaIdf1 to set
	 */
	public void setCodigoRespuestaIdf1(String codigoRespuestaIdf1) {
		this.codigoRespuestaIdf1 = codigoRespuestaIdf1;
	}

	/**
	 * @param mensajeRespuestaIdf1
	 *            the mensajeRespuestaIdf1 to set
	 */
	public void setMensajeRespuestaIdf1(String mensajeRespuestaIdf1) {
		this.mensajeRespuestaIdf1 = mensajeRespuestaIdf1;
	}

	/**
	 * @param codigoRespuestaIdf2
	 *            the codigoRespuestaIdf2 to set
	 */
	public void setCodigoRespuestaIdf2(String codigoRespuestaIdf2) {
		this.codigoRespuestaIdf2 = codigoRespuestaIdf2;
	}

	/**
	 * @param mensajeRespuestaIdf2
	 *            the mensajeRespuestaIdf2 to set
	 */
	public void setMensajeRespuestaIdf2(String mensajeRespuestaIdf2) {
		this.mensajeRespuestaIdf2 = mensajeRespuestaIdf2;
	}

	/**
	 * @param codigoRespuestaIdt1
	 *            the codigoRespuestaIdt1 to set
	 */
	public void setCodigoRespuestaIdt1(String codigoRespuestaIdt1) {
		this.codigoRespuestaIdt1 = codigoRespuestaIdt1;
	}

	/**
	 * @param mensajeRespuestaIdt1
	 *            the mensajeRespuestaIdt1 to set
	 */
	public void setMensajeRespuestaIdt1(String mensajeRespuestaIdt1) {
		this.mensajeRespuestaIdt1 = mensajeRespuestaIdt1;
	}

	/**
	 * @param codigoRespuestaIdt2
	 *            the codigoRespuestaIdt2 to set
	 */
	public void setCodigoRespuestaIdt2(String codigoRespuestaIdt2) {
		this.codigoRespuestaIdt2 = codigoRespuestaIdt2;
	}

	/**
	 * @param mensajeRespuestaIdt2
	 *            the mensajeRespuestaIdt2 to set
	 */
	public void setMensajeRespuestaIdt2(String mensajeRespuestaIdt2) {
		this.mensajeRespuestaIdt2 = mensajeRespuestaIdt2;
	}

	/**
	 * @param codigoRespuestaIdt3
	 *            the codigoRespuestaIdt3 to set
	 */
	public void setCodigoRespuestaIdt3(String codigoRespuestaIdt3) {
		this.codigoRespuestaIdt3 = codigoRespuestaIdt3;
	}

	/**
	 * @param mensajeRespuestaIdt3
	 *            the mensajeRespuestaIdt3 to set
	 */
	public void setMensajeRespuestaIdt3(String mensajeRespuestaIdt3) {
		this.mensajeRespuestaIdt3 = mensajeRespuestaIdt3;
	}

	/**
	 * @param codigoRespuestaIdt4
	 *            the codigoRespuestaIdt4 to set
	 */
	public void setCodigoRespuestaIdt4(String codigoRespuestaIdt4) {
		this.codigoRespuestaIdt4 = codigoRespuestaIdt4;
	}

	/**
	 * @param mensajeRespuestaIdt4
	 *            the mensajeRespuestaIdt4 to set
	 */
	public void setMensajeRespuestaIdt4(String mensajeRespuestaIdt4) {
		this.mensajeRespuestaIdt4 = mensajeRespuestaIdt4;
	}

	/**
	 * @return the bdCobsNombre
	 */
	public String getBdCobsNombre() {
		return bdCobsNombre;
	}

	/**
	 * @return the bdCobsJndi
	 */
	public String getBdCobsJndi() {
		return bdCobsJndi;
	}

	/**
	 * @return the bdCobsOwner
	 */
	public String getBdCobsOwner() {
		return bdCobsOwner;
	}

	/**
	 * @return the bdCobsExecuteTimeout
	 */
	public String getBdCobsExecuteTimeout() {
		return bdCobsExecuteTimeout;
	}

	/**
	 * @return the bdCobsPkgSiacReclamos
	 */
	public String getBdCobsPkgSiacReclamos() {
		return bdCobsPkgSiacReclamos;
	}

	/**
	 * @return the bdCobsSpRecssMontoreclamo
	 */
	public String getBdCobsSpRecssMontoreclamo() {
		return bdCobsSpRecssMontoreclamo;
	}

	/**
	 * @return the bdCobsPkgSiacGenerico
	 */
	public String getBdCobsPkgSiacGenerico() {
		return bdCobsPkgSiacGenerico;
	}

	/**
	 * @return the bdCobsSpObtieneListas
	 */
	public String getBdCobsSpObtieneListas() {
		return bdCobsSpObtieneListas;
	}

	/**
	 * @return the consultaEstadoCuentaOsbConsultaCuentaEndpointUrl
	 */
	public String getConsultaEstadoCuentaOsbConsultaCuentaEndpointUrl() {
		return consultaEstadoCuentaOsbConsultaCuentaEndpointUrl;
	}

	/**
	 * @return the consultaEstadoCuentaOsbConsultaCuentaConexionTimeout
	 */
	public String getConsultaEstadoCuentaOsbConsultaCuentaConexionTimeout() {
		return consultaEstadoCuentaOsbConsultaCuentaConexionTimeout;
	}

	/**
	 * @return the consultaEstadoCuentaOsbConsultaCuentaExecutionTimeout
	 */
	public String getConsultaEstadoCuentaOsbConsultaCuentaExecutionTimeout() {
		return consultaEstadoCuentaOsbConsultaCuentaExecutionTimeout;
	}

	/**
	 * @return the consultaFacturasOsbConsultaFacturasClienteEndpointUrl
	 */
	public String getConsultaFacturasOsbConsultaFacturasClienteEndpointUrl() {
		return consultaFacturasOsbConsultaFacturasClienteEndpointUrl;
	}

	/**
	 * @return the consultaFacturasOsbConsultaFacturasClienteConexionTimeout
	 */
	public String getConsultaFacturasOsbConsultaFacturasClienteConexionTimeout() {
		return consultaFacturasOsbConsultaFacturasClienteConexionTimeout;
	}

	/**
	 * @return the consultaFacturasOsbConsultaFacturasClienteExecutionTimeout
	 */
	public String getConsultaFacturasOsbConsultaFacturasClienteExecutionTimeout() {
		return consultaFacturasOsbConsultaFacturasClienteExecutionTimeout;
	}

	/**
	 * @return the reclamoPostpagoWsValidarReciboEndpointUrl
	 */
	public String getReclamoPostpagoWsValidarReciboEndpointUrl() {
		return reclamoPostpagoWsValidarReciboEndpointUrl;
	}

	/**
	 * @return the reclamoPostpagoWsValidarReciboConexionTimeout
	 */
	public String getReclamoPostpagoWsValidarReciboConexionTimeout() {
		return reclamoPostpagoWsValidarReciboConexionTimeout;
	}

	/**
	 * @return the reclamoPostpagoWsValidarReciboExecutionTimeout
	 */
	public String getReclamoPostpagoWsValidarReciboExecutionTimeout() {
		return reclamoPostpagoWsValidarReciboExecutionTimeout;
	}

	/**
	 * @return the codigoProcedureGenericoErrorIdt1
	 */
	public String getCodigoProcedureGenericoErrorIdt1() {
		return codigoProcedureGenericoErrorIdt1;
	}

	/**
	 * @return the mensajeProcedureGenericoErrorIdt1
	 */
	public String getMensajeProcedureGenericoErrorIdt1() {
		return mensajeProcedureGenericoErrorIdt1;
	}

	/**
	 * @return the codigoProcedureGenericoErrorIdt2
	 */
	public String getCodigoProcedureGenericoErrorIdt2() {
		return codigoProcedureGenericoErrorIdt2;
	}

	/**
	 * @return the mensajeProcedureGenericoErrorIdt2
	 */
	public String getMensajeProcedureGenericoErrorIdt2() {
		return mensajeProcedureGenericoErrorIdt2;
	}

	/**
	 * @return the codigoProcedureGenericoErrorIdt3
	 */
	public String getCodigoProcedureGenericoErrorIdt3() {
		return codigoProcedureGenericoErrorIdt3;
	}

	/**
	 * @return the mensajeProcedureGenericoErrorIdt3
	 */
	public String getMensajeProcedureGenericoErrorIdt3() {
		return mensajeProcedureGenericoErrorIdt3;
	}

	/**
	 * @return the usuarioValido
	 */
	public String getUsuarioValido() {
		return usuarioValido;
	}

	/**
	 * @return the aplicacionValida
	 */
	public String getAplicacionValida() {
		return aplicacionValida;
	}

	/**
	 * @return the ipValida
	 */
	public String getIpValida() {
		return ipValida;
	}

	/**
	 * @return the idListaSp
	 */
	public String getIdListaSp() {
		return idListaSp;
	}

	/**
	 * @return the aplicativoSp
	 */
	public String getAplicativoSp() {
		return aplicativoSp;
	}

	/**
	 * @return the conceptoCargoFijo
	 */
	public String getConceptoCargoFijo() {
		return conceptoCargoFijo;
	}

	/**
	 * @return the conceptoClaroServicios
	 */
	public String getConceptoClaroServicios() {
		return conceptoClaroServicios;
	}

	/**
	 * @return the conceptoOcargo
	 */
	public String getConceptoOcargo() {
		return conceptoOcargo;
	}

	/**
	 * @return the conceptoOcargoIgv
	 */
	public String getConceptoOcargoIgv() {
		return conceptoOcargoIgv;
	}

	/**
	 * @return the conceptoOcargoMora
	 */
	public String getConceptoOcargoMora() {
		return conceptoOcargoMora;
	}

	/**
	 * @return the conceptoTraficoAdicional
	 */
	public String getConceptoTraficoAdicional() {
		return conceptoTraficoAdicional;
	}

	/**
	 * @return the conceptoTraficoConsumo
	 */
	public String getConceptoTraficoConsumo() {
		return conceptoTraficoConsumo;
	}

	/**
	 * @return the conceptoLdi
	 */
	public String getConceptoLdi() {
		return conceptoLdi;
	}

	/**
	 * @return the conceptoLdn
	 */
	public String getConceptoLdn() {
		return conceptoLdn;
	}

	/**
	 * @return the conceptoRoaming
	 */
	public String getConceptoRoaming() {
		return conceptoRoaming;
	}

	/**
	 * @return the codigoRespuestaIdf0
	 */
	public String getCodigoRespuestaIdf0() {
		return codigoRespuestaIdf0;
	}

	/**
	 * @return the mensajeRespuestaIdf0
	 */
	public String getMensajeRespuestaIdf0() {
		return mensajeRespuestaIdf0;
	}

	/**
	 * @return the codigoRespuestaIdf1
	 */
	public String getCodigoRespuestaIdf1() {
		return codigoRespuestaIdf1;
	}

	/**
	 * @return the mensajeRespuestaIdf1
	 */
	public String getMensajeRespuestaIdf1() {
		return mensajeRespuestaIdf1;
	}

	/**
	 * @return the codigoRespuestaIdf2
	 */
	public String getCodigoRespuestaIdf2() {
		return codigoRespuestaIdf2;
	}

	/**
	 * @return the mensajeRespuestaIdf2
	 */
	public String getMensajeRespuestaIdf2() {
		return mensajeRespuestaIdf2;
	}

	/**
	 * @return the codigoRespuestaIdt1
	 */
	public String getCodigoRespuestaIdt1() {
		return codigoRespuestaIdt1;
	}

	/**
	 * @return the mensajeRespuestaIdt1
	 */
	public String getMensajeRespuestaIdt1() {
		return mensajeRespuestaIdt1;
	}

	/**
	 * @return the codigoRespuestaIdt2
	 */
	public String getCodigoRespuestaIdt2() {
		return codigoRespuestaIdt2;
	}

	/**
	 * @return the mensajeRespuestaIdt2
	 */
	public String getMensajeRespuestaIdt2() {
		return mensajeRespuestaIdt2;
	}

	/**
	 * @return the codigoRespuestaIdt3
	 */
	public String getCodigoRespuestaIdt3() {
		return codigoRespuestaIdt3;
	}

	/**
	 * @return the mensajeRespuestaIdt3
	 */
	public String getMensajeRespuestaIdt3() {
		return mensajeRespuestaIdt3;
	}

	/**
	 * @return the codigoRespuestaIdt4
	 */
	public String getCodigoRespuestaIdt4() {
		return codigoRespuestaIdt4;
	}

	/**
	 * @return the mensajeRespuestaIdt4
	 */
	public String getMensajeRespuestaIdt4() {
		return mensajeRespuestaIdt4;
	}

	public String getConsultaLineaCuentaWSTipoConsulta() {
      return consultaLineaCuentaWSTipoConsulta;
  }
	public String getConsultaLineaCuentaWSEndpointUrl() {
      return consultaLineaCuentaWSEndpointUrl;
  }
	public String getConsultaLineaCuentaWSConexionTimeout() {
      return consultaLineaCuentaWSConexionTimeout;
  }
	public String getConsultaLineaCuentaWSExecutionTimeout() {
      return consultaLineaCuentaWSExecutionTimeout;
  }
}
