package pe.com.claro.post.test.documentosSaldoReclamo.one.domain.service;
//package pe.com.claro.post.test.documentosSaldoReclamo.domain.service;
//
//import static org.mockito.Mockito.*;
//
//import java.io.File;
//import java.io.FileInputStream;
//import java.io.IOException;
//import java.io.InputStream;
//import java.math.BigDecimal;
//import java.net.URL;
//import java.util.ArrayList;
//import java.util.Enumeration;
//import java.util.List;
//import java.util.Properties;
//
//import javax.persistence.EntityManager;
//import javax.persistence.criteria.CriteriaBuilder;
//import javax.ws.rs.core.Configuration;
//import javax.xml.datatype.XMLGregorianCalendar;
//import javax.xml.ws.Holder;
//
//import org.junit.Assert;
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.mockito.InjectMocks;
//import org.mockito.Matchers;
//import org.mockito.Mock;
//import org.mockito.junit.MockitoJUnitRunner;
//
//import pe.com.claro.common.property.Constantes;
//import pe.com.claro.common.property.PropertiesExternos;
//import pe.com.claro.eai.crmservices.reclamos.consultafacturas.ConsultaFacturas;
//import pe.com.claro.eai.crmservices.reclamos.consultafacturas.ConsultarFacturaClienteResponse;
//import pe.com.claro.eai.crmservices.sga.BillType;
//import pe.com.claro.eai.crmservices.sga.ListaBillType;
//import pe.com.claro.eai.servicecommons.AuditType;
//import pe.com.claro.post.documentosSaldoReclamo.canonical.request.BuscarDocumentoRequest;
////import pe.com.claro.eai.ws.postventa.titularidadreclamos.types.DatosClienteType;
////import pe.com.claro.eai.ws.postventa.titularidadreclamos.types.ListaAlternativasType;
////import pe.com.claro.eai.ws.postventa.titularidadreclamos.types.ObtenerPreguntaResponse;
////import pe.com.claro.eai.ws.postventa.titularidadreclamos.types.PreguntasClienteType;
////import pe.com.claro.post.documentosSaldoReclamo.canonical.request.ObtenerPreguntaTitularidadRequest;
//import pe.com.claro.post.documentosSaldoReclamo.canonical.request.ConsultarDocumentosRequest;
////import pe.com.claro.post.documentosSaldoReclamo.canonical.response.ObtenerPreguntaTitularidadResponse;
////import pe.com.claro.post.documentosSaldoReclamo.canonical.response.ValidarRespuestaTitularidadResponse;
//import pe.com.claro.post.documentosSaldoReclamo.canonical.response.BaseResponse;
//import pe.com.claro.post.documentosSaldoReclamo.canonical.response.BuscarDocumentoResponse;
//import pe.com.claro.post.documentosSaldoReclamo.domain.repository.CobsRespository;
////import pe.com.claro.post.documentosSaldoReclamo.domain.repository.SgaRepository;
//import pe.com.claro.post.documentosSaldoReclamo.domain.service.ConsultarDocumentosService;
//import pe.com.claro.post.documentosSaldoReclamo.integration.client.ConsultaEstadoCuentaWS;
//import pe.com.claro.post.documentosSaldoReclamo.integration.client.ConsultaFacturasWS;
//import pe.com.claro.post.documentosSaldoReclamo.integration.client.ReclamoPostpagoServiceWS;
//
//@SuppressWarnings("deprecation")
//@RunWith(MockitoJUnitRunner.class)
//public class DocumentosSaldoReclamoServiceTest {
//
//	@InjectMocks
//	ConsultarDocumentosService documentosService;
//
//	@Mock
//	Configuration conf;
//
//	@Mock
//	CobsRespository cobsRespository;
//
//	@Mock
//	ConsultaEstadoCuentaWS consultaEstadoCuentaWS;
//
//	@Mock
//	ConsultaFacturasWS consultaFacturas;
//
//	@Mock
//	ReclamoPostpagoServiceWS reclamoPostpagoServiceWS;
//
//	@Mock
//	EntityManager em;
//
//	@Mock
//	CriteriaBuilder cb;
//
//	@Mock
//	Constantes constante;
//
//	Properties properties = new Properties();
//
//	@Before
//	public void init() throws IOException {
//
//		URL ruta = DocumentosSaldoReclamoServiceTest.class.getProtectionDomain().getCodeSource().getLocation();
//		String rutaProperties = new File(ruta.getPath()).getParentFile().getParentFile().getParentFile()
//				+ File.separator + "claro-post-documentosSaldoReclamo" + File.separator + ".properties";
//		InputStream input = new FileInputStream(rutaProperties);
//
//		properties.load(input);
//
//		Enumeration<?> e = properties.propertyNames();
//		while (e.hasMoreElements()) {
//			String key = (String) e.nextElement();
//			String value = properties.getProperty(key);
//			when(conf.getProperty(key)).thenReturn(value);
//		}
//	}
//
//	// @Test
//	public void obtenerPreguntas_operacionExitosa() throws Exception {
//
//		PropertiesExternos properties = new PropertiesExternos(conf);
//
//		System.out.println("Inicio Método obtenerPreguntas_operacionExitosa");
//		// ObtenerPreguntaTitularidadRequest request = new
//		// ObtenerPreguntaTitularidadRequest();
//		// ObtenerPreguntaTitularidadResponse response = new
//		// ObtenerPreguntaTitularidadResponse();
//		// ObtenerPreguntaRequest requestWS = new ObtenerPreguntaRequest();
//		// ObtenerPreguntaResponse responseWS = new ObtenerPreguntaResponse();
//
//		// AuditRequestType auditRequestType = new AuditRequestType();
//
//		// requestWS.setAuditRequest(auditRequestType);
//		// requestWS.setCanal("1");
//		// requestWS.setProducto("2");
//		// requestWS.setCustomerId("149");
//		// requestWS.setTelefono("997104141");
//		// requestWS.setTipoDocumento("2");
//		// requestWS.setNumeroDocumento("70105981");
//		// requestWS.setCodigoVendedor("E413249");
//		// requestWS.setCodigoPuntoVenta("01");
//		// requestWS.setCiudad("1");
//		// requestWS.setDistrito("1");
//
//		// ListaAlternativasType listaAlternativas = new
//		// ListaAlternativasType();
//		// PreguntasClienteType preguntas = new PreguntasClienteType();
//		// preguntas.setPregunta("¿Cual es el nombre de tu mamá?");
//		// preguntas.setNumeroOpcionRespuesta("1");
//		// listaAlternativas.getPreguntasCliente();
//		// DatosClienteType datosCliente = new DatosClienteType();
//		// datosCliente.setNumeroDocmento("70105981");
//		// datosCliente.setTipoDocumento("2");
//		// responseWS.setListaAlternativas(listaAlternativas);
//		// responseWS.setDatosCliente(datosCliente);
//
//		// when(titularidadWs.obtenerPregunta(Matchers.any(),Matchers.any())).thenReturn(responseWS);
//
//		// request.setCanal("1");
//		// request.setProducto("2");
//		// request.setCustomerId("149");
//		// request.setTelefono("997104141");
//		// request.setTipoDocumento("2");
//		// request.setNumeroDocumento("70105981");
//		// request.setCodigoVendedor("E413249");
//		// request.setCodigoVenta("01");
//		// request.setCiudad("1");
//		// request.setDistrito("");
//
//		// response = titularidadService.obtenerPreguntas(request, properties);
//
//		// Assert.assertTrue(Integer.parseInt(response.getCodigoRespuesta()) >=
//		// 0);
//
//		System.out.println("Fin Método::: obtenerPreguntas_operacionExitosa");
//		System.out.println("===================================================================");
//
//	}
//
//	@Test
//	public void buscarDocumento_correcta() throws Exception {
//		PropertiesExternos properties = new PropertiesExternos(conf);
//		System.out.println("Inicio Método::: buscarDocumento_correcta");
//		BuscarDocumentoRequest request = new BuscarDocumentoRequest();
//		BuscarDocumentoResponse response = new BuscarDocumentoResponse();
//		request.setIdTransaccion("12345");
//		request.setTipoDoc("REC");
//		request.setNumeroDocu("0050-02954895");
//		request.setCodCliente("00085185");
//		Holder<AuditType> audit = new Holder<>();
//		Holder<ListaBillType> listaBill = new Holder<>();
//		
//		XMLGregorianCalendar fecEmi = null;
//		BigDecimal montoTotal = null;
//		
////		AuditType AuditType = new AuditType();
////		AuditType.setErrorCode("0");
////		AuditType.setErrorMsg("OK");
//		
////		audit.value.setErrorCode("0");
////		audit.value.setErrorMsg("OK");
//		
//		
//		List<BillType> listaBillType = new ArrayList<>();
//		BillType BillType = new BillType();
//		BillType.setFecEmi(fecEmi);
//		BillType.setTipdoc("REC");
//		BillType.setRecibo("0050-02954895");
//		BillType.setMoneda("MN");
//		BillType.setValorOrig(montoTotal);
//		listaBillType.add(BillType);
//		
//		listaBill.value.getBill().get(0).setFecEmi(fecEmi);
//		listaBill.value.getBill().get(0).setTipdoc("REC");
//		listaBill.value.getBill().get(0).setRecibo("0050-02954895");
//		listaBill.value.getBill().get(0).setMoneda("MN");
//		listaBill.value.getBill().get(0).setValorOrig(montoTotal);
//
//		ConsultarFacturaClienteResponse responseWS = new ConsultarFacturaClienteResponse();
//		responseWS.setAudit(audit.value);
//		responseWS.setListaBill(listaBill.value);
//
//		// response.s
//
//		// when(consultaFacturas.consultarFacturas(request, properties, audit,
//		// listaBill)).thenReturn(responseWS);
//
//		response = documentosService.buscarDocumento(request, properties);
//
//		Assert.assertEquals("0", response.getCodigoRpta());
//		System.out.println("Respuesta esperada: 0");
//		System.out.println("Respuesta actual: " + response.getCodigoRpta());
//		System.out.println("Fin Método::: buscarDocumento_correcta");
//		System.out.println("===================================================================");
//	}
//
//	// @Test
//	// public void validarRespuestas_incorrecta() throws Exception {
//	// PropertiesExternos properties = new PropertiesExternos(conf);
//	// System.out.println("Inicio Método::: validarRespuestas_incorrecta");
//	// ConsultarDocumentosRequest request = new ConsultarDocumentosRequest();
//	// request.setIdTransaccion("555");
//	// request.setNumDocumento("70105981");
//	// request.setPreguntaValor(
//	// "¿Cual es la fecha exacta (dia y mes) del vencimiento del recibo de pago
//	// del servicio?");
//	// request.setRespuestValor("31/10");
//	// request.setTelefono("997104141");
//	// request.setTipoDocumento("2");
//	// ValidarRespuestaTitularidadResponse responseBD = new
//	// ValidarRespuestaTitularidadResponse();
//	// responseBD.setCodigoRespuesta("0");
//	// responseBD.setIdTransaccion("555");
//	// responseBD.setMensajeRespuesta("Operacion exitosa");
//	// responseBD.setOpciones("1:17/10|2:15/10|3:18/09|4:01/10|5:NO TIENE RECIBO
//	// FACTURADO");
//	// responseBD.setOpcionesRespuesta("5");
//	//
//	// when(titularidadRepository.buscarOpciones(Matchers.any(),
//	// Matchers.any())).thenReturn(responseBD);
//	//
//	// BaseResponse response = titularidadService.validarRespuesta(request,
//	// properties);
//	//
//	// Assert.assertEquals("1", response.getCodigoRespuesta().toString());
//	// System.out.println("Respuesta esperada: 1");
//	// System.out.println("Respuesta actual: " +
//	// response.getCodigoRespuesta().toString());
//	// System.out.println("Fin Método::: validarRespuestas_incorrecta");
//	// System.out.println("===================================================================");
//	// }
//
//	// @Test
//	// public void validarRespuestas_noData() throws Exception {
//	// PropertiesExternos properties = new PropertiesExternos(conf);
//	// System.out.println("Inicio Método::: validarRespuestas_noData");
//	// ConsultarDocumentosRequest request = new ConsultarDocumentosRequest();
//	// request.setIdTransaccion("555");
//	// request.setNumDocumento("70105981");
//	// request.setPreguntaValor(
//	// "¿Cual es la fecha exacta (dia y mes) del vencimiento del recibo de pago
//	// del servicio?");
//	// request.setRespuestValor("31/10");
//	// request.setTelefono("997104141");
//	// request.setTipoDocumento("2");
//	// ValidarRespuestaTitularidadResponse responseBD = null;
//	//
//	// when(titularidadRepository.buscarOpciones(Matchers.any(),
//	// Matchers.any())).thenReturn(responseBD);
//	//
//	// BaseResponse response = titularidadService.validarRespuesta(request,
//	// properties);
//	//
//	// Assert.assertEquals("2", response.getCodigoRespuesta().toString());
//	// System.out.println("Respuesta esperada: 2");
//	// System.out.println("Respuesta actual: " +
//	// response.getCodigoRespuesta().toString());
//	// System.out.println("Fin Método::: validarRespuestas_noData");
//	// System.out.println("===================================================================");
//	// }
//}
