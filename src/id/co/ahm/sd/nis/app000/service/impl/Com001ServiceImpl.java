package id.co.ahm.sd.nis.app000.service.impl;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EmbeddedId;
import javax.persistence.Id;

import org.apache.commons.beanutils.PropertyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import id.co.ahm.sd.nis.app000.dao.Com001AhmitMstBrndDao;
import id.co.ahm.sd.nis.app000.enumz.EnumKey;
import id.co.ahm.sd.nis.app000.model.AhmsdnisMstbrnd;
import id.co.ahm.sd.nis.app000.model.AhmsdnisMstbrndId;
import id.co.ahm.sd.nis.app000.model.DtoSample;
import id.co.ahm.sd.nis.app000.service.Com001Service;
import id.co.ahm.sd.nis.app000.utils.MapXls;


@Service("com001Service")
@Transactional
public class Com001ServiceImpl implements Com001Service {

	@Autowired
	private Com001AhmitMstBrndDao com001AhmitMstBrndDao;

	@Override
	@Transactional
	public void addMstBrand(AhmsdnisMstbrnd brand) {
		com001AhmitMstBrndDao.addMstBrand(brand);
	}

	@Override
	@Transactional
	public List<AhmsdnisMstbrnd> getAllBrand() {
		return com001AhmitMstBrndDao.getAllBrand();
	}

	@Override
	@Transactional
	public void  deleteBrand(AhmsdnisMstbrndId brandId) {
		com001AhmitMstBrndDao.deleteBrand(brandId);
	}

	public  AhmsdnisMstbrnd getBrand(AhmsdnisMstbrndId brandId) {
		return com001AhmitMstBrndDao.getBrand(brandId);
	}

	public AhmsdnisMstbrnd updateBrand(AhmsdnisMstbrnd brand) {
		// TODO Auto-generated method stub
		return com001AhmitMstBrndDao.updateBrand(brand);
	}

	public void setcom001AhmitMstBrndDAO(Com001AhmitMstBrndDao com001AhmitMstBrndDao) {
		this.com001AhmitMstBrndDao = com001AhmitMstBrndDao;
	}

	public DtoSample getSampleData() {
		return new DtoSample(123l, "Joni", "Playboy");
	}
	
	 public String getId(Object b) {
			Field[] declaredFields = b.getClass().getDeclaredFields();
			try {
				for (Field field : declaredFields) {
					Annotation[] annotations = field.getAnnotations();
					for (Annotation ann : annotations) {
						if(ann instanceof Id){
							return String.valueOf(PropertyUtils.getProperty(b, field.getName()));
						}else if(ann instanceof EmbeddedId){
							String[] id = getIdEmbed(field);
							if(id != null){
								String idembed = "";
								for (String fname : id) {
									idembed = idembed + String.valueOf(PropertyUtils.getProperty(b, field.getName()+"."+fname)) + "#";
								}
								return idembed.substring(0, idembed.length()-1);
							}else{
								return null;
							}
						}
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			return null;
		}
	 
	
	 private String[] getIdEmbed(Field field) {
		Annotation[] annotations = field.getAnnotations();
		for (Annotation ann : annotations) {
			if(ann instanceof MapXls){
				MapXls map = (MapXls) ann;
				if(map.embedId().length()>0 && map.embedId().contains("#")){
					String[] split = map.embedId().split("#");
					String[] idnya = new String[split.length];
					int i = 0;
					for (String s : split) {
						String name = s.split("-")[0];
						idnya[i] = name;
						i++;
					}
					return idnya;
					
					
				}
			}
		}
		return null;
	}

	@Override
	 public void saveToDb(List<Object> list, String key){
		 if(key.equalsIgnoreCase(EnumKey.BRAND.getString())){
			 saveAllBrand(list);
		 }
	 }
	 
	
	 @Transactional
	 private void saveAllBrand(List<Object> list) {
		 System.out.println("saveAllBrand");
			List<AhmsdnisMstbrnd> lobj = new ArrayList<>();
			for (Object object : list) {
				AhmsdnisMstbrnd b = (AhmsdnisMstbrnd)object;
//				System.out.println(b.getVbrndcd()+" "+b.getVbrndnm());
				lobj.add(b );
			}
			
			this.com001AhmitMstBrndDao.saveAll(lobj);
			
		}


}
