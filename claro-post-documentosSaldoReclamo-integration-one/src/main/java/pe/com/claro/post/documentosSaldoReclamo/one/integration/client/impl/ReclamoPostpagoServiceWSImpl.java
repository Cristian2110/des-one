/**
 * 
 */
package pe.com.claro.post.documentosSaldoReclamo.one.integration.client.impl;

import javax.ejb.Stateless;
import javax.xml.ws.BindingProvider;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import pe.com.claro.common.property.Constantes;
import pe.com.claro.common.property.JAXBUtilitarios;
import pe.com.claro.common.property.PropertiesExternos;
import pe.com.claro.esb.services.reclamopostpago.ws.EbsReclamosPostpago;
import pe.com.claro.esb.services.reclamopostpago.ws.EbsReclamosPostpagoService;
import pe.com.claro.esb.services.schemas.reclamopostpago.ValidacionReciboRequest;
import pe.com.claro.esb.services.schemas.reclamopostpago.ValidacionReciboResponse;
import pe.com.claro.post.documentosSaldoReclamo.one.integration.client.ReclamoPostpagoServiceWS;

/**
 * @author everis
 *
 */

@Stateless
public class ReclamoPostpagoServiceWSImpl implements ReclamoPostpagoServiceWS {

	private static final Logger LOG = LoggerFactory.getLogger(ReclamoPostpagoServiceWSImpl.class);

	@Override
	public ValidacionReciboResponse validarRecibo(ValidacionReciboRequest request, PropertiesExternos properties) {
		ValidacionReciboResponse response = new ValidacionReciboResponse();

		LOG.info(" Se invoca: " + properties.getReclamoPostpagoWsValidarReciboEndpointUrl());
		EbsReclamosPostpagoService service = new EbsReclamosPostpagoService();
		EbsReclamosPostpago port = service.getEbsReclamosPostpagoSoapPort();
		BindingProvider bindingProvider = (BindingProvider) port;
		bindingProvider.getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY,
				properties.getReclamoPostpagoWsValidarReciboEndpointUrl());
		LOG.info(" Tiempo de Timeout de Conexion: " + properties.getReclamoPostpagoWsValidarReciboConexionTimeout());
		bindingProvider.getRequestContext().put("com.sun.xml.ws.connect.timeout",
				Integer.parseInt(properties.getReclamoPostpagoWsValidarReciboConexionTimeout()));
		LOG.info(" Tiempo de Timeout de Ejecucion: " + properties.getReclamoPostpagoWsValidarReciboExecutionTimeout());
		bindingProvider.getRequestContext().put("com.sun.xml.ws.request.timeout",
				Integer.parseInt(properties.getReclamoPostpagoWsValidarReciboExecutionTimeout()));
		LOG.info(Constantes.DATOS_ENTRADA + JAXBUtilitarios.anyObjectToXmlText(request));
		response = port.validarRecibo(request);
		LOG.info(Constantes.DATOS_SALIDA + JAXBUtilitarios.anyObjectToXmlText(response));
		return response;
	}
}
