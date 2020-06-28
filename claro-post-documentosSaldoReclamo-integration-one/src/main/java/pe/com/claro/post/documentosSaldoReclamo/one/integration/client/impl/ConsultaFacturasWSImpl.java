/**
 * 
 */
package pe.com.claro.post.documentosSaldoReclamo.one.integration.client.impl;

import javax.ejb.Stateless;
import javax.xml.ws.BindingProvider;
import javax.xml.ws.Holder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import pe.com.claro.common.property.Constantes;
import pe.com.claro.common.property.JAXBUtilitarios;
import pe.com.claro.common.property.PropertiesExternos;
import pe.com.claro.eai.crmservices.reclamos.consultafacturas.ConsultaFacturas;
import pe.com.claro.eai.crmservices.reclamos.consultafacturas.ConsultaFacturas_Service;
import pe.com.claro.eai.crmservices.reclamos.consultafacturas.ConsultarFacturaClienteResponse;
import pe.com.claro.eai.crmservices.sga.ListaBillType;
import pe.com.claro.eai.servicecommons.AuditType;
import pe.com.claro.post.documentosSaldoReclamo.one.canonical.request.BuscarDocumentoRequest;
import pe.com.claro.post.documentosSaldoReclamo.one.canonical.request.HeaderRequest;
import pe.com.claro.post.documentosSaldoReclamo.one.integration.client.ConsultaFacturasWS;

/**
 * @author everis
 *
 */
@Stateless
public class ConsultaFacturasWSImpl implements ConsultaFacturasWS {

	private static final Logger LOG = LoggerFactory.getLogger(ConsultaFacturasWSImpl.class);

	@Override
	public ConsultarFacturaClienteResponse consultarFacturas(HeaderRequest header, BuscarDocumentoRequest request,
			PropertiesExternos properties, Holder<AuditType> audit, Holder<ListaBillType> listaBill) {
		ConsultarFacturaClienteResponse response = new ConsultarFacturaClienteResponse();

		LOG.info(" Se invoca: " + properties.getConsultaFacturasOsbConsultaFacturasClienteEndpointUrl());
		ConsultaFacturas_Service service = new ConsultaFacturas_Service();
		ConsultaFacturas port = service.getConsultaFacturasSOAP();
		BindingProvider bindingProvider = (BindingProvider) port;
		bindingProvider.getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY,
				properties.getConsultaFacturasOsbConsultaFacturasClienteEndpointUrl());
		LOG.info(" Tiempo de Timeout de Conexion: "
				+ properties.getConsultaFacturasOsbConsultaFacturasClienteConexionTimeout());
		bindingProvider.getRequestContext().put("com.sun.xml.ws.connect.timeout",
				Integer.parseInt(properties.getConsultaFacturasOsbConsultaFacturasClienteConexionTimeout()));
		LOG.info(" Tiempo de Timeout de Ejecucion: "
				+ properties.getConsultaFacturasOsbConsultaFacturasClienteExecutionTimeout());
		bindingProvider.getRequestContext().put("com.sun.xml.ws.request.timeout",
				Integer.parseInt(properties.getConsultaFacturasOsbConsultaFacturasClienteExecutionTimeout()));
		audit = new Holder<>();
		listaBill = new Holder<>();

		port.consultarFacturaCliente(header.getIdTransaccion(), Constantes.EMPTY, properties.getUsuarioValido(),
				request.getCodCliente(), audit, listaBill);
		LOG.info(Constantes.DATOS_SALIDA + JAXBUtilitarios.anyObjectToXmlText(listaBill.value));

		response.setAudit(audit.value);
		response.setListaBill(listaBill.value);
		return response;
	}

}
