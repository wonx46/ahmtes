package id.co.ahm.sd.nis.app000.model;

public class DtoSample {
	private Long id;
	private String name;
	private String descr;
	
	public DtoSample(Long id, String name, String descr) {
		super();
		this.id = id;
		this.name = name;
		this.descr = descr;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescr() {
		return descr;
	}
	public void setDescr(String descr) {
		this.descr = descr;
	}
	
	
}
