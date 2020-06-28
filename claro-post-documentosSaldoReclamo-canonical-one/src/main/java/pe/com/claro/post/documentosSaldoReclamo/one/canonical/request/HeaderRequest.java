package pe.com.claro.post.documentosSaldoReclamo.one.canonical.request;

import pe.com.claro.common.resource.exception.NotFoundException;

import javax.ws.rs.core.Configuration;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * @author everis
 */
public class HeaderRequest {

  private Configuration conf;
  Locale locale = new Locale("es", "pe");
  private String idTransaccion;
  private String msgid;
  private Date timestamp;
  private String userId;
  private String accept;
  private String timeStampH;

  public HeaderRequest(HttpHeaders httpHeaders) {
    this.idTransaccion = httpHeaders.getHeaderString("idTransaccion");
    this.msgid = httpHeaders.getHeaderString("msgid");
    this.userId = httpHeaders.getHeaderString("userId");
    this.accept = httpHeaders.getAcceptableMediaTypes().toString();
    timeStampH = httpHeaders.getHeaderString("timestamp");
  }

  public HeaderRequest(HttpHeaders httpHeaders, Configuration conf) {
    this.idTransaccion = httpHeaders.getHeaderString("idTransaccion");
    this.msgid = httpHeaders.getHeaderString("msgid");
    this.userId = httpHeaders.getHeaderString("userId");
    this.accept = httpHeaders.getAcceptableMediaTypes().toString();
    timeStampH = httpHeaders.getHeaderString("timestamp");
    this.conf = conf;
  }

  public String getIdTransaccion() {
    return idTransaccion;
  }

  public void setIdTransaccion(String idTransaccion) {
    this.idTransaccion = idTransaccion;
  }

  public String getMsgid() {
    return msgid;
  }

  public void setMsgid(String msgid) {
    this.msgid = msgid;
  }

  public Date getTimestamp() {
    return timestamp;
  }

  public void setTimestamp(Date timestamp) {
    this.timestamp = timestamp;
  }

  public String getUserId() {
    return userId;
  }

  public void setUserId(String userId) {
    this.userId = userId;
  }

  public String getAccept() {
    return accept;
  }

  public void setAccept(String accept) {
    this.accept = accept;
  }

  public void isValid() {
    String camposFaltantes = "";
    if (timeStampH == null || !timeStampH.matches("\\d\\d\\d\\d[-]\\d\\d[-]\\d\\d[T]\\d\\d[:]\\d\\d[:]\\d\\dZ")) {
      camposFaltantes = "timestamp ";
    } else {
      Calendar calendar = Calendar.getInstance();
      try {
        calendar
            .setTime(new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ", locale).parse(timeStampH.replace("Z", "+0000")));
      } catch (ParseException e) {
        camposFaltantes = "timestamp ";
      }
      setTimestamp(calendar.getTime());
    }
    if (idTransaccion == null || idTransaccion.equalsIgnoreCase("")) {
      camposFaltantes += " idTransaccion";
    }
    if (msgid == null || msgid.equalsIgnoreCase("")) {
      camposFaltantes += " msgid";
    }
    if (userId == null || userId.equalsIgnoreCase("")) {
      camposFaltantes += " userId";
    }
    if (camposFaltantes.length() > 0) {
      throw new NotFoundException(Response.Status.OK.getStatusCode(),
          Integer.parseInt(conf.getProperty("codigoRespuesta.idf1").toString()),
          conf.getProperty("mensajeRespuesta.idf1").toString().replace("replace",
              "Request Header: " + camposFaltantes.trim()),
          null);
    }

  }

  @Override
  public String toString() {
    return "HeaderRequest{" + "idTransaccion='" + idTransaccion + '\'' + ", msgid='" + msgid + '\'' + ", timestamp="
        + timeStampH + ", userId='" + userId + '\'' + ", accept='" + accept + '\'' + '}';
  }

  public String toStringLog() {
    return "HeaderRequest{" + "idTransaccion='" + idTransaccion + '\'' + ", msgid='" + msgid + '\'';
  }
}
