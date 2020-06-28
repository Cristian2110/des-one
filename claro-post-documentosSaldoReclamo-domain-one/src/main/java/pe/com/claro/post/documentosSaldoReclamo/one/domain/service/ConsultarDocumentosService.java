package pe.com.claro.post.documentosSaldoReclamo.one.domain.service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.EJBContext;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.ws.rs.core.Configuration;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;

import org.apache.commons.lang3.StringUtils;
import com.ericsson.services.ws_cil_7.contractssearch.ContractsSearchRequest;
import com.ericsson.services.ws_cil_7.contractssearch.ContractsSearchResponse;
import com.ericsson.services.ws_cil_7.contractssearch.InputAttributes;

import com.ericsson.services.ws_cil_7.contractssearch.ContractsListpartResponse;

import pe.com.claro.common.exception.TransaccionException;
import pe.com.claro.common.exception.WSException;
import pe.com.claro.common.property.Constantes;
import pe.com.claro.common.property.PropertiesExternos;
import com.ericsson.services.ws_cil_7.sessionchange.SessionChangeRequest;
import com.ericsson.services.ws_cil_7.sessionchange.ValuesListpartRequest;
import pe.com.claro.esb.services.schemas.reclamopostpago.DetalleReciboResponseType;
import com.ericsson.services.ws_cil_7.sessionchange.ValuesRequest;
import pe.com.claro.esb.services.schemas.reclamopostpago.DetalleServiciosType;
import pe.com.claro.esb.services.schemas.reclamopostpago.ValidacionReciboRequest;
import pe.com.claro.esb.services.schemas.reclamopostpago.ValidacionReciboResponse;
import pe.com.claro.post.documentosSaldoReclamo.one.LogUtil;
import pe.com.claro.post.documentosSaldoReclamo.one.canonical.request.ConsultarDocumentosRequest;
import pe.com.claro.post.documentosSaldoReclamo.one.canonical.request.HeaderRequest;
import pe.com.claro.post.documentosSaldoReclamo.one.canonical.response.ConsultaEstadoCtaResponse;
import pe.com.claro.post.documentosSaldoReclamo.one.canonical.response.ConsultarDocumentosResponse;
import pe.com.claro.post.documentosSaldoReclamo.one.canonical.response.DetalleConceptos;
import pe.com.claro.post.documentosSaldoReclamo.one.canonical.response.ListaConceptosCobs;
import pe.com.claro.post.documentosSaldoReclamo.one.canonical.response.ListaEquivalenciaCobs;
import pe.com.claro.post.documentosSaldoReclamo.one.canonical.util.Util;
import pe.com.claro.post.documentosSaldoReclamo.one.domain.repository.CobsRespository;
import pe.com.claro.post.documentosSaldoReclamo.one.integration.client.CategoriaFacturaClient;
import pe.com.claro.post.documentosSaldoReclamo.one.integration.client.ConsultaLineaCuentaWS;
import pe.com.claro.post.documentosSaldoReclamo.one.integration.client.ContractsSearchService;
import pe.com.claro.post.documentosSaldoReclamo.one.integration.client.PostFactura;
import pe.com.claro.post.documentosSaldoReclamo.one.integration.client.ReclamoPostpagoServiceWS;
import pe.com.claro.post.documentosSaldoReclamo.one.integration.client.impl.CategoriaFacturaClientImpl;
import pe.com.claro.post.documentosSaldoReclamo.one.integration.client.impl.ConsultaLineaCuentaWSImpl;
import pe.com.claro.post.documentosSaldoReclamo.one.integration.client.impl.ReclamoPostpagoServiceWSImpl;
import pe.com.claro.post.factura.canonical.bean.ListarDocReferenciaRequest;
import pe.com.claro.post.factura.canonical.request.ListarDocReferenciaRequestType;
import pe.com.claro.post.documentosSaldoReclamo.one.integration.client.ContractsSearchClient;
import pe.com.claro.post.factura.canonical.response.ListarDocReferenciaResponseType;
import pe.com.claro.soa.message.consultalineacuentaws.ConsultarLineaCuentaRequest;
import pe.com.claro.soa.message.consultalineacuentaws.ConsultarLineaCuentaResponse;
import pe.com.claro.post.categoriafactura.canonical.request.ObtenerCategoriaRequest;
import pe.com.claro.post.categoriafactura.canonical.request.ObtenerCategoriaRequestType;
import pe.com.claro.post.categoriafactura.canonical.response.ObtenerCategoriaResponse;
import pe.com.claro.post.factura.canonical.types.ResponseAuditType;
import pe.com.claro.post.factura.canonical.types.listarDocReferencia.ResponseDataType;
import pe.com.claro.post.documentosSaldoReclamo.one.integration.client.impl.ContractsSearchClientImpl;
import pe.com.claro.post.documentosSaldoReclamo.one.integration.client.FacturaClient;
import pe.com.claro.post.documentosSaldoReclamo.one.integration.client.impl.FacturaClientImpl;




@Stateless
@TransactionManagement(TransactionManagementType.BEAN)
public class ConsultarDocumentosService implements Serializable {

  private static final long serialVersionUID = -5283145043938691369L;

  private final LogUtil logUtil = new LogUtil(ConsultarDocumentosService.class);

  @Context
  private Configuration conf;

  @Context
  private HttpHeaders httpHeaders;

  @EJB
  private CobsRespository cobsRepository;

  @EJB
  private PostFactura postFactura;

  @EJB
  private CategoriaFacturaClient categoriaFacturaClient = new CategoriaFacturaClientImpl();

  @EJB
  private ContractsSearchService contractsSearchService;

  @EJB
  private ConsultaLineaCuentaWS consultaLineaCuentaWS = new ConsultaLineaCuentaWSImpl();

  @EJB
  private ReclamoPostpagoServiceWS reclamoPostpagoServiceWS = new ReclamoPostpagoServiceWSImpl();
  
  @EJB
  private ContractsSearchClient cssClient = new ContractsSearchClientImpl();
  
  @EJB
  private FacturaClient facturaClient = new FacturaClientImpl();
  
  @Resource
  private EJBContext ejbContext;

  String errors = Constantes.EMPTY;

  private boolean flagCBIO;

  /**
   * @param header
   * @Method consultarDocumentos
   * @param consultaDocRequest
   * @param properties
   * @return ConsultarDocumentosResponse
   */
  public ConsultarDocumentosResponse consultarDocumentos(HeaderRequest header,
      ConsultarDocumentosRequest consultaDocRequest, PropertiesExternos properties) {
    logUtil.inicioMetodoDebug(Constantes.METODO_CONSULTAR_DOC);
    ConsultarDocumentosResponse response = new ConsultarDocumentosResponse();

    try {
      ValidacionReciboRequest requestWS = new ValidacionReciboRequest();
      ValidacionReciboResponse responseWS = new ValidacionReciboResponse();
      ListarDocReferenciaResponseType facturaResponse = new ListarDocReferenciaResponseType();
      ConsultaEstadoCtaResponse consultaEstadoCtaResponse = new ConsultaEstadoCtaResponse();

      consultaEstadoCtaResponse = obtenerTipoDocEquiv(consultaDocRequest, properties);
      logUtil.toJson(consultaEstadoCtaResponse);
      requestWS = settingToService(header, consultaDocRequest, consultaEstadoCtaResponse, properties);
      logUtil.info(Constantes.INI_CORCHETE + "Actividad 2. Validar Recibo" + Constantes.FIN_CORCHETE);
      
      responseWS = new ValidacionReciboResponse();
      ObtenerCategoriaResponse categoriaResponse = new ObtenerCategoriaResponse();
      consultaEstadoCtaResponse = obtenerTipoDocEquiv(consultaDocRequest, properties);
      logUtil
          .info(Constantes.INI_CORCHETE + Constantes.CONSULTANDO_WS + Constantes.WS_NOMBRE2 + Constantes.FIN_CORCHETE);
      logUtil.info("[flag de convivencia] flag.convivencia.valor = " + properties.flagConvivencia);
      flagCBIO = false;
      categoriaResponse = validacionFlagConvi(properties, consultaDocRequest, header, responseWS, categoriaResponse, consultaEstadoCtaResponse);
//      if (properties.flagConvivencia.equals(Constantes.SUNO)) {
//        logUtil.info("[Flag de convivencia] Si cumple ");
//
//        logUtil.info("[Actividad 2] [Consultar Pivot]");
//        ConsultarLineaCuentaResponse consultaPivot = this.consultarPivot(consultaDocRequest, properties);
//
//        if (consultaPivot.getRptaConsulta().equals(Constantes.CERO)) {
//          logUtil.info("flujo ASIS");
//          logUtil.info("[Actividad 2] [Validar recibo]");
//          responseWS = this.validarRecibo(header, consultaDocRequest, consultaEstadoCtaResponse, properties);
//        } else if (consultaPivot.getRptaConsulta().equals(Constantes.SUNO)) {
//          logUtil.info("flujo TOBE");
//
//          logUtil.info("[Actividad 3] [Obtener customer CBIO]");
//          ContractsSearchResponse contractsSearch = this.obtenerCustomerCBIO(consultaDocRequest, properties);
//
//          logUtil.info("[Actividad 4] [Obtener conceptos del recibo]");
//          ListarDocReferenciaResponseType listarDocReferencia = this.obtenerConceptosDelRecibo(header,
//              consultaDocRequest, contractsSearch, properties);
//
//          categoriaResponse = this.obtenerConceptosRecibo(header, consultaDocRequest, properties);
//        }
//
//      } else {
//        categoriaResponse = this.obtenerConceptosRecibo(header, consultaDocRequest, properties);
//      }

      // CAMBIOS ONE
      // responseWS = reclamoPostpagoServiceWS.validarRecibo(requestWS,
      // properties);
      // } catch (Exception e) {
      // logUtil.error(Constantes.EMPTY, e);
      // errors = String.valueOf(e.toString());
      // return response = (ConsultarDocumentosResponse)
      // response.controlException(errors, Constantes.EMPTY,
      // Constantes.EMPTY, Constantes.WS_NOMBRE2,
      // Constantes.METODO_VALIDAR_RECIBO, properties);
      // }
      
      if (!flagCBIO) {
      List<ListaConceptosCobs> responseConceptoxRecibo = new ArrayList<>();
      try {
        logUtil
            .info(Constantes.INI_CORCHETE + "Actividad 6. Obtener los montos por conceptos" + Constantes.FIN_CORCHETE);
        responseConceptoxRecibo = cobsRepository.obtenerConceptosCobs(consultaEstadoCtaResponse, properties);
      } catch (Exception e) {
        logUtil.error(Constantes.EMPTY, e);
        errors = String.valueOf(e);
        return response = (ConsultarDocumentosResponse) response.controlException(errors,
            properties.getBdCobsSpRecssMontoreclamo(), properties.getBdCobsNombre(), Constantes.EMPTY, Constantes.EMPTY,
            properties);
      }
      Double montoTotal = Double.parseDouble(Constantes.CERO);
      // Monto historico
      Double montoCobs = obtenerMontoReclamosCobs(responseConceptoxRecibo);

      String estadoDoc = Constantes.EMPTY;
      
      DetalleServiciosType detalleServicio = new DetalleServiciosType();
      List<DetalleConceptos> listaDetalle = new ArrayList<>();
      

        // Validar que la lista no este vacía
      
         List<DetalleReciboResponseType> listaDetalleRecibo = responseWS.getListaReciboResponse()
              .getDetalleReciboResponse();
          Double montoFinal = Double.parseDouble(listaDetalleRecibo.get(0).getMonto());
          response.setMontoFijo(montoFinal);

          // Obtener monto Fijo en caso tipo de Documento sea ND o NC
          response = obtenerMontoFijoNDDC(montoTotal, listaDetalleRecibo, estadoDoc, montoFinal, montoCobs, detalleServicio, properties, response, listaDetalle);

//          if (!listaDetalleRecibo.get(0).getTipoDocumento().equals(Constantes.DOC_RECIBO)) {
//            logUtil.info("Tipo de Documento es: " + listaDetalleRecibo.get(0).getTipoDocumento());
//            estadoDoc = obtenerMontoFijoNdNc(montoFinal, montoCobs);
//          } else { // Obtener detalles para tipo de Documento REC
//            logUtil.info("Tipo de Documento es: REC");
//            detalleServicio = listaDetalleRecibo.get(0).getDetalleServicios().get(0);
//            List<DetalleConceptos> listaDetalleConceptos = new ArrayList<>();
//            // DetalleConceptos detalleConceptos;
//
//            logUtil.info(
//                Constantes.INI_CORCHETE + "Actividad 4. Obtener equivalenvias de Conceptos." + Constantes.FIN_CORCHETE);
//            List<ListaEquivalenciaCobs> listaEquivalencia = new ArrayList<>();
//
//            try {
//              // Obtiene Equivalencia para los conceptos de Recibo
//              listaEquivalencia = cobsRepository.obtenerEquivConceptos(properties);
//              logUtil.info(Constantes.CONSULTA_EXITOSA);
//            } catch (Exception e) {
//              logUtil.error(Constantes.EMPTY, e);
//              errors = String.valueOf(e.toString());
//              return response = (ConsultarDocumentosResponse) response.controlException(errors,
//                  properties.getBdCobsSpObtieneListas(), properties.getBdCobsNombre(), Constantes.EMPTY,
//                  Constantes.EMPTY, properties);
//            }
//
//            listaDetalleConceptos = listaEquivalenciasCobs(listaEquivalencia);
//            listaDetalle = listaDetalleAux(properties, listaDetalle, listaDetalleConceptos, detalleServicio, montoTotal);
////            for (DetalleConceptos detalleConceptoAux : listaDetalleConceptos) {
////              obtieneDetalle1(properties, detalleServicio, detalleConceptoAux);
////              obtieneDetalle2(properties, detalleServicio, detalleConceptoAux);
////              obtieneDetalle3(properties, detalleServicio, detalleConceptoAux);
////              montoTotal += Util.sumaMontos(detalleConceptoAux.getMontoFacturado());
////              listaDetalle.add(detalleConceptoAux);
////            }
//            logUtil.info("montoTotal: " + montoTotal);
//            logUtil.toJson(listaDetalle);
//            estadoDoc = setearEstadoDocumento(estadoDoc, listaDetalleConceptos, montoTotal, montoCobs);
//
//          }

          response.setFechaEmision(listaDetalleRecibo.get(0).getFechaEmision());
          response.setTipoDocumento(consultaEstadoCtaResponse.getTipoDocSP());
          response.setDetalleConceptos(listaDetalle);
          response.setCodigoInterno(listaDetalleRecibo.get(0).getCodInternoDoc());
          response.setFechaVencimiento(listaDetalleRecibo.get(0).getFecVenc());
          response.setEstadoDocumento(estadoDoc);
          response.setTipoMoneda(consultaEstadoCtaResponse.getMoneda());
          response.setNumDocumento(requestWS.getDocumento());
          response.setCodigoRpta(properties.getCodigoRespuestaIdf0());
          response.setMensajeRpta(properties.getMensajeRespuestaIdf0());
          logUtil.info(properties.getMensajeRespuestaIdf0());
        
      } else {
        logUtil.info(Constantes.INI_CORCHETE +"FLUJO TOBE" +Constantes.FIN_CORCHETE);
        logUtil.info(Constantes.INI_CORCHETE + "Conceptos CBIO" + Constantes.FIN_CORCHETE);
        
        List<DetalleConceptos> listaDetalleConceptos = new ArrayList<>();
        DetalleConceptos detalleConceptos;
        
        Double montoTotal = Double.parseDouble(Constantes.CERO);
        String estadoDoc = Constantes.EMPTY;
        
        for (int i = 0; i < categoriaResponse.getObtenerCategoriaResponse().getResponseData().getCategoria().size(); i++) {
          detalleConceptos = new DetalleConceptos();
          detalleConceptos.setConcepto(categoriaResponse.getObtenerCategoriaResponse().getResponseData().getCategoria().get(i).getDescripcionCategoria());
          detalleConceptos.setValor(categoriaResponse.getObtenerCategoriaResponse().getResponseData().getCategoria().get(i).getCodigoCategoria());
          detalleConceptos.setEstadoConcepto(validarNumeroPositivo(categoriaResponse.getObtenerCategoriaResponse().getResponseData().getCategoria().get(i).getMonto()));
          detalleConceptos.setMontoFacturado(this.obtenerDouble(categoriaResponse.getObtenerCategoriaResponse().getResponseData().getCategoria().get(i).getMonto()));
          montoTotal+=Util.sumaMontos(detalleConceptos.getMontoFacturado());
          listaDetalleConceptos.add(detalleConceptos);
        }
        
        if (!facturaResponse.getlistarDocReferenciaResponse().getResponseData().getRecibos().get(0).getTipoDocumento().equals(Constantes.DOC_RECIBO)) {
          logUtil.info("Tipo de Documento es: " + facturaResponse.getlistarDocReferenciaResponse().getResponseData().getRecibos().get(0).getTipoDocumento());
          estadoDoc = Constantes.CERO;
          if ((this.obtenerDouble(facturaResponse.getlistarDocReferenciaResponse().getResponseData().getRecibos().get(0).getMonto())-montoTotal)<=Constantes.CERO_INT) {
            estadoDoc = Constantes.SUNO;
          }
        } else { 
          for (DetalleConceptos detalleEstadoAux : listaDetalleConceptos) {
            if (montoTotal-this.obtenerDouble(facturaResponse.getlistarDocReferenciaResponse().getResponseData().getRecibos().get(0).getMonto())<=Constantes.CERO_INT) {
              if (detalleEstadoAux.getEstadoConcepto().equals(Constantes.CERO)) {
                estadoDoc = Constantes.CERO;
                break;
              } else {
                estadoDoc = Constantes.SUNO;
              }
            } else {
              estadoDoc = Constantes.CERO;
            }
          }
        }
        logUtil.info(Constantes.INI_CORCHETE + "Actividad 13. Mostrar lista de documentos obtenidos" + Constantes.FIN_CORCHETE);
        response.setMontoFijo(this.obtenerDouble(facturaResponse.getlistarDocReferenciaResponse().getResponseData().getRecibos().get(0).getMonto()));
        response.setFechaEmision(facturaResponse.getlistarDocReferenciaResponse().getResponseData().getRecibos().get(0).getFechaEmision());
        response.setTipoDocumento(consultaEstadoCtaResponse.getTipoDocSP());
        response.setDetalleConceptos(listaDetalleConceptos);
        response.setCodigoInterno(facturaResponse.getlistarDocReferenciaResponse().getResponseData().getRecibos().get(0).getCodInternoDoc());
        response.setFechaVencimiento(facturaResponse.getlistarDocReferenciaResponse().getResponseData().getRecibos().get(0).getFecVenc());
        response.setEstadoDocumento(estadoDoc);
        response.setTipoMoneda(consultaEstadoCtaResponse.getMoneda());
        response.setNumDocumento(consultaEstadoCtaResponse.getNumDoc());
        response.setCodigoRpta(properties.getCodigoRespuestaIdf0());
        response.setMensajeRpta(properties.getMensajeRespuestaIdf0());
        logUtil.info(properties.getMensajeRespuestaIdf0());
      }
    } catch (WSException e) {
      logUtil.error(e.getMessage(), e);
      response.setCodigoRpta(e.getCode());
      response.setMensajeRpta(e.getMessage());
    } catch (Exception e) {
      
      response = obtenerResponseExcepcion(e, properties, response);
//      logUtil.error(e.getMessage(), e);
//      String error = e.getMessage() == null ? "Error" : e.getMessage();
//      response.setCodigoRpta(properties.idt3Codigo);
//      response.setMensajeRpta(String.format(properties.idt3Mensaje, error));
    } finally {
      logUtil.toJson(response);
      logUtil.finMetodoDebug(Constantes.METODO_CONSULTAR_DOC);
    }
    return response;
  }

  // logUtil.info(properties.getMensajeRespuestaIdf0());

  // } else {
  // logUtil.info(properties.getMensajeRespuestaIdf2());
  // response.setFechaEmision(Constantes.EMPTY);
  // response.setTipoDocumento(Constantes.EMPTY);
  // response.setTipoMoneda(Constantes.EMPTY);
  // response.setNumDocumento(Constantes.EMPTY);
  // response.setCodigoRpta(properties.getCodigoRespuestaIdf2());
  // response.setMensajeRpta(properties.getMensajeRespuestaIdf2());
  // logUtil.toJson(response);
  // return response;
  // }
  //
  // logUtil.toJson(response);
  // logUtil.finMetodoDebug(Constantes.METODO_CONSULTAR_DOC);
  // return response;
  // }

  private ConsultaEstadoCtaResponse obtenerTipoDocEquiv(ConsultarDocumentosRequest request,
      PropertiesExternos properties) {
    ConsultaEstadoCtaResponse response = new ConsultaEstadoCtaResponse();

    String moneda = Constantes.EMPTY;

    // Identificar tipo de Moneda
    moneda = Util.identificarMoneda(request.getTipoMoneda());
    // Identificar Tipo de Documento
    response = identificarDocumento(request, properties);
    response.setMoneda(moneda);
    return response;
  }

  private ConsultaEstadoCtaResponse identificarDocumento(ConsultarDocumentosRequest request,
      PropertiesExternos properties) {
    ConsultaEstadoCtaResponse response = new ConsultaEstadoCtaResponse();
    String tipoDocWS = Constantes.EMPTY;
    String tipoDocSP = Constantes.EMPTY;
    String nDoc = Constantes.EMPTY;

    switch (request.getTipoDocumento()) {
    case Constantes.DOC_RECIBO:
        tipoDocWS = Constantes.STRES;
        tipoDocSP = properties.getTiposDocumentosRecBscs();
        nDoc = request.getNumDocumento().contains(Constantes.GUION) ? request.getNumDocumento()
          : request.getNumDocumento().substring(0, 4).concat(Constantes.GUION)
              .concat(request.getNumDocumento().substring(4));
        break;
    case Constantes.DOC_ND:
        tipoDocWS = Constantes.SUNO;
        tipoDocSP = properties.getTiposDocumentosNdBscs();
        break;
    default:
        tipoDocWS = Constantes.EMPTY;
        tipoDocSP = Constantes.EMPTY;
        break;
    }
    response.setNumDoc(nDoc);
    response.setTipoDocWS(tipoDocWS);
    response.setTipoDocSP(tipoDocSP);
    return response;
  }

  private void obtieneDetalle1(PropertiesExternos properties, DetalleServiciosType detalleServicio,
      DetalleConceptos detalleConceptoAux) {
    if (properties.getConceptoCargoFijo().equals(detalleConceptoAux.getConcepto())) {
      detalleConceptoAux.setEstadoConcepto(validarNumeroPositivo(Double.parseDouble(detalleServicio.getTotalAccess())));
      detalleConceptoAux.setMontoFacturado(Double.parseDouble(detalleServicio.getTotalAccess()));
    } else if (properties.getConceptoClaroServicios().equals(detalleConceptoAux.getConcepto())) {
      detalleConceptoAux
          .setEstadoConcepto(validarNumeroPositivo(Double.parseDouble(detalleServicio.getTotalSubscription())));
      detalleConceptoAux.setMontoFacturado(Double.parseDouble(detalleServicio.getTotalSubscription()));
    } else if (properties.getConceptoOcargo().equals(detalleConceptoAux.getConcepto())) {
      detalleConceptoAux.setEstadoConcepto(validarNumeroPositivo(Double.parseDouble(detalleServicio.getTotalOccs())));
      detalleConceptoAux.setMontoFacturado(Double.parseDouble(detalleServicio.getTotalOccs()));
    } else if (properties.getConceptoOcargoIgv().equals(detalleConceptoAux.getConcepto())) {
      detalleConceptoAux
          .setEstadoConcepto(validarNumeroPositivo(Double.parseDouble(detalleServicio.getTotalOccnIgv())));
      detalleConceptoAux.setMontoFacturado(Double.parseDouble(detalleServicio.getTotalOccnIgv()));
    }
  }

  private void obtieneDetalle2(PropertiesExternos properties, DetalleServiciosType detalleServicio,
      DetalleConceptos detalleConceptoAux) {
    if (properties.getConceptoOcargoMora().equals(detalleConceptoAux.getConcepto())) {
      detalleConceptoAux
          .setEstadoConcepto(validarNumeroPositivo(Double.parseDouble(detalleServicio.getTotalOccMora())));
      detalleConceptoAux.setMontoFacturado(Double.parseDouble(detalleServicio.getTotalOccMora()));
    } else if (properties.getConceptoTraficoAdicional().equals(Util.remove(detalleConceptoAux.getConcepto()))) {
      detalleConceptoAux
          .setEstadoConcepto(validarNumeroPositivo(Double.parseDouble(detalleServicio.getTraficoLocalAdicional())));
      detalleConceptoAux.setMontoFacturado(Double.parseDouble(detalleServicio.getTraficoLocalAdicional()));
    } else if (properties.getConceptoTraficoConsumo().equals(Util.remove(detalleConceptoAux.getConcepto()))) {
      detalleConceptoAux
          .setEstadoConcepto(validarNumeroPositivo(Double.parseDouble(detalleServicio.getTraficoLocalAConsumo())));
      detalleConceptoAux.setMontoFacturado(Double.parseDouble(detalleServicio.getTraficoLocalAConsumo()));
    }
  }

  private void obtieneDetalle3(PropertiesExternos properties, DetalleServiciosType detalleServicio,
      DetalleConceptos detalleConceptoAux) {
    if (properties.getConceptoLdi().equals(detalleConceptoAux.getConcepto())) {
      detalleConceptoAux.setEstadoConcepto(
          validarNumeroPositivo(Double.parseDouble(detalleServicio.getLargaDistanciaInternacional())));
      detalleConceptoAux.setMontoFacturado(Double.parseDouble(detalleServicio.getLargaDistanciaInternacional()));
    } else if (properties.getConceptoLdn().equals(detalleConceptoAux.getConcepto())) {
      detalleConceptoAux
          .setEstadoConcepto(validarNumeroPositivo(Double.parseDouble(detalleServicio.getLargaDistanciaNacional())));
      detalleConceptoAux.setMontoFacturado(Double.parseDouble(detalleServicio.getLargaDistanciaNacional()));
    } else if (properties.getConceptoRoaming().equals(detalleConceptoAux.getConcepto())) {
      detalleConceptoAux
          .setEstadoConcepto(validarNumeroPositivo(Double.parseDouble(detalleServicio.getRoamingInternacional())));
      detalleConceptoAux.setMontoFacturado(Double.parseDouble(detalleServicio.getRoamingInternacional()));
    }
  }

  private String validarNumeroPositivo(Double numero) {
    if (numero > Constantes.CERO_INT) {
      return Constantes.CERO;
    }
    return Constantes.SUNO;
  }

  private Double obtenerMontoReclamosCobs(List<ListaConceptosCobs> responseConceptoxRecibo) {
    Double montoConceptosCobs = Double.parseDouble(Constantes.CERO);
    for (int i = 0; i < responseConceptoxRecibo.size(); i++) {
      montoConceptosCobs += Double.parseDouble(responseConceptoxRecibo.get(i).getMonto());
      logUtil.info("Concepto: " + responseConceptoxRecibo.get(i).getConcepto() + Constantes.DOS_PUNTOS
          + responseConceptoxRecibo.get(i).getMonto());
    }
    logUtil.info("montoConceptosCobs: " + montoConceptosCobs);
    return montoConceptosCobs;
  }

  private ValidacionReciboRequest settingToService(HeaderRequest header, ConsultarDocumentosRequest consultaDocRequest,
      ConsultaEstadoCtaResponse consultaEstCtaResponse, PropertiesExternos properties) {
    ValidacionReciboRequest requestWS = new ValidacionReciboRequest();

    requestWS.setIdTransaccion(header.getIdTransaccion());
    requestWS.setLogin(properties.getUsuarioValido());
    requestWS.setSistema(properties.getNombreAplicativo());
    requestWS.setTipoDocumento(consultaEstCtaResponse.getTipoDocWS());
    requestWS.setDocumento(consultaEstCtaResponse.getNumDoc());
    requestWS.setCustomerId(consultaDocRequest.getCustomerId());
    requestWS.setFlagBusqueda(Constantes.CERO);
    return requestWS;
  }

  public String setearEstadoDocumento(String estadoDoc, List<DetalleConceptos> listaDetalle, Double montoTotal,
      Double montoCobs) {
    for (DetalleConceptos detalleEstadoAux : listaDetalle) {
      if ((montoTotal - montoCobs) <= Constantes.CERO_INT) {

        if (detalleEstadoAux.getEstadoConcepto().equals(Constantes.CERO)) {
          estadoDoc = Constantes.CERO;
          break;
        } else {
          estadoDoc = Constantes.SUNO;
        }
      } else {
        estadoDoc = Constantes.CERO;
      }
    }
    return estadoDoc;
  }

  // Obtener estado de documento
  public String obtenerMontoFijoNdNc(Double montoFinal, Double montoCobs) {
    String estadoDoc = Constantes.CERO;
    if ((montoFinal - montoCobs) <= Constantes.CERO_INT) {
      estadoDoc = Constantes.SUNO;
    }
    return estadoDoc;
  }

  public List<DetalleConceptos> listaEquivalenciasCobs(List<ListaEquivalenciaCobs> listaEquivalencia) {
    List<DetalleConceptos> listaDetalleConceptos = new ArrayList<>();
    DetalleConceptos detalleConceptos;
    for (ListaEquivalenciaCobs equivalenciaAux : listaEquivalencia) {
      detalleConceptos = new DetalleConceptos();
      detalleConceptos.setConcepto(equivalenciaAux.getDescripcion());
      detalleConceptos.setValor(equivalenciaAux.getValor());
      listaDetalleConceptos.add(detalleConceptos);
    }
    return listaDetalleConceptos;
  }
  
  public List<DetalleConceptos> listaDetalleAux(PropertiesExternos properties,List<DetalleConceptos> listaDetalle, List<DetalleConceptos> listaDetalleConceptos, DetalleServiciosType detalleServicio, Double montoTotal){
    
    for (DetalleConceptos detalleConceptoAux : listaDetalleConceptos) {
      obtieneDetalle1(properties, detalleServicio, detalleConceptoAux);
      obtieneDetalle2(properties, detalleServicio, detalleConceptoAux);
      obtieneDetalle3(properties, detalleServicio, detalleConceptoAux);
      montoTotal += Util.sumaMontos(detalleConceptoAux.getMontoFacturado());
      listaDetalle.add(detalleConceptoAux);
    }
    return listaDetalle;
  }

  public List<DetalleConceptos> detalleConceptos(List<DetalleConceptos> listaDetalleConceptos,
      PropertiesExternos properties, DetalleServiciosType detalleServicio) {
    Double montoTotal = Double.parseDouble(Constantes.CERO);
    List<DetalleConceptos> listaDetalle = new ArrayList<>();
    for (DetalleConceptos detalleConceptoAux : listaDetalleConceptos) {
      obtieneDetalle1(properties, detalleServicio, detalleConceptoAux);
      obtieneDetalle2(properties, detalleServicio, detalleConceptoAux);
      obtieneDetalle3(properties, detalleServicio, detalleConceptoAux);
      montoTotal += Util.sumaMontos(detalleConceptoAux.getMontoFacturado());
      listaDetalle.add(detalleConceptoAux);
    }
    return listaDetalle;
  }

  public List<ListaEquivalenciaCobs> generarExcepcionResponse(PropertiesExternos properties) {
    List<ListaEquivalenciaCobs> listaEquivalencia = new ArrayList<>();
    ConsultarDocumentosResponse response = new ConsultarDocumentosResponse();
    try {
      // Obtiene Equivalencia para los conceptos de Recibo
      listaEquivalencia = cobsRepository.obtenerEquivConceptos(properties);
      logUtil.info(Constantes.CONSULTA_EXITOSA);
    } catch (Exception e) {
      logUtil.error(Constantes.EMPTY, e);
      errors = String.valueOf(e.toString());
      response = (ConsultarDocumentosResponse) response.controlException(errors, properties.getBdCobsSpObtieneListas(),
          properties.getBdCobsNombre(), Constantes.EMPTY, Constantes.EMPTY, properties);
    }
    return listaEquivalencia;
  }

  // METODO ONE
  private ConsultarLineaCuentaResponse consultarPivot(ConsultarDocumentosRequest request, PropertiesExternos p)
      throws WSException {

    ConsultarLineaCuentaRequest consultarLineaCuentaRequest = new ConsultarLineaCuentaRequest();
    consultarLineaCuentaRequest.setTipoConsulta(p.getConsultaLineaCuentaWSTipoConsulta());
    consultarLineaCuentaRequest.setValorConsulta(request.getCustomerId());
    ConsultarLineaCuentaResponse response = consultaLineaCuentaWS.consultarLineaCuenta(consultarLineaCuentaRequest, p);
    if (null == response) {
      throw new WSException(p.consultarDocumentosIDF1Cod, p.consultarDocumentosIDF1Msj);
    }
    if (null == response.getRptaConsulta()) {
      throw new WSException(p.consultarDocumentosIDF1Cod, p.consultarDocumentosIDF1Msj);
    }
    String rptaConsulta = response.getRptaConsulta();
//    if (!rptaConsulta.equals(Constantes.CERO) || !rptaConsulta.equals(Constantes.SUNO)) {
//      throw new WSException(p.consultarDocumentosIDF1Cod,
//          String.format(p.consultarDocumentosIDF1Msj, " variable rptaConsulta es diferente de 0 o 1"));
//    }
    return response;
  }

  private ValidacionReciboResponse validarRecibo(HeaderRequest header, ConsultarDocumentosRequest consultaDocRequest,
      ConsultaEstadoCtaResponse consultaEstadoCtaResponse, PropertiesExternos p) throws WSException {

    ValidacionReciboRequest requestWS = settingToService(header, consultaDocRequest, consultaEstadoCtaResponse, p);
    ValidacionReciboResponse responseWS = reclamoPostpagoServiceWS.validarRecibo(requestWS, p);
    if (null == responseWS || null == responseWS.getListaReciboResponse()
        || null == responseWS.getListaReciboResponse().getDetalleReciboResponse()
        || responseWS.getListaReciboResponse().getDetalleReciboResponse().isEmpty()) {
      throw new WSException(p.getCodigoRespuestaIdf2(), p.getMensajeRespuestaIdf2());
    }

    return responseWS;
  }

//  private ContractsSearchResponse obtenerCustomerCBIO(ConsultarDocumentosRequest request, PropertiesExternos p)
//      throws WSException {
//
//    ContractsSearchRequest req = new ContractsSearchRequest();
//    req.setInputAttributes(new InputAttributes());
//    req.getInputAttributes().setCsId(new Long(request.getCustomerId()));
//    ContractsSearchResponse response = contractsSearchService.contractsSearch(req, p);
//    if (null == response || null == response.getContracts()) {
//      throw new TransaccionException(p.consultarDocumentosIDF4Cod,
//          String.format(p.consultarDocumentosIDF4Msj, " Objeto contracts vacio."));
//    }
//    if (null == response.getContracts().getItem() || response.getContracts().getItem().isEmpty()) {
//      throw new TransaccionException(p.consultarDocumentosIDF4Cod,
//          String.format(p.consultarDocumentosIDF4Msj, Constantes.NO_EXISTEN_REGISTROS));
//    }
//    return response;
//
//  }
//
//  private ListarDocReferenciaResponseType obtenerConceptosDelRecibo(HeaderRequest header,
//      ConsultarDocumentosRequest request, ContractsSearchResponse contractsSearch, PropertiesExternos p)
//          throws WSException {
//
//    ListarDocReferenciaRequestType req = new ListarDocReferenciaRequestType();
//    req.setlistarDocReferenciaRequest(new ListarDocReferenciaRequest());
//    req.getlistarDocReferenciaRequest().setCustomerIdPub(contractsSearch.getContracts().getItem().get(0).getCsIdPub());
//    req.getlistarDocReferenciaRequest().setTipoDocumento(request.getTipoDocumento());
//    req.getlistarDocReferenciaRequest().setNumeroDocumento(request.getNumDocumento());
//    req.getlistarDocReferenciaRequest().setFlagBusqueda(p.wsFacturaFlagBusqueda);
//    req.getlistarDocReferenciaRequest().setAnio(Constantes.VACIO);
//    req.getlistarDocReferenciaRequest().setMes(Constantes.VACIO);
//
//    ListarDocReferenciaResponseType response = postFactura.listarDocReferencia(header, req, p);
//
//    if (null == response || null == response.getlistarDocReferenciaResponse()
//        || null == response.getlistarDocReferenciaResponse().getResponseAudit()) {
//      throw new TransaccionException(p.consultarDocumentosIDF4Cod,
//          String.format(p.consultarDocumentosIDF4Msj, " Objeto nulo."));
//    }
//
//    ResponseAuditType audit = response.getlistarDocReferenciaResponse().getResponseAudit();
//    if (!Constantes.CERO.equals(audit.getCodigoRespuesta())) {
//      throw new TransaccionException(p.consultarDocumentosIDF4Cod,
//          String.format(p.consultarDocumentosIDF4Msj, audit.getMensajeRespuesta()));
//    }
//
//    ResponseDataType data = response.getlistarDocReferenciaResponse().getResponseData();
//    if (null == data || null == data.getRecibos() || data.getRecibos().isEmpty()) {
//      throw new TransaccionException(p.consultarDocumentosIDF4Cod,
//          String.format(p.consultarDocumentosIDF4Msj, Constantes.NO_EXISTEN_REGISTROS));
//    }
//
//    return response;
//  }

  private ObtenerCategoriaResponse obtenerConceptosRecibo(HeaderRequest header, ConsultarDocumentosRequest request,
      PropertiesExternos p) throws WSException {

    ObtenerCategoriaResponse response;

    ObtenerCategoriaRequest req = new ObtenerCategoriaRequest();
    req.setObtenerCategoriaRequest(new ObtenerCategoriaRequestType());
    req.getObtenerCategoriaRequest().setNumeroRecibo(request.getNumDocumento());

    response = categoriaFacturaClient.obtenerCategoria(header, req, p);

    if (null == response || null == response.getObtenerCategoriaResponse()
        || null == response.getObtenerCategoriaResponse().getResponseData()) {
      throw new TransaccionException(p.consultarDocumentosIDF4Cod,
          String.format(p.consultarDocumentosIDF4Msj, " Objeto nulo."));
    }

    if (null == response.getObtenerCategoriaResponse().getResponseData().getCategoria()
        || response.getObtenerCategoriaResponse().getResponseData().getCategoria().isEmpty()) {
      throw new TransaccionException(p.consultarDocumentosIDF4Cod,
          String.format(p.consultarDocumentosIDF4Msj, Constantes.NO_EXISTEN_REGISTROS));
    }

    return response;
  }

  private ObtenerCategoriaResponse validacionFlagConvi(PropertiesExternos properties,
      ConsultarDocumentosRequest consultaDocRequest, HeaderRequest header, ValidacionReciboResponse responseWS, ObtenerCategoriaResponse categoriaResponse, ConsultaEstadoCtaResponse consultaEstadoCtaResponse) throws WSException {
    ListarDocReferenciaResponseType facturaResponse = new ListarDocReferenciaResponseType();
    if (properties.flagConvivencia.equals(Constantes.SUNO)) {
      logUtil.info("[Flag de convivencia] Si cumple ");

      logUtil.info("[Actividad 2] [Consultar Pivot]");
      ConsultarLineaCuentaResponse consultaPivot = this.consultarPivot(consultaDocRequest, properties);

      if (consultaPivot.getRptaConsulta().equals(Constantes.CERO)) {
        logUtil.info("flujo ASIS");
        // ASIS = 0
        logUtil.info("[Actividad 5] [Validar recibo]");
        responseWS = this.validarRecibo(header, consultaDocRequest, consultaEstadoCtaResponse, properties);
      } else if (consultaPivot.getRptaConsulta().equals(Constantes.SUNO)) {
        //TOBE = 1
        logUtil.info("flujo TOBE");

        logUtil.info("[Actividad 3] [Obtener customer CBIO]");
        ContractsSearchResponse contractsSearchResponse = new ContractsSearchResponse();
        contractsSearchResponse = this.obtenerDatosCuentaCliente(header, consultaDocRequest, properties);
        
        logUtil.info("[Actividad 4]");
        logUtil.info("[Actividad 4] [Obtener conceptos del recibo]");
        List<ContractsListpartResponse> contractsListpartResponses = contractsSearchResponse.getContracts().getItem();
        facturaResponse = this.obtenerfacturas(header, consultaDocRequest,contractsListpartResponses.get(0).getCsIdPub(), properties);
        logUtil.info("[Actividad 4.1] [Obtener Conceptos del Recibo]");
        categoriaResponse = this.obtenerConceptosRecibo(header, consultaDocRequest, properties);
        
        flagCBIO = true;
        
      }

    } else {
      logUtil.info("[Actividad 3] [Obtener Customer CBIO]");
      ContractsSearchResponse contractsSearchResponse = new ContractsSearchResponse();
      contractsSearchResponse = this.obtenerDatosCuentaCliente(header, consultaDocRequest, properties);
      
      logUtil.info("[Actividad 4]");
      logUtil.info("[Actividad 4.0] [Obtener Conceptos del Recibo]");
      List<ContractsListpartResponse> contractsListpartResponses = contractsSearchResponse.getContracts().getItem();
      facturaResponse = this.obtenerfacturas(header, consultaDocRequest,contractsListpartResponses.get(0).getCsIdPub(), properties);
      logUtil.info("[Actividad 4.1] [Obtener Conceptos del Recibo]");
      categoriaResponse = this.obtenerConceptosRecibo(header, consultaDocRequest, properties);
      flagCBIO = true;
    }
    return categoriaResponse;
  }
  
  public ConsultarDocumentosResponse obtenerResponseExcepcion(Exception e,PropertiesExternos properties, ConsultarDocumentosResponse response){
    logUtil.error(e.getMessage(), e);
    String error = e.getMessage() == null ? "Error" : e.getMessage();
    response.setCodigoRpta(properties.idt3Codigo);
    response.setMensajeRpta(String.format(properties.idt3Mensaje, error));
    
    return response;
  }
  
  public  ConsultarDocumentosResponse obtenerMontoFijoNDDC(Double montoTotal, List<DetalleReciboResponseType> listaDetalleRecibo, String estadoDoc, Double montoFinal, Double montoCobs, DetalleServiciosType detalleServicio, PropertiesExternos properties, ConsultarDocumentosResponse response, List<DetalleConceptos> listaDetalle){
    
    if (!listaDetalleRecibo.get(0).getTipoDocumento().equals(Constantes.DOC_RECIBO)) {
      logUtil.info("Tipo de Documento es: " + listaDetalleRecibo.get(0).getTipoDocumento());
      estadoDoc = obtenerMontoFijoNdNc(montoFinal, montoCobs);
    } else { // Obtener detalles para tipo de Documento REC
      logUtil.info("Tipo de Documento es: REC");
      detalleServicio = listaDetalleRecibo.get(0).getDetalleServicios().get(0);
      List<DetalleConceptos> listaDetalleConceptos = new ArrayList<>();
      // DetalleConceptos detalleConceptos;

      logUtil.info(
          Constantes.INI_CORCHETE + "Actividad 4. Obtener equivalenvias de Conceptos." + Constantes.FIN_CORCHETE);
      List<ListaEquivalenciaCobs> listaEquivalencia = new ArrayList<>();

      try {
        // Obtiene Equivalencia para los conceptos de Recibo
        listaEquivalencia = cobsRepository.obtenerEquivConceptos(properties);
        logUtil.info(Constantes.CONSULTA_EXITOSA);
      } catch (Exception e) {
        logUtil.error(Constantes.EMPTY, e);
        errors = String.valueOf(e.toString());
        return response = (ConsultarDocumentosResponse) response.controlException(errors,
            properties.getBdCobsSpObtieneListas(), properties.getBdCobsNombre(), Constantes.EMPTY,
            Constantes.EMPTY, properties);
      }

      listaDetalleConceptos = listaEquivalenciasCobs(listaEquivalencia);
      listaDetalle = listaDetalleAux(properties, listaDetalle, listaDetalleConceptos, detalleServicio, montoTotal);
//      for (DetalleConceptos detalleConceptoAux : listaDetalleConceptos) {
//        obtieneDetalle1(properties, detalleServicio, detalleConceptoAux);
//        obtieneDetalle2(properties, detalleServicio, detalleConceptoAux);
//        obtieneDetalle3(properties, detalleServicio, detalleConceptoAux);
//        montoTotal += Util.sumaMontos(detalleConceptoAux.getMontoFacturado());
//        listaDetalle.add(detalleConceptoAux);
//      }
      logUtil.info("montoTotal: " + montoTotal);
      logUtil.toJson(listaDetalle);
      estadoDoc = setearEstadoDocumento(estadoDoc, listaDetalleConceptos, montoTotal, montoCobs);

    }
    return response;
  }
  
  private ContractsSearchResponse obtenerDatosCuentaCliente(HeaderRequest header, ConsultarDocumentosRequest request,
      PropertiesExternos p) throws WSException {
    // TODO Auto-generated method stub
    logUtil.info("Inicia llamado a Cliente" + "]");
    
    ContractsSearchResponse response = new ContractsSearchResponse();
    ContractsSearchRequest requestClient = new ContractsSearchRequest();
    InputAttributes inputAttributes = new InputAttributes();
    
    inputAttributes.setSearcher(p.cs_inputAttributes_searcher);
    inputAttributes.setCsId(Long.parseLong(request.getCustomerId()));
    
    SessionChangeRequest sessionChangeRequest = new SessionChangeRequest();
    ValuesRequest values = new ValuesRequest();
    ValuesListpartRequest valuesListpartRequest = new ValuesListpartRequest();
    
    valuesListpartRequest.setKey(p.cs_sessionchange_key);
    valuesListpartRequest.setValue(p.cs_sessionchange_value);
    
    List<ValuesListpartRequest> lista = new ArrayList<ValuesListpartRequest>();
    
    lista.add(valuesListpartRequest);
    
    values.getItem().add(valuesListpartRequest);
    
    sessionChangeRequest.setValues(values);
    requestClient.setSessionChangeRequest(sessionChangeRequest);
    requestClient.setInputAttributes(inputAttributes);
    
    response = cssClient.contractsSearch(header, requestClient, p);
    
    return response;
  }
  
  private ListarDocReferenciaResponseType obtenerfacturas(HeaderRequest header, ConsultarDocumentosRequest request,String csIdPub, PropertiesExternos p) throws WSException {
    ListarDocReferenciaResponseType response;
    ListarDocReferenciaRequestType facturaRequest = new ListarDocReferenciaRequestType();
    facturaRequest.setlistarDocReferenciaRequest(new ListarDocReferenciaRequest());
    facturaRequest.getlistarDocReferenciaRequest().setCustomerIdPub(csIdPub);
    ConsultaEstadoCtaResponse datosResponse = this.identificarDocumento(request, p);
    facturaRequest.getlistarDocReferenciaRequest().setTipoDocumento(datosResponse.getTipoDocWS());
    facturaRequest.getlistarDocReferenciaRequest().setNumeroDocumento(request.getNumDocumento());
    facturaRequest.getlistarDocReferenciaRequest().setFlagBusqueda(p.getFlagBusquedaFact());
    facturaRequest.getlistarDocReferenciaRequest().setAnio(Constantes.VACIO);
    facturaRequest.getlistarDocReferenciaRequest().setMes(Constantes.VACIO);
    List<pe.com.claro.post.factura.canonical.types.ListaOpcionalType> listaOpcionalType = new ArrayList<pe.com.claro.post.factura.canonical.types.ListaOpcionalType>();
    pe.com.claro.post.factura.canonical.types.ListaOpcionalType listaOpcional = new pe.com.claro.post.factura.canonical.types.ListaOpcionalType();
    listaOpcional.setClave(Constantes.VACIO);
    listaOpcional.setValor(Constantes.VACIO);
    listaOpcionalType.add(listaOpcional);
    facturaRequest.getlistarDocReferenciaRequest().setListaOpcional(listaOpcionalType);
    response = facturaClient.obtenerFactura(header, facturaRequest, p);
    if (null == response) {
      throw new WSException(p.getCodigoRespuestaIdf2(), p.getMensajeRespuestaIdf2());
    }
    if (null == response.getlistarDocReferenciaResponse()) {
      throw new WSException(p.getCodigoRespuestaIdf2(), p.getMensajeRespuestaIdf2());
    }
    if (null == response.getlistarDocReferenciaResponse().getResponseData()) {
      throw new WSException(p.getCodigoRespuestaIdf2(), p.getMensajeRespuestaIdf2());
    }
    if (null == response.getlistarDocReferenciaResponse().getResponseData().getRecibos()) {
      throw new WSException(p.getCodigoRespuestaIdf2(), p.getMensajeRespuestaIdf2());
    }
    if (response.getlistarDocReferenciaResponse().getResponseData().getRecibos().isEmpty()) {
      throw new WSException(p.getCodigoRespuestaIdf2(), p.getMensajeRespuestaIdf2());
    }
    return response;
  }
  
  private String validarNumeroPositivo(String numero) {
    String estadoConcepto=Constantes.SUNO;
    try {
      if (!StringUtils.isBlank(numero)&&Double.parseDouble(numero)>Constantes.CERO_INT) {
        estadoConcepto=Constantes.CERO;
      }
    } catch (Exception e) {
      logUtil.error(e+Constantes.VACIO,e);
    }
    return estadoConcepto;
  }
  
  private Double obtenerDouble(String numero) {
    if (!StringUtils.isBlank(numero)) {
      return Double.valueOf(numero);
    }else{
      return Double.parseDouble(Constantes.CERO);
    }
  }
}
