package id.co.ahm.sd.nis.app000.enumz;

public enum EnumKey implements IEnum {
	
	BRAND("brand"),
        POPULATION("population");
	
	
	private String name;

	private EnumKey(String name) {
		this.name = name;
	}
	
	public String getString() {
		// TODO Auto-generated method stub
		return name;
	}

	public String[] getItems() {
		// TODO Auto-generated method stub
		return new String[] {  BRAND.getString(),POPULATION.toString() };
	}
	
}
