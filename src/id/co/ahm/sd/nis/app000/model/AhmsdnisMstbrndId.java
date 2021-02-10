package id.co.ahm.sd.nis.app000.model;

import java.io.Serializable;

import javax.persistence.Embeddable;

@Embeddable
public class AhmsdnisMstbrndId implements Serializable {

	private String vbrndcd;
	
	private String vbrndpk;

	public String getVbrndcd() {
		return vbrndcd;
	}

	public void setVbrndcd(String vbrndcd) {
		this.vbrndcd = vbrndcd;
	}

	public String getVbrndpk() {
		return vbrndpk;
	}

	public void setVbrndpk(String vbrndpk) {
		this.vbrndpk = vbrndpk;
	}
	
	
}
