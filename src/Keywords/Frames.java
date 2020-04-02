package Keywords;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.relevantcodes.extentreports.ExtentTest;

import AdditionalSetup.ResultUpdation;
import AdditionalSetup.Objects;
import Common.Information;


public class Frames implements Information{
	WebDriver driver;
	boolean cond=false;
	Objects obj;
	
public Frames(WebDriver driver,ExtentTest test) throws Exception{
	this.driver=driver;
	obj = new Objects(driver, test);
}
public String testing(Properties p,String[] record,int row, String sh, int resultRow,String[] imp ) throws Exception{
	try{
		WebElement frame=driver.findElement(obj.getLocators().getObject(p,record[OBJECTNAME],record[OBJECTTYPE]));
		driver.switchTo().frame(frame);
		obj.getJavaScript().highlight(frame);
		VALUE_STORAGE.put(record[OBJECTNAME]+VALUE_END, "true");
		return PASS;
		}
		catch(Exception ne){
			
			ResultUpdation noe=new ResultUpdation(obj);
			noe.withoutBy(p, record, row, sh, resultRow, FRAME_WARNING,imp);
			ne.printStackTrace();
			VALUE_STORAGE.put(record[OBJECTNAME]+VALUE_END, "false");
			return Information.INFO;
		}
}

public String window(Properties p,String[] record,int row, String sh, int resultRow,String[] imp ) throws Exception{
	try{
		driver.switchTo().defaultContent();
		VALUE_STORAGE.put(record[OBJECTNAME]+VALUE_END, "true");
		return PASS;
		}
		catch(Exception ne){
			
			ResultUpdation noe=new ResultUpdation(obj);
			noe.withoutBy(p, record, row, sh, resultRow, PARENT_WARNING,imp);
			ne.printStackTrace();
			VALUE_STORAGE.put(record[OBJECTNAME]+VALUE_END, "false");
			return Information.INFO;
		}
}

}
