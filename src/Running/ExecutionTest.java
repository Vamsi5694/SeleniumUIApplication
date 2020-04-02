package Running;

import java.io.File;
import java.io.IOException;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import Common.Information;

public class ExecutionTest implements Information {

	String[] basicParams = new String[10];
	@BeforeTest
	@Parameters({"browser", "driverPath", "excel"})
	public void setup(String browserName, String driverPath, String excelName) throws IOException {
		basicParams[PLATFORM]= "Windows";
		basicParams[BROWSERNAME]= browserName;
		basicParams[EXCEL]= excelName;
		File f = new File(excelName);
		basicParams[PROPERTIES]= excelName.replace(".xlsx", ".properties");
		String time=new Timing().timeReport();
		System.out.println(f.getParent());
		new File(f.getParent()+"/reports/HTML_Reports").mkdirs();
		new File(f.getParent()+"/reports/XML_Reports").mkdirs();
		new File(f.getParent()+"/Screenshot_"+time).mkdirs();
		basicParams[EXTENTREPORT]= f.getParent()+"/reports/HTML_Reports/"+browserName+time+".html";
		basicParams[SCREENSHOT]= f.getParent()+"/"+browserName+"Screenshot_"+time;
		basicParams[BROWSERPATH]= driverPath;
		basicParams[SCENARIOS]= "SCENARIOS";
		basicParams[XML]= f.getParent()+"/reports/XML_Reports/"+browserName+time+".xml";;
	}
	
	@Test
	public void testcases() throws Exception {
		Final f = new Final();
		f.testing(basicParams);
		System.out.println(basicParams[EXTENTREPORT]);
	}
}
