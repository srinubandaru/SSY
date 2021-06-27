package DriverFactory;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.annotations.Test;

import CommonFuncLibrary.FunctionLibrary;
import Utilities.ExcelFileUtil;
import Utilities.PropertyFileUtil;

public class VisaClosure {
	
	public static WebDriver driver;
	@Test
	public static void cardClosure() throws Exception {
		
		ExcelFileUtil excel = new ExcelFileUtil();
		
		driver=startBrowser(driver);
		driver=BackOffice_login(driver, PropertyFileUtil.getValueForKey("Backofc_uname"), PropertyFileUtil.getValueForKey("Backofc_pwd"));
		
		
		for (int i = 1; i <=excel.rowCount("Visa_Closure"); i++) {
			try {
				
				FunctionLibrary.typeAction(driver, "xpath", "//input[@tooltip='Enter WC Proxy'][@placeholder='WcProxy']", excel.getData("Visa_Closure", i, 0));
						//excel.getData("Visa_Closure", i, 0));
				FunctionLibrary.clicks(driver, "xpath", "//button[@ng-click='csrSubmit()'][text()='Search']", "10");
				
				FunctionLibrary.clicks(driver, "xpath", "(//a[contains(@href, '#/admin/csr/userDashboard/')])[1]", "10");
				Thread.sleep(2000);
				FunctionLibrary.clicks(driver, "xpath", "//a[@href='#/admin/csr/statusChange'][text()='Status Change']", "10");
				Thread.sleep(2000);
				FunctionLibrary.pageScrollDown(driver);
				//Thread.sleep(2000);
				
				//FunctionLibrary.clicks(driver, "xpath", "//select[@id='ChooseCard']", "10");
				
			//	FunctionLibrary.selectAction(driver, "xpath", "(//option[@value='0'])[3]", "10");
				
				FunctionLibrary.selectAction(driver, "xpath", "//select[@id='ChooseCard']/option[@value='0']", "10");
				
				FunctionLibrary.selectAction(driver, "xpath", "//option[@value='0'][text()='BLOCKED']", "10");
				FunctionLibrary.selectAction(driver, "xpath", "//option[@value='8'][text()='VisaClosure']", "10");
				FunctionLibrary.typeAction(driver, "xpath", "(//textarea[@name='Description'][@placeholder='Description'])[2]", excel.getData("Visa_Closure", i, 1));
				
				FunctionLibrary.clicks(driver, "xpath", "(//button[@ng-click='continueTo()'][text()='Continue'])[2]", "10");
						
				
				
				
				excel.setData("Visa_Closure", i, 2, "PASS");
				
			} catch (Exception e) {
				excel.setData("Visa_Closure", i, 2, "FAIL");
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
		 
		driver.get(PropertyFileUtil.getValueForKey("BackOfc_URL"));
		
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		
		return driver;
	}
	
	
	
public static WebDriver BackOffice_login(WebDriver d,String backoffuname,String backoffpwd) throws Exception {
		
		//d.findElement(By.xpath("//input[@type='email'][@id='inputEmail3'][@placeholder='Enter Email Address']")).sendKeys(uname);
		
		FunctionLibrary.typeAction(d, "xpath", "//input[@ng-model='login.userName'][@type='text'][@placeholder='User Name']", backoffuname);
		
		//d.findElement(By.xpath("//input[@type='password'][@id='inputPassword3'][@placeholder='Enter Password ']")).sendKeys(pwd);
		
		FunctionLibrary.typeAction(d, "xpath", "//input[@ng-model='login.password'][@type='password'][@placeholder='Password']", backoffpwd);
		
		FunctionLibrary.clicks(d, "xpath", "//button[@class='btn btn-primary submit_btn'][@type='submit'][@ng-click='submit()']", "10");
		
		FunctionLibrary.clicks(d, "xpath", "//a[@href='#/admin/csr'][text()='CSR']", "10");
		
		//Thread.sleep(2000);
		
		
		
		
		
		return d;
		
		
		
	}
	

}
