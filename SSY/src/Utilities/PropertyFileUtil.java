
package Utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.Properties;

public class PropertyFileUtil 
{
	public static String getValueForKey(String key) throws Exception
	{
		
		Properties configProporties=new Properties();
		
		configProporties.load(new FileInputStream(new File("./PropertiesFile/Environment.properties")));
		
		int randomNum =  (int)(Math.random() * 100000); 
		
		String duplicateData = String.valueOf(randomNum);
		
		String randomEmailCH="SnehalCH"+duplicateData+"@yopmail.com";
		
		String randomEmailBP="SnehalBP"+duplicateData+"@yopmail.com";
		
		
		
		insetValueForKey("PayeeID", duplicateData);
			
		insetValueForKey("RandomMailCH", randomEmailCH);
		
		insetValueForKey("RandomMailBP", randomEmailBP);
		
		
		
		
		return configProporties.getProperty(key);
		
	}
	
	
	
	
	
	
	public static void insetValueForKey(String newKey,String valueText) throws Exception
	{
		
		Properties insertProp=new Properties();
		
		
		insertProp.load(new FileInputStream(new File("./PropertiesFile/Environment.properties")));
		insertProp.setProperty(newKey,valueText);
		
		//System.out.println(newKey+"  "+valueText  +"  in Property class");
		
		insertProp.store(new FileOutputStream("./PropertiesFile/Environment.properties"), "Inserting Card Holder UserID");
		
		
	}
	
	public static void insetValueForreferenceID(String newKey,String valueText) throws Exception
	{
		
		Properties insertProp=new Properties();
		
		
		insertProp.load(new FileInputStream(new File("./PropertiesFile/Environment.properties")));
		insertProp.setProperty(newKey,valueText);
		
		System.out.println(newKey+"  "+valueText  +"  in Property class");
		
		insertProp.store(new FileOutputStream("./PropertiesFile/Environment.properties"), "Inserting referenceID");
		
		
	}
}



