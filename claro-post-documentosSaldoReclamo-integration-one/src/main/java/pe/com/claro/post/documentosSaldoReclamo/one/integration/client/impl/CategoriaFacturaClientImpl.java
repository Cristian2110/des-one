package pe.com.claro.post.documentosSaldoReclamo.one.integration.client.impl;

import javax.ejb.Stateless;
import javax.ws.rs.core.MediaType;

import org.apache.log4j.Logger;

import com.google.gson.Gson;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

import pe.com.claro.common.exception.WSException;
import pe.com.claro.common.property.Constantes;
import pe.com.claro.common.property.JAXBUtilitarios;
import pe.com.claro.common.property.PropertiesExternos;
import pe.com.claro.post.categoriafactura.canonical.request.ObtenerCategoriaRequest;
import pe.com.claro.post.categoriafactura.canonical.response.ObtenerCategoriaResponse;
import pe.com.claro.post.documentosSaldoReclamo.one.canonical.request.HeaderRequest;
import pe.com.claro.post.documentosSaldoReclamo.one.integration.client.CategoriaFacturaClient;

@Stateless
public class CategoriaFacturaClientImpl implements CategoriaFacturaClient {

  private static transient Logger logger = Logger.getLogger(CategoriaFacturaClientImpl.class);

  @Override
  public ObtenerCategoriaResponse obtenerCategoria(HeaderRequest header, ObtenerCategoriaRequest request,
      PropertiesExternos p) throws WSException {

    long tiempoInicio = System.currentTimeMillis();
    logger.info("[INICIO metodo obtenerCategoria]");
    ObtenerCategoriaResponse response = new ObtenerCategoriaResponse();
    String url = p.getObtenerCategoriaEndpointUrlBasePath() + p.getObtenerCategoriaMetodo();
    String nombreComponente = p.getObtenerCategoriaNombre();
    String nombreMetodo = p.getObtenerCategoriaMetodo();
    Gson gsonReq = new Gson();

    try {
      logger.info("Componente: " + nombreComponente);
      logger.info("Metodo: " + nombreMetodo);
      logger.info("URL: " + url);
      logger.info("Request Header: " + JAXBUtilitarios.anyObjectToXmlText(header));
      logger.info("Request Body: " + JAXBUtilitarios.anyObjectToXmlText(request));

      String res = gsonReq.toJson(request);
      Client client = Client.create();
      WebResource wrs = client.resource(url);

      ClientResponse responseClient = wrs.accept(MediaType.APPLICATION_JSON)
          .header("idTransaccion", header.getIdTransaccion()).header("msgid", header.getMsgid())
          .header("timestamp", header.getTimestamp()).header("userId", header.getUserId())
          .header("accept", header.getAccept()).type(MediaType.APPLICATION_JSON).post(ClientResponse.class, res);
      response = gsonReq.fromJson(responseClient.getEntity(String.class), ObtenerCategoriaResponse.class);

      logger.info("Response Body: " + JAXBUtilitarios.anyObjectToXmlText(response));
    } catch (Exception e) {
      logger.error(e, e);
      String error = (e + Constantes.VACIO);
      if (error.contains(Constantes.TIMEOUT)) {
        throw new WSException(p.idt1Codigo,
            String.format(p.idt1Mensaje, p.obtenerCategoriaNombre, p.obtenerCategoriaMetodo), e);
      } else {
        throw new WSException(p.idt2Codigo,
            String.format(p.idt2Mensaje, p.obtenerCategoriaNombre, p.obtenerCategoriaMetodo), e);
      }

    } finally {
      logger.info("[FIN metodo obtenerCategoria]");
      logger.info(" Tiempo total de proceso (ms): " + (System.currentTimeMillis() - tiempoInicio) + " milisegundos");
    }
    return response;
  }

}
