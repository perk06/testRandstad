package testRandstad;

import static org.junit.Assert.assertEquals;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;

import junit.framework.Assert;

public class WriteResults {
	private static String directory=System.getProperty("user.dir");
	private final String filepath=directory+File.separator+"result.txt";
	//directory.getAbsolutePath());
	 private static final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
	 

	public void writeResult(String strResult) {

		try(FileWriter fw = new FileWriter(filepath, true);
			    BufferedWriter bw = new BufferedWriter(fw);
				PrintWriter out = new PrintWriter(bw))   
			{
			    out.println(strResult);
			} catch (IOException e) {
				System.out.println("Error writing results");
			}
	
	}

	public void asertThis(String strScenario, String strRequirement, String strTestCaseName, String strExpected, String strActual, boolean isVerificationPoint) {
		String strPassed = "";
		String strResult = "";
		String isoDatePattern = "yyyy-MM-dd HH:mm:ss'Z'";
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(isoDatePattern);

		String dateString = simpleDateFormat.format(new Date(0));
		
		try {
			//Verification point assertions
			assertEquals(strExpected, strActual);
			if (isVerificationPoint) {strPassed = "PASSED";}
			//Other than the verification points
			else {strPassed = "";}
			

		} catch (AssertionError e) {
			strPassed = "FAILED";
			throw e;
		}
		String strSce = strScenario.substring(0, Math.min(strScenario.length(), 12));
		String strReq = strRequirement.substring(0, Math.min(strRequirement.length(), 50));
		
		strResult = strSce + ";" + strTestCaseName + ";" + strReq + ";" +  strPassed + ";" + dateString;
		writeResult(strResult);
		//assertEquals(strExpected, strActual);
	}

}