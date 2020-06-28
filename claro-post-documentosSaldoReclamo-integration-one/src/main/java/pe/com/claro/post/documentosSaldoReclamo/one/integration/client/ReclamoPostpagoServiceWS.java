/**
 * 
 */
package pe.com.claro.post.documentosSaldoReclamo.one.integration.client;

import javax.ejb.Local;

import pe.com.claro.common.property.PropertiesExternos;
import pe.com.claro.esb.services.schemas.reclamopostpago.ValidacionReciboRequest;
import pe.com.claro.esb.services.schemas.reclamopostpago.ValidacionReciboResponse;

/**
 * @author everis
 *
 */
@Local
public interface ReclamoPostpagoServiceWS {

	ValidacionReciboResponse validarRecibo(ValidacionReciboRequest request, PropertiesExternos properties);
}
