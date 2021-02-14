package id.co.ahm.sd.nis.app000.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.springframework.scheduling.annotation.Scheduled;

public class DemoCron {
	
	
	public DemoCron(){
		System.out.println("now: "+getNow());
	}
	
	// untuk geneate cron
	//https://docs.oracle.com/cd/E12058_01/doc/doc.1014/e12030/cron_expressions.htm
	
	@Scheduled(cron="*/5 * * * * ?")
    public void tiapLimaDetik()
    {
        System.out.println("Method executed at every 5 seconds. Current time is :: "+ getNow());
    }
	
	//Fire at 10:45 AM every day
	@Scheduled(cron="0 45 10 ? * *")
    public void terjadwal()
    {
        System.out.println("yeay terjadwal Current time is :: "+ getNow());
    }


	private String getNow() {
		SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		Calendar cal = Calendar.getInstance();
		return formater.format(cal.getTime());
	}
}
