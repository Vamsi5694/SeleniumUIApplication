package Keywords;

import java.util.Properties;

import org.openqa.selenium.WebDriver;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import AdditionalSetup.ResultUpdation;
import AdditionalSetup.Objects;
import Common.Information;


public class GoToUrl implements Information{
	WebDriver driver;
	Objects obj;
	boolean cond=false;
public GoToUrl(WebDriver driver,ExtentTest test) throws Exception{
	this.driver=driver;
	obj = new Objects(driver, test);
}
 public String testing(Properties p,String[] record,int row, String sh, int resultRow,String[] imp) throws Exception{
	 try {
		driver.get(p.getProperty(record[OBJECTNAME]));
			String title_name=driver.getTitle();
			String actual="Problem loading page";
			if(title_name.equals(actual.trim())){
				cond= false;
				obj.getExcelResult().setData(cond,row,sh,resultRow,Information.FAIL,imp);
				obj.getExtentTest().log(LogStatus.FAIL, record[STEPNUMBER],"URL is not running. Please run again");	// for failure purpose
				return Information.FAIL;
			}
			else{
				cond= true;
				obj.getExcelResult().setData(cond,row,sh,resultRow,Information.PASS,imp);
				obj.getExtentTest().log(LogStatus.PASS, record[STEPNUMBER],"Description: "+record[DESCRIPTION]+"<html><br></html> Output: "+record[EXPECTED_COLUMN]);				// for pass purpose
				return Information.PASS;
			}
	} catch (Exception e) {
		// TODO Auto-generated catch block
		ResultUpdation noe=new ResultUpdation(obj);
		noe.testing(p, record, row, sh, resultRow, Information.FAIL+": Driver is failed to run '"+p.getProperty(record[OBJECTNAME])
				+"' because of "+e.getClass().getSimpleName(),imp);
		return FAIL;
	}
 }
 
 public String urlVerify(Properties p,String[] record,int row, String sh, int resultRow,String[] imp) throws Exception{
	 try {
		driver.get(p.getProperty(record[OBJECTNAME]));
			String title_name=driver.getCurrentUrl();
			VALUE_STORAGE.put(record[OBJECTNAME]+VALUE_END, title_name);
			if(title_name.equals(record[VALUE])){
				cond= true;
				obj.getExcelResult().setData(cond,row,sh,resultRow,Information.FAIL,imp);
				obj.getExtentTest().log(LogStatus.PASS, record[STEPNUMBER], record[DESCRIPTION]);	
				return Information.PASS;
			}
			else{
				cond= false;
				obj.getExcelResult().setData(cond,row,sh,resultRow,Information.FAIL,imp);
				obj.getExtentTest().log(LogStatus.FAIL, record[STEPNUMBER],"Description: "+ "Current URL verification is failed");				// for pass purpose
				return Information.FAIL;
			}
	} catch (Exception e) {
		// TODO Auto-generated catch block
		ResultUpdation noe=new ResultUpdation(obj);
		noe.testing(p, record, row, sh, resultRow, Information.FAIL+": Driver is failed to run '"+p.getProperty(record[OBJECTNAME])
				+"' because of "+e.getClass().getSimpleName(),imp);
		VALUE_STORAGE.put(record[OBJECTNAME]+VALUE_END, ERROR);
		return FAIL;
	}
 }
}
