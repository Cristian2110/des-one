package pe.com.claro.post.documentosSaldoReclamo.one.integration.client;

import javax.ejb.Local;

import pe.com.claro.common.exception.WSException;
import pe.com.claro.common.property.PropertiesExternos;
import pe.com.claro.post.documentosSaldoReclamo.one.canonical.request.HeaderRequest;
import pe.com.claro.post.factura.canonical.request.ListarDocReferenciaRequestType;
import pe.com.claro.post.factura.canonical.response.ListarDocReferenciaResponseType;

@Local
public interface FacturaClient {
    public ListarDocReferenciaResponseType obtenerFactura(HeaderRequest header, ListarDocReferenciaRequestType request,PropertiesExternos p) throws WSException ;
}
