/**
 * 
 */
package pe.com.claro.post.documentosSaldoReclamo.one.domain.repository;

import java.io.Serializable;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.ws.rs.core.Configuration;
import javax.ws.rs.core.Context;

import org.hibernate.Session;
import org.hibernate.dialect.OracleTypesHelper;
import org.hibernate.jdbc.Work;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import pe.com.claro.common.domain.repository.AbstractRepository;
import pe.com.claro.common.property.Constantes;
import pe.com.claro.common.property.PropertiesExternos;
import pe.com.claro.common.resource.exception.NotFoundException;
import pe.com.claro.common.resource.util.ClaroUtil;
import pe.com.claro.common.resource.util.RESTBUtilitarios;
import pe.com.claro.post.documentosSaldoReclamo.one.canonical.response.ListaParametro;

/**
 * @author everis
 *
 */
@Stateless
public class MgrRepository extends AbstractRepository<Object> implements Serializable {

  private static final long serialVersionUID = 1L;

  private static final Logger LOG = LoggerFactory.getLogger(MgrRepository.class);

  @Context
  private Configuration configuration;

  @PersistenceContext(unitName = Constantes.PERSISTENCE_CONTEXT2)
  public void setPersistenceUnit00(final EntityManager em) {
    this.entityManager = em;
  }

  @Override
  protected Predicate[] getSearchPredicates(Root<Object> root, Object example) {
    return new Predicate[0];
  }

  public List<ListaParametro> buscarParametros(String mensajeTransaccion, PropertiesExternos properties) {

    LOG.info(
        RESTBUtilitarios.loggerInicio(mensajeTransaccion, Constantes.METODO_BUSCAR_PARAMETROS + Constantes.REPOSITORY));

    double tiempoInicio = System.currentTimeMillis();
    List<ListaParametro> cursorFinal = new ArrayList<>();

    StringBuffer storedProcedure = new StringBuffer();
    storedProcedure.append(properties.getBdMgrOwner());
    storedProcedure.append(Constantes.PUNTO);
    storedProcedure.append(properties.getBdMgrPkgParamReclamos());
    storedProcedure.append(Constantes.PUNTO);
    storedProcedure.append(properties.getBdMgrSpListaParametro());

    LOG.info(
        Constantes.INI_CORCHETE + Constantes.CONSULTANDO_BD + storedProcedure.toString() + Constantes.FIN_CORCHETE);

    Session session = entityManager.unwrap(Session.class);

    session.doWork(new Work() {
      @Override
      public void execute(final Connection connection) throws SQLException {

        LOG.info(RESTBUtilitarios.loggerConsultaSP(mensajeTransaccion, properties.getBdMgrNombre(),
            properties.getBdMgrJndi(), Integer.parseInt(properties.getBdMgrExecuteTimeout()) * 1000));

        LOG.info(mensajeTransaccion + Constantes.EJECUTANDO + storedProcedure);
        String estado = properties.getEstadoParametro();
        ResultSet kResultado = null;

        try {

          CallableStatement call = connection
              .prepareCall(Constantes.CALL + storedProcedure.toString() + Constantes.CALL_2);
          call.setQueryTimeout(Integer.parseInt(ClaroUtil.verifiyNull(properties.getBdMgrExecuteTimeout())));

          call.setString(Constantes.PI_ESTADO, estado); // PI_ESTADO
          call.registerOutParameter(Constantes.PO_CURSOR_PARAM,
              OracleTypesHelper.INSTANCE.getOracleCursorTypeSqlType()); // PO_CURSOR
          call.execute();

          LOG.info(mensajeTransaccion + Constantes.EXITO_SP + storedProcedure);

          kResultado = (ResultSet) call.getObject(Constantes.PO_CURSOR_PARAM);
          List<ListaParametro> cursorParam = null;

          if (kResultado != null) {
            cursorParam = new ArrayList<>();
            int i = Constantes.VALOR_CERO;
            StringBuilder log = new StringBuilder();
            ListaParametro item;

            while (kResultado.next()) {
              item = new ListaParametro();
              item.setIdParam(ClaroUtil.verifiyNull(kResultado.getString(Constantes.UNO)));
              item.setIdGrupo(ClaroUtil.verifiyNull(kResultado.getString(Constantes.DOS)));
              item.setDescParam(ClaroUtil.verifiyNull(kResultado.getString(Constantes.TRES)));
              item.setOpciones(ClaroUtil.verifiyNull(kResultado.getString(Constantes.CUATRO)));
              item.setIdPadParam(ClaroUtil.verifiyNull(kResultado.getString(Constantes.CINCO)));
              item.setLlaveParam(ClaroUtil.verifiyNull(kResultado.getString(Constantes.SEIS)));
              item.setnValor1(ClaroUtil.verifiyNull(kResultado.getString(Constantes.SIETE)));
              item.setvValor2(ClaroUtil.verifiyNull(kResultado.getString(Constantes.OCHO)));
              cursorParam.add(item);
              i++;
            }
            cursorFinal.addAll(cursorParam);

            if (i == Constantes.VALOR_CERO) {
              LOG.info(mensajeTransaccion + Constantes.MSG_LLAVES, Constantes.MSG_PO_CURSOR, Constantes.TXT_VACIO);
            } else {
              LOG.info(mensajeTransaccion + Constantes.MSG_PO_CURSOR_ELE + log.toString().trim());
            }
          } else {
            LOG.info(mensajeTransaccion + Constantes.MSG_LLAVES, Constantes.MSG_PO_CURSOR, Constantes.TXT_NULL);
          }
          LOG.info(Constantes.TIEMPO_EJECUCION_SP + RESTBUtilitarios.loggerFin(mensajeTransaccion,
              Constantes.INI_CORCHETE, Constantes.METODO_BUSCAR_PARAMETROS + Constantes.REPOSITORY, tiempoInicio));
        } catch (Exception e) {
          LOG.error(e.getMessage(),e);
          String nombreSp = properties.getBdMgrSpListaParametro();
          if (kResultado != null) {
            kResultado.close();
          }
          String descripcionError = String.valueOf(e.getMessage());
          controlException(descripcionError, nombreSp, properties);
        } finally {
          LOG.info(RESTBUtilitarios.loggerFin(mensajeTransaccion, Constantes.INI_CORCHETE,
              Constantes.METODO_BUSCAR_PARAMETROS + Constantes.REPOSITORY, tiempoInicio));
        }
      }
    });
    return cursorFinal;
  }

  protected void controlException(String descripcionError, String nombreSp, PropertiesExternos properties) {
    Integer codigoRespuesta = Constantes.VALOR_CERO;
    String mensajeRespuesta = Constantes.EMPTY;
    if (descripcionError.toUpperCase(Locale.getDefault()).contains(Constantes.SQL_TIMEOUTEXCEPTION)) {
      codigoRespuesta = Integer.parseInt(properties.getCodigoRespuestaIdt1());
      mensajeRespuesta = ClaroUtil.convertProperties(properties.getMensajeRespuestaIdt1()
          .replace(Constantes.NOMBRESP, nombreSp).replace(Constantes.NOMBREDB, properties.getBdMgrNombre()));
    } else {
      codigoRespuesta = Integer.parseInt(properties.getCodigoRespuestaIdt2());
      mensajeRespuesta = ClaroUtil.convertProperties(properties.getMensajeRespuestaIdt2()).replace(Constantes.NOMBREDB,
          properties.getBdMgrNombre());
    }
    throw new NotFoundException(codigoRespuesta, codigoRespuesta, mensajeRespuesta, descripcionError);

  }
}
