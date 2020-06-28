/**
 * 
 */
package pe.com.claro.post.documentosSaldoReclamo.one.integration.client;

import javax.ejb.Local;
import javax.xml.ws.Holder;

import pe.com.claro.common.property.PropertiesExternos;
import pe.com.claro.eai.crmservices.reclamos.consultafacturas.ConsultarFacturaClienteResponse;
import pe.com.claro.eai.crmservices.sga.ListaBillType;
import pe.com.claro.eai.servicecommons.AuditType;
import pe.com.claro.post.documentosSaldoReclamo.one.canonical.request.BuscarDocumentoRequest;
import pe.com.claro.post.documentosSaldoReclamo.one.canonical.request.HeaderRequest;

/**
 * @author everis
 *
 */
@Local
public interface ConsultaFacturasWS {

	ConsultarFacturaClienteResponse consultarFacturas(HeaderRequest header, BuscarDocumentoRequest request, PropertiesExternos properties,
			Holder<AuditType> audit, Holder<ListaBillType> listaBill);
}
