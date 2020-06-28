package pe.com.claro.post.documentosSaldoReclamo.one.integration.client;

import javax.ejb.Local;

import pe.com.claro.common.exception.WSException;
import pe.com.claro.common.property.PropertiesExternos;
import pe.com.claro.soa.message.consultalineacuentaws.ConsultarLineaCuentaRequest;
import pe.com.claro.soa.message.consultalineacuentaws.ConsultarLineaCuentaResponse;

@Local
public interface ConsultaLineaCuentaWS {

  ConsultarLineaCuentaResponse consultarLineaCuenta(ConsultarLineaCuentaRequest request, PropertiesExternos properties)
      throws WSException;
}
