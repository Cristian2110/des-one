package pe.com.claro.post.documentosSaldoReclamo.one.integration.client.impl;

import javax.ejb.Stateless;
import javax.xml.ws.BindingProvider;

import org.apache.log4j.Logger;

import pe.com.claro.common.exception.WSException;
import pe.com.claro.common.property.Constantes;
import pe.com.claro.common.property.JAXBUtilitarios;
import pe.com.claro.common.property.PropertiesExternos;
import pe.com.claro.post.documentosSaldoReclamo.one.integration.client.ConsultaLineaCuentaWS;
import pe.com.claro.soa.message.consultalineacuentaws.ConsultarLineaCuentaRequest;
import pe.com.claro.soa.message.consultalineacuentaws.ConsultarLineaCuentaResponse;
import pe.com.claro.soa.service.consultalineacuentaws.ConsultaLineaCuentaWSPortType;
import pe.com.claro.soa.service.consultalineacuentaws.ConsultaLineaCuentaWSService;

@Stateless
public class ConsultaLineaCuentaWSImpl implements ConsultaLineaCuentaWS {

  private final Logger logger = Logger.getLogger(this.getClass().getName());

  @Override
  public ConsultarLineaCuentaResponse consultarLineaCuenta(ConsultarLineaCuentaRequest request, PropertiesExternos p)
      throws WSException {
    long tiempoInicio = System.currentTimeMillis();
    ConsultarLineaCuentaResponse response = new ConsultarLineaCuentaResponse();
    logger.info("[INICIO metodo consultarLineaCuenta]");

    try {

      logger.info(" URL: " + p.getConsultaLineaCuentaWSEndpointUrl());
      logger.info(" Timeout Connect: " + p.getConsultaLineaCuentaWSConexionTimeout());
      logger.info(" Timeout Request: " + p.getConsultaLineaCuentaWSExecutionTimeout());
      logger.info(" Datos Request: " + JAXBUtilitarios.anyObjectToXmlText(request));

      ConsultaLineaCuentaWSService service = new ConsultaLineaCuentaWSService();
      ConsultaLineaCuentaWSPortType port = service.getEbsConsultaLineaCuentaWSSB11();
      BindingProvider bindingProvider = (BindingProvider) port;
      bindingProvider.getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY,
          p.getConsultaLineaCuentaWSEndpointUrl());
      bindingProvider.getRequestContext().put("com.sun.xml.ws.connect.timeout",
          Integer.parseInt(p.getConsultaLineaCuentaWSConexionTimeout()));
      bindingProvider.getRequestContext().put("com.sun.xml.ws.request.timeout",
          Integer.parseInt(p.getConsultaLineaCuentaWSExecutionTimeout()));
      response = port.consultarLineaCuenta(request);

      logger.info(" Datos de Salida: " + JAXBUtilitarios.anyObjectToXmlText(response));
    } catch (Exception e) {
      logger.error(e, e);
      String error = (e + Constantes.VACIO);
      if (error.contains(Constantes.TIMEOUT)) {
        throw new WSException(p.idt1Codigo,
            String.format(p.idt1Mensaje, p.consultaLineaCuentaWS, p.consultaLineaCuentaWSMetodo), e);
      } else {
        throw new WSException(p.idt2Codigo,
            String.format(p.idt2Mensaje, p.consultaLineaCuentaWS, p.consultaLineaCuentaWSMetodo), e);
      }
    } finally {
      logger.info(" Tiempo total de proceso (ms): " + (System.currentTimeMillis() - tiempoInicio) + " milisegundos");
      logger.info("[FIN metodo consultarLineaCuenta]");
    }

    return response;
  }

}
