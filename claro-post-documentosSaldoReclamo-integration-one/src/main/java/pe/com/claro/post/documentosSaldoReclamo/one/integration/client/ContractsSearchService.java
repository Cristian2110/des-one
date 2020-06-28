package pe.com.claro.post.documentosSaldoReclamo.one.integration.client;

import javax.ejb.Local;

import com.ericsson.services.ws_cil_7.contractssearch.ContractsSearchRequest;
import com.ericsson.services.ws_cil_7.contractssearch.ContractsSearchResponse;

import pe.com.claro.common.exception.WSException;
import pe.com.claro.common.property.PropertiesExternos;

@Local
public interface ContractsSearchService {

  public ContractsSearchResponse contractsSearch(ContractsSearchRequest request, PropertiesExternos p)
      throws WSException;

}