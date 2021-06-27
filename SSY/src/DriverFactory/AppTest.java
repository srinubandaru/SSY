
package DriverFactory;

import org.testng.annotations.Test;

public class AppTest
{	

	@Test
	public void kickStart()
	{		
		DriverScript t = new DriverScript();
		
		try 
		{
			t.startTest();
		} 
		catch (Exception e)
		{			
			e.printStackTrace();
		}
	}	
}




//By Snehal.B Automation Engineer