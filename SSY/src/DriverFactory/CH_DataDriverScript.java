package DriverFactory;

import java.util.Iterator;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.annotations.Test;

import CommonFuncLibrary.FunctionLibrary;
import Utilities.ExcelFileUtil;
import Utilities.PropertyFileUtil;

public class CH_DataDriverScript {

	
	public static WebDriver driver;
	@Test
	public static void ch_enroll() throws Exception {
		
		ExcelFileUtil excel = new ExcelFileUtil();
		
		driver=startBrowser(driver);
		
		driver=BP_login(driver, PropertyFileUtil.getValueForKey("BP_Uname"), PropertyFileUtil.getValueForKey("BP_PWD"));
		
		
		
		
		
		for(int i = 1; i <= excel.rowCount("Enroll_CH_Data"); i++) {
			
			
			try {
				
				FunctionLibrary.clicks(driver, "xpath", ".//a[text()='Manage Cardholder'][@href='#']", "10");
				FunctionLibrary.clicks(driver, "xpath", ".//a[text()='Enroll Cardholder'][@class='ng-binding']", "10");
				
				FunctionLibrary.typeAction(driver, "xpath", "//input[@name='firstName']", excel.getData("Enroll_CH_Data", i, 0));
				
				FunctionLibrary.typeAction(driver, "xpath", "//input[@name='lastname'][@type='text']", excel.getData("Enroll_CH_Data", i, 1));
				
				FunctionLibrary.typeAction(driver, "xpath", "//input[@name='dob']", excel.getData("Enroll_CH_Data", i, 2));
				FunctionLibrary.typeAction(driver, "xpath", "//input[@name='email'][@type='email']", excel.getData("Enroll_CH_Data", i, 3));
				FunctionLibrary.typeAction(driver, "xpath", "//input[@name='empId'][@ng-model='userDetail.externalReferenceId'][@type='text']", excel.getData("Enroll_CH_Data", i, 4));
				FunctionLibrary.typeAction(driver, "xpath", "//input[@name='addressLine1'][@type='text']", excel.getData("Enroll_CH_Data", i, 5));
				FunctionLibrary.typeAction(driver, "xpath", "//input[@name='addressLine2'][@type='text']", excel.getData("Enroll_CH_Data", i, 6));
				FunctionLibrary.selectAction(driver, "xpath", "//select[@name='country'][@data-ng-change='updateCountry()']", excel.getData("Enroll_CH_Data", i, 7));
				FunctionLibrary.typeAction(driver, "xpath", "//input[@name='state'][@type='text'][@data-ng-model='userDetail.state']", excel.getData("Enroll_CH_Data", i, 8));
				FunctionLibrary.typeAction(driver, "xpath", "//input[@name='city'][@type='text']", excel.getData("Enroll_CH_Data", i, 9));
				FunctionLibrary.typeAction(driver, "xpath", "//input[@name='zipcode'][@type='text'][@ng-model='userDetail.zipCode']", excel.getData("Enroll_CH_Data", i, 10));
				FunctionLibrary.typeAction(driver, "xpath", "//input[@name='mobileNumber'][@type='text'][@required='required']", excel.getData("Enroll_CH_Data", i, 11));
				
				FunctionLibrary.pageScrollDown(driver);
				FunctionLibrary.pageScrollDown(driver);
				
				FunctionLibrary.clicks(driver, "xpath", "(//small[@class='ng-binding'])[3]", "10");
				
				FunctionLibrary.clicks(driver, "xpath", "//button[@type='submit'][text()='Enroll Cardholder']", "10");
				
				FunctionLibrary.clicks(driver, "xpath", "//button[@ng-if='modalOptions.actionButtonText'][text()='Ok'][@type='button']", "10");
				Thread.sleep(5000);
				
				//FunctionLibrary.textValidation(driver, "xpath", ".//button[@class='btn btn-blue btn100 ng-binding ng-scope'][@ng-if='modalOptions.actionButtonText']", excel.getData("Enroll_CH_Data", i, 12));
				
				FunctionLibrary.textValidation(driver, "xpath", ".//p[@class='font padt10 ng-binding']", excel.getData("Enroll_CH_Data", i, 12));
				
				FunctionLibrary.clicks(driver, "xpath", "//button[@class='btn btn-blue btn100 ng-binding ng-scope'][@ng-if='modalOptions.actionButtonText']", "10");
				
				excel.setData("Enroll_CH_Data", i, 13, "PASS");
				
			} catch (Exception e) {
				
				
				excel.setData("Enroll_CH_Data", i, 13, "FAIL");
			}
			
			
			
			
			
			
			}
		
		
		
	}
	
	
	

	public static WebDriver startBrowser(WebDriver driver) throws Exception 
	{
		if(PropertyFileUtil.getValueForKey("Browser").equalsIgnoreCase("firefox"))
		{
			
			System.setProperty("webdriver.gecko.driver", "CommonJarFiles/geckodriver.exe");
			                                               
			                                              
			
			driver=new FirefoxDriver();
		}
		else if(PropertyFileUtil.getValueForKey("Browser").equalsIgnoreCase("chrome"))
		{
			System.setProperty("webdriver.chrome.driver", "CommonJarFiles/chromedriver.exe");
			driver=new ChromeDriver();
		}
		else 
		{
			System.setProperty("webdriver.ie.driver","CommonJarFiles/IEDriverServer.exe");
			driver=new InternetExplorerDriver();
		}
		
		driver.manage().window().maximize();
		 
		driver.get(PropertyFileUtil.getValueForKey("BP_URL"));
		
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		
		return driver;
	}
	
	public static WebDriver BP_login(WebDriver d,String uname,String pwd) throws Exception {
		
		//d.findElement(By.xpath("//input[@type='email'][@id='inputEmail3'][@placeholder='Enter Email Address']")).sendKeys(uname);
		
		FunctionLibrary.typeAction(d, "xpath", "//input[@type='email'][@id='inputEmail3'][@placeholder='Enter Email Address']", uname);
		
		//d.findElement(By.xpath("//input[@type='password'][@id='inputPassword3'][@placeholder='Enter Password ']")).sendKeys(pwd);
		
		FunctionLibrary.typeAction(d, "xpath", "//input[@type='password'][@id='inputPassword3'][@placeholder='Enter Password ']", pwd);
		
		FunctionLibrary.clicks(d, "xpath", "//button[@type='button'][@class='btn btn-blue2 btnFull ng-binding'][@ng-click='loginSubmit()']", "10");
		
		FunctionLibrary.clicks(d, "xpath", "//a[@aria-hidden='true'][@data-ng-click='modalOptions.close()']", "10");
		
		
		
		
		return d;
		
		
		
	}
	

}
