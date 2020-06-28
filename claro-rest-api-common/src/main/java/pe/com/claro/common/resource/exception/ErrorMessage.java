package pe.com.claro.common.resource.exception;

import com.fasterxml.jackson.annotation.JsonInclude;
//import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.apache.commons.beanutils.BeanUtils;

import javax.xml.bind.annotation.XmlRootElement;
import java.lang.reflect.InvocationTargetException;


@XmlRootElement
@JsonPropertyOrder({"codigoRespuesta","mensajeRespuesta","mensajeError"})
public class ErrorMessage {

    private int codigoRespuesta;

    private String mensajeRespuesta;
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private String mensajeError;

    public ErrorMessage(Integer i, String mensajeRes, Exception exception) {
        this.codigoRespuesta = i;
        this.mensajeError = exception.getMessage().equalsIgnoreCase("") ? null : exception.getMessage();
        this.mensajeRespuesta = mensajeRes;
    }

    public int getCodigoRespuesta() {
        return codigoRespuesta;
    }

    public void setCodigoRespuesta(int codigoRespuesta) {
        this.codigoRespuesta = codigoRespuesta;
    }

    public String getMensajeRespuesta() {
        return mensajeRespuesta;
    }

    public void setMensajeRespuesta(String mensajeRespuesta) {
        this.mensajeRespuesta = mensajeRespuesta;
    }

    public String getMensajeError() {
        return mensajeError;
    }

    public void setMensajeError(String mensajeError) {
        this.mensajeError = mensajeError;
    }


    public ErrorMessage(ApiException ex) {
        try {
            BeanUtils.copyProperties(this, ex);
        } catch (IllegalAccessException e) {

            e.printStackTrace();
        } catch (InvocationTargetException e) {

            e.printStackTrace();
        }
    }


    public ErrorMessage(ComunesExceptionBean ex) {


        this.codigoRespuesta = ex.getCodigoRespuesta();
        this.mensajeError = ex.getMessage();
        this.mensajeRespuesta = ex.getMensajeRespuesta();
    }

    public ErrorMessage() {
    }
}
