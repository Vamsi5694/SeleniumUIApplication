package Running;
import java.util.Properties;

import com.relevantcodes.extentreports.ExtentTest;

import Common.Browser;
import Common.Information;


public class GettingData implements Information{
ExtentTest test;
Browser browserObject;
KeywordObjects keywordObjects;

public GettingData(Browser browserObject, ExtentTest test) throws Exception{
	this.test=test;
	this.browserObject =browserObject;
	keywordObjects = new KeywordObjects(browserObject, test);
}

public String operation(Properties p,String[] column,int row, String sh, int resultRow,String[] imp) {
	try{
		
	switch(column[KEYWORD].toUpperCase()){
	case "OPEN BROWSER":
		try {
			browserObject.webBrowser();
			return Information.PASS;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			return Information.FAIL;
		}
				
	case "CLOSE BROWSER":
		browserObject.getWebDriver().quit();
		browserObject.setNullWebDriver();
		return Information.PASS;
		
	case "GOTOURL":										// for run the url
		return keywordObjects.getGoToUrl().testing(p,column,row,sh,resultRow,imp);
		
	case "WRITETEXT":									// for typing in application
		return keywordObjects.getWriteText().testing(p, column, row, sh, resultRow, imp);
		
	case "CLICK":										// for clicking on any element
		return keywordObjects.getClick().testing(p, column, row, sh, resultRow, imp);
				
	case "OPTIONAL":
		return keywordObjects.getClick().optional(p, column, row, sh, resultRow, imp);
				
	case "PRESS":
		return keywordObjects.getClick().press(p, column, row, sh, resultRow, imp);
		
	case "RANDOM CLICK":
		return keywordObjects.getClick().randomClick(p, column, row, sh, resultRow, imp);
	
	case "CHECKBOX":								// for clicking on checkboxes
		return keywordObjects.getCheckBox().testing(p, column, row, sh, resultRow, imp);
		
	case "POPUP":									// for going to other window
		return keywordObjects.getPopup().switching(p, column, row, sh, resultRow, imp);
		
	case "UNPOPUP":									// for close the current window
		return keywordObjects.getPopup().switchingback(p, column, row, sh, resultRow, imp);
		
	case "DROPDOWN":									// select option from drop down
		return keywordObjects.getDropdown().testing(p, column, row, sh, resultRow, imp);
		
	case "OPTION FIELDS":
		return keywordObjects.getClick().selection(p, column, row, sh, resultRow, imp);
		
	case "TEXT":
	case "CALENDAR":
		return keywordObjects.getWriteText().texting(p, column, row, sh, resultRow, imp);
		
	case "REPEAT":
		return keywordObjects.getRepeat().testing(p, column, row, sh, resultRow, imp);
		
	case "SIZE":
		return keywordObjects.getSize().testing(p, column, row, sh, resultRow, imp);
		
	case "ALERT":									// for accept, close or write anything in alert boxes 
		return keywordObjects.getAlertBox().testing(p, column, row, sh, resultRow, imp);
		
	case "VERIFY TEXT":								// for verifying text in application
		return keywordObjects.getVerifyText().testing(p, column, row, sh, resultRow, imp);
		
	case "NEGATIVE VERIFY":
		return keywordObjects.getVerifyText().negative(p, column, row, sh, resultRow, imp);
		
	case "MOUSEHOVER":										// for mousehover on element
		return keywordObjects.getMouseHover().testing(p, column, row, sh, resultRow, imp);
		
	case "SCROLL":											// scroll to particular element
		return keywordObjects.getScrolling().testing(p, column, row, sh, resultRow, imp);
		
	case "SLEEP":											// sleep for sometime
		keywordObjects.getWaiting().sleep(column[VALUE]);
		return "";
		
	case "WAIT":
		keywordObjects.getWaiting().rest(p,column[OBJECTNAME],column[OBJECTTYPE], column[VALUE]);
		return "";
	
	case "SCREEN ENABLE":	
	case "SCREENSHOT":									// take the screenshot before clicking on element
		return keywordObjects.getScreenEnable().testing(p, column, row, sh, resultRow, imp);
		
	case "ELEMENT STATE":										
		return keywordObjects.getelementState().testing(p, column, row, sh, resultRow, imp);
		
	case "LIST":											// counting and verifying number of images in one page
		return keywordObjects.getListObjects().testing(p, column, row, sh, resultRow, imp);
		
	case "COUNT":
		return keywordObjects.getListObjects().counting(p, column, row, sh, resultRow, imp);
		
	case "SCROLL END":								// scroll to end of the page for number of times
		return keywordObjects.getScrolling().end(column);
		
	case "COPY TEXT":
		return keywordObjects.getCopyAndPaste().testing(p, column, row, sh, resultRow, imp);
		
	case "PASTE":
		return keywordObjects.getCopyAndPaste().paste(p, column, row, sh, resultRow, imp);
		
	case "FRAME":									// for frame purpose
		return keywordObjects.getFrames().testing(p, column, row, sh, resultRow, imp);
		
	case "MAIN":									// coming from frame
		return keywordObjects.getFrames().window(p, column, row, sh, resultRow, imp);
		
	case "DETAILS":
		return keywordObjects.getDetails().testing(p, column, row, sh, resultRow, imp);
		
	case "MENU":
		return keywordObjects.getMenu().testing(p, column, row, sh, resultRow, imp);
		
	case "UPLOAD":									// upload a file
		return keywordObjects.getUpload().testing(p, column, row, sh, resultRow, imp);
		
	case "DOWNLOAD":							// download a file
		return keywordObjects.getDownload().testing(p, column, row, sh, resultRow, imp);
		
	case "PRINT":
		return keywordObjects.getPrint().getValue(column); 
		
	case "TAB":							// for new, shift or close, open with new tab
		return keywordObjects.getTabs().testing(p, column, row, sh, resultRow, imp);
		
	case "VERIFY INPUT TEXT":
		return keywordObjects.getVerifyText().inputVerify(p, column, row, sh, resultRow, imp);
		
	case "VERIFY DROPDOWN":
		return keywordObjects.getVerifyText().dropdownVerify(p, column, row, sh, resultRow, imp);
		
	case "DROPDOWN OPTIONS":
		return keywordObjects.getDropdown().dropdownOptions(p, column, row, sh, resultRow, imp);
		
	case "FORECAST TABLE":
		return keywordObjects.getForecastTable().testing(p, column, row, sh, resultRow, imp);
		
	case "FORECAST TABLE VALIDATION":
		return keywordObjects.getForecastTableValidation().testing(p, column, row, sh, resultRow, imp);
		
	default:
			return keywordObjects.getDef().existing(p,column, row, sh, resultRow,imp);
			
	}
	}
	catch(Exception e){
		
		e.printStackTrace();
		return Information.FAIL;
	}
}

}
