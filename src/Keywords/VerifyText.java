package Keywords;

import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import AdditionalSetup.Objects;
import AdditionalSetup.ResultUpdation;
import Common.Information;

public class VerifyText implements Information {
	WebDriver driver;
	boolean cond = false;
	Objects obj;
	ResultUpdation ru;

	public VerifyText(WebDriver driver, ExtentTest test) throws Exception {
		this.driver = driver;
		obj = new Objects(driver, test);
		ru = new ResultUpdation(obj);
	}

	public String testing(Properties p, String[] record, int row, String sh, int resultRow, String[] imp) {
		try {
			int count = 0;
			String data = "";
			String[] onames = record[OBJECTNAME].split(";");
			String[] otypes = record[OBJECTTYPE].split(";");
			String text[] = record[VALUE].split(";");
			for (int i = 0; i < onames.length; i++) {
				try {
					WebElement verifyPass = obj.getFluent()
							.fluentWait(obj.getLocators().getObject(p, onames[i], otypes[i]));
					obj.getJavaScript().highlight(verifyPass);
					String actual_text = verifyPass.getText().trim();
					text[i] = text[i].replace("_", " ");
					VALUE_STORAGE.put(onames[i] + VALUE_END, actual_text);
					if (actual_text.equals(text[i].trim())) {
						obj.getJavaScript().mark(verifyPass);
						data = data + actual_text + "\n";
						count++;
					} else {
						if (obj.getVisible().isElementPresent(obj.getLocators().getObject(p, onames[i], otypes[i])))
							obj.getJavaScript().mark(verifyPass);
					}
				} catch (Exception vr) {
					vr.printStackTrace();
				}
			}
			if (count == onames.length) {
				cond = true;
				obj.getExcelResult().setData(cond, row, sh, resultRow, Information.PASS, imp);
				obj.getResultScreenshot().demand(record[STEPNUMBER], "Web page is showing " + data, Information.PASS,
						imp);
			} else {
				cond = false;
				obj.getExcelResult().setData(cond, row, sh, resultRow, Information.FAIL, imp);
				obj.getResultScreenshot().demand(record[STEPNUMBER], "Web page is showing " + data, Information.FAIL,
						imp);
			}
			for (int j = 0; j < onames.length; j++) {
				try {
					if (obj.getVisible().isElementPresent(obj.getLocators().getObject(p, onames[j], otypes[j]))) {
						WebElement verify = obj.getFluent()
								.fluentWait(obj.getLocators().getObject(p, onames[j], otypes[j]));
						obj.getJavaScript().nomark(verify);
					}
				} catch (Exception vr) {
					vr.printStackTrace();
				}
			}
			if (cond)
				return Information.PASS;
			else
				return Information.FAIL;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return Information.FAIL;
		}
	}

	public String negative(Properties p, String[] record, int row, String sh, int resultRow, String[] imp)
			throws Exception {
		try {
			By neg = obj.getLocators().getObject(p, record[OBJECTNAME], record[OBJECTTYPE]);
			if (obj.getVisible().isElementPresent(neg)) {
				WebElement negative = driver.findElement(neg);
				String negmsg = negative.getText();
				VALUE_STORAGE.put(record[OBJECTNAME] + VALUE_END, "false");
				obj.getExcelResult().setData(false, row, sh, resultRow, Information.FAIL, imp);
				obj.getJavaScript().mark(negative);
				obj.getResultScreenshot().demand(record[STEPNUMBER], "Web page is showing " + negmsg, Information.FAIL,
						imp);
				obj.getJavaScript().nomark(negative);
				return Information.FAIL;
			} else {
				cond = true;
				VALUE_STORAGE.put(record[OBJECTNAME] + VALUE_END, "true");
				obj.getExtentTest().log(LogStatus.PASS, record[STEPNUMBER], "Verification is passed");
				obj.getExcelResult().setData(cond, row, sh, resultRow, Information.PASS, imp);
				return Information.PASS;
			}
		} catch (Exception ne) {
			ru.testing(p, record, row, sh, resultRow, Information.FAIL, imp);
			ne.printStackTrace();
			VALUE_STORAGE.put(record[OBJECTNAME] + VALUE_END, "false");
			return Information.FAIL;
		}
	}

	public String inputVerify(Properties p, String[] record, int row, String sh, int resultRow, String[] imp)
			throws Exception {
		try {
			WebElement element = obj.getFluent()
					.fluent(obj.getLocators().getObject(p, record[OBJECTNAME], record[OBJECTTYPE]));
			String elementText = obj.getJavaScript().getInputText(element);
			VALUE_STORAGE.put(record[OBJECTNAME] + VALUE_END, elementText);
			if (elementText.equals(record[VALUE])) {
				cond = true;
				obj.getExtentTest().log(LogStatus.PASS, record[STEPNUMBER],
						record[VALUE] + " matches with expected value " + elementText);
				obj.getExcelResult().setData(cond, row, sh, resultRow, Information.PASS, imp);
				return Information.PASS;
			} else {
				cond = false;
				obj.getExtentTest().log(LogStatus.FAIL, record[STEPNUMBER],
						record[VALUE] + " is not matching with expected value " + elementText);
				obj.getExcelResult().setData(cond, row, sh, resultRow, Information.FAIL, imp);
				return Information.FAIL;
			}
		} catch (Exception e) {
			ru.testing(p, record, row, sh, resultRow, Information.FAIL, imp);
			e.printStackTrace();
			VALUE_STORAGE.put(record[OBJECTNAME] + VALUE_END, "false");
			return Information.FAIL;
		}
	}

	public String dropdownVerify(Properties p, String[] record, int row, String sh, int resultRow, String[] imp)
			throws Exception {
		try {
			WebElement element = obj.getFluent()
					.fluent(obj.getLocators().getObject(p, record[OBJECTNAME], record[OBJECTTYPE]));
			Select sel = new Select(element);
			String elementText = sel.getFirstSelectedOption().getText();
			VALUE_STORAGE.put(record[OBJECTNAME] + VALUE_END, elementText);
			if (elementText.equals(record[VALUE])) {
				cond = true;
				obj.getExtentTest().log(LogStatus.PASS, record[STEPNUMBER],
						record[VALUE] + " matches with expected value " + elementText + " in dropdown");
				obj.getExcelResult().setData(cond, row, sh, resultRow, Information.PASS, imp);
				return Information.PASS;
			} else {
				cond = false;
				obj.getExtentTest().log(LogStatus.FAIL, record[STEPNUMBER],
						record[VALUE] + " is not matching with expected value " + elementText + " in dropdown");
				obj.getExcelResult().setData(cond, row, sh, resultRow, Information.FAIL, imp);
				return Information.FAIL;
			}
		} catch (Exception e) {
			ru.testing(p, record, row, sh, resultRow, Information.FAIL, imp);
			e.printStackTrace();
			VALUE_STORAGE.put(record[OBJECTNAME] + VALUE_END, "false");
			return Information.FAIL;
		}
	}
}
