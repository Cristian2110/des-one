package pe.com.claro.post.documentosSaldoReclamo.one.integration.client.impl;

import javax.ejb.Stateless;
import javax.ws.rs.core.MediaType;
import org.apache.log4j.Logger;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import pe.com.claro.common.exception.WSException;
import pe.com.claro.common.property.Constantes;
import pe.com.claro.common.property.PropertiesExternos;
import pe.com.claro.post.documentosSaldoReclamo.one.canonical.request.HeaderRequest;
import pe.com.claro.post.documentosSaldoReclamo.one.integration.client.FacturaClient;
import pe.com.claro.post.factura.canonical.request.ListarDocReferenciaRequestType;
import pe.com.claro.post.factura.canonical.response.ListarDocReferenciaResponseType;

@Stateless
public class FacturaClientImpl implements FacturaClient {
  
  private static transient Logger logger = Logger.getLogger(FacturaClientImpl.class);
  
  @Override
  public ListarDocReferenciaResponseType obtenerFactura(HeaderRequest header, ListarDocReferenciaRequestType request,PropertiesExternos p) throws WSException {
    long tiempoInicio = System.currentTimeMillis();
    logger.info("[INICIO metodo obtenerFactura]");
    ListarDocReferenciaResponseType response = new ListarDocReferenciaResponseType();
    String url = p.getObtenerFacturaEndpointUrlBasePath() + p.getObtenerFacturaMetodo();
    String nombreComponente = p.getObtenerFacturaNombre();
    String nombreMetodo = p.getObtenerFacturaMetodo();
    Gson gsonReq = new Gson();
    Gson prettyGson = new GsonBuilder().setPrettyPrinting().create();
    String jsonPayload=Constantes.VACIO;
    try {
      logger.info("Componente: " + nombreComponente+", Metodo: " + nombreMetodo);
      logger.info("URL: " + url+", ConexionTimeout: "+p.getObtenerFacturaConexionTimeout()+", ExecutionTimeout : "+p.getObtenerFacturaExecutionTimeout());
      logger.info("Request Header: " +prettyGson.toJson(header));
      logger.info("Request Body: " +prettyGson.toJson(request));
      Client client = Client.create();
      client.setConnectTimeout(Integer.valueOf(p.getObtenerCategoriaConexionTimeout()));
      client.setReadTimeout(Integer.valueOf(p.getObtenerCategoriaExecutionTimeout()));
      WebResource webResource = client.resource(url);
      ClientResponse responseClient = webResource
          .accept(MediaType.APPLICATION_JSON)
          .type(MediaType.APPLICATION_JSON)
          .header(Constantes.ID_TRANSACCION,header.getIdTransaccion())
          .header(Constantes.MSGID,header.getMsgid())
          .header(Constantes.TIMES_TAMP,header.getTimestamp())
          .header(Constantes.USERID,header.getUserId())
          .post(ClientResponse.class,gsonReq.toJson(request));
      logger.info("Status: "+responseClient.getStatus());
      jsonPayload=responseClient.getEntity(String.class);
      if(responseClient.getStatus()!=200){
        logger.info("jsonPayload : "+jsonPayload);
        throw new WSException(p.idt2Codigo,jsonPayload);
      }else{
        response = gsonReq.fromJson(jsonPayload,ListarDocReferenciaResponseType.class);
        logger.info("Response Body: " + prettyGson.toJson(response));
      }
    } catch (Exception e) {
      logger.error(e, e);
      String error = (e + Constantes.VACIO);
      if (error.contains(Constantes.TIMEOUT)) {
        throw new WSException(p.idt1Codigo,String.format(p.idt1Mensaje,url,p.obtenerFacturaMetodo).concat(e+Constantes.VACIO), e);
      } else {
        throw new WSException(p.idt2Codigo,String.format(p.idt2Mensaje,url,p.obtenerFacturaMetodo).concat(e+Constantes.VACIO), e);
      }
    } finally {
      logger.info("[FIN metodo obtenerFactura]");
      logger.info(" Tiempo total de proceso (ms): " + (System.currentTimeMillis() - tiempoInicio) + " milisegundos");
    }
    return response;
  }
}
