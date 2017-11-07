package br.ufba.mata55.banco.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface MapeadoPor {
	Class DAO();
	String CampoOrigem();
	String CampoDestino();
}
