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
import pe.com.claro.post.documentosSaldoReclamo.one.canonical.response.ConsultaEstadoCtaResponse;
import pe.com.claro.post.documentosSaldoReclamo.one.canonical.response.ListaConceptosCobs;
import pe.com.claro.post.documentosSaldoReclamo.one.canonical.response.ListaEquivalenciaCobs;

/**
 * @author everis
 *
 */
@Stateless
public class CobsRespository extends AbstractRepository<Object> implements Serializable {

  /**
   * 
   */
  private static final long serialVersionUID = 1L;

  private static final Logger LOG = LoggerFactory.getLogger(CobsRespository.class);

  @PersistenceContext(unitName = Constantes.PERSISTENCE_CONTEXT1)
  public void setPersistenceUnit00(final EntityManager em) {
    this.entityManager = em;
  }

  @Override
  protected Predicate[] getSearchPredicates(Root<Object> root, Object example) {
    return new Predicate[0];
  }

  public List<ListaConceptosCobs> obtenerConceptosCobs(ConsultaEstadoCtaResponse consultaEstCtaResponse,
      PropertiesExternos propertiesExternos) {

    String mensajeTransaccion = Constantes.EMPTY;
    String nombreSP = propertiesExternos.getBdCobsSpRecssMontoreclamo();

    LOG.info(RESTBUtilitarios.loggerInicio(mensajeTransaccion,
        Constantes.METODO_OBTENER_CONCEPTOS_COBS + Constantes.REPOSITORY));

    double tiempoInicio = System.currentTimeMillis();
    List<ListaConceptosCobs> lista = new ArrayList<>();
    StringBuffer storedProcedure = new StringBuffer();
    storedProcedure.append(propertiesExternos.getBdCobsOwner());
    storedProcedure.append(Constantes.PUNTO);
    storedProcedure.append(propertiesExternos.getBdCobsPkgSiacReclamos());
    storedProcedure.append(Constantes.PUNTO);
    storedProcedure.append(nombreSP);

    LOG.info(
        Constantes.INI_CORCHETE + Constantes.CONSULTANDO_BD + storedProcedure.toString() + Constantes.FIN_CORCHETE);

    Session session = entityManager.unwrap(Session.class);

    session.doWork(new Work() {
      @Override
      public void execute(final Connection connection) throws SQLException {

        LOG.info(RESTBUtilitarios.loggerConsultaSP(mensajeTransaccion, propertiesExternos.getBdCobsNombre(),
            propertiesExternos.getBdCobsJndi(), Integer.parseInt(propertiesExternos.getBdCobsExecuteTimeout()) * 1000));
        LOG.info(mensajeTransaccion + Constantes.EJECUTANDO + storedProcedure);

        ResultSet kResultado = null;

        try {

          CallableStatement call = connection
              .prepareCall(Constantes.LLAMADA_SP + storedProcedure.toString() + "(?,?,?,?,?,?)");
          call.setQueryTimeout(Integer.parseInt(ClaroUtil.verifiyNull(propertiesExternos.getBdCobsExecuteTimeout())));
          call.setString(Constantes.PI_K_DOC_RECLA, consultaEstCtaResponse.getNumDoc()); // K_DOC_RECLA
          call.setString(Constantes.PI_K_TIPO_DOC, consultaEstCtaResponse.getTipoDocSP()); // K_TIPO_DOC
          call.setString(Constantes.PI_K_COD_APLI, propertiesExternos.getAplicativoSp()); // K_COD_APLI
          call.registerOutParameter(Constantes.PO_K_CODIGO, java.sql.Types.VARCHAR); // K_CODIGO
          call.registerOutParameter(Constantes.PO_K_ERROR_MSG, java.sql.Types.VARCHAR); // K_ERROR_MSG
          call.registerOutParameter(Constantes.PO_CURSOR, OracleTypesHelper.INSTANCE.getOracleCursorTypeSqlType()); // PO_CURSOR

          call.execute();

          LOG.info(mensajeTransaccion + Constantes.EXITO_SP + storedProcedure);

          kResultado = (ResultSet) call.getObject(Constantes.PO_CURSOR);
          List<ListaConceptosCobs> cursor = null;

          LOG.info(Constantes.TIEMPO_EJECUCION_SP + RESTBUtilitarios.loggerFin(mensajeTransaccion,
              Constantes.INI_CORCHETE, Constantes.METODO_OBTENER_CONCEPTOS_COBS + Constantes.REPOSITORY, tiempoInicio));

          if (kResultado != null) {
            cursor = new ArrayList<>();
            int i = Constantes.VALOR_CERO;
            StringBuilder log = new StringBuilder();
            ListaConceptosCobs item;
            while (kResultado.next()) {
              item = new ListaConceptosCobs();
              item.setConcepto(ClaroUtil.verifiyNull(kResultado.getString(Constantes.SIETE)));
              item.setMonto(ClaroUtil.verifiyNull(kResultado.getString(Constantes.DOS)));
              cursor.add(item);
              i++;
            }
            lista.addAll(cursor);

            if (i == Constantes.VALOR_CERO) {
              LOG.info(mensajeTransaccion + Constantes.CORCHETE_LLAVE, Constantes.PO_CURSOR, "VACIO");
            } else {
              LOG.info(mensajeTransaccion + Constantes.PO_CURSOR + " Elementos .....  \n" + log.toString().trim());
            }
          } else {
            LOG.info(mensajeTransaccion + Constantes.CORCHETE_LLAVE, Constantes.PO_CURSOR, "NULL");
          }

        } catch (Exception e) {
          LOG.error(e.getMessage(),e);
          String descripcionError = String.valueOf(e.getMessage());

          controlException(descripcionError, nombreSP, propertiesExternos);

        } finally {
          LOG.info(RESTBUtilitarios.loggerFin(mensajeTransaccion, Constantes.INI_CORCHETE,
              Constantes.METODO_OBTENER_CONCEPTOS_COBS + Constantes.REPOSITORY, tiempoInicio));
        }
      }
    });
    return lista;

  }

  public List<ListaEquivalenciaCobs> obtenerEquivConceptos(PropertiesExternos propertiesExternos) {

    String mensajeTransaccion = Constantes.EMPTY;
    LOG.info(RESTBUtilitarios.loggerInicio(mensajeTransaccion,
        Constantes.METODO_OBTENER_EQUIV_COBS + Constantes.REPOSITORY));

    String nombreSP = propertiesExternos.getBdCobsSpObtieneListas();
    double tiempoInicio = System.currentTimeMillis();
    List<ListaEquivalenciaCobs> lista = new ArrayList<>();

    StringBuffer storedProcedure = new StringBuffer();
    storedProcedure.append(propertiesExternos.getBdCobsOwner());
    storedProcedure.append(Constantes.PUNTO);
    storedProcedure.append(propertiesExternos.getBdCobsPkgSiacGenerico());
    storedProcedure.append(Constantes.PUNTO);
    storedProcedure.append(nombreSP);

    LOG.info(
        Constantes.INI_CORCHETE + Constantes.CONSULTANDO_BD + storedProcedure.toString() + Constantes.FIN_CORCHETE);

    Session session = entityManager.unwrap(Session.class);

    session.doWork(new Work() {
      @Override
      public void execute(final Connection connection) throws SQLException {

        String nombreBD = propertiesExternos.getBdCobsNombre();
        LOG.info(RESTBUtilitarios.loggerConsultaSP(mensajeTransaccion, nombreBD, propertiesExternos.getBdCobsJndi(),
            Integer.parseInt(propertiesExternos.getBdCobsExecuteTimeout()) * 1000));
        LOG.info(mensajeTransaccion + Constantes.EJECUTANDO + storedProcedure);

        ResultSet kResultado = null;

        try {

          CallableStatement call = connection.prepareCall(Constantes.LLAMADA_SP + storedProcedure.toString() + "(?,?)");
          call.setQueryTimeout(Integer.parseInt(ClaroUtil.verifiyNull(propertiesExternos.getBdCobsExecuteTimeout())));

          call.setString(Constantes.P_ID_LISTA, propertiesExternos.getIdListaSp()); // P_ID_LISTA
          call.registerOutParameter(Constantes.P_CURSOR, OracleTypesHelper.INSTANCE.getOracleCursorTypeSqlType()); // P_CURSOR

          call.execute();

          LOG.info(mensajeTransaccion + Constantes.EXITO_SP + storedProcedure);

          kResultado = (ResultSet) call.getObject(Constantes.P_CURSOR);
          List<ListaEquivalenciaCobs> cursor = null;

          LOG.info(Constantes.TIEMPO_EJECUCION_SP + RESTBUtilitarios.loggerFin(mensajeTransaccion,
              Constantes.INI_CORCHETE, Constantes.METODO_OBTENER_EQUIV_COBS + Constantes.REPOSITORY, tiempoInicio));

          if (kResultado != null) {
            cursor = new ArrayList<>();
            int i = Constantes.VALOR_CERO;
            StringBuilder log = new StringBuilder();
            ListaEquivalenciaCobs item;
            while (kResultado.next()) {
              item = new ListaEquivalenciaCobs();
              item.setDescripcion(ClaroUtil.verifiyNull(kResultado.getString(Constantes.UNO)));
              item.setValor(ClaroUtil.verifiyNull(kResultado.getString(Constantes.DOS)));
              cursor.add(item);
              i++;
            }

            lista.addAll(cursor);

            if (i == Constantes.VALOR_CERO) {
              LOG.info(mensajeTransaccion + Constantes.CORCHETE_LLAVE, Constantes.P_CURSOR, "VACIO");
            } else {
              LOG.info(mensajeTransaccion + Constantes.P_CURSOR + " Elementos .....  \n" + log.toString().trim());
            }
          } else {
            LOG.info(mensajeTransaccion + Constantes.CORCHETE_LLAVE, Constantes.P_CURSOR, "NULL");
          }
        } catch (Exception e) {
          LOG.error(e.getMessage(),e);
          if (kResultado != null) {
            kResultado.close();
          }
          String descripcionError = String.valueOf(e.getMessage());
          controlException(descripcionError, nombreSP, propertiesExternos);
        } finally {
          LOG.info(RESTBUtilitarios.loggerFin(mensajeTransaccion, Constantes.INI_CORCHETE,
              Constantes.METODO_OBTENER_EQUIV_COBS + Constantes.REPOSITORY, tiempoInicio));
        }
      }
    });
    return lista;

  }

  protected void controlException(String descripcionError, String nombreSp, PropertiesExternos propertiesExternos) {
    Integer codigoRespuesta = Constantes.VALOR_CERO;
    String mensajeRespuesta = Constantes.EMPTY;
    if (descripcionError.toUpperCase(Locale.getDefault()).contains(Constantes.SQL_TIMEOUTEXCEPTION)) {
      codigoRespuesta = Integer.parseInt(propertiesExternos.getCodigoProcedureGenericoErrorIdt1());
      mensajeRespuesta = ClaroUtil.convertProperties(propertiesExternos.getMensajeProcedureGenericoErrorIdt1()
          .replace(Constantes.NOMBRESP, nombreSp).replace(Constantes.NOMBREDB, propertiesExternos.getBdCobsNombre()));
    } else {
      codigoRespuesta = Integer.parseInt(propertiesExternos.getCodigoProcedureGenericoErrorIdt2());
      mensajeRespuesta = ClaroUtil.convertProperties(propertiesExternos.getMensajeProcedureGenericoErrorIdt2())
          .replace(Constantes.NOMBREDB, propertiesExternos.getBdCobsNombre());
    }

    throw new NotFoundException(codigoRespuesta, codigoRespuesta, mensajeRespuesta, descripcionError);

  }
}
