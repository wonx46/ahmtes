package id.co.ahm.sd.nis.app000.utils;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface MapXls {

	public int indexXls();
	
//	public String returnClassObj() default "";
	
	
}
