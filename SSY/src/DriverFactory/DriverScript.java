package DriverFactory;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

import CommonFuncLibrary.FunctionLibrary;
import Utilities.ExcelFileUtil;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class DriverScript 
{
	public static WebDriver driver;
	//// Open Appliaction for PAN and CVV
	//WebDriver dr1;
	ExtentReports report;
	//Extentreports will create html report
	ExtentTest logger;
	//To create logs in html (pass or fail )
	
	//Driver SCript will drive total framework (Reading excel data and performing operations and generating html report, excel result ex PASS oR Fail)

	@Test
	public void startTest() throws Exception
	{
		ExcelFileUtil excel = new ExcelFileUtil();
		
		for (int i = 1; i <=excel.rowCount("MasterTestCases"); i++) 
		{	
			String ModuleStatus = null;
			
			
			if (excel.getData("MasterTestCases", i, 2).equalsIgnoreCase("Y")) 
			{			
				// Define Module Name
				String TCModule=excel.getData("MasterTestCases", i, 1);	
				
				report = new ExtentReports("./Reports/"+TCModule+".html "+ FunctionLibrary.getRandomNumberFromDate());
				logger = report.startTest(TCModule);
				
				int rowcount = excel.rowCount(TCModule);
				
				for(int j=1;j<=rowcount;j++)
				{
					String Description = excel.getData(TCModule, j, 0);
					String Object_Type = excel.getData(TCModule, j, 1);
					String Locator_Type = excel.getData(TCModule, j, 2);
					String Locator_Value = excel.getData(TCModule, j, 3);
					String Test_Data = excel.getData(TCModule, j, 4); 
					
					
				 try 
				 {						
								
					if(Object_Type.equalsIgnoreCase("Browser"))
					{
						driver=FunctionLibrary.startBrowser(driver,Test_Data);
						
						//(3)
						//driver=FunctionLibrary.startBrowser(driver, Test_Data);
						
						logger.log(LogStatus.INFO, Description);
					}
					
					if(Object_Type.equalsIgnoreCase("openApp"))
					{
						FunctionLibrary.openApplication(driver,Test_Data);
						logger.log(LogStatus.INFO, Description);
					}
					
					
					if(Object_Type.equalsIgnoreCase("typeText"))
					{
						FunctionLibrary.typeAction(driver, Locator_Type, Locator_Value, Test_Data);
						logger.log(LogStatus.INFO, Description);
					}
					
					if(Object_Type.equalsIgnoreCase("enterText"))
					{
						FunctionLibrary.enterText(driver, Locator_Type, Locator_Value, Test_Data);
						logger.log(LogStatus.INFO, Description);
					}
					
					/*if(Object_Type.equalsIgnoreCase("clearText"))
					{
						FunctionLibrary.typeAction(driver, Locator_Type, Locator_Value, Test_Data);
						logger.log(LogStatus.INFO, Description);
					}
					*/
					

					/*if(Object_Type.equalsIgnoreCase("pagination"))
					{
						FunctionLibrary.typeAction(driver, Locator_Type, Locator_Value, Test_Data);
						logger.log(LogStatus.INFO, Description);
					}*/
					
					
					
					
					if(Object_Type.equalsIgnoreCase("typeAction_key"))
					{
						Thread.sleep(4000);
						FunctionLibrary.typeAction_key(driver, Locator_Type, Locator_Value, Test_Data);
						logger.log(LogStatus.INFO, Description);
					}
					
					if(Object_Type.equalsIgnoreCase("selectAction"))
					{
						FunctionLibrary.selectAction(driver, Locator_Type, Locator_Value, Test_Data);
						logger.log(LogStatus.INFO, Description);
					}
					
					if(Object_Type.equalsIgnoreCase("click"))
					{
						FunctionLibrary.clickAction(driver, Locator_Type, Locator_Value);
						logger.log(LogStatus.INFO, Description);
					}	
					if(Object_Type.equalsIgnoreCase("doubleClickAction"))
					{
						FunctionLibrary.doubleClickAction(driver, Locator_Type, Locator_Value);
						logger.log(LogStatus.INFO, Description);
					}
					if(Object_Type.equalsIgnoreCase("clickSpecificCondition"))
					{
						FunctionLibrary.clickSpecificCondition(driver, Locator_Type, Locator_Value);
						logger.log(LogStatus.INFO, Description);
					}
					
					if(Object_Type.equalsIgnoreCase("titleValidate"))
					{
						FunctionLibrary.titleValidation(driver,Test_Data);
						logger.log(LogStatus.INFO, Description);
					}
					
					if(Object_Type.equalsIgnoreCase("elementValidate"))
					{
						FunctionLibrary.elementValidation(driver, Locator_Type, Locator_Value, Test_Data);
						logger.log(LogStatus.INFO, Description);
					}
					
					if(Object_Type.equalsIgnoreCase("waitElement"))
					{
						FunctionLibrary.waitforelement(driver, Locator_Type, Locator_Value, Test_Data);
						logger.log(LogStatus.INFO, Description);
					}
					
					if(Object_Type.equalsIgnoreCase("waitforClick"))
					{
						FunctionLibrary.waitforClick(driver, Locator_Type, Locator_Value, Test_Data);
						logger.log(LogStatus.INFO, Description);
					}
					
					if(Object_Type.equalsIgnoreCase("clicks"))
					{
						FunctionLibrary.clicks(driver, Locator_Type, Locator_Value, Test_Data);
						logger.log(LogStatus.INFO, Description);
					}
					
					
					if(Object_Type.equalsIgnoreCase("closeApp"))
					{
						FunctionLibrary.closeBrowser(driver);
						logger.log(LogStatus.INFO, Description);
					}
					
					if(Object_Type.equalsIgnoreCase("pageScrollDown"))
					{
						FunctionLibrary.pageScrollDown(driver);
						logger.log(LogStatus.INFO, Description);
					}
					
					if(Object_Type.equalsIgnoreCase("pageScrollUP"))
					{
						FunctionLibrary.pageScrollUP(driver);
						logger.log(LogStatus.INFO, Description);
					}
					
					
					if(Object_Type.equalsIgnoreCase("mouseOver"))
					{
						FunctionLibrary.mouseActions(driver, Locator_Type, Locator_Value);
						logger.log(LogStatus.INFO, Description);
					}
					
					
					if(Object_Type.equalsIgnoreCase("takeText"))
					{
						FunctionLibrary.takeText(driver, Locator_Type, Locator_Value, Test_Data);
						logger.log(LogStatus.INFO, Description);
					}
					
					if(Object_Type.equalsIgnoreCase("takeReferenceID"))
					{
						FunctionLibrary.takeReferenceID(driver, Locator_Type, Locator_Value, Test_Data);
						logger.log(LogStatus.INFO, Description);
					}
					
					
					if(Object_Type.equalsIgnoreCase("tableValidation"))
					{
						System.out.println("tableValidation in Driver Script ");
						
						FunctionLibrary.tableValidation(driver, Locator_Type, Locator_Value, Test_Data);
						logger.log(LogStatus.INFO, Description);
					}
					
					if(Object_Type.equalsIgnoreCase("searchText"))
					{
						System.out.println("tableValidation in Driver Script ");
						
						FunctionLibrary.searchText(driver, Locator_Type, Locator_Value, Test_Data);
						logger.log(LogStatus.INFO, Description);
					}
					
					if(Object_Type.equalsIgnoreCase("DBValidation"))
					{
						System.out.println("DBValidation in Driver Script ");
						
						FunctionLibrary.DBValidation();
						logger.log(LogStatus.INFO, Description);
					}
					
					if(Object_Type.equalsIgnoreCase("dBEmailValidation"))
					{
						System.out.println("dBEmailValidation in Driver Script ");
						
						FunctionLibrary.dBEmailValidation();
						logger.log(LogStatus.INFO, Description);
					}
					
					if(Object_Type.equalsIgnoreCase("dBMobileValidation"))
					{
						System.out.println("dBMobileValidation in Driver Script ");
						
						FunctionLibrary.dBEmailValidation();
						logger.log(LogStatus.INFO, Description);
					}
					
/*//Continue Click in BackOffice
					
					if(Object_Type.equalsIgnoreCase("tableElementClick"))
					{
						FunctionLibrary.tableEleClick(driver, Locator_Type, Locator_Value, Test_Data);
						logger.log(LogStatus.INFO, Description);
					}
					*/
					
					
					if(Object_Type.equalsIgnoreCase("dateSelector"))
					{
						FunctionLibrary.dateSelector(driver, Locator_Type, Locator_Value, Test_Data);
						logger.log(LogStatus.INFO, Description);
					}
					
					if(Object_Type.equalsIgnoreCase("currentDateSelection"))
					{
						FunctionLibrary.currentDateSelection(driver, Locator_Type, Locator_Value, Test_Data);
						logger.log(LogStatus.INFO, Description);
					}
					
					if(Object_Type.equalsIgnoreCase("fileUpload"))
					{
						FunctionLibrary.fileUpload(driver, Test_Data);
						logger.log(LogStatus.INFO, Description);
					}
					
					if(Object_Type.equalsIgnoreCase("multiWindow"))
					{
						FunctionLibrary.multiWindow(driver, Locator_Type, Locator_Value, Test_Data);
						logger.log(LogStatus.INFO, Description);
					}
					
					if(Object_Type.equalsIgnoreCase("handlePDFNewWindow"))
					{
						FunctionLibrary.handlePDFNewWindow(driver, Locator_Type, Locator_Value, Test_Data);
						logger.log(LogStatus.INFO, Description);
					}
					
					
					if(Object_Type.equalsIgnoreCase("closeNewTab"))
					{
						FunctionLibrary.closeNewTab(driver, Locator_Type, Locator_Value, Test_Data);
						logger.log(LogStatus.INFO, Description);
					}
					
					if(Object_Type.equalsIgnoreCase("webEleValidate"))
					{
						FunctionLibrary.webEleValidate(driver, Locator_Type, Locator_Value, Test_Data);
						logger.log(LogStatus.INFO, Description);
					}
					
					if(Object_Type.equalsIgnoreCase("textValidation"))
					{
						FunctionLibrary.textValidation(driver, Locator_Type, Locator_Value, Test_Data);
						logger.log(LogStatus.INFO, Description);
					}
					
					
					
					
					
					
					
					
					
					
					
					excel.setData(TCModule, j, 5, "Pass");	
					
					ModuleStatus="true";
					
					logger.log(LogStatus.PASS, Description + " Pass");
					
				}
				
				catch(Exception e)
				{
					
					System.out.println("ERROR ###"+Description+"####  : "+e);
					
					excel.setData(TCModule, j, 5, "Fail");						
					
					ModuleStatus="False";
					
					logger.log(LogStatus.FAIL, Description + " Fail");
					
					// For Generating Screenshot
					
					File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
					
					FileUtils.copyFile(scrFile, new File("./Screenshots/"+" "+Description+"_"+FunctionLibrary.getRandomNumberFromDate()+".jpg"));
					
					// Add Screenshot image into Extent Report
					
					String image = logger.addScreenCapture("./Screenshots/"+" "+Description+"_"+FunctionLibrary.getRandomNumberFromDate()+".jpg");
					
					logger.log(LogStatus.FAIL, TCModule, image);
					
					System.out.println("Driver Script Exception Error Message: "+e);
					
					break;					
				}
				
				catch(AssertionError a)
				{
					System.out.println("ERROR ###"+Description+"####  : "+a);
					//System.out.println("ERROR AssertionError ###### : "+a);
					excel.setData(TCModule, j, 5, "Fail");						
						
					ModuleStatus="False";
						
					logger.log(LogStatus.FAIL, Description + " Fail");
						
					// For Generating Screenshot
						
					File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
						
					FileUtils.copyFile(scrFile, new File("./Screenshots/"+" "+Description+"_"+FunctionLibrary.getRandomNumberFromDate()+".jpg"));
						
					// Add Screenshot image into Extent Report
						
					String image = logger.addScreenCapture("./Screenshots/"+" "+Description+"_"+FunctionLibrary.getRandomNumberFromDate()+".jpg");
						
					logger.log(LogStatus.FAIL, TCModule, image);
						
					break;	
				}
					
			  }
				
				if(ModuleStatus.equalsIgnoreCase("true"))
				{
					excel.setData("MasterTestCases", i, 3, "Pass");
				}
				else
				{
					excel.setData("MasterTestCases", i, 3, "Fail");
				}				
		    }
			else 
			{		
				excel.setData("MasterTestCases", i, 3, "Not Executed");
			}
			
			
			try {
				System.out.println("Report in catch block logger");
				report.endTest(logger);
				report.flush();	
				
			} catch (Exception e2) {
				
			}
			
					
		}
	}
}