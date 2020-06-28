package pe.com.claro.post.documentosSaldoReclamo.one.resource;

import java.text.SimpleDateFormat;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Configuration;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.modelmapper.ModelMapper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wordnik.swagger.annotations.Api;

import pe.com.claro.common.property.Constantes;
import pe.com.claro.common.property.PropertiesExternos;
import pe.com.claro.post.documentosSaldoReclamo.one.LogUtil;
import pe.com.claro.post.documentosSaldoReclamo.one.canonical.request.BuscarDocumentoRequest;
import pe.com.claro.post.documentosSaldoReclamo.one.canonical.request.ConsultarDocumentosRequest;
import pe.com.claro.post.documentosSaldoReclamo.one.canonical.request.HeaderRequest;
import pe.com.claro.post.documentosSaldoReclamo.one.canonical.util.DateConvertModelMapper;
import pe.com.claro.post.documentosSaldoReclamo.one.domain.service.BuscarDocumentoService;
import pe.com.claro.post.documentosSaldoReclamo.one.domain.service.ConsultarDocumentosService;

/**
 * 
 * @author everis
 * @version 1.0 - 11/2019
 *
 */
@Stateless
@Path(Constantes.PATH_BASE)
@Api(value = Constantes.PATH_METODO1, description = "Bienvenido!")
@Produces({ Constantes.APPLICATION_JSON })
public class DocumentosSaldoReclamoResource {

	LogUtil logUtil = new LogUtil(DocumentosSaldoReclamoResource.class);
	private ObjectMapper mapper = new ObjectMapper();
	private ModelMapper modelMapper = new ModelMapper();

	@EJB
	private BuscarDocumentoService buscarDocService;

	@EJB
	private ConsultarDocumentosService consultarDocumentosService;

	@Context
	private Configuration conf;
	@Context
	private PropertiesExternos propertiesExternos;

	public DocumentosSaldoReclamoResource() {
		mapper.setDateFormat(new SimpleDateFormat(Constantes.FORMATOFECHADEFAULT));
		modelMapper.addConverter(new DateConvertModelMapper());
	}

	public void initProperties() {
		propertiesExternos = new PropertiesExternos(conf);
	}

	/**
	 * Metodo para obtener el documento de cliente corporativos.
	 * 
	 * @Method buscarDocumento
	 * @param buscarDocumentoRequest
	 * @return Response
	 * @throws Exception
	 */
	@Path(Constantes.METODO_BUSCAR_DOC)
	@POST
	@Consumes({ MediaType.APPLICATION_JSON })
	public Response buscarDocumento(@Context HttpHeaders httpHeaders, BuscarDocumentoRequest buscarDocumentoRequest)
			throws JsonProcessingException {
		initProperties();
		logUtil.inicioMetodo(Constantes.METODO_BUSCAR_DOC);
		logUtil.requestToJson(buscarDocumentoRequest);
		HeaderRequest header = new HeaderRequest(httpHeaders);
		logUtil.info(Constantes.INI_CORCHETE + Constantes.ACT_1 + Constantes.FIN_CORCHETE);
		buscarDocumentoRequest.isValid(propertiesExternos);

		String result = mapper.writeValueAsString(
				buscarDocService.buscarDocumento(header, buscarDocumentoRequest, propertiesExternos));
		logUtil.response(result);
		logUtil.finMetodo();
		return Response.ok().entity(result).build();

	}

	/**
	 * Metodo para listar los documentos de clientes masivos con antigüedad de
	 * dos años y disponibles para hacer reclamo.
	 * 
	 * @Method consultarDocumentos
	 * @param documentosRequest
	 * @return Response
	 * @throws Exception
	 */

	@Path(Constantes.METODO_CONSULTAR_DOC)
	@POST
	@Consumes({ MediaType.APPLICATION_JSON })
	public Response consultarDocumentos(@Context HttpHeaders httpHeaders, ConsultarDocumentosRequest documentosRequest)
			throws JsonProcessingException {
		initProperties();
		logUtil.inicioMetodo(Constantes.METODO_CONSULTAR_DOC);
		logUtil.requestToJson(documentosRequest);
		HeaderRequest header = new HeaderRequest(httpHeaders);
		logUtil.info(Constantes.INI_CORCHETE + Constantes.ACT_1 + Constantes.FIN_CORCHETE);
		documentosRequest.isValid(propertiesExternos);
		String result = mapper.writeValueAsString(
				consultarDocumentosService.consultarDocumentos(header, documentosRequest, propertiesExternos));
		logUtil.response(result);
		logUtil.finMetodo();
		return Response.ok().entity(result).build();
	}

}
