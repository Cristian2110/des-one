package pe.com.claro.post.documentosSaldoReclamo.one.integration.client.impl;

import javax.ejb.Stateless;
import javax.xml.ws.BindingProvider;

import org.apache.log4j.Logger;

import com.ericsson.services.ws_cil_7.ContractsSearchService;
import com.ericsson.services.ws_cil_7.ContractsSearchServiceSoap11QSService;
import com.ericsson.services.ws_cil_7.contractssearch.ContractsSearchRequest;
import com.ericsson.services.ws_cil_7.contractssearch.ContractsSearchResponse;

import pe.com.claro.common.exception.WSException;
import pe.com.claro.common.property.JAXBUtilitarios;
import pe.com.claro.common.property.PropertiesExternos;
import pe.com.claro.post.documentosSaldoReclamo.one.canonical.request.HeaderRequest;
import pe.com.claro.post.documentosSaldoReclamo.one.integration.client.ContractsSearchClient;

@Stateless
public class ContractsSearchClientImpl implements ContractsSearchClient {

    private static final Logger logger = Logger.getLogger(ContractsSearchClientImpl.class);

    @Override
    public ContractsSearchResponse contractsSearch(HeaderRequest headerRequest,
            ContractsSearchRequest request, PropertiesExternos propExt) throws WSException {
        // TODO Auto-generated method stub
        String metodo = "ContractsSearch";
        long tiempoInicio = System.currentTimeMillis();
        ContractsSearchResponse response = new ContractsSearchResponse();
        logger.info(" INICIO METODO: " + metodo);
        try {
            logger.info("Invocando Servicio: " + propExt.wsContractsSearchNombre + ", Metodo: "
                    + propExt.wsContractsSearchMetodo);
            logger.info(" URL del Servicio a invocar: " + propExt.wsContractsSearchWsdl);
            logger.info(" Datos de Entrada: " + JAXBUtilitarios.anyObjectToXmlText(request));

            ContractsSearchServiceSoap11QSService contractsSearchServiceSoap11QSService = new ContractsSearchServiceSoap11QSService();

            ContractsSearchService port = contractsSearchServiceSoap11QSService.getContractsSearchServiceSoap11QSPort();

            BindingProvider bindingProvider = (BindingProvider) port;
            bindingProvider.getRequestContext().put("javax.xml.ws.service.endpoint.address",
                    propExt.wsContractsSearchWsdl);
            bindingProvider.getRequestContext().put("com.sun.xml.internal.ws.connect.timeout",
                    Integer.valueOf(propExt.wsContractsSearchTimeoutConnect));
            bindingProvider.getRequestContext().put("com.sun.xml.internal.ws.request.timeout",
                    Integer.valueOf(propExt.wsContractsSearchTimeoutRequest));
            response = port.contractsSearch(request);

            if (null == response.getContracts() || response.getContracts().getItem().size() == 0)
                throw new WSException(propExt.idt2Codigo, String.format(propExt.idt2Mensaje,
                        new Object[] { propExt.wsContractsSearchNombre, propExt.wsContractsSearchMetodo }));
            logger.info(" Datos de Salida: " + JAXBUtilitarios.anyObjectToXmlText(response));
        } catch (Exception e) {
            logger.error(e, e);
            String error = e + "";
            if (error.contains("Timeout"))
                throw new WSException(propExt.idt1Codigo, String.format(propExt.idt1Mensaje,
                        new Object[] { propExt.wsContractsSearchNombre, propExt.wsContractsSearchMetodo }), e);
            throw new WSException(propExt.idt2Codigo, String.format(propExt.idt2Mensaje,
                    new Object[] { propExt.wsContractsSearchNombre, propExt.wsContractsSearchMetodo }), e);
        } finally {
            logger.info(" INICIO METODO: " + metodo);
            logger.info("Tiempo total de proceso (ms): " + (System.currentTimeMillis() - tiempoInicio)
                    + " milisegundos.");
        }
        return response;
    }
}