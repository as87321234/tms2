package ca.mgisinc.tms2.form;

import lombok.Data;
import lombok.ToString;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Data
@ToString
public class BiometricCollectionForm {
	
	private String biometricId = BiometricCollectionForm.bioid();
	private String enrolmentBase64 = "VGhlIGxpdGxlIGZveCBqdW1wIHRoZSBiaWcgZmVuY2UgaXMgcmVkLg==";
	
	static private String bioid()  {
		
		java.util.Date date = new java.util.Date();
		
		// Setting the pattern
		SimpleDateFormat sm = new SimpleDateFormat("yyyyMMddHHmmss");
        
        // myDate is the java.util.Date in yyyy-mm-dd format
        // Converting it into String using formatter
		String strDate = sm.format(date);
        
        //Converting the String back to java.util.Date
		try {
			Date dt = sm.parse(strDate);
		} catch (ParseException e) {
			throw new RuntimeException(e);
		}
		return "000000" + strDate;
	}
}
