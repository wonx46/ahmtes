package id.co.ahm.sd.nis.app000.utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;





public class GlobalVariable {
	
	private static GlobalVariable instanze = null;
	
	public static String LANGUAGE_INA_FILE		= "lang_ina_file";
	
	public static String LANGUAGE_EN_FILE		= "lang_en_file";
	
	public static String APP_FILE_PROPERTIES	= "file_properties";
	
	private Calendar date = null;
	
	private Runnable timer = null;
	
	private Map<String, Object> variable = new HashMap<String, Object>(0);
	

	public static final String MENU = "menu";
	
	
	public static final String TANGGAL_SKRG_FDATE = "TANGGAL_SKRG_FDATE";
	
	//wonx
	
	
	private Locale locale = new Locale("ina");
	
	
	private Date openDate = null;
	
	public HashMap<String, Object> fileMap = new HashMap<String, Object>(0);
	
	
	private String ipAddress;
	
	
	public Locale getLocale() {
		return locale;
	}

	public void setLocale(String language) {
		this.locale = new Locale(language);
	}
	
	public String[] getFile(String filename) {
		return (String[]) fileMap.get(filename);
	}

	public void putFile(String filename, String[] items) {
		fileMap.put(filename, items);
	}
	
	public Date getOpenDate() {
		if (openDate == null) {
			openDate = Calendar.getInstance().getTime();
		}
		return openDate;
	}
	
	public void setOpenDate(Date openDate){
		this.openDate = openDate;
	}
	
	public static GlobalVariable getInstance()
	{
		if (instanze == null)
			instanze = new GlobalVariable();
		return instanze;
	}
	
	public void putVariable(String key, Object obj)
	{
		this.variable.put(key, obj);
		
	}
	
	public Object getVariable(String key)
	{
		return this.variable.get(key);
	}
	
	/**
	 * @param key -> Kata kunci
	 * @param obj -> Object yang akan disimpan
	 */
	public void put(String key, Object obj)
	{
		this.variable.put(key, obj);
		if(obj!=null){
//			createFileSession(key);	
			List<String> listAktifSession=(List<String>) get("GlobalVariable");
			if(listAktifSession==null){
				listAktifSession=new ArrayList<String>();
			}
			if(!isEksis(key,listAktifSession) ){
				listAktifSession.add(key);
			}
			
			this.variable.put("GlobalVariable", listAktifSession);
		}
		
	}
	
	private boolean isEksis(String key, List<String> listAktifSession) {
	for (String s : listAktifSession) {
		if (s.equals(key) || s==key ){
			return true;
		}
	}
		return false;
	}

	private void createFileSession(String key) {
		buatFolder();
		String path="session/sessi";
		String realpath = "."; 
		System.out.println(realpath);
		try{
			if(!new File(realpath).exists()){
				new File(realpath).createNewFile();
			}
			
			 FileWriter fstream = new FileWriter(realpath);
			  BufferedWriter out = new BufferedWriter(fstream);
			  String keySessionExist=getKeySession(realpath);
			  key=key+"\n"+keySessionExist;
			  System.out.println("menulis kata dalam session : "+key);
			  out.write(key);
			  out.close();
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}
	
	private void simpanKeFilediServer(String filename,String path,List<String> datas) {
		buatFolder();
//		String path="session/sessi";
		String realpath = "."; 
		System.out.println(realpath);
		try{
			if(!new File(realpath).exists()){
				new File(realpath).createNewFile();
			}
			
			 FileWriter fstream = new FileWriter(realpath);
			  BufferedWriter out = new BufferedWriter(fstream);
			  System.out.println("menulis kata dalam file : "+filename);
//			  for (String s : datas) {
//				  out.write(key);
//			}
			
			  out.close();
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}

	private void buatFolder() {
		String path="session/";
		String realpath = "."; 
		new File(realpath).mkdir();
		
	}

	private String getKeySession(String nmFile) throws Exception {
//		List<String> words=new ArrayList<String>();
		String words="";
		File f1 = new File(nmFile);
		 InputStream in = new FileInputStream(f1);
		  DataInputStream din = new DataInputStream(in);
		  BufferedReader br = new BufferedReader(new InputStreamReader(din));
		  String strLine;
		  while ((strLine = br.readLine()) != null)   {
			  words=words+"\n"+strLine;
		  }
		  in.close();
		return words;
	}

	public Object get(String key)
	{
		return this.variable.get(key);
	}
	
	public void clearVariable()
	{
		this.variable.clear();
	}
	
	public void setTime(Date timeNew) {
		if (date == null) {
			date = Calendar.getInstance();
			if (timeNew != null) {
				date.setTime(timeNew);
			}
			timeNew.setTime(timeNew.getTime());

			final Runnable timer = new Runnable() {
				public void run() {
					while (true) {
						try {
							Thread.sleep(1000);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
						date.add(Calendar.SECOND, 1);
					}
				}
			};
			new Thread(timer).start();
		}
	}

	
	public Date getServerTime() {
		return  (Date) get(TANGGAL_SKRG_FDATE);
	}
	
	
	public String getIpAddress() {
		if (ipAddress == null) {
			try {
				InetAddress thisIp = InetAddress.getLocalHost();
				ipAddress = thisIp.getHostAddress();
			} catch (UnknownHostException e) {
				e.printStackTrace();
			}
		}
		return ipAddress;
	}

	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}
//	
}
