package pe.com.claro.post.documentosSaldoReclamo.one.integration.client.impl;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.ejb.Stateless;
import javax.ws.rs.HttpMethod;
import javax.ws.rs.core.MediaType;

import org.apache.log4j.Logger;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource.Builder;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;

import pe.com.claro.common.exception.WSException;
import pe.com.claro.common.property.Constantes;
import pe.com.claro.common.property.JAXBUtilitarios;
import pe.com.claro.common.property.PropertiesExternos;
import pe.com.claro.post.documentosSaldoReclamo.one.canonical.request.HeaderRequest;
import pe.com.claro.post.documentosSaldoReclamo.one.integration.client.PostFactura;
import pe.com.claro.post.factura.canonical.request.ListarDocReferenciaRequestType;
import pe.com.claro.post.factura.canonical.response.ListarDocReferenciaResponseType;

@Stateless
public class PostFacturaImpl implements PostFactura {

  private static transient Logger logger = Logger.getLogger(PostFacturaImpl.class);

  @Override
  public ListarDocReferenciaResponseType listarDocReferencia(HeaderRequest header,
      ListarDocReferenciaRequestType request, PropertiesExternos p) throws WSException {

    logger.info("[INICIO metodo listarDocReferencia]");
    long tiempoInicio = System.currentTimeMillis();
    ListarDocReferenciaResponseType response = new ListarDocReferenciaResponseType();

    try {
      logger.info(" URL: " + p.wsFacturaURL);
      logger.info(" Timeout Connect: " + p.wsFacturaTimeoutConnection);
      logger.info(" Timeout Request: " + p.wsFacturaTimeoutRequest);
      logger.info(" Datos Request: " + JAXBUtilitarios.anyObjectToXmlText(request));
      logger.info(" Datos Header: " + JAXBUtilitarios.anyObjectToXmlText(header));
      ClientConfig clientConfig = new DefaultClientConfig();
      clientConfig.getProperties().put(ClientConfig.PROPERTY_CONNECT_TIMEOUT,
          Integer.parseInt(p.wsFacturaTimeoutConnection));
      clientConfig.getProperties().put(ClientConfig.PROPERTY_READ_TIMEOUT, Integer.parseInt(p.wsFacturaTimeoutRequest));
      Client client = Client.create(clientConfig);

      Builder builder = client.resource(p.wsFacturaURL).type(MediaType.APPLICATION_JSON);
      builder.header("idTransaccion", header.getIdTransaccion());
      builder.header("msgid", header.getMsgid());
      builder.header("timestamp", new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'").format(new Date()));
      builder.header("userId", header.getUserId());
      builder.header("accept", MediaType.APPLICATION_JSON);

      response = builder.method(HttpMethod.POST, ListarDocReferenciaResponseType.class, request);

      logger.info(" Datos Response: " + JAXBUtilitarios.anyObjectToXmlText(response));

    } catch (Exception e) {
      logger.error(e, e);
      String error = (e + Constantes.TXT_VACIO);
      if (error.contains(Constantes.TIMEOUT)) {
        throw new WSException(p.idt1Codigo,
            String.format(p.idt1Mensaje, p.wsFacturaNombreServicio, p.wsFacturaNombreMetodo), e);
      } else {
        throw new WSException(p.idt2Codigo,
            String.format(p.idt2Mensaje, p.wsFacturaNombreServicio, p.wsFacturaNombreMetodo), e);
      }
    } finally {
      logger.info(
          String.format(" Tiempo total de proceso (ms): %s milisegundos", (System.currentTimeMillis() - tiempoInicio)));
      logger.info("[FIN metodo listarDocReferencia]");
    }
    return response;

  }

}
