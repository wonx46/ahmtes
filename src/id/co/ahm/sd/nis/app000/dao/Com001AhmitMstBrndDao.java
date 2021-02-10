package id.co.ahm.sd.nis.app000.dao;

import java.util.List;

import id.co.ahm.sd.nis.app000.model.AhmsdnisMstbrnd;
import id.co.ahm.sd.nis.app000.model.AhmsdnisMstbrndId;

public interface Com001AhmitMstBrndDao {

	public void addMstBrand(AhmsdnisMstbrnd brand);
	
	public void saveAll(List<AhmsdnisMstbrnd> brand);

	public List<AhmsdnisMstbrnd> getAllBrand();

	public void deleteBrand(AhmsdnisMstbrndId brandId);

	public AhmsdnisMstbrnd updateBrand(AhmsdnisMstbrnd brand);

	public AhmsdnisMstbrnd getBrand(AhmsdnisMstbrndId brandId);
}
