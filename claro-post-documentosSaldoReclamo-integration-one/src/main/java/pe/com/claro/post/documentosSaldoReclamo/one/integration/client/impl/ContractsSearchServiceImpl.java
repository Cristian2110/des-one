package pe.com.claro.post.documentosSaldoReclamo.one.integration.client.impl;

import javax.ejb.Stateless;
import javax.xml.ws.BindingProvider;

import org.apache.log4j.Logger;
import org.springframework.remoting.jaxws.JaxWsSoapFaultException;

import com.ericsson.services.ws_cil_7.ContractsSearchServiceSoap11QSService;
import com.ericsson.services.ws_cil_7.contractssearch.ContractsSearchRequest;
import com.ericsson.services.ws_cil_7.contractssearch.ContractsSearchResponse;

import pe.com.claro.common.exception.WSException;
import pe.com.claro.common.property.Constantes;
import pe.com.claro.common.property.JAXBUtilitarios;
import pe.com.claro.common.property.PropertiesExternos;
import pe.com.claro.post.documentosSaldoReclamo.one.integration.client.ContractsSearchService;

@Stateless
public class ContractsSearchServiceImpl implements ContractsSearchService {

  private final Logger logger = Logger.getLogger(this.getClass().getName());

  @Override
  public ContractsSearchResponse contractsSearch(ContractsSearchRequest request, PropertiesExternos p)
      throws WSException {
    long tiempoInicio = System.currentTimeMillis();
    ContractsSearchResponse response = new ContractsSearchResponse();
    logger.info("[INICIO metodo contractsSearch]");
    try {

      com.ericsson.services.ws_cil_7.sessionchange.SessionChangeRequest sessionChangeRequest = new com.ericsson.services.ws_cil_7.sessionchange.SessionChangeRequest();
      com.ericsson.services.ws_cil_7.sessionchange.ValuesRequest valuesRequest = new com.ericsson.services.ws_cil_7.sessionchange.ValuesRequest();
      com.ericsson.services.ws_cil_7.sessionchange.ValuesListpartRequest valuesListpartRequest = new com.ericsson.services.ws_cil_7.sessionchange.ValuesListpartRequest();

      valuesListpartRequest.setKey(p.cbioKey);
      valuesListpartRequest.setValue(p.cbioValue);
      valuesRequest.getItem().add(valuesListpartRequest);
      sessionChangeRequest.setValues(valuesRequest);

      request.setSessionChangeRequest(sessionChangeRequest);

      logger.info(" URL del Servicio a invocar: " + p.wsContractsSearchServiceURL);
      logger.info(" Timeout Connect: " + p.wsContractsSearchServiceTimeoutConnect);
      logger.info(" Timeout Request: " + p.wsContractsSearchServiceTimeoutRequest);
      logger.info(" Datos de entrada: " + JAXBUtilitarios.anyObjectToXmlText(request));
      ContractsSearchServiceSoap11QSService service = new ContractsSearchServiceSoap11QSService();
      com.ericsson.services.ws_cil_7.ContractsSearchService port = service.getContractsSearchServiceSoap11QSPort();
      BindingProvider bindingProvider = (BindingProvider) port;
      bindingProvider.getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, p.wsContractsSearchServiceURL);
      bindingProvider.getRequestContext().put(Constantes.CONNECT_TIMEOUT,
          Integer.parseInt(p.wsContractsSearchServiceTimeoutConnect));
      bindingProvider.getRequestContext().put(Constantes.REQUEST_TIMEOUT,
          Integer.parseInt(p.wsContractsSearchServiceTimeoutRequest));
      response = port.contractsSearch(request);
      logger.info(" Datos de salida: " + JAXBUtilitarios.anyObjectToXmlText(response));

    } catch (JaxWsSoapFaultException e) {
      logger.error("Error devuelto: " + e.getFault().getDetail());
      logger.error("Error JaxWsSoapFaultException: " + e, e);
      throw new WSException(p.idt3Codigo, String.format(p.idt3Mensaje, p.wsContractsSearchServiceURL), e);
    } catch (Exception e) {
      logger.error(e, e);
      String error = (e + Constantes.VACIO);
      if (error.contains(Constantes.TIMEOUT)) {
        throw new WSException(p.idt1Codigo,
            String.format(p.idt1Mensaje, p.wsContractsSearchServiceNombre, p.wsContractsSearchServiceMetodo), e);
      } else {
        throw new WSException(p.idt2Codigo,
            String.format(p.idt2Mensaje, p.wsContractsSearchServiceNombre, p.wsContractsSearchServiceMetodo), e);
      }
    } finally {
      logger.info(" Tiempo total de proceso (ms): " + (System.currentTimeMillis() - tiempoInicio) + " milisegundos");
      logger.info("[FIN metodo contractsSearch]");

    }
    return response;
  }

}
