package id.co.ahm.sd.nis.app000.dao;

import java.util.List;

import id.co.ahm.sd.nis.app000.model.AhmsdnisMstbrnd;

public interface Com001AhmitMstBrndDao {

	public void addMstBrand(AhmsdnisMstbrnd brand);

	public List<AhmsdnisMstbrnd> getAllBrand();

	public void deleteBrand(String brandId);

	public AhmsdnisMstbrnd updateBrand(AhmsdnisMstbrnd brand);

	public AhmsdnisMstbrnd getBrand(String brandId);
}
