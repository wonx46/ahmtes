package id.co.ahm.sd.nis.app000.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import id.co.ahm.sd.nis.app000.dao.Com001AhmitMstBrndDao;
import id.co.ahm.sd.nis.app000.model.AhmsdnisMstbrnd;
import id.co.ahm.sd.nis.app000.model.DtoSample;
import id.co.ahm.sd.nis.app000.service.Com001Service;


@Service("com001Service")
@Transactional(readOnly = true)
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
	public void  deleteBrand(String brandId) {
		com001AhmitMstBrndDao.deleteBrand(brandId);
	}

	public  AhmsdnisMstbrnd getBrand(String brandId) {
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


}
