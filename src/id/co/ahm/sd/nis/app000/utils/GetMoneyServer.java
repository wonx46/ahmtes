/**
 * 
 */
package id.co.ahm.sd.nis.app000.utils;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;

/**
 * @author wonx
 *
 */
public class GetMoneyServer {
	
	public final static String setPersen(BigDecimal money) {
		if (money != null) {
			 DecimalFormat kursIndonesia = (DecimalFormat) DecimalFormat.getCurrencyInstance();
		     DecimalFormatSymbols formatRp = new DecimalFormatSymbols();

		        formatRp.setCurrencySymbol(" ");
		        formatRp.setMonetaryDecimalSeparator(',');
		        formatRp.setGroupingSeparator('.');

		        kursIndonesia.setDecimalFormatSymbols(formatRp);
		       
			return  kursIndonesia.format( money)+" %";
		}
		return "";
	}
	
	public final static String setTextNonMoney(BigDecimal money) {
		if (money != null) {
			 DecimalFormat kursIndonesia = (DecimalFormat) DecimalFormat.getCurrencyInstance();
		     DecimalFormatSymbols formatRp = new DecimalFormatSymbols();

		        formatRp.setMonetaryDecimalSeparator(',');
		        formatRp.setGroupingSeparator('.');

		        kursIndonesia.setDecimalFormatSymbols(formatRp);
		       
			return  kursIndonesia.format( money);

		} else {
			return "";
		}
	}

	public final static String setText(BigDecimal money) {
		if (money != null) {
//			 DecimalFormat kursIndonesia = (DecimalFormat) DecimalFormat.getCurrencyInstance();
			  DecimalFormat kursIndonesia = new DecimalFormat("###,###,###");
		     DecimalFormatSymbols formatRp = new DecimalFormatSymbols();

		        formatRp.setCurrencySymbol("Rp. ");
		        formatRp.setMonetaryDecimalSeparator(',');
		        formatRp.setGroupingSeparator('.');

		        kursIndonesia.setDecimalFormatSymbols(formatRp);
		       
			return  "Rp. "+kursIndonesia.format( money);
			
			
			
//			String lsMoney1 = "";
//			String lsMoney2 = "";
//
//			if (!money.toString().contains(".")) {
//				lsMoney1 += "00.";
//				for (int i = money.toString().length() - 1; i > -1; i--) {
//					lsMoney1 += money.toString().charAt(i);
//				}
//			} else {
//				boolean point = false;
//				money.toString().indexOf(".");
//				for (int i = money.toString().indexOf(".") + 2; i >= money
//						.toString().indexOf("."); i--) {
//					lsMoney1 += money.toString().charAt(i);
//				}
//
//				for (int i = money.toString().length() - 1; i > -1; i--) {
//					if (point) {
//						lsMoney1 += money.toString().charAt(i);
//					}
//					if (money.toString().charAt(i) == '.') {
//						point = true;
//					}
//				}
//			}
//
//			for (int i = 0; i < lsMoney1.length(); i++) {
//				if (i == 6 || i == 9 || i == 12 || i == 15 || i == 18)
//					lsMoney2 += ",";
//				lsMoney2 += lsMoney1.charAt(i);
//			}
//
//			lsMoney1 = "";
//
//			for (int i = lsMoney2.length() - 1; i > -1; i--) {
//				lsMoney1 += lsMoney2.toString().charAt(i);
//			}
//
//			return lsMoney1;
		} else {
			return "";
		}
	}

}
