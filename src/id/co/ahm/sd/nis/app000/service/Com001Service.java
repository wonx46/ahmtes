package id.co.ahm.sd.nis.app000.service;

import java.util.List;

import id.co.ahm.sd.nis.app000.model.AhmsdnisMstbrnd;
import id.co.ahm.sd.nis.app000.model.DtoSample;

public interface Com001Service {
	

	public void addMstBrand(AhmsdnisMstbrnd brand);

	public List<AhmsdnisMstbrnd> getAllBrand();

	public void deleteBrand(String brandId);

	public AhmsdnisMstbrnd updateBrand(AhmsdnisMstbrnd brand);

	public AhmsdnisMstbrnd getBrand(String brandId);
	
	public DtoSample getSampleData();
}
