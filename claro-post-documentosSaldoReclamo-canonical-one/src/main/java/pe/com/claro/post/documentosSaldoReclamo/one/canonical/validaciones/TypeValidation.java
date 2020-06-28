package pe.com.claro.post.documentosSaldoReclamo.one.canonical.validaciones;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.FIELD, ElementType.PARAMETER })
public @interface TypeValidation {

	String FECHA = "dd/MM/yyyy";
	String FECHA_HORA = "dd/MM/yyyy hh:mm:ss";

	boolean cEntero() default false;

	boolean cDecimal() default false;

	boolean cDate() default false;

	String cFormatDate() default "";
}
