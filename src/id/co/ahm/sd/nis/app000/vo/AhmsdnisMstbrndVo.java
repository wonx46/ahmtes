package id.co.ahm.sd.nis.app000.vo;

import java.util.Date;

import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

import id.co.ahm.sd.nis.app000.utils.MapXls;

public class AhmsdnisMstbrndVo {

	@MapXls(indexXls = 0)
	private String vbrndcd;

	@MapXls(indexXls = 1)
	private String vbrndnm;

	@MapXls(indexXls = 2)
	private String vbrndtyp;

	@MapXls(indexXls = 3)
	private String vurlbrnd;
	
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "MM/dd/yyyy")
	@MapXls(indexXls = 4)
	private Date dbgneff;
	
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "MM/dd/yyyy")
	@MapXls(indexXls = 5)
	private Date dlasteff;
	
	@MapXls(indexXls = 6)
	private String vcrea;

	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "MM/dd/yyyy")
	@MapXls(indexXls = 7)
	private Date dcrea;

	@MapXls(indexXls = 8)
	private String vmodi;

	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "MM/dd/yyyy")
	@MapXls(indexXls = 9)
	private Date dmodi;

	public String getVbrndcd() {
		return vbrndcd;
	}

	public void setVbrndcd(String vbrndcd) {
		this.vbrndcd = vbrndcd;
	}

	public String getVbrndnm() {
		return vbrndnm;
	}

	public void setVbrndnm(String vbrndnm) {
		this.vbrndnm = vbrndnm;
	}

	public String getVbrndtyp() {
		return vbrndtyp;
	}

	public void setVbrndtyp(String vbrndtyp) {
		this.vbrndtyp = vbrndtyp;
	}

	public String getVurlbrnd() {
		return vurlbrnd;
	}

	public void setVurlbrnd(String vurlbrnd) {
		this.vurlbrnd = vurlbrnd;
	}

	public Date getDbgneff() {
		return dbgneff;
	}

	public void setDbgneff(Date dbgneff) {
		this.dbgneff = dbgneff;
	}

	public Date getDlasteff() {
		return dlasteff;
	}

	public void setDlasteff(Date dlasteff) {
		this.dlasteff = dlasteff;
	}

	public String getVcrea() {
		return vcrea;
	}

	public void setVcrea(String vcrea) {
		this.vcrea = vcrea;
	}

	public Date getDcrea() {
		return dcrea;
	}

	public void setDcrea(Date dcrea) {
		this.dcrea = dcrea;
	}

	public String getVmodi() {
		return vmodi;
	}

	public void setVmodi(String vmodi) {
		this.vmodi = vmodi;
	}

	public Date getDmodi() {
		return dmodi;
	}

	public void setDmodi(Date dmodi) {
		this.dmodi = dmodi;
	}
	
	
}
