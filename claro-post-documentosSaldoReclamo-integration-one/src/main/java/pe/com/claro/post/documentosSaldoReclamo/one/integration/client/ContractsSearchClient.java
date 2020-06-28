package pe.com.claro.post.documentosSaldoReclamo.one.integration.client;
import com.ericsson.services.ws_cil_7.contractssearch.ContractsSearchRequest;
import com.ericsson.services.ws_cil_7.contractssearch.ContractsSearchResponse;

import pe.com.claro.common.exception.WSException;
import pe.com.claro.common.property.PropertiesExternos;
import pe.com.claro.post.documentosSaldoReclamo.one.canonical.request.HeaderRequest;

public interface ContractsSearchClient {

    ContractsSearchResponse contractsSearch(HeaderRequest headerRequest,
            ContractsSearchRequest requestClient, PropertiesExternos propertiesExternos)throws WSException;

}