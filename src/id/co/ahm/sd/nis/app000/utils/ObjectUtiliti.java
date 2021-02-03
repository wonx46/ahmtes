package id.co.ahm.sd.nis.app000.utils;

import org.apache.commons.beanutils.PropertyUtils;

public class ObjectUtiliti {

//	copy dari a ke b
	public final static Object copyObject(Object origin, Object dest){
		try {
			PropertyUtils.copyProperties(dest, origin);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return dest;
	}
}
