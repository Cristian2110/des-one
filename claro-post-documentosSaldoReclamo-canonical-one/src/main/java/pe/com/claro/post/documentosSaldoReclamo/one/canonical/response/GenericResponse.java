package pe.com.claro.post.documentosSaldoReclamo.one.canonical.response;

import javax.ws.rs.core.Configuration;

/**
 * @author everis
 */
public class GenericResponse extends BaseResponse {

	private Object changeNameObject;

	public GenericResponse(Configuration configuration) {
		super(configuration);
	}

	public Object getChangeNameObject() {
		return changeNameObject;
	}

	public void setChangeNameObject(Object changeNameObject) {
		this.changeNameObject = changeNameObject;
	}
}
