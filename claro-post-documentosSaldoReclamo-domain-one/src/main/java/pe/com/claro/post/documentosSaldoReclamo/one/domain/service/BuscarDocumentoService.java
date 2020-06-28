/**
 * 
 */
package pe.com.claro.post.documentosSaldoReclamo.one.domain.service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.EJBContext;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.ws.rs.core.Configuration;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.xml.ws.Holder;

import pe.com.claro.common.property.Constantes;
import pe.com.claro.common.property.PropertiesExternos;
import pe.com.claro.eai.crmservices.reclamos.consultafacturas.ConsultarFacturaClienteResponse;
import pe.com.claro.eai.crmservices.sga.BillType;
import pe.com.claro.eai.crmservices.sga.ListaBillType;
import pe.com.claro.eai.servicecommons.AuditType;
import pe.com.claro.post.documentosSaldoReclamo.one.LogUtil;
import pe.com.claro.post.documentosSaldoReclamo.one.canonical.request.BuscarDocumentoRequest;
import pe.com.claro.post.documentosSaldoReclamo.one.canonical.request.HeaderRequest;
import pe.com.claro.post.documentosSaldoReclamo.one.canonical.response.BuscarDocumentoResponse;
import pe.com.claro.post.documentosSaldoReclamo.one.canonical.response.ListaParametro;
import pe.com.claro.post.documentosSaldoReclamo.one.canonical.util.Util;
import pe.com.claro.post.documentosSaldoReclamo.one.domain.repository.MgrRepository;
import pe.com.claro.post.documentosSaldoReclamo.one.integration.client.ConsultaFacturasWS;
import pe.com.claro.post.documentosSaldoReclamo.one.integration.client.impl.ConsultaFacturasWSImpl;

/**
 * @author everis
 *
 */

@Stateless
@TransactionManagement(TransactionManagementType.BEAN)
public class BuscarDocumentoService implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1678573003553796893L;

	private final LogUtil logUtil = new LogUtil(BuscarDocumentoService.class);

	@Context
	private Configuration conf;

	@Context
	private HttpHeaders httpHeaders;

	@EJB
	private MgrRepository mgrRepository;

	@EJB
	private ConsultaFacturasWS consultaFacturasWS = new ConsultaFacturasWSImpl();

	@Resource
	private EJBContext ejbContext;

	String errors = Constantes.EMPTY;

	/**
	 * @param header
	 * @Method buscarDoc
	 * @param buscarDocumentoRequest
	 * @param propertiesExternos
	 * @return BaseResponse
	 */
	public BuscarDocumentoResponse buscarDocumento(HeaderRequest header, BuscarDocumentoRequest buscarDocumentoRequest,
			PropertiesExternos propertiesExternos) {

		BuscarDocumentoResponse buscarDocumentoResponse = new BuscarDocumentoResponse();
		ConsultarFacturaClienteResponse consultarFacturas = new ConsultarFacturaClienteResponse();
		logUtil.info(Constantes.INICIO_METODO + Constantes.METODO_BUSCAR_DOC + Constantes.FIN_CORCHETE);

		Holder<AuditType> audit = new Holder<>();
		Holder<ListaBillType> listaBill = new Holder<>();

		try {
			logUtil.info(Constantes.INI_CORCHETE + "Actividad 2. Obtener documentos Facturados SGA"
					+ Constantes.FIN_CORCHETE);
			consultarFacturas = consultaFacturasWS.consultarFacturas(header, buscarDocumentoRequest, propertiesExternos,
					audit, listaBill);
		} catch (Exception e) {
			logUtil.error(Constantes.EMPTY, e);
			errors = String.valueOf(e);
			return buscarDocumentoResponse = (BuscarDocumentoResponse) buscarDocumentoResponse.controlException(errors,
					Constantes.EMPTY, Constantes.EMPTY, Constantes.WS_NOMBRE3, Constantes.METODO_CONSULTAR_FACTURA,
					propertiesExternos);
		}
		if (consultarFacturas.getListaBill() != null && !consultarFacturas.getListaBill().getBill().isEmpty()) {
			List<BillType> listaBillType = new ArrayList<>();
			listaBillType = consultarFacturas.getListaBill().getBill();

			logUtil.info(Constantes.INI_CORCHETE + "Actividad 3. Validar Documento" + Constantes.FIN_CORCHETE);

			List<ListaParametro> listaParametro = new ArrayList<>();
			try {
				listaParametro = mgrRepository.buscarParametros(Constantes.EMPTY, propertiesExternos);
			} catch (Exception e) {
				logUtil.error(Constantes.EMPTY, e);
				errors = String.valueOf(e);
				return buscarDocumentoResponse = (BuscarDocumentoResponse) buscarDocumentoResponse.controlException(
						errors, propertiesExternos.getBdMgrSpListaParametro(), propertiesExternos.getBdMgrNombre(),
						Constantes.EMPTY, Constantes.EMPTY, propertiesExternos);
			}
			String desDoc = Constantes.EMPTY;
			for (ListaParametro listaParametro2 : listaParametro) {
				if (listaParametro2.getIdGrupo().equals(Constantes.TRECE)) {
					desDoc = validarDocumento(listaParametro2, buscarDocumentoRequest, propertiesExternos, desDoc);
				}
			}

			String numDoc = Util.remove1(buscarDocumentoRequest.getNumeroDocu());
			logUtil.info("numDoc: " + numDoc);

			logUtil.info(
					Constantes.INI_CORCHETE + "Actividad 4. Buscar Documento: " + numDoc + Constantes.FIN_CORCHETE);
			buscarDocumentoResponse = seteandoBuscarDocumento(listaBillType, buscarDocumentoResponse, numDoc, desDoc,
					propertiesExternos);
		} else {
			buscarDocumentoResponse.setCodigoRpta(propertiesExternos.getCodigoRespuestaIdf2());
			buscarDocumentoResponse.setMensajeRpta(propertiesExternos.getMensajeRespuestaIdf2());
		}

		logUtil.finMetodoDebug(Constantes.METODO_BUSCAR_DOC);
		return buscarDocumentoResponse;
	}

	public String validarDocumento(ListaParametro listaParametro2, BuscarDocumentoRequest buscarDocumentoRequest,
			PropertiesExternos propertiesExternos, String desDoc) {

		if (listaParametro2.getvValor2().equals(buscarDocumentoRequest.getTipoDoc()) && (!buscarDocumentoRequest
				.getTipoDoc().equals(propertiesExternos.getTiposDocumentosNdBscs())
				|| !buscarDocumentoRequest.getTipoDoc().equals(propertiesExternos.getTiposDocumentosRecBscs()))) {
			desDoc = listaParametro2.getvValor2();
			logUtil.info(Constantes.INI_CORCHETE + "Tipo de Documento valido: " + desDoc + Constantes.FIN_CORCHETE);
		}
		return desDoc;
	}

	public BuscarDocumentoResponse seteandoBuscarDocumento(List<BillType> listaBillType,
			BuscarDocumentoResponse buscarDocumentoResponse, String numDoc, String desDoc,
			PropertiesExternos propertiesExternos) {
		for (int i = 0; i < listaBillType.size(); i++) {
			if (listaBillType.get(i).getRecibo().equals(numDoc)
					&& listaBillType.get(i).getTipdoc().equals(desDoc.trim())) {
				String moneda = Util.identificarMoneda(listaBillType.get(i).getMoneda());
				buscarDocumentoResponse.setFecVenc(listaBillType.get(i).getFecVen());
				buscarDocumentoResponse.setTipoDoc(listaBillType.get(i).getTipdoc());
				buscarDocumentoResponse.setNumDoc(listaBillType.get(i).getRecibo());
				buscarDocumentoResponse.setFecEmi(listaBillType.get(i).getFecEmi());
				buscarDocumentoResponse.setMoneda(moneda);
				buscarDocumentoResponse.setMontoTotal(listaBillType.get(i).getValorOrig());
				buscarDocumentoResponse.setCodigoRpta(propertiesExternos.getCodigoRespuestaIdf0());
				buscarDocumentoResponse.setMensajeRpta(propertiesExternos.getMensajeRespuestaIdf0());
				break;
			} else {
				buscarDocumentoResponse.setCodigoRpta(propertiesExternos.getCodigoRespuestaIdf2());
				buscarDocumentoResponse.setMensajeRpta(propertiesExternos.getMensajeRespuestaIdf2());
			}
		}
		return buscarDocumentoResponse;
	}

}
