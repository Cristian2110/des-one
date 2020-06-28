package pe.com.claro.post.documentosSaldoReclamo.one.integration.client;

import javax.ejb.Local;

import pe.com.claro.common.exception.WSException;
import pe.com.claro.common.property.PropertiesExternos;
import pe.com.claro.post.categoriafactura.canonical.request.ObtenerCategoriaRequest;
import pe.com.claro.post.categoriafactura.canonical.response.ObtenerCategoriaResponse;
import pe.com.claro.post.documentosSaldoReclamo.one.canonical.request.HeaderRequest;

@Local
public interface CategoriaFacturaClient {
  public ObtenerCategoriaResponse obtenerCategoria(HeaderRequest header, ObtenerCategoriaRequest request,
      PropertiesExternos p) throws WSException;
}
