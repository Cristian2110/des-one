package pe.com.claro.post.documentosSaldoReclamo.one.canonical.util;

import org.modelmapper.AbstractConverter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateConvertModelMapper extends AbstractConverter<String, Date> {

	@Override
	protected Date convert(String s) {
		if (s == null) {
			return null;
		}
		try {
			return new SimpleDateFormat("dd/MM/yyyy hh:mm:ss").parse(s);
		} catch (ParseException ignored) {
			try {
				return new SimpleDateFormat("dd/MM/yyyy").parse(s);
			} catch (ParseException e) {
				return null;
			}
		}
	}
}
